package tw.sanjiheart.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import tw.sanjiheart.model.Pokeman;

public interface PokemanRepo extends MongoRepository<Pokeman, Integer> {

}
