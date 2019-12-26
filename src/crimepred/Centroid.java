package crimepred;

import java.util.Date;
import java.util.HashMap;

public class Centroid {
    public HashMap<String, Double> hm;
    public int classNr;
    
    public double longi;
    public double lati;
    public Date date;
    public int time;
    
    public Centroid(HashMap<String, Double> HashMap, Date date){
        this.hm = HashMap;
        this.date = date;
        setLongLat();
    }
    
    public void setLongLat(){
        this.lati = (double) hm.get("LATITUDE");
        this.longi = (double) hm.get("LONGITUDE");
    }
    
    public HashMap<String, Double> getCoordinates(){
        return hm;
    }
    
    public void addToHashMap(String s, Double o){
        hm.put(s, o);
    }
}
