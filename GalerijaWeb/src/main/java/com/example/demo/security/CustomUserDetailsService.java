package com.example.demo.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.services.KorisnikService;

import model.Korisnik;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private KorisnikService korisnikService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Optional<Korisnik> korisnik = korisnikService.nadjiKorisnikaPoEmailIliKorisnickomImenu(username);

        String role = korisnik.get().getUloga().equals("admin") ? "ROLE_ADMIN" : "ROLE_USER";

        return new org.springframework.security.core.userdetails.User(
            korisnik.get().getKorisnickoIme(),
            korisnik.get().getSifra(), 
            List.of(new SimpleGrantedAuthority(role))
        );
    }
}