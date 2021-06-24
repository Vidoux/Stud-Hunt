package util.user;

import java.util.List;

import util.User;
import util.UserTypes;

public class Company extends User {
	private List<JobOffer> jobOffers;

	public Company(UserTypes userType, String email, String name, String password, List<JobOffer> jobOffers, List<Industry> industries) {
		super(userType, email, name, password, industries);
		this.jobOffers = jobOffers;
	}
}
