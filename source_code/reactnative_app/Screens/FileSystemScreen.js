import React, { Component } from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    StatusBar,
    TouchableOpacity,
    Button,
    Image
} from 'react-native';

import * as RNFS from 'react-native-fs';

import Benchmarker from '../Components/Benchmarker';

export default class FileSystemScreen extends Component {
    static navigationOptions = {
        title: 'Filesystem'
    }

    constructor(props) {
        super(props);
        this.state = {
            image: ''
        };
    }

    async runTask(currentRun) {
        return new Promise((resolve, reject) => {
            currentRun.startTime = new Date();
            RNFS.readFile(RNFS.DocumentDirectoryPath + '/benchmarker.png', 'base64')
                .then(() => {
                    currentRun.completionTime = new Date();
                    //this.setState({ image: b64 });
                    currentRun.resultValue = `Run ${currentRun.runNumber} completed.`;
                    resolve(currentRun);
                })
                .catch((error) => {
                    console.error('Error in runTask in FileSystemScreen');
                    reject(error);
                });
        });
    }

    async saveBenchmarkImageToDevice() {
        RNFS.writeFile(RNFS.DocumentDirectoryPath + '/benchmarker.png', require('./FileSystemTest/file_benchmark_b64').default, 'base64')
            .then((res) => {
                alert(res);
            })
            .catch((error) => {
                alert('An error occurred: ', JSON.stringify(error));
                console.error(error);
            });
    }

    render() {
        return (
            <View style={styles.container}>
                <StatusBar backgroundColor='#31429D' barStyle='light-content' />
                {/*
                    this.state.image.length > 0 ?
                        <Image
                            style={{
                                width: 300,
                                height: 300,
                                resizeMode: 'contain',
                            }}
                            source={{ uri: `data:image/png;base64,${this.state.image}` }}
                        /> : <View></View>
                        */}
                <Button title="Save image to device (only once)" onPress={this.saveBenchmarkImageToDevice.bind(this)} />
                <Benchmarker description="Fetch a base64-encoded image from internal device storage and display in image preview" feature="Filesystem" task={this.runTask.bind(this)}></Benchmarker>
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
