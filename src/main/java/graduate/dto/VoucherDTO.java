package graduate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO {
  private String voucherID;
  private String restaurantID;
  private String description;
  private String img;
  private double reducedPrice;
  private double minimumPrice;
  private int quantity;
  private boolean VoucherType;
  private Boolean isEdit = false;
}
