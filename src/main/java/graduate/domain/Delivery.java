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
@Table(name = "delivery")
public class Delivery implements Serializable{
	@Id
	@Column(length = 10)
	private String delyveryID;
	@Column(length = 10)
	private String orderID;
	@Column(length = 10)
	private String driverID;
	private int DeliveryTime;
	private double RestaurantPayment;
	private double CustomerFees;
	
//	Tạo trường dữ liệu có kiểu dữ liệu là datetime
	@Temporal(TemporalType.TIMESTAMP)
	private Date ConfimedOrder;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ReceivedOrder;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ArrivedRestaurant;
	@Temporal(TemporalType.TIMESTAMP)
	private Date tookOrder;
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivedCustomer;
	@Temporal(TemporalType.TIMESTAMP)
	private Date FinishedOrder;
	private String noteForDriver;
}
