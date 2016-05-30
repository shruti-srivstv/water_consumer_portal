<!-- Not used unless you declare a <form-login login-page="/login.jsp"/> element -->

<html>
<head>
  <title>Custom Spring Security Login</title>
</head>

<body>
  <h1>Custom Spring Security Login</h1>

<%
  if (request.getParameter("login_error") != null) {
%>
Your login attempt was not successful, try again. ${SPRING_SECURITY_LAST_EXCEPTION.message}<br/><br/>
<%
  }
%>

<form action="login" method="POST">
  <table>
    <tr><td>User:</td><td><input type='text' name='username' value=''/></td></tr>
    <tr><td>Password:</td><td><input type='password' name='password'></td></tr>
    <tr><td><input type="checkbox" name="remember-me"></td><td>Don't ask for my password for two weeks</td></tr>
    <tr><td colspan='2'><input name="submit" type="submit"></td></tr>
    <tr><td colspan='2'><input name="reset" type="reset"></td></tr>
  </table>
</form>

</body>

</html>
