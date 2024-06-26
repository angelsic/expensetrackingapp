package com.project.expensetrackingapp.exception.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.expensetrackingapp.repository.entity.ErrResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Manage exception for Denied Access
 * @author Angel Sic
 */
@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ErrResponse err = new ErrResponse("08", "Acceso Denegado");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), err);
    }
}
