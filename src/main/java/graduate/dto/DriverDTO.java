package graduate.dto;

import graduate.domain.Account;
import graduate.domain.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO implements Serializable {
    private String driverID;
    private String licensePlates;
    private String name;
    private String phoneNumber;
    private Boolean sex;
//    private String phoneNumber;
    private String email;
    private String motorbike;
    private Boolean workStatus;
    private String identificationCard;
    private String img;
    private MultipartFile imageFile;
    private List<Delivery> deliveries;
    private String username;
    private int confirm = 0;
    private Boolean isEdit = false;
}
