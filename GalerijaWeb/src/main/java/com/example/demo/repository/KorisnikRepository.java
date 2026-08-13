package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	
	@Modifying
	@Query("UPDATE Korisnik k SET k.uloga = :uloga WHERE k.idKorisnik = :idKorisnik")
	void promeniUloguKorisniku(@Param("idKorisnik") Integer idKorisnik, @Param("uloga") String uloga);
	
	Optional<Korisnik> findByKorisnickoIme(String KorisnickoIme);
	Optional<Korisnik> findByMail(String mail);
	Optional<Korisnik> findByKorisnickoImeOrMail(String korisnickoIme, String mail);
}
