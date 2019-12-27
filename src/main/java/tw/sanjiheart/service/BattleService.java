package tw.sanjiheart.service;

import org.springframework.stereotype.Service;

import tw.sanjiheart.model.Pokeman;
import tw.sanjiheart.model.Status;

@Service
public class BattleService {

  public boolean battle(Pokeman hero, Pokeman enemy) {
    Status heroStatus = hero.getStatus();
    Status enemyStatus = enemy.getStatus();
    return true;
  }

}
