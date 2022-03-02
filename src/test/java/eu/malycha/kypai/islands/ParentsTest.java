package eu.malycha.kypai.islands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParentsTest {

    @Test
    void shouldFillTwoSlotsByDefault() {
        Parents parents = new Parents();
        assertEquals(0, parents.getAncestor(0));
        assertEquals(1, parents.getAncestor(1));
    }

    @Test
    void shouldRaiseExceptionWhenOutOfRange() {
        Parents parents = new Parents();
        assertThrows(IndexOutOfBoundsException.class, () -> parents.getAncestor(2));
    }

    @Test
    void shouldAddNewSlot() {
        Parents parents = new Parents();
        parents.add();
        assertEquals(2, parents.getAncestor(2));
    }

    @Test
    void shouldReturnTopmostParent() {
        Parents parents = new Parents();
        parents.add();
        parents.add();
        parents.add();
        parents.setParent(3, 2);
        parents.setParent(4, 3);
        assertEquals(2, parents.getAncestor(2));
        assertEquals(2, parents.getAncestor(3));
        assertEquals(2, parents.getAncestor(4));
    }

    @Test
    void shouldReturnCountOfTopmostParents() {
        Parents parents = new Parents();
        parents.add();
        parents.add();
        parents.add();
        parents.setParent(3, 2);
        parents.setParent(4, 3);
        assertEquals(1, parents.ancestorCount());
    }

    @Test
    void shouldPrettyPrintParentsList() {
        Parents parents = new Parents();
        parents.add();
        parents.add();
        parents.setParent(3, 2);
        assertEquals("[0, 1, 2, 2]", parents.toString());
    }
}
