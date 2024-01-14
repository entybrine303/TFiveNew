package graduate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import graduate.domain.Account;
import graduate.domain.Customer;
import graduate.dto.CustomerDTO;
import graduate.dto.LoginDTO;
import graduate.service.AccountService;
import graduate.service.CookieService;
import graduate.service.CustomerService;
import graduate.service.MailSenderService;
import graduate.utils.RamdomID;
import graduate.utils.RedirectHelper;

@Controller
@RequestMapping("tfive/account")
public class LoginController {

	@Autowired
	AccountService accountService;

	@Autowired
	CustomerService customerService;

	@Autowired
	MailSenderService mailSenderService;

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	CookieService cookieService;

	@GetMapping("login")
	public String viewLogin(ModelMap model) {

		model.addAttribute("register", new LoginDTO());
		
		return "customerUI/login";
	}

	@GetMapping("logout")
	public ModelAndView logout(ModelMap model) {

		session.invalidate();
		cookieService.remove("username");
		cookieService.remove("password");

		return RedirectHelper.redirectTo("/tfive/");
	}

	@PostMapping("pLogin")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") LoginDTO dto, BindingResult result,
			@RequestParam(value = "rememberMe", required = false) boolean rememberMe) {
		if (result.hasErrors()) {
			model.addAttribute("messL", "Sai dữ liệu, mời nhập lại");
			return new ModelAndView("customerUI/login", model);
		}

		Account account = accountService.login(dto.getUsername(), dto.getPassword());
		if (account == null) {
			model.addAttribute("messL", "Không tìm thấy thông tin đăng nhập");
			return new ModelAndView("customerUI/login", model);
		}

		session.setAttribute("username", account.getUsername());
		session.setAttribute("role", account.getRole());
		
		if (rememberMe == true) {
	        // Tạo một cookie
	        cookieService.add("username", dto.getUsername(), 1);
	        cookieService.add("password", dto.getPassword(), 1);
	        model.addAttribute("username", cookieService.get("username"));
	        model.addAttribute("password", cookieService.get("password"));
		}else {
			cookieService.remove("username");
			cookieService.remove("password");
		}

		if (account.getRole().equals("admin"))
			return RedirectHelper.redirectTo("/tfive/admin/category/view");
		if (account.getRole().equals("driver"))
			return RedirectHelper.redirectTo("/tfive/driver/home");

		System.out.println(session.getAttribute("role") + " Login");
		return RedirectHelper.redirectTo("/tfive/");
	}

	@PostMapping("/pRegister")
	public ModelAndView saveorUpdate(ModelMap model, @Valid @ModelAttribute("register") LoginDTO dto,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("messR", "Sai dữ liệu đầu vào");
			return new ModelAndView("customerUI/login");
		}

		if (accountService.existsById(dto.getUsername())) {
			model.addAttribute("messR", "Tên đăng nhập đã tồn tại");
			return new ModelAndView("customerUI/register");
		}

		if (!dto.getConfirmPassword().equals(dto.getPassword())) {
			model.addAttribute("messR", "Đăng kí thất bại! Mật khẩu không trùng khớp");
			return new ModelAndView("customerUI/register");
		}
		

		Account entity = new Account();

		BeanUtils.copyProperties(dto, entity);

//		Khi người dùng đăng kí ở form của người dùng, thì mặc định set role cho người dùng là 'user'
		entity.setRole("user");

		accountService.save(entity);

		/*
		 * Khi người dùng đăng kí thành công thì tự động set userID ngẫu nhiên vào bảng
		 * User, sau đó cũng lưu username vừa được đăng kí vào bảng User
		 */
		Customer user = new Customer();
		user.setCustomerID("U-" + RamdomID.generateRandomId());
		user.setAccount(new Account(entity.getUsername()));
		user.setSex(true);
		customerService.save(user);

		model.addAttribute("messL", "Đăng kí thành công");
		return new ModelAndView("customerUI/login");
	}

	@GetMapping("forgot-password")
	public String viewForgotPassword(ModelMap model) {

		model.addAttribute("register", new LoginDTO());
		return "customerUI/forgot-password";
	}

	@PostMapping("pForgotPass")
	public ModelAndView forgotPassword(ModelMap model, @Valid @ModelAttribute("account") CustomerDTO dto,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("messL", "Sai dữ liệu, mời nhập lại");
			return new ModelAndView("customerUI/login", model);
		}

		Customer customer = customerService.findByEmail(dto.getEmail());
		if (customer == null) {
			model.addAttribute("messL", "Không tìm thấy email này");
			return new ModelAndView("customerUI/forgot-password", model);
		}
		
		String contentInMail="Chào "+ customer.getName()+", \n"
				+ "Chúng tôi xin gửi bạn thông tin đăng nhập\n"
				+ "Vui lòng không cung cấp cho người khác để tránh bị chiếm đoạt tài khoản\n"
				+ "Username: "+ customer.getAccount().getUsername()+"\n"
				+ "Mật khẩu: "+ customer.getAccount().getPassword();
		mailSenderService.sendEmail(dto.getEmail(), "THÔNG TIN ĐĂNG NHẬP", contentInMail);

		model.addAttribute("messL", "Chúng tôi đã gửi thông tin đăng nhập về mail của bạn");

		return RedirectHelper.redirectTo("/tfive/account/login");
	}
}
