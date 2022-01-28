package com.moroccanpixels.moroccanpixels.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PlanParameters implements Serializable {
    private int numberOfImages;
    private String type;
    private int pricePerMonth;
}
