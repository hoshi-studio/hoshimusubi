<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/postdetailsuhwa.css" />

<div class="wrapper">
    <main class="main-content">
        <!-- 여기에 메인 콘텐츠를 작성하세요 -->
         <div class="detail-container">
            <h1 class="page-title">${post.title}</h1>

            <div class="post-box">
                <h2 class="post-title"></h2>
                <div class="post-meta">
                    <span>작성자: ${post.user_Id}</span>
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
                        <strong>${comment.writer}</strong>: ${comment.content}
                    </div>
                </c:forEach>

                <form action="addComment.do" method="post" class="comment-form">
                    <input type="hidden" name="postId" value="${post.id}" />
                    <textarea name="content" placeholder="댓글을 입력하세요..." required></textarea>
                    <button type="submit">댓글 등록</button>
                </form>
            </section>
        </div>
        <!-- ... -->
    </main>

    <%@ include file="footer.jsp" %>
</div>
