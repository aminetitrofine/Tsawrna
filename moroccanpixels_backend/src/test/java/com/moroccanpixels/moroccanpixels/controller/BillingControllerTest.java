package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.model.entity.Billing;
import com.moroccanpixels.moroccanpixels.service.BillingService;
import com.neovisionaries.i18n.CountryCode;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BillingControllerTest {
    private final BillingService billingService = Mockito.mock(BillingService.class);

    @Test
    void shouldListBillings() {
        Billing myBilling = new Billing();
        List<Billing> myBillingsList = new ArrayList<>();
        myBillingsList.add(myBilling);

        when(billingService.listBillings()).thenReturn(myBillingsList);
        BillingController billingController = new BillingController(billingService);
        assertEquals(myBillingsList, billingController.listBillings());
    }

    @Test
    void shouldGetBilling() {
        Billing myBilling = new Billing(1L,"method","address",
                123, CountryCode.MA,"city", null);

        when(billingService.getBilling(1L)).thenReturn(myBilling);
        BillingController billingController = new BillingController(billingService);
        assertEquals(myBilling, billingController.getBilling(1L));
    }

    @Test
    void shouldUpdateBilling() {
        Billing myBilling = new Billing(1L,"method","address",
                123, CountryCode.MA,"city", null);

        when(billingService.updateBilling(myBilling.getId(), myBilling)).thenReturn(myBilling);

        BillingController billingController = new BillingController(billingService);
        assertEquals(myBilling, billingController.updateBilling(myBilling.getId(), myBilling));
    }

    @Test
    void shouldDeleteBilling() {
        Billing myBilling = new Billing(1L,"method","address",
                123, CountryCode.MA,"city", null);

        billingService.deleteBilling(myBilling.getId());
        verify(billingService).deleteBilling(myBilling.getId());

        BillingController billingController = new BillingController(billingService);
        billingController.deleteBilling(myBilling.getId());
    }
}