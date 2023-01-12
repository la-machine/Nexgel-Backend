package com.example.nexgel.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.nexgel.model.User;
import com.example.nexgel.Service.UserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService userService;
    @Autowired
	private JWTTokenHelper jWTTokenHelper;
    @Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

    private final BCryptPasswordEncoder passwordEncoder;

    

    @Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http
        //     .csrf().disable()
        //     .authorizeRequests()
        //         .antMatchers("/api/**")
        //         .permitAll()
        //     .anyRequest()
        //     .authenticated().and()
        //     .formLogin();

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint).and()
				.authorizeRequests((request) -> request.antMatchers("/api/**").permitAll()
						.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated());

		http.csrf().disable().cors().and().headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());    
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
         
        return authProvider;
    }
    
}
