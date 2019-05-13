package tacos.web.api;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.User;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@Slf4j
@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private TacoRepository tacoRepo;

    @Autowired
    EntityLinks entityLinks;

    public DesignTacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    //	@GetMapping("/recent")
    //	public Iterable<Taco> recentTacos() {
    //		PageRequest page = PageRequest.of(
    //				0, 12, Sort.by("createdAt").descending());
    //		return tacoRepo.findAll(page).getContent();
    //	}

    @GetMapping("/recent")
    public Resources<Resource<Taco>> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        
        List<Taco> tacos = tacoRepo.findAll(page).getContent();
        Resources<Resource<Taco>> recentResources = Resources.wrap(tacos);
        
        recentResources.add(
                linkTo(methodOn(DesignTacoController.class).recentTacos())
                .withRel("recents"));
        
//        recentResources.add(
//                ControllerLinkBuilder.linkTo(DesignTacoController.class)
//                                     .slash("recent")
//                                     .withRel("recents"));
        
        return recentResources;
    }
    
//    @GetMapping("/recent")
//    public Resources<Resource<Taco>> recentTacos() {
//        PageRequest page = PageRequest.of(
//                0, 12, Sort.by("createdAt").descending());
//        
//        List<Taco> tacos = tacoRepo.findAll(page).getContent();
//        Resources<Resource<Taco>> recentResources = Resources.wrap(tacos);
//        
//        recentResources.add(
//                new Link("http://localhost:8080/design/recent", "recents"));
//        
//        return recentResources;
//    }

    //	@GetMapping("/{id}")
    //	public Taco tacoById(@PathVariable("id") Long id) {
    //		Optional<Taco> optTaco = tacoRepo.findById(id);
    //		return optTaco.orElse(null);
    //	}

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTace(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
}
