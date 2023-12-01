package graduate.dto;


import graduate.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	private String customerID;
	private String restaurantID;
  private String address;
  private String email;
  private String img;
  private String name;
  private MultipartFile imageFile;
  private String phoneNumber;
  private Boolean sex;
  private Boolean isEdit = false;
  private String username;
}
