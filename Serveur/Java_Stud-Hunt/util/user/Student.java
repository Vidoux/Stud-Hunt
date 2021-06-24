package util.user;

import java.util.List;

import util.User;
import util.UserTypes;

public class Student extends User {
	private String forname;
	private List<Project> projects;
	private List<School> schools;
	
	public Student(UserTypes userType, String email, String name, String forname, String password, List<Project> projects, List<Industry> industries, List<School> schools) {
		super(userType, email, name, password, industries);
		this.forname = forname;
		this.projects = projects;
		this.schools = schools;
		
	}
}
