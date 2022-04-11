package database;



/**
* Generic container for object retreived from the database  
*
*/
 
public class RSQKey
{
	int type; 
	// changed to object in order to accomodate everything
	int value = 0; 
	public static final int INT =0;  
	public static final int CHAR =1;  
		
	public RSQKey()
	{
		type = INT; 
	}

	public int getType() 
	{ 
		return type; 		
	} 
	
	public int getValue() 
	{ 
		return value; 		
	} 
	
	

}