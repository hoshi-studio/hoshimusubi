<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<style>
/* ---------- 공통 ----------- */
:root{
    --violet-light:#f8eeff;
    --violet-mid:#d8c4f2;
    --violet-dark:#b79de8;
    --label-bg:#e7d7f6;
    --txt-main:#222;
    --txt-sub:#8463b4;
}
body{
    margin:0;
    font-family:'Noto Sans JP','Noto Sans KR','맑은 고딕','Malgun Gothic',sans-serif;
    background:linear-gradient(180deg,#F3F2FD 0%,#DEC7F7 100%);
}
/* ---------- 컨테이너 ----------- */
.main-container{
    min-height:90vh;
    display:flex;
    align-items:center;
    justify-content:center;
}
.profile-box{
    width:100%;
    max-width:520px;
    background:var(--violet-light);
    border-radius:24px;
    padding:56px 48px 42px;
    box-shadow:0 6px 40px #e6d7fa55;
}
/* ---------- 상단(로고행) ----------- */
.logo-row{
    display:flex;
    justify-content:center;
    align-items:center;
    gap:16px;
    margin-bottom:4px;
}
.logo-row img.logo{width:54px;height:54px;}
.logo-row img.cat{width:40px;height:40px;}
.title{
    font-size:2.6rem;
    font-weight:800;
    letter-spacing:5px;
    margin:0;
}
.subtitle{
    text-align:center;
    font-size:1.45rem;
    letter-spacing:2px;
    margin:22px 0 40px;
    color:var(--txt-sub);
}
/* ---------- 프로필 영역 ----------- */
.profile-main{
    display:grid;
    grid-template-columns:120px 1fr;
    gap:32px 26px;          /* 행, 열 간격 */
    align-items:center;
}
.profile-pic{
    width:120px;height:120px;
    border-radius:50%;
    background:var(--violet-mid);
    display:flex;align-items:center;justify-content:center;
    box-shadow:0 3px 12px #e6d7fa88;
}
.profile-pic img{
    width:88px;height:88px;border-radius:50%;object-fit:cover;
}
.nickname-btn{
    margin-top:10px;
    background:#fff;
    border:none;border-radius:22px;
    padding:7px 24px;
    font-size:1.05rem;font-weight:500;
    color:var(--txt-sub);
    box-shadow:0 2px 8px #e6d7fa33;
}
/* ---------- 정보 테이블 ----------- */
.profile-info-table{
    width:100%;
    border-collapse:separate;
    border-spacing:8px 10px;    /* col, row 간격 */
}
.profile-info-table td{height:46px;font-size:1.05rem;}
.info-label{
    padding:0 14px;
    background:var(--label-bg);
    border-radius:10px;
    font-weight:600;
    color:var(--txt-sub);
    white-space:nowrap;         /* 한 줄 유지 */
    text-align:right;
}
.info-value{
    background:#fff;
    border-radius:10px;
    padding-left:22px;
    color:var(--txt-main);
}
/* ---------- 버튼 ----------- */
.message-btn{
    margin:38px auto 0;
    display:block;
    width:160px;height:46px;
    background:var(--violet-dark);
    border:none;border-radius:14px;
    color:#fff;
    font-size:1.18rem;font-weight:600;
    letter-spacing:2px;
    cursor:pointer;
    transition:background .15s;
}
.message-btn:hover{background:#a489d0;}
/* ---------- 반응형 ----------- */
@media(max-width:640px){
    .profile-box{padding:38px 6vw;}
    .profile-main{grid-template-columns:1fr;gap:18px;}
    .profile-info-table{border-spacing:6px 8px;}
    .profile-pic{margin:0 auto;}
    .nickname-btn{margin:10px auto 0;}
}

.subtitle {
  text-align: center;
  font-size: 1.45rem;
  letter-spacing: 2px;
  margin: 22px 0 40px;
  font-weight: 800;
  color: #9a7fc3; /* 기존보다 연한 퍼플 톤 */

  text-shadow: 0 0.8px 2px rgba(130, 100, 180, 0.2); /* 은은하게 */
  animation: fadeIn 0.6s ease forwards;
  opacity: 0;
  position: relative;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(4px); }
  to { opacity: 1; transform: translateY(0); }
}
/* 마우스 오버 시 info-value 반응 */
.info-value {
  background: #fff;
  border-radius: 10px;
  padding-left: 22px;
  color: var(--txt-main);
  transition: background 0.2s ease, box-shadow 0.2s ease, transform 0.15s ease;
}

.info-value:hover {
  background: #f4ecff;
  box-shadow: 0 2px 8px rgba(180, 140, 230, 0.2);
  transform: scale(1.015);
  color: #6f4eb3;
  cursor: default;
}

.profile-pic {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background: var(--violet-mid);
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 3px 12px #e6d7fa88;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.profile-pic:hover {
  transform: scale(1.05);
  box-shadow: 0 6px 16px #c5aee766;
}

.nickname-btn {
  margin-top: 10px;
  background: #fff;
  border-radius: 22px;
  padding: 7px 24px;
  font-size: 1.05rem;
  font-weight: 500;
  color: var(--txt-sub);
  box-shadow: 0 2px 8px #e6d7fa33;
  display: inline-block;
  transition: background 0.2s ease, color 0.2s ease, transform 0.15s ease;
}

.nickname-btn:hover {
  background: var(--violet-mid);
  color: var(--txt-main);
  transform: scale(1.03);
  cursor: default; /* 손가락 커서 ❌ 대신 자연스럽게 */
}

.message-btn {
  margin: 38px auto 0;
  display: block;
  width: 160px;
  height: 46px;
  background: linear-gradient(90deg, #b79de8, #d4c0f3); /* 퍼플 그라데이션 */
  border: none;
  border-radius: 14px;
  color: #fff;
  font-size: 1.18rem;
  font-weight: 600;
  letter-spacing: 2px;
  cursor: pointer;
  box-shadow: 0 4px 12px rgba(183, 157, 232, 0.35);
  transition: transform 0.15s ease, background 0.2s ease, box-shadow 0.2s ease;
}

.message-btn:hover {
  background: linear-gradient(90deg, #a78bd9, #cbb4f0);
  box-shadow: 0 6px 20px rgba(165, 130, 220, 0.4);
  transform: translateY(-2px) scale(1.02);
}

</style>

<div class="main-container">
  <div class="profile-box">
    <!-- 로고 & 타이틀 -->
    <div class="logo-row">
      <img class="logo" src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고">
      <h1 class="title">マイページ</h1>
      <img class="cat" src="${pageContext.request.contextPath}/resources/img/login.png" alt="고양이">
    </div>
	<p class="subtitle">“星結び、星と私を結ぶ場所。”</p>


    <!-- 프로필 메인 -->
    <div class="profile-main">
      <!-- 왼쪽 프로필사진+닉네임 -->
      <div style="text-align:center">
        <div class="profile-pic">
			<img src="${pageContext.request.contextPath}${user.profilePic}" alt="프로필">
        </div>
		<div class="nickname-btn">${user.nickname}</div>
      </div>

      <!-- 오른쪽 정보 표 -->
	  <table class="profile-info-table">
	    <tr>
	      <td class="info-label">性別</td>
	      <td class="info-value">${user.gender}</td>
	    </tr>
	    <tr>
	      <td class="info-label">イーメール</td>
	      <td class="info-value">${user.email}</td>
	    </tr>
	    <tr>
	      <td class="info-label">星座</td>
		  <td class="info-value">${user.zodiacNameJa}</td>

	    </tr>
	  </table>
    </div>

    <!-- 메시지 버튼 -->
	<form action="${pageContext.request.contextPath}/messageWrite" method="get" style="display: inline;">
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	    <input type="hidden" name="userId" value="${user.user_Id}" />
	    <button type="submit" class="message-btn">メッセージ</button>
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

  </div>
</div>

<%@ include file="footer.jsp" %>
