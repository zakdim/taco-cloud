package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TacoCloudApplication {
//public class TacoCloudApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	/*
	 * Could be here too, but instead placed in tacos.web.WebConfig
	 */
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("home");
//	}

}
