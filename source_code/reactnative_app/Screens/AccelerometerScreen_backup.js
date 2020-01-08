import React, { PureComponent } from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    StatusBar,
    TouchableOpacity
} from 'react-native';

import { decorator as sensors } from 'react-native-sensors';

import Benchmarker from '../Components/Benchmarker';

type Props = {
    Accelerometer: Object
}
class AccelerometerScreenBackup extends PureComponent<void, Props, void> {

    runTask(currentRun) {
        currentRun.startTime = new Date();
        return new Promise((resolve, reject) => {
            console.log('inside promise')
            let acceleration = this.props.Accelerometer;
            currentRun.completionTime = new Date();
            currentRun.resultValue = `Run ${currentRun.runNumber} completed (${acceleration.x}, ${acceleration.y}, ${acceleration.z})`;
            resolve(currentRun);
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

export default sensors({
    Accelerometer: {
        updateInterval: 100, // optional
    },
    Gyroscope: false,
})(AccelerometerScreen);

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
