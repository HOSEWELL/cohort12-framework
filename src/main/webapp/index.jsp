<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="app.pages.Courses" %>
<%@ page import="java.time.LocalTime" %>

<%! Courses courses = new Courses(); %>
<!DOCTYPE html>
<html>
<head>
    <title>Training Portal</title>
    <style>
        body {
            font-family: Arial;
            margin: 40px;
            background-color: #f4f6f8;
        }
        header {
            background-color: #2c3e50;
            color: white;
            padding: 15px;
        }
        section {
            margin-top: 20px;
            padding: 15px;
            background: white;
            border-radius: 5px;
        }
        a {
            color: #3498db;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>

<body>

    <%-- Header --%>
    <jsp:useBean id="now" class="java.util.Date" />
    <fmt:formatDate value="${now}" pattern = "H" var = "hour" />

    <header>
        <h1>
        <c:choose>
            <c:when test="${hour < 12}">
                Good Morning!! Welcome to our portal: Hour <c:out value="${hour}"/><br/>
            </c:when>
            <c:when test="${hour > 17}">
                Good Evening!! Welcome to our portal: Hour <c:out value="${hour}"/><br/>
            </c:when>
            <c:otherwise>
                Good Afternoon!! Welcome to our portal<br/>
            </c:otherwise>
        </c:choose>
        Welcome to ${applicationScope.applicationName} Training PORTAL</h1>
        <c:out value="<p>Empowering Developers with Real-World Skills</p>" escapeXml="false"/>
    </header>

    <%-- Courses section --%>
    <section>
        <h2>Available Courses</h2>
        <ul>
            <%= courses.list() %>
        </ul>
    </section>

    <!-- About training -->
    <section>
        <h2>About Our Training</h2>
        <%
            String [] aboutInfos = {
                "<p>Our training programs focus on hands-on experience and real-world projects.</p>",
                "<p>We help developers build strong backend systems using modern technologies.</p>",
                "<p>Mentorship and practical coding sessions are at the core of our learning approach.</p>"
            };
            request.setAttribute("aboutInfos", aboutInfos);
        %>
        <c:forEach var="aboutInfo" items="${aboutInfos}">
            <p>${aboutInfo}</p>
        </c:forEach>

    </section>

    <!-- Schedule -->

    <jsp:useBean id="weekdaySchedule" class="app.pages.Schedule" />
    <jsp:setProperty name="weekdaySchedule" property="scheduleType" />
    <jsp:setProperty name="weekdaySchedule" property="scheduleTime" />

    <jsp:useBean id="weekendSchedule" class="app.pages.Schedule" />
    <jsp:setProperty name="weekendSchedule" property="scheduleType" value="Weekend Only Bootcamps" />
    <jsp:setProperty name="weekendSchedule" property="scheduleTime" value="9:00 AM - 3:00 PM" />
    <section>
        <h2>Upcoming Schedule</h2>
        <p>
        ${weekdaySchedule.scheduleType} : ${weekdaySchedule.scheduleTime}
        </p>
        <p>
        ${weekendSchedule.scheduleType} : ${weekendSchedule.scheduleTime}
        </p>
    </section>

    <jsp:include page="footer.jsp" />

</body>
</html>