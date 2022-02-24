package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.model.entity.Billing;
import com.moroccanpixels.moroccanpixels.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BillingService {
    private final BillingRepository billingRepository;

    @Autowired
    public BillingService(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    public List<Billing> listBillings() {
        return billingRepository.findAll();
    }
    public Billing getBilling(Long billingId) {
        return billingRepository.findById(billingId)
                .orElseThrow(()->new IllegalStateException(String.format("billing with id %d not found",billingId)));
    }
    public Billing addBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    @Transactional
    public Billing updateBilling(Long billingId, Billing newBilling) {
        Billing billing = billingRepository.findById(billingId)
                .orElseThrow(()->new IllegalStateException(String.format("billing with id %d not found",billingId)));
        if(newBilling.getBillingAddress()!=null) billing.setBillingAddress(newBilling.getBillingAddress());
        if(newBilling.getBillingMethode()!=null) billing.setBillingMethode(newBilling.getBillingMethode());
        if(newBilling.getPostalCode()!=0) billing.setPostalCode(newBilling.getPostalCode());
        if(newBilling.getBillingCountry()!=null) billing.setBillingCountry(newBilling.getBillingCountry());
        if(newBilling.getBillingCity()!=null) billing.setBillingCity(newBilling.getBillingCity());
        return billing;
    }

    public void deleteBilling(Long billingId) {
        Billing billing = billingRepository.findById(billingId)
                .orElseThrow(()->new IllegalStateException(String.format("billing with id %d not found",billingId)));
        billingRepository.delete(billing);
    }
}
