package com.braggbay8888.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbay8888.dao.GenericDAO;
import com.braggbay8888.service.GenericService;
import com.braggbay8888.service.impl.GenericServiceImpl;
import com.braggbay8888.dao.ReportDAO;
import com.braggbay8888.domain.Report;
import com.braggbay8888.dto.ReportDTO;
import com.braggbay8888.dto.ReportSearchDTO;
import com.braggbay8888.dto.ReportPageDTO;
import com.braggbay8888.dto.ReportConvertCriteriaDTO;
import com.braggbay8888.dto.common.RequestDTO;
import com.braggbay8888.dto.common.ResultDTO;
import com.braggbay8888.service.ReportService;
import com.braggbay8888.util.ControllerUtils;





@Service
public class ReportServiceImpl extends GenericServiceImpl<Report, Integer> implements ReportService {

    private final static Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

	@Autowired
	ReportDAO reportDao;

	


	@Override
	public GenericDAO<Report, Integer> getDAO() {
		return (GenericDAO<Report, Integer>) reportDao;
	}
	
	public List<Report> findAll () {
		List<Report> reports = reportDao.findAll();
		
		return reports;	
		
	}

	public ResultDTO addReport(ReportDTO reportDTO, RequestDTO requestDTO) {

		Report report = new Report();

		report.setReportId(reportDTO.getReportId());


		report.setReason(reportDTO.getReason());


		report.setReportDate(reportDTO.getReportDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		report = reportDao.save(report);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Report> getAllReports(Pageable pageable) {
		return reportDao.findAll(pageable);
	}

	public Page<Report> getAllReports(Specification<Report> spec, Pageable pageable) {
		return reportDao.findAll(spec, pageable);
	}

	public ResponseEntity<ReportPageDTO> getReports(ReportSearchDTO reportSearchDTO) {
	
			Integer reportId = reportSearchDTO.getReportId(); 
 			String reason = reportSearchDTO.getReason(); 
   			String sortBy = reportSearchDTO.getSortBy();
			String sortOrder = reportSearchDTO.getSortOrder();
			String searchQuery = reportSearchDTO.getSearchQuery();
			Integer page = reportSearchDTO.getPage();
			Integer size = reportSearchDTO.getSize();

	        Specification<Report> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, reportId, "reportId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, reason, "reason"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("reason")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Report> reports = this.getAllReports(spec, pageable);
		
		//System.out.println(String.valueOf(reports.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(reports.getTotalPages()));
		
		List<Report> reportsList = reports.getContent();
		
		ReportConvertCriteriaDTO convertCriteria = new ReportConvertCriteriaDTO();
		List<ReportDTO> reportDTOs = this.convertReportsToReportDTOs(reportsList,convertCriteria);
		
		ReportPageDTO reportPageDTO = new ReportPageDTO();
		reportPageDTO.setReports(reportDTOs);
		reportPageDTO.setTotalElements(reports.getTotalElements());
		return ResponseEntity.ok(reportPageDTO);
	}

	public List<ReportDTO> convertReportsToReportDTOs(List<Report> reports, ReportConvertCriteriaDTO convertCriteria) {
		
		List<ReportDTO> reportDTOs = new ArrayList<ReportDTO>();
		
		for (Report report : reports) {
			reportDTOs.add(convertReportToReportDTO(report,convertCriteria));
		}
		
		return reportDTOs;

	}
	
	public ReportDTO convertReportToReportDTO(Report report, ReportConvertCriteriaDTO convertCriteria) {
		
		ReportDTO reportDTO = new ReportDTO();
		
		reportDTO.setReportId(report.getReportId());

	
		reportDTO.setReason(report.getReason());

	
		reportDTO.setReportDate(report.getReportDate());

	

		
		return reportDTO;
	}

	public ResultDTO updateReport(ReportDTO reportDTO, RequestDTO requestDTO) {
		
		Report report = reportDao.getById(reportDTO.getReportId());

		report.setReportId(ControllerUtils.setValue(report.getReportId(), reportDTO.getReportId()));

		report.setReason(ControllerUtils.setValue(report.getReason(), reportDTO.getReason()));

		report.setReportDate(ControllerUtils.setValue(report.getReportDate(), reportDTO.getReportDate()));



        report = reportDao.save(report);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ReportDTO getReportDTOById(Integer reportId) {
	
		Report report = reportDao.getById(reportId);
			
		
		ReportConvertCriteriaDTO convertCriteria = new ReportConvertCriteriaDTO();
		return(this.convertReportToReportDTO(report,convertCriteria));
	}







}
