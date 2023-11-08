package graduate.controller;

import javax.servlet.http.HttpServletRequest;
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

import graduate.utils.CheckSession;
@Controller
@RequestMapping("tfive")
public class OrtherController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	CheckSession sub=new CheckSession();
	
	@GetMapping("blog")
	public String viewBlog(ModelMap model) {
		sub.checkUsername(request);
		sub.checkRole(request);
		
		return "customerUI/blog";
	}
	
	@GetMapping("about")
	public String viewAbout(ModelMap model) {
		sub.checkUsername(request);
		sub.checkRole(request);
		
		return "customerUI/about";
	}
	
	@GetMapping("contact")
	public String viewContact(ModelMap model) {
		sub.checkUsername(request);
		sub.checkRole(request);
		
		return "customerUI/contact";
	}
	
	@GetMapping("privacy-policy")
	public String viewPrivacypolicy(ModelMap model) {
		sub.checkUsername(request);
		sub.checkRole(request);
		
		return "customerUI/privacy-policy";
	}
	
	@GetMapping("error-page")
	public String viewError(ModelMap model) {
		sub.checkUsername(request);
		sub.checkRole(request);
		
		return "customerUI/404";
	}
	
	@GetMapping("faq")
	public String viewFaq(ModelMap model) {
		sub.checkUsername(request);
		sub.checkRole(request);
		
		return "customerUI/faq";
	}
	
}
