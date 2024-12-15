import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack'
import {Home} from './app/screens/HomeScreen';
import {Contacts} from './app/screens/ContactScreen';
import {Producto} from './app/screens/ProductosScreen'

const Stack = createNativeStackNavigator();

export default function App() {
  return (
  <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name='HomeNav'  component={Home} />
        <Stack.Screen name='ProduNav'  component={Producto} />
        <Stack.Screen name ='ContactsNav' component={Contacts} />
      </Stack.Navigator>
  </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
