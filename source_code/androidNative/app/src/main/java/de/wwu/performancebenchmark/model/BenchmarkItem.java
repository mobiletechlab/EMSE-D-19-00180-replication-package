package de.wwu.performancebenchmark.model;

import java.util.Date;

/**
 * Individual benchmark result.
 */
public class BenchmarkItem {

    // Type of benchmark
    protected BenchmarkType type;

    // Which run in the current benchmark series
    protected int runNumber;

    // Start time of the benchmark
    protected Date startTime;

    // End time of the benchmark
    protected Date completionTime;

    // Is this benchmark erroneous
    protected boolean error = false;

    // Value returned by the benchmark
    protected String resultValue;

    public BenchmarkItem(BenchmarkType type, int runNumber){
        this.type = type;
        this.runNumber = runNumber;
    }

    public BenchmarkType getType() {
        return type;
    }

    public void setType(BenchmarkType type) {
        this.type = type;
    }

    public int getRunNumber() {
        return runNumber;
    }

    public void setRunNumber(int runNumber) {
        this.runNumber = runNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }
}
