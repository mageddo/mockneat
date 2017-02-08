package com.mockneat.random;

import com.mockneat.utils.ArrayUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static com.mockneat.utils.ArrayUtils.toWrapperArray;
import static com.mockneat.utils.FunctUtils.cycle;
import static org.junit.Assert.assertTrue;

public class LongsTest {

    @Test
    public void testNextLongInCorrectRange() throws Exception {
        long upperBound = 100;
        cycle(RandTestConstants.FLOATS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.longs().bound(upperBound).val())
                    .forEach(num -> assertTrue(num >= 0 && num < upperBound)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound() throws Exception {
        long upperBound = -100;
        RandTestConstants.RAND.longs().bound(upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNullNotBound() throws Exception {
        Long bound = null;
        RandTestConstants.RAND.longs().bound(bound).val();
    }

    @Test
    public void testNextLongInCorrectRange2() throws Exception {
        long upperBound = 1L;
        cycle(RandTestConstants.LONGS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.longs().bound(upperBound).val())
                    .forEach(num -> assertTrue(num.equals(0L))));
    }

    @Test
    public void testNextLongInCorrectRange3() throws Exception {
        Long upperBound = Long.MAX_VALUE;
        cycle(RandTestConstants.LONGS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.longs().bound(upperBound).val())
                    .forEach(num -> assertTrue(num < upperBound)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound2() throws Exception {
        long lowerBound = -1;
        long upperBound = 100;
        RandTestConstants.RAND.longs().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound3() throws Exception {
        long lowerBound = 100;
        long upperBound = -5;
        RandTestConstants.RAND.longs().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNullNotBound2() throws Exception {
        RandTestConstants.RAND.longs().range(null, 10L).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNullNotBound3() throws Exception {
        RandTestConstants.RAND.longs().range(10L, null).val();
    }

    @Test
    public void testNextLongInCorrectRange4() throws Exception {
        long lowerBound = 5;
        long upperBound = 8;
        cycle(RandTestConstants.LONGS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.longs().range(lowerBound, upperBound).val())
                    .forEach( num -> assertTrue((num >= lowerBound) && (num < upperBound))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNonEqualBounds() throws Exception {
        long lowerBound = 10, upperBound = 10;
        RandTestConstants.RAND.longs().range(lowerBound, upperBound).val();
    }

    @Test
    public void testNextLongCorrectValues() throws Exception {
        long lowerBound = 10, upperBound = lowerBound + 1;
        cycle(RandTestConstants.LONGS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.longs().range(lowerBound, upperBound).val())
                    .forEach(num -> num.equals(lowerBound)));
    }

    @Test
    public void testNextLongInCorrectRange5() throws Exception {
        long lowerBound = 0;
        long upperBound = Long.MAX_VALUE;
        cycle(RandTestConstants.LONGS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.longs().range(lowerBound, upperBound).val())
                    .forEach(num -> assertTrue(num >= lowerBound && num < upperBound)));
    }

    @Test
    public void testNextCorrectValues() throws Exception {
        long[] alphabet = { 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };
        Set<Long> helperSet = new HashSet<>(asList(ArrayUtils.toWrapperArray(alphabet)));
        cycle(RandTestConstants.LONGS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.longs().from(alphabet).val())
                    .forEach(num -> assertTrue(helperSet.contains(num))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextNulLNotAlphabet() throws Exception {
        long[] alphabet = null;
        RandTestConstants.RAND.longs().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextEmptyArrayNotAlphabet() throws Exception {
        long[] alphabet = new long[]{};
        RandTestConstants.RAND.longs().from(alphabet).val();
    }
}