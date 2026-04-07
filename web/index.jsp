<%-- 
    Document   : index
    Created on : 07/04/2026, 11:55:47
    Author     : jdgal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Academic System</title>
    </head>
    <body>

        <h1>🎓 Academic Management System</h1>

        <!-- 🔥 BOTONES DE NAVEGACIÓN -->
        <form action="studentInfo.jsp" method="GET" style="display:inline;">
            <input type="submit" value="➕ Manage Students" />
        </form>

        <form action="courseInfo.jsp" method="GET" style="display:inline;">
            <input type="submit" value="➕ Manage Courses" />
        </form>

        <hr>

        <!-- 👨‍🎓 TABLA DE ESTUDIANTES -->
        <h2>Students</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Year Level</th>
            </tr>

            <c:forEach var="s" items="${allStudents}">
                <tr>
                    <td>${s.studentId}</td>
                    <td>${s.firstName}</td>
                    <td>${s.lastName}</td>
                    <td>${s.yearLevel}</td>
                </tr>
            </c:forEach>
        </table>

        <br><br>

        <!-- 📚 TABLA DE CURSOS -->
        <h2>Courses</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Code</th>
                <th>Name</th>
                <th>Credits</th>
                <th>Semester</th>
            </tr>

            <c:forEach var="c" items="${allCourses}">
                <tr>
                    <td>${c.courseId}</td>
                    <td>${c.courseCode}</td>
                    <td>${c.courseName}</td>
                    <td>${c.credits}</td>
                    <td>${c.semester}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>