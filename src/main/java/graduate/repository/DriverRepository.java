package graduate.repository;

import graduate.domain.Driver;
import graduate.domain.DriverRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String>{

}
