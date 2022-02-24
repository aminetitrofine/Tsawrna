package com.moroccanpixels.moroccanpixels.model;

import lombok.Getter;

@Getter
public enum PlanType {
    MONTHLY_NO_CONTRACT("monthly,no contract"),
    ANNUAL_BILLED_MONTHLY("annual, billed monthly"),
    ANNUAL_BILLED_UPFRONT("annual, billed upfront");

    private final String value;

    PlanType(String value){
        this.value=value;
    }
}
