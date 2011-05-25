<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head>
    <title>Friend Requests</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <h2>Friend Requests</h2>
    <c:forEach var="requester" items="${friendRequests}">
      <a href="profile?personId=${requester.id}">${requester.firstName} ${requester.lastName}</a><br />
      <a href="profile?personId=${requester.id}"><img src="${requester.picture}" height="100"/></a>
      <table>
        <tbody>
          <tr>
            <td>
	      <form action="accept_friend_request">
                <input type="hidden" name="sourceId" value="${requester.id}" />
                <input type="submit" value="Confirm" />
	      </form>
	    </td>
            <td>
	      <form action="decline_friend_request">
                <input type="hidden" name="sourceId" value="${requester.id}" />
	        <input type="submit" value="Ignore" />
	      </form>
	    </td>
          </tr>
        </tbody>
      </table>
    </c:forEach>
  </body>
</html>




