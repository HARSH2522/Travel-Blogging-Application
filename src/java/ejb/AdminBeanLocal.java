/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.TblBlogPost;
import entity.TblCategory;
import entity.TblPhotos;
import entity.TblPlace;
import entity.TblReview;
import entity.TblUser;
import entity.TblVideos;
import entity.TblWishlist;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Harsh
 */
@Local
public interface AdminBeanLocal {
    Collection<TblReview> getBlogPostOfUser(Integer uid);
    
    String addUser(String username,String fname,String lname,String email,String profile_img,Integer contact_no,String password,Date registration_date,String groupname );
    String updateUser(Integer userid,String username,String fname,String lname,String email,String profile_img,Integer contact_no,String password,Date registration_date,String groupname );
    void deleteUser(Integer userid);
    Collection<TblUser> getAllUsers();
    TblUser findUserById(Integer userid);   
    Object findUserByUsername(String username);
    Object findByGroupname(String groupname);
    
    String addCategory(String categoryname);
    String updateCategory(Integer categoryid,String categoryname);
    void deleteCategory(Integer categoryid);
    Collection<TblCategory> getAllCategorys();
    TblCategory findCategoryById(Integer categoryid);
    
    String addPlace(String place_name,String address,String description,Integer categoryid);
    String updatePlace(Integer placeid,String place_name,String address,String description,Integer categoryid);
    void deletePlace(Integer placeid,Integer categoryid);
    Collection<TblPlace> getAllPlaces();
    TblPlace findPlaceById(Integer placeid);
    List<TblPlace> ShowSearchPlaces(String pname);
    
    
    String addBlogPost(String blog_title,String blog_description,Date date,Integer place_id,String rating);
    String udpateBlogPost(Integer blog_post_id,String blog_title,String blog_description,Date date,Integer place_id);
    void deleteBlogPost(Integer blog_post_id,Integer place_id);
    Collection<TblBlogPost> getAllBlogPost();
    TblBlogPost findBlogPostById(Integer blog_post_id);
    Collection<TblBlogPost> findBlogPostByDate();
    
    
    String addPhotos(String photo_url,Integer blog_post_id);
    String updatePhotos(Integer photo_id,String photo_url,Integer blog_post_id);
    void deletePhotos(Integer photo_id,Integer blog_post_id);
    Collection<TblPhotos> getAllPhotos();
    TblPhotos findPhotosById(Integer photo_id);
    
    String addVideos(String video_url,Integer blog_post_id);
    String updateVideos(Integer video_id,String video_url,Integer blog_post_id);
    void deletevideos(Integer video_id,Integer blog_post_id);
    Collection<TblVideos> getAllVideos();
    TblVideos findvideosById(Integer video_id);
    
    
    String addReview(String rating,Integer userid,Integer blog_post_id);
    String updateReview(Integer review_id,String rating,Integer userid,Integer blog_post_id);
    void deleteReview(Integer review_id,Integer userid,Integer blog_post_id);
    Collection<TblReview> getAllReview();
    TblReview findreviewById(Integer review_id);
    
    String addWishlist(Integer blog_post_id,Integer userid);
    String updateWishlist(Integer wishlist_id,Integer blog_post_id,Integer userid);
    void deleteWishlist(Integer wishlist_id,Integer blog_post_id,Integer userid);
    Collection<TblWishlist> getAllWishlist();
    TblWishlist findwishlistById(Integer wishlist_id);
    Collection<TblReview> getReviewByPostID(int id);

    public Collection<TblWishlist> getAllWishlistOfUser(Integer uid);
}
