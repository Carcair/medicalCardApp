package com.emird.medicalCardApp.config;

import com.emird.medicalCardApp.filters.JwtRequestFilter;
import com.emird.medicalCardApp.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
			We are enabling access without authentication
			for authenticate endpoint
			and all swagger documentation endpoints
		*/
		http.csrf().disable()
			.authorizeRequests().antMatchers(HttpMethod.POST,"/api/*/authenticate").permitAll()
			.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
			.antMatchers(HttpMethod.GET, "/configuration/ui").permitAll()
			.antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
			.antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
			.anyRequest().authenticated()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		/*
			Before accessing protected routes
			we are initiating JWT filter as authentication filter
		 */
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		// I used no encoder because our flyway migration already inserts some default users, with not encoded passwords
		// Production application should have some encoder
		return NoOpPasswordEncoder.getInstance();
	}
}
