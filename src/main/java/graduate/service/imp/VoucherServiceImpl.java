package graduate.service.imp;

import graduate.domain.DriverRegister;
import graduate.domain.Voucher;
import graduate.repository.VoucherRepository;
import graduate.service.VoucherService;
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
public class VoucherServiceImpl implements VoucherService {

  @Autowired
  private VoucherRepository voucherRepository;

  public VoucherServiceImpl(VoucherRepository voucherRepository) {
    this.voucherRepository = voucherRepository;
  }



//  @Override
//  public Page<Voucher> findByNameContaining(String name, Pageable pageable) {
//    return voucherRepository.findByNameContaining(name, pageable);
//  }

  @Override
  public Voucher save(Voucher entity) {
    return voucherRepository.save(entity);
  }

  @Override
  public <S extends Voucher> Optional<S> findOne(Example<S> example) {
    return voucherRepository.findOne(example);
  }

  @Override
  public List<Voucher> findAll() {
    return voucherRepository.findAll();
  }

  @Override
  public Page<Voucher> findAll(Pageable pageable) {
    return voucherRepository.findAll(pageable);
  }

  @Override
  public List<Voucher> findAll(Sort sort) {
    return voucherRepository.findAll(sort);
  }

  @Override
  public List<Voucher> findAllById(Iterable<String> ids) {
    return voucherRepository.findAllById(ids);
  }

  @Override
	public <S extends Voucher> List<S> saveAll(Iterable<S> entities) {
		return voucherRepository.saveAll(entities);
	}

  @Override
  public void flush() {
    voucherRepository.flush();
  }

  @Override
  public Voucher saveAndFlush(Voucher entity) {
    return voucherRepository.saveAndFlush(entity);
  }

  @Override
  public <S extends Voucher> List<S> saveAllAndFlush(Iterable<S> entities) {
    return voucherRepository.saveAllAndFlush(entities);
  }

  @Override
  public <S extends Voucher> Page<S> findAll(Example<S> example, Pageable pageable) {
    return voucherRepository.findAll(example, pageable);
  }

  @Override
  public Optional<Voucher> findById(String id) {
    return voucherRepository.findById(id);
  }

  @Override
  public void deleteInBatch(Iterable<Voucher> entities) {
    voucherRepository.deleteInBatch(entities);
  }

  @Override
  public boolean existsById(String id) {
    return voucherRepository.existsById(id);
  }

  @Override
  public void deleteAllInBatch(List<Voucher> entities) {
    voucherRepository.deleteAllInBatch(entities);
  }

  @Override
  public <S extends Voucher> boolean exists(Example<S> example) {
    return voucherRepository.exists(example);
  }

  @Override
  public void deleteAllByIdInBatch(Iterable<String> ids) {
    voucherRepository.deleteAllByIdInBatch(ids);
  }

  @Override
  public <S extends Voucher, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
    return voucherRepository.findBy(example, queryFunction);
  }

  @Override
  public void deleteAllInBatch() {
    voucherRepository.deleteAllInBatch();
  }

  @Override
  public void deleteById(String id) {
    voucherRepository.deleteById(id);
  }

  @Override
  public Voucher getOne(String id) {
    return voucherRepository.getOne(id);
  }

  @Override
  public void delete(Voucher entity) {
    voucherRepository.delete(entity);
  }

  @Override
  public Voucher getById(String id) {
    return voucherRepository.getById(id);
  }

  @Override
  public void deleteAllById(Iterable<? extends String> ids) {
    voucherRepository.deleteAllById(ids);
  }

  @Override
  public void deleteAll(Iterable<? extends Voucher> entities) {
    voucherRepository.deleteAll(entities);
  }

  @Override
  public void deleteAll() {
    voucherRepository.deleteAll();
  }

  @Override
  public Voucher getReferenceById(String id) {
    return null;
  }

  @Override
  public <S extends Voucher> List<S> findAll(Example<S> example) {
    return voucherRepository.findAll(example);
  }

  @Override
  public <S extends Voucher> List<S> findAll(Example<S> example, Sort sort) {
    return voucherRepository.findAll(example, sort);
  }
}
