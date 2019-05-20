package tacos.restclient;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;

@Service
@Slf4j
public class TacoCloudClient {

	private RestTemplate rest;

	public TacoCloudClient(RestTemplate rest) {
		this.rest = rest;
	}

	//
	// GET examples
	//

	/*
	 * Specify parameter as varargs argument
	 */
//	public Ingredient getIngredientById(String ingredientId) {
//		return rest.getForObject(
//				"http://localhost:8080/api/ingredients/{id}", 
//				Ingredient.class, ingredientId);
//	}

	/*
	 * Alternate implementations... The next three methods are alternative
	 * implementations of getIngredientById() as shown in chapter 6. If you'd like
	 * to try any of them out, comment out the previous method and uncomment the
	 * variant you want to use.
	 */

	/*
	 * Specify parameters with a map
	 */
//	public Ingredient getIngredientById(String ingredientId) {
//		Map<String, String> urlVariables = new HashMap<>();
//		urlVariables.put("id", ingredientId);
//
//		return rest.getForObject(
//				"http://localhost:8080/api/ingredients/{id}", 
//				Ingredient.class, urlVariables);
//	}

	/*
	 * Request with URI instead of String
	 */
//	public Ingredient getIngredientById(String ingredientId) {
//		Map<String, String> urlVariables = new HashMap<>();
//		urlVariables.put("id", ingredientId);
//		URI url = UriComponentsBuilder
//				.fromHttpUrl("http://localhost:8080/api/ingredients/{id}")
//				.build(urlVariables);
//		
//		return rest.getForObject(url, Ingredient.class);
//	}

	/*
	 * Use getForEntity() instead of getForObject()
	 */
	public Ingredient getIngredientById(String ingredientId) {
		ResponseEntity<Ingredient> responseEntity = rest.getForEntity("http://localhost:8080/ingredients/{id}",
				Ingredient.class, ingredientId);

		LocalDateTime fetchTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(responseEntity.getHeaders().getDate()),
				ZoneId.systemDefault());

		log.info(String.format("Fetched time: %s", fetchTime));

		return responseEntity.getBody();
	}

	public List<Ingredient> getAllIngredients() {
		return rest.exchange("http://localhost:8080/ingredients", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Ingredient>>() {
				}).getBody();
	}

	//
	// PUT examples
	//
	public void updateIngredient(Ingredient ingredient) {
		rest.put("http://localhost:8080/ingredients/{id}",
				ingredient, ingredient.getId());
	}

	//
	// DELETE examples
	//
	public void deleteIngredient(Ingredient ingredient) {
		rest.delete("http://localhost:8080/ingredients/{id}", 
				ingredient.getId());
	}

	//
	// POST examples
	//
	public Ingredient createIngredient(Ingredient ingredient) {
		return rest.postForObject(
				"http://localhost:8080/ingredients",
				ingredient, Ingredient.class);
	}
}
