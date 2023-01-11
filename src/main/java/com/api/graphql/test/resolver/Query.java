package com.api.graphql.test.resolver;
import com.api.graphql.test.entity.Addresses;
import com.api.graphql.test.entity.Customer;
import com.api.graphql.test.repository.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.api.graphql.test.repository.CustomerRepository;
@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressesRepository addressesRepository;

	public Iterable<Addresses> allAddressess() {return addressesRepository.findAll();}

	public Addresses addresses(Long id) {
		return addressesRepository.findById(id).orElseGet(null);
	}

	public Iterable<Customer> allCustomers() {
		return customerRepository.findAll();
	}

	public Customer customer(Long id) {return customerRepository.findById(id).orElseGet(null);}

   public Boolean deleteCustomer(Long id){
		customerRepository.deleteById(id);
		return true;
	}

	public Boolean deleteAddresses(Long id){
	addressesRepository.deleteById(id);
		return true;
	}}
