package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="student_enrollment",urlPatterns = "/servletStudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override //go dodava studentot od createStudent serveltot i gi lista site dodadeni studenti vo toj kurs
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        WebContext context = new WebContext(req,resp, req.getServletContext());
        context.setVariable("students", courseService.listStudentsByCourse((long) req.getSession().getAttribute("courseId")));
        context.setVariable("course", courseService.courseNameFromId((long) req.getSession().getAttribute("courseId")));
        springTemplateEngine.process("studentsInCourse.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        courseService.addStudentInCourse(req.getParameter("username"),(long) req.getSession().getAttribute("courseId"));
        resp.sendRedirect("/StudentEnrollmentSummary");
    }
}
