import React, { Component } from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    StatusBar,
    TouchableOpacity
} from 'react-native';

import Geolocation from 'react-native-geolocation-service';
import Permissions from 'react-native-permissions';

import Benchmarker from '../Components/Benchmarker';

export default class GeolocationScreen extends Component {
    static navigationOptions = {
        title: 'Geolocation'
    }

    componentDidMount() {
        Permissions.request('location')
            .then((val) => console.log(val))
            .catch((err) => console.error(err));
    }

    async runTask(currentRun) {
        return new Promise((resolve, reject) => {
            currentRun.startTime = new Date();
            let watchID = Geolocation.watchPosition(
                (position) => {
                    Geolocation.clearWatch(watchID);
                    currentRun.completionTime = new Date();
                    currentRun.resultValue = `Run ${currentRun.runNumber} completed (${position.coords.latitude}, ${position.coords.latitude}, ${position.coords.latitude})`;
                    resolve(currentRun);
                },
                (error) => {
                    Geolocation.clearWatch(watchID);
                    reject(error);
                }
            , { enableHighAccuracy: true, timeout: 20000 });
        });
    }

    render() {
        return (
            <View style={styles.container}>
                <StatusBar backgroundColor='#31429D' barStyle='light-content' />
                <Benchmarker description="Fetch the current geolocation" feature="Geolocation" task={this.runTask.bind(this)}></Benchmarker>
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
        paddingRight: 20
    },
    pageList: {
        marginTop: 20,
        alignSelf: 'stretch'
    }
});
