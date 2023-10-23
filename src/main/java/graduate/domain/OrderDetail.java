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
@Table(name = "orderDetail")
public class OrderDetail implements Serializable{
	@Id
	@Column(length = 10)
	private String orderDetailID;
	@Column(length = 10)
	private String orderID;
	@Column(length = 10)
	private String dishID;
	private int quantity;
	private double totalAmount;
	
}
