/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import RestApi.CategoryClient;
import entity.TblCategory;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Harsh
 */
@Named(value = "categoryCDIBean")
@SessionScoped
public class CategoryCDIBean implements Serializable {

    CategoryClient cc = new CategoryClient();
    
    int categoryid;
    String categoryname;

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String addCategory()
    {
        TblCategory ct= new TblCategory();
        ct.setCategoryName(categoryname);

        String successmsg = cc.AddCategory(categoryname);
        if(successmsg!="")
        {
            return "ShowCategory.xhtml";
        }
        return "code inserted";
    }
    
    
    public List<TblCategory> showCategory()
    {
        Response rs=cc.ShowCategory(Response.class);
        ArrayList<TblCategory> slist = new ArrayList<TblCategory>();

        GenericType<Collection<TblCategory>> gcstate = new GenericType<Collection<TblCategory>>(){};
        slist = ( ArrayList<TblCategory> ) rs.readEntity(gcstate);
        return slist;
    }
    
    public String DeleteCategory(int cid)
    
    {
        cc.DeleteCategory(cid+"");
        return "ShowCategory.xhtml?faces-redirect=true";
    }
    
    public String searchCategory(int catgoryid)
    {
        Response rs=cc.searchCategory(Response.class,catgoryid+ "");
        GenericType<TblCategory> gstate=new GenericType<TblCategory>(){};
        TblCategory tc=rs.readEntity(gstate);
        categoryid=tc.getCategoryId();
        categoryname=tc.getCategoryName();
        return "CategoryEdit.xhtml?faces-redirect=true";
    }
    
    public String updateCategory()
    {
        TblCategory c = new TblCategory();
        c.setCategoryId(categoryid);
        c.setCategoryName(categoryname);
        cc.UpdateCategory(categoryid+"", categoryname);
        return "ShowCategory.xhtml?faces-redirect=true";
    }
    
    
    /**
     * Creates a new instance of CategoryCDIBean
     */
    public CategoryCDIBean() {
    }
    
}
