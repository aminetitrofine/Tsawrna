package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.model.PlanType;
import com.moroccanpixels.moroccanpixels.model.entity.Plan;
import com.moroccanpixels.moroccanpixels.service.PlanService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PlanControllerTest {
    private final PlanService planService = Mockito.mock(PlanService.class);

    @Test
    void listPlans() {
        Plan myPlan = new Plan();
        List<Plan> myPlansList = new ArrayList<Plan>();
        myPlansList.add(myPlan);

        when(planService.listPlans()).thenReturn(myPlansList);

        PlanController planController = new PlanController(planService);
        assertEquals(myPlansList, planController.listPlans());
    }

    @Test
    void getPlan() {
        Plan myPlan = new Plan(1L,"my plan","this is my first plan",5,
                PlanType.MONTHLY_NO_CONTRACT,50);

        when(planService.getPlan(1L)).thenReturn(myPlan);

        PlanController planController = new PlanController(planService);
        assertEquals(myPlan, planController.getPlan(1L));
    }

    @Test
    void createPlan() {
        Plan myPlan = new Plan(1L,"my plan","this is my first plan",5,
                PlanType.MONTHLY_NO_CONTRACT,50);

        when(planService.createPlan(myPlan)).thenReturn(myPlan);

        PlanController planController = new PlanController(planService);
        assertEquals(myPlan, planController.CreatePlan(myPlan));
    }

    @Test
    void deletePlan() {
        Plan myPlan = new Plan(1L,"my plan","this is my first plan",5,
                PlanType.MONTHLY_NO_CONTRACT,50);

        planService.deletePlan(myPlan.getId());
        verify(planService).deletePlan(myPlan.getId());
        //when(planService.deletePlan(1L)).thenReturn(true);

        PlanController planController = new PlanController(planService);
        planController.deletePlan(myPlan.getId());
        //verify(planController).deletePlan(myPlan.getId());
    }

    @Test
    void updatePlan() {
        Plan myPlan = new Plan(1L,"my plan","this is my first plan",5,
                PlanType.MONTHLY_NO_CONTRACT,50);

        when(planService.updatePlan(myPlan.getId(),myPlan)).thenReturn(myPlan);

        PlanController planController = new PlanController(planService);
        assertEquals(myPlan, planController.updatePlan(myPlan, myPlan.getId()));
    }
}