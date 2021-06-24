package util.usercontent;

import java.sql.Date;

public class JobOffer {
	private int offerType;
	private String email;
	private int apprenticeship;
	private int internship;
	private int levelstudy;
    private String industry;
	private Date startingdate;
	private int contractlen;
	   
	public JobOffer(int offerType) {
		this.setOfferType(offerType);
	}

	public int getOfferType() {
		return offerType;
	}

	public void setOfferType(int offerType) {
		this.offerType = offerType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getApprenticeship() {
		return apprenticeship;
	}

	public void setApprenticeship(int apprenticeship) {
		this.apprenticeship = apprenticeship;
	}

	public int getInternship() {
		return internship;
	}

	public void setInternship(int internship) {
		this.internship = internship;
	}

	public int getLevelstudy() {
		return levelstudy;
	}

	public void setLevelstudy(int levelstudy) {
		this.levelstudy = levelstudy;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Date getStartingdate() {
		return startingdate;
	}

	public void setStartingdate(Date startingdate) {
		this.startingdate = startingdate;
	}

	public int getContractlen() {
		return contractlen;
	}

	public void setContractlen(int contractlen) {
		this.contractlen = contractlen;
	}

}
