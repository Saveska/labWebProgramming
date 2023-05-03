package mk.ukim.finki.wp.lab.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class CourseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getServletPath();

//        if ((!path.contains("/courses") && req.getSession().getAttribute("courseId") == null) || path.equalsIgnoreCase("/listcourses"))
//            resp.sendRedirect("/courses");
//        else{
//            filterChain.doFilter(servletRequest, servletResponse);
//        }

        if (((!(path.contains("/courses") || path.contains("/login") || path.contains("/logout")))  && req.getSession().getAttribute("courseId") == null)|| path.equalsIgnoreCase("/listcourses"))
            resp.sendRedirect("/courses");
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
