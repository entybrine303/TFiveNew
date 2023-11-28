package graduate.controller.adminController;

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
import graduate.service.RestaurantService;
import graduate.service.CustomerService;
import graduate.service.OrderService;
import graduate.service.StorageService;
import graduate.utils.RamdomID;

@Controller
@RequestMapping("tfive/admin")
public class ProfileAdminController {

	@Autowired
	private HttpSession session;

	@Autowired
	private StorageService storageService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private AccountService accountService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private OrderService orderService;

	public void fillRestaurantInfo(ModelMap model) {
		try {
			Restaurant restaurant= restaurantService.findByUsername(session.getAttribute("username").toString());
			model.addAttribute("restaurant", restaurant);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}

	@GetMapping("/profile")
	public String viewProfile(ModelMap model) {

		fillRestaurantInfo(model);

		return "restaurantUI/profile";
	}

	@PostMapping("profile/update")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("restaurant") CustomerDTO dto, BindingResult result) {
		
		if (result.hasErrors()) {
			return new ModelAndView(viewProfile(model));
		}

		Restaurant entity = new Restaurant();
		BeanUtils.copyProperties(dto, entity);

		entity.setAccount(new Account(session.getAttribute("username").toString()));
		restaurantService.save(entity);
		model.addAttribute("mess", "Profile is updated");

		return new ModelAndView(viewProfile(model), model);
	}

//	@GetMapping("account/change-password")
//	public String viewChangePass(ModelMap model) {
//		Optional<Account> account = accountService.findById(session.getAttribute("username").toString());
//		LoginDTO dto = new LoginDTO();
//
//		if (account.isPresent()) {
//			Account entity = account.get();
//			BeanUtils.copyProperties(entity, dto);
//			dto.setPassword(null);
//			dto.setConfirmPassword(null);
//
//			model.addAttribute("account", dto);
//			return "customerUI/change-password";
//		}
//		return "customerUI/change-password";
//	}
//
//	@PostMapping("account/change-password/pChangePassword")
//	public ModelAndView changePass(ModelMap model, @Valid @ModelAttribute("account") LoginDTO dto,
//			BindingResult result) {
//		if (result.hasErrors()) {
//			return new ModelAndView(viewChangePass(model));
//		}
//		if (!dto.getConfirmPassword().equals(dto.getNewPassword())) {
//			model.addAttribute("mess", "Đổi mật khẩu thất bại");
//			return new ModelAndView(viewChangePass(model));
//		}
//
//		Account entity = new Account();
//		Optional<Account> acc = accountService.findById(session.getAttribute("username").toString());
//
//		BeanUtils.copyProperties(dto, entity);
//
//		entity.setPassword(dto.getNewPassword());
//		entity.setRole(session.getAttribute("role").toString());
//
//		accountService.save(entity);
//
//		model.addAttribute("mess", "Đổi mật khẩu thành công");
//		return new ModelAndView(viewChangePass(model));
//	}
}
