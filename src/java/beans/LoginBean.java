package beans;

import ejb.AdminBeanLocal;
import entity.TblUser;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import static javax.security.enterprise.AuthenticationStatus.SUCCESS;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import record.KeepRecord;


/**
 *
 * @author root
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
    
    
    private String username;
    private String password;
    private Set<String> roles;
    private String errorstatus;
    private AuthenticationStatus status;
    @Inject private SecurityContext ctx;
   @EJB AdminBeanLocal abl;

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getErrorStatus() {
        return errorstatus;
    }

    public void setErrorStatus(String status) {
        this.errorstatus = status;
    }

    public String login()
    {
        System.out.println(username);
        try{
       FacesContext context = FacesContext.getCurrentInstance();
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
         HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//      request.getSession().setAttribute("logged-group","");
     status=   ctx.authenticate(request, response, withParams().credential(credential));

   // AuthenticationStatus mystatus = AuthenticationStatus.;
     
       if (status.equals(SEND_CONTINUE)) {
            // Authentication mechanism has send a redirect, should not
            // send anything to response from JSF now. The control will now go into HttpAuthenticationMechanism
           context.responseComplete();
       } 
     
     if(status.equals(SUCCESS))
        {
            TblUser udata = (TblUser)abl.findUserByUsername(KeepRecord.getUsername());            
            System.out.println("userdata : "+udata.getUserid());
            request.getSession().setAttribute("uid", udata.getUserid());
            if(roles.contains("Admin")){            
                request.getSession().setAttribute("logged-group","Admin");
                return "/AdminDashboard.jsf?faces-redirect=true";
            }
            else if(roles.contains("User")){            
                request.getSession().setAttribute("logged-group","User");
                return "/UserHome.jsf?faces-redirect=true";
            }
            else if(roles.contains("Blogger")){
                request.getSession().setAttribute("logged-group","Blogger");         
                return "/headerBlogger.jsf?faces-redirect=true";
            }
       
        }
     else if(status.equals(SEND_FAILURE))
     {
         System.out.println("In Loginbean status : "+status);
          setErrorStatus("User Name or Password may be wrong");
//          return "AdminLogin.jsf";
     }
       
        }
        catch(Exception e)
        {
             errorstatus= "User Name or Password may be wrong";
            System.out.println("Error in Login bean : "+e.getMessage());
        }
        return "/AdminLogin.jsf";
    }
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        
       // errorstatus="";
        setErrorStatus(KeepRecord.getError());        
       System.out.print("error status : "+errorstatus);
    }
    
    public String logout()
    {
        try{
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            request.getSession().invalidate();
            KeepRecord.setToken(null);
            return "AdminLogin.jsf?faces-redirect=true";
        }
        catch(ServletException e)
        {
            System.out.println("In logout : " + e.getMessage());
        }
        return "Error";
    }
    
}