package com.foodie.delivery.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.foodie.delivery.service.implementation.JwtServiceImpl;
import com.foodie.delivery.service.implementation.UserRegistrationServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	ApplicationContext context;

    @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException{
        System.out.println("Authorization header: " + request.getHeader("Authorization"));
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
            System.out.println("Extracted token: " + token);
			username = jwtService.extractUserName(token);
            System.out.println("Extracted username: " + username);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = context.getBean(UserRegistrationServiceImpl.class).loadUserByUsername(username);
            System.out.println("UserDetails: " + userDetails);
            boolean valid = jwtService.validateToken(token, userDetails);
            System.out.println("Token valid? " + valid);
			 if(jwtService.validateToken(token, userDetails)) {
				 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 SecurityContextHolder.getContext().setAuthentication(authToken);
			 }
		}
		
		filterChain.doFilter(request, response);
		
	}
	

	
}
