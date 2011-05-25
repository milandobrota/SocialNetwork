    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comments</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
	<% if (session.getAttribute("personId") != null) { %>
        <h2>Add comment</h2>
        <form action="create_comment" method="post">
            <input type="hidden" name="postId" value="<%=request.getParameter("postId")%>" />
            <textarea name="text" rows="5" cols="25"></textarea><br />
            <input type="submit" value="Submit">
        </form>
	<% } %>
        <h4>Existing Comments:</h4>
        <table cellspacing="2" cellpadding="0" border="0">
            <tbody>
                <c:forEach var="comment" items="${comments}">
                    <tr>
                        <td>
                            <img src="${comment.commenter.picture}" height="100"/><br />
                            <i>${comment.commenter.firstName} ${comment.commenter.lastName}</i>
                        </td>
                        <td>
                            <p style="font-size:0.9em;">${comment.text}</p>
                            <br /><br />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
