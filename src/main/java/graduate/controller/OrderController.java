package graduate.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.expression.Calendars;

import graduate.controller.commonController.CommonController;
import graduate.domain.Cart;
import graduate.domain.Customer;
import graduate.domain.Delivery;
import graduate.domain.Dish;
import graduate.domain.Order;
import graduate.domain.OrderDetail;
import graduate.domain.Restaurant;
import graduate.domain.Voucher;
import graduate.dto.CartDTO;
import graduate.dto.CheckoutDTO;
import graduate.dto.VoucherDTO;
import graduate.service.CartService;
import graduate.service.CustomerService;
import graduate.service.DeliveryService;
import graduate.service.DishService;
import graduate.service.OrderDetailService;
import graduate.service.OrderService;
import graduate.service.VoucherService;
import graduate.utils.RamdomID;
import graduate.utils.RedirectHelper;

@Controller
@RequestMapping("tfive")
public class OrderController {

	@Autowired
	private HttpSession session;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private DishService dishService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private VoucherService voucherService;

	@Autowired
	private DeliveryService deliveryService;

	@Autowired
	private RedirectHelper redirectHelper;

	void fillCustomerInfo(ModelMap model) {
		try {
			Customer customer = customerService.findByUsername(session.getAttribute("username").toString());
			model.addAttribute("customer", customer);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}

	void fillVoucher(ModelMap model) {
		List<Voucher> list = voucherService.findAll();
		model.addAttribute("vouchers", list);
	}

	public void fillProduct(ModelMap model, String orderID) {
		try {
			Optional<Order> order = orderService.findById(orderID);
			Order getOrder = order.get();
			if (getOrder.getNoteForRestaurant() == null)
				model.addAttribute("displayNote", false);
			else
				model.addAttribute("displayNote", true);
			model.addAttribute("order", getOrder);

			List<OrderDetail> list = orderDetailService.findByOrder_OrderID(orderID);
			model.addAttribute("listOrder", list);

			double reducedPrice = 0;
			if (getOrder.getVoucher() != null) {
				reducedPrice = getOrder.getVoucher().getReducedPrice();
			}
			int totalQuantity = 0;
			for (int i = 0; i < list.size(); i++) {
				totalQuantity += list.get(i).getQuantity();
			}

			model.addAttribute("totalQuantity", totalQuantity);
			model.addAttribute("totalMoney", getOrder.getTotalPrice() + getOrder.getShipMoney() - reducedPrice);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}

	public void fillDelivery(ModelMap model, String orderID) {
		try {
			Delivery delivery = deliveryService.findByOrder_OrderId(orderID);

			model.addAttribute("took", delivery.getTookOrder());
			model.addAttribute("complete", delivery.getCompleteOrder());
			Date time = deliveryService.findLatestUpdateDate(orderID);
			model.addAttribute("time", time);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("mess", e.getMessage());
		}
	}

	double calculaterTotalMoney(ModelMap model) {
		double totalPrice, shipMoney, reducedPrice;
		totalPrice = (double) model.getAttribute("cartTotalPrice");
		shipMoney = (int) 20000;
		if (model.getAttribute("reducedPrice") == null) {
			reducedPrice = 0;
		} else {
			reducedPrice = (double) model.getAttribute("reducedPrice");
		}
		double totalMoney = totalPrice + shipMoney - reducedPrice;
		return totalMoney;
	}

	@GetMapping("checkout")
	public String viewCheckout(ModelMap model) {
		fillCustomerInfo(model);
		fillVoucher(model);

		model.addAttribute("shipMoney", 20000);
		model.addAttribute("totalMoney", calculaterTotalMoney(model));

//		Ở đây kiểm tra xem attribute "ipVoucher", nếu bằng null thì gán trực tiếp giá trị là null, để tránh lỗi phát sinh
		if (model.getAttribute("ipVoucherID") == null)
			model.addAttribute("ipVoucherID", null);
		return "customerUI/checkout";
	}

	@PostMapping("checkout/findVoucher")
	public ModelAndView findVoucher(ModelMap model, @Valid @ModelAttribute("voucher") VoucherDTO dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("customerUI/checkout");
		}
		Optional<Voucher> opt = voucherService.findById(dto.getVoucherID());
		
		dto.setVoucherID(dto.getVoucherID());
		model.addAttribute("voucher", dto);
		if (!opt.isPresent()) {
			model.addAttribute("mess", "Không tìm thấy voucher này");
			return new ModelAndView(viewCheckout(model), model);
		}

		if ((double) model.getAttribute("cartTotalPrice") < opt.get().getMinimumPrice()) {
			model.addAttribute("mess", "Đơn hàng phải tối thiểu " + opt.get().getMinimumPrice()+"đ");
			return new ModelAndView(viewCheckout(model), model);
		}

		model.addAttribute("reducedPrice", opt.get().getReducedPrice());
		model.addAttribute("ipVoucherID",dto.getVoucherID());
		return new ModelAndView(viewCheckout(model), model);
	}

	@PostMapping("checkout/finish")
	public ModelAndView order(ModelMap model, @Valid @ModelAttribute("customer") CheckoutDTO dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("customerUI/checkout");
		}
		String orderID = "O-" + RamdomID.generateRandomId();

		try {
			insertToOrderTbl(model, dto, result, orderID);
			insertToOrderDetailTbl(model, dto, result, orderID);
			insertToDeliveryTbl(model, dto, result, orderID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		voucherService.decreaseQuantityByOne(dto.getVoucherID());
		cartService.deleteByCustomer_CustomerID(session.getAttribute("customerID").toString());
		return RedirectHelper.redirectTo("/tfive/my-order");

	}

	@GetMapping("cancel-order/{orderID}")
	public ModelAndView cancelOrder(ModelMap model, @PathVariable("orderID") String orderID) {
		orderService.updateStatus("Đã huỷ", orderID);
		return RedirectHelper.redirectTo("/tfive/order-detail/" + orderID);
	}

	void insertToOrderTbl(ModelMap model, CheckoutDTO dto, BindingResult result, String orderID) {
		try {
			Order entity = new Order();
			BeanUtils.copyProperties(dto, entity);
			entity.setOrderID(orderID);
			entity.setShipMoney((double) 20000);
			entity.setStatus("Đã đặt");
			entity.setTotalPrice(calculaterTotalMoney(model));

			int totalQuantity = (int) model.getAttribute("cartTotalQuantity");
			entity.setTotalQuantity(totalQuantity);

			// Lưu thông tin customer vào đơn hàng
			entity.setCustomer(new Customer(session.getAttribute("customerID").toString()));

			entity.setRestaurant(new Restaurant("R01"));
			entity.setNoteForRestaurant(dto.getNoteForR());
			entity.setNoteForDriver(dto.getNoteForD());

			/*
			 * Nếu dto.getVoucherID có giá trị hoặc khác một chuỗi rỗng mới gán giá trị vào
			 * db Nếu k đặt điều kiện khác chuỗi rỗng, nó sẽ lấy giá trị rỗng insert vào db,
			 * từ đó phát sinh ra lỗi k trùng khớp id(relationship)
			 */
			if (dto.getVoucherID() != null && !dto.getVoucherID().equals(""))
				entity.setVoucher(new Voucher(dto.getVoucherID()));

			orderService.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void insertToOrderDetailTbl(ModelMap model, CheckoutDTO dto, BindingResult result, String orderID) {
		try {
			List<Cart> list = cartService.findByCustomer_CustomerID(session.getAttribute("customerID").toString());
			for (Cart cart : list) {
				Dish product = dishService.findById(cart.getDish().getDishID()).get();
				OrderDetail entity = new OrderDetail();
				entity.setOrders(new Order(orderID));
				entity.setDish(product);
				entity.setQuantity(cart.getQuantity());
				entity.setTotalAmount(cart.getTotalAmount());
				orderDetailService.save(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void insertToDeliveryTbl(ModelMap model, CheckoutDTO dto, BindingResult result, String orderID) {
		try {
			Delivery entity = new Delivery();

			entity.setOrders(new Order(orderID));

			deliveryService.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@GetMapping("order-detail/{orderID}")
	public String viewOrderDetail(ModelMap model, @PathVariable("orderID") String orderID) {
		fillProduct(model, orderID);

		return "customerUI/order-detail";
	}

	@GetMapping("my-order")
	public String viewOrder(ModelMap model) {
		List<Order> list = orderService.findByCustomer_CustomerID(session.getAttribute("customerID").toString());

		List<Order> list1 = list.stream().sorted((o1, o2) -> o2.getOrderDate().compareTo(o1.getOrderDate()))
				.collect(Collectors.toList());
		model.addAttribute("listOrder", list1);
		return "customerUI/my-order";
	}
}
