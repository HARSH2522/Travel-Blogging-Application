/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package RestApi;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:CategoryResource [generic]<br>
 * USAGE:
 * <pre>
 *        CategoryClient client = new CategoryClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Harsh
 */
public class CategoryClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/TravelBlog/webresources";

    public CategoryClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("generic");
    }

    public void DeleteCategory(String categoryid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("DeleteCategory/{0}", new Object[]{categoryid})).request().delete();
    }

    public <T> T ShowPlace(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ShowPlace");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public String AddCategory(String categoryname) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("AddCategory/{0}", new Object[]{categoryname})).request().post(null, String.class);
    }

    public String AddVideos(String video_url, String blog_post_id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("InsertVideo/{0}/{1}", new Object[]{video_url, blog_post_id})).request().post(null, String.class);
    }

    public <T> T SearchPhotos(Class<T> responseType, String photo_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SearchPhotos/{0}", new Object[]{photo_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void DeletePlace(String placeid, String categoryid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("DeletePlace/{0}/{1}", new Object[]{placeid, categoryid})).request().delete();
    }

    public <T> T ShowPhotos(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ShowPhotos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T searchCategory(Class<T> responseType, String categoryid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SearchCategory/{0}", new Object[]{categoryid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T ShowBlogPost(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ShowBlogPost");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public String AddPhotos(String photo_url, String blog_post_id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("InsertPhotos/{0}/{1}", new Object[]{photo_url, blog_post_id})).request().post(null, String.class);
    }

    public void DeletePhotos(String photo_id, String blog_post_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("DeletePhotos/{0}/{1}", new Object[]{photo_id, blog_post_id})).request().delete();
    }

    public void DeleteBlogPOst(String blog_post_id, String place_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("DeleteBlogPost/{0}/{1}", new Object[]{blog_post_id, place_id})).request().delete();
    }

    public String AddPlace(String place_name, String address, String description, String categoryid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("AddPlace/{0}/{1}/{2}/{3}", new Object[]{place_name, address, description, categoryid})).request().post(null, String.class);
    }

    public String UpdateVideos(String video_id, String video_url, String blog_post_id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UpdateVideos/{0}/{1}/{2}", new Object[]{video_id, video_url, blog_post_id})).request().post(null, String.class);
    }

    public String UpdateBlogPost(String blog_post_id, String blog_title, String blog_description, String date, String place_id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UpdateBlogPost/{0}/{1}/{2}/{3}/{4}", new Object[]{blog_post_id, blog_title, blog_description, date, place_id})).request().post(null, String.class);
    }

    public String UpdateCategory(String categoryid, String categoryname) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UpdateCategory/{0}/{1}", new Object[]{categoryid, categoryname})).request().post(null, String.class);
    }

    public String AddBlogPost(String blog_title, String blog_description, String date, String place_id, String rating) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("AddBlogPost/{0}/{1}/{2}/{3}/{4}", new Object[]{blog_title, blog_description, date, place_id, rating})).request().post(null, String.class);
    }

    public <T> T ShowVideos(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ShowVideos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T ShowSearchPlace(Class<T> responseType, String place_name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("ShowSearchPlace/{0}", new Object[]{place_name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T SearchVideos(Class<T> responseType, String video_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SearchVideos/{0}", new Object[]{video_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void DeleteVideos(String video_id, String blog_post_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("DeleteVideos/{0}/{1}", new Object[]{video_id, blog_post_id})).request().delete();
    }

    public String UpdatePlace(String placeid, String place_name, String address, String description, String categoryid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UpdatePlace/{0}/{1}/{2}/{3}/{4}", new Object[]{placeid, place_name, address, description, categoryid})).request().post(null, String.class);
    }

    public <T> T searchPlace(Class<T> responseType, String placeid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SearchPlace/{0}", new Object[]{placeid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T ShowCategory(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("ShowCategory");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public String UpdatePhotos(String photo_id, String photo_url, String blog_post_id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("UpdatePhotos/{0}/{1}/{2}", new Object[]{photo_id, photo_url, blog_post_id})).request().post(null, String.class);
    }

    public <T> T SearchgBlogPost(Class<T> responseType, String blog_post_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SearchBlogPost/{0}", new Object[]{blog_post_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
