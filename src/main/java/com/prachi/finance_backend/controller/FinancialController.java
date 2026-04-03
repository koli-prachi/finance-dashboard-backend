package com.prachi.finance_backend.controller;


import com.prachi.finance_backend.model.FinancialRecord;
import com.prachi.finance_backend.model.RecordType;
import com.prachi.finance_backend.service.FinancialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class FinancialController {

    private final FinancialService financialService;

    @PostMapping
    public FinancialRecord create(
            @RequestHeader("role") String role,
            @Valid @RequestBody FinancialRecord record) {

        return financialService.createRecord(record, role);
    }

    @GetMapping
    public List<FinancialRecord> getAll(
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        return financialService.getFilteredRecords(type, category, startDate, endDate);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @RequestHeader("role") String role,
            @PathVariable Long id) {

        financialService.deleteRecord(id, role);
    }

    @PutMapping("/{id}")
    public FinancialRecord update(
            @RequestHeader("role") String role,
            @PathVariable Long id,
            @Valid @RequestBody FinancialRecord record) {

        return financialService.updateRecord(id, record, role);
    }


}