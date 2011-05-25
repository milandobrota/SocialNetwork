<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head>
    <title>Notifications</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <h2>Notifications</h2>
    <c:forEach var="notification" items="${notifications}">
      <a href="${notification.url}">${notification.text}</a><br />
    </c:forEach>
  </body>
</html>




