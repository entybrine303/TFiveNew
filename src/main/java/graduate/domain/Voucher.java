package graduate.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voucher")
public class Voucher implements Serializable{
	@Id
	@Column(length = 10)
	private String voucherID;
	@Column(columnDefinition = "nvarchar(max)")
	private String description;
	private String img;
	private Double reducedPrice;
	private Double minimumPrice;
	private Boolean isShare;
	private Boolean VoucherType;
	
	@ManyToOne
	@JoinColumn(name = "restaurantID", referencedColumnName = "restaurantID")
	private Restaurant restaurant;

	@OneToMany(mappedBy = "voucher")
    private List<Order> orders;

	public Voucher(String voucherID) {
		this.voucherID = voucherID;
	}
}
