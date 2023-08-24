package com.atipera.githubrepotioryfetchingapi;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HeadersResponseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getHeader("Accept").equals("application/xml")) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Unsupported 'Accept' header: 'application/xml'. Must accept 'application/json'.");
            return;
        }
        filterChain.doFilter(request, response);

    }

}
