    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
     */
    package cdiBeans;

    import RestApi.CategoryClient;
import ejb.AdminBeanLocal;
    import entity.TblPlace;
    import javax.inject.Named;
    import javax.enterprise.context.SessionScoped;
    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.List;
import javax.ejb.EJB;
    import javax.ws.rs.core.GenericType;
    import javax.ws.rs.core.Response;

    /**
     *
     * @author Harsh
     */
    @Named(value = "placeCDIBean")
    @SessionScoped
    public class PlaceCDIBean implements Serializable {

    @EJB
    private AdminBeanLocal adminBean;

        
        
        CategoryClient cc = new CategoryClient();

        int placeid;
        String place_name;
        String address;
        String description;
        int categoryid;

        public int getPlaceid() {
            return placeid;
        }

        public void setPlaceid(int placeid) {
            this.placeid = placeid;
        }

        public String getPlace_name() {
            return place_name;
        }

        public void setPlace_name(String place_name) {
            this.place_name = place_name;
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

        public int getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(int categoryid) {
            this.categoryid = categoryid;
        }

        public String AddPlace()
        {
            String successmsg = cc.AddPlace(place_name, address, description,categoryid+"");
            if(successmsg!="")
            {
                return "ShowPlace.xhtml";
            }
            return "Code Inserted";
        }

        public List<TblPlace> ShowPlace()
        {
            Response rs = cc.ShowPlace(Response.class);
            ArrayList<TblPlace> slist = new ArrayList<TblPlace>();

            GenericType<Collection<TblPlace>> gcstate = new GenericType<Collection<TblPlace>>(){};
            slist = ( ArrayList<TblPlace> ) rs.readEntity(gcstate);
            return slist;
        }

        public String DeletePlace(int placeid,int categoryid)
        {
            cc.DeletePlace(placeid+"",categoryid+"");
            return "ShowPlace.xhtml?faces-redirect=true";
        }

        public String SearchPlace(int pid)
        {
            Response rs=cc.searchPlace(Response.class,pid+ "");
            GenericType<TblPlace> gstate=new GenericType<TblPlace>(){};
            TblPlace tc=rs.readEntity(gstate);
            placeid=tc.getPlaceId();
            place_name=tc.getPlaceName();
            address=tc.getAddress();
            description=tc.getDescription();
            categoryid=tc.getCategoryId().getCategoryId();
            return "EditPlace.xhtml?faces-redirect=true";
        }

        public String UpdatePlace()
        {
            cc.UpdatePlace(placeid+"", place_name, address, description,categoryid+"");
            return "ShowPlace.xhtml?faces-redirect=true";
        }

    public CategoryClient getCc() {
        return cc;
    }

    public void setCc(CategoryClient cc) {
        this.cc = cc;
    }

    public ArrayList<TblPlace> getSlist() {
        return slist;
    }

    public void setSlist(ArrayList<TblPlace> slist) {
        this.slist = slist;
    }
    
    ArrayList<TblPlace> slist = new ArrayList<TblPlace>();
    
    public void ShowSearchPlace()
    {
         Response rs = cc.ShowSearchPlace(Response.class, place_name);
         GenericType<Collection<TblPlace>> gcstate = new GenericType<Collection<TblPlace>>(){};
         slist = (ArrayList<TblPlace> )rs.readEntity(gcstate);
//         return slist;
    }
    
    public Collection<TblPlace> ShowPlaceCount()
    {
         Collection<TblPlace>slist=adminBean.getAllPlaces();
         return slist;
    }


        public PlaceCDIBean() {
        }

    }
