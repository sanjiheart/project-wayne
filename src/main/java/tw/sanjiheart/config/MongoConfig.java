package tw.sanjiheart.config;

import java.util.Objects;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

  @Override
  public MongoClient mongoClient() {
    String uri = Objects.isNull(System.getenv("MONGO_URI")) ? "mongodb://127.0.0.1:27017" : System.getenv("MONGO_URI");
    return new MongoClient(new MongoClientURI(uri));
  }

  @Override
  protected String getDatabaseName() {
    return Objects.isNull(System.getenv("DB_NAME")) ? "project-wayne" : System.getenv("DB_NAME");
  }

}
