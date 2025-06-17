<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/messagewrite.css" />

<div class="wrapper">
    <main class="main-content">
		<div class="post-create-wrapper">
		    <h1 class="page-title">メッセージ作成</h1>

		    <form action="${pageContext.request.contextPath}/sendMessage" method="post">
		        <div class="form-group">
		            <label for="receiverNickname">受信者</label>
		            <input type="text" id="receiverNickname" value="${receiver.nickname}" disabled />
		            <input type="hidden" name="receiverId" value="${receiver.id}" />
		        </div>

		        <div class="form-group">
		            <label for="title">件名</label>
		            <input type="text" id="title" name="title" placeholder="件名を入力してください" required />
		        </div>

		        <div class="form-group">
		            <label for="content">メッセージ内容</label>
		            <textarea id="content" name="content" placeholder="メッセージを入力してください" required></textarea>
		        </div>

		        <div class="form-actions">
		            <button type="submit">送信</button>
		            <a href="${pageContext.request.contextPath}/">キャンセル</a>
		        </div>
		    </form>
		</div>
    </main>

    <%@ include file="footer.jsp" %>
</div>