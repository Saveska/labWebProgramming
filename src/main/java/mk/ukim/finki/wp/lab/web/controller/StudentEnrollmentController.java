package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/StudentEnrollmentSummary")
public class StudentEnrollmentController {

    private final CourseService courseService;

    public StudentEnrollmentController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getStudentEnrollmentPage(@SessionAttribute(required = false) Long courseId, Model model){
        model.addAttribute("students",courseService.listStudentsByCourse(courseId));
        model.addAttribute("course",courseService.getCourseFromId(courseId));
        model.addAttribute("grades",courseService.getGradesForCourse(courseId));
        return "studentsInCourse";
    }

    @PostMapping
    public String postStudentEnrollment(@RequestParam String username, @SessionAttribute Long courseId, HttpServletRequest request, Model model) {
        try {
            courseService.addStudentInCourse(username, courseId);
            request.getSession().setAttribute("currentStudent",username);
            return "redirect:/StudentEnrollmentSummary";
        } catch (Exception e) {
            return "redirect:/courses?error=" + e.getMessage();

        }


    }
}
