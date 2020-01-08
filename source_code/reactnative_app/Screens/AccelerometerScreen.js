import React, { Component } from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    StatusBar,
    TouchableOpacity
} from 'react-native';

import ReactNativeSensors from 'react-native-sensors';

import Benchmarker from '../Components/Benchmarker';

export default class AccelerometerScreen extends Component {

    runTask(currentRun) {
        currentRun.startTime = new Date();
        return new Promise((resolve, reject) => {
            try {
                let observable = new ReactNativeSensors.Accelerometer({ updateInterval: 50 });

                observable.first().subscribe((acceleration) => {
                    currentRun.completionTime = new Date();
                    currentRun.resultValue = `Run ${currentRun.runNumber} completed (${acceleration.x}, ${acceleration.y}, ${acceleration.z})`;
                    resolve(currentRun);
                });
            } catch (e) {
                reject(e);
            }
        });
    }

    render() {
        return (
            <View style={styles.container}>
                <StatusBar backgroundColor='#31429D' barStyle='light-content' />
                <Benchmarker description="Fetch the current accelerometer values" feature="Accelerometer" task={this.runTask.bind(this)}></Benchmarker>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
        paddingTop: 20,
        paddingLeft: 20,
        paddingRight: 20,
        alignSelf: 'stretch'
    },
    pageList: {
        marginTop: 20,
        alignSelf: 'stretch'
    }
});
