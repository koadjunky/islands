package eu.malycha.kypai.islands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Stores map of isles, can be used to iterate over map points and mark isles
 * with different numbers. Allows isles joins through Parents class.
 * Class is modifying map passed in constructor - this is bad practice,
 * cutting this corner to save memory. Alternatively we can use buffer of
 * two lines to avoid this.
 * Terms:
 * point - any number on map (sea=0, land=1, or any marked island=2,3...)
 * isle - point on map greater than 1 (marked island)
 */
class Archipelago {

    private static final Logger LOGGER = LoggerFactory.getLogger(Archipelago.class);

    private static final int SEA = 0;
    private static final int LAND = 1;

    private final int[][] map;
    private final Parents parents;

    Archipelago(int[][] map, Parents parents) {
        this.map = map;
        this.parents = parents;
    }

    int getHeight() {
        return map.length;
    }

    int getWidth(int i) {
        return map[i].length;
    }

    int getPoint(int i, int j) {
        return Array2DUtils.get(map, i, j, SEA);
    }

    boolean isLand(int i, int j) {
        return getPoint(i, j) == LAND;
    }

    List<Integer> getPrecedingPoints(int i, int j) {
        List<Integer> values = new ArrayList<>();
        values.add(getPoint(i-1, j-1));
        values.add(getPoint(i-1, j));
        values.add(getPoint(i-1, j+1));
        values.add(getPoint(i, j-1));
        return values;
    }

    List<Integer> getPrecedingIsles(int i, int j) {
        List<Integer> points = getPrecedingPoints(i, j);
        return points.stream()
                .filter(k -> k != SEA)
                .map(parents::getAncestor)
                .distinct()
                .collect(Collectors.toList());
    }

    int getIsle(int i, int j) {
        return parents.getAncestor(getPoint(i, j));
    }

    void markIsle(int i, int j, int value) {
        map[i][j] = value;
    }

    int joinIsles(int i, int j, List<Integer> isles) {
        if (isles.size() > 0) {
            int isle = isles.get(0);
            isles.forEach(k -> parents.setParent(k, isle));
            if (isles.size() > 1) {
                LOGGER.info("Merging isles {} around point ({}, {})", isles, i, j);
            }
            return isle;
        } else {
            int result = parents.add();
            LOGGER.info("Adding new isle {} at point ({}, {})", result, i, j);
            return result;
        }
    }

    int islesCount() {
        return parents.ancestorCount();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(i); j++) {
                sb.append(getIsle(i, j));
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
