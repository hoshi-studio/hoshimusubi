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

                    <span>작성일: ${post.created_at}</span>
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
                    <span id="comment-count">💬 ${post.commentCount}</span>
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
				    <div class="comment-actions">
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

				<c:forEach var="comment" items="${comments}">
				    <div class="comment" id="comment-${comment.id}">
				        <strong>${comment.nickname}</strong>: 
				        <span class="comment-content">${comment.content}</span>
				        
				        <c:if test="${loginUserId eq comment.userId}">
				            <div class="comment-actions">
				                <a href="javascript:void(0);" class="edit-btn" data-id="${comment.id}">수정</a>
				                <a href="javascript:void(0);" class="delete-btn" data-id="${comment.id}">삭제</a>
				            </div>
				        </c:if>
				    </div>
				</c:forEach>


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
    
    const userId = "${sessionScope.userId}"; 
	const contextPath = "${pageContext.request.contextPath}";
	
	$(document).ready(function () {
		const postId = $(this).data('post-id');
    	const userId = $(this).data('user-id');
		//댓글 저장
	    $('#commentForm').submit(function (e) {
		    e.preventDefault(); 
		
		    $.ajax({
		        type: 'POST',
		        url: contextPath + '/addComment', 
		        data: $(this).serialize(),
		        dataType: 'json',
		        success: function (response) {
		            $('.comment-form').before(response.commentHtml); // 댓글 추가
		            $('textarea[name="content"]').val(''); // 입력창 초기화
		
		            // 댓글 수 갱신
		            $('#comment-count').text(response.commentCount);
		        },
		        error: function () {
		            alert('댓글 등록에 실패했습니다.');
		        }
		    });
		});
	    //댓글 수정 
		$(document).on("click", ".edit-btn", function () {
		    const id = $(this).data("id");
		    const commentBox = $("#comment-" + id);
		    const content = commentBox.find(".comment-content").text();

			const textarea = $("<textarea>")
			        .val(content)
			        .attr("style", `
					width: 100%;
					min-height: 60px;
					padding: 8px 10px;
					font-size: 14px;
					font-family: inherit;
					border: none;
					outline: none;
					background-color: transparent;
					color: #333;
					resize: vertical;
					box-shadow: none;
			        `);

			    const saveBtn = $("<button>").text("저장").addClass("save-edit").data("id", id);

			    commentBox.find(".comment-content").replaceWith(textarea);
			    $(this).replaceWith(saveBtn);
		});

	    // 댓글 수정 저장
		$(document).on("click", ".save-edit", function () {
		    const $this = $(this);  // 현재 클릭한 버튼
		    const id = $this.data("id");
		    const $commentBox = $("#comment-" + id);
		    const newContent = $commentBox.find("textarea").val();

		    $.ajax({
		        type: "POST",
		        url: "${pageContext.request.contextPath}/comment_update",
		        data: {
		            id: id,
		            content: newContent
		        },
		        success: function () {
		            const newSpan = $("<span>").addClass("comment-content").text(newContent);
		            $commentBox.find("textarea").replaceWith(newSpan);

		            const newEditBtn = $("<a>")
		                .attr("href", "javascript:void(0);")
		                .addClass("edit-btn")
		                .attr("data-id", id)
		                .text("수정");

		            $this.replaceWith(newEditBtn);
		        },
		        error: function () {
		            alert("댓글 수정에 실패했습니다.");
		        }
		    });
		});

	    // 댓글 삭제
		$(document).on("click", ".delete-btn", function () {
		    const id = $(this).data("id");
		    const postId = "${post.id}"; // 또는 data-post-id 속성 활용 가능

		    if (confirm("댓글을 삭제하시겠습니까?")) {
		        $.ajax({
		            type: "POST",
		            url: contextPath + "/comment_delete",
		            data: {
		                id: id,
		                postId: postId
		            },
		            dataType: "json",
		            success: function (response) {
		                $("#comment-" + id).remove();
		                $("#comment-count").text("💬 " + response.commentCount);
		            },
		            error: function () {
		                alert("댓글 삭제에 실패했습니다.");
		            }
		        });
		    }
		});
	    
	    //좋아요 기능
	    $('#like-btn').click(function () {
	    	const postId = $(this).data('post-id');
	    	const userId = $(this).data('user-id');
	        const liked = $(this).data('liked');
	        const $img = $(this);
		
		    $.ajax({
		        type: 'POST',
		        url: liked ? contextPath + '/unlike' : contextPath + '/like',
		        data: { postId: postId},
		        dataType: 'json', 
		        success: function (response) {
		            const newCount = response.likeCount; // JSON 객체에서 count 추출
		            if (liked) {
		                $img.attr('src', contextPath + '/resources/img/unlike.png');
		                $img.data('liked', false);
		            } else {
		                $img.attr('src', contextPath + '/resources/img/like.png');
		                $img.data('liked', true);
		            }
		            $('#like-count').text(newCount);
		        },
		        error: function () {
		            alert("처리 중 오류 발생");
		        }
		    });
		});
			    
	    //북마크 기능
	    $('#bookmark-btn').click(function () {
	        const postId = $(this).data('post-id');
	        const bookmarked = $(this).data('bookmarked');
	        const $img = $(this);

	        $.ajax({
	            type: 'POST',
	            url: bookmarked ? '${pageContext.request.contextPath}/unbookmark' : '${pageContext.request.contextPath}/bookmark',
	            data: { postId: postId },
	            success: function () {
	                // 이미지 및 data-bookmarked 속성 토글
	                if (bookmarked) {
	                    $img.attr('src', '${pageContext.request.contextPath}/resources/img/dontsave.png');
	                    $img.data('bookmarked', false);
	                } else {
	                    $img.attr('src', '${pageContext.request.contextPath}/resources/img/save.png');
	                    $img.data('bookmarked', true);
	                }
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
