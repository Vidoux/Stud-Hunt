package util.user;

public class Project {
	private String projectName;
	private int realisation_year;

	public Project(String projectName, int realisation_year) {
		this.projectName = projectName;
		this.realisation_year = realisation_year;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getRealisation_year() {
		return realisation_year;
	}

	public void setRealisation_year(int realisation_year) {
		this.realisation_year = realisation_year;
	}

}
