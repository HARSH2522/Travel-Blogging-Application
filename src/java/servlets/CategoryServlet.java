/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.AdminBeanLocal;
import entity.TblCategory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harsh
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends HttpServlet {

    @EJB
    private AdminBeanLocal adminBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            
            try{
               //adminBean.addCategory("Mountain");
               
               //Update code 
               //adminBean.updateCategory(2, "Mountttt");
               
               //Delete
               //adminBean.deleteCategory(2);
               
               
                out.println("Data of id 3");
                TblCategory ct = adminBean.findCategoryById(3);
                out.println("Id : " + ct.getCategoryId()+ "<br>");
                out.println("Username : " + ct.getCategoryName()+ "<br>");

                
                Collection<TblCategory> category = adminBean.getAllCategorys();
                for (TblCategory c : category) {
                out.println("Id : " + c.getCategoryId()+ "<br>");
                out.println("Username : " + c.getCategoryName()+ "<br>");
                out.print("<hr>");
            }
            }
            
            catch(Exception ex)
            {
                out.print(ex.getMessage());
            }
            
            
            
            
            out.println("<h1>Servlet CategoryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

}
