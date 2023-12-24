package oleksandrcod.demo.repository;

import io.micrometer.observation.annotation.Observed;
import oleksandrcod.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

@Observed
public interface OrderRepository extends JpaRepository<Order, Long> {
}
