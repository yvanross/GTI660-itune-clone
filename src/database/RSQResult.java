package database;

import java.util.ArrayList; 
import java.sql.*; 

/**
* Class that define a result object that will be gathered 
* from the database. This class  OracleResultSet 
* which describe the result from a Oracle database
*/ 

public class RSQResult 
{
	ResultSet rs = null; 
	ResultSetMetaData rsmd = null;  	
	
	// ArrayList that store all the row and the column 
	// dynamically. ArrayList contains a set of ArrayList 
	// which represents each Column. Reference is stored 
	// into a Ref object... 
	static ArrayList rowList = null; 
	static ArrayList title = null; 

	int nbRows = 0; 
	int nbCols = 0; 
	
	final int MAXNBROWS = 100; 
	final int MAXNBCOLS = 50; 


	boolean reallocate = false; 


	public RSQResult() 
	{ 
		rowList = new ArrayList(MAXNBROWS); 
		title = new ArrayList(MAXNBCOLS);  		

		ArrayList colList =null; 
		// allocate vector for future 
		// entry 
		// 100 rows and 50 
		for (int i =0; i < MAXNBROWS; i ++)
		{ 
			colList = new ArrayList(MAXNBCOLS);  
			for (int j =0; j < MAXNBCOLS; j ++)
			{ 
				title.add(j, new String("")); 
				colList.add(j, new String("")); 
			}
			rowList.add(i, colList); 	
		} 
	}		
	
	/**
	* Constructor of a Result set with a Oracle set and a meta data set 
	*
	*/
	public RSQResult(ResultSet rs, ResultSetMetaData rsmd) 
	{ 
		super(); 
		this.rs = rs; 	
		this.rsmd = rsmd; 			
	}		

	/**
	* Function that set the result set and the metadata
	*
	*/
	public void setResultSet(ResultSet rs, ResultSetMetaData rsmd) 
	{ 
		this.rs = rs; 	
		this.rsmd = rsmd; 	
	} 
	
	/**
	* Function that return the ArrayList containing the  
	* result. 
	*/ 
	public ArrayList getArrayList() 
	{ 
		return rowList; 
	} 

	/**
	* Function that return the Object at row and col  
	*  
	*/ 
	public Object getObjectAt(int row, int col) 
	{ 
		return (((ArrayList)rowList.get(row)).get(col))  ; 
	} 

	/**
	* Function that return the ArrayList containing the  
	* row. 
	*/ 
	public ArrayList getRowAt(int row) 
	{ 
		return ((ArrayList)rowList.get(row)); 
	} 
	

	public int getRowCount() { 
		return nbRows; 
	}

	public int getColCount() { 
		return nbCols; 
	}


	/**
	* Function that get the label for each column 
	* . 
	*/ 
	public Object getTitleAt(int i) 
	{ 
		return title.get(i); 
	} 


	/**
	* Function that expand the array if necessary 
	* Column only
	*/ 
	private void expandArray() 
	{ 
		int temp = nbRows; 
		nbRows = MAXNBROWS; 
//		System.err.println("(RSQResult) Dans expand " + nbCols); 
//		System.err.println("(RSQResult) Dans expand " + nbRows); 

		if (nbCols > MAXNBCOLS) 
		{ 				
		// reallocate each col in rows 
		title.clear(); 
		for (int i=0; i < nbCols; i ++)
		{ 
			title.add(i, new String("")); 		
		}

		ArrayList tempCols = null; 
		for (int i =0; i < MAXNBROWS; i ++)
		{ 
			tempCols = (ArrayList)rowList.get(i); 				
			tempCols.clear(); 

			for (int j=0; j < nbCols; j ++)
			{ 
				tempCols.add(j, new String("")); 		
			}
			rowList.set(i, tempCols); 				
		}				
		reallocate = true; 
		}	 

		nbRows = temp; 
//		System.err.println("(RSQResult) fin expand " + nbRows); 
	} 

