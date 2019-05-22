package tw.sanjiheart.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import tw.sanjiheart.model.Noise;

public interface NoiseRepo extends PagingAndSortingRepository<Noise, String> {

}
