package se.rsv.arende.arendeinformationspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import se.rsv.arende.arendeinformationspring.model.Arende;

@SpringBootApplication
public class ArendeinformationspringApplication extends SpringBootServletInitializer {
	
	@Autowired
	private  ArendeRepository repository;
	
	private static final Logger log =
			LoggerFactory.getLogger(ArendeinformationspringApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(ArendeinformationspringApplication.class, args);
	}
	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder builder) { return
	 * builder.sources(ArendeinformationspringApplication.class); }
	 */

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
		repository.save(new Arende("123", "SKV", "2020-03-01"));
		repository.save(new Arende("456", "SKV", "2020-03-02"));
		repository.save(new Arende("789", "TRV", "2020-03-01"));
		repository.save(new Arende("222", "TRV", "2020-03-02"));
		
		log.info("test-demo:");
		for (Arende arende : repository.findAll()) {
			log.info(arende.toString());
		}
		log.info("");
		
		Arende arende = repository.findById(1L);
		log.info("hitta med ID");
		log.info(arende.toString());
		log.info("");
		
		log.info("sök på ärendnummer");
		repository.findByMyndighet("SKV").forEach(skv -> {log.info(skv.toString());
		});
		
		log.info("");
	};
	}		
}
