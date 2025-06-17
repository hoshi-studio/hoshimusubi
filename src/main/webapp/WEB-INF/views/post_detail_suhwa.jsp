<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/postdetailsuhwa.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<div class="wrapper">
    <main class="main-content">
        <!-- 여기에 메인 콘텐츠를 작성하세요 -->
         <div class="detail-container">
            <h1 class="page-title">${post.title}</h1>

            <div class="post-box">
                <h2 class="post-title"></h2>
                <div class="post-meta">
					<a href="${pageContext.request.contextPath}/mypage?userId=${post.user_Id}" style="text-decoration: none; color: inherit;">
					    <span>작성자: ${post.nickname}</span>
					</a>

                    <span>작성일: ${formattedCreatedAt}</span>
                    <span>조회수: ${post.views}</span>
                    <c:choose>
					    <c:when test="${post.likedByCurrentUser}">
					        <img src="${pageContext.request.contextPath}/resources/img/like.png" 
					             id="like-btn"
					             data-userId = "${user.id}" 
					             data-post-id="${post.id}" 
					             data-liked="true" 
					             style="width: 24px; height: 24px; cursor: pointer;" />
					    </c:when>
					    <c:otherwise>
					        <img src="${pageContext.request.contextPath}/resources/img/unlike.png" 
					             id="like-btn"
					             data-userId = "${user.id}"   
					             data-post-id="${post.id}" 
					             data-liked="false" 
					             style="width: 24px; height: 24px; cursor: pointer;" />
					    </c:otherwise>
					</c:choose>
                    <span id="like-count">${post.likeCount}</span>
                    <span >💬 </span>
                    <span id="comment-count">${post.commentCount}</span>
                    <c:choose>
					    <c:when test="${post.bookmarkedByCurrentUser}">
					        <img src="${pageContext.request.contextPath}/resources/img/save.png" 
					             id="bookmark-btn" 
					             data-userId = "${user.id}" 
					             data-post-id="${post.id}" 
					             data-bookmarked="true" 
					             style="width: 24px; height: 24px; cursor: pointer;" />
					    </c:when>
					    <c:otherwise>
					        <img src="${pageContext.request.contextPath}/resources/img/dontsave.png" 
					             id="bookmark-btn" 
					             data-userId = "${user.id}"  
					             data-post-id="${post.id}" 
					             data-bookmarked="false" 
					             style="width: 24px; height: 24px; cursor: pointer;" />
					    </c:otherwise>
					</c:choose>
                </div>
                
				<c:if test="${loginUserId eq post.user_Id}">
				    <div class="post-actions">
				        <a href="${pageContext.request.contextPath}/post_modify?id=${post.id}" >수정</a>
				        <a href="${pageContext.request.contextPath}/post_delete?id=${post.id}" >삭제</a>
				    </div>
				</c:if>


                <div class="post-content">
                    <p>${post.content}</p>
                    <c:if test="${not empty post.imageUrl}">
                        <img src="${post.imageUrl}" alt="첨부 이미지" class="post-image" />
                    </c:if>
                </div>
            </div>

            <section class="comments">
                <h3>댓글</h3>

				<div id="comment-container">
				
				</div>
				
                <form id="commentForm" method="post" class="comment-form">
				    <input type="hidden" name="postId" value="${post.id}" />
				    <textarea name="content" placeholder="댓글을 입력하세요..." required></textarea>
				    <button type="submit">댓글 등록</button>
				</form>
            </section>
        </div>
        <!-- ... -->
    </main>
	<script>
	
	$(document).ready(function () {
		const postId = $("input[name='postId']").val();
	    const userId = "${sessionScope.userId}"; 
		const contextPath = "${pageContext.request.contextPath}";
		loadComments(1);
    	
    	function loadComments(page) {
    		/* const postId = $("input[name='postId']").val();
    	    const userId = "${sessionScope.userId}"; 
    		const contextPath = "${pageContext.request.contextPath}";
    		 */
    		console.log("postId:", postId);
    		
    	    $.ajax({
    	        type: "GET",
    	        url: contextPath + "/comments",
    	        data: {
    	            postId: postId,
    	            page: page
    	        },
    	        dataType: "text/html", 
    	        success: function (data) {
    	        	consol.log(data);
    	            $("#comment-container").html(data);  // 프래그먼트 주입
    	        },
    	        error: function () {
    	            alert("댓글을 불러오는 데 실패했습니다.");
    	        }
    	    });
    	}

        // 댓글 등록
        $('#commentForm').submit(function (e) {
            e.preventDefault();
            $.ajax({
                type: 'POST',
                url: contextPath + '/addComment',
                data: $(this).serialize(),
                dataType: 'json',
                success: function () {
                    $('textarea[name="content"]').val('');
                    loadComments(1);
                },
                error: function () {
                    alert('댓글 등록에 실패했습니다.');
                }
            });
        });

        // 댓글 수정 버튼 클릭
        $(document).on("click", ".edit-btn", function () {
            const id = $(this).data("id");
            const commentBox = $("#comment-" + id);
            const content = commentBox.find(".comment-content").text();

            const textarea = $("<textarea>").val(content).css({
                width: "100%",
                minHeight: "60px",
                padding: "8px 10px",
                fontSize: "14px",
                fontFamily: "inherit",
                border: "none",
                outline: "none",
                backgroundColor: "transparent",
                color: "#333",
                resize: "vertical"
            });

            const saveBtn = $("<button>").text("저장").addClass("save-edit").data("id", id);
            commentBox.find(".comment-content").replaceWith(textarea);
            $(this).replaceWith(saveBtn);
        });

        // 댓글 수정 저장
        $(document).on("click", ".save-edit", function () {
            const $this = $(this);
            const id = $this.data("id");
            const newContent = $("#comment-" + id).find("textarea").val();

            $.ajax({
                type: "POST",
                url: contextPath + "/comment_update",
                data: { id: id, content: newContent },
                success: function () {
                    loadComments(1);
                },
                error: function () {
                    alert("댓글 수정에 실패했습니다.");
                }
            });
        });

        // 댓글 삭제
        $(document).on("click", ".delete-btn", function () {
            const id = $(this).data("id");
            if (confirm("댓글을 삭제하시겠습니까?")) {
                $.ajax({
                    type: "POST",
                    url: contextPath + "/comment_delete",
                    data: { id: id, postId: postId },
                    dataType: "json",
                    success: function () {
                        loadComments(1);
                    },
                    error: function () {
                        alert("댓글 삭제에 실패했습니다.");
                    }
                });
            }
        });

        // 페이지 이동
        $(document).on("click", ".comment-page-link", function (e) {
            e.preventDefault();
            const page = $(this).data("page");
            loadComments(page);
        });

        // 댓글 목록 로딩 함수
        function loadComments(page) {
            $.ajax({
                type: "GET",
                url: contextPath + "/comments",
                data: { postId: postId, page: page },
                success: function (html) {
                    $("#comment-list-area").html(html);
                },
                error: function () {
                    alert("댓글 목록을 불러오지 못했습니다.");
                }
            });
        }

        // 좋아요
        $('#like-btn').click(function () {
            const liked = $(this).data('liked');
            const $img = $(this);
            $.ajax({
                type: 'POST',
                url: liked ? contextPath + '/unlike' : contextPath + '/like',
                data: { postId: postId },
                dataType: 'json',
                success: function (response) {
                    $img.attr('src', contextPath + '/resources/img/' + (liked ? 'unlike' : 'like') + '.png');
                    $img.data('liked', !liked);
                    $('#like-count').text(response.likeCount);
                },
                error: function () {
                    alert("처리 중 오류 발생");
                }
            });
        });

        // 북마크
        $('#bookmark-btn').click(function () {
            const bookmarked = $(this).data('bookmarked');
            const $img = $(this);
            $.ajax({
                type: 'POST',
                url: contextPath + (bookmarked ? '/unbookmark' : '/bookmark'),
                data: { postId: postId },
                success: function () {
                    $img.attr('src', contextPath + '/resources/img/' + (bookmarked ? 'dontsave' : 'save') + '.png');
                    $img.data('bookmarked', !bookmarked);
                },
                error: function () {
                    alert("북마크 처리 중 오류 발생");
                }
            });
        });
    });
	</script>
    <%@ include file="footer.jsp" %>
</div>
