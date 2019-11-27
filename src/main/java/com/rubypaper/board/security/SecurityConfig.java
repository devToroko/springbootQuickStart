package com.rubypaper.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.userDetailsService(userDetailsService);
		
		security.authorizeRequests().antMatchers("/","/system/**").permitAll()
		.antMatchers("/board/**").authenticated()
		.antMatchers("/admin/**").hasRole("ADMIN");
		
		security.csrf().disable();
		security.formLogin().loginPage("/system/login").defaultSuccessUrl("/board/getBoardList",true);
		security.exceptionHandling().accessDeniedPage("/system/accessDenied");
		security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
//	@Bean
//	public SpringSecurityDialect securityDialect() {
//	    return new SpringSecurityDialect();
//	}
	/*
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {

		String query1 = "select id username, concat('{noop}',password) password, true enabled from member where id=?";
		String query2 = "select id, role from member where id=?";
		
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query1).authoritiesByUsernameQuery(query2);
	}
	*/
	
}
