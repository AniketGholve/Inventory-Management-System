package com.patient.Entity;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Orders {
	
	@Id
	private int ehr_Order_Message_Id;
	private int provider_Org_id;
	private String provider_Org_Name;
	private int provider_Org_Location_Id;
	private String provider_Org_Loc_Name;
	private int order_Num;
	private int ordering_Provider_Id;
	private String ordering_Provider_First_Name;
	private String ordering_Provider_Last_Name;
	private String ordering_Provider_Middle_Name;
	private String patient_First_Name;
	private String patient_Middle_Name;
	private String patient_Last_Name;
	private int patient_Id;
	private Date patient_Dob;
	private int rfx_Ent_Products_Prod_Id;
	private int rfx_Ent_Locations_Enterprise_Id;
	private int rfx_Ent_Locations_Location_Id;
	private int order_Status;
	private int serial_Number;
	private int lot_Number;
	private Date lot_Exp_Date;
	private Long home_Phone;
	private Long office_Phone;
	private Long cell_Phone;
	private String qualifier;
	private Date ehr_Order_Date;
	private String ehr_Doc_Name;
	private String ehr_Order_Doc_Name;
	private Date ehr_Result_Date;
	private String ehr_Result_Doc_Name;
	private int src_Id;
	private int doc_Id;
	
	public Orders() {}

	public Orders(int ehr_Order_Message_Id, int provider_Org_id, String provider_Org_Name, int provider_Org_Location_Id,
			String provider_Org_Loc_Name, int order_Num, int ordering_Provider_Id, String ordering_Provider_First_Name,
			String ordering_Provider_Last_Name, String ordering_Provider_Middle_Name, String patient_First_Name,
			String patient_Middle_Name, String patient_Last_Name, int patient_Id, Date patient_Dob,
			int rfx_Ent_Products_Prod_Id, int rfx_Ent_Locations_Enterprise_Id, int rfx_Ent_Locations_Location_Id,
			int order_Status, int serial_Number, int lot_Number, Date lot_Exp_Date, Long home_Phone, Long office_Phone,
			Long cell_Phone, String qualifier, Date ehr_Order_Date, String ehr_Doc_Name, String ehr_Order_Doc_Name,
			Date ehr_Result_Date, String ehr_Result_Doc_Name, int src_Id, int doc_Id) {
		super();
		this.ehr_Order_Message_Id = ehr_Order_Message_Id;
		this.provider_Org_id = provider_Org_id;
		this.provider_Org_Name = provider_Org_Name;
		this.provider_Org_Location_Id = provider_Org_Location_Id;
		this.provider_Org_Loc_Name = provider_Org_Loc_Name;
		this.order_Num = order_Num;
		this.ordering_Provider_Id = ordering_Provider_Id;
		this.ordering_Provider_First_Name = ordering_Provider_First_Name;
		this.ordering_Provider_Last_Name = ordering_Provider_Last_Name;
		this.ordering_Provider_Middle_Name = ordering_Provider_Middle_Name;
		this.patient_First_Name = patient_First_Name;
		this.patient_Middle_Name = patient_Middle_Name;
		this.patient_Last_Name = patient_Last_Name;
		this.patient_Id = patient_Id;
		this.patient_Dob = patient_Dob;
		this.rfx_Ent_Products_Prod_Id = rfx_Ent_Products_Prod_Id;
		this.rfx_Ent_Locations_Enterprise_Id = rfx_Ent_Locations_Enterprise_Id;
		this.rfx_Ent_Locations_Location_Id = rfx_Ent_Locations_Location_Id;
		this.order_Status = order_Status;
		this.serial_Number = serial_Number;
		this.lot_Number = lot_Number;
		this.lot_Exp_Date = lot_Exp_Date;
		this.home_Phone = home_Phone;
		this.office_Phone = office_Phone;
		this.cell_Phone = cell_Phone;
		this.qualifier = qualifier;
		this.ehr_Order_Date = ehr_Order_Date;
		this.ehr_Doc_Name = ehr_Doc_Name;
		this.ehr_Order_Doc_Name = ehr_Order_Doc_Name;
		this.ehr_Result_Date = ehr_Result_Date;
		this.ehr_Result_Doc_Name = ehr_Result_Doc_Name;
		this.src_Id = src_Id;
		this.doc_Id = doc_Id;
	}

	public int getEhr_Order_Message_Id() {
		return ehr_Order_Message_Id;
	}

	public int getProvider_Org_id() {
		return provider_Org_id;
	}

	public String getProvider_Org_Name() {
		return provider_Org_Name;
	}

	public int getProvider_Org_Location_Id() {
		return provider_Org_Location_Id;
	}

	public String getProvider_Org_Loc_Name() {
		return provider_Org_Loc_Name;
	}

	public int getOrder_Num() {
		return order_Num;
	}

	public int getOrdering_Provider_Id() {
		return ordering_Provider_Id;
	}

	public String getOrdering_Provider_First_Name() {
		return ordering_Provider_First_Name;
	}

	public String getOrdering_Provider_Last_Name() {
		return ordering_Provider_Last_Name;
	}

	public String getOrdering_Provider_Middle_Name() {
		return ordering_Provider_Middle_Name;
	}

	public String getPatient_First_Name() {
		return patient_First_Name;
	}

	public String getPatient_Middle_Name() {
		return patient_Middle_Name;
	}

	public String getPatient_Last_Name() {
		return patient_Last_Name;
	}

	public int getPatient_Id() {
		return patient_Id;
	}

	public Date getPatient_Dob() {
		return patient_Dob;
	}

	public int getRfx_Ent_Products_Prod_Id() {
		return rfx_Ent_Products_Prod_Id;
	}

	public int getRfx_Ent_Locations_Enterprise_Id() {
		return rfx_Ent_Locations_Enterprise_Id;
	}

	public int getRfx_Ent_Locations_Location_Id() {
		return rfx_Ent_Locations_Location_Id;
	}

	public int getOrder_Status() {
		return order_Status;
	}

	public int getSerial_Number() {
		return serial_Number;
	}

	public int getLot_Number() {
		return lot_Number;
	}

	public Date getLot_Exp_Date() {
		return lot_Exp_Date;
	}

	public Long getHome_Phone() {
		return home_Phone;
	}

	public Long getOffice_Phone() {
		return office_Phone;
	}

	public Long getCell_Phone() {
		return cell_Phone;
	}

	public String getQualifier() {
		return qualifier;
	}

	public Date getEhr_Order_Date() {
		return ehr_Order_Date;
	}

	public String getEhr_Doc_Name() {
		return ehr_Doc_Name;
	}

	public String getEhr_Order_Doc_Name() {
		return ehr_Order_Doc_Name;
	}

	public Date getEhr_Result_Date() {
		return ehr_Result_Date;
	}

	public String getEhr_Result_Doc_Name() {
		return ehr_Result_Doc_Name;
	}

	public int getSrc_Id() {
		return src_Id;
	}

	public int getDoc_Id() {
		return doc_Id;
	}

	public void setEhr_Order_Message_Id(int ehr_Order_Message_Id) {
		this.ehr_Order_Message_Id = ehr_Order_Message_Id;
	}

	public void setProvider_Org_id(int provider_Org_id) {
		this.provider_Org_id = provider_Org_id;
	}

	public void setProvider_Org_Name(String provider_Org_Name) {
		this.provider_Org_Name = provider_Org_Name;
	}

	public void setProvider_Org_Location_Id(int provider_Org_Location_Id) {
		this.provider_Org_Location_Id = provider_Org_Location_Id;
	}

	public void setProvider_Org_Loc_Name(String provider_Org_Loc_Name) {
		this.provider_Org_Loc_Name = provider_Org_Loc_Name;
	}

	public void setOrder_Num(int order_Num) {
		this.order_Num = order_Num;
	}

	public void setOrdering_Provider_Id(int ordering_Provider_Id) {
		this.ordering_Provider_Id = ordering_Provider_Id;
	}

	public void setOrdering_Provider_First_Name(String ordering_Provider_First_Name) {
		this.ordering_Provider_First_Name = ordering_Provider_First_Name;
	}

	public void setOrdering_Provider_Last_Name(String ordering_Provider_Last_Name) {
		this.ordering_Provider_Last_Name = ordering_Provider_Last_Name;
	}

	public void setOrdering_Provider_Middle_Name(String ordering_Provider_Middle_Name) {
		this.ordering_Provider_Middle_Name = ordering_Provider_Middle_Name;
	}

	public void setPatient_First_Name(String patient_First_Name) {
		this.patient_First_Name = patient_First_Name;
	}

	public void setPatient_Middle_Name(String patient_Middle_Name) {
		this.patient_Middle_Name = patient_Middle_Name;
	}

	public void setPatient_Last_Name(String patient_Last_Name) {
		this.patient_Last_Name = patient_Last_Name;
	}

	public void setPatient_Id(int patient_Id) {
		this.patient_Id = patient_Id;
	}

	public void setPatient_Dob(Date patient_Dob) {
		this.patient_Dob = patient_Dob;
	}

	public void setRfx_Ent_Products_Prod_Id(int rfx_Ent_Products_Prod_Id) {
		this.rfx_Ent_Products_Prod_Id = rfx_Ent_Products_Prod_Id;
	}

	public void setRfx_Ent_Locations_Enterprise_Id(int rfx_Ent_Locations_Enterprise_Id) {
		this.rfx_Ent_Locations_Enterprise_Id = rfx_Ent_Locations_Enterprise_Id;
	}

	public void setRfx_Ent_Locations_Location_Id(int rfx_Ent_Locations_Location_Id) {
		this.rfx_Ent_Locations_Location_Id = rfx_Ent_Locations_Location_Id;
	}

	public void setOrder_Status(int order_Status) {
		this.order_Status = order_Status;
	}

	public void setSerial_Number(int serial_Number) {
		this.serial_Number = serial_Number;
	}

	public void setLot_Number(int lot_Number) {
		this.lot_Number = lot_Number;
	}

	public void setLot_Exp_Date(Date lot_Exp_Date) {
		this.lot_Exp_Date = lot_Exp_Date;
	}

	public void setHome_Phone(Long home_Phone) {
		this.home_Phone = home_Phone;
	}

	public void setOffice_Phone(Long office_Phone) {
		this.office_Phone = office_Phone;
	}

	public void setCell_Phone(Long cell_Phone) {
		this.cell_Phone = cell_Phone;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public void setEhr_Order_Date(Date ehr_Order_Date) {
		this.ehr_Order_Date = ehr_Order_Date;
	}

	public void setEhr_Doc_Name(String ehr_Doc_Name) {
		this.ehr_Doc_Name = ehr_Doc_Name;
	}

	public void setEhr_Order_Doc_Name(String ehr_Order_Doc_Name) {
		this.ehr_Order_Doc_Name = ehr_Order_Doc_Name;
	}

	public void setEhr_Result_Date(Date ehr_Result_Date) {
		this.ehr_Result_Date = ehr_Result_Date;
	}

	public void setEhr_Result_Doc_Name(String ehr_Result_Doc_Name) {
		this.ehr_Result_Doc_Name = ehr_Result_Doc_Name;
	}

	public void setSrc_Id(int src_Id) {
		this.src_Id = src_Id;
	}

	public void setDoc_Id(int doc_Id) {
		this.doc_Id = doc_Id;
	}

}
