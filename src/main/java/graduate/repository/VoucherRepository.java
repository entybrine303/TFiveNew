package graduate.repository;


import graduate.domain.Voucher;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String> {
	@Transactional
    @Modifying
    @Query("UPDATE Voucher v SET v.quantity = v.quantity - 1 WHERE v.voucherID = :voucherID")
    void decreaseQuantityByOne(String voucherID);
	
	
}
