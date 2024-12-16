import { StatusBar } from 'expo-status-bar';
import { StyleSheet } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { GradeForm } from './app/screens/GradeForm';
import { ListGrades } from './app/screens/ListGrades';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen 
          name="ListGradesNav" 
          component={ListGrades} 
          options={{ title: 'Lista de Notas' }} 
        />
        <Stack.Screen 
          name="GradeFromNav" 
          component={GradeForm} 
          options={{ title: 'Formulario de Notas' }} 
        />
      </Stack.Navigator>
      <StatusBar style="auto" />
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

