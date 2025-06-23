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
  <a href="${pageContext.request.contextPath}/zodiac/1" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Aquarius.png" alt="물병자리" />
    <div>水瓶座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/2" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Pisces.png" alt="물고기자리" />
    <div>魚座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/3" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Aries.png" alt="양자리" />
    <div>牡羊座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/4" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Taurus.png" alt="황소자리" />
    <div>牡牛座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/5" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Gemini.png" alt="쌍둥이자리" />
    <div>双子座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/6" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Cancer.png" alt="게자리" />
    <div>蟹座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/7" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Leo.png" alt="사자자리" />
    <div>獅子座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/8" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Virgo.png" alt="처녀자리" />
    <div>乙女座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/9" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Libra.png" alt="천칭자리" />
    <div>天秤座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/10" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Scorpio.png" alt="전갈자리" />
    <div>蠍座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/11" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Sagittarius.png" alt="사수자리" />
    <div>射手座</div>
  </a>
</div>
<div class="zodiac-item">
  <a href="${pageContext.request.contextPath}/zodiac/12" class="zodiac-link">
    <img src="${pageContext.request.contextPath}/resources/img/Capricorn.png" alt="염소자리" />
    <div>山羊座</div>
  </a>
</div>

</div>
<%@ page import="java.util.Calendar, java.text.SimpleDateFormat" %>
<%
    String[] zodiacs = { "山羊座", "水瓶座", "魚座", "牡羊座", "牡牛座", "双子座",
                         "蟹座", "獅子座", "乙女座", "天秤座", "蠍座", "射手座" };
						 

    String[] descs = {
		 "責任感が強く、真面目で努力家タイプです。目標に向かって着実に進めば、周囲からの信頼もアップします。",
		  "独創的で自由を大切にする個性派です。今日はあなたのアイディアが評価されるチャンスがありそう。",
		  "感受性が豊かで、想像力にあふれたロマンチストです。芸術や音楽に触れると心が癒される日になるでしょう。",
		  "エネルギッシュでチャレンジ精神にあふれています。新しいことに積極的に取り組むことで、運気が上昇します！",
		  "心の安定がカギとなる日です。焦らず、自分のペースを守ることで良い結果が得られるでしょう。美味しいものを食べたり、お気に入りの音楽を聴くと運気アップ◎。",
		  "人とのコミュニケーションが楽しくなる日。友人とのおしゃべりや新しい出会いから、刺激を受けられそうです。情報収集にも良い日なので、アンテナを広げてみて。",
		  "思いやりがあり、家族や友人を大切にする優しい人です。今日は誰かのために行動することで、自分にも良い運気がめぐってきます。",
		  "自信にあふれ、リーダーシップを発揮するカリスマです！注目を浴びる場面も多く、自分らしさを大切にすると吉◎。",
		  "几帳面で繊細、完璧を目指す努力家です。細かなところに気を配ることで、周囲からの信頼が高まりそうです。",
		  "バランス感覚に優れ、公平で穏やかな性格です。周囲との調和を大切にすると、自然と良い流れが生まれます。",
		  "直感が鋭く、ミステリアスな魅力を持っています。今日はインスピレーションに従って動くと、思わぬ幸運が訪れるかも。",
		  "冒険好きで、前向きな楽天家タイプです。新しい場所や人との出会いがあなたに活力を与えてくれます！"
    };
	
	String[] futagos = {
	    "山羊座は、努力家で責任感が強い星座です。",
	    "水瓶座は、自由で独創的な発想が魅力です。",
	    "魚座は、優しくて感受性が豊かな星座です。",
	    "牡羊座は、エネルギッシュで行動力があります。",
	    "牡牛座は、安定志向で我慢強いタイプです。",
	    "双子座は、好奇心旺盛でコミュ力が高い星座です。",
	    "蟹座は、家族思いでやさしい性格です。",
	    "獅子座は、自信家で華やかなリーダーです。",
	    "乙女座は、繊細で几帳面な完璧主義者です。",
	    "天秤座は、バランス感覚と社交性が魅力です。",
	    "蠍座は、情熱的でミステリアスな星座です。",
	    "射手座は、自由を愛する冒険家タイプです。"
	  };

    String[] imgs = {
        "Capricorn.png", "Aquarius.png", "Pisces.png", "Aries.png", "Taurus.png", "Gemini.png",
        "Cancer.png", "Leo.png", "Virgo.png", "Libra.png", "Scorpio.png", "Sagittarius.png"
    };

    Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH);
    String zodiac = zodiacs[month];
    String desc = descs[month];
	String futago = futagos[month];
    String img = imgs[month];

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日");
    String today = sdf.format(cal.getTime());

    String path = request.getContextPath();
