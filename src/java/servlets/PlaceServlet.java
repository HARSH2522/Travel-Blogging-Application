/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.AdminBeanLocal;
import entity.TblPlace;
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
@WebServlet(name = "PlaceServlet", urlPatterns = {"/PlaceServlet"})
public class PlaceServlet extends HttpServlet {

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
            out.println("<title>Servlet PlaceServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            //Add Place
            //adminBean.addPlace("Saputara", "Dang", "250km from surat", 3);
            
            //Update place 
            //adminBean.updatePlace(3, "Goa", "South", "900km from surat", 1);

            //Delete Place 
            //adminBean.deletePlace(3,3);
            
            //Find specific id
//            TblPlace placee= adminBean.findPlaceById(2);
//                out.println("Id : " + placee.getPlaceId()+ "<br>");
//                out.println("Name : " + placee.getPlaceName()+ "<br>");
//                out.println("Address : " + placee.getAddress()+ "<br>");
//                out.println("Description : " + placee.getDescription()+ "<br>");
//                out.println("Category : " + placee.getCategoryId().getCategoryName()+ "<br>");
            
            
            Collection<TblPlace> place=adminBean.getAllPlaces();
            for(TblPlace p : place)
            {
                out.println("Id : " + p.getPlaceId()+ "<br>");
                out.println("Name : " + p.getPlaceName()+ "<br>");
                out.println("Address : " + p.getAddress()+ "<br>");
                out.println("Description : " + p.getDescription()+ "<br>");
                out.println("Category : " + p.getCategoryId().getCategoryName()+ "<br>");
            }
            
            out.println("<h1>Servlet PlaceServlet at " + request.getContextPath() + "</h1>");
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
