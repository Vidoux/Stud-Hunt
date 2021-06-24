package util.usertypes;

import java.sql.Date;
import java.util.List;

import util.User;
import util.UserTypes;
import util.usercontent.Project;
import util.userrefernces.School;

public class Student extends User {
	private String forname;
	private int apprenticeship;
	private int internship;
	private int levelstudy;
	private String industry;
	private Date startingdate;
	private int contractlen;
	private String diploma;
	private List<Project> projects;
	private List<School> schools;
	
	public Student(String email, String name, String forname, String password) {
		super(UserTypes.STUDENT, email, name, password);
		this.forname = forname;
	}
	
	public Student(String email, String name, String forname, int apprenticeship, int internship, String password, String bio, String industry, int levelstudy, Date startingdate, int contractlen, String diploma, List<Project> projects, List<School> schools) {
		super(UserTypes.STUDENT, email, name, password, bio);
		this.forname = forname;
		this.apprenticeship = apprenticeship;
		this.internship = internship;
		this.levelstudy = levelstudy;
		this.industry = industry;
		this.startingdate = startingdate;
		this.contractlen = contractlen;
		this.diploma = diploma;
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

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
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
