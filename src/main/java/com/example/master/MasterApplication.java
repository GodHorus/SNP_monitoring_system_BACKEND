package com.example.master;

//import com.example.master.Dto.testDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableAsync
@SpringBootApplication
@EnableMethodSecurity
@CrossOrigin(origins = "*")
public class MasterApplication {
//CommandLineRunner run () {
//    return args -> {
//        var testDto  = new testDto( 1, "sanket", 2002L);
//        System.out.println(testDto.id());
//    };
//}
	public static void main(String[] args) {
		SpringApplication.run(MasterApplication.class, args);
	}

}
