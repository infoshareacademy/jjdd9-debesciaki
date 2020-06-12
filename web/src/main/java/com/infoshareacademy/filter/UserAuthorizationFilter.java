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

        boolean isUSer = session != null && String.valueOf(session.getAttribute("role"))
                .matches("USER|ADMIN|SUPER_ADMIN");
        if (!isUSer) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect("https://images.squarespace-cdn.com/content/5521673ce4b09eb81bf68349/1448711636375-N0M8RIC90IEP439GGM9I/image-asset.jpeg?format=1000w&content-type=image%2Fjpeg");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
