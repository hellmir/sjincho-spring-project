package personal.delivery.order;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.delivery.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
