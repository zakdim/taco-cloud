package tacos.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tacos.Order;

public interface OrderRepository 
	extends CrudRepository<Order, Long> {
	
	List<Order> findByDeliveryZip(String deliveryZip);
	
//	List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(
//            String deliveryZip, Date startDate, Date endDate);
//	
//	List<Order> findByDeliveryNameAndDeliveryCityAllIgnoringCase(
//	        String deliveryName, String deliveryCity);
//	
//	List<Order> findByDeliveryCityOrderByDeliveryName(String city);
//	
//	@Query("Order o where o.deliveryCity='Seattle'")
//	List<Order> readOrdersDeliveredInSeattle();
}