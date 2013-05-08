package com.techmaster.countrytab;

import java.util.HashMap;
import java.util.List;

public class CountriesModel {
	
	private static CountriesModel mCountriesModel;
	private HashMap<String, List<Country>> map;
    protected CountriesModel(){
    	
    }
    
    public static CountriesModel getInstance(){
    	if(mCountriesModel == null){
    		mCountriesModel = new CountriesModel();
    	}
    	return mCountriesModel;
    }

	public HashMap<String, List<Country>> getMap() {
		return map;
	}

	public void setMap(HashMap<String, List<Country>> map) {
		this.map = map;
	} 
	
	public List<Country> getAbbreviation(String continental) {
		return (List<Country>) this.map.get(continental);
	}
}
