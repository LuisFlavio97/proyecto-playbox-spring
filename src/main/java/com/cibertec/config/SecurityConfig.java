package com.cibertec.config;

import com.cibertec.models.service.DetalleUsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;
    private final DetalleUsuarioService detalleUsuarioService;

    // Constructor para inyectar los servicios necesarios
    public SecurityConfig(DetalleUsuarioService detalleUsuarioService, CustomAuthenticationSuccessHandler successHandler) {
        this.detalleUsuarioService = detalleUsuarioService;
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/static/**", "/css/**", "/js/**","/images/**").permitAll()
                            .requestMatchers("/auth/**").permitAll()
                            // Proteger explícitamente la ruta de videojuegos
                            .requestMatchers("/home","/views/videojuegos/**","/views/usuarios/**","/transaccion/**","/reportes/**").hasRole("Administrador")
                            .requestMatchers("/catalogo/**").hasAnyRole("Proveedor", "Cliente", "User")
                            .anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage("/auth/login")
                            .successHandler(successHandler)
                            .usernameParameter("nomusuario")
                            .passwordParameter("password")
                            .failureUrl("/auth/login?error=Credenciales incorrectas")
                            .permitAll();
                })
                .logout(logout -> {
                    logout.logoutUrl("/auth/logout")
                            .logoutSuccessUrl("/auth/login?logout=Sesión cerrada exitosamente")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                            .clearAuthentication(true)
                            .addLogoutHandler((request, response, authentication) -> {
                                SessionRegistry sessionRegistryBean = sessionRegistry();
                                sessionRegistryBean.removeSessionInformation(request.getSession().getId());
                            })
                            .permitAll();
                })
                .sessionManagement(session -> {
                    session
                            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                            .maximumSessions(1)
                            .maxSessionsPreventsLogin(false) // Invalida la sesión anterior
                            .expiredUrl("/auth/login?error=Sesión expirada")
                            .sessionRegistry(sessionRegistry());
                    session.invalidSessionUrl("/auth/login?error=Sesión inválida");
                    session.sessionFixation().migrateSession();
                })
                .exceptionHandling(exceptions -> {
                    exceptions
                            .accessDeniedHandler((request, response, accessDeniedException) -> {
                                response.sendRedirect("/error/access-denied");
                            })
                            .authenticationEntryPoint((request, response, authException) -> {
                                response.sendRedirect("/auth/login?authError=true");
                            });
                })
                .authenticationProvider(getAuthenticationProvider())
                .build();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(detalleUsuarioService); // Utiliza el servicio DetalleUsuarioService
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder()); // Utiliza BCryptPasswordEncoder para la codificación de contraseñas
        return daoAuthenticationProvider;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
