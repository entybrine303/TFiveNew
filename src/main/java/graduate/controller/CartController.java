package graduate.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import graduate.domain.Cart;
import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.Dish;
import graduate.dto.CartDTO;
import graduate.dto.CategoryDTO;
import graduate.dto.DishDTO;
import graduate.repository.CustomerRepository;
import graduate.service.CartService;
import graduate.service.CustomerService;
import graduate.utils.CheckSession;
import graduate.utils.RamdomID;
@Controller
@RequestMapping("tfive")
public class CartController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CustomerService customerService;
	
	
	void fillToTable(ModelMap model) {
//		Tìm các sản phẩm được lưu trong giỏ hàng, tương ứng với customerID
		Customer customer=customerService.findByUsername(session.getAttribute("username").toString());
	
		List<Cart> list = cartService.findByCustomer_CustomerID(customer.getCustomerID());
		model.addAttribute("customerID", customer.getCustomerID());
		model.addAttribute("cartItems", list);
	}
	
	@GetMapping("cart")
	public String viewCart(ModelMap model) {
		CheckSession sub=new CheckSession();
		sub.checkUsername(request);
		sub.checkRole(request);
		
		fillToTable(model);
		
		return "customerUI/cart";
	}
	
	@PostMapping("cart/addToCart")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("cart") CartDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView(viewCart(model));
		}

		Cart entity = new Cart();
		BeanUtils.copyProperties(dto, entity);
		entity.setCartID("C-"+RamdomID.generateRandomId());
		entity.setTotalAmount(dto.getQuantity()*dto.getPrice());
		
		entity.setDish(new Dish(dto.getDishID()));
		
//		Lưu thông tin customer vào cart
		Customer customer=customerService.findByUsername(session.getAttribute("username").toString());
		entity.setCustomer(new Customer(customer.getCustomerID()));
		
		cartService.save(entity);
		model.addAttribute("mess", "Product is saved");
		return new ModelAndView(viewCart(model), model);
	}
	
	@GetMapping("cart/delete/{cartID}")
	public ModelAndView delete(ModelMap model, @PathVariable("cartID") String cartID) {
		cartService.deleteById(cartID);
		model.addAttribute("mess", "Category id delete");
		return new ModelAndView(viewCart(model), model);
	}

	@GetMapping("cart/delete-all/{customerID}")
	public ModelAndView deleteAll(ModelMap model,  @PathVariable("customerID") String customerID) {
		cartService.deleteByCustomerCustomerID(customerID);
		model.addAttribute("mess", "Category id delete");
		return new ModelAndView(viewCart(model), model);
	}
	
}
