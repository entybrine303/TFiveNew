package graduate.controller.adminController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import graduate.domain.Account;
import graduate.domain.Customer;
import graduate.domain.Dish;
import graduate.domain.Driver;
import graduate.dto.CustomerDTO;
import graduate.dto.DishDTO;
import graduate.dto.DriverDTO;
import graduate.dto.DriverRegisterDTO;
import graduate.service.AccountService;
import graduate.service.DriverRegisterService;
import graduate.service.DriverService;
import graduate.service.MailSenderService;
import graduate.utils.RamdomID;
import graduate.utils.RedirectHelper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.convert.DtoInstantiatingConverter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import graduate.domain.DriverRegister;

@Controller
@RequestMapping("tfive/admin/driver")
public class ManagementDriverController {
	@Autowired
	private DriverService driverService;
	

	@Autowired
	private AccountService accountService;
	@Autowired
	private HttpSession session;
	
	@Autowired
	MailSenderService mailSenderService;

	@Autowired
	private DriverRegisterService driverRegisterService;

	void fillToTable(ModelMap model) {
		List<Driver> list = driverService.findAll();
		model.addAttribute("drivers", list);
		List<DriverRegister> list2 = driverRegisterService.findAll();
		model.addAttribute("rDriver", list2);
	}

	@GetMapping("viewRegister")
	public String viewRegister(ModelMap model) {
		model.addAttribute("driver", new DriverDTO());
		return "customerUI/driver-register";
	}

	@GetMapping("view")
	public String viewDriver(ModelMap model) {
		fillToTable(model);
		return "restaurantUI/managementDriver";
	}

	@GetMapping("edit")
	public String EDIT(ModelMap model) {
		fillToTable(model);
		return "restaurantUI/update-driver";
	}

//	Cập nhật thông tin tài xế
	@PostMapping("update")
	public ModelAndView update(ModelMap model, @Valid @ModelAttribute("driver") DriverDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("customerUI/driver-register");
		}
		if (!driverService.existsById(dto.getDriverID())) {
			model.addAttribute("mess", "Bạn không được thay đổi số điện thoại");
			return new ModelAndView(viewDriver(model), model);
		}
		SimpleMailMessage msg = new SimpleMailMessage();
		Driver entity = new Driver();
		BeanUtils.copyProperties(dto, entity);
		entity.setDriverID(dto.getDriverID());
		entity.setWorkStatus(dto.getWorkStatus());
		Optional<Driver> driver=driverService.findById(dto.getDriverID());
		entity.setAccount(driver.get().getAccount());
		driverService.save(entity);
		model.addAttribute("mess", "Cập nhập thành công");
		model.addAttribute("driver", new DriverDTO());
		return new ModelAndView(viewDriver(model), model);

	}

	@GetMapping("confirm/{phoneNumber}")
	public ModelAndView confirm(ModelMap model, @PathVariable("phoneNumber") String phoneNumber) {
		Optional<DriverRegister> opt = driverRegisterService.findById(phoneNumber);
		if (opt.isPresent()) {
			Driver driver=new Driver();
			
			
			Account account=new Account();			
			account.setUsername(opt.get().getPhoneNumber());
			account.setPassword(RamdomID.generateRandomId());
			account.setRole("driver");
			
			accountService.save(account);
			
			driver.setDriverID("D-"+RamdomID.generateRandomId());
			driver.setPhoneNumber(opt.get().getPhoneNumber());
			driver.setEmail(opt.get().getEmail());
			driver.setName(opt.get().getName());
			driver.setIdentificationCard(opt.get().getIdentificationCard());
			driver.setAccount(account);	
			driverService.save(driver);
			driverRegisterService.deleteById(phoneNumber);
			
			String contentInMail="Chào "+ opt.get().getName()+", \n"
					+ "Đơn đăng kí trở thành đối tác tài xế của bạn đã được chấp thuận. \n"
					+ "Chúng tôi gửi bạn thông tin đăng nhập cho tài khoản Driver của bạn: \n"
					+ "Username: "+ account.getUsername()+"\n"
					+ "Mật khẩu: "+ account.getPassword();
			mailSenderService.sendEmail(opt.get().getEmail(), "ĐĂNG KÍ THÀNH CÔNG", contentInMail);
			
			
			
		}

		return RedirectHelper.redirectTo("/tfive/admin/driver/view");
	}

	@GetMapping("delete/{driverID}")
	public ModelAndView delete(ModelMap model, @PathVariable("driverID") String driverID) {
		driverService.deleteById(driverID);
		return RedirectHelper.redirectTo("/tfive/admin/driver/view");
	}
	
	@GetMapping("cancel-driver/{phoneNumber}")
	public ModelAndView cancelDriver(ModelMap model, @PathVariable("phoneNumber") String phoneNumber) {
		driverRegisterService.deleteById(phoneNumber);
		return RedirectHelper.redirectTo("/tfive/admin/driver/view");
	}

	@GetMapping("edit/{driverID}")
	public ModelAndView edit(ModelMap model, @PathVariable("driverID") String driverID) {
		fillToTable(model);
		Optional<Driver> opt = driverService.findById(driverID);
		DriverDTO dto = new DriverDTO();
		if (opt.isPresent()) {
			Driver entity = opt.get();
			dto.setIsEdit(true);
			BeanUtils.copyProperties(entity, dto);
			model.addAttribute("driver", dto);
			model.addAttribute("editMode", true);

			return new ModelAndView("restaurantUI/update-driver", model);
		}
		model.addAttribute("mess", "");

		return new ModelAndView("restaurantUI/managementDriver", model);
	}
	
	@PostMapping("search")
	public String searchCustomerByName(ModelMap model, @Valid @ModelAttribute("driver") DriverDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return "restaurantUI/managementCustomer";
		}
		List<Driver> list= driverService.findByNameContaining(dto.getName());
		model.addAttribute("drivers", list);
		return "restaurantUI/managementDriver";
	}
}
