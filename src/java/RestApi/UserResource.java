/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package RestApi;

import ejb.AdminBeanLocal;
import entity.TblReview;
import entity.TblUser;
import entity.TblWishlist;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Harsh
 */
@Path("generic")
@RequestScoped
public class UserResource {

    AdminBeanLocal adminBean = lookupAdminBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    
    //    -----------------USER TABLE CRUD STARTS HERE -------------------------------
    @GET
    @Path("ShowUser")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<TblUser> ShowUser()
    {
        return adminBean.getAllUsers();
    }
    
    @POST
    @Path("AddUser/{username}/{fname}/{lname}/{email}/{profile_img}/{contact_no}/{password}/{registration_date}/{groupname}")
    public String AddUser(@PathParam("username") String username,@PathParam("fname") String fname,@PathParam("lname") String lname,@PathParam("email") String email,@PathParam("profile_img") String profile_img,@PathParam("contact_no") Integer contact_no,@PathParam("password") String password,@PathParam("registration_date") String registration_date,@PathParam("groupname") String groupname)
    {
        try{
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(registration_date);
            return adminBean.addUser(username, fname, lname, email, profile_img, contact_no, password, date1, groupname);
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        
    }
    
    @DELETE
    @Path("DeleteUser/{userid}")
    public void DeleteUser(@PathParam("userid") int userid)
    {
        adminBean.deleteUser(userid);
    }
    
    
    @GET
    @Path("SearchUser/{userid}")
    @Produces(MediaType.APPLICATION_XML)
    public TblUser SearchUser(@PathParam("userid") int userid)
    {
        return adminBean.findUserById(userid);
    }
   
    @POST
    @Path("UpdateUser/{userid}/{username}/{fname}/{lname}/{email}/{profile_img}/{contact_no}/{password}/{registration_date}/{groupname}")
    public String UpdateUser(@PathParam("userid") Integer userid,@PathParam("username") String username,@PathParam("fname") String fname,@PathParam("lname") String lname,@PathParam("email") String email,@PathParam("profile_img") String profile_img,@PathParam("contact_no") Integer contact_no,@PathParam("password") String password,@PathParam("registration_date") String registration_date,@PathParam("groupname") String groupname)
    {
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(registration_date);
            return adminBean.updateUser(userid, username, fname, lname, email, profile_img, contact_no, password, date1, groupname);
        } 
        catch (Exception ex) 
        {
            return ex.getMessage();
        }
    }
    
    
    //    -----------------Review TABLE CRUD STARTS HERE -------------------------------
    
    @GET
    @Path("ShowReview/")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<TblReview> ShowReview()
    {
        return adminBean.getAllReview();
    }
    
    @POST
    @Path("AddReview/{rating}/{userid}/{blog_post_id}")
    public String AddReview(@PathParam("rating") String rating,@PathParam("userid") int userid,@PathParam("blog_post_id") int blog_post_id)
    {
        try{
            return adminBean.addReview(rating, userid, blog_post_id);
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        
    }
    
    @DELETE
    @Path("DeleteReview/{reviewid}/{userid}/{blog_post_id}")
    public void DeleteReview(@PathParam("review_id") int reviewid,@PathParam("userid") int userid,@PathParam("blog_post_id") int blog_post_id)
    {
        adminBean.deleteReview(reviewid, userid, blog_post_id);
    }
    
    
    @GET
    @Path("SearchReview/{review_id}")
    @Produces(MediaType.APPLICATION_XML)
    public TblReview SearchReview(@PathParam("review_id") int review_id)
    {
        return adminBean.findreviewById(review_id);
    }
   
    @POST
    @Path("UpdateReview/{review_id}/{rating}/{userid}/{blog_post_id}")
    public String UpdateReview(@PathParam("review_id") Integer review_id,@PathParam("rating") String rating,@PathParam("userid") int userid,@PathParam("blog_post_id") int blog_post_id)
    {
        try {
            return adminBean.updateReview(review_id, rating, userid, blog_post_id);
        } 
        catch (Exception ex) 
        {
            return ex.getMessage();
        }
    }

    
    //    -----------------WISHLIST TABLE CRUD STARTS HERE -------------------------------
    
    @GET
    @Path("ShowWishlist/")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<TblWishlist> ShowWishlist()
    {
        return adminBean.getAllWishlist();
    }
    
    @POST
    @Path("AddWishlist/{blog_post_id}/{userid}")
    public String AddWishlist(@PathParam("blog_post_id") int blog_post_id,@PathParam("userid") int userid)
    {
        try{
            return adminBean.addWishlist(blog_post_id, userid);
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        
    }
    
    @DELETE
    @Path("DeleteWishlist/{wishlist_id}/{blog_post_id}/{userid}")
    public void DeleteWishlist(@PathParam("wishlist_id") int wishlist_id,@PathParam("blog_post_id") int blog_post_id,@PathParam("userid") int userid)
    {
        adminBean.deleteWishlist(wishlist_id, blog_post_id, userid);
    }
    
    
    @GET
    @Path("SearchWishlist/{wishlist_id}")
    @Produces(MediaType.APPLICATION_XML)
    public TblWishlist SearchWishlist(@PathParam("wishlist_id") int wishlist_id)
    {
        return adminBean.findwishlistById(wishlist_id);
    }
   
    @POST
    @Path("UpdateWishlist/{wishlist_id}/{blog_post_id}/{userid}")
    public String UpdateWishlist(@PathParam("wishlist_id") Integer wishlist_id,@PathParam("blog_post_id") Integer blog_post_id,@PathParam("userid") int userid)
    {
        try {
            return adminBean.updateWishlist(wishlist_id, blog_post_id, userid);
        } 
        catch (Exception ex) 
        {
            return ex.getMessage();
        }
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
