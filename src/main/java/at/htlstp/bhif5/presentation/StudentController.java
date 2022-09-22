package at.htlstp.bhif5.presentation;

import at.htlstp.bhif5.domain.Student;
import at.htlstp.bhif5.persistence.ClassRepository;
import at.htlstp.bhif5.persistence.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("students")
public record StudentController(StudentRepository studentRepository, ClassRepository classRepository) {

    @GetMapping //route /students
    public String all(Model model) {
        model.addAttribute("classes", classRepository.findAll());
        return "classes";
    }

    @GetMapping("{class}")
    public String fromClass(@PathVariable(name = "class") String schoolClass, Model model) {
        model.addAttribute("students", studentRepository.findAllBySchoolClass_Name(schoolClass));
        model.addAttribute("class", schoolClass);
        return "students";
    }

    @GetMapping("new")
    public String getStudentEnrollmentForm(Model model, Student student) {
        if (student == null)
            model.addAttribute("new-student", new Student());
        model.addAttribute("classes", classRepository.findAll());
        return "students-new";
    }

    @PostMapping("new")
    public String handleStudentEnrollment(Model model, @Valid @ModelAttribute("new-student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return getStudentEnrollmentForm(model, student);
        studentRepository.save(student);
        return "redirect:/students";    //kein .html file, sondern eine route
    }
}
