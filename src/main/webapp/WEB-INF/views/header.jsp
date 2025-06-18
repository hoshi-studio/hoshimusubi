<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <%-- 시큐리티 태그 --%>
<c:set var="_csrf" value="${_csrf}" />
<meta name="_csrf"        content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<%
    String contextPath = request.getContextPath();
%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css" />

<header class="header">
    <!-- 왼쪽: 로고는 항상 고정 -->
    <div class="header-left">
        <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Hoshimusubi 로고" class="header-logo" />
        </a>
    </div>

    <!-- 중앙: 타이틀 -->
    <div class="header-center">
        <h1 class="header-title">星結び</h1>
    </div>

    <!-- 오른쪽: 로그인 or 로그아웃 -->
    <div class="header-right">
        <!-- 비로그인 시 로그인 버튼 -->
        <sec:authorize access="isAnonymous()">
            <a href="${pageContext.request.contextPath}/login">
                <img src="${pageContext.request.contextPath}/resources/img/logout.png" alt="로그인" class="header-login" />
            </a>
        </sec:authorize>
		<!-- 로그인 시 마이페이지 버튼 -->
		    <sec:authorize access="isAuthenticated()">
		        <a href="${pageContext.request.contextPath}/mypage">
		            <img src="${pageContext.request.contextPath}/resources/img/login.png" alt="마이페이지" class="header-login" />
		        </a>
		    </sec:authorize>
	    </div>
</header>