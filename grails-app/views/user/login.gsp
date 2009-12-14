<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
  </head>
  <body>
    <h1>Login</h1>
  <g:form controller="user" action="login">
    <input type="text" name="login" />
    <input type="password" name="password" />
    <input type="submit" value="login" />
  </g:form>
</body>
</html>
