<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String contextPath = request.getContextPath();
%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/header.css" />

<header class="header">
    <div class="header-left">
        <a href="/">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고" class="header-logo" />
        </a>
    </div>

    <div class="header-center">
        <h1 class="header-title">星結び</h1>
    </div>

    <div class="header-right">
        <a href="/login">
            <img src="${pageContext.request.contextPath}/resources/img/login.png" alt="로그인" class="header-login" />
        </a>
    </div>
</header>