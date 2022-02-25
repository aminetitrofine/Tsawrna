package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.model.entity.Billing;
import com.moroccanpixels.moroccanpixels.model.entity.PaymentMethod;
import com.moroccanpixels.moroccanpixels.repository.BillingRepository;
import com.neovisionaries.i18n.CountryCode;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class BillingServiceTest {

    private final BillingRepository billingRepository = mock(BillingRepository.class);

    @Test
    void shouldListBillings() {
        billingRepository.findAll();
        verify(billingRepository).findAll();
    }

    @Test
    void shouldGetBilling() {
        Billing billing = new Billing(1L,"method","address",123, CountryCode.MA,"city", null);
        when(billingRepository.findById(billing.getId())).thenReturn(Optional.of(billing));
    }

    @Test
    void shouldAddBilling() {
        Billing billing = new Billing(1L,"method","address",123, CountryCode.MA,"city", null);
        billingRepository.save(billing);
        ArgumentCaptor<Billing> billingArgumentCaptor = ArgumentCaptor.forClass(Billing.class);
        verify(billingRepository).save(billingArgumentCaptor.capture());
        Billing capturedBilling = billingArgumentCaptor.getValue();
        assertThat(capturedBilling).isEqualTo(billing);
    }

    @Test
    void shouldUpdateBilling() {
        Billing existingBilling = new Billing(1L,"method","address",123, CountryCode.MA,"city", null);
        Billing newBilling = new Billing(2L,"method2","address2",456, CountryCode.MA,"city2", null);
        when(billingRepository.findById(existingBilling.getId())).thenReturn(Optional.of(existingBilling));

        assertThat(newBilling.getBillingAddress()).isNotNull();
        existingBilling.setBillingAddress(newBilling.getBillingAddress());

        assertThat(newBilling.getBillingMethode()).isNotNull();
        existingBilling.setBillingMethode(newBilling.getBillingMethode());

        assertThat(newBilling.getPostalCode()).isNotEqualTo(0);
        existingBilling.setPostalCode(newBilling.getPostalCode());

        assertThat(newBilling.getBillingCountry()).isNotNull();
        existingBilling.setBillingCountry(newBilling.getBillingCountry());

        assertThat(newBilling.getBillingCity()).isNotNull();
        existingBilling.setBillingCity(newBilling.getBillingCity());
    }

    @Test
    void shouldDeleteBilling() {
        Billing billing = new Billing(1L,"method","address",123, CountryCode.MA,"city", null);
        when(billingRepository.findById(billing.getId())).thenReturn(Optional.of(billing));

        billingRepository.delete(billing);
        ArgumentCaptor<Billing> billingArgumentCaptor = ArgumentCaptor.forClass(Billing.class);
        verify(billingRepository).delete(billingArgumentCaptor.capture());
        Billing capturedBilling = billingArgumentCaptor.getValue();
        assertThat(capturedBilling).isEqualTo(billing);
    }
}