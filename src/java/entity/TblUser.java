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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Harsh
 */
@Entity
@Table(name = "tbl_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUser.findAll", query = "SELECT t FROM TblUser t"),
    @NamedQuery(name = "TblUser.findByUserid", query = "SELECT t FROM TblUser t WHERE t.userid = :userid"),
    @NamedQuery(name = "TblUser.findByUsername", query = "SELECT t FROM TblUser t WHERE t.username = :username"),
    @NamedQuery(name = "TblUser.findByFname", query = "SELECT t FROM TblUser t WHERE t.fname = :fname"),
    @NamedQuery(name = "TblUser.findByLname", query = "SELECT t FROM TblUser t WHERE t.lname = :lname"),
    @NamedQuery(name = "TblUser.findByEmail", query = "SELECT t FROM TblUser t WHERE t.email = :email"),
    @NamedQuery(name = "TblUser.findByProfileImg", query = "SELECT t FROM TblUser t WHERE t.profileImg = :profileImg"),
    @NamedQuery(name = "TblUser.findByContactNo", query = "SELECT t FROM TblUser t WHERE t.contactNo = :contactNo"),
    @NamedQuery(name = "TblUser.findByPassword", query = "SELECT t FROM TblUser t WHERE t.password = :password"),
    @NamedQuery(name = "TblUser.findByRegistrationDate", query = "SELECT t FROM TblUser t WHERE t.registrationDate = :registrationDate"),
    @NamedQuery(name = "TblUser.findByGroupname", query = "SELECT t FROM TblUser t WHERE t.groupname = :groupname")})
public class TblUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "fname")
    private String fname;
    @Basic(optional = false)
    @Column(name = "lname")
    private String lname;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "profile_img")
    private String profileImg;
    @Basic(optional = false)
    @Column(name = "contact_no")
    private int contactNo;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
     @Basic(optional = false)
    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @Basic(optional = false)
    @Column(name = "groupname")
    private String groupname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<TblWishlist> tblWishlistCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<TblReview> tblReviewCollection;


    public Collection<TblWishlist> getTblWishlistCollection() {
        return tblWishlistCollection;
    }

    public void setTblWishlistCollection(Collection<TblWishlist> tblWishlistCollection) {
        this.tblWishlistCollection = tblWishlistCollection;
    }

    public Collection<TblReview> getTblReviewCollection() {
        return tblReviewCollection;
    }

    public void setTblReviewCollection(Collection<TblReview> tblReviewCollection) {
        this.tblReviewCollection = tblReviewCollection;
    }
   
    public TblUser() {
    }

    public TblUser(Integer userid) {
        this.userid = userid;
    }

    public TblUser(Integer userid, String username, String fname, String lname, String email, String profileImg, int contactNo, String password, Date registrationDate, String groupname) {
        this.userid = userid;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.profileImg = profileImg;
        this.contactNo = contactNo;
        this.password = password;
        this.registrationDate = registrationDate;
        this.groupname = groupname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUser)) {
            return false;
        }
        TblUser other = (TblUser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TblUser[ userid=" + userid + " ]";
    }
    
}
