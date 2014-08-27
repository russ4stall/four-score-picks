<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.pick.UserPicksAction"--%>
<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="userResultList" required="true" type="java.util.ArrayList" %>




<div id="user_week_result_tool">
    <span class="tool_title">Week ${userResultList[0].game.week} Results</span><br>
    <br>

    <c:set var="pointsPossible" value="0"/>
    <c:set var="userScore" value="0"/>

    <table>
        <tr>
            <th>Away</th>
            <th>Home</th>
            <th>&nbsp</th>
            <th>Your Picks</th>
        </tr>

        <c:forEach items="${userResultList}" var="gameAndPick">
        <c:if test="${gameAndPick.game.gameHasStarted}">
            <c:choose>
                <c:when test="${gameAndPick.game.hotGame}">
                    <c:set var="pointsPossible" value="${pointsPossible+2}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="pointsPossible" value="${pointsPossible+1}"/>
                </c:otherwise>
            </c:choose>

            <tr>
                <c:choose>
                    <c:when test="${gameAndPick.game.awayTeam.id == gameAndPick.game.winningTeam.id}">
                        <td style="text-align: center; border: 1px dotted green">${gameAndPick.game.awayTeam.name}</td>
                    </c:when>
                    <c:otherwise>
                        <td style="text-align: center; ">${gameAndPick.game.awayTeam.name}</td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${gameAndPick.game.homeTeam.id == gameAndPick.game.winningTeam.id}">
                        <td style="text-align: center; border: 1px dotted green">${gameAndPick.game.homeTeam.name}</td>
                    </c:when>
                    <c:otherwise>
                        <td style="text-align: center; ">${gameAndPick.game.homeTeam.name}</td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <c:if test="${gameAndPick.game.hotGame}">
                        <img src="${pageContext.request.contextPath}/images/flame1.png" alt="HG">
                    </c:if>
                </td>
                <td id="picks_col" style="text-align: center">
                    <c:choose>
                        <c:when test="${gameAndPick.pick.pickTeamName == null && gameAndPick.game.hotGame && gameAndPick.game.gameHasStarted}">
                            <span style="color: orangered">FREE POINT</span> <sup>+1</sup>
                            <c:set var="userScore" value="${userScore+1}"/>
                        </c:when>
                        <c:when test="${gameAndPick.pick.pickTeamName == null}">
                            <span style="color: #FFA6A6; font-style: italic">none</span>
                        </c:when>
                        <c:when test="${gameAndPick.pick.pickTeamName != null && gameAndPick.game.hotGame}">
                            <c:choose>
                                <c:when test="${gameAndPick.pick.pickTeamId == gameAndPick.game.winningTeam.id}">
                                    <span style="color: lightgreen">${gameAndPick.pick.pickTeamName}</span> <sup>+2</sup>
                                    <c:set var="userScore" value="${userScore+2}"/>
                                </c:when>
                                <c:otherwise>
                                    <span style="color: #FFA6A6">${gameAndPick.pick.pickTeamName}</span>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${gameAndPick.pick.pickTeamId == gameAndPick.game.winningTeam.id}">
                                    <span style="color: lightgreen">${gameAndPick.pick.pickTeamName}</span> <sup>+1</sup>
                                    <c:set var="userScore" value="${userScore+1}"/>
                                </c:when>
                                <c:otherwise>
                                    <span style="color: #FFA6A6">${gameAndPick.pick.pickTeamName}</span>
                                </c:otherwise>
                            </c:choose> </c:otherwise>
                    </c:choose>
                </td>
            </tr>

        </c:if>
        </c:forEach>
    </table>

    <c:if test="${userScore >= pointsPossible && userScore > 0}">
        <div id="all_right">
            <c:set var="userScore" value="${userScore + 5}"/>
            <div>You got them all right! +5</div>
        </div>
    </c:if>

    <div id="user_week_result_score">
        &nbsp &nbsp &nbsp Score for week ${userResultList[0].game.week}
        <div>${userScore}</div>
    </div>

</div>

