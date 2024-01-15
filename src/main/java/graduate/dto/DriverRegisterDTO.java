package graduate.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverRegisterDTO implements Serializable {
	private String name;
	private String email;
	private String phoneNumber;
	private String identificationCard;
	private String img;
	private MultipartFile imageFile;
}
