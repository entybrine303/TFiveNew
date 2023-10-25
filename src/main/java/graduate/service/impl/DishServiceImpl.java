package graduate.service.impl;


import graduate.domain.Categories;
import graduate.domain.Dish;
import graduate.repository.DishRepository;
import graduate.service.DishService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class DishServiceImpl  implements DishService {

  @Autowired
  private DishRepository dishRepository;


  @Override
  public List<Dish> findByNameContaining(String name) {
    return dishRepository.findByNameContaining(name);
  }


  @Override
  public Page<Dish> findByNameContaining(Categories category, Pageable pageable) {
    return dishRepository.findByNameContaining(category, pageable);
  }


  @Override
  public Page<Dish> findByNameContaining(String name, Pageable pageable) {
    return dishRepository.findByNameContaining(name, pageable);
  }
  @Override
  public Dish save(Dish entity) {
    return dishRepository.save(entity);
  }
  @Override
  public List<Dish> findAll() {
    return dishRepository.findAll();
  }
  @Override
  public Optional<Dish> findById(String id) {
    return dishRepository.findById(id);
  }

  @Override
  public <S extends Dish> long count(Example<S> example) {
    return dishRepository.count(example);
  }
  @Override
  public long count() {
    return dishRepository.count();
  }

  @Override
  public void deleteById(String id) {
    dishRepository.deleteById(id);
  }

  @Override
  public void delete(Dish entity) {
    dishRepository.delete(entity);
  }

}
