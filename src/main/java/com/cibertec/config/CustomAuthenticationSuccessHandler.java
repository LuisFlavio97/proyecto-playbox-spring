package com.cibertec.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.debug("Authentication successful for user: {}", authentication.getName());
        logger.debug("Authorities: {}", authentication.getAuthorities());

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        try {
            if (roles.contains("ROLE_Administrador")) {
                logger.debug("Redirecting to /home for ROLE_Administrador");
                response.sendRedirect("/home");
            } else if (roles.contains("ROLE_Proveedor") || roles.contains("ROLE_Cliente") || roles.contains("ROLE_User")) {
                logger.debug("Redirecting to /catalogo for ROLE_Proveedor, ROLE_Cliente, or ROLE_User");
                response.sendRedirect("/catalogo");
            } else {
                logger.warn("No valid role found for user: {}. Roles: {}", authentication.getName(), roles);
                response.sendRedirect("/auth/login?error=Rol no válido");
            }
        } catch (Exception e) {
            logger.error("Error in CustomAuthenticationSuccessHandler: {}", e.getMessage(), e);
            response.sendRedirect("/auth/login?error=Error en manejador de autenticación");
        }
    }
}
