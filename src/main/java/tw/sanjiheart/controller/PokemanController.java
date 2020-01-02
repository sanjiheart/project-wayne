package tw.sanjiheart.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.sanjiheart.model.Pokeman;
import tw.sanjiheart.service.PokemanService;

@RestController
@RequestMapping("/api")
public class PokemanController {

  @Autowired
  private PokemanService pokemanService;

  @GetMapping("/pokeman/{no}")
  public Pokeman get(@PathVariable Integer no) {
    return pokemanService.get(no);
  }

  @PutMapping("/pokeman/1")
  public Pokeman customHero(@RequestBody Pokeman pokeman) {
    Objects.requireNonNull(pokeman.getName());
    Objects.requireNonNull(pokeman.getGender());
    Objects.requireNonNull(pokeman.getLv());
    Objects.requireNonNull(pokeman.getStatus().getHp());
    Objects.requireNonNull(pokeman.getStatus().getAttack());
    Objects.requireNonNull(pokeman.getStatus().getDefense());
    Objects.requireNonNull(pokeman.getStatus().getSpecialAttack());
    Objects.requireNonNull(pokeman.getStatus().getSpecialDefense());
    Objects.requireNonNull(pokeman.getStatus().getSpeed());
    Objects.requireNonNull(pokeman.getPhotoUrl());

    return pokemanService.customHero(pokeman);
  }

}
