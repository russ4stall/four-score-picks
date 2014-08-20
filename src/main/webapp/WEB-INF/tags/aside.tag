<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="empty" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ attribute name="userList" required="true" type="java.util.ArrayList" %>


<div id="aside">
    <div id="score_board">
        <h2>TOP TEN</h2>
            <c:set var="rank" value="1"/>
            <c:forEach items="${userList}" var="user" begin="0" end="9">

                <div id="display_user">
                      ${rank}.
                      <a href="${pageContext.request.contextPath}/userPicks?userId=${user.id}">${user.name}</a>
                    <span style="float: right">${user.seasonScore}</span>
                </div>
                <c:set var="rank" value="${rank+1}"/>
            </c:forEach>


    </div>
    <div id="picks_resources">
        <h4>Quick Links</h4>

        <a href="http://espn.go.com/nfl/picks" target="_blank">ESPN Expert Picks</a><br>
        <a href="http://www.nfl.com/injuries" target="_blank">NFL Injury Report</a><br>
        <a href="http://espn.go.com/nfl/schedule" target="_blank">Schedule</a><br>
        <a href="http://www.fourscorepress.com" target="_blank">FourScorePress</a><br>
        <br>
       <%-- <a href="${pageContext.request.contextPath}/trashTalk">Report a Bug!</a><br>--%>

    </div>


</div>