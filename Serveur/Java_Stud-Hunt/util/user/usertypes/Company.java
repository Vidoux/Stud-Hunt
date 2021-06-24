package util.user.usertypes;

import java.util.List;

import util.User;
import util.UserTypes;
import util.user.usercontent.JobOffer;

public class Company extends User {
	private List<JobOffer> jobOffers;

	public Company(String email, String name, String password) {
		super(UserTypes.COMPANY, email, name, password);
	}
	
	public Company(String email, String name, String password, String bio) {
		super(UserTypes.COMPANY, email, name, password, bio);
	}
	
	public Company(String email, String name, String password, String bio, List<JobOffer> jobOffers) {
		super(UserTypes.COMPANY, email, name, password);
		this.jobOffers = jobOffers;
	}

	public List<JobOffer> getJobOffers() {
		return jobOffers;
	}

	public void setJobOffers(List<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}
}
