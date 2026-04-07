<%-- 
    Document   : courseInfo
    Created on : 07/04/2026, 11:48:06
    Author     : jdgal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Information</title>
    </head>
    <body>
        <h1>Course Information</h1>

        <form action="./CourseServlet" method="POST">
            <table>
                <tr>
                    <td>Course ID</td>
                    <td>
                        <input type="text" name="courseId" value="${course.courseId}" />
                    </td>
                </tr>

                <tr>
                    <td>Course Code</td>
                    <td>
                        <input type="text" name="courseCode" value="${course.courseCode}" />
                    </td>
                </tr>

                <tr>
                    <td>Course Name</td>
                    <td>
                        <input type="text" name="courseName" value="${course.courseName}" />
                    </td>
                </tr>

                <tr>
                    <td>Credits</td>
                    <td>
                        <input type="text" name="credits" value="${course.credits}" />
                    </td>
                </tr>

                <tr>
                    <td>Semester</td>
                    <td>
                        <input type="text" name="semester" value="${course.semester}" />
                    </td>
                </tr>

                <tr>
                    <td>Max Students</td>
                    <td>
                        <input type="text" name="maxStudents" value="${course.maxStudents}" />
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Add" />
                        <input type="submit" name="action" value="Edit" />
                        <input type="submit" name="action" value="Delete" />
                        <input type="submit" name="action" value="Search" />
                    </td>
                </tr>
            </table>
        </form>

        <br>

        <table border="1">
            <th>ID</th>
            <th>Code</th>
            <th>Name</th>
            <th>Credits</th>
            <th>Semester</th>
            <th>Max Students</th>

            <c:forEach var="c" items="${allCourses}">
                <tr>
                    <td>${c.courseId}</td>
                    <td>${c.courseCode}</td>
                    <td>${c.courseName}</td>
                    <td>${c.credits}</td>
                    <td>${c.semester}</td>
                    <td>${c.maxStudents}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>