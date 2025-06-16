<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String contextPath = request.getContextPath();
%>

<!-- 헤더 전용 CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css" />

<header class="header">
    <!-- 왼쪽: 로고 -->
    <div class="header-left">
        <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Hoshimusubi 로고" class="header-logo" />
        </a>
    </div>

    <!-- 가운데: 타이틀 -->
    <div class="header-center">
        <h1 class="header-title">星結び</h1>
    </div>

    <!-- 오른쪽: 로그인 -->
    <div class="header-right">
        <a href="${pageContext.request.contextPath}/login">
            <img src="${pageContext.request.contextPath}/resources/img/login.png" alt="ログイン" class="header-login" />
        </a>
    </div>
</header>

