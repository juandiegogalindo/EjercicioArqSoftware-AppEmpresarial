<%-- 
    Document   : studentInfo
    Created on : 06/04/2026, 12:51:06
    Author     : jdgal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.jdgalindo.model.Student"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Student Information</title>
    </head>
    <body>

```
    <h1>Student Information</h1>
    <form action="./StudentServlet" method="POST">
        <table>

            <tr>
                <td>Student ID</td>
                <td>
                    <input type="text" name="studentId" value="${student.studentId}" />
                </td>
            </tr>

            <tr>
                <td>First Name</td>
                <td>
                    <input type="text" name="firstName" value="${student.firstName}" />
                </td>
            </tr>

            <tr>
                <td>Last Name</td>
                <td>
                    <input type="text" name="lastName" value="${student.lastName}" />
                </td>
            </tr>

            <tr>
                <td>Year Level</td>
                <td>
                    <input type="text" name="yearLevel" value="${student.yearLevel}" />
                </td>
            </tr>

            <!-- 🔥 MANY TO MANY -->
            <tr>
                <td>Courses</td>
                <td>
                    <c:forEach var="c" items="${allCourses}">
                        
                        <c:set var="isChecked" value="false" />

                        <c:forEach var="sc" items="${student.courses}">
                            <c:if test="${sc.courseId == c.courseId}">
                                <c:set var="isChecked" value="true" />
                            </c:if>
                        </c:forEach>

                        <input type="checkbox" name="courses" value="${c.courseId}"
                            <c:if test="${isChecked}">checked</c:if>
                        />
                        ${c.courseName}
                        <br/>
                    </c:forEach>
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

    <!-- 🔥 TABLA DE ESTUDIANTES -->
    <table border="1">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Year Level</th>
            <th>Courses</th>
        </tr>

        <c:forEach var="stud" items="${allStudents}">
            <tr>
                <td>${stud.studentId}</td>
                <td>${stud.firstName}</td>
                <td>${stud.lastName}</td>
                <td>${stud.yearLevel}</td>

                <td>
                    <c:forEach var="c" items="${stud.courses}">
                        ${c.courseName}<br/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
```

</html>
