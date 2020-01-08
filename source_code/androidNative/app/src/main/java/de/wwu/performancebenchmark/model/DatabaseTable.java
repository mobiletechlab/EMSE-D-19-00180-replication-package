package de.wwu.performancebenchmark.model;

import android.provider.BaseColumns;

/**
 * Used to store database benchmark files
 */

public final class DatabaseTable {

    // No instantiation allowed
    private DatabaseTable() {}

    /* Inner class that defines the table contents */
    public static class Benchmark128 implements BaseColumns {
        public static final String TABLE_NAME = "benchmark128";
        public static final String COLUMN_NAME_KEY = "key";
        public static final String COLUMN_NAME_VALUE = "value";
    }

    public static class Benchmark2048 implements BaseColumns {
        public static final String TABLE_NAME = "benchmark2048";
        public static final String COLUMN_NAME_KEY = "key";
        public static final String COLUMN_NAME_VALUE = "value";
    }
}
