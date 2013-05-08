package com.techmaster.countrytab;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.sax.Element;
import android.util.Log;

public class CountryParser {

	private static final String tag = "CountryParser";
	private static final String FILE_EXTENSION = ".png";

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private final HashMap<String, List<Country>> map;
	
	/*private final List<Country> mSouthAmericanList;
	private final List<Country> mNorthAmericanList;
	private final List<Country> mEuropeList;
	private final List<Country> mAfricaList;*/
	private Context mContext;
    private CountriesModel mCoutriesModel;
	public CountryParser(Context context) {
		//this.mCountriesList = new ArrayList<Country>();
		/*this.mEuropeList = new ArrayList<Country>();
		this.mSouthAmericanList = new ArrayList<Country>();
		this.mNorthAmericanList = new ArrayList<Country>();
		this.mAfricaList = new ArrayList<Country>();*/
		this.map = new HashMap<String, List<Country>>();
		mContext = context;
		mCoutriesModel = CountriesModel.getInstance();
	}

	private String getNodeValue(NamedNodeMap map, String key) {
		String nodeValue = null;
		Node node = map.getNamedItem(key);
		if (node != null) {
			nodeValue = node.getNodeValue();
		}
		return nodeValue;
	}

	/**
	 * Parse XML file containing body part X/Y/Description
	 * 
	 * @param inStream
	 */
	public void parse() {
		try {
			// TODO: after we must do a cache of this XML!!!!
			AssetManager asset = mContext.getAssets();
			String[] continental = asset.list("country");

			this.factory = DocumentBuilderFactory.newInstance();
			this.builder = this.factory.newDocumentBuilder();
			this.builder.isValidating();
           
			for (String continentalName : continental) {
				String[] countriesName = asset.list("country/" + continentalName);
				List<Country> countriesList = new ArrayList<Country>();
				for (String country : countriesName) {
					InputStream is = asset.open("country/" + continentalName + "/" + country + "/info.xml");
					Document doc = this.builder.parse(is, null);
					doc.getDocumentElement().normalize();
					Node node = doc.getFirstChild();
					NamedNodeMap attr = node.getAttributes();
					final String countryName = getNodeValue(attr, "name");
					final String countryAbbr = getNodeValue(attr, "abbreviation");
					final String countryRegion = continentalName;
					Country c = new Country(countryName, countryAbbr,
							countryRegion, countryAbbr + FILE_EXTENSION);
					countriesList.add(c);
					Log.d(tag, c.toString()); 
				}
				map.put(continentalName, countriesList);
			}			
			mCoutriesModel.setMap(map);
			/*
			 * Document doc = this.builder.parse(null, null);
			 * 
			 * doc.getDocumentElement().normalize();
			 * 
			 * NodeList countryList = doc.getElementsByTagName("country"); final
			 * int length = countryList.getLength();
			 * 
			 * 
			 * for (int i = 0; i < length; i++) {
			 * 
			 * final NamedNodeMap attr = countryList.item(i).getAttributes();
			 * final String countryName = getNodeValue(attr, "name"); final
			 * String countryAbbr = getNodeValue(attr, "abbreviation"); final
			 * String countryRegion = getNodeValue(attr, "region");
			 * 
			 * // Construct Country object Country country = new
			 * Country(countryName, countryAbbr, countryRegion, countryAbbr +
			 * FILE_EXTENSION);
			 * 
			 * if(countryRegion.equals("Asia")){ mAsiaList.add(country); }else
			 * if(countryRegion.equals("africa")){ mAfricaList.add(country);
			 * }else if(countryRegion.equals("Europe")){
			 * mEuropeList.add(country); }else
			 * if(countryRegion.equals("S. America")){
			 * mSouthAmericanList.add(country); }else
			 * if(countryRegion.equals("N. America")){
			 * mNorthAmericanList.add(country); }
			 * 
			 * Log.d(tag, country.toString()); }
			 * 
			 * // Creat Map countrname-abbrev this.map.put("Asia", mAsiaList);
			 * this.map.put("Africa", mAfricaList); this.map.put("Europe",
			 * mEuropeList); this.map.put("North American", mNorthAmericanList);
			 * this.map.put("South American", mSouthAmericanList);
			 */
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}
