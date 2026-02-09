package com.muyi.studCRUD.controller;

import com.muyi.studCRUD.entity.Student;
import com.muyi.studCRUD.repository.StudentRepository;
import com.muyi.studCRUD.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    private final StudentService studentService;
    public HomeController(StudentService studentService) {
        this.studentService = studentService;
    }



    @GetMapping("/")
    public String home(@RequestParam( value = "name", defaultValue = "")String name,
                       Model model) {

        List<Student> studentList =studentService.getAllStudents();
        model.addAttribute("studentList",studentList);

        return "home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Student student = new Student();
        model.addAttribute("student",student);
        return "create";
    }

    @PostMapping("/save")
    public String save(@Valid  @ModelAttribute("student") Student student,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message", "Student has been saved successfully");
        return "redirect:/home";
    }

//Edit Student data
    @GetMapping("student/{id}/edit")
    public String edit(@PathVariable long id, Model model){
        Student student = studentService.findStudentById(id).orElse(null);

        model.addAttribute("student",student);
        return "create";
    }

//    Delete Student
@GetMapping("student/{id}/delete")
public String delete(@PathVariable long id, RedirectAttributes redirectAttributes){
    studentService.deleteById(id);

    redirectAttributes.addFlashAttribute("message", "Student has been deleted successfully");
    return "redirect:/";
}

//Show method to view customer
    @GetMapping("student/{id}/show")
    public String show(@PathVariable long id, Model model){
        studentService.findStudentById(id).ifPresent(student -> model.addAttribute("student",student));

        return "show";
    }



    // This MUST match the th:href="@{/home}"
    @GetMapping("/home")
    public String displayDashboard(Model model) {
        model.addAttribute("studentList", studentService.getAllStudents());
        return "home"; // This MUST match your file name home.html
    }
}
