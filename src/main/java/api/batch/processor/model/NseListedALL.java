package api.batch.processor.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NseListedALL {
	
	@Id
	private Integer code;
	private String symbol;
	private String isin;
	private String company;
	private String ldate;
	private String faceval;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLdate() {
		return ldate;
	}
	public void setLdate(String ldate) {
		this.ldate = ldate;
	}
	public String getFaceval() {
		return faceval;
	}
	public void setFaceval(String faceval) {
		this.faceval = faceval;
	}
	
	

}
