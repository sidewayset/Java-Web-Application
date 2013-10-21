/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MySQL;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Vahagn Madatyan
 */
public class MySQL {
    
   private String url;
   private String driver;
   private String user;
   private String pass;
   private String database;
   private Connection conn;
   private Statement stmt;
   private String ann;
   public boolean error=false;

   
    public String getAnn()
        {
        return ann;
        }
    
    public void setAnn(int count,int code,String state,String message)
        {
        /*
            StringWriter stringWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(stringWriter,true);
            writer.println("MySQL Exceptions: "+String.valueOf(count));
            writer.println("MySQL Error Code: "+String.valueOf(code));
        */
            
            this.ann="MySQL Exceptions: "+String.valueOf(count);
            this.ann+="MySQL Error Code: "+String.valueOf(code)+"\n";
            this.ann+="MySQL State: "+state+"\n";
            this.ann+="MySQL Error Message "+message;
            
        }

    public MySQL()
        {
        url = "jdbc:mysql://mysql.joomla.sidewayset.com/javawebapp";
        driver = "com.mysql.jdbc.Driver";
        user = "sidewaysetphp";
        pass = "set2set";
        connect();
        }


   
   public void connect()
       {
           try
               {
                System.out.println("Registering Driver");
                Class.forName(driver).newInstance();
                System.out.println("Driver Registered");
                System.out.println("Connecting To Database");
                conn= DriverManager.getConnection(url,user,pass);
                System.out.println("Connected To Database");
               }
           catch (Exception e)
               {
                e.printStackTrace();
                
               }
       }
   
   public void delete(String tablename)
   {
       try
       {
     
       System.out.println("Declaring Query");
       stmt= conn.createStatement();
       String sql ="DROP TABLE "+tablename+"";
       stmt.executeUpdate(sql);
       System.out.println(tablename+" Deleted");
       conn.close();
       this.ann="Your Table "+tablename+" Is Deleted";
       }
       
       catch(SQLException se)
       {
           SQLException(se);
            
       } catch (Exception e) {
           e.printStackTrace();
       }
       
   }
   public void createTable(String tableName)
   {
       try
           {
               System.out.println("Creating Table");
               stmt=conn.createStatement();
               String sql = "CREATE TABLE "+tableName+""+
                            "(id INTEGER not NULL, "+
                            " first VARCHAR(255), "+
                            " last  VARCHAR(255), "+
                            " age INTEGER, "+
                            " PRIMARY KEY( id ))";
               stmt.executeUpdate(sql);
               System.out.println("Table "+tableName+" Is Created" );
               conn.close();
               this.ann="Your Table "+tableName+" Is Created";
               error=false;
           }
       catch (SQLException se)
           {
            SQLException(se);
            
           }
   }
           
   public void insertRecord(String id,String firstName, String lastName,String age)
       {
           
          
           
           try
               {
                   System.out.println("Inserting Records...");
                   stmt=conn.createStatement();
                   String sql = "INSERT INTO mytest "+
                                "VALUES ('"+id+"','"+firstName+"','"+lastName+"','"+age+"')";
                   stmt.executeUpdate(sql);
                   conn.close();
                   error=false;
                   this.ann="Data Is Inserted!";
               }
           catch (SQLException se)
               {
               SQLException(se);
              
 
                }
    }
   
   public void updateRecord()//String tableName,String data,String newData,String id
       {
           try
               {
                    String id="300";
                    System.out.println("Updating Records...");
                    stmt=conn.createStatement();
                    String sql = "UPDATE mytest"+
                                 "SET first = a WHERE id in (300)"; //SET '"+data+"' = '"+newData+"' WHERE id in ("+id+")
                    stmt.executeUpdate(sql);
                    conn.close();
                    error=false;
                    this.ann="Data Is Updated";
               }
           catch (SQLException se)
               {
                    SQLException(se);
               }
       }
   
   
   public void SQLException(SQLException se)
       {
                         error=true;
                       int count=1;
                       int code;
                       String state;
                       String message;

                       while(se !=null)
                           {

                            System.out.println("SQLException " + count);
                            code=se.getErrorCode();
                            System.out.println("Code: " + code);
                            state=se.getSQLState();
                            System.out.println("SqlState: " + state);
                            message=se.getMessage();
                            System.out.println("Error Message: " + message);
                            se = se.getNextException();
                            setAnn(count, code, state, message);
                            count++;
                        }
       }
   
}
       
   
    
  
/*
 finally
       {
           //finally block used to close resources
           try
           {
              if(stmt!=null)
                {
                    conn.close();
                }
           }
           catch(SQLException se)
           {
               
           }
           try
           {
               if(conn!=null)
               {
                   conn.close();
               }
           }
           catch(SQLException se)
                   {
                    se.printStackTrace();
                   }
       }
*/

