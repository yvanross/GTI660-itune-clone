package database;

import java.util.logging.*;

public class RSQLog
{	
	static private Level levelSevere = null; 
	static private Level levelWarning = null; 
	static private Level levelInfo = null; 
	static private Level levelConfig = null; 
	static private Level levelFine = null; 
	static private Level levelFiner = null; 
	static private Level levelFinest = null; 

    private static Logger logger = Logger.getLogger("global");
    private static FileHandler fh; 
	     
	public RSQLog() 
	{ 
		
	} 

	static public void init(String filename) 
	{ 
		try { 
			fh = new FileHandler(filename);
			XMLFormatter xf = new XMLFormatter();  
			fh.setFormatter(xf);
    		logger.getLogger("global").addHandler(fh);
    	}
    	catch (Exception e) 
    	{ 
    		System.err.println("(RSQLog) Cannot open log file"); 
    	} 
	} 
	
	static public void message(Level level, String msg ) 
	{ 
		logger.log(level, msg); 
	} 
	
	static public void message(int level, String msg ) 
	{ 
		Level l = null; 

		switch (level) { 
			case 6 :   l = Level.SEVERE; 
								break;		
			case 5 :   l = Level.WARNING;  
								break;		
			case 4 :   l = Level.INFO; 
								break;		
			case 3 :   l = Level.CONFIG; 
								break;		
			case 2 :   l = Level.FINE; 
								break;		
			case 1 :   l = Level.FINER; 
								break;		
			case 0 :   l = Level.FINEST; 
								break;		
			default : 			
								System.err.println("(RSQLog) Error log level invalid");
								return; 
		} 

		message(l, msg); 
		
	} 
}

