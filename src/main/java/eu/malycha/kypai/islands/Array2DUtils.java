package eu.malycha.kypai.islands;

import org.apache.commons.lang3.ArrayUtils;

/*
 * Safe version of array access. Returns default value if array index is invalid.
 * Turns out, commons-lang3 doesn't have int[] version, so it is included here.
 */
class Array2DUtils {

    private static boolean isArrayIndexValid(final int[] array, final int index) {
        return index >= 0 && ArrayUtils.getLength(array) > index;
    }

    private static int get(final int[] array, final int index, final int defaultValue) {
        return isArrayIndexValid(array, index) ? array[index] : defaultValue;
    }

    public static int get(int[][] arr, int i, int j, int def) {
        int[] row = ArrayUtils.get(arr, i, ArrayUtils.EMPTY_INT_ARRAY);
        return get(row, j, def);
    }
}
