package graduate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CartDTO {
	
  private String dishID;
//  private String customerID;
  private String name;
  private double discountPrice;
  private int quantity;
  private Boolean isEdit=false;
}
