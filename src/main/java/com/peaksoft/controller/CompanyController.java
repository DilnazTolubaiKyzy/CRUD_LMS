package com.peaksoft.controller;

import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Student;
import com.peaksoft.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("companies")
public class CompanyController {

    private final CompanyService companyService;
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public String  companies(Model model){
        model.addAttribute("companies",companyService.getAllCompany());
        return "views/company/companies";
    }

    @GetMapping("/addCompany")
    public String add(Model model){
        model.addAttribute("company",new Company());
        return "views/company/addCompany";
    }

    @PostMapping("/saveCompany")
    public String save(@ModelAttribute("company") Company company){
        companyService.addCompany(company);
        return "redirect:/companies";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,Model model){
        Company company = companyService.getById(id);
        model.addAttribute("company",company);
        return "views/company/updateCompany";
    }

    @PatchMapping("/{id}")
    public String updateCompany(@PathVariable("id")Long  id,@ModelAttribute("company")Company company){
        companyService.updateCompany(id,company);
        return "redirect:/companies";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id ){
        Company company = companyService.getById(id);
        companyService.deleteCompany(company);
        return "redirect:/companies";
    }
    @GetMapping("/courses/{id}")
    public String getCourses(@PathVariable("id") Long id,Model model){
        List<Course> courses = companyService.getCoursesById(id);
        model.addAttribute("courses",courses);
        return "views/company/courses";
    }
    @GetMapping("/students/{id}")
    public String getStudents(@PathVariable("id") Long id, Model model){
        List<Student> students = companyService.getStudentsById(id);
        model.addAttribute("students",students);
        model.addAttribute("size",students.size());
        return "views/company/students";
    }
    @GetMapping("/hello")
    public String hello(){
        return "views/hello";
    }
}
