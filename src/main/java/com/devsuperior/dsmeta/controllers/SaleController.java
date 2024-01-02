package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleMinDTO>> getReport(Pageable pageable,
                                                      @RequestParam(required = false) String minDate,
                                                      @RequestParam(required = false) String maxDate,
                                                      @RequestParam(required = false) String name) {
        return ResponseEntity.ok(service.reportsForAllSellers(minDate, maxDate, name, pageable));
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<List<SaleSumDTO>> getSummary(@RequestParam(required = false) String minDate, @RequestParam(required = false) String maxDate) {
        return ResponseEntity.ok(service.listAllSales(minDate, maxDate));
    }
}
