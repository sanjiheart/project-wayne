package tw.sanjiheart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.sanjiheart.model.Pokeman;
import tw.sanjiheart.service.PokemanService;

@RestController
@RequestMapping("/api")
public class PokemanController {

  @Autowired
  private PokemanService pokemanService;

  @GetMapping("/pokeman")
  public Pokeman get(@RequestParam(defaultValue = "false") boolean hero) {
    if (hero) {
      return pokemanService.hero();
    }
    return pokemanService.enemy();
  }

}
