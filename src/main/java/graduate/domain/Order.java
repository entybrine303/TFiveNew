package graduate.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
//	Tạo trường dữ liệu có kiểu dữ liệu là datetime
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	@Column(columnDefinition = "nvarchar(100)")
	private String status;
	private Double totalPrice;
	@Column(columnDefinition = "nvarchar(max)")
	private String noteForRestaurant;
	private Double shipMoney;
	
	@ManyToOne
	@JoinColumn(name = "restaurantID", referencedColumnName = "restaurantID")
	private Restaurant restaurant;	
	@ManyToOne
	@JoinColumn(name = "customerID", referencedColumnName = "customerID")
	private Customer customer;
	@OneToOne
    @JoinColumn(name = "voucherid", referencedColumnName = "voucherid")
    private Voucher voucher;

	@OneToOne(mappedBy = "orders")
    private OrderDetail orderDetail;
	@OneToOne(mappedBy = "orders")
    private Delivery delivery;
}
