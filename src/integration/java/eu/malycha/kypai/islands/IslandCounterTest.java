package eu.malycha.kypai.islands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IslandCounterTest {

    @Test
    void shouldCountIslandsGivenInExample() {
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
        assertEquals(4, IslandCounter.count(map));
    }

    @Test
    void shouldCountIslandsOnEmptySea() {
        int[][] map = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        assertEquals(0, IslandCounter.count(map));
    }

    @Test
    void shouldCountIslandsOnZeroSea() {
        int[][] map = {{}};
        assertEquals(0, IslandCounter.count(map));
    }

    @Test
    void shouldCountIslandsVShaped() {
        int[][] map = {
                {1, 1, 0, 0, 0, 0, 0, 1, 1},
                {0, 1, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 1, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        assertEquals(1, IslandCounter.count(map));
    }

    @Test
    void shouldCountIslandsAShaped() {
        int[][] map = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0},
                {1, 1, 0, 0, 0, 0, 0, 1, 1}
        };
        assertEquals(1, IslandCounter.count(map));
    }

    @Test
    void shouldCountIslandsAtlantis() {
        int[][] map = {
                {0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 1, 1, 1, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 1, 1, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 0, 0},
        };
        assertEquals(3, IslandCounter.count(map));
    }
}
