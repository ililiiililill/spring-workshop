package io.pivotal.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class HelloSpringBootApplication {

	Logger logger = LoggerFactory
			.getLogger(HelloSpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringBootApplication.class, args);
	}

	@Autowired
	private GreetingRepository greeting;

	@Autowired
	private GreetingCrudRepository crudGreeting;

	@RequestMapping("/findAll")
	public List<Greeting> findAll(){
		java.util.logging.Logger.getGlobal().info("Handling request...");
		return greeting.findAll();
	}

	@RequestMapping("/findById")
	public Greeting findById(int id){
		java.util.logging.Logger.getGlobal().info("Handling request...");
		return crudGreeting.findOne(id);
	}

	@RequestMapping("/findByText")
	public Greeting findByText(String text){
		java.util.logging.Logger.getGlobal().info("Handling request...");
		return crudGreeting.findByText(text);
	}

	@RequestMapping("/findByPartialText")
	public List<Greeting> findByPartialText(String text){
		java.util.logging.Logger.getGlobal().info("Handling request...");
		return crudGreeting.findByTextContainingIgnoreCase(text);
	}

	@RequestMapping("/greet")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "Hello "+name+", I'm a simple Spring Boot App\n";
	}

	/**
	 * 
	 * Loads the database on startup
	 * 
	 * @param gr
	 * @return
	 */
	@Bean
	CommandLineRunner loadDatabase(GreetingRepository gr) {
		return args -> {
			logger.debug("loading database..");
			gr.save(new Greeting(1, "Hello"));
			gr.save(new Greeting(2, "Hola"));
			gr.save(new Greeting(3, "Ohai"));
			logger.debug("record count: {}", gr.count());
			gr.findAll().forEach(x -> logger.debug(x.toString()));
		};

	}

}
