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
@Table(name = "tbl_wishlist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblWishlist.findAll", query = "SELECT t FROM TblWishlist t"),
    @NamedQuery(name = "TblWishlist.findByWishlistId", query = "SELECT t FROM TblWishlist t WHERE t.wishlistId = :wishlistId")})
public class TblWishlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "wishlist_id")
    private Integer wishlistId;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private TblUser userid;
    @JoinColumn(name = "blog_post_id", referencedColumnName = "blog_post_id")
    @ManyToOne(optional = false)
    private TblBlogPost blogPostId;

    public TblWishlist() {
    }

    public TblWishlist(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
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
        hash += (wishlistId != null ? wishlistId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblWishlist)) {
            return false;
        }
        TblWishlist other = (TblWishlist) object;
        if ((this.wishlistId == null && other.wishlistId != null) || (this.wishlistId != null && !this.wishlistId.equals(other.wishlistId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblWishlist[ wishlistId=" + wishlistId + " ]";
    }
    
}
