/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Harsh
 */
@Entity
@Table(name = "tbl_review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblReview.findAll", query = "SELECT t FROM TblReview t"),
    @NamedQuery(name = "TblReview.findReviewByPostID", query = "SELECT t FROM TblReview t where t.blogPostId.blogPostId=:postid"),
    @NamedQuery(name = "TblReview.findByReviewId", query = "SELECT t FROM TblReview t WHERE t.reviewId = :reviewId"),
    @NamedQuery(name = "TblReview.findByRating", query = "SELECT t FROM TblReview t WHERE t.rating = :rating")})
public class TblReview implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "review_id")
    private Integer reviewId;
    @Basic(optional = false)
    @Column(name = "rating")
    private String rating;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private TblUser userid;
    @JoinColumn(name = "blog_post_id", referencedColumnName = "blog_post_id")
    @ManyToOne(optional = false)
    private TblBlogPost blogPostId;

    public TblReview() {
    }

    public TblReview(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public TblReview(Integer reviewId, String rating) {
        this.reviewId = reviewId;
        this.rating = rating;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public TblUser getUserid() {
        return userid;
    }

    public void setUserid(TblUser userid) {
        this.userid = userid;
    }

    public TblBlogPost getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(TblBlogPost blogPostId) {
        this.blogPostId = blogPostId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewId != null ? reviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblReview)) {
            return false;
        }
        TblReview other = (TblReview) object;
        if ((this.reviewId == null && other.reviewId != null) || (this.reviewId != null && !this.reviewId.equals(other.reviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblReview[ reviewId=" + reviewId + " ]";
    }
    
}
