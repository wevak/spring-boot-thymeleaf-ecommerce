package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.CustomSuccessHandler;
import com.example.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Disable CSRF
            .csrf(csrf -> csrf.disable())
            
            // 2. Authorize requests
//            .authorizeHttpRequests(auth -> auth
//                .anyRequest().authenticated()
//            )
            .authorizeHttpRequests(request->request.requestMatchers("/admin/**")
    				.hasAuthority("ADMIN").requestMatchers("/cart").hasAuthority("USER")
    				.requestMatchers("/","/home","/shop/**","/register", "/register/user","/css/**","/images/**","/productImages/**", "/user/**").permitAll()
    				.anyRequest().authenticated())
    		.formLogin(form->form.loginPage("/login").loginProcessingUrl("/login")
    				.usernameParameter("email")
//    				.defaultSuccessUrl("/admin/dashboard", true)
    				.successHandler(customSuccessHandler)	//had to comment this line for successful login
    				.permitAll())
    		.logout(form->form.invalidateHttpSession(true).clearAuthentication(true)
//    				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    				.logoutSuccessUrl("/login?logout").permitAll());
            
            // 3. Enable Basic Auth (for Postman)
//            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
