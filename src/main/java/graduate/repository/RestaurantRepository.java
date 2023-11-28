package graduate.repository;

import graduate.domain.Category;
import graduate.domain.Restaurant;
import graduate.domain.Dish;
import graduate.domain.Restaurant;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
	Restaurant findByAccount_Username(String username);
}
