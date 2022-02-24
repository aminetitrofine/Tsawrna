package com.moroccanpixels.moroccanpixels.model.entity;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String billingMethode;
    private String billingAddress;
    private int postalCode;
    private CountryCode billingCountry;
    private String billingCity;

    @OneToOne
    private PaymentMethod paymentMethod;

}
