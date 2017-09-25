package com.aspone.brokerwebapp.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
	public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
			// @formatter:off
			auth.inMemoryAuthentication()
					.withUser("broker").password("brokerpass").roles("ADMIN")
					.and()
					.withUser("trader").password("traderpass").roles("USER");
			// @formatter:on
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.httpBasic().and()
					.logout().and()
					.authorizeRequests()
					.antMatchers("/index.html", "/").permitAll()
					.anyRequest().authenticated()
					.and()
					.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		}
	}