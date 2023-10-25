package graduate.service;

import graduate.domain.Categories;
import graduate.domain.Dish;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DishService {


  List<Dish> findByNameContaining(String name);

  Page<Dish> findByNameContaining(Categories category, Pageable pageable);

  Page<Dish> findByNameContaining(String name, Pageable pageable);

  Dish save(Dish entity);
  List<Dish> findAll();
  Optional<Dish> findById(String id);
  <S extends Dish> long count(Example<S> example);
  long count();
  void deleteById(String id);
  void delete(Dish entity);
}
