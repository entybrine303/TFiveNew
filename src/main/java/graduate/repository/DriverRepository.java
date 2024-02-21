package graduate.repository;

import graduate.domain.Category;
import graduate.domain.Driver;
import graduate.domain.Dish;
import graduate.domain.Driver;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
	List<Driver> findByNameContaining(String name);
	
	Driver findByAccount_Username(String username);

	Page<Driver> findByNameContaining(String name, Pageable pageable);
}
