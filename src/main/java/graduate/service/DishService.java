package graduate.service;

import graduate.domain.Categories;
import graduate.domain.Dish;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;

public interface DishService {


  Dish save(Dish entity);
  List<Dish> findAll();
  Optional<Dish> findById(String id);
  <S extends Dish> long count(Example<S> example);
  long count();
  void deleteById(String id);
  void delete(Dish entity);
}
