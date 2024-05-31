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
@Table(name = "tbl_videos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblVideos.findAll", query = "SELECT t FROM TblVideos t"),
    @NamedQuery(name = "TblVideos.findByVideoId", query = "SELECT t FROM TblVideos t WHERE t.videoId = :videoId"),
    @NamedQuery(name = "TblVideos.findByVideoUrl", query = "SELECT t FROM TblVideos t WHERE t.videoUrl = :videoUrl")})
public class TblVideos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "video_id")
    private Integer videoId;
    @Basic(optional = false)
    @Column(name = "video_url")
    private String videoUrl;
    @JoinColumn(name = "blog_post_id", referencedColumnName = "blog_post_id")
    @ManyToOne(optional = false)
    private TblBlogPost blogPostId;

    public TblVideos() {
    }

    public TblVideos(Integer videoId) {
        this.videoId = videoId;
    }

    public TblVideos(Integer videoId, String videoUrl) {
        this.videoId = videoId;
        this.videoUrl = videoUrl;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
        hash += (videoId != null ? videoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblVideos)) {
            return false;
        }
        TblVideos other = (TblVideos) object;
        if ((this.videoId == null && other.videoId != null) || (this.videoId != null && !this.videoId.equals(other.videoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblVideos[ videoId=" + videoId + " ]";
    }
    
}
