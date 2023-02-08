package com.emailzip.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.emailzip.Entity.EmailEntity;
import com.emailzip.Entity.ZipEntity;
import com.emailzip.Repo.ZipRepo;
import com.emailzip.Service.ZipService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ZipServiceImpl implements ZipService {
	
	@Autowired
	private ZipRepo zipRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	

	@Override
	public ZipEntity createData(ZipEntity zipEntity) {
		// TODO Auto-generated method stub
		ZipEntity zipInsertedEntity=zipRepo.save(zipEntity);
		File f=new File(zipEntity.getFileLocation());
		System.out.println(f.exists());
		return zipInsertedEntity;
	}

	@Override
	public String zipAndMail(EmailEntity email) throws IOException, MessagingException {
		// TODO Auto-generated method stub
		File zipfolder=new File("C:/Users/rishabh_jain/Downloads" + "/compressed.zip");
		FileOutputStream fileOutputStream = new FileOutputStream(zipfolder);
		ZipOutputStream zipOutput = new ZipOutputStream(fileOutputStream);
		List<ZipEntity> zipEntityList= zipRepo.findLatestCreatedFile();
		
		for(ZipEntity z:zipEntityList)
		{
			File f=new File(z.getFileLocation());
			FileInputStream fileInputStream = new FileInputStream(f);
			ZipEntry zipEntry = new ZipEntry(f.getName());
			zipOutput.putNextEntry(zipEntry);
			
            byte[] bytes = new byte[1024];
            int length;
            while((length = fileInputStream.read(bytes)) >= 0) {
            	zipOutput.write(bytes, 0, length);
            }
            fileInputStream.close();
			
			System.out.println(z.getFileName());

		}
		System.out.println(email.getBody());
		System.out.println(zipfolder.getAbsolutePath());
		zipOutput.close();
		fileOutputStream.close();
		
		//Sent Email Code
		String footer="\nPlease find the attachment which have the list of files that are processes in last 24 hours. \n\nThanks & Regards,\nRishabh Jain";
		
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
		mimeMessageHelper.setTo(email.getTo());
		mimeMessageHelper.setSubject(email.getSubject());
		mimeMessageHelper.setText(email.getBody()+footer);
		mimeMessageHelper.setFrom("jainrishabh2306@gmail.com");
		
		mimeMessageHelper.addAttachment(zipfolder.getName(), zipfolder);
		javaMailSender.send(mimeMessage);
		return "zip file created and sent on email";
	}
	
	
	
	
	
	
	

}
