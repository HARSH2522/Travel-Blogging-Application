package servlets;

import ejb.AdminBeanLocal;
import entity.TblReview;
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
@WebServlet(name = "ReviewServlet", urlPatterns = {"/ReviewServlet"})
public class ReviewServlet extends HttpServlet {

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
            out.println("<title>Servlet ReviewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            
            //To add Review 
            //adminBean.addReview("Demo", 1, 1);
            
            //To update Review
            //adminBean.updateReview(1, "xyz", 3, 1);
            
            //To Delete Review
            //adminBean.deleteReview(1, 3, 1);
            
//            Collection<TblReview> review = adminBean.getAllReview();
//            for (TblReview r : review) {
//                out.println("Id : " + r.getReviewId()+ "<br>");
//                out.println("Rating : " + r.getRating()+ "<br>");
//                out.println("Username : " + r.getUserid().getUsername()+ "<br>");
//                out.println("BlogPost : " + r.getBlogPostId().getBlogTitle()+ "<br>");
//
//                out.print("<hr>");
//            }
            
            
            
            out.println("<h1>Servlet ReviewServlet at " + request.getContextPath() + "</h1>");
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
