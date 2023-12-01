package graduate.controller.adminController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import graduate.domain.Account;
import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.Driver;
import graduate.dto.CategoryDTO;
import graduate.dto.CustomerDTO;
import graduate.dto.DriverDTO;
import graduate.service.CustomerService;
import graduate.service.StorageService;
import graduate.utils.RamdomID;
import graduate.utils.RedirectHelper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("tfive/admin/customer")
public class ManagementCustomerController {
	@Autowired
	private HttpSession session;

	@Autowired
	private StorageService storageService;

	@Autowired
	private CustomerService customerService;

	public void fillToTable(ModelMap model) {
		List<Customer> list = customerService.findAll();
		model.addAttribute("customers", list);
	}

	@GetMapping("view")
	public String viewForm(ModelMap model) {
		fillToTable(model);
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryID("C-" + RamdomID.generateRandomId());
		model.addAttribute("customer", categoryDTO);
		return "restaurantUI/managementCustomer";
	}

	@GetMapping("delete/{customerId}")
	public ModelAndView delete(ModelMap model, @PathVariable("customerId") String customerId) {
		customerService.deleteById(customerId);
		model.addAttribute("mess", "Customer id delete");
		return RedirectHelper.redirectTo("tfive/admin/customer/view");
	}

	@GetMapping("edit/{customerId}")
	public ModelAndView edit(ModelMap model, @PathVariable("customerId") String customerId) {
		fillToTable(model);
		Optional<Customer> opt = customerService.findById(customerId);
		CustomerDTO dto = new CustomerDTO();

		if (opt.isPresent()) {
			Customer entity = opt.get();
			dto.setIsEdit(true);
			BeanUtils.copyProperties(entity, dto);
			model.addAttribute("customer", dto);
			model.addAttribute("editMode", true);
			return new ModelAndView("restaurantUI/update-user", model);
		}
		model.addAttribute("mess", "Dish is not existed");

		return new ModelAndView("restaurantUI/managementDish", model);
	}

	@PostMapping("update")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("customer") CustomerDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView(viewForm(model));
		}

		Customer entity = new Customer();
		BeanUtils.copyProperties(dto, entity);

		Account account = new Account(session.getAttribute("username").toString());
		entity.setAccount(account);

		customerService.save(entity);
		model.addAttribute("mess", "Product is saved");

		return RedirectHelper.redirectTo("/tfive/admin/customer/edit/"+dto.getCustomerID());
	}
}
