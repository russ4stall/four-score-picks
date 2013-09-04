<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.HomeAction"--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fourscorestyles.css">
    <title>FourScorePicks-Home</title>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>
<fsp:navigation/>
<div id="wrapper">
    <h1>News</h1>


    <c:forEach items="${action.newsList}" var="news">
    <div id="news_block">

        <div class="news_date"><fmt:formatDate value="${news.datePosted}" type="both" pattern="EEEEEE MMMMMMMMM dd, yyyy"/></div>
        <c:if test="${sessionScope.user.admin}">
            <a href="${pageContext.request.contextPath}/editNews?newsId=${news.id}">edit</a>
        </c:if>

        ${news.newsText}


    </div>

    </c:forEach>

</div>
<fsp:aside userList="${action.seasonRoster}"/>
</body>
</html>