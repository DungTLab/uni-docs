package util;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "AccessLoggingFilter")
public class AccessLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();
        String completeRequestURL = httpRequest.getQueryString() == null ? requestURI : requestURI + "?" + httpRequest.getQueryString();
        String remoteAddr = httpRequest.getRemoteAddr();
        String method = httpRequest.getMethod();
        String sessionId = httpRequest.getSession().getId();
        String userAgent = httpRequest.getHeader("User-Agent");

        LoggerUtil.log(String.format("UA: %s; SESSIONID: %s, IP: %s; Request: %s %s",
                userAgent,
                sessionId,
                remoteAddr,
                method,
                completeRequestURL)
        );

        chain.doFilter(request, response);
    }

    private String displayRequestParams(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        request.getParameterMap().forEach((key, values) -> {
            params.append(key).append("=");
            for (String value : values) {
                params.append(value).append(",");
            }
            params.deleteCharAt(params.length() - 1); // Remove last comma
            params.append("&");
        });
        return "?" + params;
    }

}
