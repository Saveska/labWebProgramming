package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.exceptions.CourseDoesntExistException;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Comparator;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final GradeService gradeService;

    public CourseController(CourseService courseService, TeacherService teacherService,GradeService gradeService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.gradeService = gradeService;
    }

// ////////////////////////////////////////////////////////////////////////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error,@RequestParam(required = false) Long teacherId, Model model){
        model.addAttribute("courses", courseService.listAll().stream().sorted(Comparator.comparing(c->c.getName().toLowerCase())).toList());
        model.addAttribute("teachers",teacherService.findAll());
        if(teacherId != null) {
            model.addAttribute("selectedTeacher", teacherId);
        }
        if(error != null){
            model.addAttribute("error",error);
        }
        return "listCourses";
    }

// ////////////////////////////////////////////////////////////////////////////////////////////
// /////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCourse(@RequestParam(required = false) Long courseId,
                             @RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long teacherId){

        try{
            if(courseId != null){
                courseService.editCourse(courseId,name,description,teacherId);
            }else {

                courseService.addNewCourse(name,description,teacherId);
            }
        }catch (Exception e){
            return "redirect:/courses?error="+e.getMessage();
        }
        return "redirect:/courses";
    }


    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditCoursePage(@PathVariable Long id, Model model){
        try{
            if(courseService.getCourseFromId(id).isEmpty())
                throw new CourseDoesntExistException();
            model.addAttribute("course",courseService.getCourseFromId(id).get());
        }catch (Exception e){
            return "redirect:/courses?error="+e.getMessage();
        }
        model.addAttribute("teachers",teacherService.findAll());
        return "add-course";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddCoursePage(Model model){
        model.addAttribute("teachers",teacherService.findAll());
        return "add-course";
    }


    @PostMapping
    public String postSelectedCourse(@RequestParam Long courseId, HttpServletRequest request) {
        request.getSession().setAttribute("courseId", courseId);
        return "redirect:/AddStudent";
    }


    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    @PostMapping("/add-grade")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addGrade(@RequestParam String selectedStudent, @RequestParam Long selectedCourse,@RequestParam Character selectedGrade, @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateGrade){
        if(dateGrade == null){
            dateGrade = LocalDateTime.now();

        }
        courseService.addGrade(selectedGrade,dateGrade,selectedCourse,selectedStudent);
        return "redirect:/StudentEnrollmentSummary";
    }

    @GetMapping("/grades")
    public String getGradePage( @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,

                                Model model){


        if(from == null && to == null){
            model.addAttribute("grades", gradeService.showAllGrades());
        }else if(from == null){
            model.addAttribute("grades",gradeService.showAllGradesBefore(to));
        }else if(to == null){
            model.addAttribute("grades",gradeService.showGradesBetween(from,LocalDateTime.now()));
        }else{
            model.addAttribute("grades",gradeService.showGradesBetween(from,to));
        }

        return "listGrades";
    }


}
