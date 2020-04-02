package com.sangdaero.walab;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.sangdaero.walab.user.application.service.UserService;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserService mUserService;
	
	public AppSecurityConfig(UserService userService) {
		mUserService = userService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*http
			.antMatcher("/**").authorizeRequests()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and()
			.oauth2Login();*/
		
		http
        .authorizeRequests(a -> a
        		.antMatchers("/","/error").permitAll()
        		.anyRequest().authenticated()
        )
        .logout(l -> l
        		.logoutSuccessUrl("/").permitAll()
        )
        .csrf(c -> c
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        )
        .oauth2Login()
        .userInfoEndpoint()
        .oidcUserService(mUserService);
		
	}

	
	
}
