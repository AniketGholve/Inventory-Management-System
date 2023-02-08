package com.emailzip.Service;

import java.io.IOException;

import com.emailzip.Entity.EmailEntity;
import com.emailzip.Entity.ZipEntity;

import jakarta.mail.MessagingException;

public interface ZipService {
	
	public ZipEntity createData(ZipEntity zipEntity);
	
	public String zipAndMail(EmailEntity email) throws IOException,MessagingException;

}
