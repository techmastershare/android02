package com.techmaster.countrytab;

public class Country {
	private String name;
	private String abbreviation;
	private String region;
	private String resourceId;
	private String coutryDetail;

	public Country() {
		// TODO Auto-generated constructor stub
	}

	public Country(String name, String abbreviation, String region,
			String resourceFilePath) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.region = region;
		this.resourceId = resourceFilePath;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getCoutryDetail() {
		return coutryDetail;
	}

	public void setCoutryDetail(String coutryDetail) {
		this.coutryDetail = coutryDetail;
	}
	
	
}
