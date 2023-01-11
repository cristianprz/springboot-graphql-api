package com.api.graphql.test.resolver;

import com.api.graphql.test.entity.Addresses;
import com.api.graphql.test.entity.Customer;
import com.api.graphql.test.repository.AddressesRepository;

import com.api.graphql.test.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class Mutation implements GraphQLMutationResolver {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressesRepository addressesRepository;

	public Customer addCustomer(String name,Float age) {
		Customer customer = new Customer();
		Calendar date = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date.getTime());
		customer.setName(name);
		customer.setAge(age);
		customer.setLast_update(strDate);
		customer.setRegistration_date(strDate);

		return customerRepository.saveAndFlush(customer);
	}

	public Customer updateCustomer(Long id, String name, Float age) {
		Calendar date = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date.getTime());
		Customer customer = new Customer();
		customer.setId(id);
		customer.setLast_update(strDate);
		if(age != null){
			customer.setAge(age);
		}
		if(name != null){
			customer.setName(name);
		}

		return customerRepository.saveAndFlush(customer);
	}

	public Boolean deleteCustomer(Long id) {
		customerRepository.deleteById(id);
		return true;
	}

	public Addresses addAddresses(String zip_code,Float number, Long customer_id) {

		String regex = "^[0-9]{5}(?:-[0-9]{4})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(zip_code);
		Addresses addresses = new Addresses();

		if(matcher.matches()){
			Customer customer = customerRepository.findById(customer_id).orElseGet(null);
			addresses.setZip_code(zip_code);
			addresses.setNumber(number);
			addresses.setCustomer(customer);
		}
		return addressesRepository.saveAndFlush(addresses);
	}

	public Addresses updateAddresses(Long id, String zip_code,Float number) {
		String regex = "^[0-9]{5}(?:-[0-9]{4})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(zip_code);
		Addresses addresses = new Addresses();

		if(matcher.matches()) {
			addresses.setId(id);
			addresses.setNumber(number);
			addresses.setZip_code(zip_code);
		}
		return addressesRepository.saveAndFlush(addresses);
	}

	public Boolean deleteAddresses(Long id) {
		addressesRepository.deleteById(id);
		return true;
	}

}
