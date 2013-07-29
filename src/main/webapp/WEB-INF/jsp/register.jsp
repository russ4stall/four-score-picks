<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" type="text/css" href="/fourscorestyles.css">
    <title>FourScorePicks</title>
</head>
<body>
<div id="banner">FOUR SCORE PICKS</div>

<div id="user_form">
    <b>Sign Up</b>
    <br><br>
    <s:form action="register!execute" method="post">
        <s:textfield key="name" label="Name"/>
        <s:textfield key="email" label="Email Address"/>
        <s:textfield key="email2" label="Confirm Email Address"/>
        <s:password key="password" label="Password"/>
        <s:password key="password2" label="Confirm Password"/>
        <s:submit value="Submit"/>
    </s:form>
    Already have an account? <a href="login">Log In</a>
</div>
</body>
</html>