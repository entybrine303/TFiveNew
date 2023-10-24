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
@Table(name = "driver")
public class Driver implements Serializable{
	@Id
	@Column(length = 10)
	private String driverID;
	@Column(length = 20)
	private String username;
	@Column(length = 10)
	private String licensePlates;
	@Column(length = 50)
	private String name;
	private Boolean sex;
	@Column(length = 10)
	private String phoneNumber;
	private String email;
	private Boolean workStatus;
	@Column(length = 12)
	private String identificationCard;
	private String img;
	
}
