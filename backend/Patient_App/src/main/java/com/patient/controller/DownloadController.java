package com.patient.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.patient.Entity.DisplayClinic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@RestController
@CrossOrigin
@RequestMapping("/download")
public class DownloadController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private DisplayClinic displayClinic;
	
	@GetMapping(value = "/excel", produces = "application/vnd.ms-excel")
	public ResponseEntity<byte[]> downloadExcel() throws IOException{
		
		 
		Query q = entityManager.createQuery("select c.country,c.name,c.phone,c.city,c.state,c.zipcode,DATE_FORMAT(max(co.orderDatetime),'%Y-%m-%d %T.%f') as lastOrderDate,DATE_FORMAT(max(dp.createdOn),'%Y-%m-%d %T.%f') as lastDispense from Clinic c inner join ClinicOrder co on c.locationId=co.locationId inner join DispenseToPatient dp on dp.locationId=co.locationId where c.deleted=:u and c.active=:v group by c.locationId");
		q.setParameter("u", false);
		q.setParameter("v", true);
		List<Object[]> rows = q.getResultList();
		System.out.println(Arrays.toString(rows.get(0)));
		

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("ListOfClinics");
		
		//create header row
		Row header = sheet.createRow(0);
		
		Cell cell = header.createCell(0);
		cell.setCellValue("Territory");
		Cell cell1 = header.createCell(1);
		cell1.setCellValue("clinicName");
		Cell cell2 = header.createCell(2);
		cell2.setCellValue("clinicNumber");
		Cell cell3 = header.createCell(3);
		cell3.setCellValue("city");
		Cell cell4 = header.createCell(4);
		cell4.setCellValue("state");
		Cell cell5 = header.createCell(5);
		cell5.setCellValue("zipcode");
//		Cell cell6 = header.createCell(6);
//		cell6.setCellValue("Facility Count");
		Cell cell7 = header.createCell(6);
		cell7.setCellValue("lastOrderDate");
		Cell cell8 = header.createCell(7);
		cell8.setCellValue("lastDispense");
		
//		int colNum = 0;
//		for(Object[] column : col) {
//	
//			System.out.println((String)column[0]);
//			Cell cell = header.createCell(colNum);
//			colNum++;
//			cell.setCellValue((String) column[0]);
//			
//		}
		
		//create Data rows
		int rowNum = 1;
		for(Object[] row:rows) {
			Row dataRow = sheet.createRow(rowNum++);
			int colNum =0;
			for(Object value:row) {
				Cell cells = dataRow.createCell(colNum++);
				if(value instanceof String) {
					cells.setCellValue((String)value);
				}
				else if(value instanceof Integer) {
					cells.setCellValue((Integer)value);
				}
			}
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		workbook.close();
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clinic.xlsx").body(baos.toByteArray());
		
	}
	
	@GetMapping(value = "/pdf", produces = "application/pdf")
	public ResponseEntity<byte[]> downloadPdf() throws IOException, DocumentException {
	
		Query q = entityManager.createQuery("select c.country,c.name,c.phone,c.city,c.state,c.zipcode,max(co.orderDatetime) as lastOrderDate,max(dp.createdOn) as lastDispense from Clinic c inner join ClinicOrder co on c.locationId=co.locationId inner join DispenseToPatient dp on dp.locationId=co.locationId where c.deleted=:u and c.active=:v group by c.locationId");
		q.setParameter("u", false);
		q.setParameter("v", true);
		List<Object[]> rows = q.getResultList();
		System.out.println(Arrays.toString(rows.get(0)));
	
	
	Document document = new Document();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PdfWriter.getInstance(document, baos);

	document.open();

	// Create table
	PdfPTable table = new PdfPTable(8); 
	PdfPCell cell0 = new PdfPCell(new Phrase("Territory"));
	table.addCell(cell0);
	PdfPCell cell1 = new PdfPCell(new Phrase("clinicName"));
	table.addCell(cell1);
	PdfPCell cell2 = new PdfPCell(new Phrase("clinicNumber"));
	table.addCell(cell2);
	PdfPCell cell3 = new PdfPCell(new Phrase("city"));
	table.addCell(cell3);
	PdfPCell cell4 = new PdfPCell(new Phrase("state"));
	table.addCell(cell4);
	PdfPCell cell5 = new PdfPCell(new Phrase("zipcode"));
	table.addCell(cell5);
	PdfPCell cell6 = new PdfPCell(new Phrase("lastOrderDate"));
	table.addCell(cell6);
	PdfPCell cell7 = new PdfPCell(new Phrase("lastDispense"));
	table.addCell(cell7);

	for (Object[] row : rows) {
		for (Object value : row) {
			PdfPCell cells = new PdfPCell(new Phrase(String.valueOf(value)));
			table.addCell(cells);
		}
	}

	document.add(table);
	document.close();

	return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clinics.pdf")
	.body(baos.toByteArray());
	}
	
	
	@GetMapping(value = "/inventoryPdf/{locationId}", produces = "application/pdf")
	public ResponseEntity<byte[]> downloadInventoryPdf(@PathVariable int locationId) throws IOException, DocumentException {
	
		Query q = entityManager.createNativeQuery("select p.product_name,s.expiry_date,s.serial_number,s.serial_status from serial s inner join product p where p.product_id=s.product_id and s.location_id=? and s.serial_status =? and s.expiry_date > CURRENT_DATE");
		q.setParameter(1, locationId);
		q.setParameter(2, "Recieved");
		List<Object[]> rows = q.getResultList();
		System.out.println(Arrays.toString(rows.get(0)));
		
		Query q1 = entityManager.createNativeQuery("select p.product_name,s.expiry_date,s.serial_number,s.serial_status from serial s inner join product p where p.product_id=s.product_id and s.location_id=? and s.expiry_date < CURRENT_DATE");
		q1.setParameter(1, locationId);
		List<Object[]> expRows = q1.getResultList();
	
	
	Document document = new Document();
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PdfWriter.getInstance(document, baos);

	document.open();

	// Create table
	PdfPTable tablehead = new PdfPTable(1);
	PdfPCell cellhead = new PdfPCell(new Phrase("1)				On hand Doses"));
	tablehead.addCell(cellhead);
	PdfPTable table = new PdfPTable(4);
	PdfPCell cell0 = new PdfPCell(new Phrase("Dose"));
	table.addCell(cell0);
	PdfPCell cell1 = new PdfPCell(new Phrase("Expiry"));
	table.addCell(cell1);
	PdfPCell cell2 = new PdfPCell(new Phrase("SerialNumber"));
	table.addCell(cell2);
	PdfPCell cell3 = new PdfPCell(new Phrase("status"));
	table.addCell(cell3);
	
	for (Object[] row : rows) {
		for (Object value : row) {
			PdfPCell cells = new PdfPCell(new Phrase(String.valueOf(value)));
			table.addCell(cells);
		}
	}
	document.add(tablehead);
	document.add(table);
	
	PdfPTable Exptablehead = new PdfPTable(1);
	PdfPCell Expcellhead = new PdfPCell(new Phrase("2)				Expired Doses"));
	Exptablehead.addCell(Expcellhead);
	PdfPTable table1 = new PdfPTable(4);
	PdfPCell cell4 = new PdfPCell(new Phrase("Dose"));
	table.addCell(cell4);
	PdfPCell cell5 = new PdfPCell(new Phrase("Expiry"));
	table.addCell(cell5);
	PdfPCell cell6 = new PdfPCell(new Phrase("SerialNumber"));
	table.addCell(cell6);
	PdfPCell cell7 = new PdfPCell(new Phrase("status"));
	table.addCell(cell7);
	
	for(Object[] expRow: expRows) {
		for(Object val:expRow) {
			PdfPCell expCells = new PdfPCell(new Phrase(String.valueOf(val)));
			table1.addCell(expCells);
		}
	}
	
	document.add(Exptablehead);
	document.add(table1);
	document.close();

	return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Inventory.pdf")
	.body(baos.toByteArray());
	}
	
	
	@GetMapping(value = "/InventoryExcel/{locationId}", produces = "application/vnd.ms-excel")
	public ResponseEntity<byte[]> downloadInventoryExcel(@PathVariable int locationId) throws IOException{
		
		 
		Query q = entityManager.createNativeQuery("select p.product_name,DATE_FORMAT(s.expiry_date,'%Y-%m-%d') as Expiry,s.serial_number,s.serial_status from serial s inner join product p where p.product_id=s.product_id and s.location_id=? and s.serial_status =? and s.expiry_date > CURRENT_DATE");
		q.setParameter(1, locationId);
		q.setParameter(2, "Recieved");
		List<Object[]> rows = q.getResultList();
		System.out.println(Arrays.toString(rows.get(0)));
		
		Query q1 = entityManager.createNativeQuery("select p.product_name,DATE_FORMAT(s.expiry_date,'%Y-%m-%d') as Expiry,s.serial_number,s.serial_status from serial s inner join product p where p.product_id=s.product_id and s.location_id=? and s.expiry_date < CURRENT_DATE");
		q1.setParameter(1, locationId);
		List<Object[]> expRows = q1.getResultList();
		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Inventory");
		
		//create header row
		Row header0 = sheet.createRow(0);
		Cell cell0 = header0.createCell(1);
		cell0.setCellValue("On Hand Doses");
		
		Row header = sheet.createRow(1);
		
		Cell cell = header.createCell(0);
		cell.setCellValue("Dose");
		Cell cell1 = header.createCell(1);
		cell1.setCellValue("Expiry");
		Cell cell2 = header.createCell(2);
		cell2.setCellValue("SerialNumber");
		Cell cell3 = header.createCell(3);
		cell3.setCellValue("Status");
	
		//create Data rows
		int rowNum = 2;
		for(Object[] row:rows) {
			Row dataRow = sheet.createRow(rowNum++);
			int colNum =0;
			for(Object value:row) {
				Cell cells = dataRow.createCell(colNum++);
				if(value instanceof String) {
					cells.setCellValue((String)value);
				}
				else if(value instanceof Integer) {
					cells.setCellValue((Integer)value);
				}
				else if(value instanceof Date) {
					cells.setCellValue((Date)value);
				}			
			}
		}
		
		rowNum = rowNum + 2;
		Row Expheader = sheet.createRow(rowNum);
		Cell cell9 = Expheader.createCell(1);
		cell9.setCellValue("Expired Doses");
		
		rowNum = rowNum +1;
		Row header1 = sheet.createRow(rowNum);
		
		Cell cell4 = header1.createCell(0);
		cell4.setCellValue("Dose");
		Cell cell5 = header1.createCell(1);
		cell5.setCellValue("Expiry");
		Cell cell6 = header1.createCell(2);
		cell6.setCellValue("SerialNumber");
		Cell cell7 = header1.createCell(3);
		cell7.setCellValue("Status");
		
		for(Object[] row:expRows) {
			Row dataRow = sheet.createRow(rowNum++);
			int colNum =0;
			for(Object value:row) {
				Cell cells = dataRow.createCell(colNum++);
				if(value instanceof Date) {
					cells.setCellValue((Date)value);
				}
				else if(value instanceof Integer) {
					cells.setCellValue((Integer)value);
				}
				else if(value instanceof String) {
					cells.setCellValue((String)value);
				}			
			}
		}
		
		
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		workbook.close();
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Inventory.xlsx").body(baos.toByteArray());
		
	}
}
