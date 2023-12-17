package graduate.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.Dish;
import graduate.domain.Order;
import graduate.domain.Restaurant;
import graduate.dto.CustomerDTO;
import graduate.dto.DishDTO;
import graduate.dto.LoginDTO;
import graduate.service.AccountService;
import graduate.service.CustomerService;
import graduate.service.OrderService;
import graduate.service.StorageService;
import graduate.utils.RamdomID;

@Controller
@RequestMapping("tfive")
public class AccountController {

	@Autowired
	private HttpSession session;

	@Autowired
	private StorageService storageService;


	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountService accountService;


	public void fillCustomerInfo(ModelMap model) {
		try {
			Customer customer = customerService.findByUsername(session.getAttribute("username").toString());
			if (customer.getSex() == null) {
				customer.setSex(true);
			}
			model.addAttribute("customer", customer);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}

	@GetMapping("profile")
	public String viewProfile(ModelMap model) {

		fillCustomerInfo(model);

		return "customerUI/profile";
	}

	@PostMapping("profile/saveOrUpdate")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("customer") CustomerDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView(viewProfile(model));
		}

		Customer entity = new Customer();
		BeanUtils.copyProperties(dto, entity);

		if (!dto.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			entity.setImg(storageService.getStoredFileName(dto.getImageFile(), uuString));
			storageService.storeImageWithResize(dto.getImageFile(), entity.getImg(), 209, 171);
		}

		Account account = new Account(session.getAttribute("username").toString());
		entity.setAccount(account);

		customerService.save(entity);

		return new ModelAndView(viewProfile(model), model);
	}

	@GetMapping("account/change-password")
	public String viewChangePass(ModelMap model) {
		Optional<Account> account = accountService.findById(session.getAttribute("username").toString());
		LoginDTO dto = new LoginDTO();

		if (account.isPresent()) {
			Account entity = account.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setPassword(null);
			dto.setConfirmPassword(null);

			model.addAttribute("account", dto);
			return "customerUI/change-password";
		}
		return "customerUI/change-password";
	}


	@PostMapping("account/change-password/pChangePassword")
	public ModelAndView changePass(ModelMap model, @Valid @ModelAttribute("account") LoginDTO dto,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("error", "Lỗi dữ liệu đầu vào!");
			return new ModelAndView(viewChangePass(model));
		}
		
		Optional<Account> acc = accountService.findById(session.getAttribute("username").toString());

		if (!dto.getPassword().equals(acc.get().getPassword())) {
			model.addAttribute("error", "Mật khẩu cũ sai!");
			
			return new ModelAndView(viewChangePass(model));
		}
		if (dto.getNewPassword().equals(dto.getPassword())) {
			model.addAttribute("error", "Mật khẩu mới trùng với mật khẩu cũ!");
			
			return new ModelAndView(viewChangePass(model));
		}
		if (!dto.getConfirmPassword().equals(dto.getNewPassword())) {
			model.addAttribute("error", "Mật khẩu không trùng khớp");
			return new ModelAndView(viewChangePass(model));
		}
		
		Account entity = new Account();

		BeanUtils.copyProperties(dto, entity);

		entity.setPassword(dto.getNewPassword());
		entity.setRole(session.getAttribute("role").toString());

		accountService.save(entity);

		model.addAttribute("mess", "Đổi mật khẩu thành công");
		return new ModelAndView(viewChangePass(model));
	}
}
