package database;


/**
* Class that connect to the database and process the authentification 
* of the user. User information are stored into Oracle information.  
*/ 

public class RSQAuthentification
{
	/**
	* Constructor of the Authentification class
	*/
	public RSQAuthentification() 
	{ 
			
	} 	

	public boolean isValid(RSQUser user) 
	{ 
		// check if the user specified has the right to 
		// connect to the database ???
		return true; 
	}
}