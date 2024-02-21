package graduate.service.imp;

import graduate.domain.Wishlist;
import graduate.domain.DriverRegister;
import graduate.repository.CartRepository;
import graduate.repository.WishlistRepository;
import graduate.service.CartService;
import graduate.service.WishlistService;

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

@Repository
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;

	@Override
	public Long countByCustomerID(String customerID) {
		return wishlistRepository.countByCustomerID(customerID);
	}

	@Override
	public void deleteByDish_DishID(String dishID) {
		wishlistRepository.deleteByDish_DishID(dishID);
	}

	@Override
	public List<Wishlist> findByCustomer_CustomerID(String customerID) {
		// TODO Auto-generated method stub
		return wishlistRepository.findByCustomer_CustomerID(customerID);
	}

	@Override
	public Wishlist save(Wishlist entity) {
		return wishlistRepository.save(entity);
	}

	@Override
	public <S extends Wishlist> Optional<S> findOne(Example<S> example) {
		return wishlistRepository.findOne(example);
	}

	@Override
	public List<Wishlist> findAll() {
		return wishlistRepository.findAll();
	}

	@Override
	public Page<Wishlist> findAll(Pageable pageable) {
		return wishlistRepository.findAll(pageable);
	}

	@Override
	public List<Wishlist> findAll(Sort sort) {
		return wishlistRepository.findAll(sort);
	}

	@Override
	public List<Wishlist> findAllById(Iterable<String> ids) {
		return wishlistRepository.findAllById(ids);
	}

	@Override
	public <S extends Wishlist> List<S> saveAll(Iterable<S> entities) {
		return wishlistRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		wishlistRepository.flush();
	}

	@Override
	public Wishlist saveAndFlush(Wishlist entity) {
		return wishlistRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Wishlist> List<S> saveAllAndFlush(Iterable<S> entities) {
		return wishlistRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Wishlist> Page<S> findAll(Example<S> example, Pageable pageable) {
		return wishlistRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Wishlist> findById(String id) {
		return wishlistRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Wishlist> entities) {
		wishlistRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return wishlistRepository.existsById(id);
	}

	@Override
	public <S extends Wishlist> boolean exists(Example<S> example) {
		return wishlistRepository.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		wishlistRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Wishlist, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return wishlistRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllInBatch() {
		wishlistRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(String id) {
		wishlistRepository.deleteById(id);
	}

	@Override
	public Wishlist getOne(String id) {
		return wishlistRepository.getOne(id);
	}

	@Override
	public void delete(Wishlist entity) {
		wishlistRepository.delete(entity);
	}

	@Override
	public Wishlist getById(String id) {
		return wishlistRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		wishlistRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Wishlist> entities) {
		wishlistRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		wishlistRepository.deleteAll();
	}

	@Override
	public Wishlist getReferenceById(String id) {
		return null;
	}

	@Override
	public <S extends Wishlist> List<S> findAll(Example<S> example) {
		return wishlistRepository.findAll(example);
	}

	@Override
	public <S extends Wishlist> List<S> findAll(Example<S> example, Sort sort) {
		return wishlistRepository.findAll(example, sort);
	}

	@Override
	public void deleteAllInBatch(List<Wishlist> entities) {
		wishlistRepository.deleteInBatch(entities);

	}

	@Override
	public boolean productIsPresentInWishlist(String productId, String customerId) {
		Optional<Wishlist> w=  wishlistRepository.findByProductIdAndCustomerId(productId, customerId);
		if (w.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteByCustomer_CustomerID(String customerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByProductIdAndCustomerId(String productId, String customerId) {
		wishlistRepository.deleteByProductIdAndCustomerId(productId, customerId);
	}

}
