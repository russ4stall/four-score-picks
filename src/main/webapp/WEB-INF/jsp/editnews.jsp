<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.EditNewsAction"--%>
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
    <title>ADMIN-Edit</title>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>
<fsp:navigation/>
<div id="wrapper">
    <h1>Edit</h1>

    <div id="news_tool">
        <span class="tool_title" style="color: white">Edit</span>

                <a title="Delete this post"
                   href="${pageContext.request.contextPath}/editNews!deleteNews?newsId=${action.news.id}">
                    delete
                </a>

        <br><br>

        <form action="${pageContext.request.contextPath}/editNews!editNews" method="post">
            <input type="hidden" name="newsId" value="${action.news.id}">
            <textarea name="newsText" cols="74" rows="10">
                ${action.news.newsText}
            </textarea>
            <br>
            <input type="submit" value="Update">


        </form>

    </div>

</div>
<fsp:aside userList="${action.seasonRoster}"/>
</body>
</html>