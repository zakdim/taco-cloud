package tacos.web.api;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import tacos.Taco;

public class TacoResource extends ResourceSupport {
	
	private static final IngredientResourceAssembler 
		ingredientAssembler = new IngredientResourceAssembler();

	@Getter
	private final String name;
	
	@Getter
	private final Date createAt;
	
	@Getter
	private final List<IngredientResource> ingredients;
	
	public TacoResource(Taco taco) {
		this.name = taco.getName();
		this.createAt = taco.getCreatedAt();
		this.ingredients = 
				ingredientAssembler.toResources(taco.getIngredients());
	}
	
}
