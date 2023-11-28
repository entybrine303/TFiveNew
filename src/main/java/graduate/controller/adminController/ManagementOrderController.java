package graduate.controller.adminController;

import java.util.Date;
import java.util.List;
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

import graduate.domain.Dish;
import graduate.domain.Order;
import graduate.domain.OrderDetail;
import graduate.service.DeliveryService;
import graduate.service.OrderDetailService;
import graduate.service.OrderService;
import graduate.utils.RedirectHelper;
@Controller
@RequestMapping("tfive/admin/order")
public class ManagementOrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private DeliveryService deliveryService;
	
	void fillAllOrder(ModelMap model) {
		List<Order> list=orderService.findAll();
		model.addAttribute("listOrder", list);
	}

	public void fillOrder(ModelMap model, String orderID) {
		try {
			Optional<Order> order = orderService.findById(orderID);
			Order getOrder = order.get();
			model.addAttribute("order", getOrder);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}
	
	void fillProduct(ModelMap model) {
		List<OrderDetail> list=orderDetailService.findAll();
		model.addAttribute("orderDetail", list);
	}
	

	public void fillProductDetail(ModelMap model, String orderID) {
		try {
			Optional<Order> order = orderService.findById(orderID);
			
			Order getOrder = order.get();
			if (getOrder.getNoteForRestaurant() == null || getOrder.getNoteForRestaurant().equals(""))
				model.addAttribute("displayNote", false);
			else
				model.addAttribute("displayNote", true);
			
			model.addAttribute("order", getOrder);
			
			Date time=deliveryService.findLatestUpdateDate(orderID);
			model.addAttribute("time", time);

			fillOrderDetail(model, orderID, getOrder);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}

	public void fillOrderDetail(ModelMap model, String orderID, Order getOrder) {
		List<OrderDetail> list = orderDetailService.findByOrder_OrderID(orderID);
		model.addAttribute("listOrder", list);
		
/*		Kiểm tra order này có áp voucher hay k, nếu k thì set cho reducedPrice bằng 0,
			để tránh Thành tiền trên web bị fill là null */
		double reducedPrice=0;
		if (getOrder.getVoucher()!=null) {
			reducedPrice=getOrder.getVoucher().getReducedPrice();
		}
		int totalQuantity = 0;
		for (int i = 0; i < list.size(); i++) {
			totalQuantity += list.get(i).getQuantity();
		}

		model.addAttribute("totalQuantity", totalQuantity);
		model.addAttribute("totalMoney",
				getOrder.getTotalPrice() + getOrder.getShipMoney() - reducedPrice);
	}
	
	@GetMapping("view")
	public String viewOrder(ModelMap model) {
		fillAllOrder(model);
		
		return "restaurantUI/managementOrder";
	}
	
	@GetMapping("detail/{orderID}")
	public String viewProductDetail(ModelMap model, @PathVariable("orderID") String orderID) {
		fillOrder(model, orderID);
		fillProductDetail(model, orderID);
		return "restaurantUI/order-detail";
	}
	

	@GetMapping("updateStatus/{orderID}/{status}")
	public ModelAndView updateStatus(ModelMap model, @PathVariable("orderID") String orderID,
			@PathVariable("status") String status) {
		orderService.updateStatus(status, orderID);
		
		return RedirectHelper.redirectTo("/tfive/admin/order/detail/"+orderID);
	}
}
