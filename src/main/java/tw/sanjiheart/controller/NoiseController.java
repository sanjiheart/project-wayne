package tw.sanjiheart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.sanjiheart.model.Noise;
import tw.sanjiheart.service.NoiseService;

@RestController
public class NoiseController {

  @Autowired
  private NoiseService noiseService;

  @GetMapping(value = "/api/sensors/noise")
  public ResponseEntity<Noise> getLatest() {
    return new ResponseEntity<Noise>(noiseService.getLatest(), HttpStatus.OK);
  }

}
