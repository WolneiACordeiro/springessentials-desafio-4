package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate) {
		if (minDate == null && maxDate == null) {
			maxDate = LocalDate.now();
			minDate = maxDate.minusMonths(12);
		}
		List<Object[]> summary = repository.summaryTwelveMonths(minDate, maxDate);
		List<SummaryDTO> summaryDTOList = new ArrayList<>();
		for (Object[] row : summary) {
			String sellerName = (String) row[0];
			Double total = (Double) row[1];
			SummaryDTO summaryDTO = new SummaryDTO(sellerName, total);
			summaryDTOList.add(summaryDTO);
		}
		return summaryDTOList;
	}
	public List<ReportDTO> getReport(LocalDate minDate, LocalDate maxDate, String name) {
		if (minDate == null && maxDate == null) {
			maxDate = LocalDate.now();
			minDate = maxDate.minusMonths(12);
		}
		List<Object[]> summary;
		summary = repository.reportTwelveMonthsByName(minDate, maxDate, name);
		return summary.stream()
				.map(row -> new ReportDTO((Long) row[0], (LocalDate) row[1], (Double) row[2], (String) row[3]))
				.collect(Collectors.toList());
	}
}
