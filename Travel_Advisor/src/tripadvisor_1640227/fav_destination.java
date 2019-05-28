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
public class fav_destination {
    public String city_id;
    public String fav_city;
//    public String att_id;
//    public String fav_attraction;
    public fav_destination(String city_id, String f_city)
    {
        this.city_id=city_id;
        fav_city=f_city;
//        this.att_id=att_id;
//        fav_attraction=f_attraction;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getFav_city() {
        return fav_city;
    }

    public void setFav_city(String fav_city) {
        this.fav_city = fav_city;
    }
    
    
}
