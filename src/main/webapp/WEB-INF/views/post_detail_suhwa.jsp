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
					    <span>作成者: ${post.nickname}</span>
					</a>

                    <span>作成日: ${formattedCreatedAt}</span>
                    <span>閲覧数: ${post.views}</span>
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
				        <a href="${pageContext.request.contextPath}/post_modify?id=${post.id}" >修整</a>
				        <a href="${pageContext.request.contextPath}/post_delete?id=${post.id}" >削除</a>
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
                <h3>コメント</h3>

				<div id="comment-list-area">
				
				</div>
				
                <form id="commentForm" method="post" class="comment-form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				    <input type="hidden" name="postId" value="${post.id}" />
				    <textarea name="content" placeholder="댓글을 입력하세요..." required></textarea>
				    <button type="submit">コメント登録</button>
				</form>
            </section>
        </div>
        <!-- ... -->
    </main>
	<script>
	const postId = $("input[name='postId']").val();
    const userId = "${sessionScope.userId}"; 
	const contextPath = "${pageContext.request.contextPath}";
	
	// CSRF 토큰 설정
	const token = $("meta[name='_csrf']").attr("content");
	const header = $("meta[name='_csrf_header']").attr("content");

	// 전역 Ajax 설정
	$(document).ajaxSend(function (e, xhr, options) {
	    xhr.setRequestHeader(header, token);
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
	$(document).ready(function () {
		loadComments(1);
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
                    alert('コメントの登録に失敗しました。');
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

            const saveBtn = $("<button>").text("登録").addClass("save-edit").data("id", id);
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
                    alert("コメントの修正に失敗しました。");
                }
            });
        });

        // 댓글 삭제
        $(document).on("click", ".delete-btn", function () {
            const id = $(this).data("id");
            if (confirm("コメントを削除しますか？")) {
                $.ajax({
                    type: "POST",
                    url: contextPath + "/comment_delete",
                    data: { id: id, postId: postId },
                    dataType: "json",
                    success: function () {
                        loadComments(1);
                    },
                    error: function () {
                        alert("コメントの削除に失敗しました。");
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
                    alert("処理中にエラーが発生");
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
                    alert("ブックマーク処理中にエラーが発生");
                }
            });
        });
    });
	</script>
    <%@ include file="footer.jsp" %>
</div>
