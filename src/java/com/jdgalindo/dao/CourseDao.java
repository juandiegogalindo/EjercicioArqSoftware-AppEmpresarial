/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jdgalindo.dao;

import com.jdgalindo.model.Course;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jdgal
 */

@Stateless
public class CourseDao {

    @PersistenceContext(unitName = "EjercicioArqSoftware-AppEmpresarialCRUDPU")
    private EntityManager em;

    public void addCourse(Course course) {
        em.persist(course);
    }

    public List<Course> getAllCourses() {
        return em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }

    public Course getCourse(int id) {
        return em.find(Course.class, id);
    }
}