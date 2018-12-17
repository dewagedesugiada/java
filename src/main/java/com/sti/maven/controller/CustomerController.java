package com.sti.maven.controller;

import java.util.ArrayList; 
import java.util.List;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sti.maven.dao.CustomerDao;
import com.sti.maven.model.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value="id", defaultValue="") String id) {
		try {
			Customer customer = customerDao.getById(Integer.valueOf(id));
			if(customer==null) {
				return "data tidak ditemukan" ;
			}else {
				return "hello" + " "+customer.getFirstName() ;
//				return new hello("{'aa':'bb'}");
			}
		} catch (NumberFormatException e) {
			return "salah format input";
		}catch(SQLGrammarException e) {
			return String.format("terjadi kesalahan SQL : %s", e.getMessage());
		}
		catch (Exception e) {
			return String.format("terjadi kesalahan sistem : %s", e.getMessage());
		}
	}
	
	@PostMapping("/customer")
	public Customer customer(@RequestBody Customer customer) throws Exception {	
		
		Customer cs = customerDao.save(customer);
		return cs;
	}
	
	@DeleteMapping("/customer/{id}")
	public void delcustomer (@PathVariable ("id") Customer customer ) throws Exception {
		customerDao.delete(customer);
	}
	
	@PutMapping("/customer")
	public Customer upcustomer(@RequestBody Customer customer ) throws Exception{
		Customer update = customerDao.save(customer);
		
		return update ;
	}
	
	@GetMapping("/customer")
	public List<Customer> getList() throws Exception{	
		List<Customer> list = customerDao.getList();
		
		return list;
	}
	
	
	
//	List<Customer> list = new ArrayList<>();
	
//	@GetMapping("/customer/{id}")
//	public Customer getByid(@PathVariable ("id") String customernumber) {
//		Customer cs = new Customer();
//		cs.setCustomerNumber(Integer.parseInt(customernumber));
//		cs.setFirstName("Dewa");
//		cs.setLastName("Gede");
//		
//		return cs ;
//	}
//	
//	@PostMapping("/customerssss")
//	public Customer post(@RequestParam ("id") String customer) {	
//		Customer cs = new Customer();
//		cs.setFirstName(customer);
//		return cs;
//	}
//	
//	@PutMapping("/cust")
//	public Customer up(@RequestParam ("data") String customer) {
//		Customer cs = new Customer();
//		cs.setFirstName(customer);
//		return cs ;
//	}
//	
//	@PatchMapping("/cust")
//	public Customer patch(@RequestBody Customer customer) {
//		customer.setCustomerNumber(1);
//		customer.setFirstName("dewak");
//		return customer;
//	}
//	
//	
//	@PutMapping("/customer")
//	public Customer update(@RequestBody Customer customer) {
//		customer.setFirstName("dewak");
//		return customer ;
//	}
//	
//	@DeleteMapping("/customer/{id}")
//	public Customer delete(@PathVariable ("id") String id) {
//		Customer cs = new Customer();
//		cs.setCustomerNumber(Integer.parseInt(id));
//		
//		return cs ;
//	}
//	@GetMapping("/customerst")
//	public Customer getlagi(@RequestBody Customer customer) {
//		
//		return customer ;
//	}
//	
//	
//	@GetMapping("/custumers")
//	public List<Customer> getlist() {
//		ArrayList<Customer> list = new ArrayList<Customer>();
//		return list ;
//	}
//	
//	@PostMapping("/customerss")
//	public List<Customer> getlistall(){
//		Customer cs = new Customer();
//		cs.setCustomerNumber(123456);
//		cs.setFirstName("Dewa");
//		cs.setLastName("Gede");
//		
//		
//		ArrayList<Customer> list = new ArrayList<>();
//		list.add(cs) ;
//		
//		return list ;
//	} 
	
//	@PutMapping("/custom/{id}")
//	public ResponseEntity<?> updateCus(
//	  @PathVariable int id,
//	  @RequestParam("firstname") String fisrtname) {
//	    String firstname = null;
//		return new ResponseEntity<>(new Customer(id, firstname), HttpStatus.OK);
//	}
	
	
	
	
	
	
}
