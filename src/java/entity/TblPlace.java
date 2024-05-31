/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Harsh
 */
@Entity
@Table(name = "tbl_place")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPlace.findAll", query = "SELECT t FROM TblPlace t"),
    @NamedQuery(name = "TblPlace.findByPlaceId", query = "SELECT t FROM TblPlace t WHERE t.placeId = :placeId"),
    @NamedQuery(name = "TblPlace.findByPlaceName", query = "SELECT t FROM TblPlace t WHERE t.placeName = :placeName")})
public class TblPlace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "place_id")
    private Integer placeId;
    @Basic(optional = false)
    @Column(name = "place_name")
    private String placeName;
    @Basic(optional = false)
    @Lob
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placeId")
    private Collection<TblBlogPost> tblBlogPostCollection;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne(optional = false)
    private TblCategory categoryId;

    public TblPlace() {
    }

    public TblPlace(Integer placeId) {
        this.placeId = placeId;
    }

    public TblPlace(Integer placeId, String placeName, String address, String description) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.address = address;
        this.description = description;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<TblBlogPost> getTblBlogPostCollection() {
        return tblBlogPostCollection;
    }

    public void setTblBlogPostCollection(Collection<TblBlogPost> tblBlogPostCollection) {
        this.tblBlogPostCollection = tblBlogPostCollection;
    }

    public TblCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(TblCategory categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placeId != null ? placeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPlace)) {
            return false;
        }
        TblPlace other = (TblPlace) object;
        if ((this.placeId == null && other.placeId != null) || (this.placeId != null && !this.placeId.equals(other.placeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblPlace[ placeId=" + placeId + " ]";
    }
    
}
