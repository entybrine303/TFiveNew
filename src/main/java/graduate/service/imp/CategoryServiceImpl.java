package graduate.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import org.springframework.stereotype.Repository;

import graduate.domain.Category;
import graduate.repository.CategoryRepository;
import graduate.service.CategoryService;

@Repository
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoriesRepository;

	public CategoryServiceImpl(CategoryRepository categoriesRepository) {
		this.categoriesRepository = categoriesRepository;
	}

	@Override
	public List<Category> findByNameContaining(String name) {
		return categoriesRepository.findByNameContaining(name);
	}

	@Override
	public Page<Category> findByNameContaining(String name, Pageable pageable) {
		return categoriesRepository.findByNameContaining(name, pageable);
	}

	@Override
	public Category save(Category entity) {
		return categoriesRepository.save(entity);
	}

	@Override
	public <S extends Category> Optional<S> findOne(Example<S> example) {
		return categoriesRepository.findOne(example);
	}

	@Override
	public List<Category> findAll() {
		return categoriesRepository.findAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoriesRepository.findAll(pageable);
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoriesRepository.findAll(sort);
	}

	@Override
	public List<Category> findAllById(Iterable<String> ids) {
		return categoriesRepository.findAllById(ids);
	}

	@Override
	public List<Category> saveAll(List<Category> entities) {
		return categoriesRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		categoriesRepository.flush();
	}

	@Override
	public Category saveAndFlush(Category entity) {
		return categoriesRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoriesRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
		return categoriesRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Category> findById(String id) {
		return categoriesRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Category> entities) {
		categoriesRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return categoriesRepository.existsById(id);
	}

	@Override
	public void deleteAllInBatch(List<Category> entities) {
		categoriesRepository.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Category> boolean exists(Example<S> example) {
		return categoriesRepository.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		categoriesRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Category, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return categoriesRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllInBatch() {
		categoriesRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(String id) {
		categoriesRepository.deleteById(id);
	}

	@Override
	public Category getOne(String id) {
		return categoriesRepository.getOne(id);
	}

	@Override
	public void delete(Category entity) {
		categoriesRepository.delete(entity);
	}

	@Override
	public Category getById(String id) {
		return categoriesRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		categoriesRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		categoriesRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		categoriesRepository.deleteAll();
	}

	@Override
	public Category getReferenceById(String id) {
		return null;
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example) {
		return categoriesRepository.findAll(example);
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
		return categoriesRepository.findAll(example, sort);
	}

	@Override
	public Page<Category> findByNameContaining(String name, String categoriesID) {
		// TODO Auto-generated method stub
		return null;
	}

}
