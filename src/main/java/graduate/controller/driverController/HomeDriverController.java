package graduate.controller.driverController;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import graduate.domain.Customer;
import graduate.domain.Driver;
import graduate.domain.Order;
import graduate.service.CustomerService;
import graduate.service.DriverService;
import graduate.service.OrderService;
import graduate.utils.RedirectHelper;
@Controller
@RequestMapping("tfive/driver")
public class HomeDriverController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderService orderService;
	
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
	
	void fillAllOrder(ModelMap model) {
		List<Order> list=orderService.findAll();
		model.addAttribute("listOrder", list);
	}
	
	@GetMapping("home")
	public String viewHome(ModelMap model) {
		fillDriverInfo(model);
		fillAllOrder(model);
		
		return "driverUI/index";
	}
	

	@GetMapping("history-order")
	public String viewhistoryOrder(ModelMap model) {
		fillDriverInfo(model);
		fillAllOrder(model);
		
		return "driverUI/historyOrder";
	}
	
	@GetMapping("received-order/{orderID}")
	public ModelAndView receiveOrder(ModelMap model, @PathVariable("orderID") String orderID) {
		orderService.updateStatus("Đã nhận", orderID);
		orderService.receivedOrder(session.getAttribute("driverID").toString(), orderID);
		
		return RedirectHelper.redirectTo("/tfive/driver/home");
	}
}
