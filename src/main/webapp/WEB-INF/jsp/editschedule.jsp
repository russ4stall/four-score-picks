<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.admin.EditScheduleAction"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
    <script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

    <fsp:head/>
    <title>ADMIN</title>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>
<fsp:navigation/>
<div id="wrapper">
    <h1>Administrator Tools</h1>

    <div id="work_bench">


        <div id="new_game_tool">
            <span class="tool_title">Add a Game</span><br>
            <br>
            <form action="${pageContext.request.contextPath}/editSchedule!addGame" method="post">
                <select name="awayTeamId" autofocus>
                    <option>Away Team</option>
                    <option>----------------</option>
                    <option value="30">49ers</option>
                    <option value="17">Bears</option>
                    <option value="2">Bengals</option>
                    <option value="9">Bills</option>
                    <option value="13">Broncos</option>
                    <option value="3">Browns</option>
                    <option value="24">Buccaneers</option>
                    <option value="29">Cardinals</option>
                    <option value="16">Chargers</option>
                    <option value="14">Chiefs</option>
                    <option value="6">Colts</option>
                    <option value="25">Cowboys</option>
                    <option value="10">Dolphins</option>
                    <option value="27">Eagles</option>
                    <option value="21">Falcons</option>
                    <option value="26">Giants</option>
                    <option value="7">Jaguars</option>
                    <option value="12">Jets</option>
                    <option value="18">Lions</option>
                    <option value="19">Packers</option>
                    <option value="22">Panthers</option>
                    <option value="11">Patriots</option>
                    <option value="15">Raiders</option>
                    <option value="32">Rams</option>
                    <option value="1">Ravens</option>
                    <option value="28">Redskins</option>
                    <option value="23">Saints</option>
                    <option value="31">Seahawks</option>
                    <option value="4">Steelers</option>
                    <option value="5">Texans</option>
                    <option value="8">Titans</option>
                    <option value="20">Vikings</option>
                </select>
                @
                <select name="homeTeamId">
                    <option>Home Team</option>
                    <option>----------------</option>
                    <option value="30">49ers</option>
                    <option value="17">Bears</option>
                    <option value="2">Bengals</option>
                    <option value="9">Bills</option>
                    <option value="13">Broncos</option>
                    <option value="3">Browns</option>
                    <option value="24">Buccaneers</option>
                    <option value="29">Cardinals</option>
                    <option value="16">Chargers</option>
                    <option value="14">Chiefs</option>
                    <option value="6">Colts</option>
                    <option value="25">Cowboys</option>
                    <option value="10">Dolphins</option>
                    <option value="27">Eagles</option>
                    <option value="21">Falcons</option>
                    <option value="26">Giants</option>
                    <option value="7">Jaguars</option>
                    <option value="12">Jets</option>
                    <option value="18">Lions</option>
                    <option value="19">Packers</option>
                    <option value="22">Panthers</option>
                    <option value="11">Patriots</option>
                    <option value="15">Raiders</option>
                    <option value="32">Rams</option>
                    <option value="1">Ravens</option>
                    <option value="28">Redskins</option>
                    <option value="23">Saints</option>
                    <option value="31">Seahawks</option>
                    <option value="4">Steelers</option>
                    <option value="5">Texans</option>
                    <option value="8">Titans</option>
                    <option value="20">Vikings</option>
                </select>
                <br>
                wk:<input type="text" name="weekNumNewGame" size="2" value="${action.weekNum + 1}">
                date:<input type="date" name="gameDay">
                time:<select name="gameTime">

                <option value="13:00:00">1:00pm</option>
                <option value="16:05:00">4:05pm</option>
                <option value="16:25:00">4:25pm</option>
                <option value="19:00:00">7:00pm</option>
                <option value="19:10:00">7:10pm</option>
                <option value="20:25:00">8:25pm</option>
                <option value="20:30:00">8:30pm</option>
                <option value="20:40:00">8:40pm</option>
                <option value="22:15:00">10:15pm</option>
                <option value="22:20:00">10:20pm</option>
            </select>
                &nbsp <input type="submit" value="Submit">
            </form>
        </div>

        <div id="schedule_tool">
            <span class="tool_title">Schedule</span><br>
            <br>
            <table>

                    <tr><th>&nbsp;</th>
                        <th>Wk</th>
                        <th>Date</th>
                        <th>Away</th>
                        <th>Home</th>
                        <th>&nbsp;</th>
                        <th colspan="2">Scores</th>

                    </tr>
                <c:forEach items="${action.gameList}" var="game">
                    <tr>
                        <c:choose>
                           <c:when test="${game.week >= action.weekNum}">
                               <th>
                                   <a href="${pageContext.request.contextPath}/editSchedule!removeGame?gameId=${game.id}">
                                       <img title="Delete Game" src="${pageContext.request.contextPath}/images/minus.png">
                                   </a>
                               </th>
                           </c:when>
                            <c:otherwise>
                                <th>&nbsp;</th>
                            </c:otherwise>
                       </c:choose>
                        <td style="text-align: center">
                            ${game.week}
                        </td>
                        <td>
                            <fmt:formatDate value="${game.gameTime}" pattern="E MMM d, h:mm"/>
                        </td>


                        <c:choose>
                            <c:when test="${game.awayTeam.id == game.winningTeam.id}">
                                <td style="text-align: center; border: 1px dotted green">${game.awayTeam.name}</td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align: center; ">${game.awayTeam.name}</td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${game.homeTeam.id == game.winningTeam.id}">
                                <td style="text-align: center; border: 1px dotted green">${game.homeTeam.name}</td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align: center; ">${game.homeTeam.name}</td>
                            </c:otherwise>
                        </c:choose>
                        <td style="text-align: center;">
                            <c:choose>
                                <c:when test="${game.hotGame}">
                                    <a href="${pageContext.request.contextPath}/editSchedule!removeHotGame?gameIdHotGame=${game.id}&weekNumHotGame=${game.week}">
                                        <img style="border-bottom: 1px solid orangered" title="Remove Hot Game" src="${pageContext.request.contextPath}/images/flame1.png">
                                    </a>

                                </c:when>
                                <c:when test="${!game.hotGame}">
                                    <a href="${pageContext.request.contextPath}/editSchedule!addHotGame?gameIdHotGame=${game.id}&weekNumHotGame=${game.week}">
                                        <span title="Make Hot Game" style="border: 1px dotted orange">&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                    </a>
                                </c:when>
                            </c:choose>
                        </td>
                    <form action="${pageContext.request.contextPath}/editSchedule!setResult" method="post">
                        <input type="hidden" name="gameId" value="${game.id}">
                        <input type="hidden" name="homeTeamId" value="${game.homeTeam.id}">
                        <input type="hidden" name="awayTeamId" value="${game.awayTeam.id}">

                        <td style="text-align: center">
                            <input type="text" name="awayTeamScore" size="2" value="${game.awayTeamScore}">
                        </td>
                        <td style="text-align: center">
                            <input type="text" name="homeTeamScore" size="2" value="${game.homeTeamScore}">
                        </td>
                        <th>
                            <input type="submit" value="Update">
                        </th>
                    </form>
                    </tr>
                </c:forEach>



            </table>
        </div>

    </div>
</div>
<fsp:aside userList="${action.seasonRoster}"/>
</body>
</html>