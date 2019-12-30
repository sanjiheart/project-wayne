package tw.sanjiheart.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import tw.sanjiheart.model.Pokeman;
import tw.sanjiheart.model.Status;

@Service
public class PokemanService {

  public Pokeman hero() {
    Status status = new Status(255, 255, 255, 100, 100, 128);
    Pokeman wayne = new Pokeman("Wayne", "Male", 100, status);
    return wayne;
  }

  public Pokeman enemy() {
    Random r = new Random();
    int hp = 20 + r.nextInt(256);
    int atk = 20 + r.nextInt(256);
    int def = 20 + r.nextInt(256);
    int sAtk = 20 + r.nextInt(256);
    int sDef = 20 + r.nextInt(256);
    int spd = 20 + r.nextInt(256);
    Status status = new Status(hp, atk, def, sAtk, sDef, spd);
    Pokeman pokeman = new Pokeman("Peter", "Male", 100, status);
    return pokeman;
  }

}
