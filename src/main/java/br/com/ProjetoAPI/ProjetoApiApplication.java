package br.com.ProjetoAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApiApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}

}
