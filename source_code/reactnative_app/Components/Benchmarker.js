import React, { Component } from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    TouchableOpacity,
    Slider,
    TextInput,
    FlatList
} from 'react-native';

import BenchmarkItem from '../Models/BenchmarkItem';

import moment from 'moment';

/* type Props = {
}; */
export default class Benchmarker extends Component {
    constructor(props) {
        super(props);

        this.state = {
            repetitions: 30,
            benchmarkRunning: false,
            benchmarkResults: [],
            outputLog: [],
            currentRunNumber: 0
        };
    }

    async resetBenchmark() {
        await this.setBenchmarkRunning(false);
        let numOfSuccessfulRuns = 0;
        let numOfFailedRuns = 0;
        let sumOfRunDuration = 0;

        for (let item of this.state.benchmarkResults) {
            if (!item.error) {
                numOfSuccessfulRuns++;
                sumOfRunDuration += item.completionTime.getTime() - item.startTime.getTime();
            } else {
                numOfFailedRuns++;
            }
        }
        await this.pushLogOutput(`Average duration of ${this.state.currentRunNumber} successful runs: ${(sumOfRunDuration / numOfSuccessfulRuns)} ms`);
        if (numOfFailedRuns > 0) {
            await this.pushLogOutput(`${numOfFailedRuns} runs failed.`);
        }
        await this.setCurrentRunNumber(0);
    }

    resetLogAndResults() {
        return new Promise((resolve, reject) => {
            this.setState({
                benchmarkResults: [],
                outputLog: []
            }, () => {
                resolve();
            });
        });
    }

    pushLogOutput(msg) {
        return new Promise((resolve, reject) => {
            this.setState(prevState => ({
                outputLog: [...prevState.outputLog, `${moment().format('HH:mm:ss:SSS')} - ${msg}`]
            }), () => {
                resolve();
            });
        });
    }

    pushBenchmarkResult(resultFromRun) {
        return new Promise((resolve, reject) => {
            this.setState(prevState => ({
                benchmarkResults: [...prevState.benchmarkResults, resultFromRun]
            }), () => {
                resolve();
            });
        });
    }

    incrementCurrentRunNumber() {
        return new Promise((resolve, reject) => {
            this.setState(prevState => ({
                currentRunNumber: prevState.currentRunNumber + 1
            }), () => {
                resolve();
            });
        });
    }

    setCurrentRunNumber(num) {
        return new Promise((resolve, reject) => {
            this.setState(prevState => ({
                currentRunNumber: num
            }), () => {
                resolve();
            });
        });
    }

    setBenchmarkRunning(isRunning) {
        return new Promise((resolve, reject) => {
            this.setState(prevState => ({
                benchmarkRunning: isRunning
            }), () => {
                resolve();
            });
        });
    }

    async initBenchmark() {
        await this.setBenchmarkRunning(true);
        await this.pushLogOutput(`${this.props.feature.toUpperCase()} Benchmark started`);
    }

    async runBenchmark() {
        if (this.state.currentRunNumber < this.state.repetitions) {
            let currentRun = new BenchmarkItem();
            currentRun.runNumber = this.state.currentRunNumber;
            currentRun.type = this.props.feature;

            try {
                let runResult = await this.props.task(currentRun);
                await this.pushLogOutput(runResult.resultValue);
                await this.incrementCurrentRunNumber();
                currentRun.runNumber = this.state.currentRunNumber;
                await this.pushBenchmarkResult(currentRun);
                await this.runBenchmark(); // Run test again if more than 1 repetition

            } catch (e) {
                console.error(e)
            }

        } else {
            await this.resetBenchmark();
        }
    }

    async startBenchmarkRun() {
        if (!this.state.benchmarkRunning && this.state.repetitions > 0) {
            await this.resetLogAndResults();
            await this.initBenchmark();

            await this.runBenchmark();

        } else {
            await this.pushLogOutput(`Benchmark cancelled.`);
            await this.resetBenchmark();
        }
    }

    render() {
        return (
            <View style={styles.container}>
                <Text>{this.props.description}</Text>
                <Text style={{ marginTop: 10 }}>Please specify test parameters</Text>
                <View style={styles.repetitionGrid}>
                    <Text style={{ flex: 3 }}>Repetitions</Text>
                    <Slider style={{ flex: 6 }} minimumValue={1} maximumValue={50} step={1} value={this.state.repetitions} onValueChange={(val) => this.setState({ repetitions: val })} />
                    <TextInput style={{ flex: 1, textAlign: 'center' }} keyboardType="numeric" defaultValue={this.state.repetitions.toString()} onChangeText={(text) => this.setState({ repetitions: Number.parseInt(text) })} />
                </View>
                <TouchableOpacity style={{ marginBottom: 20 }} onPress={() => this.startBenchmarkRun()}>
                    {/* <TouchableOpacity style={{ marginBottom: 20 }} onPress={() => this.props.task()}> */}
                    <View style={{ backgroundColor: '#D6D7D7', padding: 10, borderRadius: 5 }}>
                        <Text style={{ textAlign: 'center', color: '#202020', fontWeight: 'bold' }}>{this.state.benchmarkRunning ? 'STOP' : 'START'} BENCHMARK</Text>
                    </View>
                </TouchableOpacity>
                <FlatList
                    data={this.state.outputLog}
                    renderItem={({ item }) => <Text>{item}</Text>}
                    keyExtractor={(item, index) => index.toString()}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignSelf: 'stretch',
        alignItems: 'stretch',
        paddingTop: 10,
        paddingLeft: 10,
        paddingRight: 10
    },
    repetitionGrid: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center'
    }
});
