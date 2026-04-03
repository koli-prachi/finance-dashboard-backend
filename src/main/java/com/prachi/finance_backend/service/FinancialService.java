package com.prachi.finance_backend.service;


import com.prachi.finance_backend.model.FinancialRecord;
import com.prachi.finance_backend.model.RecordType;
import com.prachi.finance_backend.repository.FinancialRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.prachi.finance_backend.exception.UnauthorizedException;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FinancialService {

    private final FinancialRecordRepository recordRepository;

    public FinancialRecord createRecord(FinancialRecord record, String role) {

        if (!role.equals("ADMIN")) {
            throw new UnauthorizedException("Only ADMIN can create records");        }

        return recordRepository.save(record);
    }

    public List<FinancialRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    public void deleteRecord(Long id, String role) {

        if (!role.equals("ADMIN")) {
            throw new UnauthorizedException("Only ADMIN can create records");        }

        recordRepository.deleteById(id);
    }

    public double getTotalIncome() {
        return recordRepository.findAll().stream()
                .filter(r -> r.getType().name().equals("INCOME"))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return recordRepository.findAll().stream()
                .filter(r -> r.getType().name().equals("EXPENSE"))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    public double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    public Map<String, Double> getCategoryWiseTotals() {

        List<FinancialRecord> records = recordRepository.findAll();

        Map<String, Double> result = new HashMap<>();

        for (FinancialRecord record : records) {

            String category = record.getCategory();
            double amount = record.getAmount();

            result.put(category, result.getOrDefault(category, 0.0) + amount);
        }

        return result;
    }


    public FinancialRecord updateRecord(Long id, FinancialRecord updatedRecord, String role) {

        if (!role.equals("ADMIN")) {
            throw new UnauthorizedException("Only ADMIN can update records");
        }

        FinancialRecord existing = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        existing.setAmount(updatedRecord.getAmount());
        existing.setType(updatedRecord.getType());
        existing.setCategory(updatedRecord.getCategory());
        existing.setDate(updatedRecord.getDate());
        existing.setNote(updatedRecord.getNote());

        return recordRepository.save(existing);
    }

    public List<FinancialRecord> getFilteredRecords(
            RecordType type,
            String category,
            LocalDate startDate,
            LocalDate endDate) {

        List<FinancialRecord> records = recordRepository.findAll();

        return records.stream()
                .filter(r -> type == null || r.getType() == type)
                .filter(r -> category == null || r.getCategory().equalsIgnoreCase(category))
                .filter(r -> startDate == null || !r.getDate().isBefore(startDate))
                .filter(r -> endDate == null || !r.getDate().isAfter(endDate))
                .toList();
    }

    public Map<String, Map<String, Double>> getMonthlyTrends() {

        List<FinancialRecord> records = recordRepository.findAll();

        Map<String, Map<String, Double>> trends = new HashMap<>();

        for (FinancialRecord record : records) {

            String month = record.getDate().getYear() + "-" +
                    String.format("%02d", record.getDate().getMonthValue());

            // Initialize if not present
            trends.putIfAbsent(month, new HashMap<>());
            Map<String, Double> monthData = trends.get(month);

            monthData.putIfAbsent("income", 0.0);
            monthData.putIfAbsent("expense", 0.0);

            if (record.getType().name().equals("INCOME")) {
                monthData.put("income", monthData.get("income") + record.getAmount());
            } else {
                monthData.put("expense", monthData.get("expense") + record.getAmount());
            }
        }

        return trends;
    }



}