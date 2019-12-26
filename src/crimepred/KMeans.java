/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crimepred;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class KMeans {

    private static final Random random = new Random();

    public static HashMap<Centroid, ArrayList<Record>> fit(ArrayList<Record> records,
            int k, Distance distance, int maxIterations) {

        ArrayList<Centroid> centroids = randomCentroids(records, k);
        HashMap<Centroid, ArrayList<Record>> clusters = new HashMap<>();
        HashMap<Centroid, ArrayList<Record>> lastState = new HashMap<>();

        // iterate for a pre-defined number of times
        for (int i = 0; i < maxIterations; i++) {
            boolean isLastIteration = i == maxIterations - 1;

            // in each iteration we should find the nearest centroid for each record
            for (Record record : records) {
                Centroid centroid = nearestCentroid(record, centroids, distance);
                assignToCluster(clusters, record, centroid);
            }

            // if the assignments do not change, then the algorithm terminates
            boolean shouldTerminate = isLastIteration || clusters.equals(lastState);
            lastState = clusters;
            if (shouldTerminate) {
                break;
            }

            // at the end of each iteration we should relocate the centroids
            centroids = (ArrayList<Centroid>) relocateCentroids(clusters);
            clusters = new HashMap<>();
        }

        return lastState;
    }

    private static ArrayList<Centroid> randomCentroids(ArrayList<Record> records, int k) {
        ArrayList<Centroid> centroids = new ArrayList<>();
        Map<String, Double> maxs = new HashMap<>();
        Map<String, Double> mins = new HashMap<>();

        for (Record record : records) {
            record.getFeatures().forEach((key, value) -> {
                // compares the value with the current max and choose the bigger value between them
                maxs.compute(key, (k1, max) -> max == null || value > max ? value : max);

                // compare the value with the current min and choose the smaller value between them
                mins.compute(key, (k1, min) -> min == null || value < min ? value : min);
            });
        }

        Set<String> attributes = records.stream()
                .flatMap(e -> e.getFeatures().keySet().stream())
                .collect(toSet());
        for (int i = 0; i < k; i++) {
            HashMap<String, Double> coordinates = new HashMap<>();
            for (String attribute : attributes) {
                double max = maxs.get(attribute);
                double min = mins.get(attribute);
                coordinates.put(attribute, random.nextDouble() * (max - min) + min);
            }

            centroids.add(new Centroid(coordinates, new Date()));
        }

        return centroids;
    }

    private static Centroid nearestCentroid(Record record, ArrayList<Centroid> centroids, Distance distance) {
        double minimumDistance = Double.MAX_VALUE;
        Centroid nearest = null;

        for (Centroid centroid : centroids) {
            double currentDistance = distance.calculate(record.getFeatures(), centroid.getCoordinates());

            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearest = centroid;
            }
        }

        return nearest;
    }

    private static void assignToCluster(HashMap<Centroid, ArrayList<Record>> clusters,
            Record record, Centroid centroid) {
        clusters.compute(centroid, (key, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }

            list.add(record);
            return list;
        });
    }

    private static Centroid average(Centroid centroid, ArrayList<Record> records) {
        if (records == null || records.isEmpty()) {
            return centroid;
        }

        HashMap<String, Double> average = centroid.getCoordinates();
        records.stream().flatMap(e -> e.getFeatures().keySet().stream())
                .forEach(k -> average.put(k, 0.0));

        for (Record record : records) {
            record.getFeatures().forEach(
                    (k, v) -> average.compute(k, (k1, currentValue) -> v + currentValue)
            );
        }

        average.forEach((k, v) -> average.put(k, v / records.size()));

        return new Centroid(average, new Date());
    }

    private static java.util.List<Centroid> relocateCentroids(HashMap<Centroid, ArrayList<Record>> clusters) {
        return clusters.entrySet().stream().map(e -> average(e.getKey(), e.getValue())).collect(toList());
    }
}
