package crimepred;

import java.util.Map;

public class Record {
//    private final String description;
    private final Map<String, Double> features;
    public int classNr;
    
    public Record(String description, Map<String, Double> feats){
//        this.description = description;
        this.features = feats;
        classNr = -1;
    }
    
    public Map<String, Double> getFeat(){
        return features;
    }
 
    // constructor, getter, toString, equals and hashcode
}