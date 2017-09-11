package model;

import java.io.Serializable;

public class Company implements Serializable{
	
	private long id;
	private String name;
	
	/* CONSTRUCTEURS */
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Company(long id) {
		super();
		this.id = id;
	}

	public Company(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/* GETTERS & SETTERS */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
