package model;

public class Company {
	
	private long id;
	private String name;
	
	/* CONSTRUCTEURS */
	public Company () {
		super ();
	}
	
	public Company (long id) {
		super ();
		this.id = id;
	}

	public Company (long id, String name) {
		super ();
		this.id = id;
		this.name = name;
	}

	/* GETTERS & SETTERS */
	public long getId () {
		return id;
	}
	public void setId (long id) {
		this.id = id;
	}
	public String getName () {
		return name;
	}
	public void setName (String name) {
		this.name = name;
	}
}
