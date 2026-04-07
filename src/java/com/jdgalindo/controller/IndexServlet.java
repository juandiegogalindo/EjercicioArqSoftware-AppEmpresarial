/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdgalindo.controller;

import com.jdgalindo.dao.StudentDaoLocal;
import com.jdgalindo.dao.CourseDao;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "IndexServlet", urlPatterns = {"/IndexServlet"})
public class IndexServlet extends HttpServlet {

    @EJB
    private StudentDaoLocal studentDao;

    @EJB
    private CourseDao courseDao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔥 Cargar datos
        request.setAttribute("allStudents", studentDao.getAllStudents());
        request.setAttribute("allCourses", courseDao.getAllCourses());

        // 🔥 Redirigir al dashboard
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
}