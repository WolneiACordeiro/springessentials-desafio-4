package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s.seller.name, SUM(s.amount) " +
            "FROM Sale s " +
            "WHERE s.date >= :minDate AND s.date <= :maxDate " +
            "GROUP BY s.seller.name")
    List<Object[]> summaryTwelveMonths(@Param("minDate") LocalDate startDate,
                                       @Param("maxDate") LocalDate endDate);
    @Query("SELECT s.id, s.date, s.amount, s.seller.name " +
            "FROM Sale s " +
            "WHERE s.date >= :minDate AND s.date <= :maxDate " +
            "AND (:name IS NULL OR UPPER(s.seller.name) LIKE UPPER(CONCAT('%', :name, '%')))")
    List<Object[]> reportTwelveMonthsByName(@Param("minDate") LocalDate startDate,
                                            @Param("maxDate") LocalDate endDate,
                                            @Param("name") String name);
}
