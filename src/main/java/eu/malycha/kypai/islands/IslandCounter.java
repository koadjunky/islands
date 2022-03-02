package eu.malycha.kypai.islands;

import java.util.List;

/*
 * In this problem we are counting all disjoint subgraphs, the obvious solution
 * is to use DFS or BFS but they are stack- or memory intensive. This solution
 * performs linear scan over the array, marking vertices using closest visited
 * neighbours and tracking subgraph joins.
 */
public class IslandCounter {

    public static int count(int[][] map) {
        Parents parents = new Parents();
        Archipelago archipelago = new Archipelago(map, parents);
        return count(archipelago);
    }

    public static int count(Archipelago archipelago) {
        for (int i = 0; i < archipelago.getHeight(); i++) {
            for (int j = 0; j < archipelago.getWidth(i); j++) {
                if (archipelago.isLand(i, j)) {
                    List<Integer> isles = archipelago.getPrecedingIsles(i, j);
                    int isle = archipelago.joinIsles(i, j, isles);
                    archipelago.markIsle(i, j, isle);
                }
            }
        }
        return archipelago.islesCount();
    }

    public static void main(String[] args) {
        int[][] map = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0}
        };
        Archipelago archipelago = new Archipelago(map, new Parents());
        System.out.println("Input map:");
        System.out.println(archipelago);
        System.out.println("Isles count: " + IslandCounter.count(archipelago));
        System.out.println("Map with islands marked:");
        System.out.println(archipelago);
    }
}

