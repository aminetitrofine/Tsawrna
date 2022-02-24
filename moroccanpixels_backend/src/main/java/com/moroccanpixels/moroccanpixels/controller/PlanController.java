package com.moroccanpixels.moroccanpixels.controller;

import com.moroccanpixels.moroccanpixels.model.entity.Plan;
import com.moroccanpixels.moroccanpixels.service.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plan")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }
    @GetMapping
    public List<Plan> listPlans(){
        return planService.listPlans();
    }
    @GetMapping("{planId}")
    public Plan getPlan(@PathVariable Long planId){
        return planService.getPlan(planId);
    }
    @PostMapping
    public Plan CreatePlan(@RequestBody Plan plan){
        return planService.createPlan(plan);
    }

    @DeleteMapping("{planId}")
    public void deletePlan(@PathVariable Long planId){
        planService.deletePlan(planId);
    }
    @PutMapping("{planId}")
    public Plan updatePlan(@RequestBody Plan plan,@PathVariable Long planId){
        return planService.updatePlan(planId,plan);
    }

}
