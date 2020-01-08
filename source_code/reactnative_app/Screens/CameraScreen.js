import React, { Component } from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    StatusBar,
    TouchableOpacity
} from 'react-native';

export default class CameraScreen extends Component {
    static navigationOptions = {
        title: 'Camera'
    }

    render() {
        return (
            <View style={styles.container}>
                <StatusBar backgroundColor='#31429D' barStyle='light-content' />
                <Text>Camera</Text>
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
