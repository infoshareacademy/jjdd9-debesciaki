package com.infoshareacademy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "SuperAdminAuthorizationFilter",
        urlPatterns = {"/upload-rest", "/upload-pla", "/upload-org", "/upload-cat", "/upload-eve" , "/stat"}
)
public class SuperAdminAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpSession session = httpServletRequest.getSession(false);

        boolean isAdmin = session != null && String.valueOf(session.getAttribute("role"))
                .matches("ADMIN|SUPER_ADMIN");
        if (!isAdmin) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendError(403);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
