package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.model.PlanType;
import com.moroccanpixels.moroccanpixels.model.entity.Plan;
import com.moroccanpixels.moroccanpixels.repository.PlanRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class PlanServiceTest {

    private final PlanRepository planRepository = mock(PlanRepository.class);

    @Test
    void shouldListPlans() {
        planRepository.findAll();
        verify(planRepository).findAll();
    }

    @Test
    void shouldGetPlan() {
        Plan plan = new Plan(1L,"my plan","this is my first plan",5,
                PlanType.MONTHLY_NO_CONTRACT,50);
        when(planRepository.findById(plan.getId())).thenReturn(Optional.of(plan));
    }

    @Test
    void shouldCreatePlan() {
        Plan plan = new Plan(1L,"my plan","this is my first plan",5,
                PlanType.MONTHLY_NO_CONTRACT,50);
        planRepository.save(plan);
        ArgumentCaptor<Plan> planArgumentCaptor = ArgumentCaptor.forClass(Plan.class);
        verify(planRepository).save(planArgumentCaptor.capture());
        Plan capturedPlan = planArgumentCaptor.getValue();
        assertThat(capturedPlan).isEqualTo(plan);
    }

    @Test
    void shouldDeletePlan() {
        Plan plan = new Plan(1L,"my plan","this is my first plan",5,
                PlanType.MONTHLY_NO_CONTRACT,50);
        planRepository.deleteById(plan.getId());
        verify(planRepository).deleteById(plan.getId());
    }

    @Test
    void shouldUpdatePlan() {
        Plan existantPlan = new Plan(1L,"my plan","this is my first plan",5,
                PlanType.MONTHLY_NO_CONTRACT,50);
        Plan newPlan = new Plan(2L,"my plan updated","this is my plan updated",10,
                PlanType.MONTHLY_NO_CONTRACT,70);
        when(planRepository.findById(existantPlan.getId())).thenReturn(Optional.of(existantPlan));
        newPlan.setId(existantPlan.getId());
        planRepository.save(newPlan);
        ArgumentCaptor<Plan> planArgumentCaptor = ArgumentCaptor.forClass(Plan.class);
        verify(planRepository).save(planArgumentCaptor.capture());
        Plan capturedPlan = planArgumentCaptor.getValue();
        assertThat(capturedPlan).isEqualTo(newPlan);
    }
}