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
@Table(name = "restaurant")
public class Restaurant implements Serializable{
	@Id
	@Column(length = 10)
	private String restaurantID;
	@Column(length = 20)
	private String username;
	private String address;
	@Column(length = 10)
	private String phoneNumber;
	private String email;
	private String img;
	
}
