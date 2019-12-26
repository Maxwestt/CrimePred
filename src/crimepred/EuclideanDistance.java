/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crimepred;

import java.util.HashMap;

/**
 *
 * @author s141452
 */
public class EuclideanDistance implements Distance {
 
    @Override
    public double calculate(HashMap<String, Double> f1, HashMap<String, Double> f2) {
        double sum = 0;
        for (String key : f1.keySet()) {
            Double v1 = f1.get(key);
            Double v2 = f2.get(key);
 
            if (v1 != null && v2 != null) {
                sum += Math.pow(v1 - v2, 2);
            }
        }
 
        return Math.sqrt(sum);
    }
}
