package com.peaksoft.controller;

import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;
import com.peaksoft.service.CourseService;
import com.peaksoft.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("groups")
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;
    @Autowired
    public GroupController(GroupService groupService,CourseService courseService) {
        this.groupService = groupService;
        this.courseService = courseService;
    }

    @GetMapping
    public String groups(Model model){
        model.addAttribute("groups",groupService.getAllGroup());
        return "views/group/groups";
    }

    @GetMapping("/addGroup")
    public String add(Model model){
        model.addAttribute("group",new Group());
        model.addAttribute("courses",courseService.getAllCourse());
        return "views/group/addGroup";
    }

    @PostMapping("/saveGroup")
    public String save(@ModelAttribute("group") Group group){
        groupService.addGroup(group,group.getCourseId());
        return "redirect:/groups";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        Group group = groupService.getById(id);
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("group" ,group );
        model.addAttribute("courses",courses);
        return "views/group/updateGroup";
    }
    @PatchMapping("/{id}")
    public String updateGroup(@PathVariable("id") Long id,@ModelAttribute("group") Group group){
        groupService.updateGroup(id,group,group.getCourseId());
        return "redirect:/groups";
    }
    @GetMapping("/students/{id}")
    public String students(@PathVariable("id") Long id, Model model,@ModelAttribute("student")Student student){
        List<Student>students = groupService.getStudentsByGroupId(id);
        model.addAttribute("group",groupService.getById(id));
        model.addAttribute("students",students);
        return "views/group/students";
    }
    @GetMapping("/search/{id}")
    public String search(@PathVariable("id") Long id, Model model,@RequestParam("firstName")String firstName){
        List<Student>students = groupService.search(id,firstName);
        model.addAttribute("students",students);
        return "views/group/search";
    }
    @GetMapping("/courses/{id}")
    public String getCourses(@PathVariable("id") Long id,Model model){
        List<Course> courses = groupService.getCoursesById(id);
        model.addAttribute("courses" ,courses);
        return "views/group/courses";
    }



    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Group group = groupService.getById(id);
        groupService.deleteGroup(group);
        return "redirect:/groups";
    }

    @GetMapping("/hello")
    public String hello(){
        return "views/hello";
    }
}
