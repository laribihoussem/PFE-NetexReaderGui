package com.Netex.Reader.Gui;



import com.Netex.Reader.Gui.models.Routes;
import com.Netex.Reader.Gui.service.RoutesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@SpringBootApplication
public class NetexReaderGuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetexReaderGuiApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



	@Bean
	CommandLineRunner run(RoutesService routesService) {
		return args -> {
			routesService.saveRoute(new Routes(1,"converting files", "converted"));
			routesService.saveRoute(new Routes(2,"Sending File via SFTP and message to kafka", "Sent"));
			/*userService.saveRole(new Role(1, "ROLE_USER"));
			userService.saveRole(new Role(2, "ROLE_MANAGER"));
			userService.saveRole(new Role(3, "ROLE_ADMIN"));
			userService.saveRole(new Role(4, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new Users(1, "John Travolta", "john", "john.com",123456,"1234", new ArrayList<>()));
			userService.saveUser(new Users(2, "Will Smith", "will", "will.com",123456, "1234", new ArrayList<>()));
			userService.saveUser(new Users(3, "Jim Carry", "jim", "jim.com",123456,"1234", new ArrayList<>()));
			userService.saveUser(new Users(3, "Jim Carry", "jim", "arnold.com",123456,"1234", new ArrayList<>()));
			userService.saveUser(new Users(1, "John Travolta", "john", "joh1.com",123456,"1234", new ArrayList<>()));
			userService.saveUser(new Users(2, "Will Smith", "will", "will3.com",123456, "1234", new ArrayList<>()));
			userService.saveUser(new Users(3, "Jim Carry", "jim", "jim2.com",123456,"1234", new ArrayList<>()));
			userService.saveUser(new Users(3, "Jim Carry", "jim", "arnold65.com",123456,"1234", new ArrayList<>()));


			userService.addRoleToUser("john.com", "ROLE_USER");
			userService.addRoleToUser("will.com", "ROLE_MANAGER");
			userService.addRoleToUser("jim.com", "ROLE_ADMIN");
			userService.addRoleToUser("arnold.com", "ROLE_SUPER_ADMIN");*/
			//userService.addRoleToUser("arnold.com", "ROLE_ADMIN");
			//userService.addRoleToUser("john.com", "ROLE_ADMIN");
		};
	}




}
