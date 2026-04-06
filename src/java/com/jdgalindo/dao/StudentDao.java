/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdgalindo.dao;

import com.jdgalindo.model.Student;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jdgal
 */
@Stateless
public class StudentDao implements StudentDaoLocal {

    @PersistenceContext(unitName = "EjercicioArqSoftware-AppEmpresarialCRUDPU")
    private EntityManager em;

    @Override
    public void addStudent(Student student) {
        em.persist(student);
    }

    @Override
    public void editStudent(Student studentId) {
        em.merge(studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
        Student s = em.find(Student.class, studentId);
        if (s != null) {
            em.remove(s);
        }
    }

    @Override
    public Student getStudent(int studentId) {
        return em.find(Student.class, studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return em.createNamedQuery("Student.getAll", Student.class).getResultList();
    }
    
}
