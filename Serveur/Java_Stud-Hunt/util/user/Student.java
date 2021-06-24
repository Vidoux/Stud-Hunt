package util.user;

import java.util.List;

import util.User;
import util.UserTypes;

public class Student extends User {
	private String forname;
	private int apprenticeship;
	private int internship;
	private List<Project> projects;
	private List<School> schools;
	
	public Student(UserTypes userType, String email, String name, String forname, int apprenticeship, int internship, String password, List<Project> projects, List<Industry> industries, List<School> schools) {
		super(userType, email, name, password, industries);
		this.forname = forname;
		this.apprenticeship = apprenticeship;
		this.internship = internship;
		this.projects = projects;
		this.schools = schools;
		
	}

	public String getForname() {
		return forname;
	}

	public void setForname(String forname) {
		this.forname = forname;
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

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}
}
