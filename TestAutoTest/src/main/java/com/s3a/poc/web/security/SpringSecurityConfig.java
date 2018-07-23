package com.s3a.poc.web.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.s3a.poc.web.security.CustomAuthenticationProvider;
import com.s3a.poc.web.security.CustomFilter;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Override
	public void configure(WebSecurity web) throws Exception {

	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            //http://localhost:8080/vendors/bootstrap/dist/css/bootstrap.min.css
                .antMatchers("/", "/home","/index.html","/h2-console/**","/swagger-ui/**","/build/**","/vendors/**","i18n/**","/admin/**").permitAll()
//                .anyRequest().authenticated()
                  .and()
        		  .exceptionHandling().accessDeniedPage("/index.html#/start")
        		  .and()
//        		  .addFilterBefore(new CustomFilter("/", "/?error"), BasicAuthenticationFilter.class)
        			.csrf().disable()
//                    .authorizeRequests().anyRequest().authenticated().and()
                    .formLogin() 
//        		  .and()
//            .formLogin()
        	          .loginPage("/")
//        	          .defaultSuccessUrl("/sites")
        	          .failureUrl("/?error")
//        	          .successHandler(yourSuccessHandlerBean) // autowired or defined below
//                .loginPage("/")
        		.loginProcessingUrl("/j_spring_security_check")//
        		.usernameParameter("j_username")//
        		.passwordParameter("j_password")//
                .permitAll()
                .and()
                
            .logout()
    		    .logoutUrl("/logout").logoutSuccessUrl("/main.html#/start")
                .permitAll();
        
        

		http.headers().httpStrictTransportSecurity();
		http.headers().xssProtection();
		http.headers().contentTypeOptions();

		http.headers().frameOptions().disable();
        
//		http
//		.headers()
//		.contentSecurityPolicy(
//		"   default-src 'self'	*.facebook.com *.google.com *.twitter.com www.google-analytics.com www.gstatic.com ; "
//		+ "	script-src	'self' 'unsafe-eval' 'unsafe-inline' https://www.google-analytics.com ajax.googleapis.com *.google.com www.gstatic.com https://www.google.com/recaptcha/ https://www.gstatic.com/recaptcha/;"
//		+ "	style-src	'self' 'unsafe-eval' 'unsafe-inline' fonts.googleapis.com www.google-analytics.com www.gstatic.com ;"
//		+ 
//"frame-src https://www.google.com/recaptcha/ ;"
//		+ "img-src	'self' 'unsafe-eval' 'unsafe-inline'  *.google.com www.gstatic.com https://www.google-analytics.com data:;"
//		);
		



    	http.addFilterAfter(new CustomFilter("/", "/?error"), BasicAuthenticationFilter.class);
		
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//             User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		AuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
		return authenticationProvider;
	}
}