package be.epsmarche.sgbd.pierre.springPierre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "be.epsmarche.sgbd.pierre.springPierre.controller")
public class SpringPierreApplication {

	public static void main(String[] args) {		
		SpringApplication.run(SpringPierreApplication.class, args);
	}

}
