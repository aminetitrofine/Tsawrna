package com.moroccanpixels.moroccanpixels.service;

import com.moroccanpixels.moroccanpixels.model.entity.Plan;
import com.moroccanpixels.moroccanpixels.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Plan> listPlans() {
        return planRepository.findAll();
    }

    public Plan getPlan(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(()->new IllegalStateException(String.format("plan with id %d not found.",planId)));
    }

    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    public void deletePlan(Long planId) {
        planRepository.deleteById(planId);
    }

    public Plan updatePlan(Long planId, Plan newPlan) {
        planRepository.findById(planId)
                .orElseThrow(() -> new IllegalStateException(String.format("plan with id %d not found.", planId)));
        newPlan.setId(planId);
        return planRepository.save(newPlan);
    }
}
