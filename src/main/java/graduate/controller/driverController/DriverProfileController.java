package graduate.controller.driverController;

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

import graduate.domain.Driver;
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
	
}
