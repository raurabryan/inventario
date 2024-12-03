import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View,TextInput,Button  } from 'react-native';
import {useState}  from 'react';

export default function App() {
  const[peso, setPeso]=useState();
  const[estatura, setEstatura]=useState();
  const[imc, setImc]=useState();
  const[estado, setEstado]=useState();
  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>CALCULADORA IMC</Text>
      
      <TextInput 
        style={styles.caja}
        value={peso}
        onChangeText={(txt)=>{
          setPeso(txt);
        }}
        placeholder="Ingrese su peso"
      
      />
      <TextInput 
        style={styles.caja}
        value={estatura}
        onChangeText={(txt)=>{
          setEstatura(txt);
        }}
        
        placeholder="Ingrese su estatura"
      
      />
      <Button 
        title="CALCULAR IMC" 
        onPress={()=>{
          let estad= null;
          let pesoF= parseFloat(peso);
          let estaturaF = parseFloat(estatura);
          let imcF = pesoF/(estaturaF*estaturaF);
          setImc(imcF);
          
        }}

      />
      <Button 
        title="ESTADO" 
        onPress={()=>{
          let estad= null;
          
          if(imc<18.5){
            estad="Su peso es inferior al normal";
            setEstado(estad);
          }else if (imc>=18.5 && imc<25.0){
            estad="Su peso es normal";
            setEstado(estad);
          }else if (imc>=25.0 && imc<30.0){
            estad="Su peso es superior al normal";
            setEstado(estad);
          }else{
            estad="tiene obesidad";
            setEstado(estad);
          }
        }}

      />
      <Text style={styles.titulo}> Su IMC es de {imc} </Text>
      <Text style={styles.titulo}> Por lo cual {estado} </Text>
      
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'stretch',
    justifyContent: 'center',
    flexDirection: 'column',
    paddingHorizontal:10
  },
  caja:{
    borderColor: 'gray',
    borderWidth:1,
    paddingTop:5,
    paddingHorizontal:10,
    marginBottom:10
  },
  titulo:{
    fontSize:16,
    marginBottom:10,
    fontWeight:"bold",
    justifyContent: 'center',
    
  },
  
});
