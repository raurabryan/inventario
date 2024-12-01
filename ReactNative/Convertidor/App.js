import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View,Button,TextInput } from 'react-native';
import {useState} from 'react';

export default function App() {
  const [dolares,setDolares]=useState();
  const [pesosCol,setPesosCol]=useState();
  const [pesosMex,setpesosMex]=useState();
  const [euros,setEuros]=useState();

  return (
    <View style={styles.container}>
      <Text>CONVERTIDOR DE DIVISAS</Text>
      <StatusBar style="auto" />
      <Text>INGRESE EL MONTO EN DOLARES</Text>
      <TextInput 
        style={styles.cajatexto}
        value={dolares}
        onChangeText={(txt)=>{
          setDolares(txt);
        }}
      />
      <Button 
        title="Pesos Colombianos"
        onPress={()=>{
          let dolar= parseFloat(dolares);
          let pesCol = dolar*4374;
          setPesosCol(pesCol);
        }}
      />
      <Button 
        title="Pesos mexicanos"
        onPress={()=>{
          let dolar= parseFloat(dolares);
          let pesMex= dolar*20.38;
          setpesosMex(pesMex);
        }}
      />
      <Button 
        title="Euros"
        onPress={()=>{
          let dolar= parseFloat(dolares);
          let euro= dolar*0.95;
          setEuros(euro);
        }}
      />
      <Text>{dolares} DOLARES EN PESOS COLOMBIANOS ES:  {pesosCol}</Text>
      <Text>{dolares} DOLARES EN PESOS MEXICANOS ES:  {pesosMex}</Text>
      <Text>{dolares} DOLARES EN EUROS ES:  {euros}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  cajatexto:{
    borderColor:"black",
    borderWidth:1,
    paddingTop:5,
    paddingHorizontal:10,

  }
});
