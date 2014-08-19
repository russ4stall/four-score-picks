<%--@elvariable id="action" type="com.github.russ4stall.fourscorepicks.pick.UserPicksAction"--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <fsp:head/>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>
<fsp:navigation/>
<div id="wrapper">
    <h1>${action.user.name}'s Picks</h1>



    <c:forEach items="${action.weekUserResultList}" var="resultList">

        <fsp:userPicks  userResultList="${resultList}"/>

    </c:forEach>
</div>

</div>
<fsp:aside userList="${action.seasonRoster}"/>
</body>
</html>