package graduate.repository;

import graduate.domain.Category;
import graduate.domain.Dish;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DishRepository extends JpaRepository<Dish, String> {
	List<Dish> findByNameContaining(String name);

	Page<Dish> findByNameContaining(String name, Pageable pageable);

	Page<Dish> findByNameContaining(Category categories, Pageable pageable);

	@Query(value = "SELECT TOP 3 * FROM Dish ORDER BY NEWID()", nativeQuery = true)
	List<Dish> findRandomDishes();
	
	@Query("SELECT d FROM Dish d WHERE d.category.categoryID = :categoryID")
    List<Dish> findByCategoryId(@Param("categoryID") String categoryID);
	
	@Query("SELECT d FROM Dish d WHERE d.discountPrice BETWEEN :min AND :max")
    List<Dish> findByPriceRange(@Param("min") Double min, @Param("max") Double max);
	@Query("SELECT d FROM Dish d ORDER BY d.createdDate DESC")
    List<Dish> findAllOrderByCreatedDateDesc();
	 List<Dish> findTop8ByOrderByCreatedDateDesc();
	 
	 @Query("SELECT od.dish " +
		       "FROM OrderDetail od " +
		       "GROUP BY od.dish " +
		       "ORDER BY COUNT(od.dish) DESC")
		List<Dish> findTop8ByOrderByDishCountDesc();
}
