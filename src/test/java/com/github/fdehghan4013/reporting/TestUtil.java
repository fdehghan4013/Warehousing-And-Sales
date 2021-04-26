package com.github.fdehghan4013.reporting;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.text.DecimalFormat;
import java.util.*;

/**
 * <b>never and ever use this in production code. it must be used only for the @Test methods</b>
 */
public class TestUtil {
    private static final DecimalFormat decimalFormatter = new DecimalFormat("#.##");

    public static String randomString(int length) {
        return RandomStringUtils.random(length);
    }

    public static Integer randomInt(int start, int stop) {
        return RandomUtils.nextInt(start, stop);
    }

    public static Double randomDouble(double start, double stop) {
        double randomValue = RandomUtils.nextDouble(start, stop);

        return Double.valueOf(decimalFormatter.format(randomValue));
    }

    public static Date randomDate() {
        return randomDate(2010, Calendar.getInstance().get(Calendar.YEAR));
    }

    public static Date randomDate(int startYear, int endYear) {
        Calendar calendar = Calendar.getInstance();

        int year = randomInt(startYear, endYear);

        calendar.set(Calendar.YEAR, year);

        int dayOfYear = randomInt(1, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));

        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return new Date(calendar.getTimeInMillis());
    }

    public static <T> T randomSample(Collection<T> arrayList) {
        T result = null;
        if (arrayList != null && !arrayList.isEmpty()) {
            List<T> list = new ArrayList<>(arrayList);
            int i = randomInt(0, (list.size() - 1));
            result = list.get(i);
        }

        return result;
    }

    public static <T> T randomSample(T[] array) {
        T result = null;
        if (array != null && array.length != 0) {
            int i = randomInt(0, array.length - 1);
            result = array[i];
        }

        return result;
    }

    public static <T extends Enum<T>> T randomEnum(Class<T> enumeration) {
        T[] enumConstants = enumeration.getEnumConstants();

        T result = null;
        if (enumConstants != null && enumConstants.length != 0) {
            List<String> names = new ArrayList<>();
            for (T e : enumConstants) names.add(e.name());

            String aRandomName = randomSample(names);

            result = Enum.valueOf(enumeration, aRandomName);
        }

        return result;
    }
}
