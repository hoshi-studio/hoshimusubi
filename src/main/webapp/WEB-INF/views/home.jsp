<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>星結び</title>
    <!-- 일본어 예쁜 웹폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;700&display=swap" rel="stylesheet">
    <!-- 기본 CSS (home.css) -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
    <!-- Aladin Lite 스타일시트 -->
    <link rel="stylesheet" href="https://aladin.cds.unistra.fr/AladinLite/api/aladin.min.css">

    <style>
      
        .title-row {
            display: flex; justify-content: center; align-items: center;
        }
        .title-row img { height: 54px; margin-right: 16px; }
        .cat-icon { position: absolute; right:46px; top:38px; width:36px; }
        .zodiac-list {
            margin: 28px 0; display: grid;
            grid-template-columns: repeat(6, 1fr);
            gap: 12px 18px;
            background: rgba(255,255,255,0.5);
            border-radius: 18px; padding: 16px 10px;
        }
        .zodiac-item img { width:54px; display:block; margin:0 auto 6px; }
        .zodiac-item { text-align:center; font-size:1em; }

        /* Aladin Lite 영역 */
        .aladin-section {
            margin: 34px 0; text-align: center;
        }
        #aladin-lite-div {
            width: 800px; height: 400px;
            margin: 0 auto; border-radius: 18px;
            box-shadow: 0 2px 10px rgba(70,70,130,0.13);
            background: #222;
        }
        .aladin-controls {
            margin-top: 12px;
        }
        .aladin-controls button {
            background: #604cb8; color:#fff; border:none;
            border-radius:8px; padding:6px 18px; margin:0 6px 6px 0;
            cursor:pointer; transition: background .2s;
        }
        .aladin-controls button:hover {
            background:#866de7;
        }
    </style>
</head>

<body>
	<%@ include file="header.jsp" %>
	<div class="main-container">
	        <!-- 타이틀/로고 -->
	        <div class="title-row">
	            <!--<img src="/resources/img/logo.png" alt="ロゴ">-->
	            <!--<h1 style="font-size:2.2rem; letter-spacing:4px;">星結び</h1>-->
	        </div>

			<div class="zodiac-list">
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/aries" class="zodiac-link">
			            <img src="/resources/img/Aries.png" alt="양자리" />
			            <div>牡羊座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/taurus" class="zodiac-link">
			            <img src="/resources/img/Taurus.png" alt="황소자리" />
			            <div>牡牛座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/gemini" class="zodiac-link">
			            <img src="/resources/img/Gemini.png" alt="쌍둥이자리" />
			            <div>双子座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/cancer" class="zodiac-link">
			            <img src="/resources/img/Cancer.png" alt="게자리" />
			            <div>蟹座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/leo" class="zodiac-link">
			            <img src="/resources/img/Leo.png" alt="사자자리" />
			            <div>獅子座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/virgo" class="zodiac-link">
			            <img src="/resources/img/Virgo.png" alt="처녀자리" />
			            <div>乙女座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/libra" class="zodiac-link">
			            <img src="/resources/img/Libra.png" alt="천칭자리" />
			            <div>天秤座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/scorpio" class="zodiac-link">
			            <img src="/resources/img/Scorpio.png" alt="전갈자리" />
			            <div>蠍座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/sagittarius" class="zodiac-link">
			            <img src="/resources/img/Sagittarius.png" alt="사수자리" />
			            <div>射手座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/capricorn" class="zodiac-link">
			            <img src="/resources/img/Capricorn.png" alt="염소자리" />
			            <div>山羊座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/aquarius" class="zodiac-link">
			            <img src="/resources/img/Aquarius.png" alt="물병자리" />
			            <div>水瓶座</div>
			        </a>
			    </div>
			    <div class="zodiac-item">
			        <a href="${pageContext.request.contextPath}/board/pisces" class="zodiac-link">
			            <img src="/resources/img/Pisces.png" alt="물고기자리" />
			            <div>魚座</div>
			        </a>
			    </div>
			</div>

		
		
		
		<!-- stellarium iframe 예시 -->
		<div style="width:800px; height:400px; margin:0 auto; border-radius:18px; overflow:hidden; box-shadow:0 2px 10px rgba(70,70,130,0.13);">
		  <iframe 
		    src="https://stellarium-web.org/" 
		    width="100%" 
		    height="400" 
		    style="border:none; border-radius:18px;"
		    allowfullscreen>
		  </iframe>
		</div>

		<!-- 예쁜 전체화면 버튼 -->
		<div style="text-align:center; margin-top:16px;">
		  <button
		    onclick="window.open('https://stellarium-web.org/', '_blank')"
		    style="
		      background: linear-gradient(90deg, #7c4dff, #4db6ff);
		      color: #fff;
		      border: none;
		      border-radius: 24px;
		      padding: 12px 28px;
		      font-size: 1rem;
		      font-weight: bold;
		      cursor: pointer;
		      box-shadow: 0 3px 12px rgba(0,0,0,0.15);
		      transition: background 0.2s, transform 0.1s;
		    "
		    onmouseover="this.style.background='linear-gradient(90deg, #4db6ff, #7c4dff)'"
		    onmouseout="this.style.background='linear-gradient(90deg, #7c4dff, #4db6ff)'"
		    onmousedown="this.style.transform='translateY(1px)';"
		    onmouseup="this.style.transform='translateY(0)';"
		  >
		    全画面表示
		  </button>
		</div>
	 <%@ include file="footer.jsp" %>
</body>
</html>

