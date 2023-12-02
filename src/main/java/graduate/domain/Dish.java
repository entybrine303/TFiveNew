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
@Table(name = "dish")
public class Dish implements Serializable {
	@Id
	@Column(length = 10)
	private String dishID;
	@Column(length = 50, columnDefinition = "nvarchar(100)")
	private String name;
	@Column(columnDefinition = "nvarchar(max)")
	private String description;
	private String img;
	private Double price;
	private Boolean status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@PrePersist
    public void prePersist() {
        Calendar calendar = Calendar.getInstance();
        createdDate = calendar.getTime();
    }

	@ManyToOne
	@JoinColumn(name = "restaurantID", referencedColumnName = "restaurantID")
	private Restaurant restaurant;
	@ManyToOne
	@JoinColumn(name = "categoryID", referencedColumnName = "categoryID")
	private Category category;
	
	@OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
	@OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Cart> carts;
	@OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<Wishlist> wishlists;
	
	public Dish(String dishID) {
		this.dishID = dishID;
	}
}
