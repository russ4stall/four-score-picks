<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="empty" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ attribute name="userList" required="true" type="java.util.ArrayList" %>


<div id="aside">
    <div id="score_board">
        <h2>SCORE BOARD</h2>
            <c:set var="rank" value="1"/>
            <c:forEach items="${userList}" var="user" begin="0" end="9">

                <div id="display_user">
                      ${rank}.
                      ${user.name}
                      <c:if test="${rank == 1}">
                        <img src="/images/star-icon.png">
                      </c:if>
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
        <a href="http://www.espn.com" target="_blank">FourScorePress</a><br>

    </div>
</div>