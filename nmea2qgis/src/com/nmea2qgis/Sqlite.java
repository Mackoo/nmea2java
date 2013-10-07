package com.nmea2qgis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteConfig;

public class Sqlite
{
	Connection connection;
	
public  Sqlite() throws ClassNotFoundException, SQLException
{
try
{
Class.forName("org.sqlite.JDBC");



SQLiteConfig config = new SQLiteConfig();
config.enableLoadExtension(true);

//connection = DriverManager.getConnection("jdbc:sqlite:spatialiteDB.db", config.toProperties());
connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Maciek\\Documents\\GIG\\magisterka\\programing\\dbspatial114.sqlite", config.toProperties());
//connection = DriverManager.getConnection("jdbc:sqlite:D:\\nmea.sqlite", config.toProperties());

Statement statement = connection.createStatement();
//ResultSet rs = statement.executeQuery("SELECT Geometry FROM Towns");
ResultSet rs = statement.executeQuery("CREATE TABLE IF NOT EXISTS nmea2GGA(utc datetime primary key, lat real,lon real, fixstatus integer, numsv integer, hdop real, msl real, geoid real,speed real, datastatus text)");
/*
while(rs.next())
{
System.out.println("Type = " + rs.getString("utc"));
}
*/
//statement.execute("select load_extension('/home/luca/workspace/SqliteTests/lib/ext/libspatialite.so')");
//

//ResultSet rs2 = statement.executeQuery("SELECT PK_UID, Area(Geometry), AsText(Centroid(Geometry)), Dimension(Geometry), GeometryType(Geometry) FROM Regions ORDER BY Area(Geometry) DESC LIMIT 5;");
/*
while(rs2.next())
{
System.out.println(" = = = = = = = = = = = = ");
System.out.println("Id = " + rs2.getString(1) );
System.out.println("Id = " + rs2.getString(2) );
System.out.println("Id = " + rs2.getString(3) );
System.out.println("Id = " + rs2.getString(4) );
System.out.println("Id = " + rs2.getString(5) );
}
*/
statement.close();

}catch (Exception e) {
System.out.println("ERROR "+ e.getMessage());
}

}
public void query(String qu) throws SQLException
{
	Statement statement = connection.createStatement();

	statement.execute(qu);
	statement.close();
	}


}







