<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.standings.StandingsAction"--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fsp:head/>
    <title>FourScorePicks</title>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>
<fsp:navigation/>
<div id="wrapper">
    <h1>Standings</h1>

    <div id="work_bench">
        <div id="season_standings_container">

        <div class="standings_tool">
            <span class="tool_title">Season</span><br>
            <br>

            <c:set var="rank" value="1"/>
            <c:forEach items="${action.seasonRoster}" var="user">

            <c:choose>
            <c:when test="${user.name == sessionScope.user.name}">
            <div class="rank_row" style="border: 1px dotted lightcyan">
                </c:when>
                <c:otherwise>
                <div class="rank_row">
                    </c:otherwise>
                    </c:choose>

                        ${rank}.
                    <span>
                            <a href="${pageContext.request.contextPath}/userPicks?userId=${user.id}">${user.name}</a>

                    </span>
                    <span style="float: right">
                            ${user.seasonScore}
                    </span>
                </div>
                <c:set var="rank" value="${rank+1}"/>
                </c:forEach>

            </div>
        </div>
        </div>





        <div id="week_standings_container">


            <div class="standings_tool">
                <span class="tool_title">Week ${action.weekNum}</span><br>
                <br>

                <c:set var="rank" value="1"/>
                <c:forEach items="${action.weekRoster}" var="user">


                <c:choose>
                    <c:when test="${user.name == sessionScope.user.name}">
                            <div class="rank_row" style="border: 1px dotted lightcyan">
                    </c:when>
                    <c:otherwise>
                        <div class="rank_row">
                    </c:otherwise>
                </c:choose>
                            ${rank}.
                    <span>
                        <a href="${pageContext.request.contextPath}/userPicks?userId=${user.id}">${user.name}</a>
                    </span>
                    <span style="float: right">
                            ${user.weekScore}
                    </span>
                    </div>
                    <c:set var="rank" value="${rank+1}"/>
                    </c:forEach>

                </div>




        </div>
</div>

        </div>

        <fsp:aside userList="${action.seasonRoster}"/>
</body>
</html>