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
public interface Distance {
    double calculate(HashMap<String, Double> f1, HashMap<String, Double> f2);
}
