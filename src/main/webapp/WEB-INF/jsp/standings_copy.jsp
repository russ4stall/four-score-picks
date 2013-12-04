<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.standings.StandingsAction"--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fourscorestyles.css">
    <title>FourScorePicks</title>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>
<fsp:navigation/>
<div id="wrapper">
    <h1>Standings</h1>

    <div id="work_bench">
    <c:forEach items="${action.weekStats}" var="weekStat" >

    <div id="week_stats">
        <h2>Week ${weekStat.weekNum}</h2>
        <div id="season_roster" class="roster">
        <span class="sea_wee" >Season</span>
            <br><br>
            <c:set var="seasonRank" value="1"/>
            <c:forEach items="${weekStat.seasonRoster.users}" var="user">
                <c:choose>
                    <c:when test="${user.name == sessionScope.user.name}">
                        <div class="rank_row" style="border: 1px dotted lightcyan">
                                ${seasonRank}.
                                <span>
                                    <a href="${pageContext.request.contextPath}/userPicks?userId=${user.id}">${user.name}</a>
                                </span>
                                <span style="float: right">
                                        ${user.weeklySeasonScores[weekStat.weekNum-1]}
                                </span>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="rank_row">
                                ${seasonRank}.
                                <span>
                                    <a href="${pageContext.request.contextPath}/userPicks?userId=${user.id}">${user.name}</a>
                                </span>
                                <span style="float: right">
                                        ${user.weeklySeasonScores[weekStat.weekNum-1]}
                                </span>
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:set var="seasonRank" value="${seasonRank+1}"/>
            </c:forEach>

        </div>

        <div id="week_roster" class="roster">
            <span class="sea_wee">Week</span>
            <br><br>
            <c:set var="weekRank" value="1"/>
            <c:forEach items="${weekStat.weekRoster.users}" var="user">
                <c:choose>
                    <c:when test="${user.name == sessionScope.user.name}">
                        <div class="rank_row" style="border: 1px dotted lightcyan">
                                ${weekRank}.
                                <span>
                                    <a href="${pageContext.request.contextPath}/userPicks?userId=${user.id}">${user.name}</a>
                                </span>
                                <span style="float: right">
                                        ${user.weeklyScores[weekStat.weekNum-1]}
                                </span>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="rank_row">
                                ${weekRank}.
                                <span>
                                    <a href="${pageContext.request.contextPath}/userPicks?userId=${user.id}">${user.name}</a>
                                </span>
                                <span style="float: right">
                                        ${user.weeklyScores[weekStat.weekNum-1]}
                                </span>
                        </div>
                    </c:otherwise>
                </c:choose>
                <c:set var="weekRank" value="${weekRank+1}"/>
            </c:forEach>


        </div>
    </div>
    </c:forEach>
    </div>
</div>
<fsp:aside userList="${action.seasonRoster.users}"/>
</body>
</html>