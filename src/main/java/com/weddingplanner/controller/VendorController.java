package com.weddingplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weddingplanner.model.Vendor;
import com.weddingplanner.service.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping
    public ResponseEntity<Vendor> registerVendor(@RequestBody Vendor vendor) {
        Vendor createdVendor = vendorService.registerVendor(vendor);
        return new ResponseEntity<>(createdVendor, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/updateAvailability")
    public ResponseEntity<Vendor> updateVendorAvailability(@PathVariable Long id, @RequestParam boolean availability) {
        Vendor updatedVendor = vendorService.updateAvailability(id, availability);
        return new ResponseEntity<>(updatedVendor, HttpStatus.OK);
    }
}