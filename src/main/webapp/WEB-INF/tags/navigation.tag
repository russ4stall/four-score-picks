<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="empty" %>

<div id="nav">

    <a class="links" href="${pageContext.request.contextPath}/home">Home</a>
    <a class="links" href="${pageContext.request.contextPath}/standings">Standings</a>
    <a class="links" href="${pageContext.request.contextPath}/mypicks">My Picks</a>
    <a style="margin-top: -3px;" class="links" href="${pageContext.request.contextPath}/trashTalk">Trash Talk<sup> NEW</sup></a>
    <%--<a class="links" href="${pageContext.request.contextPath}/contact">Contact</a>--%>

    <div id="logged_in_as">
        <c:if test="${sessionScope.user.admin}">
            <a class="admin_link" href="${pageContext.request.contextPath}/admin" >admin tools</a>
        </c:if>
         ${sessionScope.user.name}  &nbsp
        <a href="${pageContext.request.contextPath}/logout" >Log Out</a>

    </div>
</div>