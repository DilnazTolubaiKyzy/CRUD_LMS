package com.peaksoft.dao;

import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Student;

import java.util.List;

public interface CompanyDao {

    List<Company> getAllCompany();
    void addCompany(Company company);
    void updateCompany(long id,Company company);
    Company getById(long id);
    void deleteCompany(Company company);
    List<Course>getCoursesById(long id);

    List<Student>getStudentsById(Long id);
}
