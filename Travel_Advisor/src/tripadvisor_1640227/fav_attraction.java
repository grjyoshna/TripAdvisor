/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tripadvisor_1640227;

/**
 *
 * @author reddyjyoshnagurrala
 */
public class fav_attraction {
    public String fav_att_id;
    public String fav_attraction;
    public fav_attraction(String fav_att_id, String fav_attraction)
    {
        this.fav_att_id=fav_att_id;
        this.fav_attraction=fav_attraction;
    }

    public String getFav_att_id() {
        return fav_att_id;
    }

    public void setFav_att_id(String fav_att_id) {
        this.fav_att_id = fav_att_id;
    }

    public String getFav_attraction() {
        return fav_attraction;
    }

    public void setFav_attraction(String fav_attraction) {
        this.fav_attraction = fav_attraction;
    }
    
}
