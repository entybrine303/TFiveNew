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
@Table(name = "dish")
public class Dish implements Serializable{
	@Id
	@Column(length = 10)
	private String driverID;
	@Column(length = 10)
	private String restaurantID;
	@Column(length = 10)
	private String categoryID;
	@Column(length = 50)
	private String name;
	private String description;
	private String img;
	private double price;
	private boolean status;
}
