/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import RestApi.UserClient;
import ejb.AdminBeanLocal;
import entity.TblReview;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Harsh
 */
@Named(value = "reviewBean")
@SessionScoped
public class ReviewBean implements Serializable {

    AdminBeanLocal adminBean = lookupAdminBeanLocal();
    UserClient uc = new UserClient();
    @Inject
    private HttpSession session;
    int review_id;
    String rating;
    int userid;
    int blog_post_id;

    public UserClient getUc() {
        return uc;
    }

    public void setUc(UserClient uc) {
        this.uc = uc;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBlog_post_id() {
        return blog_post_id;
    }

    public void setBlog_post_id(int blog_post_id) {
        this.blog_post_id = blog_post_id;
    }
    
    public String AddReview()
    {
        System.out.println("In add review");
//        System.out.println(rating);
//        System.out.println((Integer)session.getAttribute("uid"));
//        System.out.println(blog_post_id);
        String successmsg = uc.AddReview(rating, userid+"", blog_post_id+"");
//        String successmsg = adminBean.addReview(rating, (Integer)session.getAttribute("uid"), blog_post_id);
        if(successmsg!=null)
        {
            return "ShowReview.jsf?faces-redirect=true";
        }
        return "Inserted";
    }
    
    public Collection<TblReview> ShowReview()
    {
        
       Collection<TblReview>slist= adminBean.getAllReview();
       return slist;
        
    }
    
     public String DeleteReview(int review_id,int userid,int blog_post_id)
    {
        uc.DeleteReview(review_id+"", userid+"", blog_post_id+"");
        return "ShowReview.jsf?faces-redirect=true";
    }
    
    public String SearchReview(int rid)
    {
        Response rs = uc.SearchReview(Response.class, rid+"");
        GenericType<TblReview> gcstate = new GenericType<TblReview>(){};
        TblReview tr = rs.readEntity(gcstate);
        
        
        rating=tr.getRating();
        review_id=tr.getReviewId();
        userid=tr.getUserid().getUserid();
        blog_post_id=tr.getBlogPostId().getBlogPostId();
        
        return "EditReview.jsf?faces-redirect=true";
    }
    
    public String UpdateReview()
    {   
        uc.UpdateReview(review_id+"", rating, userid+"", blog_post_id+"");
        return "ShowReview.jsf?faces-redirect=true";
    }
    
    
    public Collection<TblReview> ShowReviewCount()
    {
         Collection<TblReview>slist=adminBean.getAllReview();
         return slist;
    }
    
     public Collection<TblReview> ShowPostReview(int pid)
    {
         Collection<TblReview>slist=adminBean.getReviewByPostID(pid);
         return slist;
    }

    
    public ReviewBean() {
    }
    
     private AdminBeanLocal lookupAdminBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (AdminBeanLocal) c.lookup("java:global/TravelBlog/AdminBean!ejb.AdminBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
