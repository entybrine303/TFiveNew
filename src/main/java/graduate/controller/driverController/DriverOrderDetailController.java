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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import graduate.controller.adminController.ManagementOrderController;
import graduate.domain.Order;
import graduate.service.OrderService;
import graduate.utils.RedirectHelper;
@Controller
@RequestMapping("tfive/driver")
public class DriverOrderDetailController {
	@Autowired
	ManagementOrderController managementOrderController;

	@Autowired
	private OrderService orderService;
	
	@GetMapping("detail/{orderID}")
	public String viewProductDetail(ModelMap model, @PathVariable("orderID") String orderID) {
		managementOrderController.fillOrder(model, orderID);
		managementOrderController.fillProductDetail(model, orderID);
		return "driverUI/orderDetail";
	}
	
	@GetMapping("updateStatus/{orderID}/{status}")
	public ModelAndView updateStatus(ModelMap model, @PathVariable("orderID") String orderID,
			@PathVariable("status") String status) {
		orderService.updateStatus(status, orderID);
		
		return RedirectHelper.redirectTo("/tfive/driver/detail/"+orderID);
	}
}
