package graduate.controller.commonController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import graduate.domain.Cart;
import graduate.domain.Customer;
import graduate.service.CartService;
import graduate.service.CustomerService;

@ControllerAdvice
public class CommonController {

	@Autowired
	private HttpSession session;

	@Autowired
	private CartService cartService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CustomerService customerService;

	@ModelAttribute
	public void globalAttributes(ModelMap model) {
		if (session.getAttribute("username") == null) {
			return;
		}
		checkUsername(request);
		checkRole(request);
		fillCart(model);
	}

	void fillCart(ModelMap model) {
		try {
//		Phương thức này chỉ thực hiện khi người dùng đăng nhập với role là user
			if (session.getAttribute("role").toString().equals("user")) {
//		Tìm các sản phẩm được lưu trong giỏ hàng, tương ứng với customerID
				Customer customer = customerService.findByUsername(session.getAttribute("username").toString());
				
				Long numbersProduct=cartService.countByCustomerID(customer.getCustomerID());
				model.addAttribute("numbersProduct", numbersProduct);
				
				List<Cart> list = cartService.findByCustomer_CustomerID(customer.getCustomerID());
				model.addAttribute("customerID", customer.getCustomerID());
				
//		Tính tổng tiền của các sản phẩm đã được lưu trong giỏ hàng
				double totalPrice = 0;
				for (int i = 0; i < list.size(); i++) {
					totalPrice += list.get(i).getTotalAmount();
				}
				
				if (list.isEmpty()) {
					model.addAttribute("cartItems", null);
				} else {
					model.addAttribute("cartItems", list);
					model.addAttribute("cartTotalPrice", totalPrice);
				}
			}
			else {
				return;
			}
			
		} catch (Exception e) {
			
			return;
		}
	}

//	Dùng để kiểm tra xem username đã được khởi tạo trong session hay chưa, chưa thì khởi tạo và gán giá trị bằng null
	void checkUsername(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username == null) {
			username = null; // Gán giá trị null nếu chưa được khởi tạo
			session.setAttribute("username", username);
		}

	}

//	Dùng để kiểm tra xem role đã được khởi tạo trong session hay chưa, chưa thì khởi tạo và gán giá trị bằng null
	void checkRole(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role == null) {
			role = "guest"; 
			session.setAttribute("role", role);
		}

	}

}
