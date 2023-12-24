package oleksandrcod.demo.repository;

import io.micrometer.observation.annotation.Observed;
import java.util.List;
import java.util.Optional;
import oleksandrcod.demo.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

@Observed
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
