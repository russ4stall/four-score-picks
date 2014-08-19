<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fsp" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<fsp:head/>
    <title>FourScorePicks</title>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>
<div id="welcome_text">
    <h1>Welcome</h1>
    <p>
        Four Score Picks is a site dedicated for friends and sports fans to come together, make predictions, and compete.

    </p>
</div>
<div id="user_form">
    <b>Log In</b>
    <br><br>
<s:form action="login!execute" method="post">
    <s:textfield key="email" type="email" label="Email Address"/>
    <s:password key="password" label="Password"/>
    <s:checkbox key="rememberMe" label="Remember Me" value="true"/>
    <s:submit value="Login"/>
</s:form>
   Don't have an account? <a style="color: blue; border-bottom: 1px dotted blue;" href="${pageContext.request.contextPath}/register">Sign Up</a>
</div>
</body>
</html>