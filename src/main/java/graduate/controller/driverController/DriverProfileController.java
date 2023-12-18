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
import graduate.dto.LoginDTO;
import graduate.service.AccountService;
import graduate.service.DriverService;
import graduate.utils.RedirectHelper;
@Controller
@RequestMapping("tfive/driver")
public class DriverProfileController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private DriverService driverService;
	

	@Autowired
	private AccountService accountService;
	
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
	
	@GetMapping("profile/change-password")
	public String viewChangePassword(ModelMap model) {
		Optional<Account> account = accountService.findById(session.getAttribute("username").toString());
		LoginDTO dto = new LoginDTO();

		if (account.isPresent()) {
			Account entity = account.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setPassword(null);
			dto.setConfirmPassword(null);

			model.addAttribute("account", dto);
			return "driverUI/change-password";
		}
		return "driverUI/change-password";
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
		entity.setAccount(new Account(session.getAttribute("username").toString()));
		driverService.save(entity);
		model.addAttribute("mess", "Cập nhập thành công");
		model.addAttribute("driver", new DriverDTO());
		return RedirectHelper.redirectTo("/tfive/driver/profile/detail");

	}

	@PostMapping("profile/change-password/pChangePassword")
	public ModelAndView changePass(ModelMap model, @Valid @ModelAttribute("account") LoginDTO dto,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("error", "Lỗi dữ liệu đầu vào!");
			return new ModelAndView(viewChangePassword(model));
		}
		
		Optional<Account> acc = accountService.findById(session.getAttribute("username").toString());

		if (!dto.getPassword().equals(acc.get().getPassword())) {
			model.addAttribute("error", "Mật khẩu cũ sai!");
			
			return new ModelAndView(viewChangePassword(model));
		}
		if (dto.getNewPassword().equals(dto.getPassword())) {
			model.addAttribute("error", "Mật khẩu mới trùng với mật khẩu cũ!");
			
			return new ModelAndView(viewChangePassword(model));
		}
		if (!dto.getConfirmPassword().equals(dto.getNewPassword())) {
			model.addAttribute("error", "Mật khẩu không trùng khớp");
			return new ModelAndView(viewChangePassword(model));
		}
		
		Account entity = new Account();

		BeanUtils.copyProperties(dto, entity);

		entity.setPassword(dto.getNewPassword());
		entity.setRole(session.getAttribute("role").toString());

		accountService.save(entity);

		model.addAttribute("mess", "Đổi mật khẩu thành công");
		return new ModelAndView(viewChangePassword(model));
	}
}
