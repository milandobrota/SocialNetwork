<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head>
    <title>Wall</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  </head>
  <body>


    <jsp:include page="header.jsp"/>
    <h2>Wall</h2>
    <a href="wall?ownerId=${wallOwnerId}">Chronological</a> |
    <a href="wall?ownerId=${wallOwnerId}&filter=popular">Popular</a>
    <br /><br />
    <a href="addPost.jsp?ownerId=${wallOwnerId}">Post on this wall</a>
    <br /><br />

    <table cellspacing="2" cellpadding="0" border="0">
      <tbody>
        <c:forEach var="post" items="${posts}">
            <tr>
              <td>
                <a href="profile?personId=${post.poster.id}"><img src="${post.poster.picture}" height="100"/></a><br />
                <a href="profile?personId=${post.poster.id}"><i>${post.poster.firstName} ${post.poster.lastName}</i></a>
              </td>
              <td>
                <h3>${post.title}</h3>
                <p>${post.text}</p>
			<c:forEach var="picture" items="${post.pictures}">
				<p><a href="${picture.image}"><img src="${picture.image}" width="200" /></a></p>
			</c:forEach>
			<c:forEach var="video" items="${post.videos}">
					<object style="height: 390px; width: 640px">
<param name="movie" value="${video.link}?version=3&feature=player_embedded">
<param name="allowFullScreen" value="true">
<param name="allowScriptAccess" value="always">
<embed src="${video.link}?version=3&feature=player_embedded" type="application/x-shockwave-flash" allowfullscreen="true" allowScriptAccess="always" width="425" height="344"></object>
			</c:forEach>
			<c:forEach var="link" items="${post.links}">
				<p><a href="${link.link}" target="_blank">${link.link}</a></p>
			</c:forEach>
                <p>${post.formattedDate}</p>
                <p><i><a href="comments?postId=${post.id}">View/Add Comments</a></i></p>
                <br />
              </td>
            </tr>
        </c:forEach>
      </tbody>
    </table>

    
  </body>
</html>







