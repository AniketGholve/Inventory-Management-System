package com.patient.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendDespenseMail(String UserMail,String clinicName,String patientName,String Dose) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(UserMail);
		message.setSubject("Dispensed to patient");
		LocalDate date = LocalDate.now();
		System.out.println("Dispense Mail Sent");
		message.setText("Hello,\n"
						+"The dose "+Dose+" is been dispensed to patient.\n"
						+"Patient Name: "+patientName+"\n"
						+"clinic name: "+clinicName+"\n"
						+"Date: "+date);
		mailSender.send(message);
	}
	
	public void sendAddtoInventoryMail(String UserMail,Integer serialNo,String clinicName,String Dose,Date expiryDate,Integer lot,String gtin) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(UserMail);
		message.setSubject("Added To Inventory");
		LocalDate date = LocalDate.now();
		System.out.println("Add To Inventory Mail Sent");
		message.setText("Hello,\n"
				+"The dose "+Dose+" is been Added to Inventory.\n"
				+"Serial Number : "+serialNo+"\n"
				+"clinic name: "+clinicName+"\n"
				+"Expiry Date: "+expiryDate+"\n"
				+"Lot Number: "+lot+"\n"
				+"Gtin: "+gtin+"\n"
				+"Date: "+date);
		mailSender.send(message);
	}
	
	public void sendOverdueMail(String[] Mails,String clinicName,String patientName,String Dose, String nextInjection) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(Mails);
		message.setSubject("Overdue Reminder");
		LocalDate date = LocalDate.now();
		System.out.println("Overdue Mail Sent");
		message.setText("Hello,\n"
						+"The Patient:"+patientName+" is in overdue.\n"
						+"Dose: "+Dose+"\n"
						+"clinic name: "+clinicName+"\n"
						+"Next Injection: "+nextInjection+"\n"
						+"Date: "+date);
		mailSender.send(message);
	}
}
