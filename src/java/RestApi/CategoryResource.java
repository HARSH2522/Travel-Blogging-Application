/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package RestApi;

import ejb.AdminBeanLocal;
import entity.TblBlogPost;
import entity.TblCategory;
import entity.TblPhotos;
import entity.TblPlace;
import entity.TblVideos;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Harsh
 */
@Path("generic")
public class CategoryResource {

    AdminBeanLocal adminBean = lookupAdminBeanLocal();

    @Context
    private UriInfo context;
    
    public CategoryResource() {
    }
//    -----------------CATEGORY TABLE CRUD STARTS HERE -------------------------------
    @GET
    @Path("ShowCategory/")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<TblCategory> ShowCategory()
    {
        return adminBean.getAllCategorys();
    }
    
    @POST
    @Path("AddCategory/{categoryname}")
    public String AddCategory(@PathParam("categoryname") String categoryname)
    {
        TblCategory c = new TblCategory();
        c.setCategoryName(categoryname);
        return adminBean.addCategory(categoryname);
    }
    
    @DELETE
    @Path("DeleteCategory/{categoryid}")
    public void DeleteCategory(@PathParam("categoryid") int categoryid)
    {
        adminBean.deleteCategory(categoryid);
    }
    
    
    @GET
    @Path("SearchCategory/{categoryid}")
    @Produces(MediaType.APPLICATION_XML)
    public TblCategory searchCategory(@PathParam("categoryid") int categoryid)
    {
        return adminBean.findCategoryById(categoryid);
    }
    
    @POST
    @Path("UpdateCategory/{categoryid}/{categoryname}")
    public String UpdateCategory(@PathParam("categoryid") int categoryid,@PathParam("categoryname") String categoryname)
    {
//        TblCategory c = new TblCategory();
//        c.setCategoryId(categoryid);
//        c.setCategoryName(categoryname);
        return adminBean.updateCategory(categoryid, categoryname);
    }
    
//    -----------------PLACE TABLE CRUD STARTS HERE -------------------------------
    
    @GET
    @Path("ShowPlace/")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<TblPlace> ShowPlace()
    {
        return adminBean.getAllPlaces();
    }
    
    @POST
    @Path("AddPlace/{place_name}/{address}/{description}/{categoryid}")
    public String AddPlace(@PathParam("place_name") String place_name,@PathParam("address") String address,@PathParam("description") String description,@PathParam("categoryid") Integer categoryid)
    {

        String msg= adminBean.addPlace(place_name, address, description, categoryid);
        return msg;
    }
    
    @DELETE
    @Path("DeletePlace/{placeid}/{categoryid}")
    public void DeletePlace(@PathParam("placeid") int placeid,@PathParam("categoryid") int categoryid)
    {
        adminBean.deletePlace(placeid, categoryid);
    }
    
    
    @GET
    @Path("SearchPlace/{placeid}")
    @Produces(MediaType.APPLICATION_XML)
    public TblPlace searchPlace(@PathParam("placeid") int placeid)
    {
        return adminBean.findPlaceById(placeid);
    }
    
    @POST
    @Path("UpdatePlace/{placeid}/{place_name}/{address}/{description}/{categoryid}")
    public String UpdatePlace(@PathParam("placeid") int placeid,@PathParam("place_name") String place_name,@PathParam("address") String address,@PathParam("description") String description,@PathParam("categoryid") Integer categoryid)
    {
        return adminBean.updatePlace(placeid, place_name, address, description, categoryid);
    }
    
    @GET
    @Path("ShowSearchPlace/{place_name}")
    @Produces(MediaType.APPLICATION_XML)        
    public List<TblPlace> ShowSearchPlace(@PathParam("place_name") String place_name)
    {
        return adminBean.ShowSearchPlaces(place_name);
    }
    
//-----------------------BLOGPOST TABLE CRUD STARTS HERE -------------------------------
    
    @GET
    @Path("ShowBlogPost/")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<TblBlogPost> ShowBlogPost()
    {
        return adminBean.getAllBlogPost();
    }
    
