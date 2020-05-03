package se.rsv.arende.arendeinformationspring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.springframework.security.core.userdetails.User.builder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	
	/*
	 * Här skapar man med hjälp av encoder nya användare, deras lösenord och deras roll.
	 * Det finns 2 olika roller admin och user.
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
			builder()
				.passwordEncoder(input -> passwordEncoder().encode(input))
				.username("user")
				.password("123")
				.roles("USER")
				.build(),
			builder()
				.passwordEncoder(input -> passwordEncoder().encode(input))
				.username("admin")
				.password("password")
				.roles("USER", "ADMIN")
				.build()
		);
	}
	

	/*
	 * Om man fyller i fel lösenord eller användarnamn.
	 * Då ska man skickas tillbaka till index-sidan.
	 * 
	 * Man kontrollerar de infyllda datan mot det som är det rätta.
	 */

    protected void configure(final HttpSecurity http) throws Exception {
        AuthenticationFailureHandler authenticationFailureHandler;
        authenticationFailureHandler = new AuthenticationFailureHandler() {
			
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				// TODO Auto-generated method stub
				System.out.print(exception);
				System.out.print(response);
				System.out.print(request);

				
			}
		};
		http
                .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/register")
                    .usernameParameter("username").passwordParameter("password")
                    .failureHandler(authenticationFailureHandler)
                    .failureForwardUrl("/login-error")
                    .permitAll()
                    .and()
                .logout().permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/index")
            .and()
                .authorizeRequests()
                .antMatchers("/register").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/shared/**").hasAnyRole("USER","ADMIN");
        //    .and()
        //        .exceptionHandling();
         //       .accessDeniedPage("/403.html");

    }



}
