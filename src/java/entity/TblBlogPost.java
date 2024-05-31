/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Harsh
 */
@Entity
@Table(name = "tbl_blog_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblBlogPost.findAll", query = "SELECT t FROM TblBlogPost t"),
    @NamedQuery(name = "TblBlogPost.findByBlogPostId", query = "SELECT t FROM TblBlogPost t WHERE t.blogPostId = :blogPostId"),
    @NamedQuery(name = "TblBlogPost.findByBlogTitle", query = "SELECT t FROM TblBlogPost t WHERE t.blogTitle = :blogTitle"),
    @NamedQuery(name = "TblBlogPost.findByDate", query = "SELECT t FROM TblBlogPost t WHERE t.date = :date")})
public class TblBlogPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "blog_post_id")
    private Integer blogPostId;
    @Basic(optional = false)
    @Column(name = "blog_title")
    private String blogTitle;
    @Basic(optional = false)
    @Lob
    @Column(name = "blog_description")
    private String blogDescription;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blogPostId")
    private Collection<TblVideos> tblVideosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blogPostId")
    private Collection<TblPhotos> tblPhotosCollection;
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    @ManyToOne(optional = false)
    private TblPlace placeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blogPostId")
    private Collection<TblWishlist> tblWishlistCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blogPostId")
    private Collection<TblReview> tblReviewCollection;

    public TblBlogPost() {
    }

    public TblBlogPost(Integer blogPostId) {
        this.blogPostId = blogPostId;
    }

    public TblBlogPost(Integer blogPostId, String blogTitle, String blogDescription, Date date) {
        this.blogPostId = blogPostId;
        this.blogTitle = blogTitle;
        this.blogDescription = blogDescription;
        this.date = date;
    }

    public Integer getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(Integer blogPostId) {
        this.blogPostId = blogPostId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Collection<TblVideos> getTblVideosCollection() {
        return tblVideosCollection;
    }

    public void setTblVideosCollection(Collection<TblVideos> tblVideosCollection) {
        this.tblVideosCollection = tblVideosCollection;
    }

    @XmlTransient
    public Collection<TblPhotos> getTblPhotosCollection() {
        return tblPhotosCollection;
    }

    public void setTblPhotosCollection(Collection<TblPhotos> tblPhotosCollection) {
        this.tblPhotosCollection = tblPhotosCollection;
    }

    public TblPlace getPlaceId() {
        return placeId;
    }

    public void setPlaceId(TblPlace placeId) {
        this.placeId = placeId;
    }

    @XmlTransient
    public Collection<TblWishlist> getTblWishlistCollection() {
        return tblWishlistCollection;
    }

    public void setTblWishlistCollection(Collection<TblWishlist> tblWishlistCollection) {
        this.tblWishlistCollection = tblWishlistCollection;
    }

    @XmlTransient
    public Collection<TblReview> getTblReviewCollection() {
        return tblReviewCollection;
    }

    public void setTblReviewCollection(Collection<TblReview> tblReviewCollection) {
        this.tblReviewCollection = tblReviewCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blogPostId != null ? blogPostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblBlogPost)) {
            return false;
        }
        TblBlogPost other = (TblBlogPost) object;
        if ((this.blogPostId == null && other.blogPostId != null) || (this.blogPostId != null && !this.blogPostId.equals(other.blogPostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblBlogPost[ blogPostId=" + blogPostId + " ]";
    }
    
}
