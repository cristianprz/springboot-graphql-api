package com.api.graphql.test.repository;

import com.api.graphql.test.entity.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressesRepository extends JpaRepository<Addresses, Long> {

}
