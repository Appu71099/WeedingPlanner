package com.weddingplanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weddingplanner.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<Vendor> findByIsAvailable(boolean isAvailable);

    List<Vendor> findByNameContainingIgnoreCase(String name);
}
