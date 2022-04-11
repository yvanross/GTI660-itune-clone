package database;

import java.sql.*;


/**
 * Class that connect to the database and keep a connection
 * to it in order to send Query. This class is a little bit
 * like a socket connection
 */
public class RSQConnect
{
    Connection dbConnect = null;
    boolean isConnected = false;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    String output = new String();

    RSQQuery currentQuery= new RSQQuery();
    RSQResult result = null;

    /**
     * Info on the database requested
     *
     */
    public RSQConnect()
    {
        result = new RSQResult();
    }
    
    public RSQResult getResult()
    {
        return result;
    }
    
    
    /**
     * Function that open the connection with the database
     *
     */
    public boolean open(RSQUser user, RSQAddress address)  throws SQLException
    {
        dbConnect = null;

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } 
        catch (ClassNotFoundException e)
        {
            System.err.println("(RSQConnect) DB Driver Class not found");
            return false;
        } catch(Exception e) {
            System.err.println("(RSQConnect) No driver found");
            return false;
        }
        
        System.err.println("(RSQConnect) Connection String: " + 
                address.toString() + " " +
                user.getUsername() + " " +
                user.getPassword());

        dbConnect = DriverManager.getConnection(address.toString(), 
                                                user.getUsername(),
                                                user.getPassword());
        
        if (dbConnect != null) {
            isConnected = true;
            System.err.println("(RSQConnect) Connected to DB ");
        } else {
            isConnected = false;
        }
        
        return isConnected;
    }
    
    public boolean send(RSQQuery query)
    {
        if (query.toString().trim().matches("INSERT") || query.toString().trim().matches("UPDATE"))
            return (sendUpdate(query.toString()));
        else
            return (send(query.toString()));
    }
    
    public boolean sendUpdate(String query)
    {
        if (isConnected) {
            try {
                System.err.println("(RSQConnect) Sending query:\t" + query);
                PreparedStatement pstmt = (PreparedStatement)dbConnect.prepareStatement(query);
                int test = pstmt.executeUpdate();
                if (test >=0)
                    return true;
                else
                    return false;
                
            } catch (SQLException e) {
                if ( e.getMessage().trim().equals("No ResultSet was produced")) {
                    return true;
                } else {
                    System.err.println("(RSQConnect) Cannot send statement to DB because: " + e.getMessage());
                    return false;
                }
            }
        } else {
            return false;
        }
    }
    
    public boolean send(String query)
    {
        
        if (isConnected) {
            try {
                System.err.println("(RSQConnect) Sending query:\t" + query);
                PreparedStatement pstmt = (PreparedStatement)dbConnect.prepareStatement(query);
                
                rs = (ResultSet)pstmt.executeQuery();
                rsmd = (ResultSetMetaData)rs.getMetaData();
                result.setResultSet(rs, rsmd);
                
                if (result.extractResult()) {
                    output = "";
                    for (int i = 0; i < result.getColCount(); i ++) {
                        output = output + "\t" + result.getTitleAt(i);
                    }
                    
                    System.err.println(output);
                    System.err.println("=================================================================================");
                    output = "";
                    
                    for(int i =0; i < result.getRowCount(); i++) {
                        for(int j =0; j < result.getColCount(); j++) {
                            //System.err.println("Trace " + i + " " + j);
                            output = output + "\t" + result.getObjectAt(i, j);
                        }
                        System.err.println(output);
                        output = "";
                    }
                    
                    return true;
                }
          
            } catch (SQLException e) {
                if ( e.getMessage().trim().equals("No ResultSet was produced")) {
                    return true;
                } else {
                    System.err.println("(RSQConnect) Cannot send statement to DB because: " + e.getMessage());
                    return false;
                }
            }
        } else {
            return false;
        }
    return false; 
    }

    /**
     * Function that close the connection with the database
     *
     */
    public void close() throws SQLException
    {
        //Clean up resources, close the connection.
        if(dbConnect != null) {
            dbConnect.close();
            isConnected = false;
        }
    }
    
    public boolean isOpen()
    {
        return isConnected;
        
    }
}