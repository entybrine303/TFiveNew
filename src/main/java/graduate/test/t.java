package graduate.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import graduate.service.MailSenderService;


@Controller
@RequestMapping("test")
public class t {
	@Autowired
	MailSenderService mailSenderService;
	
	@GetMapping()
	public String viewIndex(ModelMap model) {
		String contentInMail="Chào thành, \n"
				+ "Đơn đăng kí trở thành đối tác tài xế của bạn đã được chấp thuận. \n"
				+ "Chúng tôi gửi bạn thông tin đăng nhập cho tài khoản Driver của bạn: \n"
				+ "Username: "+ "\n"
				+ "Mật khẩu: ";
		mailSenderService.sendEmail("thanhnmpd06751@fpt.edu.vn", "Đăng kí thành công", contentInMail);
		
		
		
		return "customerUI/index";
	}
}
