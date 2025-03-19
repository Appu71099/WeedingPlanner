package com.weddingplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weddingplanner.exception.VendorNotFoundException;
import com.weddingplanner.model.Vendor;
import com.weddingplanner.repository.VendorRepository;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public Vendor registerVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Vendor updateAvailability(Long vendorId, boolean availability) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new VendorNotFoundException(vendorId));

        vendor.setAvailable(availability);

        return vendorRepository.save(vendor);
    }
}