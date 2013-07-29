<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="userResultList" required="true" type="java.util.ArrayList" %>




<div id="make_your_picks_tool">
    <span class="tool_title">Week ${userResultList[0].game.week} Results</span><br>
    <br>
    <div id="user_week_result_score">
         Your score for week ${userResultList[0].game.week}
        <div>${sessionScope.user.weekScore}</div>
    </div>
    <table>
        <tr>
            <th>Away</th>
            <th>Home</th>
            <th>&nbsp</th>
            <th>Your Picks</th>
        </tr>
        <c:set var="counter" value="0"/>
        <c:forEach items="${userResultList}" var="gameAndPick">
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
                        <img src="/images/flame1.png" alt="HG">
                    </c:if>
                </td>
                <td id="picks_col" style="text-align: center">
                    <c:choose>
                        <c:when test="${gameAndPick.pick.pickTeamName == null && gameAndPick.game.hotGame}">
                            <span style="color: orangered">FREE POINT</span> <sup>+1</sup>
                        </c:when>
                        <c:when test="${gameAndPick.pick.pickTeamName == null}">
                            <span style="color: #FFA6A6; font-style: italic">none</span>
                        </c:when>
                        <c:when test="${gameAndPick.pick.pickTeamName != null && gameAndPick.game.hotGame}">
                            <c:choose>
                                <c:when test="${gameAndPick.pick.pickTeamId == gameAndPick.game.winningTeam.id}">
                                    <span style="color: lightgreen">${gameAndPick.pick.pickTeamName}</span> <sup>+2</sup>
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
                                </c:when>
                                <c:otherwise>
                                    <span style="color: #FFA6A6">${gameAndPick.pick.pickTeamName}</span>
                                </c:otherwise>
                            </c:choose> </c:otherwise>
                    </c:choose>
               </td>
            </tr>
        </c:forEach>
    </table>



</div>