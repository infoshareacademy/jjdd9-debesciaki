package com.infoshareacademy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "UserAuthorizationFilter",
        urlPatterns = {"/show-favourites"}
)
public class UserAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpSession session = httpServletRequest.getSession(false);

        boolean isUser = session != null && String.valueOf(session.getAttribute("role"))
                .matches("USER|ADMIN|SUPER_ADMIN");
        if (!isUser) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendError(403);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
