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
                    <span>❤ ${post.likeCount}</span>
                    <span>💬 ${post.commentCount} 댓글</span>
                </div>

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
	});
	</script>
    <%@ include file="footer.jsp" %>
</div>
