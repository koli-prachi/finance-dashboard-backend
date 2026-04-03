package com.prachi.finance_backend.repository;


import com.prachi.finance_backend.model.FinancialRecord;
import com.prachi.finance_backend.model.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(String type);
    List<FinancialRecord> findByTypeAndCategory(RecordType type, String category);
}