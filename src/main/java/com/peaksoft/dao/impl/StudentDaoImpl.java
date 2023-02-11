package com.peaksoft.dao.impl;

import com.peaksoft.dao.StudentDao;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudent() {
        List<Student> students = entityManager.createQuery("from Student ").getResultList();
        return students;
    }

    @Override
    public void addStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    public void updateStudent(long id, Student student ) {
        Student student1 = entityManager.find(Student.class,id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }

    @Override
    public Student getById(long id) {
        Student student = entityManager.find(Student.class,id);
        return student;
    }

    @Override
    public void deleteStudent(Student student) {
        entityManager.remove(entityManager.contains(student) ? student : entityManager.merge(student));
    }

    @Override
    public List<Group> getGroupById(Long id) {
        List<Group>groups = entityManager.createQuery(" select g from Group  g join g.students stu where stu.id=?1")
                .setParameter(1,id).getResultList();
        return groups;
    }

}
