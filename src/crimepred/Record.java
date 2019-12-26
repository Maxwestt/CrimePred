package crimepred;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

public class Record {
//    private final String description;
    private final HashMap<String, Double> features;
    public int classNr;
    
    public double longi;
    public double lati;
    public Date date;
    public int time;
    
    public Record(HashMap<String, Double> feats, Date date){
//        this.description = description;
        this.features = feats;
        this.date = date;
        classNr = -1;
        setLongLat();
    }
    
    public void setLongLat(){
        this.lati = (double) features.get("LATITUDE");
        this.longi = (double) features.get("LONGITUDE");
//        this.date = (Date) features.get("ARREST_DATE");
    }
    
    public HashMap<String, Double> getFeatures(){
        return features;
    }
    
    public void addToHashMap(String s, double o){
        features.put(s, o);
    }
 
    // constructor, getter, toString, equals and hashcode
}