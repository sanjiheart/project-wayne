package tw.sanjiheart.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tw.sanjiheart.model.Noise;
import tw.sanjiheart.repo.NoiseRepo;

@Service
@EnableScheduling
public class NoiseService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private Socket clientSocket;
  private PrintWriter out;
  private BufferedReader in;

  @Value("${gateway.host:192.168.1.211}")
  private String host;

  @Value("${gateway.port:48001}")
  private int port;

  @Autowired
  private NoiseRepo noiseRepo;

  @Scheduled(fixedDelay = 5000)
  private void getNoise() {
    try {
      clientSocket = new Socket(host, port);
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      out.println("select device,ai0 from ida-R1240 where device=00000000000000000001;");

      String resp = in.readLine();
      double db = 0;
      while (!resp.equalsIgnoreCase(";")) {
        if (resp.contains("00000000000000000001")) {
          db = Double.parseDouble(resp.substring(resp.indexOf(":") + 1));
          logger.debug("Received noise: {} db", db);
        }
        resp = in.readLine();
      }
      Noise noise = new Noise();
      noise.setValue(db);
      noise.setAt(System.currentTimeMillis());
      noiseRepo.save(noise);

      in.close();
      out.close();
      clientSocket.close();
    } catch (Exception e) {
      Noise noise = new Noise();
      noise.setValue(0);
      noise.setAt(System.currentTimeMillis());
      noiseRepo.save(noise);
      logger.error(e.getMessage());
    }
  }

  public Noise getLatest() {
    return noiseRepo.findAll(PageRequest.of(0, 1, new Sort(Direction.DESC, "at"))).stream().findFirst().get();
  }

}
