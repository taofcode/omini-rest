package zw.co.sbs.steward.freelancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class FreelancerApplication {

	public static void main(String[] args) {
		System.getProperties().put( "server.port", 83 );
		SpringApplication.run(FreelancerApplication.class, args);
	}
}
