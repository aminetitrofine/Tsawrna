package com.moroccanpixels.moroccanpixels.model.entity;

import com.moroccanpixels.moroccanpixels.model.PlanType;
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
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer numberOfImages;

    @Enumerated(EnumType.STRING)
    private PlanType type;

    private int pricePerMonth;
}
