package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.model.PlanType;
import com.moroccanpixels.moroccanpixels.model.entity.Plan;
import com.moroccanpixels.moroccanpixels.service.PlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlanController.class)
class PlanControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlanService planService;

    @Test
    void shouldListPlans() throws Exception {
        Plan myPlan = new Plan();
        List<Plan> myPlansList = new ArrayList<Plan>();
        myPlansList.add(myPlan);

        when(planService.listPlans()).thenReturn(myPlansList);
        mockMvc.perform(get("plan/"))
                .andExpect(status().isOk());

        verify(planService).listPlans();
    }

    @Test
    void getPlan() throws Exception {
        Plan myPlan = new Plan(1L,"my plan","this is my first plan",5,
                PlanType.MONTHLY_NO_CONTRACT,50);

        when(planService.getPlan(1L)).thenReturn(myPlan);
        mockMvc.perform(get("{planId}",1L))
                .andExpect(status().isOk());
        verify(planService).getPlan(1L);
    }

    @Test
    void createPlan() {
    }

    @Test
    void deletePlan() {
    }

    @Test
    void updatePlan() {
    }
}