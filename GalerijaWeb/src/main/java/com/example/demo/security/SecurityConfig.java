package com.example.demo.security;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.services.KorisnikService;

import jakarta.servlet.http.HttpSession;
import model.Korisnik;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	KorisnikService korisnikService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/login","/error", "/registracija","/korisnik/**","/umetnici","/narudzbina/**",
	                             "/pocetnaStrana", "/slike","/slika/**", "/umetnik/**",
	                             "/izlozbe", "/izlozba/**","/css/**", "/js/**","/paintings/**","/*.jpg","/*.jpeg","/*.jsp").permitAll()
	            .requestMatchers("/adminPanel").hasRole("ADMIN")
	        )
	        .formLogin(form -> form
	            .loginPage("/login")
	            .loginProcessingUrl("/login").successHandler((request, response, authentication) -> {
	                HttpSession session = request.getSession();
	                
	                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	                String username = userDetails.getUsername();
	                
	                Korisnik korisnik = korisnikService.nadjiKorisnikaPoEmailIliKorisnickomImenu(username).get();
	                
	                session.setAttribute("idKorisnik", korisnik.getIdKorisnik());
	                session.setAttribute("uloga", korisnik.getUloga());
	                session.setAttribute("korpa", new HashSet<Integer>());
	                response.sendRedirect(request.getContextPath() + "/pocetnaStrana");
	                
	            })
	        )
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/pocetnaStrana")
	            .permitAll()
	        );

	    return http.build();
	}

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
