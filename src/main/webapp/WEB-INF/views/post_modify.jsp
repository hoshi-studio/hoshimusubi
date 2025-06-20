<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/postmodify.css" />

<div class="wrapper">
    <main class="main-content">
    <div class="wrapper">
    <main class="main-content">
        <div class="post-edit-wrapper">
            <h1 class="page-title">修正</h1>

            <form action="${pageContext.request.contextPath}/post_update" method="post" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" name="id" value="${post.id}" />
                <input type="hidden" name="userId" value="${post.user_Id}" />

                <div class="form-group">
                    <label for="title">タイトル</label>
                    <input type="text" name="title" id="title" value="${post.title}" required />
                </div>

                <div class="form-group">
                    <label for="content">内容</label>
                    <textarea name="content" id="content" rows="10" required>${post.content}</textarea>
                </div>

                <div class="form-group">
                    <label>イメージ</label>
                    <c:if test="${not empty post.imageUrl}">
                        <div class="image-preview">
                            <img src="${post.imageUrl}" alt="첨부 이미지" class="post-image" />
                            <label><input type="checkbox" name="removeImage" value="true" /> 이미지 삭제</label>
                        </div>
                    </c:if>
                </div>

                <div class="form-group">
                    <label for="imageFile">イメージ アップロード</label>
                    <input type="file" name="imageFile" id="imageFile" accept="image/*" />
                </div>

                <div class="form-actions">
                    <button type="submit">登録</button>
                    <a href="${pageContext.request.contextPath}/post_detail?id=${post.id}">キャンセル</a>
                </div>
            </form>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
</div>
