<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/postwrite.css" />

<div class="wrapper">
    <main class="main-content">
    <div class="post-create-wrapper">
    <h1 class="page-title">게시글 작성</h1>

    <form action="${pageContext.request.contextPath}/post/create" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" name="title" id="title" required />
        </div>

        <div class="form-group">
            <label for="content">내용</label>
            <textarea name="content" id="content" rows="10" required></textarea>
        </div>

        <div class="form-group">
            <label for="imageFile">이미지 업로드</label>
            <input type="file" name="imageFile" id="imageFile" accept="image/*" />
        </div>

        <!-- 세션에서 로그인된 사용자 ID 넘김 -->
        <input type="hidden" name="userId" value="${sessionScope.loginUser.id}" />

        <div class="form-actions">
            <button type="submit">등록</button>
            <a href="${pageContext.request.contextPath}/post/list">취소</a>
        </div>
    </form>
</div>
    </main>

    <%@ include file="footer.jsp" %>
</div>
