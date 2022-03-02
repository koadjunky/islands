package eu.malycha.kypai.islands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Keeps track of isles joins. Top-level isles are their own parents.
 * parent is immediate superior isle
 * ancestor is top-level superior isle (without any superiors)
 */
class Parents {

    private final List<Integer> parents = new ArrayList<>();

    int add() {
        int size = parents.size();
        parents.add(size);
        return size;
    }

    void setParent(int child, int parent) {
        parents.set(child, parent);
    }

    int getAncestor(int i) {
        int result = i;
        while (result != parents.get(result)) {
            result = parents.get(result);
        }
        return result;
    }

    int ancestorCount() {
        int result = 0;
        for (int i = 2; i < parents.size(); i++) {
            if (parents.get(i) == i) {
                result += 1;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(parents.toArray());
    }

    {
        parents.add(0);
        parents.add(1);
    }
}

