package tw.sanjiheart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.sanjiheart.service.BattleService;
import tw.sanjiheart.service.PokemanService;

@RestController
public class BattleController {

  @Autowired
  private PokemanService pokemanService;

  @Autowired
  private BattleService battleService;

  @GetMapping("/battle")
  public ResponseEntity<List<String>> battle() {
    return new ResponseEntity<List<String>>(battleService.battle(pokemanService.hero(), pokemanService.enemy()),
        HttpStatus.OK);
  }

}
