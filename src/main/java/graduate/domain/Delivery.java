package graduate.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "delivery")
public class Delivery implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deliveryID;
//	Thời gian giao hàng
	private Integer DeliveryTime;
	
//	Tạo trường dữ liệu có kiểu dữ liệu là datetime
	
//	Xác nhận đơn
	@Temporal(TemporalType.TIMESTAMP)
	private Date ConfimedOrder;
//	Làm xong đơn
	@Temporal(TemporalType.TIMESTAMP)
	private Date FinishedOrder;
	// Tài xế nhận đơn
	@Temporal(TemporalType.TIMESTAMP)
	private Date ReceivedOrder;
//	Tài xế lấy đơn
	@Temporal(TemporalType.TIMESTAMP)
	private Date TookOrder;
//	Huỷ đơn
	@Temporal(TemporalType.TIMESTAMP)
	private Date CanceledOrder;
//	Tài xế giao xong đơn
	@Temporal(TemporalType.TIMESTAMP)
	private Date CompleteOrder;
	

	@ManyToOne
	@JoinColumn(name = "driverID", referencedColumnName = "driverID")
	private Driver driver;	
	
	@OneToOne
    @JoinColumn(name = "orderID", referencedColumnName = "orderID")
//	Khi join bảng, cần đặt tên trường trùng với tên bảng cần join *****
    private Order orders;
}
