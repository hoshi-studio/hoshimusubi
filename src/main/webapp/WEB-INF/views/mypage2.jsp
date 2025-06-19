<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mypage2.css" />

<h2 class="mypage-title">マイページ</h2>  <!-- ❗ container 바깥에 위치 -->

<div class="mypage-container">

	<div class="logout-btn-container">
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit" class="logout-btn">ログアウト</button>
    </form>
	</div>

    <div class="profile-box">
        <div class="profile-left">
            <img src="${myInfo.profilePic}" class="profile-pic" />
            <div class="nickname">${myInfo.nickname}</div>
        </div>

        <div class="profile-right">
            <div class="info-row">
                <label>性別</label>
                <div class="info-value">${myInfo.gender}</div>
            </div>
            <div class="info-row">
                <label>イーメール</label>
                <div class="info-value">${myInfo.email}</div>
            </div>
            <div class="info-row">
                <label>星座</label>
                <div class="info-value">${myInfo.zodiacName}</div>
            </div>
        </div>
    </div>

        <div class="stat-buttons">
            <div class="stat-btn" onclick="showSection('section-posts')">投稿文<br><span class="count">${myInfo.postCount}</span></div>
            <div class="stat-btn" onclick="showSection('section-comments')">コメント<br><span class="count">${myInfo.commentCount}</span></div>
            <div class="stat-btn" onclick="showSection('section-likes')">いいね<br><span class="count">${myInfo.likeCount}</span></div>
            <div class="stat-btn" onclick="showSection('section-bookmarks')">スクラップ<br><span class="count">${myInfo.bookmarkCount}</span></div>
            <div class="stat-btn" onclick="openModal()">メッセージ<br><span class="count">${myInfo.messageCount}</span></div>
        </div>
    </div>
	
	
	
    <!-- 게시글 리스트 -->
    <div class="mypage-section" id="section-posts" style="display: none;">
    <!-- 버튼 영역 -->
		<div class="board-controls">
		    <select class="sort-select" onchange="onSortChangeForPosts(this.value)">
		        <option value="recent" ${postSort eq 'recent' ? 'selected' : ''}>最新順</option>
		        <option value="like" ${postSort eq 'like' ? 'selected' : ''}>いいね順</option>
		        <option value="view" ${postSort eq 'view' ? 'selected' : ''}>閲覧順</option>
		    </select>
		</div>
    
    
          <table class="post-table">
        <thead>
        <tr>
            <th>Date</th>
            <th>作成者</th>
            <th>題目</th>
            <th>照会数</th>
            <th>Like</th>
            <th>コメント</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="post" items="${myPosts}">
            <tr>
       			 <td>
					    <c:choose>
					        <c:when test="${post.formattedDate == today}">
					            ${post.formattedTime}
					        </c:when>
					        <c:otherwise>
					            ${post.formattedDate}
					        </c:otherwise>
					    </c:choose>
				</td>
                <td>${post.nickname}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/post_detail?id=${post.id}">${post.postTitle}</a>
                </td>
                <td>${post.views}</td>
                <td>${post.likeCount}</td>
                <td>${post.commentCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
            <!-- 페이지네이션 -->
<form method="get" action="">
    <input type="hidden" name="sort" value="${postSort}" />
    <input type="hidden" name="section" id="sectionInput" value="${param.section}" />            
            
<div class="pagination">
    <!-- 이전 페이지 화살표 -->
    <c:if test="${currentPage > 1}">
        <button type="submit" name="page" value="${currentPage - 1}" class="arrow">&#x25C0;</button>
    </c:if>

    <!-- 페이지 번호 -->
    <c:forEach var="i" begin="1" end="${totalPages}">
        <button type="submit" name="page" value="${i}" class="page-link ${i == currentPage ? 'active' : ''}">${i}</button>
    </c:forEach>

    <!-- 다음 페이지 화살표 -->
  <c:if test="${currentPage < totalPages}">
        <button type="submit" name="page" value="${currentPage + 1}" class="arrow">&#x25B6;</button>
    </c:if>
</div>
</form>
     </div>
    
    <!-- 댓글 리스트 -->
    <div class="mypage-section" id="section-comments" style="display: none;">
      <!-- 버튼 영역 -->
<div class="board-controls">
    <select class="sort-select" onchange="onSortChangeForComments(this.value)">
        <option value="recent" ${commentSort eq 'recent' ? 'selected' : ''}>最新順</option>
        <option value="like" ${commentSort eq 'like' ? 'selected' : ''}>いいね順</option>
        <option value="view" ${commentSort eq 'view' ? 'selected' : ''}>閲覧順</option>
    </select>
</div>

<table class="post-table">
    <thead>
    <tr>
        <th>Date</th>
        <th>作成者</th>
        <th>題目</th>
        <th>照会数</th>
        <th>Like</th>
        <th>コメント</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${commentedPosts}">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${post.formattedDate == today}">
                        ${post.formattedTime}
                    </c:when>
                    <c:otherwise>
                        ${post.formattedDate}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${post.nickname}</td>
            <td>
                <a href="${pageContext.request.contextPath}/post_detail?id=${post.id}">${post.postTitle}</a>
            </td>
            <td>${post.views}</td>
            <td>${post.likeCount}</td>
            <td>${post.commentCount}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
        
 <!-- 페이지네이션 -->
<form method="get" action="">
    <input type="hidden" name="sort" value="${commentSort}" />
    <input type="hidden" name="section" value="section-comments" />           
            
<div class="pagination">
    <!-- 이전 페이지 화살표 -->
    <c:if test="${commentCurrentPage  > 1}">
        <button type="submit" name="page" value="${commentCurrentPage  - 1}" class="arrow">&#x25C0;</button>
    </c:if>

    <!-- 페이지 번호 -->
    <c:forEach var="i" begin="1" end="${commentTotalPages}">
        <button type="submit" name="page" value="${i}" class="page-link ${i == commentCurrentPage ? 'active' : ''}">${i}</button>
    </c:forEach>

    <!-- 다음 페이지 화살표 -->
  <c:if test="${commentCurrentPage  < commentTotalPages}">
        <button type="submit" name="page" value="${commentCurrentPage  + 1}" class="arrow">&#x25B6;</button>
    </c:if>
</div>
</form>
      
    </div>

    <!-- 좋아요 리스트 -->
    <div class="mypage-section" id="section-likes" style="display: none;">
          <!-- 버튼 영역 -->
<div class="board-controls">
    <select class="sort-select" onchange="onSortChangeForLikes(this.value)">
        <option value="recent" ${likeSort  eq 'recent' ? 'selected' : ''}>最新順</option>
        <option value="like" ${likeSort  eq 'like' ? 'selected' : ''}>いいね順</option>
        <option value="view" ${likeSort  eq 'view' ? 'selected' : ''}>閲覧順</option>
    </select>
</div>

<table class="post-table">
    <thead>
    <tr>
        <th>Date</th>
        <th>作成者</th>
        <th>題目</th>
        <th>照会数</th>
        <th>Like</th>
        <th>コメント</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${likedPosts}">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${post.formattedDate == today}">
                        ${post.formattedTime}
                    </c:when>
                    <c:otherwise>
                        ${post.formattedDate}
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${post.nickname}</td>
            <td>
                <a href="${pageContext.request.contextPath}/post_detail?id=${post.id}">${post.postTitle}</a>
            </td>
            <td>${post.views}</td>
            <td>${post.likeCount}</td>
            <td>${post.commentCount}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
        
 <!-- 페이지네이션 -->
<form method="get" action="">
    <input type="hidden" name="sort" value="${likeSort}" />
    <input type="hidden" name="section" value="section-likes" />        
            
<div class="pagination">
    <!-- 이전 페이지 화살표 -->
    <c:if test="${likeCurrentPage  > 1}">
        <button type="submit" name="page" value="${likeCurrentPage  - 1}" class="arrow">&#x25C0;</button>
    </c:if>

    <!-- 페이지 번호 -->
    <c:forEach var="i" begin="1" end="${likeTotalPages}">
        <button type="submit" name="page" value="${i}" class="page-link ${i == likeCurrentPage  ? 'active' : ''}">${i}</button>
    </c:forEach>

    <!-- 다음 페이지 화살표 -->
  <c:if test="${likeCurrentPage < likeTotalPages}">
        <button type="submit" name="page" value="${likeCurrentPage + 1}" class="arrow">&#x25B6;</button>
    </c:if>
</div>
</form>
    </div>

    <!-- 북마크 리스트 -->
    <div class="mypage-section" id="section-bookmarks" style="display: none;">
       <!-- 버튼 영역 -->
		<div class="board-controls">
		    <select class="sort-select" onchange="onSortChangeForBookMarks(this.value)">
		        <option value="recent" ${bookMarkSort eq 'recent' ? 'selected' : ''}>最新順</option>
		        <option value="like" ${bookMarkSort eq 'like' ? 'selected' : ''}>いいね順</option>
		        <option value="view" ${bookMarkSort eq 'view' ? 'selected' : ''}>閲覧順</option>
		    </select>
		</div>
    
          <table class="post-table">
        <thead>
        <tr>
            <th>Date</th>
            <th>作成者</th>
            <th>題目</th>
            <th>照会数</th>
            <th>Like</th>
            <th>コメント</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="post" items="${bookMarkedPosts}">
            <tr>
       			 <td>
					    <c:choose>
					        <c:when test="${post.formattedDate == today}">
					            ${post.formattedTime}
					        </c:when>
					        <c:otherwise>
					            ${post.formattedDate}
					        </c:otherwise>
					    </c:choose>
				</td>
                <td>${post.nickname}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/post_detail?id=${post.id}">${post.postTitle}</a>
                </td>
                <td>${post.views}</td>
                <td>${post.likeCount}</td>
                <td>${post.commentCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
            <!-- 페이지네이션 -->
<form method="get" action="">
    <input type="hidden" name="sort" value="${bookMarkSort}" />
    <input type="hidden" name="section" value="section-bookmarks" />            
            
<div class="pagination">
    <!-- 이전 페이지 화살표 -->
    <c:if test="${bookMarkCurrentPage > 1}">
        <button type="submit" name="page" value="${bookMarkCurrentPage  - 1}" class="arrow">&#x25C0;</button>
    </c:if>

    <!-- 페이지 번호 -->
    <c:forEach var="i" begin="1" end="${bookMarkTotalPages}">
        <button type="submit" name="page" value="${i}" class="page-link ${i == bookMarkCurrentPage ? 'active' : ''}">${i}</button>
    </c:forEach>

    <!-- 다음 페이지 화살표 -->
  <c:if test="${bookMarkCurrentPage  < bookMarkTotalPages}">
        <button type="submit" name="page" value="${bookMarkCurrentPage + 1}" class="arrow">&#x25B6;</button>
    </c:if>
</div>
</form>
</div>

 <!-- 메시지 모달 -->
<div id="messageModal" class="modal" style="display:none;">
  <div class="modal-content">
    <span class="close" onclick="closeModal()">&times;</span>
    <h3>メッセージ</h3>
    <ul>
     <c:forEach var="msg" items="${receivedMessages}">
  <li class="message-item" onclick="openMessage('${msg.senderNickname}', '${fn:escapeXml(msg.content)}', '${msg.senderId}', '${msg.messageId}', ${msg.isRead})">
    <strong>${msg.senderNickname}</strong> さんからのメッセージ
    <c:if test="${msg.isRead == 1}">
  		<span class="read-status">既読</span>
	</c:if>
    <span class="date">${msg.sentAt}</span>
  </li>
</c:forEach>
    </ul>
  </div>
</div>

<!-- 메시지 보기용 모달 -->
<div id="messageViewModal" class="modal" style="display:none;">
  <div class="modal-content">
    <span class="close" onclick="closeMessageView()">&times;</span>
    <h4 id="messageSender"></h4>
    <p id="messageContent"></p>
     <!-- 답장하기 버튼 -->
    <button id="replyToggleBtn" onclick="toggleReplyForm()">返信する</button>
     <!-- 답장 폼 -->
    <div id="replyForm" style="display:none; margin-top:10px;">
      <form action="${pageContext.request.contextPath}/sendMessage2" method="post">
        <input type="hidden" name="receiverId" id="replyReceiverId" />
        <textarea name="content" rows="3" cols="40" placeholder="返信内容を入力してください..."></textarea><br/>
        <button type="submit">送信</button>
      </form>
    </div>
  </div>
</div>    
        


<script>
function showSection(sectionId) {
    const sections = ['section-posts', 'section-comments', 'section-likes', 'section-bookmarks'];
    sections.forEach(id => {
        document.getElementById(id).style.display = (id === sectionId) ? 'block' : 'none';
    });

    // 선택한 섹션을 URL에 반영
    const url = new URL(window.location.href);
    url.searchParams.set('section', sectionId);
    window.history.replaceState({}, '', url);
    
}
</script>


<script>
window.addEventListener('DOMContentLoaded', () => {
    const sectionId = new URLSearchParams(window.location.search).get('section');
    showSection(sectionId);
});
</script>

<script>
document.addEventListener('DOMContentLoaded', function () {
    const sectionInput = document.getElementById('sectionInput');
    const form = document.querySelector('.pagination')?.closest('form');
    
    if (form && sectionInput) {
        form.addEventListener('submit', function () {
            const visibleSection = Array.from(document.querySelectorAll('.mypage-section'))
                .find(section => section.style.display !== 'none');
            if (visibleSection) {
                sectionInput.value = visibleSection.id;
            }
        });
    }
    
    const replyForm = document.querySelector("#replyForm form");
    if (replyForm) {
        replyForm.addEventListener("submit", function(e) {
            const receiver = document.getElementById("replyReceiverId").value;
            if (!receiver || isNaN(receiver) || parseInt(receiver) <= 0) {
                alert("受信者が無効です。メッセージを選択してください。");
                e.preventDefault();
            }
        });
    }
});
</script>

<script>
function onSortChangeForPosts(value) {
    location.href = '?postSort=' + value + '&section=section-posts';
}
</script>

<script>
function onSortChangeForComments(value) {
	location.href = '?commentSort=' + value + '&section=section-comments';
}
</script>

<script>
function onSortChangeForLikes(value) {
	location.href = '?likeSort=' + value + '&section=section-likes';
}
</script>

<script>
function onSortChangeForMarks(value) {
	location.href = '?bookMarkSort=' + value + '&section=section-bookmarks';
}

</script>

<script>
function openModal() {
  document.getElementById("messageModal").style.display = "block";
}

function closeModal() {
  document.getElementById("messageModal").style.display = "none";
}

function openMessage(sender, content, senderId, messageId, isRead) {
	  document.getElementById("messageSender").textContent = sender + " さんからのメッセージ";
	  document.getElementById("messageContent").textContent = content;
	  document.getElementById("replyReceiverId").value = senderId;
	  document.getElementById("messageViewModal").style.display = "block";
	  
	  console.log("isRead 값:", isRead);
	  
	  if (!isRead) {
		    fetch("${pageContext.request.contextPath}/markMessageAsRead?id=" + messageId, {
		      method: "POST"
		    }).then(res => {
		      if (res.ok) {
		        console.log("既読処理成功");
		        // 이미 읽은 메시지임을 표시하거나 새로고침 없이 처리 UI 업데이트 가능
		        //location.reload();
		      } else {
		        console.error("既読処理失敗");
		      }
		    });
		    
		  }

	}

function closeMessageView() {
  document.getElementById("messageViewModal").style.display = "none";
}
</script>

<script>
function toggleReplyForm() {
  const replyForm = document.getElementById("replyForm");
  if (replyForm.style.display === "none") {
    replyForm.style.display = "block";
  } else {
    replyForm.style.display = "none";
  }
}
</script>


<%@ include file="footer.jsp" %>
