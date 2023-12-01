package graduate.controller.driverController;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import graduate.domain.Account;
import graduate.domain.Driver;
import graduate.dto.DriverDTO;
import graduate.service.DriverService;
@Controller
@RequestMapping("tfive/driver")
public class DriverProfileController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private DriverService driverService;
	
	void fillDriverInfo(ModelMap model) {
		try {
			Driver driver = driverService.findByUsername(session.getAttribute("username").toString());
			
			model.addAttribute("driver", driver);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}
	
	@GetMapping("profile")
	public String viewDriverProfile(ModelMap model) {
		fillDriverInfo(model);
		
		return "driverUI/profile";
	}
	
	@GetMapping("profile/detail")
	public String viewUpdateProfile(ModelMap model) {
		fillDriverInfo(model);
		
		return "driverUI/update-driver";
	}
	

//	Cập nhật thông tin tài xế
	@PostMapping("profile/update")
	public ModelAndView update(ModelMap model, @Valid @ModelAttribute("driver") DriverDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("driverUI/index");
		}
		if (!driverService.existsById(dto.getDriverID())) {
			model.addAttribute("mess", "Bạn không được thay đổi số điện thoại");
			return new ModelAndView(viewUpdateProfile(model), model);
		}
		Driver entity = new Driver();
		BeanUtils.copyProperties(dto, entity);
		entity.setWorkStatus(dto.getWorkStatus());
		entity.setConfirm(1);
		entity.setAccount(new Account(session.getAttribute("username").toString()));
		driverService.save(entity);
		model.addAttribute("mess", "Cập nhập thành công");
		model.addAttribute("driver", new DriverDTO());
		return new ModelAndView(viewDriverProfile(model), model);

	}
}
