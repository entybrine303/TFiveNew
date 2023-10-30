package graduate.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@Column(length = 10)
	private String restaurantID;
	private String description;
	private String img;
	private double reducedPrice;
	private double minimumPrice;
	private boolean IsShare;
	private boolean VoucherType;
}
