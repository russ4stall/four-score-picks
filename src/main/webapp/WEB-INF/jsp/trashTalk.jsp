<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.content.action.TrashTalkAction"--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fsp:head/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

    <script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
    <script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

    <title>FourScorePicks-Home</title>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>
<fsp:navigation/>
<div id="wrapper">
    <h1>Trash Talk</h1>
<div id="comment_text_area">
    <form id="new_comment_form" action="${pageContext.request.contextPath}/addComment" method="post">
        <textarea  name="commentText" id="texting" cols="49" rows="10"></textarea>
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
<fsp:aside userList="${action.seasonRoster}"/>

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