package com.lpc.mode;

public class LoginModel {
	
	private String id;
	private String name;
	private int limit;

	public LoginModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 * @param limit
	 * @param pass
	 */
	public LoginModel(String id, String name, int limit) {
		super();
		this.id = id;
		this.name = name;
		this.limit = limit;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getLimit() {
		return limit;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
