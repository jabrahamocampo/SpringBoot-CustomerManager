package net.codejava.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.model.Product;
import net.codejava.service.ProductService;

@CrossOrigin(origins="http://localhost:4200", maxAge = 3600)
@RequestMapping("api/v1")
@RestController
public class AppControllerApi {

	@Autowired
	private ProductService service; 
	
	//getAll
	@RequestMapping(value = "/customerlist", method = RequestMethod.GET)
	public List<Product> getCustomersList(){
		List<Product> customerList = service.listAll();
		return customerList;
	}
	
	//saveNewCustomer
	@RequestMapping(value = "/newcustomer", method = RequestMethod.POST)
	public void saveCustomer(@RequestBody Product product) {
		service.save(product);
	}
	
	@GetMapping(path = "/customer/{id}")
	public Product getCustomerById(@PathVariable("id") long id) {
		return service.get(id);
	}
	
	//delete
	@DeleteMapping(path = "deletecustomer/{id}")
	public void deleteCustomer(@PathVariable("id") long id) {
		service.delete(id);
	}
	
	//update
	@PutMapping(path = "/updatecustomer/{id}")
	public void updateCustomer(@PathVariable("id") long id, @RequestBody Product person) {
		Product updatePerson = service.get(id);
		updatePerson.setName(person.getName());
		updatePerson.setEmail(person.getEmail());
		updatePerson.setAddress(person.getAddress());
		updatePerson.setGender(person.getGender());
		service.save(updatePerson);
	}
	
}
