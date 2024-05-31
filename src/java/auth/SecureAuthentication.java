/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import beans.LoginBean;
import io.jsonwebtoken.ExpiredJwtException;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import static jwtrest.Constants.AUTHORIZATION_HEADER;
import static jwtrest.Constants.BEARER;
import jwtrest.JWTCredential;
import jwtrest.TokenProvider;
import record.KeepRecord;

/**
 *
 * @author root
 */
@Named
@RequestScoped
public class SecureAuthentication implements HttpAuthenticationMechanism, Serializable {
@Inject IdentityStoreHandler handler;
CredentialValidationResult result;
AuthenticationStatus status;
 @Inject TokenProvider tokenProvider;
 @Inject LoginBean lbean;
    
    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext ctx) throws AuthenticationException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   String token = extractToken(ctx);
   System.out.println("Token : " + token);
      try{
        if(request.getSession().getAttribute("logged-group") == null && (request.getRequestURI().contains("AdminDashboard.jsf"))){
//            || (request.getRequestURI().contains("AboutUs.jsf")) || (request.getRequestURI().contains("Category.jsf"))|| (request.getRequestURI().contains("CategoryEdit.jsf"))|| (request.getRequestURI().contains("Contact.jsf"))|| (request.getRequestURI().contains("Display.jsf"))|| (request.getRequestURI().contains("DisplayBlogPost.jsf"))|| (request.getRequestURI().contains("DisplayCategory.jsf"))|| (request.getRequestURI().contains("DisplayPlace.jsf"))|| (request.getRequestURI().contains("EditBlogPost.jsf")) || (request.getRequestURI().contains("EditPhotos.jsf")) || (request.getRequestURI().contains("EditPlace.jsf")) || (request.getRequestURI().contains("EditRegistration.jsf")) || (request.getRequestURI().contains("EditVideos.jsf")) || (request.getRequestURI().contains("InsertBlogPost.jsf")) || (request.getRequestURI().contains("InsertPhotos.jsf")) || (request.getRequestURI().contains("InsertPlace.jsf")) || (request.getRequestURI().contains("InsertVideos.jsf")) || (request.getRequestURI().contains("ShowBlogPost.jsf")) || (request.getRequestURI().contains("ShowBlogPost_Blogger.jsf")) || (request.getRequestURI().contains("ShowCategory.jsf")) || (request.getRequestURI().contains("ShowCategory_Blogger.jsf")) || (request.getRequestURI().contains("ShowPhotos.jsf")) || (request.getRequestURI().contains("ShowPhotos_Blogger.jsf")) || (request.getRequestURI().contains("ShowPlace.jsf")) || (request.getRequestURI().contains("ShowPlace_Blogger.jsf")) || (request.getRequestURI().contains("ShowReview.jsf")) || (request.getRequestURI().contains("ShowSearchPlace.jsf")) || (request.getRequestURI().contains("ShowUser.jsf")) || (request.getRequestURI().contains("ShowVideos.jsf")) || (request.getRequestURI().contains("ShowVideos_Blogger.jsf")) || (request.getRequestURI().contains("ShowWishlist.jsf")) || (request.getRequestURI().contains("headerAdmin.jsf")) || (request.getRequestURI().contains("headerBlogger.jsf"))
            System.out.println("In Blank Session");
            try {           
                response.sendRedirect(request.getContextPath() + "/AdminLogin.jsf");
//                request.getServletContext().getRequestDispatcher("/AdminLogin.jsf").forward(request, response);
            } catch (IOException ex) {
                System.out.println("Error in Blank Session : " + ex.getMessage());
            }
        }else{
//            if(request.getSession().getAttribute("logged-group") == "User" && (request.getRequestURI().contains("AdminDashboard.jsf"))){
//                System.out.println("In Blank Session");
//                try {           
//                    response.sendRedirect(request.getContextPath() + "/AdminLogin.jsf");
////                    request.getServletContext().getRequestDispatcher("/AdminLogin.jsf").forward(request, response);
//                } catch (IOException ex) {
//                    System.out.println("Error in Blank Session : " + ex.getMessage());
//                }
//            }else if(request.getSession().getAttribute("logged-group") == "Blogger" && (request.getRequestURI().contains("AdminDashboard.jsf")) && (request.getRequestURI().contains("ShowCategory.jsf"))){
//                try {           
//                    response.sendRedirect(request.getContextPath() + "/AdminLogin.jsf");
////                    request.getServletContext().getRequestDispatcher("/AdminLogin.jsf").forward(request, response);
//                } catch (IOException ex) {
//                    System.out.println("Error in Blank Session : " + ex.getMessage());
//                }
//            }
        }
        
      if(token==null && lbean.getUsername()!=null )
      {
//          String username = request.getParameter("username");
//          String password = request.getParameter("password");
          System.out.println("In Auth");
          String username = lbean.getUsername();
          String password = lbean.getPassword();
          status = lbean.getStatus();
          Credential credential = new UsernamePasswordCredential(username, new Password(password));
          result = handler.validate(credential);
          System.out.println("In auth Status : "+ result.getStatus());
          if(result.getStatus()== Status.VALID)
          {
              status = createToken(result, ctx);
             
              status = ctx.notifyContainerAboutLogin(result);
              KeepRecord.setUsername(username);
              KeepRecord.setPassword(password);
              KeepRecord.setPrincipal(result.getCallerPrincipal());
              KeepRecord.setRoles(result.getCallerGroups());
                KeepRecord.setError("");
              lbean.setStatus(status);
              lbean.setRoles(result.getCallerGroups());
              return status;
        
        }
          else
          {              
            lbean.setErrorStatus("User or Password is not correct !");
            KeepRecord.setError("User or Password is not correct !");
            System.out.println(lbean.getErrorStatus());
//                    lbean.setStatus(AuthenticationStatus.SEND_FAILURE);
//                    response.sendRedirect(request.getContextPath() + "/AdminLogin.jsf");
            return ctx.redirect(request.getContextPath() + "/AdminLogin.jsf");
                  //              response.setHeader("Location", request.getContextPath() + "/AdminLogin.jsf");
//                  request.getServletContext().getRequestDispatcher("/AdminLogin.jsf").forward(request, response);
              
//              return ctx.redirect(request.getContextPath() + "/AdminLogin.jsf");
//              JOptionPane.showMessageDialog(null, "Invalid Username or Password");              
          }
      }
     if(KeepRecord.getToken()!=null)
     {
          Credential credential1 = new UsernamePasswordCredential(KeepRecord.getUsername(), new Password(KeepRecord.getPassword()));
          result = handler.validate(credential1);
          status = createToken(result, ctx);
          ctx.notifyContainerAboutLogin(KeepRecord.getPrincipal(), KeepRecord.getRoles());
     }
      
      }
    catch(HeadlessException e)
    {
        e.getMessage();
    }
      
     if (token != null) {
            // validation of the jwt credential

            return validateToken(token, ctx);
        } else if (ctx.isProtected()) {
            // A protected resource is a resource for which a constraint has been defined.
            // if there are no credentials and the resource is protected, we response with unauthorized status
            return ctx.responseUnauthorized();
        }
      return ctx.doNothing();
    }
    
    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenProvider.validateToken(token)) {
                JWTCredential credential = tokenProvider.getCredential(token);
               // System.out.println("JWTAuthenticationMechanism-Token Validated");
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());

            }
            // if token invalid, response with unauthorized status
            return context.responseUnauthorized();
        } catch (ExpiredJwtException eje) {
            //LOGGER.log(Level.INFO, "Security exception for user {0} - {1}", new String[]{eje.getClaims().getSubject(), eje.getMessage()});
            return context.responseUnauthorized();
        }
    }

    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        if (!isRememberMe(context)) {
            // if (true) {
            String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
            //context.getRequest().getSession().setAttribute("token", jwt);
            KeepRecord.setToken(jwt);
            context.getResponse().addHeader(AUTHORIZATION_HEADER, BEARER + jwt);
            System.out.println("Token Value"+ jwt);
        
           
        }
        System.out.println("JWTAuthenticationMechanism - Token Created");
        
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    /**
     * To extract the JWT from Authorization HTTP header
     *
     * @param context
     * @return The JWT access tokens
     */
    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length(), authorizationHeader.length());
          //  System.out.println("JWTAuthenticationMechanism - Extract Tokens");
            return token;
        }
        return null;
    }

    /**
     * this function invoked using RememberMe.isRememberMeExpression EL
     * expression
     *
     * @param context
     * @return The remember me flag
     */
    public Boolean isRememberMe(HttpMessageContext context) {
        return Boolean.valueOf(context.getRequest().getParameter("rememberme"));
    }
}
