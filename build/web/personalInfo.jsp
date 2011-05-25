<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<jsp:useBean id="person" scope="request" class="entity.Person"/>

<html>
  <head>
    <title>Personal Information</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body>
    <jsp:include page="header.jsp"/>
    <h2>Personal Information</h2>
    ${ friendRequestFragment } |
    <a href="wall?ownerId=${person.id}">Go to wall</a> |
    <a href="friends?personId=${person.id}">See friends</a>
    <br /><br />
    <table cellspacing="2" cellpadding="0" border="0">
      <tbody>
        <tr>
          <td>Photo:</td>
          <td><a href="${person.picture}"><img src="${person.picture}" height="128"/></a></td>
        </tr>
        <tr>
          <td>First Name:</td>
          <td>${person.firstName}</td>
        </tr>
        <tr>
          <td>Last Name:</td>
          <td>${person.lastName}</td>
        </tr>
        <tr>
          <td>Sex:</td>
          <td>${person.sex ? "Male" : "Female"} </td>
        </tr>
        <tr>
          <td>Birth Date:</td>
          <td>${person.formattedDateOfBirth}</td>
        </tr>
        <tr>
          <td>Email:</td>
          <td>${person.email}</td>
        </tr>
        <tr>
          <td>Place:</td>
          <td>${person.place}</td>
        </tr>
        <tr>
          <td>Website:</td>
          <td><a href="${person.website}" target="_blank" >${person.website}</a></td>
        </tr>
        <tr>
          <td>Education:</td>
          <td>${person.education}</td>
        </tr>
        <tr>
          <td>Employment:</td>
          <td>${person.employment}</td>
        </tr>
      </tbody>
    </table>
    <br />
  </body>
</html>







