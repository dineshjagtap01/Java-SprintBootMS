package com.restdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restdemo.entities.CloudVendor;

@Repository
public interface CloudVendorRepo extends JpaRepository<CloudVendor, Integer> {	
	CloudVendor findByVendorId(int id);
}
