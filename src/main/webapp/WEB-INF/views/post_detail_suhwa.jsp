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
                    <span>작성자: ${post.nickname}</span>
                    <span>작성일: ${post.created_at}</span>
                    <span>조회수: ${post.views}</span>
                    <c:choose>
					    <c:when test="${post.likedByCurrentUser}">
					        <img src="${pageContext.request.contextPath}/resources/images/like.png" 
					             id="like-btn" 
					             data-post-id="${post.id}" 
					             data-liked="true" 
					             style="cursor:pointer;" />
					    </c:when>
					    <c:otherwise>
					        <img src="${pageContext.request.contextPath}/resources/images/unlike.png" 
					             id="like-btn" 
					             data-post-id="${post.id}" 
					             data-liked="false" 
					             style="cursor:pointer;" />
					    </c:otherwise>
					</c:choose>
                    <span>${post.likeCount}</span>
                    <span>💬 ${post.commentCount}</span>
                    <c:choose>
					    <c:when test="${post.bookmarkedByCurrentUser}">
					        <img src="${pageContext.request.contextPath}/resources/images/save.png" 
					             id="bookmark-btn" 
					             data-post-id="${post.id}" 
					             data-bookmarked="true" 
					             style="cursor:pointer;" />
					    </c:when>
					    <c:otherwise>
					        <img src="${pageContext.request.contextPath}/resources/images/dontsave.png" 
					             id="bookmark-btn" 
					             data-post-id="${post.id}" 
					             data-bookmarked="false" 
					             style="cursor:pointer;" />
					    </c:otherwise>
					</c:choose>
                </div>
                
                <c:if test="${sessionScope.loginUser.id eq comment.userId}">
		            <div class="comment-actions">
		                <a href="javascript:void(0);" class="edit-btn" data-id="${comment.id}">수정</a>
		                <a href="javascript:void(0);" class="delete-btn" data-id="${comment.id}">삭제</a>
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
                    <div class="comment">
                        <strong>${comment.nickname}</strong>: ${comment.content}
                        
                        <c:if test="${sessionScope.loginUser.id eq comment.userId}">
				            <div class="comment-actions">
				                <a href="${pageContext.request.contextPath}/comment/edit?id=${comment.id}" class="edit-btn">수정</a>
				                <a href="${pageContext.request.contextPath}/comment/delete?id=${comment.id}" class="delete-btn">삭제</a>
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
	$(document).ready(function () {
		//댓글 저장
	    $('#commentForm').submit(function (e) {
	        e.preventDefault(); // 기본 제출 방지
	
	        $.ajax({
	            type: 'POST',
	            url: '${pageContext.request.contextPath}/addComment',
	            data: $(this).serialize(),
	            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	            success: function (response) {
	                // 성공 시 댓글 영역에 바로 추가
	                $('.comment-form').before(response);
	                $('textarea[name="content"]').val(''); // 입력창 초기화
	            },
	            error: function () {
	                alert('댓글 등록에 실패했습니다.');
	            }
	        });
	    });
	    
	 // 댓글 수정 시작
	    $(document).on("click", ".edit-btn", function () {
	        const id = $(this).data("id");
	        const commentBox = $("#comment-" + id);
	        const content = commentBox.find(".comment-content").text();

	        const textarea = $("<textarea>").val(content);
	        const saveBtn = $("<button>").text("저장").addClass("save-edit").data("id", id);

	        commentBox.find(".comment-content").replaceWith(textarea);
	        $(this).replaceWith(saveBtn);
	    });

	    // 댓글 수정 저장
	    $(document).on("click", ".save-edit", function () {
	        const id = $(this).data("id");
	        const newContent = $("#comment-" + id).find("textarea").val();

	        $.ajax({
	            type: "POST",
	            url: "${pageContext.request.contextPath}/comment/update",
	            data: {
	                id: id,
	                content: newContent
	            },
	            success: function () {
	                const newSpan = $("<span>").addClass("comment-content").text(newContent);
	                $("#comment-" + id).find("textarea").replaceWith(newSpan);
	                $(".save-edit").replaceWith(`<a href="javascript:void(0);" class="edit-btn" data-id="${id}">수정</a>`);
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
	                url: "${pageContext.request.contextPath}/comment/delete",
	                data: { id: id },
	                success: function () {
	                    $("#comment-" + id).remove();
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
	        const liked = $(this).data('liked');
	        const $img = $(this);

	        $.ajax({
	            type: 'POST',
	            url: liked ? '/unlike' : '/like',
	            data: { postId: postId },
	            success: function (newCount) {
	                // 이미지 변경
	                if (liked) {
	                    $img.attr('src', '${pageContext.request.contextPath}/resources/images/unlike.png');
	                    $img.data('liked', false);
	                } else {
	                    $img.attr('src', '${pageContext.request.contextPath}/resources/images/like.png');
	                    $img.data('liked', true);
	                }

	                // 좋아요 수 업데이트
	                $img.prev('span').text(newCount);
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
	                    $img.attr('src', '${pageContext.request.contextPath}/resources/images/dontsave.png');
	                    $img.data('bookmarked', false);
	                } else {
	                    $img.attr('src', '${pageContext.request.contextPath}/resources/images/save.png');
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
