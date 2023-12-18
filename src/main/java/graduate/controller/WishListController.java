package graduate.controller;

import java.util.List;
import java.util.Optional;

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
import graduate.domain.Customer;
import graduate.domain.Dish;
import graduate.domain.Wishlist;
import graduate.service.DishService;
import graduate.service.WishlistService;
import graduate.utils.RamdomID;
import graduate.utils.RedirectHelper;

@Controller
@RequestMapping("tfive")
public class WishListController {

	@Autowired
	private HttpSession session;

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private DishService dishService;

	@Autowired
	private WishlistService wishlistService;

	public void fillAllProduct(ModelMap model) {
		List<Dish> list= dishService.findAll();
		model.addAttribute("products", list);
	}

	void fillWishlist(ModelMap model) {
		try {
			List<Wishlist> list = wishlistService
					.findByCustomer_CustomerID(session.getAttribute("customerID").toString());
			model.addAttribute("listWishlist", list);
		} catch (Exception e) {
			return;
		}
	}

	@GetMapping("wishlist")
	public String viewWishList(ModelMap model) {
		fillWishlist(model);;
		fillAllProduct(model);
		return "customerUI/wishlist";
	}

	@GetMapping("wishlist/addToWishlist/{dishID}")
	public ModelAndView saveOneProduct(ModelMap model, @PathVariable("dishID") String productID) {
		Optional<Dish> opt = dishService.findById(productID);

		Wishlist entity = new Wishlist();
		entity.setCustomer(new Customer(session.getAttribute("customerID").toString()));
		entity.setDish(opt.get());

		wishlistService.save(entity);
		model.addAttribute("mess", "Product is saved");
		return RedirectHelper.redirectTo("/tfive/product/"+productID);
	}

	@GetMapping("wishlist/delete/{wishListId}")
	public ModelAndView delete(ModelMap model, @PathVariable("wishListId") String wishListId) {
		wishlistService.deleteById(wishListId);
		model.addAttribute("mess", "Đã xoá");

		return RedirectHelper.redirectTo("/tfive/wishlist");
	}
	
	@GetMapping("wishlist/deleteBy/{productId}")
	public ModelAndView deleteByProductIdAndCustomerId(ModelMap model, @PathVariable("productId") String productId) {
		wishlistService.deleteByProductIdAndCustomerId(productId, session.getAttribute("customerID").toString());

		return RedirectHelper.redirectTo("/tfive/wishlist");
	}
	

	@GetMapping("wishlist/delete-all/{customerID}")
	public ModelAndView deleteAll(ModelMap model,  @PathVariable("customerID") String customerID) {
		wishlistService.deleteByCustomer_CustomerID(customerID);
		model.addAttribute("mess", "Category id delete");

		return RedirectHelper.redirectTo("/tfive/wishlist");
	}
	
}
