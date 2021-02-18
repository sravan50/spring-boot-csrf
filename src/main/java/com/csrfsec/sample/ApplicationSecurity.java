package com.csrfsec.sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//.antMatchers(HttpMethod.POST,"/login").permitAll()
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.ignoringAntMatchers("/login")
		.and().authorizeRequests()
				.anyRequest().authenticated().and().httpBasic();

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).roles("ADMIN", "USER").and()
				.withUser("user").password(encoder.encode("user")).roles("USER");
	}

}