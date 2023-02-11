package com.peaksoft.controller;

import com.peaksoft.entity.Course;
import com.peaksoft.entity.Student;
import com.peaksoft.entity.Teacher;
import com.peaksoft.service.CourseService;
import com.peaksoft.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("teachers")
public class TeacherController {
    private final TeacherService teacherService;
    private final CourseService courseService;
    @Autowired
    public TeacherController(TeacherService teacherService,CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }
    @GetMapping
    public String teachers(Model model){
        model.addAttribute("teachers",teacherService.getAllTeacher());
        return "views/teacher/teachers";
    }

    @GetMapping("/addTeacher")
    public String add(Model model){
        model.addAttribute("teacher",new Teacher());
        model.addAttribute("courses",courseService.getAllCourse());
        return "views/teacher/addTeacher";
    }

    @PostMapping("/saveTeacher")
    public String save(@ModelAttribute("company") Teacher teacher){
        teacherService.addTeacher(teacher, teacher.getCourseId());
        return "redirect:/teachers";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses",courses);
        Teacher teacher = teacherService.getById(id);
        model.addAttribute("teacher" ,teacher );
        return "views/teacher/updateTeacher";
    }

    @PatchMapping("/{id}")
    public String updateTeacher(@PathVariable("id") Long id,@ModelAttribute("teacher") Teacher teacher){
        teacherService.updateTeacher(id,teacher, teacher.getCourseId());
        return "redirect:/teachers";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Teacher teacher = teacherService.getById(id);
        teacherService.deleteTeacher(teacher);
        return "redirect:/teachers";
    }
    @GetMapping("/students/{id}")
    public String getStudents(@PathVariable("id") Long id, Model model){
        List<Student> students = teacherService.getStudentsById(id);
        model.addAttribute("students",students);
        return "views/teacher/students";
    }
    @GetMapping("/hello")
    public String hello(){
        return "views/hello";
    }

}
