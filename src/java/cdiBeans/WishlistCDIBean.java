/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import RestApi.UserClient;
import ejb.AdminBeanLocal;
import entity.TblReview;
import entity.TblWishlist;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Harsh
 */
@Named(value = "wishlistCDIBean")
@SessionScoped
public class WishlistCDIBean implements Serializable {

    @EJB
    private AdminBeanLocal adminBean;
            
    UserClient uc = new UserClient();
    
    int wishlist_id;
    int blog_post_id;
    int userid;

    public UserClient getUc() {
        return uc;
    }

    public void setUc(UserClient uc) {
        this.uc = uc;
    }

    public int getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(int wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public int getBlog_post_id() {
        return blog_post_id;
    }

    public void setBlog_post_id(int blog_post_id) {
        this.blog_post_id = blog_post_id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    public String AddWishlist(Integer blog_post,Integer uid)
    {
        System.out.print("Bog post Id :" + blog_post);
        System.out.print("User Id :" + uid);
//        String successmsg = uc.AddWishlist(blog_post+"", uid+"");
        String successmsg = adminBean.addWishlist(blog_post, uid);
        if(successmsg!=null)
        {
            return "ShowWishlist.jsf?faces-redirect=true";
        }
            return "ShowWishlist.jsf?faces-redirect=true";
    }
    
    public Collection<TblWishlist> ShowWishlist(Integer uid)
    {
//        Response rs = uc.ShowWishlist(Response.class);
//        ArrayList<TblWishlist> slist = new ArrayList<TblWishlist>();
//        
//        GenericType<Collection<TblWishlist>> gcstate = new GenericType<Collection<TblWishlist>>(){};
//        slist = ( ArrayList<TblWishlist> ) rs.readEntity(gcstate);
        Collection<TblWishlist> slist = adminBean.getAllWishlistOfUser(uid);    
        return slist;
    }
    
     public String DeleteWishlist(int wishlist_id,int blog_post_id,int userid)
    {
        uc.DeleteWishlist(wishlist_id+"", blog_post_id+"", userid+"");
        return "ShowWishlist.xhtml?faces-redirect=true";
    }
    
    public String SearchWishlist(int wid)
    {
        Response rs = uc.SearchWishlist(Response.class, wid+"");
        GenericType<TblWishlist> gcstate = new GenericType<TblWishlist>(){};
        TblWishlist tr = rs.readEntity(gcstate);
        
        wishlist_id=tr.getWishlistId();
        blog_post_id=tr.getBlogPostId().getBlogPostId();
        userid=tr.getUserid().getUserid();
        
        return "EditWishlist.xhtml?faces-redirect=true";
    }
    
    public String UpdateWishlist()
    {   
        uc.UpdateWishlist(wishlist_id+"", blog_post_id+"", userid+"");
        return "ShowWishlist.xhtml?faces-redirect=true";
    }
    
    
    public WishlistCDIBean() {
    }
    
}
