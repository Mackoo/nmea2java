package com.nmea2qgis;

public class Funkcje_parse 
{
    public static String[] getline(String line)
    {
        return line.split(",");
    }
	
    public static String parse_gga(String line)
    {
        String query="";
        String[] data = getline(line);
        
        
        String key = data[1];
        String utc=data[1].substring(0,2)+':'+data[1].substring(2,4)+':'+data[1].substring(4,6);
        String latt=(String.valueOf(Double.valueOf(data[2].substring(0,2))+Double.valueOf(data[2].substring(2))/60)).replace(",",".");
        int ind=data[4].indexOf('.');
        String lonn = String.valueOf(Double.valueOf(data[4].substring(0, ind - 2)) + Double.valueOf(data[4].substring(ind - 2)) / 60).replace(",", ".");
        String numsv=data[7];
        String hdop=data[8];
        String msl=data[9];
        String geoid=data[11];
        String fixstatus=data[6];
        query="update nmea2GGA set lat="+latt+",lon="+lonn+",fixstatus="+fixstatus+",numsv="+numsv+",hdop="+hdop+",msl="+msl+",geoid="+geoid+" where utc='"+utc+"';";  
        
        return query;
    }

    public static String parse_rmc(String line)
    {
        String query="";
        String[] data = getline(line);
        String key = data[1];
        String utc=data[1].substring(0,2)+':'+data[1].substring(2,4)+':'+data[1].substring(4,6);
        String latt=(String.valueOf(Double.valueOf(data[3].substring(0,2))+Double.valueOf(data[3].substring(2))/60)).replace(",",".");
        int ind=data[5].indexOf('.');
        String lonn = String.valueOf(Double.valueOf(data[5].substring(0, ind - 2)) + Double.valueOf(data[5].substring(ind - 2)) / 60).replace(",", ".");
        String speed=data[7];
        String datastatus=data[2];
        query="update nmea2GGA set lat="+latt+",lon="+lonn+",datastatus='"+datastatus+"',speed="+speed+" where utc='"+utc+"';";
        //MessageBox.Show(query);
        return query;
    }
    public static String parse_gll(String line)
    {
        String query = "";
        String[] data = getline(line);
        String key = data[5];
        String utc = data[5].substring(0, 2) + ':' + data[5].substring(2, 4) + ':' + data[5].substring(4, 6);
        String latt=(String.valueOf(Double.valueOf(data[1].substring(0,2))+Double.valueOf(data[1].substring(2))/60)).replace(",",".");

        int ind = data[3].indexOf('.');
        String lonn = String.valueOf(Double.valueOf(data[3].substring(0, ind - 2)) + Double.valueOf(data[3].substring(ind - 2)) / 60).replace(",", ".");
        String datastatus = data[6];

        query = "update nmea2GGA set lat=" + latt + ",lon=" + lonn + ",datastatus='" + datastatus + "' where utc='" + utc + "';";
        //MessageBox.Show(query);
        return query;
    }
    
}
