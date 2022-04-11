package database;


import java.sql.*; 

/**
* Class that define the current session opened by a RSQUser
* 
* Each session are ruled by these state: 
* 1) Authentification
* 2) Connection opened with the database  
* 3) Transmission of SQL Query via the Query class   
* 4) Processing of the result according to the answer of the Database 
*    ex. a collection of Object class contained in the Result class 
*/ 
public class RSQSession
{
				
	RSQAuthentification authentification = null; 	
	RSQConnect dbConnect = null; 
	
	/**
	* Constructor of a new Session
	*
	*/ 	
	public RSQSession()
	{		
		authentification = new RSQAuthentification(); 
		dbConnect = new RSQConnect(); 
	}

	public boolean createSession(RSQUser user, RSQAddress address) 
	{ 
		if (authentification.isValid(user)) 
		{ 
			try 
			{
				// create a new session
				dbConnect.open(user, address); 
			}
			catch (SQLException e) 
			{ 
				System.err.println("(RSQSession) Cannot create session"); 			
				return false; 
			}
			return true; 		
		}
		else 
		{ 
			System.err.println("(RSQSession) Authentification failed"); 
			return false;
		}
	} 


	public boolean submitQuery(RSQQuery query) 
	{ 
		try 
		{ 
			dbConnect.isOpen(); 
			dbConnect.send(query); 
		}
		catch(Exception e)  
		{
			System.err.println("(RSQSession) Error cannot submit query"); 						
			return false; 
		}		
		return true; 
	}

	public void destroySession() 
	{ 
		if (authentification != null) 
		{ 
			try 
			{
				dbConnect.close(); 
				authentification = null; 			
			}
			catch (SQLException e) 
			{
				System.err.println("(RSQSession) Error cannot close db connection"); 					
			}					
		}	

	}

}
