package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.data.IngredientRepository;
import tacos.data.UserRepository;
import tacos.Ingredient.Type;

@Profile({"!prod", "!qa"})
@Configuration
public class DevelopmentConfig {

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo,
			UserRepository userRepo, PasswordEncoder encoder) {	// user repo for ease of testing with a build-in user
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
				repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
				repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
				repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
				repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
				repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
				repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
				repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
				repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
				repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
				repo.save(new Ingredient("SPCY", "Spicy", Type.SAUCE));
				
				userRepo.save(new User("habuma", encoder.encode("password"), 
						"Graig Walls", "123 North Street", "Cross Roads", "TX",
						"76227", "123-123-1234"));
				
				userRepo.save(new User("zakd", encoder.encode("zakd"), 
						"Dmitri Zakharov", "123 North Street", "Montreal", "QC",
						"H2N2N2", "123-123-1234"));
			}
		};
	}
}
