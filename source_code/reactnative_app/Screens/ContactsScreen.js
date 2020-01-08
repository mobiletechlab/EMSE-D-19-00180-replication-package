import React, { Component } from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    StatusBar,
    TouchableOpacity
} from 'react-native';

import Benchmarker from '../Components/Benchmarker';
import Contacts from 'react-native-contacts'

export default class ContactsScreen extends Component {
    static navigationOptions = {
        title: 'Contacts'
    }

    createContact(currentRun) {
        return new Promise((resolve, reject) => {
            let contact = {
                familyName: 'REACTNATIVE_FAKE',
                givenName: currentRun.runNumber.toString(),
                phoneNumbers: [{
                    label: 'mobile',
                    number: "123456789",
                }],
            }

            Contacts.addContact(contact, (err) => {
                if (err) reject(err);
                resolve();
            });
        });
    }

    async runTask(currentRun) {
        return new Promise((resolve, reject) => {
            currentRun.startTime = new Date();
            this.createContact(currentRun)
                .then(() => {
                    currentRun.completionTime = new Date();
                    currentRun.resultValue = `Run ${currentRun.runNumber} completed (contact saved)`;
                    resolve(currentRun);
                },
                    (error) => reject(error)
                );
        });
    }

    render() {
        return (
            <View style={styles.container}>
                <StatusBar backgroundColor='#31429D' barStyle='light-content' />
                <Benchmarker description="Save contacts to the device's contact list." feature="Contacts" task={this.runTask.bind(this)}></Benchmarker>
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
