<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.content.action.addcommentaction"--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="comment_block${action.comment.id}" class="comment_block">
    <c:if test="${sessionScope.user.name == action.comment.user.name}">
        <div class="delete_comment_button${action.comment.id} fancy_button" style="float: right">delete</div>
    </c:if>
    <div style="float: left;">${action.comment.user.name}</div>
    <div class="news_date" style="max-width:200px;clear: left;"><fmt:formatDate value="${action.comment.datePosted}" type="both" pattern="k:m a  EE MM/dd/yy"/></div>

    <div id="comment_text">${action.comment.commentText}</div>

</div>