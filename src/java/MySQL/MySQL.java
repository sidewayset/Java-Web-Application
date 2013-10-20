/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MySQL;
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

    public MySQL() {
       url = "jdbc:mysql://joomla.sidewayset.com";
        driver = "com.mysql.jdbc.Driver";
        user = "sidewaysetphp";
        pass = "set2set";
        database = "javawebapp";
     
       
    }
   
   public void connect()
   {
      
     
      
   
   }
   public void delete()
   {
       try
       {
       Class.forName(driver);
       System.out.println("Connecting To Database");
       conn= DriverManager.getConnection(url+database,user,pass);
       System.out.println("Inserting Records");
       stmt= conn.createStatement();
       String sql ="DROP TABLE test";
       stmt.executeUpdate(sql);
       System.out.println("Inserted");
       conn.close();
       }
       
       catch(SQLException se)
       {
           se.printStackTrace();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
   public void insert(String data)
   {
       try
       {
       Class.forName(driver);
       System.out.println("Connecting To Database");
       conn= DriverManager.getConnection(url+database,user,pass);
       System.out.println("Inserting Records");
       stmt= conn.createStatement();
       String sql ="INSERT INTO test" + "VALUES("+data+")";
       stmt.executeQuery(sql);
       System.out.println("Inserted");
       }
       
       catch(SQLException se)
       {
           se.printStackTrace();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
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

