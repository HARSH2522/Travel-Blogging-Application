/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBeans;

import RestApi.UserClient;
import ejb.AdminBeanLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;

/**
 *
 * @author Harsh
 */
@Named(value = "bloggerCDIBean")
@SessionScoped
public class BloggerCDIBean implements Serializable {

    AdminBeanLocal adminBean = lookupAdminBeanLocal();
    UserClient uc = new UserClient();
    
    int userid;
    String username;
    String fname;
    String lname;
    String email;
    String profile_img;
    int contact_no;
    String password;
    Date registration_date;
    String groupname;
    private Part file;

    public AdminBeanLocal getAdminBean() {
        return adminBean;
    }

    public void setAdminBean(AdminBeanLocal adminBean) {
        this.adminBean = adminBean;
    }

    public UserClient getUc() {
        return uc;
    }

    public void setUc(UserClient uc) {
        this.uc = uc;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public int getContact_no() {
        return contact_no;
    }

    public void setContact_no(int contact_no) {
        this.contact_no = contact_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String AddBlogger()
    {
        if(file !=null)
        {
            try {
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
                
                DateFormat dte1 = new SimpleDateFormat("yyyy-MM-dd");        
                String ddate = dte1.format(new Date());

                String successmsg = uc.AddUser(username, fname, lname, email, fileName, contact_no+"", password, ddate, "Blogger");
                if(successmsg!=null)
                {
                    return "AdminLogin.jsf?faces-redirect=true";
                }
            } 
            catch (Exception ex)
            {
                return ex.getMessage();
            }
        }
        return "Inserted";
    }
    
    
    /**
     * Creates a new instance of BloggerCDIBean
     */
    public BloggerCDIBean() {
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
