package crimepred;

import java.util.Map;

public class Centroid {
    public Map<String, Double> map;
    
    public Centroid(Map<String, Double> map){
        this.map = map;
    }
    
    public Map<String, Double> getMap(){
        return map;
    }
}
