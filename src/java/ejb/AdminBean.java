/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.GroupMaster;
import entity.TblBlogPost;
import entity.TblUser;
import entity.TblReview;
import entity.TblWishlist;
//import entity.GroupMaster;
import entity.TblCategory;
import entity.TblPhotos;
import entity.TblPlace;
import entity.TblVideos;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author Harsh
 */
@Stateless
public class AdminBean implements AdminBeanLocal {

    @PersistenceContext(unitName = "TravelBlogPU")
    private EntityManager em;
    
    @Override
    public Collection<TblReview> getBlogPostOfUser(Integer uid){
        TblUser user = em.find(TblUser.class, uid);
        return user.getTblReviewCollection();
    }
    
    @Override
    public String addUser(String username, String fname, String lname, String email, String profile_img, Integer contact_no,String password, Date registration_date, String groupname) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
//        GroupMaster role = em.find(GroupMaster.class, groupid);
       try{
            Pbkdf2PasswordHashImpl hash = new Pbkdf2PasswordHashImpl();
            TblUser u = new TblUser();
            u.setUsername(username);
            u.setFname(fname);
            u.setLname(lname);
            u.setEmail(email);
            u.setProfileImg(profile_img);
            u.setContactNo(contact_no);
            u.setPassword(hash.generate(password.toCharArray()));
            u.setRegistrationDate(registration_date);
            u.setGroupname(groupname);

            em.persist(u);
            GroupMaster gm=new GroupMaster();
            gm.setGroupname(groupname);
            gm.setUsername(username);
            em.persist(gm);
            return "Inserted";
       }
       catch(Exception ex)
       {
           return ex.getMessage();
       }
    }

    @Override
    public String updateUser(Integer userid, String username, String fname, String lname, String email, String profile_img, Integer contact_no, String password, Date registration_date, String groupname) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        try{
            Pbkdf2PasswordHashImpl hash = new Pbkdf2PasswordHashImpl();
            TblUser u = em.find(TblUser.class,userid);
            u.setUsername(username);
            u.setFname(fname);
            u.setLname(lname);
            u.setEmail(email);
            u.setProfileImg(profile_img);
            u.setContactNo(contact_no);
            u.setPassword(hash.generate(password.toCharArray()));
            u.setRegistrationDate(registration_date);
            u.setGroupname(groupname);
            
            em.merge(u);
            return "Updated";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public void deleteUser(Integer userid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
       TblUser u = em.find(TblUser.class, userid);
       List<GroupMaster> gmlst = em.createNamedQuery("GroupMaster.findByUsername").setParameter("username", u.getUsername()).getResultList();
      GroupMaster gm= gmlst.get(0);
      
       em.remove(u);
        em.remove(gm);
    }

    @Override
    public Collection<TblUser> getAllUsers() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        return em.createNamedQuery("TblUser.findAll").getResultList();
    
    }

    @Override
    public TblUser findUserById(Integer userid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        TblUser u = em.find(TblUser.class, userid);
        return u;
    }

    @Override
    public Object findUserByUsername(String username){
        return em.createNamedQuery("TblUser.findByUsername").setParameter("username", username).getSingleResult();
    }
    
    @Override
    public Object findByGroupname(String groupname){
        return em.createNamedQuery("TblUser.findByGroupname").setParameter("groupname", groupname).getSingleResult();
    }
    
    @Override
    public String addPlace(String place_name, String address, String description, Integer categoryid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        try{
            TblCategory tc = em.find(TblCategory.class, categoryid);
       
            Collection<TblPlace> place = tc.getTblPlaceCollection();

            TblPlace p = new TblPlace();
            p.setPlaceName(place_name);
            p.setAddress(address);
            p.setDescription(description);
            p.setCategoryId(tc);

            place.add(p);

            tc.setTblPlaceCollection(place);

            em.persist(p);
            em.merge(tc);
            return "Inserted";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public String updatePlace(Integer placeid, String place_name, String address, String description, Integer categoryid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        try{
            TblCategory tc = em.find(TblCategory.class, categoryid);
        
            Collection<TblPlace> place = tc.getTblPlaceCollection();

            TblPlace p = em.find(TblPlace.class, placeid);

            if(place.contains(p))
            {
                place.remove(p);

                p.setPlaceName(place_name);
                p.setAddress(address);
                p.setDescription(description);
                p.setCategoryId(tc);

                place.add(p);

                tc.setTblPlaceCollection(place);

                em.merge(p);
                em.merge(tc);
                
            }
            return "Updated";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public void deletePlace(Integer placeid, Integer categoryid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        TblCategory tc = em.find(TblCategory.class, categoryid);
        Collection<TblPlace> place = tc.getTblPlaceCollection();
        TblPlace p = em.find(TblPlace.class, placeid);
        
        if(place.contains(p))
        {
            place.remove(p);
            tc.setTblPlaceCollection(place);
            em.remove(p);
        }
    }

    @Override
    public Collection<TblPlace> getAllPlaces() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("TblPlace.findAll").getResultList();
    }

    @Override
    public TblPlace findPlaceById(Integer placeid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        TblPlace p = em.find(TblPlace.class, placeid);
        return p;
        
    }

    @Override
    public List<TblPlace> ShowSearchPlaces(String pname) {
        try{
            List<TblPlace> tp=em.createNamedQuery("TblPlace.findByPlaceName").setParameter("placeName",pname).getResultList();
            return tp;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @Override
    public String addCategory(String categoryname) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
             
        try{
            TblCategory c = new TblCategory();
            c.setCategoryName(categoryname);

            em.persist(c);
            return "Inserted";
        }

        catch(Exception ex)
        {
            return ex.getMessage();
        }

    }

    @Override
    public String updateCategory(Integer categoryid, String categoryname) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        try{
            TblCategory c = em.find(TblCategory.class,categoryid);
            c.setCategoryName(categoryname);
            em.merge(c);
            return "Updated";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public void deleteCategory(Integer categoryid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        TblCategory c = em.find(TblCategory.class,categoryid);
        em.remove(c);
        
    }

    @Override
    public Collection<TblCategory> getAllCategorys() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        return em.createNamedQuery("TblCategory.findAll").getResultList();
    }

    @Override
    public TblCategory findCategoryById(Integer categoryid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        TblCategory c = em.find(TblCategory.class,categoryid);
        return c;
    }

    @Override
    public String addBlogPost(String blog_title, String blog_description, Date date, Integer place_id,String rating) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try{
            TblPlace place = em.find(TblPlace.class, place_id);
            TblReview review = em.find(TblReview.class,rating);

            TblBlogPost blogPost = new TblBlogPost();
            blogPost.setBlogTitle(blog_title);
            blogPost.setBlogDescription(blog_description);
            
            blogPost.setDate(date);
            blogPost.setPlaceId(place);
            review.setRating(rating);

            place.getTblBlogPostCollection().add(blogPost);

            em.persist(blogPost);
            em.merge(place);
            em.merge(rating);
            return "Inserted";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public String udpateBlogPost(Integer blog_post_id, String blog_title, String blog_description, Date date, Integer place_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try{
            TblBlogPost blogPost = em.find(TblBlogPost.class, blog_post_id);

            if (blogPost != null) {
                blogPost.setBlogTitle(blog_title);
                blogPost.setBlogDescription(blog_description);
                blogPost.setDate(date);

                TblPlace place = em.find(TblPlace.class, place_id);
                blogPost.setPlaceId(place);

                em.merge(blogPost);
            }
            return "Updated";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
        
    }

    @Override
    public void deleteBlogPost(Integer blog_post_id, Integer place_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        TblPlace tp = em.find(TblPlace.class, place_id);
        Collection<TblBlogPost> blogpost = tp.getTblBlogPostCollection();
        TblBlogPost tbp = em.find(TblBlogPost.class, blog_post_id);
        
        if(blogpost.contains(tbp)){
            blogpost.remove(tbp);
            tp.setTblBlogPostCollection(blogpost);
            em.remove(tbp);
        }
    }

    @Override
    public Collection<TblBlogPost> getAllBlogPost() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        return em.createNamedQuery("TblBlogPost.findAll").getResultList();
    }

    @Override
    public TblBlogPost findBlogPostById(Integer blog_post_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        TblBlogPost c = em.find(TblBlogPost.class,blog_post_id);
        return c;
    }

    @Override
    public Collection<TblBlogPost> findBlogPostByDate() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        return em.createNamedQuery("TblBlogPost.findByDate").setParameter("date", new Date()).getResultList();
    }

    @Override
    public String addPhotos(String photo_url, Integer blog_post_id) {
        try{
            TblPhotos photo = new TblPhotos();

            photo.setPhotoUrl(photo_url);

            TblBlogPost blogPost = em.find(TblBlogPost.class, blog_post_id);

            photo.setBlogPostId(blogPost);

            em.persist(photo);
            return "Inserted";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }   
    }

    @Override
    public String updatePhotos(Integer photo_id, String photo_url, Integer blog_post_id) {
        try{
            TblPhotos photo = em.find(TblPhotos.class, photo_id);

            if (photo != null) {
                photo.setPhotoUrl(photo_url);

                TblBlogPost blogPost = em.find(TblBlogPost.class, blog_post_id);

                photo.setBlogPostId(blogPost);

                em.merge(photo);
            }
            return "Updated";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public void deletePhotos(Integer photo_id, Integer blog_post_id) {
        TblBlogPost bp = em.find(TblBlogPost.class, blog_post_id);
        Collection<TblPhotos> photos = bp.getTblPhotosCollection();
        TblPhotos tp = em.find(TblPhotos.class, photo_id);
        
        if(photos.contains(tp)){
            photos.remove(tp);
            bp.setTblPhotosCollection(photos);
            em.remove(tp);
        }
    }

    @Override
    public Collection<TblPhotos> getAllPhotos() { 
        return em.createNamedQuery("TblPhotos.findAll").getResultList();
    }

    @Override
    public TblPhotos findPhotosById(Integer photo_id) {
        TblPhotos c = em.find(TblPhotos.class,photo_id);
        return c;
    }

    @Override
    public String addVideos(String video_url, Integer blog_post_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        try {
            TblVideos photo = new TblVideos();

            // Set the photo URL and blog post ID
            photo.setVideoUrl(video_url);

            // Assuming you have an entity class for blog posts, adjust the following line accordingly
            TblBlogPost blogPost = em.find(TblBlogPost.class, blog_post_id);

            // Set the blog post for this photo
            photo.setBlogPostId(blogPost);

            // Persist the new photo
            em.persist(photo);
            return "Inserted";
        } 
        catch (Exception ex) 
        {
            return ex.getMessage();
        }
    }

    @Override
    public String updateVideos(Integer video_id, String video_url, Integer blog_post_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        try{
            TblVideos video = em.find(TblVideos.class, video_id);

            if (video != null) {
                // Update the photo URL
                video.setVideoUrl(video_url);

                // Assuming you have an entity class for blog posts, adjust the following line accordingly
                TblBlogPost blogPost = em.find(TblBlogPost.class, blog_post_id);

                // Update the blog post for this photo
                video.setBlogPostId(blogPost);

                // Merge the updated photo entity
                em.merge(video);
            }
            return "Updated";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public void deletevideos(Integer video_id, Integer blog_post_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        TblBlogPost bp = em.find(TblBlogPost.class, blog_post_id);
        Collection<TblVideos> videos = bp.getTblVideosCollection();
        TblVideos tp = em.find(TblVideos.class, video_id);
        
        if(videos.contains(tp)){
            videos.remove(tp);
            bp.setTblVideosCollection(videos);
            em.remove(tp);
        }
    }

    @Override
    public Collection<TblVideos> getAllVideos() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
         return em.createNamedQuery("TblVideos.findAll").getResultList();
    }

    @Override
    public TblVideos findvideosById(Integer video_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        TblVideos c = em.find(TblVideos.class,video_id);
        return c;
    }

    @Override
    public String addReview(String rating, Integer userid, Integer blog_post_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try{
            TblUser user = em.find(TblUser.class, userid);
            TblBlogPost blogPost = em.find(TblBlogPost.class, blog_post_id);
            
            TblReview review = new TblReview();
            review.setRating(rating);

            
            review.setUserid(user);
            review.setBlogPostId(blogPost);

            user.getTblReviewCollection().add(review);

            blogPost.getTblReviewCollection().add(review);

            em.persist(review);
            return "Inserted";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public String updateReview(Integer review_id, String rating, Integer userid, Integer blog_post_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        try{
            // Find the review to update
        TblReview review = em.find(TblReview.class, review_id);

            if (review != null) {
                // Update the review properties
                review.setRating(rating);
                // Assuming you have a relationship between TblReview and TblUser
                TblUser user = em.find(TblUser.class, userid);
                review.setUserid(user);
                // Assuming you have a relationship between TblReview and TblBlogPost
                TblBlogPost blogPost = em.find(TblBlogPost.class, blog_post_id);
                review.setBlogPostId(blogPost);

                // Merge the updated review
                em.merge(review);
            }
            return "Updated";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public void deleteReview(Integer review_id, Integer userid, Integer blog_post_id) 
    {
        TblUser user = em.find(TblUser.class, userid);
        TblBlogPost bp = em.find(TblBlogPost.class, blog_post_id);
        Collection<TblReview> reviews = bp.getTblReviewCollection();

        // Iterate through the reviews and find the one associated with the specified user
        TblReview reviewToDelete = null;
        for (TblReview rev : reviews) {
            if (rev.getUserid().equals(user)) {
                reviewToDelete = rev;
                break;
            }
        }

            if (reviewToDelete != null) {
                // Remove the review from the blog post's collection
                reviews.remove(reviewToDelete);
                bp.setTblReviewCollection(reviews);

                // Remove the review entity
                em.remove(reviewToDelete);
        }
    }

    @Override
    public Collection<TblReview> getAllReview() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        return em.createNamedQuery("TblReview.findAll").getResultList();
    }
    
    @Override
    public Collection<TblReview> getReviewByPostID(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        return em.createNamedQuery("TblReview.findReviewByPostID").setParameter("postid", id).getResultList();
    }
    

    @Override
    public TblReview findreviewById(Integer review_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        TblReview c = em.find(TblReview.class,review_id);
        return c;
    }
    
    

    @Override
    public String addWishlist(Integer blog_post_id, Integer userid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        try{
             TblUser user = em.find(TblUser.class, userid);
            TblBlogPost blogPost = em.find(TblBlogPost.class, blog_post_id);

            if (user != null && blogPost != null) {
                // Create a new TblWishlist entity
                TblWishlist wishlist = new TblWishlist();

                // Set the user and blog post for the wishlist
                wishlist.setUserid(user);
                wishlist.setBlogPostId(blogPost);

                // Persist the wishlist entity
                em.persist(wishlist);
            }
            return "Inserted";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }

    @Override
    public String updateWishlist(Integer wishlist_id, Integer blog_post_id, Integer userid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        try{
            // Find the wishlist item to update
            TblWishlist wishlist = em.find(TblWishlist.class, wishlist_id);

            if (wishlist != null) {
                // Update the wishlist item properties
                // Assuming you have a relationship between TblWishlist and TblUser
                TblUser user = em.find(TblUser.class, userid);
                wishlist.setUserid(user);
                // Assuming you have a relationship between TblWishlist and TblBlogPost
                TblBlogPost blogPost = em.find(TblBlogPost.class, blog_post_id);
                wishlist.setBlogPostId(blogPost);

                // Merge the updated wishlist item
                em.merge(wishlist);
            }
            return "Updated";
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }   
    }

    @Override
    public void deleteWishlist(Integer wishlist_id, Integer blog_post_id, Integer userid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        
        TblWishlist wishlistItem = em.find(TblWishlist.class, wishlist_id);

        if (wishlistItem != null) {
            // Remove the wishlist item from the user's collection
            TblUser user = wishlistItem.getUserid();
//            Collection<TblWishlist> userWishlist = user.getTblWishlistCollection();
//            userWishlist.remove(wishlistItem);
//            user.setTblWishlistCollection(userWishlist);

            // Remove the wishlist item from the blog post's collection
            TblBlogPost blogPost = wishlistItem.getBlogPostId();
            Collection<TblWishlist> blogPostWishlist = blogPost.getTblWishlistCollection();
            blogPostWishlist.remove(wishlistItem);
            blogPost.setTblWishlistCollection(blogPostWishlist);

            // Remove the wishlist item entity
            em.remove(wishlistItem);

            // Merge the user and blog post entities to update their collections
            em.merge(user);
            em.merge(blogPost);
        }
        
    }

    @Override
    public Collection<TblWishlist> getAllWishlist() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        return em.createNamedQuery("TblWishlist.findAll").getResultList();
    }
    @Override
    public Collection<TblWishlist> getAllWishlistOfUser(Integer uid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        TblUser u = em.find(TblUser.class, uid);
        return u.getTblWishlistCollection();
    }

    @Override
    public TblWishlist findwishlistById(Integer wishlist_id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        TblWishlist c = em.find(TblWishlist.class,wishlist_id);
        return c;
    }
//    
    
    
   
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }
}
