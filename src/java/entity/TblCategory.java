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
@Table(name = "tbl_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCategory.findAll", query = "SELECT t FROM TblCategory t"),
    @NamedQuery(name = "TblCategory.findByCategoryId", query = "SELECT t FROM TblCategory t WHERE t.categoryId = :categoryId"),
    @NamedQuery(name = "TblCategory.findByCategoryName", query = "SELECT t FROM TblCategory t WHERE t.categoryName = :categoryName")})
public class TblCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic(optional = false)
    @Column(name = "category_name")
    private String categoryName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<TblPlace> tblPlaceCollection;

    public TblCategory() {
    }

    public TblCategory(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public TblCategory(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @XmlTransient
    public Collection<TblPlace> getTblPlaceCollection() {
        return tblPlaceCollection;
    }

    public void setTblPlaceCollection(Collection<TblPlace> tblPlaceCollection) {
        this.tblPlaceCollection = tblPlaceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCategory)) {
            return false;
        }
        TblCategory other = (TblCategory) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblCategory[ categoryId=" + categoryId + " ]";
    }
    
}
