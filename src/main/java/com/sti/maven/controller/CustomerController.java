package com.sti.maven.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.maven.dao.AccountDao;
import com.sti.maven.dao.CustomerDao;
import com.sti.maven.error.CustomException;
import com.sti.maven.model.Account;
import com.sti.maven.model.Customer;
import com.sti.maven.model.dto.AccountDto;
import com.sti.maven.model.dto.CommonResponse;
import com.sti.maven.model.dto.CustomerDto;

@RestController
@RequestMapping("/customer")
@SuppressWarnings("rawtypes")
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AccountDao accountdao;
	
	@GetMapping(value="/{customerNumber}")
	public CommonResponse getById(@PathVariable("customerNumber") String customerNumber) throws CustomException{
		LOGGER.info("customerNumber : {}", customerNumber);
		
		try {
			Customer customer = customerDao.getById(Integer.valueOf(customerNumber));
			return new CommonResponse<CustomerDto>(modelMapper.map(customer, CustomerDto.class));
					
		} catch (CustomException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} 
		catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("04", "parameter harus angka");
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
		
	}
	
	@GetMapping(value="/{customerNumber}/accounts")
	public CommonResponse getAccounts(@PathVariable("customerNumber") String customerNumber) throws CustomException{
		LOGGER.info("customerNumber : {}", customerNumber);
		
		try {
			Customer customer = customerDao.getById(Integer.valueOf(customerNumber));
			List<Account> accounts = accountdao.getListByCustomer(customer);
			
			return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc -> modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
			
					
		} catch (CustomException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} 
		catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("04", "parameter harus angka");
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
		
	}
	
	@PostMapping(value="")
	public CommonResponse insert(@RequestBody CustomerDto customerDto) throws CustomException {	
		try {
			Customer customer = modelMapper.map(customerDto, Customer.class);
			customer.setCustomerNumber(0);
			customer = customerDao.save(customer);
			
			return new CommonResponse<CustomerDto>(modelMapper.map(customer, CustomerDto.class));
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	
	}
	
	@DeleteMapping(value="/{customer}")
	public CommonResponse  delete (@PathVariable ("customer") Integer customerNumber ) throws CustomException {
		try {
				Customer checkCustomer = customerDao.getById(customerNumber);
				if(checkCustomer==null) {
					return new CommonResponse("06", "customer tidak ditemukan");
				}
				customerDao.delete(checkCustomer);
				return new CommonResponse();
		} catch (CustomException e) {
			return new CommonResponse("06", e.getMessage());
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
		
	}
	
	@PutMapping("/customer")
	public CommonResponse update(@RequestBody CustomerDto customer ) throws CustomException{
		
		try {
			Customer checkCustomer = customerDao.getById(customer.getCustomerNumber());
			if (checkCustomer==null) {
				return new CommonResponse("14", "customer tidak ditemukan");
			}
			if (customer.getBirthDate()!=null) {
				checkCustomer.setBirthDate(customer.getBirthDate());
			}
			if (customer.getFirstName()!=null) {
				checkCustomer.setFirstName(customer.getFirstName());
			}
			if(customer.getLastName()!=null) {
				checkCustomer.setLastName(customer.getLastName());
			}
			if(customer.getPhonetype()!=null) {
				checkCustomer.setPhonetype(customer.getPhonetype());
			}
			if (customer.getPhoneNumber()!=null) {
				checkCustomer.setPhoneNumber(customer.getPhoneNumber());
			}
			
			checkCustomer = customerDao.save(checkCustomer);
			return new CommonResponse<CustomerDto>(modelMapper.map(checkCustomer, CustomerDto.class));

			
			
		} catch (CustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

		
	}
	
	@GetMapping(value="/list")
	public CommonResponse getList(@RequestParam(name="customer", defaultValue="") String customer ) throws CustomException{	
		try {
			LOGGER.info("customer get list, param  : {} ", customer);
			List<Customer> customers = customerDao.getList();
			
			return new CommonResponse<List<CustomerDto>>(customers.stream().map(cust -> modelMapper.map(cust, CustomerDto.class)).collect(Collectors.toList()));
			
		} catch (CustomException e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} 
		catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());

		}
	
	}
	

	
	
}
