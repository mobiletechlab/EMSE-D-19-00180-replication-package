package de.wwu.performancebenchmark;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.wwu.performancebenchmark.model.BenchmarkItem;
import de.wwu.performancebenchmark.model.BenchmarkType;

/**
 * Generic benchmark fragment that contains all relevant logic to start/pause/stop benchmarks
 * and respective monitoring.
 */
public class BenchmarkFragment extends Fragment {
    // The type of benchmark
    public static final String TYPE = "type";

    // Activity performing the actual benchmark
    protected OnBenchmarkInteractionListener mListener;

    // View elements
    protected View rootView;
    protected TextView outputMessages;

    // Variables for benchmark run management
    protected int currentRunNumber;
    protected BenchmarkItem currentRun;
    protected ArrayList<BenchmarkItem> results;
    protected boolean allowNewRuns = false;

    public BenchmarkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param benchmarkType Type.
     * @return A new instance of fragment BenchmarkFragment.
     */
    public static BenchmarkFragment newInstance(BenchmarkType benchmarkType) {
        BenchmarkFragment fragment = new BenchmarkFragment();
        Bundle args = new Bundle();
        args.putSerializable(TYPE, benchmarkType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_benchmark, container, false);

        // Retrieve fields
        final EditText repetitionsValueField = rootView.findViewById(R.id.setupRepetitionsValue);
        final SeekBar repetitionsBar = rootView.findViewById(R.id.setupRepetitionsBar);
        outputMessages = rootView.findViewById(R.id.benchmarkOutput);

        // Set default value
        repetitionsValueField.setText(getContext().getResources().getInteger(R.integer.setup_repetitions_default) + "");

        // Listener for repetitions text field changes
        repetitionsValueField.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                int value = 0;

                if (s.length() > 0) {
                    value = Integer.valueOf(s.toString());
                    if (value < getContext().getResources().getInteger(R.integer.setup_repetitions_min)) {
                        value = getContext().getResources().getInteger(R.integer.setup_repetitions_min);
                    } else if (value > getContext().getResources().getInteger(R.integer.setup_repetitions_max)) {
                        value = getContext().getResources().getInteger(R.integer.setup_repetitions_max);
                    }
                }

                repetitionsBar.setProgress(value);
            }
        });

        // Listener for repetitions bar changes
        repetitionsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar b, int progress, boolean fromUser) {
                repetitionsValueField.setText(String.valueOf(progress));
                repetitionsValueField.setSelection(String.valueOf(progress).length());
            }
        });

        // Listener for benchmark start/stop
        final Button benchmarkStartStop = rootView.findViewById(R.id.benchmarkStartStop);
        benchmarkStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (benchmarkStartStop.getText().equals(getString(R.string.setup_btnStart))) {
                    // Start benchmark
                    prepareBenchmarkStart();
                } else {
                    // Interrupt benchmark runs
                    allowNewRuns = false;
                    mListener.onBenchmarkReset();
                    if(currentRun != null) currentRun.setError(true);
                    sendMessage(getString(R.string.setup_log_userCancel));
                    afterBenchmarkStop();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBenchmarkInteractionListener) {
            // Get activity container
            mListener = (OnBenchmarkInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBenchmarkInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onBenchmarkCompleted(String value) {
        Date completionTime = new Date(System.currentTimeMillis());

        // Save benchmark run
        if (currentRun != null) {
            currentRun.setCompletionTime(completionTime);
            currentRun.setResultValue(value);

            results.add(currentRun);
        }

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(allowNewRuns) nextBenchmarkRepetition();
            }
        }, 2000);
    }

    public void onBenchmarkFailed(String value) {
        Date completionTime = new Date(System.currentTimeMillis());

        // Save benchmark run
        if (currentRun != null) {
            currentRun.setCompletionTime(completionTime);
            currentRun.setResultValue(value);
            currentRun.setError(true);

            results.add(currentRun);
        }

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(allowNewRuns) nextBenchmarkRepetition();
            }
        }, 500);
    }

    protected void printBenchmarkState(BenchmarkItem item) {
        if (item == null || item.getStartTime() == null || item.getCompletionTime() == null) return;

        // Print start
        String messageStart = "Run " + item.getRunNumber() + " started";
        sendMessage(messageStart, item.getStartTime());

        // Print result
        String messageResult;
        if (currentRun.isError()) {
            messageResult = "Run " + item.getRunNumber() + " failed (" + item.getResultValue() + ")";
        } else {
            messageResult = "Run " + item.getRunNumber() + " completed (" + item.getResultValue() + ")";
        }
        sendMessage(messageResult, item.getCompletionTime());
    }

    public void sendMessage(String message) {
        sendMessage(message, null);
    }

    public void sendMessage(String message, Date time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
            if (time == null) {
                time = (new Date(System.currentTimeMillis()));
            }
            outputMessages.setText(outputMessages.getText() + "\n" + sdf.format(time) + " - " + message);
        } catch (Exception ex) {
        }
    }

    protected void prepareBenchmarkStart() {
        // Block UI
        rootView.findViewById(R.id.setupRepetitionsValue).setEnabled(false);
        rootView.findViewById(R.id.setupRepetitionsBar).setEnabled(false);

        // Update button
        Button btn = rootView.findViewById(R.id.benchmarkStartStop);
        btn.setText(getString(R.string.setup_btnStop));

        // Reset log
        outputMessages.setText("");
        sendMessage(getArguments().getSerializable(TYPE) + " " + getString(R.string.setup_log_start));

        // Set initial values and start
        currentRunNumber = 0;
        results = new ArrayList<>();

        allowNewRuns = true;
        runBenchmark();
    }

    protected void runBenchmark() {
        // Prepare result item
        currentRun = new BenchmarkItem((BenchmarkType) getArguments().getSerializable(TYPE), ++currentRunNumber);

        // Notify activity to start benchmark
        currentRun.setStartTime(new Date(System.currentTimeMillis()));
        mListener.onBenchmarkStart();
    }

    protected void nextBenchmarkRepetition() {
        // Update log
        printBenchmarkState(currentRun);

        // Reset state
        mListener.onBenchmarkReset();
        currentRun = null;

        // Start subsequent run
        if (currentRunNumber < ((SeekBar) rootView.findViewById(R.id.setupRepetitionsBar)).getProgress()) {
            // Delay new start to let UI settle
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if(allowNewRuns) runBenchmark();
                }
            }, 500);
        } else {
            // All runs done
            afterBenchmarkStop();
        }
    }

    protected void afterBenchmarkStop() {
        // Unblock UI
        rootView.findViewById(R.id.setupRepetitionsValue).setEnabled(true);
        rootView.findViewById(R.id.setupRepetitionsBar).setEnabled(true);

        // Update button
        Button btn = rootView.findViewById(R.id.benchmarkStartStop);
        btn.setText(getString(R.string.setup_btnStart));

        // Calculate average benchmark time
        long sumOfRunDuration = 0;
        int numOfSuccessfulRuns = 0;
        for (BenchmarkItem item : results) {
            if (!item.isError()) {
                numOfSuccessfulRuns++;
                sumOfRunDuration += item.getCompletionTime().getTime() - item.getStartTime().getTime();
            }
        }
        if (numOfSuccessfulRuns > 0) {
            sendMessage("Average duration of " + numOfSuccessfulRuns + " successful runs: " + (sumOfRunDuration / numOfSuccessfulRuns) + "ms");
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity.
     */
    public interface OnBenchmarkInteractionListener {
        // Called when the benchmark should be executed
        void onBenchmarkStart();

        // Called when the benchmark class should reset itself to a pre-benchmark state
        void onBenchmarkReset();
    }
}
