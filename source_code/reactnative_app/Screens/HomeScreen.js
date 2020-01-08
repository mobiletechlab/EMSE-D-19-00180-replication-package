import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View,
  StatusBar,
  TouchableOpacity
} from 'react-native';

export default class HomeScreen extends Component {
  static navigationOptions = {
    title: 'Feature Performance Benchmark'
  }
  constructor(props) {
    super(props);
    this.state = {
      pages: [
        'AccelerometerPage',
        'Contacts',
        'Database',
        'FileSystem',
        'Geolocation',
      ]
    }
  }
  render() {
    return (
      <View style={styles.container}>
        <StatusBar backgroundColor='#31429D' barStyle='light-content' />
        <Text>React Native benchmarks for platform/hardware feature access</Text>
        <View style={styles.pageList}>
          {
            this.state.pages.map((page, i) => {
              return (
                <TouchableOpacity key={i} style={{ marginBottom: 20 }} onPress={() => this.props.navigation.navigate(page)}>
                  <View style={{ backgroundColor: '#D6D7D7', padding: 10, borderRadius: 5 }}>
                    <Text style={{ textAlign: 'center', color: '#202020', fontWeight: 'bold' }}>{page.toUpperCase()}</Text>
                  </View>
                </TouchableOpacity>
              )
            })
          }
        </View>
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
