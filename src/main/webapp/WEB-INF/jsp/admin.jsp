<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.AdminAction"--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
    <script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

    <link rel="stylesheet" type="text/css" href="/fourscorestyles.css">
    <title>ADMIN</title>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>
<fsp:navigation/>
<div id="wrapper">
    <h1>Administrator Tools</h1>

    <div id="work_bench">

        <div id="news_tool">
            <span class="tool_title">Post</span><br><br>
            <form action="admin!postNews" method="post">

                <textarea name="newsText" cols="74" rows="10"> </textarea>
                <br>
                <input type="submit" value="Post">

            </form>

        </div>

        <div id="hot_game_tool">
            <span class="tool_title">Week ${action.gameList[0].week} Hot Games</span><br>
            <br>
                <table>


                <c:forEach items="${action.gameList}" var="game">
                    <tr>

                        <td>
                            <c:choose>
                               <c:when test="${game.hotGame}">
                                    <a href="admin!removeHotGame?gameIdHotGame=${game.id}&weekNumHotGame=${weekNum}"><img src="/images/minus.png"> ${game.awayTeam.name} @ ${game.homeTeam.name}</a>
                                    <img src="/images/flame1.png">
                                </c:when>
                                <c:when test="${!game.hotGame}">
                                    <a href="admin!addHotGame?gameIdHotGame=${game.id}&weekNumHotGame=${weekNum}"><img src="/images/add-icon.png"> ${game.awayTeam.name} @ ${game.homeTeam.name}</a>
                                </c:when>
                            </c:choose>
                        </td>
                       <!-- <td>${game.awayTeam.name} @ ${game.homeTeam.name}</td>-->
                    </tr>
                </c:forEach>



                </table>
        </div>

        <div id="new_game_tool">
            <span class="tool_title">Add a Game</span><br>
            <br>
            <form action="admin!addGame" method="post">
                <select name="awayTeamId">
                    <option>Away Team</option>
                    <option>----------------</option>
                    <option value="30">49ers</option>
                    <option value="17">Bears</option>
                    <option value="2">Bengels</option>
                    <option value="9">Bills</option>
                    <option value="13">Broncos</option>
                    <option value="3">Browns</option>
                    <option value="24">Buccaneers</option>
                    <option value="29">Cardinals</option>
                    <option value="16">Chargers</option>
                    <option value="14">Cheifs</option>
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
                    <option value="2">Bengels</option>
                    <option value="9">Bills</option>
                    <option value="13">Broncos</option>
                    <option value="3">Browns</option>
                    <option value="24">Buccaneers</option>
                    <option value="29">Cardinals</option>
                    <option value="16">Chargers</option>
                    <option value="14">Cheifs</option>
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
                wk:<input type="text" name="weekNumNewGame" size="1" value="${action.gameList[0].week + 1}">
                date:<input type="date" name="gameDay">
                time:<select name="gameTime">

                    <option value="13:00:00">1:00pm</option>
                    <option value="16:05:00">4:05pm</option>
                    <option value="16:25:00">4:25pm</option>
                    <option value="19:00:00">7:00pm</option>
                    <option value="19:10:00">7:10pm</option>
                    <option value="20:25:00">8:25pm</option>
                    <option value="20:30:00">8:30pm</option>
                    <option value="22:15:00">10:15pm</option>
                    <option value="22:20:00">10:20pm</option>
                </select>
                &nbsp <input type="submit" value="Submit">
            </form>
        </div>
    </div>
</div>
<fsp:aside userList="${action.seasonRoster}"/>
</body>
</html>