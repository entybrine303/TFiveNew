package graduate.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer implements Serializable{
	@Id
	@Column(length = 10)
	private String customerID;
	@Column(length = 50, columnDefinition = "nvarchar(100)")
	private String name;
	private Boolean sex;
	@Column(length = 10)
	private String phoneNumber;
	private String email;
	@Column(columnDefinition = "nvarchar(max)")
	private String address;
	private String img;
	

	@OneToMany(mappedBy = "customer")
    private List<Cart> carts;
	@OneToMany(mappedBy = "customer")
    private List<Order> orders;
	
	@OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Account account;

	@OneToMany(mappedBy = "customer")
    private List<Wishlist> wishlists;

	public Customer(String customerID) {
		this.customerID = customerID;
	}
}
