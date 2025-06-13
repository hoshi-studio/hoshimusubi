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
</style>

<div class="main-container">
  <div class="profile-box">
    <!-- 로고 & 타이틀 -->
    <div class="logo-row">
      <img class="logo" src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고">
      <h1 class="title">星結び</h1>
      <img class="cat" src="${pageContext.request.contextPath}/resources/img/login.png" alt="고양이">
    </div>
    <p class="subtitle">マイページ</p>

    <!-- 프로필 메인 -->
    <div class="profile-main">
      <!-- 왼쪽 프로필사진+닉네임 -->
      <div style="text-align:center">
        <div class="profile-pic">
          <img src="${pageContext.request.contextPath}/resources/img/女の子.png" alt="프로필">
        </div>
        <button class="nickname-btn">닉네임</button>
      </div>

      <!-- 오른쪽 정보 표 -->
      <table class="profile-info-table">
        <tr>
          <td class="info-label">性別</td>
          <td class="info-value">남/여</td>
        </tr>
        <tr>
          <td class="info-label">イーメール</td>
          <td class="info-value">user@email.com</td>
        </tr>
        <tr>
          <td class="info-label">星座</td>
          <td class="info-value">염소자리</td>
        </tr>
      </table>
    </div>

    <!-- 메시지 버튼 -->
    <button class="message-btn" onclick="location.href='messageWrite.do'">メッセージ</button>
  </div>
</div>

<%@ include file="footer.jsp" %>
