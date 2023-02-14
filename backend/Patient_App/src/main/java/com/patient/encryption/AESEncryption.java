package com.patient.encryption;



import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;

@Configuration
//first denote what type of object we will store in a database and second denote the how we store it in a database
public class AESEncryption implements AttributeConverter<Object, String>{
	
	//this key size can be of 16 24 or 32 byte and each char is of 2 byte so the length of the key can be 8 12 16 length
	
	public final static String  encryptionKey="this-is-patient-";
	private final String  encryptionCipher="AES";
	
	//this will use the encryption key string and the encryption cipher 
	private Key key;
	private Cipher cipher;


	
	//we are using the private method here because we are not using it outside the class
	private Key getKey() {
		if(key==null)  key=new SecretKeySpec(encryptionKey.getBytes(), encryptionCipher);
		return key;
	}

	 
	private Cipher getCipher() throws GeneralSecurityException {
		if(cipher==null)  cipher=Cipher.getInstance(encryptionCipher);
		return cipher;
	}
	
	private void initCipher(int encryptMode) throws GeneralSecurityException{
		getCipher().init(encryptMode, getKey());
	}

	@Override
	@SneakyThrows
	public String convertToDatabaseColumn(Object attribute) {
		// TODO Auto-generated method stub
		if(attribute==null) return null;
		
			try {
				initCipher(cipher.ENCRYPT_MODE);

				byte[] bytes=SerializationUtils.serialize(attribute);

				return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
			
			
		
		}
		
		
	

	@SuppressWarnings("deprecation")
	@Override
	@SneakyThrows
	public Object convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		if(dbData==null) {
			return null;
	}
			
		
		try {
			
			
			initCipher(cipher.DECRYPT_MODE);

			byte[] bytes=getCipher().doFinal(Base64.getDecoder().decode(dbData));

			return SerializationUtils.deserialize(bytes);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  null;
		
		
	
		
	}

}
