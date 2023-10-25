package graduate.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {
  private String driverID;
  private String restaurantID;
  private String categoryID;
  private String name;
  private String description;
  private String img;
  private double price;
  private boolean status;
}
