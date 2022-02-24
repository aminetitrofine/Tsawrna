package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.model.entity.Billing;
import com.moroccanpixels.moroccanpixels.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("billing")
public class BillingController {
    private final BillingService billingService;

    @Autowired
    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping()
    public List<Billing> listBillings(){
        return billingService.listBillings();
    }

    @GetMapping("{billingId}")
    public Billing getBilling(@PathVariable Long billingId){
        return billingService.getBilling(billingId);
    }
    @PostMapping
    public Billing addBilling(@RequestBody Billing billing){
        return billingService.addBilling(billing);
    }

    @PutMapping("{billingId}")
    public Billing updateBilling(@PathVariable Long billingId,@RequestBody Billing billing){
        return billingService.updateBilling(billingId,billing);
    }

    @DeleteMapping("{billingId}")
    public void deleteBilling(@PathVariable Long billingId){
        billingService.deleteBilling(billingId);
    }

}
