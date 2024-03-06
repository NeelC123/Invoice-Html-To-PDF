package com.rt.pot.invoice.service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.rt.pot.invoice.controller.InvoiceController;
import com.rt.pot.util.AppUtil;

@Service
public class InvoiceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

	public String htmltoPdf(String processedHtml) {

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		String result = null;
		String logTag = "htmltoPdf() :";
		try {
			LOGGER.info(AppUtil.getStartMethodMessage(logTag));

			FileOutputStream fout = new FileOutputStream("D:\\Desktop\\Document\\jam.pdf");
			PdfWriter pdfWriter = new PdfWriter((fout));
			DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, true, false);
			ConverterProperties converterProperties = new ConverterProperties();
			converterProperties.setFontProvider(defaultFontProvider);
			HtmlConverter.convertToPdf(processedHtml, pdfWriter, converterProperties);

			System.out.println(fout.toString());
			byteArrayOutputStream.writeTo(fout);
			byteArrayOutputStream.close();
			byteArrayOutputStream.flush();
			fout.close();
			result = "Saved";
			return result;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;

	}
}
