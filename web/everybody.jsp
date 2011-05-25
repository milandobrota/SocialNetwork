<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head>
    <title>Everybody</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <h2>Everybody</h2>
    Sort by:<br />
    Name <a href="all_people?order=ASC&orderBy=name">ASC</a> <a href="all_people?order=DESC&orderBy=name">DESC</a> |
    Birth Date <a href="all_people?order=ASC&orderBy=dateOfBirth">ASC</a> <a href="all_people?order=DESC&orderBy=dateOfBirth">DESC</a> |
    Place <a href="all_people?order=ASC&orderBy=place">ASC</a> <a href="all_people?order=DESC&orderBy=place">DESC</a> <br /><br />

    <table>
    <c:forEach var="person" items="${people}">
	    <tr>
		    <td>
			    <a href="profile?personId=${person.id}"><img src="${person.picture}" height="64"/></a>
		    </td>
		    <td>
			    <a href="profile?personId=${person.id}">${person.firstName} ${person.lastName}</a><br />
		    </td>
	    </tr>
    </c:forEach>
      </table>
  </body>
</html>




