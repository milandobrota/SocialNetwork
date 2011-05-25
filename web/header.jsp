<div>Welcome,
<% if(session.getAttribute("personId") != null) { %>
    <a href="profile"><%= session.getAttribute("personName")%></a><br />
<%} else {%>
    Guest<br />
<% } %>
    <a href="all_people">Everybody</a> |
<% if(session.getAttribute("personId") != null) { %>
    <a href="wall">My wall</a> |
    <a href="addPost.jsp">New post</a> | 
    <a href="friends">Friends</a> |
    <a href="friend_request">Friend requests</a> |
    <a href="notifications">Notifications</a> |
    <a href="edit_profile">Edit profile</a> |
    <a href="editPassword.jsp">Change password</a> |
    <a href="logout">Logout</a>
<% } else { %>
    <a href="login">Login</a> |
    <a href="signup.jsp">Sign up</a>
<% } %>
</div>
