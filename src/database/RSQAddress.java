/*
 * @(#)RSQIPAddress.java 1.0 02/07/31
 *
 * You can modify the template of this file in the
 * directory ..\JCreator\Templates\Template_1\Project_Name.java
 *
 * You can also create your own project template by making a new
 * folder in the directory ..\JCreator\Template\. Use the other
 * templates as examples.
 *
 */
 
package database;
/**
* Class that connect to the database and keep a connection
* to it in order to send Query. This class is a little bit 
* like a socket connection
*/ 
public class RSQAddress 
{	
	String address;  
	String port; 
	String dbname; 

	final String dbdrivertype = "jdbc"; 
	final String dbdrivername = "oracle";
	final String dbparam= "thin";
	
	
	public RSQAddress(String address, String port, String dbname) 
	{ 
		this.address = address;  
		this.port = port;  	
		this.dbname = dbname; 		
	}


	public String getAddress() 
	{ 
		return address; 
	}

	public String getPort() 
	{ 
		return port; 
	}

	public String getDBName() 
	{ 
		return dbname; 
	}

	public String toString() 
	{ 
                // jdbc:oracle:thin:@myhost:1521:orcl
		return (dbdrivertype + ":" + dbdrivername +  ":" + dbparam + ":@"
                                        + address + ":" + port + ":" + dbname);
	} 

}