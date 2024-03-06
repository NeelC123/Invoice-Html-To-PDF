package com.rt.pot.invoice.mapper;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.rt.pot.invoice.model.InvoiceData;

@Service
public class DataMapper {
	public Context setData(InvoiceData invoiceData) {

		Context context = new Context();
		context.setVariable("invoiceData", invoiceData);
		return context;
	}

}
