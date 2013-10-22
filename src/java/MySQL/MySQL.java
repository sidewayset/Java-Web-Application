/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MySQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
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
   public Statement stmt;
   private String ann;
   public boolean error=false;
   public ResultSet rs;
   public  String page;
   public List dataList;

   
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
         dataList = new ArrayList(); 
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
   
   public void updateRecord(String tableName,String data,String newData,String id)//
       {
           try
               {
                   
                    System.out.println("Updating Records...");
                    stmt=conn.createStatement();
                    String sql = "UPDATE "+tableName+" "+
                                 "SET "+data+" = '"+newData+"' WHERE id IN ("+id+")"; //SET '"+data+"' = '"+newData+"' WHERE id in ("+id+")
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
   
   public List readRecord(HttpServletResponse response) throws IOException
       {
       
            try
               {
                     System.out.println("Reading Records...");
                     stmt = conn.createStatement();

                     String sql = "SELECT id, first, last, age FROM mytest";
                     stmt.executeQuery(sql);
                     rs=stmt.getResultSet();
                     page="readmysql.jsp";
                     PrintWriter out = response.getWriter();
                     response.setContentType("text/html");
                     
                     while(rs.next())
                                        {
                                                dataList.add(rs.getInt("id"));
                                                dataList.add(rs.getString("first"));
                                                dataList.add(rs.getString("last"));
                                                dataList.add(rs.getInt("age"));
                                        }
                                         rs.close();
                                         stmt.close();
                     /*
                     while(rs.next()){
                        //Retrieve by column name
                        int id  = rs.getInt("id");
                        int age = rs.getInt("age");
                        String first = rs.getString("first");
                        String last = rs.getString("last");

                        //Display values
                        System.out.print("ID: " + id);
                        System.out.print(", Age: " + age);
                        System.out.print(", First: " + first);
                        System.out.println(", Last: " + last);
                     }
                     */
                     
                     
                    
               }
           catch (SQLException se)
               {
                    SQLException(se);
               }
        return dataList;
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

