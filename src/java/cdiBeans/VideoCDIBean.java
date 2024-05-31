/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import RestApi.CategoryClient;
import entity.TblPhotos;
import entity.TblVideos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.Part;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Harsh
 */
@Named(value = "videoCDIBean")
@SessionScoped
public class VideoCDIBean implements Serializable {

    CategoryClient cc = new CategoryClient();
    
    int video_id;
    String video_url;
    int blog_post_id;
    private Part file;

    public CategoryClient getCc() {
        return cc;
    }

    public void setCc(CategoryClient cc) {
        this.cc = cc;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getBlog_post_id() {
        return blog_post_id;
    }

    public void setBlog_post_id(int blog_post_id) {
        this.blog_post_id = blog_post_id;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String AddVideos() {
        if (file != null) 
        {
            try {
                String fileName = file.getSubmittedFileName();
                InputStream in = file.getInputStream();
                File uploadedFile = new File("C:\\Drives\\D\\Java-codes\\TravelBlog\\web\\video_uploads\\" + fileName);
                FileOutputStream out = new FileOutputStream(uploadedFile);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1)
                {
                    out.write(buffer, 0, bytesRead);
                }
                String successmsg=cc.AddVideos(fileName, blog_post_id+"");
                if(successmsg!="")
                {
                    return "ShowVideos.xhtml?faces-redirect=true";
                }
                return "Inserted";
            } 
            catch (Exception ex) 
            {
                return ex.getMessage();
            }
        }
        return "InsertVideos.xhtml?faces-redirect=true";
    }
    
    public List<TblVideos> ShowVideos()
    {
        Response rs = cc.ShowVideos(Response.class);
        ArrayList<TblVideos> slist = new ArrayList<TblVideos>();
        
        GenericType<Collection<TblVideos>> gcstate = new GenericType<Collection<TblVideos>>(){};
        slist = ( ArrayList<TblVideos> ) rs.readEntity(gcstate);
        return slist;
    }
    
    public String DeleteVideos(int video_id,int blog_post_id)
    {
        cc.DeleteVideos(video_id+"", blog_post_id+"");
        return "ShowVideos.xhtml?faces-redirect=true";
    }
    
    public String SearchVideos(int vid)
    {
        Response rs = cc.SearchVideos(Response.class, vid+"");
        GenericType<TblVideos> gcstate = new GenericType<TblVideos>(){};
        TblVideos tp = rs.readEntity(gcstate);
        
        video_id=tp.getVideoId();
        video_url=tp.getVideoUrl();
        blog_post_id=tp.getBlogPostId().getBlogPostId();
        return "EditVideos.xhtml?faces-redirect=true";
    }
    
    public String UpdateVideos()
    {
        if (file != null) 
        {
            try{
                String fileName = file.getSubmittedFileName();
                InputStream in = file.getInputStream();
                File uploadedFile = new File("C:\\Drives\\D\\Java-codes\\TravelBlog\\web\\video_uploads\\" + fileName);
                FileOutputStream out = new FileOutputStream(uploadedFile);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1)
                {
                    out.write(buffer, 0, bytesRead);
                }
                cc.UpdateVideos(video_id+"", fileName, blog_post_id+"");
                return "ShowVideos.xhtml?faces-redirect=true";
            }
            catch(Exception ex)
            {
                return ex.getMessage();
            }
        }
        return "ShowVideos.xhtml?faces-redirect=true";
    }
    
    
    /**
     * Creates a new instance of VideoCDIBean
     */
    public VideoCDIBean() {
    }
    
}
