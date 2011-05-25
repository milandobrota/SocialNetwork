<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head>
    <title>Edit Password</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <br />
    <%= request.getAttribute("error") == null ? "" : request.getAttribute("error") %>
    <h2>Edit Password</h2>
    <form action="update_password" method="post">
      <table cellspacing="2" cellpadding="0" border="0">
        <tbody>
          <tr>
            <td>Old Password:</td>
            <td><input type="password" name="oldPassword" size="25" value="<%= (request.getParameter("password") == null) ? "" : request.getParameter("password") %>"/></td>
          </tr>
          <tr>
            <td>New Password:</td>
            <td><input type="password" name="newPassword" size="25" /></td>
          </tr>
          <tr>
            <td>Password Confirmation:</td>
            <td><input type="password" name="passwordConfirmation" size="25" /></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Submit" /></td>
          </tr>
        </tbody>
      </table>
    </form>
  </body>
</html>




