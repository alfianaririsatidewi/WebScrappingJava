package tutorial;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Main {
private static final String EBAY_GLOBAL_DEALS_URL = "https://www.tokopedia.com/search?navsource=home&sc=24&st=product&q=mobile%20phones";
    
    private static final String PRODUCT_NAME_CLASS = "css-12fc2sy";
    private static final String PRODUCT_DESCRIPTION_CLASS = "css-1wa8o67";
    private static final String PRODUCT_PRICE_CLASS = "css-a94u6c";
    private static final String PRODUCT_IMG_CLASS= "div.css-1sfomcl img";
    private static final String PRODUCT_STORE_SELECTOR = "css-1rn0irl";
    private static final String PRODUCT_RATING_CLASS = "css-1ffszw6";

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		 try {
	            // First we create an output file for the desired output
	            File output = new File("Result.csv");
	            if (output.createNewFile()) {
	              System.out.println("File created: " + output.getName());
	            } else {
	              System.out.println("File already exists.");
	            }
	            // We initiate a new FileWriter object for us to write into our output file
	            FileWriter writer = new FileWriter("Result.csv");
	            	            
	            Document doc = Jsoup.connect(EBAY_GLOBAL_DEALS_URL).get();
	         
	            Elements productElements = doc.getElementsByAttributeValueMatching("data-testid","divSRPContentProducts");
	            for (Element productElement : productElements) {
	  	             Elements name = productElement.getElementsByClass(PRODUCT_NAME_CLASS);
	  	             if (!name.isEmpty()) {
		  	              writer.append("Product Name = ");
		  	            writer.append('\n');
		  	              for (int j=0;j<name.size();j++) {
		  	            	writer.append(name.get(j).text());
		  	            	writer.append('\n');
		  	            	
		  	              }
		  	         }
	  	           
		             Elements description = productElement.getElementsByClass(PRODUCT_DESCRIPTION_CLASS);
		             if (!description.isEmpty()) {
		  	              writer.append("Product Description = ");
		  	            writer.append('\n');
		  	              for (int j=0;j<description.size();j++) {
			            	writer.append("Product Description = "+description.get(j).text());
		  	            	writer.append('\n');
		  	            	
		  	              }
		  	         }
		  	          
	  	             Elements price = productElement.getElementsByClass(PRODUCT_PRICE_CLASS);
	  	             if (!price.isEmpty()) {
		  	              writer.append("Product Price = ");
		  	            writer.append('\n');
		  	              for (int j=0;j<price.size();j++) {
			            		writer.append(price.get(j).text());
		  	            	writer.append('\n');
		  	            	
		  	              }
		  	         }
	  	             
	  	             Elements imgUrl = productElement.select(PRODUCT_IMG_CLASS);
	  	             if (!imgUrl.isEmpty()) {
	   	              writer.append("Product Image Url = ");
	   	           writer.append('\n');
	   	              for (int j=0;j<imgUrl.size();j++) {
	 	            		writer.append(imgUrl.get(j).attr("src"));
	   	            	writer.append('\n');
	   	            	
	   	              }
	   	             }
	  	             Elements store = productElement.getElementsByClass(PRODUCT_STORE_SELECTOR);
	  	             if (!store.isEmpty()) {
	 	              writer.append("Product Store = ");
	 	             writer.append('\n');
	 	              for (int j=0;j<store.size();j++) {
	 	            	writer.append(store.get(j).text());
	 	            	writer.append('\n');
	 	            	
	 	              }
	 	             }
	  	             Elements rating = productElement.getElementsByClass(PRODUCT_RATING_CLASS);
	  	             if (!rating.isEmpty()) {
	 	              writer.append("Product Rating = ");
	 	             writer.append('\n');
	 	              for (int j=0;j<rating.size();j++) {
	 	            	writer.append(rating.get(j).text());
	 	            	writer.append('\n');
	 	            	
	 	              }
	 	             }
	  	            
	  	          writer.close();
	            
	        }    
		 }
	        // In case of any IO errors, we want the messages written to the console
	        catch (IOException e) {
	            e.printStackTrace();
	        }

		
	}

}
