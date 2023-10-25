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



import graduate.domain.Categories;
import graduate.dto.CategoriesDTO;
import graduate.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


public interface CategoriesService {


	List<Categories> findByNameContaining(String name);

	Page<Categories> findByNameContaining(String name, Pageable pageable);

	Categories save(Categories entity);

	<S extends Categories> Optional<S> findOne(Example<S> example);

	List<Categories> findAll();

	Page<Categories> findAll(Pageable pageable);

	List<Categories> findAll(Sort sort);

	List<Categories> findAllById(Iterable<String> ids);

	List<Categories> saveAll(List<Categories> entities);

	void flush();

	Categories saveAndFlush(Categories entity);

	<S extends Categories> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Categories> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Categories> findById(String id);

	void deleteInBatch(Iterable<Categories> entities);

	boolean existsById(Long id);

	<S extends Categories> long count(Example<S> example);

	void deleteAllInBatch(List<Categories> entities);

	<S extends Categories> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<String> ids);

	<S extends Categories, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteAllInBatch();

	void deleteById(String id);

	Categories getOne(String id);

	void delete(Categories entity);

	Categories getById(Long id);

	Categories getById(String id);

	void deleteAllById(Iterable<? extends String> ids);

	void deleteAll(Iterable<? extends Categories> entities);

	void deleteAll();

	Categories getReferenceById(Long id);

	<S extends Categories> List<S> findAll(Example<S> example);

	<S extends Categories> List<S> findAll(Example<S> example, Sort sort);

	Page<Categories> findByNameContaining(String name, String categoriesID);

	boolean existsById(String id);
}
