/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdgalindo.controller;

import com.jdgalindo.dao.CourseDao;
import com.jdgalindo.dao.StudentDaoLocal;
import com.jdgalindo.model.Course;
import com.jdgalindo.model.Student;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "StudentServlet", urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet {

    @EJB
    private StudentDaoLocal studentDao;

    @EJB
    private CourseDao courseDao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String studentIdStr = request.getParameter("studentId");

        int studentId = 0;
        if (studentIdStr != null && !studentIdStr.isEmpty()) {
            studentId = Integer.parseInt(studentIdStr);
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String yearLevelStr = request.getParameter("yearLevel");

        int yearLevel = 0;
        if (yearLevelStr != null && !yearLevelStr.isEmpty()) {
            yearLevel = Integer.parseInt(yearLevelStr);
        }

        // 🔥 cursos seleccionados (CLAVE)
        String[] courseIds = request.getParameterValues("courses");

        Student student = new Student();

        if ("Add".equalsIgnoreCase(action)) {

            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setYearLevel(yearLevel);

            // 🔥 asignar cursos ANTES de guardar
            if (courseIds != null) {
                for (String id : courseIds) {
                    Course course = courseDao.getCourse(Integer.parseInt(id));
                    student.getCourses().add(course);

                    // relación bidireccional (PRO)
                    course.getStudents().add(student);
                }
            }

            studentDao.addStudent(student);

        } else if ("Edit".equalsIgnoreCase(action)) {

            Student existingStudent = studentDao.getStudent(studentId);

            if (existingStudent != null) {

                existingStudent.setFirstName(firstName);
                existingStudent.setLastName(lastName);
                existingStudent.setYearLevel(yearLevel);

                // 🔥 limpiar cursos anteriores
                existingStudent.getCourses().clear();

                // 🔥 asignar nuevos cursos
                if (courseIds != null) {
                    for (String id : courseIds) {
                        Course course = courseDao.getCourse(Integer.parseInt(id));
                        existingStudent.getCourses().add(course);

                        // relación bidireccional (opcional pero pro)
                        course.getStudents().add(existingStudent);
                    }
                }

                studentDao.editStudent(existingStudent);
                student = existingStudent;
            }

        } else if ("Delete".equalsIgnoreCase(action)) {

            studentDao.deleteStudent(studentId);

        } else if ("Search".equalsIgnoreCase(action)) {

            student = studentDao.getStudent(studentId);

            if (student == null) {
                student = new Student(); // evita errores en JSP
            }
        }

        // 🔥 enviar datos a la vista
        request.setAttribute("student", student);
        request.setAttribute("allStudents", studentDao.getAllStudents());
        request.setAttribute("allCourses", courseDao.getAllCourses());

        request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Student Servlet with ManyToMany relationship";
    }
}