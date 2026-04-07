/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdgalindo.controller;

import com.jdgalindo.dao.CourseDao;
import com.jdgalindo.model.Course;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "CourseServlet", urlPatterns = {"/CourseServlet"})
public class CourseServlet extends HttpServlet {

    @EJB
    private CourseDao courseDao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String courseIdStr = request.getParameter("courseId");

        int courseId = 0;
        if (courseIdStr != null && !courseIdStr.isEmpty()) {
            courseId = Integer.parseInt(courseIdStr);
        }

        String code = request.getParameter("courseCode");
        String name = request.getParameter("courseName");
        String creditsStr = request.getParameter("credits");
        String semester = request.getParameter("semester");
        String maxStr = request.getParameter("maxStudents");

        int credits = (creditsStr != null && !creditsStr.isEmpty()) ? Integer.parseInt(creditsStr) : 0;
        int max = (maxStr != null && !maxStr.isEmpty()) ? Integer.parseInt(maxStr) : 0;

        Course course = new Course();

        if ("Add".equalsIgnoreCase(action)) {
            course.setCourseCode(code);
            course.setCourseName(name);
            course.setCredits(credits);
            course.setSemester(semester);
            course.setMaxStudents(max);

            courseDao.addCourse(course);

        } else if ("Edit".equalsIgnoreCase(action)) {

            course = courseDao.getCourse(courseId); // 🔥 TRAER EL REAL

            if (course != null) {
                course.setCourseCode(code);
                course.setCourseName(name);
                course.setCredits(credits);
                course.setSemester(semester);
                course.setMaxStudents(max);

                courseDao.editCourse(course);
            }
        } else if ("Delete".equalsIgnoreCase(action)) {
            courseDao.deleteCourse(courseId);

        } else if ("Search".equalsIgnoreCase(action)) {
            course = courseDao.getCourse(courseId);

            if (course == null) {
                course = new Course(); // evita null en JSP
            }
        }

        // 🔥 MUY IMPORTANTE
        request.setAttribute("course", course);
        request.setAttribute("allCourses", courseDao.getAllCourses());

        request.getRequestDispatcher("courseInfo.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }
}
