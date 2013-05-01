package ebook.ngocnk.ebookreader.models;

import java.io.InputStream;

public class Ebook {
      private String ebookName;
      private InputStream imagePath;
      
      public Ebook(String name, InputStream path){
    	  ebookName = name;
    	  imagePath = path;
      }

	public String getReaderName() {
		return ebookName;
	}

	public void setEbookName(String ebookName) {
		this.ebookName = ebookName;
	}

	public InputStream getImagePath() {
		return imagePath;
	}

	public void setImagePath(InputStream imagePath) {
		this.imagePath = imagePath;
	}
}