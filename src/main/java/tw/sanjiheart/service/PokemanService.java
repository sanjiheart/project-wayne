package tw.sanjiheart.service;

import java.net.URI;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tw.sanjiheart.model.Pokeman;
import tw.sanjiheart.model.Status;
import tw.sanjiheart.model.Uniname;
import tw.sanjiheart.repo.PokemanRepo;

@Service
public class PokemanService {

  @Autowired
  private PokemanRepo pokemanRepo;

  public Pokeman hero() {
    Status status = new Status(255, 255, 255, 100, 100, 128);
    Pokeman hero = new Pokeman("Wayne", "Male", 100, status, "imgs/hero.jpg");
    hero.setNo(1);
    return hero;
  }

  public Pokeman enemy() {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent",
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36");
    RequestEntity<Void> reqEntity = new RequestEntity<Void>(headers, HttpMethod.GET,
        URI.create("https://uinames.com/api/?ext"));
    Uniname uniname = restTemplate.exchange(reqEntity, Uniname.class).getBody();
    Random r = new Random();
    int hp = 20 + r.nextInt(236);
    int atk = 20 + r.nextInt(236);
    int def = 20 + r.nextInt(236);
    int sAtk = 20 + r.nextInt(236);
    int sDef = 20 + r.nextInt(236);
    int spd = 20 + r.nextInt(236);
    Status status = new Status(hp, atk, def, sAtk, sDef, spd);
    Pokeman pokeman = new Pokeman(uniname.getName(), uniname.getGender(), 100, status, uniname.getPhoto());
    pokeman.setNo(2);
    return pokeman;
  }

  public Pokeman customHero(Pokeman pokeman) {
    pokeman.setNo(1);
    return pokemanRepo.save(pokeman);
  }

  public Pokeman get(Integer no) {
    if (no == 1) {
      try {
        return pokemanRepo.existsById(no) ? pokemanRepo.findById(no).get() : hero();
      } catch (DataAccessResourceFailureException e) {
        e.printStackTrace();
        return hero();
      }
    } else {
      return enemy();
    }
  }

}
