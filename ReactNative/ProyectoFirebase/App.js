import "react-native-gesture-handler";
import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { createDrawerNavigator } from "@react-navigation/drawer";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { Icon } from "react-native-elements";
import { GradeForm } from "./app/screens/GradeForm";
import { ListGrades } from "./app/screens/ListGrades";
import { ContenidoA } from './app/screens/ContenidoA';
import { ContenidoB } from './app/screens/ContenidoB';

const Stack = createNativeStackNavigator();
const Drawer = createDrawerNavigator();
const Tab = createBottomTabNavigator();

const TabNav = () => {
  return (
    <Tab.Navigator>
      <Tab.Screen
        name="TabContenidoA"
        component={ContenidoA}
        options={{
          headerShown: false,
          tabBarLabel: "Configuración",
          tabBarIcon: ({ size, color }) => (
            <Icon name="user" size={size} color={color} type="ant-design" />
          ),
        }}
      />
      <Tab.Screen
        name="TabContenidoB"
        component={ContenidoB}
        options={{
          headerShown: false,
          tabBarLabel: "Acerca De",
          tabBarIcon: ({ size, color }) => (
            <Icon name="mail" size={size} color={color} type="ant-design" />
          ),
        }}
      />
    </Tab.Navigator>
  );
};

const DrawerNav = () => {
  return (
    <Drawer.Navigator>
      <Drawer.Screen
        name="ListGradesNav"
        component={ListGrades}
        options={{ title: "Productos" }}
      />
      <Drawer.Screen
        name="DrawerEjemploTabs"
        component={StackNav}
        options={{ title: "Ejemplo tabs" }}
      />
      <Drawer.Screen
        name="TabContenidoA"
        component={TabNav}
        options={{ title: "Navegacion Tab" }}
      />
    </Drawer.Navigator>
  );
};

const StackNav = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen name="ListGradesNav" component={ListGrades} />
      <Stack.Screen name="GradeFormNav" component={GradeForm} />
    </Stack.Navigator>
  );
};

export default function App() {
  return (
    <NavigationContainer>
      <DrawerNav />
    </NavigationContainer>
  );
}
