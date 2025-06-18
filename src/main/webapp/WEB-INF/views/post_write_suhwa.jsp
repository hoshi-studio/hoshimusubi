<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/postwrite.css" />

<div class="wrapper">
    <main class="main-content">
    <div class="post-create-wrapper">
    <h1 class="page-title">게시글 작성</h1>

    <form action="${pageContext.request.contextPath}/post_create" method="post" enctype="multipart/form-data">
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
            <div id="filename-error" style="color:red;"></div>
        </div>

        <input type="hidden" name="zodiacId" value="${zodiacId}" />

        <div class="form-actions">
            <button type="submit">등록</button>
            <a href="${pageContext.request.contextPath}/zodiac/${zodiacId}">취소</a>
        </div>
    </form>
</div>
    </main>
    <script>
		$(document).ready(function () {
		    $("#imageFile").on("change", function () {
		        const file = this.files[0];
		        if (!file) return;
		
		        const fileName = file.name;
		
		        $.ajax({
		            url: "/check-image-name",
		            type: "POST",
		            data: { imageName: fileName },
		            success: function (response) {
		                if (response.duplicate) {
		                    $("#filename-error").text("이미 사용 중인 파일명입니다. 다른 파일을 선택해 주세요.");
		                    $("#imageFile").val(""); // 파일 입력 초기화
		                } else {
		                    $("#filename-error").text("");
		                }
		            },
		            error: function () {
		                alert("이미지 이름 확인 중 오류 발생");
		            }
		        });
		    });
		});
		</script>

    <%@ include file="footer.jsp" %>
</div>
