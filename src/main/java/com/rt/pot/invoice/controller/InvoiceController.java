package com.rt.pot.invoice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.rt.pot.common.Constants;
import com.rt.pot.common.URLConstants;
import com.rt.pot.exception.DataGettingException;
import com.rt.pot.invoice.mapper.DataMapper;
import com.rt.pot.invoice.model.InvoiceData;
import com.rt.pot.invoice.response.Response;
import com.rt.pot.invoice.service.InvoiceService;
import com.rt.pot.util.AppUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(URLConstants.Invoice.API_BASE)
@CrossOrigin(origins = "http://localhost:4200")
public class InvoiceController {

	@Autowired
	private DataMapper dataMapper;
	@Autowired
	SpringTemplateEngine springTemplateEngine;
	@Autowired
	private InvoiceService invoiceService;

	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

	
//	1.-----------------------------Invoice Generator ----------------------------------------------
	
	@Operation(summary = "Generate Invoice", description = "This service is used for Generate Invoice")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Exception Details", content = @Content(schema = @Schema(implementation = Response.class))) })
	@PostMapping(URLConstants.Invoice.GET_INVOICE)
	public ResponseEntity<Response> generateDocument(@RequestBody InvoiceData invoiceData) throws DataGettingException {
		String logTag = "updateException() :";
		LOGGER.info(AppUtil.getStartMethodMessage(logTag));
		Response response = null;
		String respData = null;
		String finalHtml = null;

		try {
			Context dataContext = dataMapper.setData(invoiceData);
			finalHtml = springTemplateEngine.process("template", dataContext);
			respData = invoiceService.htmltoPdf(finalHtml);
			invoiceService.htmltoPdf(finalHtml);
			if (respData == null) {
				respData = "Invoice Not Generated";
				response = new Response(Constants.NOT_FOUND, "Invoice Not Generated", "Not Created");
			} else {
				response = new Response(Constants.OK, "Invoice Generated", "Created");
			}
		} catch (Exception e) {
			throw new DataGettingException(e.getMessage());
		}

		LOGGER.info(AppUtil.getEndMethodMessage(logTag));
		return ResponseEntity.ok(response);

	}

}
