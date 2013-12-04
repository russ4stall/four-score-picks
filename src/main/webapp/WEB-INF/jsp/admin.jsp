<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.admin.AdminAction"--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
    <script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fourscorestyles.css">
    <title>ADMIN</title>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>
<fsp:navigation/>
<div id="wrapper">
    <h1>Administrator Tools</h1>

    <div id="work_bench">

            <a class="admin_tool_button" href="${pageContext.request.contextPath}/editSchedule">Edit Schedule</a>

        <div id="news_tool">
            <span class="tool_title">Post</span><br><br>
            <form action="admin!postNews" method="post">

                <textarea id="text_area_bg" name="newsText" cols="74" rows="10"> </textarea>
                <br>
                <input type="checkbox" name="sendAsEmail" value="true"> Email this to all users
                <br>
                <br>
                <input type="submit" value="Post">

            </form>
        </div>

    </div>
</div>
<fsp:aside userList="${action.seasonRoster}"/>
</body>
</html>