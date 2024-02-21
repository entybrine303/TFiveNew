package graduate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDTO {
  private String voucherID;
  private String noteForR;
  
  
  private String noteForD;

}
