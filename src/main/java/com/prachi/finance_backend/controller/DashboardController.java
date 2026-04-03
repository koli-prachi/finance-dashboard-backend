package com.prachi.finance_backend.controller;


import com.prachi.finance_backend.exception.UnauthorizedException;
import com.prachi.finance_backend.service.FinancialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final FinancialService financialService;

    @GetMapping("/summary")
    public Map<String, Double> getSummary(
            @RequestHeader(value = "role", required = false) String role) {

        if (role == null) {
            role = "ADMIN"; // default for demo
        }

        if (!(role.equals("ADMIN") || role.equals("ANALYST"))) {
            throw new UnauthorizedException("Access Denied");
        }

        Map<String, Double> response = new HashMap<>();
        response.put("totalIncome", financialService.getTotalIncome());
        response.put("totalExpense", financialService.getTotalExpense());
        response.put("netBalance", financialService.getNetBalance());

        return response;
    }


    @GetMapping("/category")
    public Map<String, Double> getCategoryData(
            @RequestHeader(value = "role", required = false) String role) {

        if (role == null) {
            role = "ADMIN";
        }

        if (!(role.equals("ADMIN") || role.equals("ANALYST"))) {
            throw new UnauthorizedException("Access Denied");
        }

        return financialService.getCategoryWiseTotals();
    }

    @GetMapping("/trends")
    public Map<String, Map<String, Double>> getTrends(
            @RequestHeader(value = "role", required = false) String role) {

        if (role == null) {
            role = "ADMIN";
        }

        if (!(role.equals("ADMIN") || role.equals("ANALYST"))) {
            throw new UnauthorizedException("Access Denied");
        }

        return financialService.getMonthlyTrends();
    }

}