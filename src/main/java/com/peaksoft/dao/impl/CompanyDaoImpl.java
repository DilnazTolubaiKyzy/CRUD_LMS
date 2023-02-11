package com.peaksoft.dao.impl;

import com.peaksoft.dao.CompanyDao;
import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Company> getAllCompany() {
        List <Company> companies = entityManager.createQuery("from Company ").getResultList();
        return companies;
    }

    @Override
    public void addCompany(Company company) {
    entityManager.persist(company);
    }

    @Override
    public void updateCompany(long id, Company company) {
    Company company1 = entityManager.find(Company.class,id);
    company1.setCompanyName(company.getCompanyName());
    company1.setLocatedCountry(company.getLocatedCountry());
    company1.setCourses(company.getCourses());
    entityManager.merge(company1);
    }

    @Override
    public Company getById(long id) {
        Company company = entityManager.find(Company.class,id);
        return company;
    }

    @Override
    public void deleteCompany(Company company) {
    entityManager.remove(entityManager.contains(company) ? company : entityManager.merge(company));
    }

    @Override
    public List<Course> getCoursesById(long id) {
        List<Course>courses = entityManager.createQuery(" select c from Course c join c.company com where com.id=?1")
                .setParameter(1,id).getResultList();
        return courses;
    }

    @Override
    public List<Student> getStudentsById(Long id) {
        List<Student>students=entityManager.
                createQuery("select s from Student s join s.group g join g.courses c join c.company com where com.id=?1")
                .setParameter(1,id).getResultList();
        return students;
    }


}