    @POST
    @Path("AddBlogPost/{blog_title}/{blog_description}/{date}/{place_id}/{rating}")
    public String AddBlogPost(@PathParam("blog_title") String blog_title,@PathParam("blog_description") String blog_description,@PathParam("date") String date,@PathParam("place_id") Integer place_id,@PathParam("rating") String rating)
    {
        try{
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        
            String msg = adminBean.addBlogPost(blog_title, blog_description, date1, place_id,rating);
            return msg;
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    
    @DELETE
    @Path("DeleteBlogPost/{blog_post_id}/{place_id}")
    public void DeleteBlogPOst(@PathParam("blog_post_id") int blog_post_id,@PathParam("place_id") Integer place_id)
    {
        adminBean.deleteBlogPost(blog_post_id, place_id);
    }
    
    
    @GET
    @Path("SearchBlogPost/{blog_post_id}")
    @Produces(MediaType.APPLICATION_XML)
    public TblBlogPost SearchgBlogPost(@PathParam("blog_post_id") int blog_post_id)
    {
        return adminBean.findBlogPostById(blog_post_id);
    }
    
    @POST
    @Path("UpdateBlogPost/{blog_post_id}/{blog_title}/{blog_description}/{date}/{place_id}")
    public String UpdateBlogPost(@PathParam("blog_post_id") int blog_post_id,@PathParam("blog_title") String blog_title,@PathParam("blog_description") String blog_description,@PathParam("date") String date,@PathParam("place_id") Integer place_id)
    {
        try{
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return adminBean.udpateBlogPost(blog_post_id, blog_title, blog_description, date1, place_id);
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    
    
//-----------------------PHOTOS TABLE CRUD STARTS HERE -------------------------------
    
    @GET
    @Path("ShowPhotos")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<TblPhotos> ShowPhotos()
    {
        return adminBean.getAllPhotos();
    }
    
    @POST
    @Path("InsertPhotos/{photo_url}/{blog_post_id}")
    public String AddPhotos(@PathParam("photo_url") String photo_url,@PathParam("blog_post_id") Integer blog_post_id)
    {
        String msg = adminBean.addPhotos(photo_url, blog_post_id);
        return msg;
    }
    
    @DELETE
    @Path("DeletePhotos/{photo_id}/{blog_post_id}")
    public void DeletePhotos(@PathParam("photo_id") Integer photo_id,@PathParam("blog_post_id") Integer blog_post_id)
    {
        adminBean.deletePhotos(photo_id, blog_post_id);
    }
    
    @GET
    @Path("SearchPhotos/{photo_id}")
    @Produces(MediaType.APPLICATION_XML)
    public TblPhotos SearchPhotos(@PathParam("photo_id") Integer photo_id)
    {
            return adminBean.findPhotosById(photo_id);
    }
    
    @POST
    @Path("UpdatePhotos/{photo_id}/{photo_url}/{blog_post_id}")
    public String UpdatePhotos(@PathParam("photo_id") Integer photo_id,@PathParam("photo_url") String photo_url,@PathParam("blog_post_id") Integer blog_post_id)
    {
        return adminBean.updatePhotos(photo_id, photo_url, blog_post_id);
    }
            
    //-----------------------VIDEOS TABLE CRUD STARTS HERE -------------------------------
    
    @GET
    @Path("ShowVideos/")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<TblVideos> ShowVideos()
    {
        return adminBean.getAllVideos();
    }
    
    @POST
    @Path("InsertVideo/{video_url}/{blog_post_id}")
    public String AddVideos(@PathParam("video_url") String video_url,@PathParam("blog_post_id") Integer blog_post_id)
    {
        String msg = adminBean.addVideos(video_url, blog_post_id);
        return msg;
    }
    
    @DELETE
    @Path("DeleteVideos/{video_id}/{blog_post_id}")
    public void DeleteVideos(@PathParam("video_id") Integer video_id,@PathParam("blog_post_id") Integer blog_post_id)
    {
        adminBean.deletevideos(video_id, blog_post_id);
    }
    
    @GET
    @Path("SearchVideos/{video_id}")
    @Produces(MediaType.APPLICATION_XML)
    public TblVideos SearchVideos(@PathParam("video_id") Integer video_id)
    {
            return adminBean.findvideosById(video_id);
    }
    
    @POST
    @Path("UpdateVideos/{video_id}/{video_url}/{blog_post_id}")
    public String UpdateVideos(@PathParam("video_id") Integer video_id,@PathParam("video_url") String video_url,@PathParam("blog_post_id") Integer blog_post_id)
    {
        return adminBean.updateVideos(video_id, video_url, blog_post_id);
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
