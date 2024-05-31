/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import RestApi.CategoryClient;
import RestApi.UserClient;
import ejb.AdminBeanLocal;
import entity.TblPhotos;
import entity.TblReview;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Harsh
 */
@Named(value = "photosCDIBean")
@SessionScoped
public class PhotosCDIBean implements Serializable {

    AdminBeanLocal adminBean = lookupAdminBeanLocal();
    UserClient uc = new UserClient();
    CategoryClient cc = new CategoryClient();
    @Inject
    private HttpSession session;
    int photo_id;
    String photo_url;
    int blog_post_id;
    private Part file;
    String blog_desc;
    String blog_title;
    String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
    
    
    public String getBlog_title() {
        return blog_title;
    }

    public void setBlog_title(String blog_title) {
        this.blog_title = blog_title;
    }
    
    public String getBlog_desc() {
        return blog_desc;
    }

    public void setBlog_desc(String blog_desc) {
        this.blog_desc = blog_desc;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public int getBlog_post_id() {
        return blog_post_id;
    }

    public void setBlog_post_id(int blog_post_id) {
        this.blog_post_id = blog_post_id;
    }
    
    
    public String AddPhotos() {
        if (file != null) 
        {
            try {
//                session.getAttribute("uid");
                String fileName = file.getSubmittedFileName();
                InputStream in = file.getInputStream();
                File uploadedFile = new File("C:\\Drives\\D\\Java-codes\\TravelBlog\\web\\uploads\\" + fileName);
                FileOutputStream out = new FileOutputStream(uploadedFile);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1)
                {
                    out.write(buffer, 0, bytesRead);
                }
                String successmsg=cc.AddPhotos(fileName, blog_post_id+"");
                if(successmsg!="")
                {
                    return "ShowPhotos.xhtml?faces-redirect=true";
                }
                return "Inserted";
            } 
            catch (Exception ex) 
            {
                return ex.getMessage();
            }
        }
        return "InsertPhotos.jsf?faces-redirect=true";
    }
    
    public List<TblPhotos> ShowPhotos()
    {
        Response rs = cc.ShowPhotos(Response.class);
        ArrayList<TblPhotos> slist = new ArrayList<TblPhotos>();
        
        GenericType<Collection<TblPhotos>> gcstate = new GenericType<Collection<TblPhotos>>(){};
        slist = ( ArrayList<TblPhotos> ) rs.readEntity(gcstate);
        return slist;
    }
    
    public String DeletePhoto(int photo_id,int blog_post_id)
    {
        cc.DeletePhotos(photo_id+"", blog_post_id+"");
        return "ShowPhotos.jsf?faces-redirect=true";
    }
    
    public String SearchPhotos(int pid)
    {
        Response rs = cc.SearchPhotos(Response.class, pid+"");
        GenericType<TblPhotos> gcstate = new GenericType<TblPhotos>(){};
        TblPhotos tp = rs.readEntity(gcstate);
        
        photo_id=tp.getPhotoId();
        photo_url=tp.getPhotoUrl();
        blog_post_id=tp.getBlogPostId().getBlogPostId();
        return "EditPhotos.jsf?faces-redirect=true";
    }
    
    public String SearchPhotosByID(int pid)
    {
        Response rs = cc.SearchPhotos(Response.class, pid+"");
        GenericType<TblPhotos> gcstate = new GenericType<TblPhotos>(){};
        TblPhotos tp = rs.readEntity(gcstate);
        
        photo_id=tp.getPhotoId();
        photo_url=tp.getPhotoUrl();
        setBlog_post_id(tp.getBlogPostId().getBlogPostId());
        blog_desc=tp.getBlogPostId().getBlogDescription();
        blog_title=tp.getBlogPostId().getBlogTitle();
        
        return "DisplayBlogPost.jsf?faces-redirect=true";
    }
    
    public String UpdatePhotos()
    {
        if (file != null) 
        {
            try{
                String fileName = file.getSubmittedFileName();
                InputStream in = file.getInputStream();
                File uploadedFile = new File("C:\\Drives\\D\\Java-codes\\TravelBlog\\web\\uploads\\" + fileName);
                FileOutputStream out = new FileOutputStream(uploadedFile);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1)
                {
                    out.write(buffer, 0, bytesRead);
                }
                cc.UpdatePhotos(photo_id+"", fileName, blog_post_id+"");
                return "ShowPhotos.jsf?faces-redirect=true";
            }
            catch(Exception ex)
            {
                return ex.getMessage();
            }
        }
        return "ShowPhotos.xhtml?faces-redirect=true";
    }
    
    public String AddReview()
    {
        String successmsg = uc.AddReview(rating,session.getAttribute("uid")+"", blog_post_id+"");
        if(successmsg!=null)
        {
            return "ShowReview.jsf?faces-redirect=true";
        }
        return "Inserted";
    }
    
    
     public Collection<TblReview> ShowPostReview()
    {
         Collection<TblReview>slist=adminBean.getReviewByPostID(this.getBlog_post_id());
         return slist;
    }
    
    /**
     * Creates a new instance of PhotosCDIBean
     */
    public PhotosCDIBean() {
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
