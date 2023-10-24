package graduate.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable{
	@Id
	@Column(length = 10)
	private String orderID;
	@Column(length = 10)
	private String restaurantID;
	@Column(length = 10)
	private String customerID;
	@Column(length = 10)
	private String voucherID;
	
//	Tạo trường dữ liệu có kiểu dữ liệu là datetime
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	private String status;
	private double totalPrice;
	private String noteForRestaurant;
}
