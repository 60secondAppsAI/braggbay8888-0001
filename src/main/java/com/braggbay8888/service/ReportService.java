package com.braggbay8888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbay8888.domain.Report;
import com.braggbay8888.dto.ReportDTO;
import com.braggbay8888.dto.ReportSearchDTO;
import com.braggbay8888.dto.ReportPageDTO;
import com.braggbay8888.dto.ReportConvertCriteriaDTO;
import com.braggbay8888.service.GenericService;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ReportService extends GenericService<Report, Integer> {

	List<Report> findAll();

	ResultDTO addReport(ReportDTO reportDTO, RequestDTO requestDTO);

	ResultDTO updateReport(ReportDTO reportDTO, RequestDTO requestDTO);

    Page<Report> getAllReports(Pageable pageable);

    Page<Report> getAllReports(Specification<Report> spec, Pageable pageable);

	ResponseEntity<ReportPageDTO> getReports(ReportSearchDTO reportSearchDTO);
	
	List<ReportDTO> convertReportsToReportDTOs(List<Report> reports, ReportConvertCriteriaDTO convertCriteria);

	ReportDTO getReportDTOById(Integer reportId);







}





