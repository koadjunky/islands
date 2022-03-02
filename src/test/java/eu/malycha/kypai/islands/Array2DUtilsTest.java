package eu.malycha.kypai.islands;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Array2DUtilsTest {

    int[][] testArray = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};

    private static Stream<Arguments> provideCellsWithinRange() {
        return Stream.of(
                Arguments.of(0, 0, 1),
                Arguments.of(1, 1, 5),
                Arguments.of(2, 0, 7)
        );
    }

    private static Stream<Arguments> provideCellsOutsideRange() {
        return Stream.of(
                Arguments.of(-1, -1, 0),
                Arguments.of(0, 3, 0),
                Arguments.of(3, 3, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCellsWithinRange")
    void shouldReturnCellIfWithinRange(int i, int j, int expected) {
        assertEquals(expected, Array2DUtils.get(testArray, i, j, 0));
    }

    @ParameterizedTest
    @MethodSource("provideCellsOutsideRange")
    void shouldReturnCellIfOutsideRange(int i, int j, int expected) {
        assertEquals(expected, Array2DUtils.get(testArray, i, j, 0));
    }
}
