import { AppRegistry } from 'react-native';
import { StackNavigator } from 'react-navigation';

import HomeScreen from './Screens/HomeScreen';
import AccelerometerScreen from './Screens/AccelerometerScreen';
import DatabaseScreen from './Screens/DatabaseScreen';
import FileSystemScreen from './Screens/FileSystemScreen';
import GeolocationScreen from './Screens/GeolocationScreen';
import ContactsScreen from './Screens/ContactsScreen';

const Router = StackNavigator(
    {
        Home: {
            screen: HomeScreen,
        },
        AccelerometerPage: {
            screen: AccelerometerScreen,
            navigationOptions: {
                title: 'Accelerometer'
            }
        },
        Contacts: {
            screen: ContactsScreen,
        },
        Database: {
            screen: DatabaseScreen,
        },
        FileSystem: {
            screen: FileSystemScreen,
        },
        Geolocation: {
            screen: GeolocationScreen,
        }
    },
    {
        initialRouteName: 'Home',
        navigationOptions: {
            headerStyle: {
                backgroundColor: '#4054B2',
            },
            headerTintColor: '#fff',
            headerTitleStyle: {
                fontWeight: 'bold',
            },
        },
    }
);

AppRegistry.registerComponent('reactnative_app', () => Router);
