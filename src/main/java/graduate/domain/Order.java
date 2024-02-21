package graduate.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
	
	@Column(columnDefinition = "nvarchar(100)")
	private String status;
	private Double totalPrice;
	private Integer totalQuantity;
	@Column(columnDefinition = "nvarchar(max)")
	private String noteForRestaurant;
	@Column(columnDefinition = "nvarchar(max)")
	private String noteForDriver;
	private Double shipMoney;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	@PrePersist
    public void prePersist() {
        Calendar calendar = Calendar.getInstance();
        orderDate= calendar.getTime();
    }
	
	@ManyToOne
	@JoinColumn(name = "restaurantID", referencedColumnName = "restaurantID")
	private Restaurant restaurant;	
	@ManyToOne
	@JoinColumn(name = "customerID", referencedColumnName = "customerID")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "driverID", referencedColumnName = "driverID")
	private Driver driver;
	@ManyToOne
    @JoinColumn(name = "voucherID", referencedColumnName = "voucherID")
    private Voucher voucher;

	@OneToMany(mappedBy = "orders" , cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
	@OneToOne(mappedBy = "orders" , cascade = CascadeType.ALL)
    private Delivery delivery;

	public Order(String orderID) {
		this.orderID = orderID;
	}
}
