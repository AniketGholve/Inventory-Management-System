package com.patient.Entity;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "documents")
@Component
public class PatientFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "doc_id")
	private String fileId;
	@Column(name = "file_name")
	private String fileName;
	@Column(name = "source_data_file")
	private String sourceDataFile;
	@Column(name = "trans_data_file")
	private String transDataFile;
	@Column(name = "source_file_format_id")
	private String sourceFileFormatId;
	@Column(name = "trans_file_format_id")
	private String transFileFormatId;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "file_status")
	private String fileStatus;
	@Column(name = "source_mime_type")
	private String sourceMimeType;
	@Column(name = "enterprise_id")
	private String enterpriseId;
	@Column(name = "location_id")
	private String locationId;
	@Column(name = "recv_through")
	private String recvthrough;
	@Column(name = "trans_mime_type")
	private String transMimeType;
	@Column(name = "msg_format_version")
	private String msgFormatVersion;
	@Column(name = "source_file_subformat_id")
	private String sourceFileSubformatId;
	@Column(name = "trans_file_subformat_id")
	private String transFileSubformatId;
	@Column(name = "request")
	private String request;
	@Column(name = "response")
	private String response;
	@Column(name = "soap_message_type")
	private String soapMessageType;
	@Column(name = "operation")
	private String operation;
	@Column(name = "sender_id")
	private String senderId;
	@Column(name = "sender_name")
	private String senderName;
	@Column(name = "receiver_id")
	private String receiverId;
	@Column(name = "receiver_name")
	private String receiverName;
	@Column(name = "message_details")
	private String messageDetails;
	@Column(name="size")
	private Long size;
	@Lob
	@Column(name="file_data",columnDefinition = "LONGBLOB")
	private byte[] fileData;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonBackReference
//	private Patient patient;
	
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	
	@JoinTable(name = "patient_documents",inverseJoinColumns = {@JoinColumn(name="patient_id",referencedColumnName = "id")},joinColumns = {@JoinColumn(name="doc_id",referencedColumnName = "doc_id")})
	private Patient patient;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSourceDataFile() {
		return sourceDataFile;
	}

	public void setSourceDataFile(String sourceDataFile) {
		this.sourceDataFile = sourceDataFile;
	}

	public String getTransDataFile() {
		return transDataFile;
	}

	public void setTransDataFile(String transDataFile) {
		this.transDataFile = transDataFile;
	}

	public String getSourceFileFormatId() {
		return sourceFileFormatId;
	}

	public void setSourceFileFormatId(String sourceFileFormatId) {
		this.sourceFileFormatId = sourceFileFormatId;
	}

	public String getTransFileFormatId() {
		return transFileFormatId;
	}

	public void setTransFileFormatId(String transFileFormatId) {
		this.transFileFormatId = transFileFormatId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getSourceMimeType() {
		return sourceMimeType;
	}

	public void setSourceMimeType(String sourceMimeType) {
		this.sourceMimeType = sourceMimeType;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getRecvthrough() {
		return recvthrough;
	}

	public void setRecvthrough(String recvthrough) {
		this.recvthrough = recvthrough;
	}

	public String getTransMimeType() {
		return transMimeType;
	}

	public void setTransMimeType(String transMimeType) {
		this.transMimeType = transMimeType;
	}

	public String getMsgFormatVersion() {
		return msgFormatVersion;
	}

	public void setMsgFormatVersion(String msgFormatVersion) {
		this.msgFormatVersion = msgFormatVersion;
	}

	public String getSourceFileSubformatId() {
		return sourceFileSubformatId;
	}

	public void setSourceFileSubformatId(String sourceFileSubformatId) {
		this.sourceFileSubformatId = sourceFileSubformatId;
	}

	public String getTransFileSubformatId() {
		return transFileSubformatId;
	}

	public void setTransFileSubformatId(String transFileSubformatId) {
		this.transFileSubformatId = transFileSubformatId;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getSoapMessageType() {
		return soapMessageType;
	}

	public void setSoapMessageType(String soapMessageType) {
		this.soapMessageType = soapMessageType;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(String messageDetails) {
		this.messageDetails = messageDetails;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public PatientFile(String fileId, String fileName, String sourceDataFile, String transDataFile,
			String sourceFileFormatId, String transFileFormatId, Date createdDate, Date updatedDate, String fileStatus,
			String sourceMimeType, String enterpriseId, String locationId, String recvthrough, String transMimeType,
			String msgFormatVersion, String sourceFileSubformatId, String transFileSubformatId, String request,
			String response, String soapMessageType, String operation, String senderId, String senderName,
			String receiverId, String receiverName, String messageDetails, Long size, byte[] fileData,
			Patient patient) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.sourceDataFile = sourceDataFile;
		this.transDataFile = transDataFile;
		this.sourceFileFormatId = sourceFileFormatId;
		this.transFileFormatId = transFileFormatId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.fileStatus = fileStatus;
		this.sourceMimeType = sourceMimeType;
		this.enterpriseId = enterpriseId;
		this.locationId = locationId;
		this.recvthrough = recvthrough;
		this.transMimeType = transMimeType;
		this.msgFormatVersion = msgFormatVersion;
		this.sourceFileSubformatId = sourceFileSubformatId;
		this.transFileSubformatId = transFileSubformatId;
		this.request = request;
		this.response = response;
		this.soapMessageType = soapMessageType;
		this.operation = operation;
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
		this.messageDetails = messageDetails;
		this.size = size;
		this.fileData = fileData;
		this.patient = patient;
	}

	public PatientFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PatientFile [fileId=" + fileId + ", fileName=" + fileName + ", sourceDataFile=" + sourceDataFile
				+ ", transDataFile=" + transDataFile + ", sourceFileFormatId=" + sourceFileFormatId
				+ ", transFileFormatId=" + transFileFormatId + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", fileStatus=" + fileStatus + ", sourceMimeType=" + sourceMimeType + ", enterpriseId="
				+ enterpriseId + ", locationId=" + locationId + ", recvthrough=" + recvthrough + ", transMimeType="
				+ transMimeType + ", msgFormatVersion=" + msgFormatVersion + ", sourceFileSubformatId="
				+ sourceFileSubformatId + ", transFileSubformatId=" + transFileSubformatId + ", request=" + request
				+ ", response=" + response + ", soapMessageType=" + soapMessageType + ", operation=" + operation
				+ ", senderId=" + senderId + ", senderName=" + senderName + ", receiverId=" + receiverId
				+ ", receiverName=" + receiverName + ", messageDetails=" + messageDetails + ", size=" + size
				+ ", fileData=" + Arrays.toString(fileData) + ", patient=" + patient + "]";
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
