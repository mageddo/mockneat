package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.*;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.mockneat.utils.CheckUtils.checkAlphabet;
import static com.mockneat.utils.CheckUtils.checkType;

/**
 * Created by andreinicolinciobanu on 26/01/2017.
 */
public class Objs {

    private Rand rand;

    public Objs(Rand rand){
        this.rand = rand;
    }

    public <T> RandUnit<T> from(List<T> alphabet) {
        Supplier<T> supp = () -> {
            checkAlphabet(alphabet);
            int idx = rand.getRandom().nextInt(alphabet.size());
            return alphabet.get(idx);
        };
        return () -> supp;
    }

    public <T> RandUnit<T> from(T[] alphabet) {
        Supplier<T> supp = () -> {
            checkAlphabet(alphabet);
            int idx = rand.getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

    public <T extends Enum<?>> RandUnit<T> from(Class<T> enumClass) {
        checkType(enumClass);
        T[] arr = enumClass.getEnumConstants();
        return from(arr);
    }

    public <T> RandUnit<T> fromKeys(Map<T, ?> map) {
        Supplier<T> supp = () -> {
            T[] keys = (T[]) map.keySet().toArray();
            int idx = rand.getRandom().nextInt(keys.length);
            return keys[idx];
        };
        return () -> supp;
    }

    public <T> RandUnit<T> fromValues(Map<?, T> map) {
        Supplier<T> supp = () -> {
            T[] values = (T[]) map.values().toArray();
            int idx = rand.getRandom().nextInt(values.length);
            return values[idx];
        };
        return () -> supp;
    }

    public RandUnitInt fromInts(Integer[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitInt fromInts(int[] alphabet) {
        return () -> rand.ints().from(alphabet)::val;
    }

    public RandUnitInt fromInts(List<Integer> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitInt fromIntsValues(Map<?, Integer> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitInt fromIntsKeys(Map<Integer, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public RandUnitDouble fromDoubles(Double[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitDouble fromDoubles(double[] alphabet) {
        return () -> rand.doubles().from(alphabet)::val;
    }

    public RandUnitDouble fromDoubles(List<Double> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitDouble fromDoublesValues(Map<?, Double> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitDouble fromDoublesKeys(Map<Double, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public RandUnitLong fromLongs(Long[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitLong fromLongs(long[] alphabet) {
        return () -> rand.longs().from(alphabet)::val;
    }

    public RandUnitLong fromLongs(List<Long> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitLong fromLongsValues(Map<?, Long> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitLong fromLongsKeys(Map<Long, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public RandUnitString fromStrings(String[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitString fromStrings(List<String> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitString fromStringsValues(Map<?, String> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitString fromStringsKeys(Map<String, ?> map) {
        return () -> fromKeys(map)::val;
    }
}
