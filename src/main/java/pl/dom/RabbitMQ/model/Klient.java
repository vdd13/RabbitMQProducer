package pl.dom.RabbitMQ.model;

import lombok.Data;

public class Klient {

	private String fName;
	private String lName;
	private Long id;
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Klient [fName=" + fName + ", lName=" + lName + ", id=" + id + "]";
	}

}
