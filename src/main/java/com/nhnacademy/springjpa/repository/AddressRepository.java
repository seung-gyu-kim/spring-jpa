package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Address.PK> {
    List<Address> findAllByUserId(String userId);

    long countByUserIdAndAddress(String userId, String address);
}
