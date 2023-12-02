package graduate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {
  private String dishID;
  private String restaurantID;
  private String categoryID;
  private String name;
  private String description;
  private String img;
  private MultipartFile imageFile;
  private double price;
  private boolean status;
  private Boolean isEdit = false;
  private Date createdDate;
  
  private double min, max;
}
