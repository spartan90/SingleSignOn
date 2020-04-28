package com.singlesignon.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.singlesignon.utility.JWTUtility;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	@Autowired
	JWTUtility jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader("Authorization");
		LOGGER.debug("in Custome filter. token : {}", token);
		
		if(token != null) {
			try {
				UserDetails userDetails = jwtUtil.validateAndExtractUser(token);
				LOGGER.debug("userDetails built from claims : {}", userDetails);
				if(userDetails != null) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}catch(Exception e) {
				LOGGER.error("Error while parsing token : {}, Error : {}", token, e);
			}
		}
		filterChain.doFilter(request, response);
	}

}
