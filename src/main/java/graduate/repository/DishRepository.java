package graduate.repository;

import graduate.domain.Category;
import graduate.domain.Dish;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DishRepository extends JpaRepository<Dish, String> {
  List<Dish> findByNameContaining(String name);
  Page<Dish> findByNameContaining(String name, Pageable pageable);
  Page<Dish> findByNameContaining(Category categories, Pageable pageable);
  @Query(value = "SELECT TOP 3 * FROM Dish ORDER BY NEWID()", nativeQuery = true)
  List<Dish> findRandomDishes();
}
