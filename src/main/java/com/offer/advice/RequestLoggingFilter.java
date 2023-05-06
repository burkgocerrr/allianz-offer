package com.offer.advice;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final int MAX_REQUEST_TIME_MS = 5;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        long startTime = System.currentTimeMillis();

        chain.doFilter(request, response);

        long endTime = System.currentTimeMillis();

        long requestTime = endTime - startTime;

        if (requestTime > MAX_REQUEST_TIME_MS) {
            logger.warn("Slow request: {} {} ({} ms)", httpRequest.getMethod(), httpRequest.getRequestURI(), requestTime);
        }
    }
}
