<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head>
    <title>Add Post</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <h2>Add Post</h2>
    <form action="create_post" method="post" enctype="multipart/form-data">
      <table cellspacing="2" cellpadding="0" border="0">
        <tbody>
          <tr>
            <td>Title:</td>
            <td><input type="text" name="title" size="25" /></td>
          </tr>
          <tr>
            <td>Text:</td>
            <td><textarea name="text" rows="5" cols="25"></textarea></td>
          <tr>
            <td>Upload Photo:</td>
            <td><input type="file" name="photo" size="25" /></td>
          </tr>
          <tr>
            <td>Add Youtube Video ID:</td>
            <td><input type="text" name="video" size="25" /></td>
          </tr>
          <tr>
            <td>Add Link:</td>
            <td><input type="text" name="link" size="25" /></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>
                <input type="hidden" name="ownerId" value="<%= request.getParameter("ownerId") %>" />
                <input type="submit" value="Submit" />
            </td>
          </tr>
        </tbody>
      </table>
    </form>
  </body>
</html>




