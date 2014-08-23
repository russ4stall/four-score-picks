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
    <div id="week_stats">
        <h2>Week ${weekStat.weekNum}</h2>
        <div id="season_roster" class="roster">
        <span class="sea_wee" >Season</span>
            <br><br>
            <c:set var="seasonRank" value="1"/>
            <c:forEach items="${weekStat.seasonRoster.users}" var="user">
                <!--highlight the users rows -->
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

    </div>

<div id="comment_wrapper" style="float:left;">
    <div id="comment_text_area">
        <form id="new_comment_form" action="${pageContext.request.contextPath}/addComment" method="post">
            <textarea  name="commentText" id="texting" cols="49" rows="5"></textarea>
            <input type="hidden" name="userId" value="${sessionScope.user.id}">
            <input type="submit" value="Post Comment" class="fancy_button">
        </form>
    </div>
    <%--<div id="post_comment_button" class="fancy_button">Post Comment</div>--%>

    <div id="comment_section">
        <c:forEach items="${action.comments}" var="comment">
            <div id="comment_block${comment.id}" class="comment_block">
                <c:if test="${sessionScope.user.name == comment.user.name}">
                    <div class="delete_comment_button${comment.id} fancy_button" style="float: right">delete</div>
                </c:if>
                <div style="float: left;">${comment.user.name}</div>
                <div class="news_date" style="max-width:200px;clear: left;"><fmt:formatDate value="${comment.datePosted}" type="both" pattern="h:mm a  EE MM/dd/yy"/></div>

                <div id="comment_text">${comment.commentText}</div>
            </div>
        </c:forEach>
    </div>
</div>
</div>
<fsp:aside userList="${action.seasonRoster.users}"/>
<script type="text/javascript">
    $(document).ready(function () {
        <c:forEach items="${action.comments}" var="comment">
        makeComment(${comment.id});
        </c:forEach>

        /*
         $('#new_comment_form').submit(function() {
         // submit the form
         $(this).ajaxSubmit(newCommentOptions);
         // return false to prevent normal browser submit and page navigation
         return false;
         });

         $("#post_comment_button").button().click(function () {
         $("#new_comment_form").submit();
         });

         *//*$("#post_comment_button").button().click(function () {
         alert($("#texting").val());
         $ajax({
         url: "${pageContext.request.contextPath}/addComment",
         data: {userId: ${sessionScope.user.id}, commentText: "nnn"},
         success: function (responseText, textStatus, jqXHR) {
         $("#comment_section").append(responseText);
         var commentId = jqXHR.getResponseHeader("Comment-Id");
         makeComment(commentId);
         }
         });
         });
         *//*

         var newCommentOptions = {

         resetForm: true,
         dataType: "html",
         success: function (responseText, textStatus, jqXHR) {
         $("#comment_section").append(responseText);
         var commentId = jqXHR.getResponseHeader("Comment-Id");
         makeComment(commentId);
         }
         };*/

    });

    function makeComment(id){
        var selector = "#comment_block" + id;
        $(".delete_comment_button"+id).click(function(){
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteComment?commentId=" + id,
                success: function () {
                    $(selector).hide();
                }
            });
        });
    }
</script>

</body>
</html>