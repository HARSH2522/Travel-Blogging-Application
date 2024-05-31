/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package RestApi;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:UserResource [generic]<br>
 * USAGE:
 * <pre>
 *        UserClient client = new UserClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Harsh
 */
public class UserClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/TravelBlog/webresources";

    public UserClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("generic");
    }

    public <T> T ShowUser(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ShowUser");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public String AddReview(String rating, String userid, String blog_post_id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("AddReview/{0}/{1}/{2}", new Object[]{rating, userid, blog_post_id})).request().post(null, String.class);
    }

    public <T> T ShowWishlist(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ShowWishlist");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public String UpdateWishlist(String wishlist_id, String blog_post_id, String userid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UpdateWishlist/{0}/{1}/{2}", new Object[]{wishlist_id, blog_post_id, userid})).request().post(null, String.class);
    }

    public void DeleteUser(String userid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("DeleteUser/{0}", new Object[]{userid})).request().delete();
    }

    public <T> T SearchUser(Class<T> responseType, String userid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SearchUser/{0}", new Object[]{userid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T ShowReview(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ShowReview");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public String AddUser(String username, String fname, String lname, String email, String profile_img, String contact_no, String password, String registration_date, String groupname) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("AddUser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{username, fname, lname, email, profile_img, contact_no, password, registration_date, groupname})).request().post(null, String.class);
    }

    public <T> T SearchReview(Class<T> responseType, String review_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SearchReview/{0}", new Object[]{review_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void DeleteReview(String reviewid, String userid, String blog_post_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("DeleteReview/{0}/{1}/{2}", new Object[]{reviewid, userid, blog_post_id})).request().delete();
    }

    public String UpdateReview(String review_id, String rating, String userid, String blog_post_id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UpdateReview/{0}/{1}/{2}/{3}", new Object[]{review_id, rating, userid, blog_post_id})).request().post(null, String.class);
    }

    public String UpdateUser(String userid, String username, String fname, String lname, String email, String profile_img, String contact_no, String password, String registration_date, String groupname) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UpdateUser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}", new Object[]{userid, username, fname, lname, email, profile_img, contact_no, password, registration_date, groupname})).request().post(null, String.class);
    }

    public void DeleteWishlist(String wishlist_id, String blog_post_id, String userid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("DeleteWishlist/{0}/{1}/{2}", new Object[]{wishlist_id, blog_post_id, userid})).request().delete();
    }

    public String AddWishlist(String blog_post_id, String userid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("AddWishlist/{0}/{1}", new Object[]{blog_post_id, userid})).request().post(null, String.class);
    }

    public <T> T SearchWishlist(Class<T> responseType, String wishlist_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SearchWishlist/{0}", new Object[]{wishlist_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
