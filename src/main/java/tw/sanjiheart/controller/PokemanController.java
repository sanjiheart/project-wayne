package tw.sanjiheart.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.sanjiheart.model.Pokeman;
import tw.sanjiheart.util.HttpException;
import tw.sanjiheart.service.PokemanService;

@RestController
@RequestMapping("/api")
public class PokemanController {

  @Autowired
  private PokemanService pokemanService;

  @GetMapping("/pokeman/{no}")
  public Pokeman get(@PathVariable Integer no) {
    if (no != 1 && no != 2) {
      throw new HttpException(HttpStatus.NOT_FOUND, "Parameter 'no' only accepts '1' or '2'.");
    }

    return pokemanService.get(no);
  }

  @PutMapping("/pokeman/1")
  public Pokeman customHero(@RequestBody Pokeman pokeman) {
    Objects.requireNonNull(pokeman.getName(), "Field 'name' is required.");
    Objects.requireNonNull(pokeman.getGender(), "Field 'gender' is required.");
    Objects.requireNonNull(pokeman.getLv(), "Field 'lv' is required.");
    Objects.requireNonNull(pokeman.getStatus(), "Field 'status' is required.");
    Objects.requireNonNull(pokeman.getPhotoUrl(), "Field 'photoUrl' is required.");

    return pokemanService.customHero(pokeman);
  }

}
