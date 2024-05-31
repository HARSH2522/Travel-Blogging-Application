    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import RestApi.CategoryClient;
import RestApi.UserClient;
import ejb.AdminBeanLocal;
import entity.TblBlogPost;
import entity.TblReview;
import javax.inject.Named;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Harsh
 */
@Named(value = "blogPostCDIBean")
@SessionScoped
//@RequestScoped
public class BlogPostCDIBean implements Serializable {
    
    @EJB 
    AdminBeanLocal adminBean;

    CategoryClient cc = new CategoryClient();
    UserClient uc = new UserClient();
    
    private HttpSession session;
    int blog_post_id;
    String blog_title;
    String blog_description;
    Date date;
    int place_id;
    String rating;
    Collection<TblReview> reviews;

    public String deletePost(Integer blogId,Integer placeId,Integer reviewId,Integer userId){
        System.out.print("Delete Post : " + blogId);
        try{
            uc.DeleteReview(reviewId.toString(), userId.toString(), blogId.toString());
            cc.DeleteBlogPOst(blogId.toString(), placeId.toString());
//            adminBean.deleteReview(reviewId, userId, blogId);
//            adminBean.deleteBlogPost(blogId, placeId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "ShowBlogPost_Blogger.jsf?faces-redirect=true";
    }
    
    public Collection<TblReview> getReviews(Integer uid) {
        reviews = adminBean.getBlogPostOfUser(uid);
        return reviews;
    }

    public void setReviews(Collection<TblReview> reviews) {
        this.reviews = reviews;
    }

    public int getBlog_post_id() {
        return blog_post_id;
    }

    public void setBlog_post_id(int blog_post_id) {
        this.blog_post_id = blog_post_id;
    }

    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }

    public String getBlog_description() {
        return blog_description;
    }

    public void setBlog_description(String blog_description) {
        this.blog_description = blog_description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }
    
    
    public String AddBlogPost()
    {
        DateFormat dte1 = new SimpleDateFormat("yyyy-MM-dd");        
        String ddate = dte1.format(date);

        String successmsg = cc.AddBlogPost(blog_title, blog_description, ddate, place_id+"",rating);       
        if(successmsg!="")
        {
            return "ShowBlogPost.jsf?faces-redirect=true";
        }
        return "Inserted";
    }
    
    public List<TblBlogPost> ShowBlogPost()
    {
        Response rs = cc.ShowBlogPost(Response.class);
        ArrayList<TblBlogPost> slist = new ArrayList<TblBlogPost>();
        
        GenericType<Collection<TblBlogPost>> gcstate = new GenericType<Collection<TblBlogPost>>(){};
        slist = ( ArrayList<TblBlogPost> ) rs.readEntity(gcstate);
        return slist;
    }
    
    public String DeleteBlogPost(Integer blog_post_id,int place_id)
    {
        System.out.print("delete id :" + blog_post_id);
        cc.DeleteBlogPOst(blog_post_id.toString(), place_id+"");
        return "ShowBlogPost.jsf?faces-redirect=true";
    }
    
    public String SearchBlogPost(int bpid)
    {
        Response rs = cc.SearchgBlogPost(Response.class, bpid+"");
        GenericType<TblBlogPost> gcstate = new GenericType<TblBlogPost>(){};
        TblBlogPost tbp = rs.readEntity(gcstate);
        
        blog_post_id=tbp.getBlogPostId();
        blog_title=tbp.getBlogTitle();
        blog_description=tbp.getBlogDescription();
        date=tbp.getDate();
        place_id=tbp.getPlaceId().getPlaceId();
        return "EditBlogPost.jsf?faces-redirect=true";
    }
    
    public String UpdateBlogPost()
    {
        DateFormat dte1 = new SimpleDateFormat("yyyy-MM-dd");        
        String ddate = dte1.format(date);
        
        cc.UpdateBlogPost(blog_post_id+"", blog_title, blog_description, ddate, place_id+"");
        return "ShowBlogPost.xhtml?faces-redirect=true";
    }
    
    
    public Collection<TblBlogPost> ShowBlogPostsCount()
    {
         Collection<TblBlogPost>slist=adminBean.getAllBlogPost();
         return slist;
    }
    
      
    public Collection<TblBlogPost> ShowBlogPostByDate()
    {
         Collection<TblBlogPost>slist=adminBean.findBlogPostByDate();
         return slist;
    }
    
    public Collection<TblBlogPost> ShowPlaceCount()
    {
         Collection<TblBlogPost>slist=adminBean.getAllBlogPost();
         return slist;
    }

    
    public BlogPostCDIBean() {
    }
    
}
