package api.batch.processor.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IndN500 {

	@Id
	private Integer code;
	private String company;
	private String industry;
	private String symbol;
	private String series;
	private String isinCode;
	
	public IndN500() {
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getIsinCode() {
		return isinCode;
	}

	public void setIsinCode(String isinCode) {
		this.isinCode = isinCode;
	}

	@Override
	public String toString() {
		return "IndN500 [code=" + code + ", company=" + company + ", industry=" + industry + ", symbol=" + symbol
				+ ", series=" + series + ", isinCode=" + isinCode + "]";
	}

	
	
}