%>
<div style="display: flex; flex-wrap: wrap; justify-content: center; gap: 40px; margin-top: 40px;">

	<!-- 왼쪽: 별자리 카드 -->
	<div style="flex: 1; min-width: 320px; max-width: 500px; text-align: center; background: rgba(255,255,255,0.6); padding: 24px; border-radius: 18px;">
	  <h2>📅 <%= today %> の星座</h2>
	  <h3>🌞 今月の星座は <strong><%= zodiac %></strong> です！</h3>
	  <img src="<%= path %>/resources/img/<%= img %>" alt="<%= zodiac %>"
	       style="width: 130px; border-radius: 50%; box-shadow: 0 0 10px rgba(0,0,0,0.2); margin: 16px 0;" />
	  <p style="font-size: 16px;"><%= futago %></p>
	</div>

  
  
  
  
  
  <!-- Stellarium Web 임베드 -->
  <div style="width:100%; max-width:800px; margin:0 auto; border-radius:18px; overflow:hidden; box-shadow:0 2px 10px rgba(70,70,130,0.13);">
    <iframe
      src="https://stellarium-web.org/"
      width="100%"
      height="400"
      style="border:none; border-radius:18px;"
      allowfullscreen>
	  <div style="display: flex; justify-content: center; gap: 40px; align-items: flex-start; flex-wrap: wrap; padding: 30px 0;">
		<div style="display: flex; justify-content: center; gap: 50px; align-items: flex-start; flex-wrap: wrap; padding: 40px 0;">

			<div style="display: flex; justify-content: center; gap: 50px; align-items: flex-start; flex-wrap: wrap; padding: 40px 0;">

			  <!-- 🌌 Stellarium 지도 영역 -->
			  <div style="flex: 1 1 550px; max-width: 600px;">

			    <!-- 👉 안내 문구 추가 -->
			    <div style="text-align: center; margin-bottom: 15px; font-size: 1rem; color: #333;">
			      🌠 星座を見渡すには、下のメニューから <strong>三角形のボタン</strong> を押してください！
			    </div>

			    <!-- 🌌 Stellarium iframe -->
			    <iframe src="https://stellarium-web.org/" width="100%" height="420" style="border-radius: 20px; border: none;"></iframe>
			  </div>
			  
			  
			  <!-- 🔮 오른쪽 운세 설명 박스 -->
			  <div style="flex: 1 1 400px; max-width: 500px; background: rgba(255, 255, 255, 0.7); padding: 25px 30px; border-radius: 18px; box-shadow: 0 0 12px rgba(0,0,0,0.1);">
			    <h3 style="font-size: 1.5rem; margin-bottom: 14px;">🔮 今日の運勢</h3>
			    <p style="font-size: 1.05rem; color: #333; line-height: 1.7;">
			      今日の星座は<strong> <%= zodiac %> </strong>です！<br><br>
			      <%= desc %>
				  </p>
 			     
				  🧭 左下の <strong>三角形ボタン</strong> をタップして、星座をめぐってみよう！
				  </p>
				  
				  ✔️I AGREEを必ず押さないと！
				  </p>
			  </div>


			</div>

		</div>



  <%@ include file="footer.jsp" %>

</body>
</html>
