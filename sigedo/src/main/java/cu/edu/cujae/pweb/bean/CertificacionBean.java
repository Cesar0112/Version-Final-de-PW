package cu.edu.cujae.pweb.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CertificacionBean {
	private String names;
	private String surnames;
	private String schollarCourse;
	private int year;
	private String subject;
	private int evaluation;

	public CertificacionBean() {
	}


	public CertificacionBean(String names, String surnames, String schollarCourse, int year, String subject, int evaluation) {
		super();
		this.schollarCourse = schollarCourse;
		this.names = names;
		this.surnames = surnames;
		this.year = year;
		this.subject = subject;
		this.evaluation = evaluation;
	}

	public String getSchollarCourse() {
		return schollarCourse;
	}

	public void setSchollarCourse(String schollarCourse) {
		this.schollarCourse = schollarCourse;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}
	public String getSurnames() {
		return surnames;
	}
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
}
