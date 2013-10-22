/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MySqlController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.security.rsa.RSACore;

/**
 *
 * @author development
 */
public class MySqlController extends HttpServlet{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        
      
        
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        MySQL.MySQL  query = new MySQL.MySQL();
        if(request.getParameter("delete")!=null)
            {
                String tablename=request.getParameter("table");
                query.delete(tablename);

                errorChecker(query, request, response);

            }
        else 
            if(request.getParameter("create")!=null)
                {
                    String tableDelete=request.getParameter("tablename");
                    query.createTable(tableDelete);
                    
                    errorChecker(query, request, response);
                }
        else
                if(request.getParameter("insert")!=null)
                    {
                        String id = request.getParameter("id");
                        String firstName = request.getParameter("firstName");
                        String lastName = request.getParameter("lastName");
                        String age = request.getParameter("age");
                        query.insertRecord(id, firstName, lastName, age); 
                        errorChecker(query, request, response);
                    
                    
                    }
       else
               if(request.getParameter("update")!=null)
                   {
                        int i=0;
                        String updateTable = request.getParameter("updateTable");
                        String[] data = request.getParameterValues("data");
                        while(data[i]==null && i<data.length)
                            {
                                i++;
                            }
                        String olddata = data[i];
                        String newData= request.getParameter("newData");
                        String id = request.getParameter("inID");
                        query.updateRecord(updateTable, olddata, newData, id); //updateTable, olddata, newData, id
                        errorChecker(query, request, response);
                   }
        else
                   if(request.getParameter("read")!=null)
                       {
                       /*
                            String page="readmysql.jsp";
                            PrintWriter out = response.getWriter();
                            response.setContentType("text/html");
                            List dataList = new ArrayList();
                            query.readRecord();
                            try
                               {
                                         while(query.rs.next())
                                        {
                                                dataList.add(query.rs.getInt("id"));
                                                dataList.add(query.rs.getString("first"));
                                                dataList.add(query.rs.getString("last"));
                                                dataList.add(query.rs.getInt("age"));
                                        }
                                         query.rs.close();
                                         query.stmt.close();
                               }
                           catch (Exception e)
                               {
                                System.out.println("Exception is ;"+e);
                               }
                               */
                           query.readRecord(response);
                           request.setAttribute("data", query.dataList);
                           RequestDispatcher dispatcher = request.getRequestDispatcher(query.page);
                           if(dispatcher!=null)
                               {
                                dispatcher.forward(request, response);
                               }
                       }
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    public void errorChecker(MySQL.MySQL query,HttpServletRequest request,HttpServletResponse response)
        {
            try
                {
                        if(query.error==true)
                        {
                            request.setAttribute("ann", query);
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                            
                        }
                    if(query.error==false)
                        {
                            request.setAttribute("ann", query);
                            request.getRequestDispatcher("success.jsp").forward(request, response);
                            
                        }
                }
            catch (Exception e)
                {
                  e.printStackTrace();
                }
        
        }
}
