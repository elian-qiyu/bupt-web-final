package cn.edu.bupt.springmvc.demo;


import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//public class LoginInterceptor implements HandlerInterceptor{
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        if (request.getRequestURI().contains("login")) return true;
//        if (session.getAttribute("user") != null) return true;
//        response.sendRedirect("/login");
//        return false;
//    }
//}
