package com.nmea2qgis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Sqlite db=new Sqlite();
		
		
		
		Path file = Paths.get("D:\\point_kortowo.nmea");
		try (InputStream in = Files.newInputStream(file);
		    BufferedReader reader =
		      new BufferedReader(new InputStreamReader(in))) {
		    String line = null;
		    
		    
		    PreparedStatement statement = null;
	    	String sqlInsertRecord="insert or ignore into nmea2GGA(utc,msl) values(?,0)";
	    	statement=db.connection.prepareStatement(sqlInsertRecord);
	    	int[] iNoRows=null;
		    
		    while ((line = reader.readLine()) != null) {
	    		//System.out.println(line);
		    	
		    	
		    	
		    	switch (line.substring(3, 6)) {
		    	
				case "GGA":
			        String[] data = Funkcje_parse.getline(line);
			        String key = data[1].substring(0,2)+':'+data[1].substring(2,4)+':'+data[1].substring(4,6);
			        
                    //String que = "insert or ignore into nmea2GGA(utc,msl) values('" + key + "',0);";
                    //db.query(que);
			        statement.setString(1,key);
			        statement.addBatch(); 
					break;
					
				case "RMC":
			        String[] data1 = Funkcje_parse.getline(line);
			        String key1 = data1[1].substring(0,2)+':'+data1[1].substring(2,4)+':'+data1[1].substring(4,6);
                    //String que1 = "insert or ignore into nmea2GGA(utc,msl) values('" + key1 + "',0);";
                    //db.query(que1);
			        statement.setString(1,key1);
			        statement.addBatch(); 
                   
					break;
					
				case "GLL":
			        String[] data2 = Funkcje_parse.getline(line);
			        String key2 = data2[5].substring(0,2)+':'+data2[5].substring(2,4)+':'+data2[5].substring(4,6);
                    //String que2 = "insert or ignore into nmea2GGA(utc,msl) values('" + key2 + "',0);";
                    //db.query(que2);
			        statement.setString(1,key2);
			        statement.addBatch(); 
					break;

				default:
					//System.out.println("pusta linia");
					break;
				}
		    	
		    	
		    	 	
		    }
		    iNoRows=statement.executeBatch();
		    
		    
		    
		    /*
		    InputStream in2 = Files.newInputStream(file);
		    BufferedReader reader2 =
				      new BufferedReader(new InputStreamReader(in2));
		    
		    
		    
		  
		    while ((line = reader2.readLine()) != null) {
	    		System.out.println(line);
	    		
		    	switch (line.substring(3, 6)) {
		    	
				case "GGA":
		    		String linegga=Funkcje_parse.parse_gga(line);
		    		db.query(linegga);
			        System.out.println(linegga);
					break;
					
				case "RMC":
		    		String linermc=Funkcje_parse.parse_rmc(line);
		    		db.query(linermc);
			        System.out.println(linermc);
					break;
					
				case "GLL":
		    		String linegll=Funkcje_parse.parse_gll(line);
		    		db.query(linegll);
			        System.out.println(linegll);
					break;

				default:
					System.out.println("pusta linia");
					break;
				}
		    	 	
		    }
		    
		    
		    */
		    
		    
		} catch (IOException x) {
		    System.err.println(x);
		}
		

		
		
		
		
		
		

	}

}