	/**
	* Function that trim the array 
	*
	*/ 	
	private void trimArray()  
	{ 
		int curRows = 0; 
		int curCols = 0;
	
//		System.err.println("(RSQResult) Dans trim"); 
		if (reallocate == true) 
		{ 
			if (rowList.size() > 0)
			{
				curRows = rowList.size(); 
				curCols = ((ArrayList)rowList.get(0)).size();  
			}
			else 
			{ 
				curRows = 0; 
				curCols = 0; 
			}	
				
			// Need to trim in Rows???
			if (curRows > MAXNBROWS) 
			{ 
				int k =curRows; 
				while (k > (MAXNBROWS))
				{
					rowList.remove(rowList.get(k)); 
					k = rowList.size(); 
				};				
				curRows = k; 
			} 
			
			// Need to trim in Column ??
			if (nbCols > MAXNBCOLS)
			{ 
				int k = curCols; 
				while (k >= (MAXNBCOLS))
				{
					title.remove(title.get(k)); 
					k = title.size(); 
				};
				
				curCols = k; 
				
				for (int i =0; i < nbRows; i ++)
				{ 
					ArrayList colList = (ArrayList)rowList.get(i); 		
					
					k =nbCols; 
					while (k >= (MAXNBCOLS))
					{
						colList.remove(colList.get(k)); 
						k = colList.size(); 
					};
				}				
			}			
			reallocate = false; 		
		}
	} 
	
	
	/**
	* Function that extract the result and store it into an array
	*
	*/ 	
	public boolean extractResult() 
	{ 				
		// initialize output vector 
		if ((rsmd == null) || (rs == null)) 
		{ 
			System.err.println("(RSQResult) Result Set is null"); 
			return false; 
		}

		ArrayList colList = null; 

		/**
		* trim if necessary the array
		*/
/*		try
		{ 
			trimArray(); 
		}
		catch (Exception e) 
		{ 
			System.err.println("(RSQResult) Cannot trim Array: " + e.getMessage()); 
			return false; 
		}*/

		nbCols = 0; 
		nbRows = 0; 

		// current index 
		int row =0; 
		int col =0; 

		try 
		{
			nbCols = rsmd.getColumnCount(); 		
			
			/**
			* expand in Column if necessary the array
			*/
			try
			{
				expandArray(); 
			}
			catch (Exception e) 
			{ 
				System.err.println("(RSQResult) Cannot expand Array: " + e.getMessage()); 
				return false; 
			}
			
			// print column title 
			for (int i = 1; i <= nbCols; i ++) 
			{
				// add in arraylist 
				title.set(i-1, rsmd.getColumnLabel(i)); 		
			}

			// starting to extract data
			if (rs.next())	
			{
				if (row >= MAXNBROWS) 
				{ 
					colList = new ArrayList(nbCols); 
				}
				else 
				{ 
					colList = ((ArrayList)rowList.get(row)); 
				}

				for (int i = 1; i <= nbCols; i ++) 
				{
					colList.set((i-1), rs.getObject(i)); 		
    			}

				if (row >= MAXNBROWS) 
				{ 
					rowList.add(row, colList); 
				}
				else 
				{ 
					rowList.set(row, colList); 
				}		
				row++; 

    			// for all the rows in the result set ...
				boolean isMaxRow = false; 

    			while (rs.next())
    			{ 
					if (row >= MAXNBROWS) 
					{ 
						colList = new ArrayList(); 
						isMaxRow = true; 
					}
					else 
					{ 
						colList = ((ArrayList)rowList.get(row)); 
					}

					colList.clear(); 
					for (int i = 1; i <= nbCols; i ++) 
					{
    					colList.add((i-1), rs.getObject(i)); 		
    				}				

					if (isMaxRow)
						rowList.add(row, colList); 
    				else 
						rowList.set(row, colList); 

					row++; 
    			}    			

				if (row >= MAXNBROWS) 
				{ 
					reallocate = true;
				}
				nbRows = row; 
    		}
    		else 
    		{ 	
    			System.err.println("(RSQResult) Invalid result"); 																		
				return false; 
			}		
		}catch (SQLException e) 
		{ 
    		System.err.println("(RSQResult) SQL error: " + e.getMessage()); 																			
			return false; 
		}

		System.err.println("row: " + row); 
		System.err.println("NbRows: " + nbRows); 
		System.err.println("NbCols: " + nbCols); 

		System.err.println("NbRows: " + rowList.size()); 
		System.err.println("NbCols: " + ((ArrayList)rowList.get(0)).size()); 
		return true; 
	}
}