package com.emird.medicalCardApp.filters;

import com.emird.medicalCardApp.service.MyUserDetailsService;
import com.emird.medicalCardApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		FilterChain filterChain
	) throws ServletException, IOException {

		// If there is Authorization header, this variable will contain "Bearer <token>"
		final String authorizationHeader = httpServletRequest.getHeader("Authorization");

		String username = null;
		String jwt = null;

		// Checking if token was sent in appropriate form
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			// Token starts after 7th char
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);

			UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);

			if (jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities()
				);

				usernamePasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
