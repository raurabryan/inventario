import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View,Button,TextInput } from 'react-native';
import {useState} from 'react';

export default function App() {

  const [num1,setnum1]=useState("Ingrese el primer digito");
  const [num2,setnum2]=useState("Ingrese el segundo digito");
  const [sum,setsum]=useState();
  const [rest,setrest]=useState();
  const [mult,setMult]=useState();
  

  return (
    <View style={styles.container}>
      <Text>Calculadora</Text>
      <TextInput 
        style={styles.cajatexto}
        value={num1}
        onChangeText={(txt)=>{
          setnum1(txt);
        }}
      />
      <TextInput 
        style={styles.cajatexto}
        value={num2}
        onChangeText={(txt)=>{
          setnum2(txt);
        }}
      />
      <Button 
        title="Sumar"
        onPress={()=>{
          let numero1= parseInt(num1);
          let numero2 = parseInt(num2);
          let  sumar = (numero1+numero2);
          setsum(sumar);
        }}
      />
      <Button 
        title="Restar"
        onPress={()=>{
          let numero1= parseInt(num1);
          let numero2 = parseInt(num2);
          let restar = (numero1-numero2);
          setrest(restar);
        }}

      />
       <Button 
        title="Multiplicar"
        onPress={()=>{
          let numero1= parseInt(num1);
          let numero2 = parseInt(num2);
          let multiplicar = (numero1*numero2);
          setMult(multiplicar);
        }}

      />
      
      <Text>El resultado de la suma es : {sum}</Text>
      <Text>El resultado de la resta es : {rest}</Text>
      <Text>El resultado de la multiplicacion es : {mult}</Text>
      <StatusBar style="auto" />
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
