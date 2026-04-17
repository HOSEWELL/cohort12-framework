<jsp:forward page="contact_us_display.jsp" >
    <jsp:param name="name" value="<%= request.getParameter(\"name\") %>" />
    <jsp:param name="email" value="<%= request.getParameter(\"email\") %>" />
    <jsp:param name="subject" value="<%= request.getParameter(\"subject\") %>" />
    <jsp:param name="message" value="<%= request.getParameter(\"message\") %>" />
    <jsp:param name="country" value="Kenya" />
    <jsp:param name="county" value="Nairobi & Murang'a" />
    <jsp:param name="town" value="Nairobi" />
</jsp:forward>