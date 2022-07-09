package com.proyecto.proyecto;

import com.proyecto.proyecto.services.IMails;
import com.proyecto.proyecto.services.Observador;
import com.proyecto.proyecto.services.ServicioMails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ComponentScan(basePackages = {"com.proyecto.proyecto"})
@SpringBootApplication
public class ProyectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}



}
