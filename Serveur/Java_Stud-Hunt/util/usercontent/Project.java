package util.usercontent;

public class Project {
	private String projectName;
	private String projectBio;
	private int realisation_year;

	public Project(String projectName, String projectBio, int realisation_year) {
		this.projectName = projectName;
		this.projectBio = projectBio;
		this.realisation_year = realisation_year;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectBio() {
		return projectBio;
	}

	public void setProjectBio(String projectBio) {
		this.projectBio = projectBio;
	}

	public int getRealisation_year() {
		return realisation_year;
	}

	public void setRealisation_year(int realisation_year) {
		this.realisation_year = realisation_year;
	}

}
