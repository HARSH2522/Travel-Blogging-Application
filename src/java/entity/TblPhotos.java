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
@Table(name = "tbl_photos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPhotos.findAll", query = "SELECT t FROM TblPhotos t"),
    @NamedQuery(name = "TblPhotos.findByPhotoId", query = "SELECT t FROM TblPhotos t WHERE t.photoId = :photoId"),
    @NamedQuery(name = "TblPhotos.findByPhotoUrl", query = "SELECT t FROM TblPhotos t WHERE t.photoUrl = :photoUrl")})
public class TblPhotos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "photo_id")
    private Integer photoId;
    @Basic(optional = false)
    @Column(name = "photo_url")
    private String photoUrl;
    @JoinColumn(name = "blog_post_id", referencedColumnName = "blog_post_id")
    @ManyToOne(optional = false)
    private TblBlogPost blogPostId;

    public TblPhotos() {
    }

    public TblPhotos(Integer photoId) {
        this.photoId = photoId;
    }

    public TblPhotos(Integer photoId, String photoUrl) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
        hash += (photoId != null ? photoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPhotos)) {
            return false;
        }
        TblPhotos other = (TblPhotos) object;
        if ((this.photoId == null && other.photoId != null) || (this.photoId != null && !this.photoId.equals(other.photoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblPhotos[ photoId=" + photoId + " ]";
    }
    
}
