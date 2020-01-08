package de.wwu.performancebenchmark.model;

/**
 * Response for asynchronous background tasks.
 */
public interface AsyncTaskResponse {
    void processFinish(Boolean result);
}
