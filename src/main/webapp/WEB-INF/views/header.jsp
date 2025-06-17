<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> <%-- 시큐리티 태그 --%>

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
                <img src="${pageContext.request.contextPath}/resources/img/login.png" alt="로그인" class="header-login" />
            </a>
        </sec:authorize>

        <!-- 로그인 시 로그아웃 버튼 -->
        <sec:authorize access="isAuthenticated()">
            <form action="${pageContext.request.contextPath}/logout" method="post" style="display:inline;">
                <button type="submit" style="background:none; border:none; padding:0; cursor:pointer;">
                    <img src="${pageContext.request.contextPath}/resources/img/logout.png" alt="로그아웃" class="header-login" />
                </button>
            </form>
        </sec:authorize>
    </div>
</header>
