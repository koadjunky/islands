package eu.malycha.kypai.islands;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ArchipelagoTest {

    int[][] map = {
            {2, 0, 1},
            {0, 1},
            {2, 0, 3},
    };

    @Test
    void shouldReturnMapHeight() {
        Parents parents = Mockito.mock(Parents.class);
        Archipelago archipelago = new Archipelago(map, parents);
        assertEquals(3, archipelago.getHeight());
    }

    @Test
    void shouldReturnMapWidth() {
        Parents parents = Mockito.mock(Parents.class);
        Archipelago archipelago = new Archipelago(map, parents);
        assertEquals(3, archipelago.getWidth(0));
        assertEquals(2, archipelago.getWidth(1));
        assertEquals(3, archipelago.getWidth(2));
    }

    @Test
    void shouldReturnPointValuesFillingGaps() {
        Parents parents = Mockito.mock(Parents.class);
        Archipelago archipelago = new Archipelago(map, parents);
        assertEquals(2, archipelago.getPoint(0, 0));
        assertEquals(0, archipelago.getPoint(1, 0));
        assertEquals(1, archipelago.getPoint(1, 1));
        assertEquals(0, archipelago.getPoint(1, 2));
    }

    @Test
    void shouldReturnLandValuesFillingGaps() {
        Parents parents = Mockito.mock(Parents.class);
        Archipelago archipelago = new Archipelago(map, parents);
        assertFalse(archipelago.isLand(0, 0));
        assertFalse(archipelago.isLand(1, 0));
        assertTrue(archipelago.isLand(1, 1));
        assertFalse(archipelago.isLand(1, 2));
    }

    @Test
    void shouldReturnPrecedingPointsFillingGaps() {
        Parents parents = Mockito.mock(Parents.class);
        Archipelago archipelago = new Archipelago(map, parents);
        assertEquals(Arrays.asList(0, 1, 0, 2), archipelago.getPrecedingPoints(2, 1));
    }

    @Test
    void shouldReturnPrecedingIslesFillingGaps() {
        Parents parents = Mockito.mock(Parents.class);
        when(parents.getAncestor(anyInt())).thenAnswer(i -> i.getArguments()[0]);
        Archipelago archipelago = new Archipelago(map, parents);
        assertEquals(Arrays.asList(1, 2), archipelago.getPrecedingIsles(2, 1));
    }

    @Test
    void shouldReturnIsleIndexRespectingParents() {
        Parents parents = Mockito.mock(Parents.class);
        when(parents.getAncestor(anyInt())).thenAnswer(i -> i.getArguments()[0]);
        when(parents.getAncestor(3)).thenReturn(2);
        Archipelago archipelago = new Archipelago(map, parents);
        assertEquals(2, archipelago.getIsle(2, 2));
    }

    @Test
    void shouldMarkIsle() {
        Parents parents = Mockito.mock(Parents.class);
        Archipelago archipelago = new Archipelago(map, parents);
        archipelago.markIsle(2, 1, 4);
        assertEquals(4, archipelago.getPoint(2, 1));
    }

    @Test
    void shouldJoinIsles() {
        Parents parents = Mockito.mock(Parents.class);
        when(parents.getAncestor(anyInt())).thenAnswer(i -> i.getArguments()[0]);
        Archipelago archipelago = new Archipelago(map, parents);
        assertEquals(2, archipelago.joinIsles(0, 0, Arrays.asList(2, 3, 4)));
        verify(parents).setParent(3, 2);
        verify(parents).setParent(4, 2);
    }

    @Test
    void shouldPrettyPrint() {
        Parents parents = Mockito.mock(Parents.class);
        when(parents.getAncestor(anyInt())).thenAnswer(i -> i.getArguments()[0]);
        Archipelago archipelago = new Archipelago(map, parents);
        assertEquals("201\n01\n203\n", archipelago.toString());
    }
}
