package tw.sanjiheart.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import tw.sanjiheart.model.Pokeman;

@Service
public class BattleService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private int attack(Pokeman attacker, Pokeman defender) {
    // pokemon damage formula: damage = ((2 * LV + 10) / 250 * (ATK / DEF) * move damage + 2) * bonus
    float a = (2 * (float) attacker.getLv() + 10) / 250;
    float b = (float) attacker.getStatus().getAttack() / (float) defender.getStatus().getDefense();
    int damage = Math.round((a * b * 80 + 2) * 1);
    return damage;
  }

  public List<String> battle(Pokeman pk1, Pokeman pk2) {
    List<String> battleLogs = Lists.newArrayList();
    battleLogs.add(String.format("%s HP: %d, %s HP: %d", pk1.getName(), pk1.getStatus().getHp(), pk2.getName(),
        pk2.getStatus().getHp()));
    while (pk1.getStatus().getHp() > 0 && pk2.getStatus().getHp() > 0) {
      int dmg = 0;
      if (pk1.getStatus().getSpeed() > pk2.getStatus().getSpeed()) {
        dmg = attack(pk1, pk2);
        pk2.getStatus().setHp(pk2.getStatus().getHp() - dmg);
        battleLogs.add(String.format("%s causes %s %d damage.", pk1.getName(), pk2.getName(), dmg));
      } else {
        dmg = attack(pk2, pk1);
        pk1.getStatus().setHp(pk1.getStatus().getHp() - dmg);
        battleLogs.add(String.format("%s causes %s %d damage.", pk2.getName(), pk1.getName(), dmg));
      }
      battleLogs.add(String.format("%s HP: %d, %s HP: %d", pk1.getName(), pk1.getStatus().getHp(), pk2.getName(),
          pk2.getStatus().getHp()));
      if (pk1.getStatus().getSpeed() > pk2.getStatus().getSpeed()) {
        dmg = attack(pk2, pk1);
        pk1.getStatus().setHp(pk1.getStatus().getHp() - dmg);
        battleLogs.add(String.format("%s causes %s %d damage.", pk2.getName(), pk1.getName(), dmg));
      } else {
        dmg = attack(pk1, pk2);
        pk2.getStatus().setHp(pk2.getStatus().getHp() - dmg);
        battleLogs.add(String.format("%s causes %s %d damage.", pk1.getName(), pk2.getName(), dmg));
      }
      battleLogs.add(String.format("%s HP: %d, %s HP: %d", pk1.getName(), pk1.getStatus().getHp(), pk2.getName(),
          pk2.getStatus().getHp()));
    }
    return battleLogs;
  }

}
