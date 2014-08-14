<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.pick.MyPicksAction"--%>
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
    <h1>My Picks</h1>

    <div id="work_bench">
        <div id="make_your_picks_tool">
            <span class="tool_title">Make Your Picks for Week ${action.gameAndPickList[0].game.week}</span><br>
            <br>
            <div id="make_your_picks_instructions">
                <ul>
                    <li>
                        Make your selection for each game then click the update button.
                    </li>
                    <li>
                        Hot games have a flame next them. Select a team or leave it blank for the free point.
                    </li>
                    <li>
                        To remove a pick for the Hot Game, click on the pick.
                    </li>
                    <li>
                        To change a pick, simply choose the team and click the update button.
                    </li>
                    <li>
                        Once a game has started, you will no longer be able to alter your picks.
                    </li>
                    <li>
                        You must make at least one pick to receive the Hot Game free point.
                    </li>
                </ul>

            </div>
            <c:if test="${not empty action.actionErrors}">
                <div class="action-errors">
                    <c:forEach items="${action.actionErrors}" var="error">
                        <span>${error}</span>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${action.picksUpdated}">
                <div class="action-messages">
                        <span>INFO: Picks successfully updated!</span>
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/mypicks!execute" method="post">
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Away</th>
                        <th>&nbsp</th>
                        <th>Home</th>
                        <th>&nbsp</th>
                        <th>Your Picks</th>
                    </tr>
                    <c:set var="counter" value="0"/>
                    <c:forEach items="${action.gameAndPickList}" var="gameAndPick">

                        <tr>
                            <td>
                                    ${gameAndPick.game.date}
                            </td>

                            <td style="text-align: right">
                                <label for="${gameAndPick.game.awayTeam.name}">${gameAndPick.game.awayTeam.name}</label>

                                <c:if test="${!gameAndPick.game.gameHasStarted}">
                                    <input
                                    id="${gameAndPick.game.awayTeam.name}" type="radio"
                                    name="picks[${gameAndPick.game.id}]"
                                    value="${gameAndPick.game.awayTeam.id}">
                                </c:if>

                            </td>
                            <td>vs</td>
                            <td style="text-align: left">
                                <c:if test="${!gameAndPick.game.gameHasStarted}">
                                    <input id="${gameAndPick.game.homeTeam.name}" type="radio"
                                    name="picks[${gameAndPick.game.id}]"
                                    value="${gameAndPick.game.homeTeam.id}">
                                </c:if>
                                <label
                                    for="${gameAndPick.game.homeTeam.name}">${gameAndPick.game.homeTeam.name}</label>
                            </td>
                            <td>
                                <c:if test="${gameAndPick.game.hotGame}">
                                    <img src="${pageContext.request.contextPath}/images/flame1.png" alt="HG">
                                </c:if>
                            </td>
                            <td id="picks_col" style="text-align: center">
                                <c:choose>
                                    <c:when test="${gameAndPick.pick.pickTeamName == null && gameAndPick.game.hotGame}">
                                        <span style="color: orangered">FREE POINT</span>
                                    </c:when>
                                    <c:when test="${gameAndPick.pick.pickTeamName == null}">
                                        <span style="color: #FFA6A6; font-style: italic">none</span>
                                    </c:when>
                                    <c:when test="${gameAndPick.pick.pickTeamName != null && gameAndPick.game.hotGame}">
                                        <c:choose>
                                            <c:when test="${!gameAndPick.game.gameHasStarted}">
                                                <a title="Remove Pick" style="border-bottom: 1px dotted orange"
                                                href="${pageContext.request.contextPath}/mypicks!deletePick?gameId=${gameAndPick.game.id}">${gameAndPick.pick.pickTeamName}</a>
                                            </c:when>
                                            <c:when test="${gameAndPick.game.gameHasStarted}">
                                                ${gameAndPick.pick.pickTeamName}
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        ${gameAndPick.pick.pickTeamName}
                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </tr>

                    </c:forEach>
                    <tr>
                        <th>&nbsp</th>
                    </tr>
                    <tr>
                        <th colspan="6" style="text-align: center"><input type="submit" value="Update"/></th>
                    </tr>
                </table>
            </form>


        </div>

<h1>My Results</h1>

        <c:forEach items="${action.weekUserResultList}" var="resultList">

            <fsp:userweekresult  userResultList="${resultList}"/>
        </c:forEach>
    </div>

</div>
<fsp:aside userList="${action.seasonRoster}"/>
</body>
</html>