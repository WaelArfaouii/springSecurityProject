package com.arfaoui.springSecurityApp.config;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAutheticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService ;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
            ) throws ServletException, IOException {
             final String authHeader = request.getHeader("Authorization") ;
             final String jwt ;
             final String userEmail ;
             if (authHeader == null || !authHeader.startsWith("Bearer ")){
                 filterChain.doFilter(request,response);
                 return;
             }
             jwt = authHeader.substring(7) ;
             userEmail = //todo extract user email from jwt token


    }
}
