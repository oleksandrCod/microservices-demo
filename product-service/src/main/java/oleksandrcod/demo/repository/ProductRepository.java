package oleksandrcod.demo.repository;

import io.micrometer.observation.annotation.Observed;
import oleksandrcod.demo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

@Observed
public interface ProductRepository extends MongoRepository<Product, Long> {
}
