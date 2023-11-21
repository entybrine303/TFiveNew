package graduate.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;



import graduate.domain.Category;
import graduate.domain.DriverRegister;
import graduate.dto.CategoryDTO;
import graduate.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


public interface CategoryService {

	Page<Category> findByNameContaining(String name, String categoriesID);

	<S extends Category> List<S> findAll(Example<S> example, Sort sort);

	<S extends Category> List<S> findAll(Example<S> example);

	Category getReferenceById(String id);

	void deleteAll();

	void deleteAll(Iterable<? extends Category> entities);

	void deleteAllById(Iterable<? extends String> ids);

	Category getById(String id);

	void delete(Category entity);

	Category getOne(String id);

	void deleteById(String id);

	void deleteAllInBatch();

	<S extends Category, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<String> ids);

	<S extends Category> boolean exists(Example<S> example);

	void deleteAllInBatch(List<Category> entities);

	boolean existsById(String id);

	void deleteInBatch(Iterable<Category> entities);

	Optional<Category> findById(String  id);

	<S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

	Category saveAndFlush(Category entity);

	void flush();

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	List<Category> findAllById(Iterable<String> ids);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> Optional<S> findOne(Example<S> example);

	Category save(Category entity);

	Page<Category> findByNameContaining(String name, Pageable pageable);

	List<Category> findByNameContaining(String name);

}
