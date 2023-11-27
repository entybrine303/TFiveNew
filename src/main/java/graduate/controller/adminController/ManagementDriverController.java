package graduate.controller.adminController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import graduate.domain.Dish;
import graduate.domain.Driver;
import graduate.dto.DishDTO;
import graduate.dto.DriverDTO;
import graduate.service.DriverService;
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

import graduate.domain.DriverRegister;
import graduate.service.DriverRegisterService;
@Controller
@RequestMapping("tfive/admin/driver")
public class ManagementDriverController {
	@Autowired
	private DriverService driverRegisterService;
	
	void fillToTable(ModelMap model) {
		List<Driver> list = driverRegisterService.findAll();
		model.addAttribute("driver", list);
	}

	@GetMapping("viewRegister")
	public String viewRegister(ModelMap model) {
		model.addAttribute("driver", new DriverDTO());
		return "customerUI/driver-register";
	}
	@GetMapping("view")
	public String viewDriver(ModelMap model) {
		fillToTable(model);
		return "restaurantUI/managementDriver";
	}
	@GetMapping("edit")
	public String EDIT(ModelMap model) {
		fillToTable(model);
		return "customerUI/update-driver";
	}
	@PostMapping("saveOrUpdate")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("driver") DriverDTO dao,
									 BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("customerUI/driver-register");
		}
		if (driverRegisterService.existsById(dao.getDriverID())) {
			model.addAttribute("mess", "ID này đã tồn tại. Vui lòng chọn một ID khác.");
			return new ModelAndView(viewDriver(model), model);
		}

		Driver entity = new Driver();
		BeanUtils.copyProperties(dao, entity);
		entity.setConfirm(0);
		driverRegisterService.save(entity);
		model.addAttribute("mess", "Tài khoản đã được lưu thành công");
		model.addAttribute("driver", new DriverDTO());
		return new ModelAndView("customerUI/driver-register", model);
	}

	@PostMapping("update")
	public ModelAndView update(ModelMap model, @Valid @ModelAttribute("driver") DriverDTO dao,
									 BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("customerUI/driver-register");
		}
		if (!driverRegisterService.existsById(dao.getDriverID())) {
			model.addAttribute("mess", "Bạn không được thay đổi số điện thoại");
			return new ModelAndView(viewDriver(model), model);
		}
		Driver entity = new Driver();
		BeanUtils.copyProperties(dao, entity);
		entity.setConfirm(1);
		driverRegisterService.save(entity);
		model.addAttribute("mess", "Cập nhập thành công");
		model.addAttribute("driver", new DriverDTO());
		return new ModelAndView(viewDriver(model), model);

	}
@GetMapping("confirm/{driverID}")
public ModelAndView confirm(ModelMap model, @PathVariable("driverID") String dishID) {
	Optional<Driver> opt = driverRegisterService.findById(dishID);

	if (opt.isPresent()) {
		Driver driver = opt.get();
		driver.setConfirm(1);
		driverRegisterService.save(driver); // Assuming you have a save/update method in your service
	}

	return new ModelAndView(viewDriver(model), model);
}
	
	@GetMapping("delete/{phoneNumber}")
	public ModelAndView delete(ModelMap model, @PathVariable("phoneNumber") String phoneNumber) throws IOException {
		fillToTable(model);
		Optional<Driver> optional=driverRegisterService.findById(phoneNumber);
		
		if (optional.isPresent()) {
			
			driverRegisterService.delete(optional.get());
//			model.addAttribute("mess", "Tài khoản "+optional.get().get()+" đã được xoá");
			
		}else {
			model.addAttribute("mess", "Không tìm thấy tài khoản");
		}
		
		return new ModelAndView(viewDriver(model),model);
	}

	@GetMapping("edit/{driverID}")
	public ModelAndView edit(ModelMap model, @PathVariable("driverID") String driverID) {
		fillToTable(model);
		Optional<Driver> opt = driverRegisterService.findById(driverID);
		DriverDTO dto = new DriverDTO();

		if (opt.isPresent()) {
			Driver entity = opt.get();
			dto.setIsEdit(true);
			BeanUtils.copyProperties(entity, dto);
			model.addAttribute("driver", dto);
			model.addAttribute("editMode", true);

			return new ModelAndView("customerUI/update-driver", model);
		}
		model.addAttribute("mess", "Dish is not existed");

		return new ModelAndView("restaurantUI/managementDish", model);
	}
}
