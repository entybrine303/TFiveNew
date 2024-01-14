package graduate.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import graduate.domain.Driver;
import graduate.domain.DriverRegister;
import graduate.dto.DriverDTO;
import graduate.dto.DriverRegisterDTO;
import graduate.service.AccountService;
import graduate.service.DriverRegisterService;
import graduate.service.DriverService;
import graduate.service.MailSenderService;

@Controller
@RequestMapping("tfive")
public class OrtherController {
	@Autowired
	MailSenderService mailSenderService;

	@Autowired
	private DriverRegisterService driverRegisterService;
	
	@Autowired
	DriverService driverService;

	
	@Autowired
	private HttpServletRequest request;
	
	
	@GetMapping("blog")
	public String viewBlog(ModelMap model) {
		
		return "customerUI/blog";
	}
	
	@GetMapping("about")
	public String viewAbout(ModelMap model) {
		
		return "customerUI/about";
	}
	
	@GetMapping("contact")
	public String viewContact(ModelMap model) {
		
		return "customerUI/contact";
	}
	
	@GetMapping("privacy-policy")
	public String viewPrivacypolicy(ModelMap model) {
		
		return "customerUI/privacy-policy";
	}
	
	@GetMapping("error-page")
	public String viewError(ModelMap model) {
		return "customerUI/404";
	}
	
	@GetMapping("faq")
	public String viewFaq(ModelMap model) {
		return "customerUI/faq";
	}
	
//	Lưu form đăng kí của tài xế
	@PostMapping("account/driverRegister/saveOrUpdate")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("driver") DriverRegisterDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			// Lấy danh sách tất cả các lỗi
		    List<ObjectError> errors = result.getAllErrors();
		    
		    // In thông tin về lỗi
		    for (ObjectError error : errors) {
		        System.out.println("Error: " + error.getDefaultMessage());
		    }
			return new ModelAndView("customerUI/driver-register");
		}

		if (driverRegisterService.existsById(dto.getPhoneNumber())) {
			model.addAttribute("messfail", "Tài khoản đã tồn tại");
			System.out.println("Tài khoản đã tồn tại");
		}
		else {
			List<Driver> drivers = driverService.findAll();
			for (Driver driver : drivers) {
				if (driver.getPhoneNumber().equals(dto.getPhoneNumber())) {
					model.addAttribute("messfail", "SDT trùng");
				}else {
					DriverRegister entity = new DriverRegister();
					BeanUtils.copyProperties(dto, entity);
					driverRegisterService.save(entity);
					model.addAttribute("mess", "Tài khoản đã được lưu thành công");
					model.addAttribute("driver", new DriverDTO());
				}
			}
		}
		return new ModelAndView("customerUI/driver-register", model);
	}
}
