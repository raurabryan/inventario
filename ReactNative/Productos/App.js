import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, FlatList, TextInput, Button, Alert } from 'react-native';
import { useState } from 'react';

let productos = [
  { codigo: '1', nombre: 'leche', categoria: 'lacteo', precioCompra: '1', precioTotal: '' },
  { codigo: '2', nombre: 'Norteño', categoria: 'licor', precioCompra: '7', precioTotal: '' },
];

let esNuevo = true;
let indiceSeleccionado = -1;

export default function App() {
  const [txtCodigo, setTxtCodigo] = useState();
  const [txtNombre, setTxtNombre] = useState();
  const [txtCategoria, setTxtCategoria] = useState();
  const [txtPrecioCompra, setTxtPrecioCompra] = useState();
  const [txtPrecioTotal, setTxtPrecioTotal] = useState();
  const [numElementos, setNumElementos] = useState(productos.length);

  // Función para calcular el precio total
  let calcularPrecioTotal = (precioCompra) => {
    return (parseFloat(precioCompra) * 1.2).toFixed(2); // 20% adicional
  };

  // Item de Producto
  let ItemProductos = (props) => {
    return (
      <View style={styles.itemProducto}>
        <View style={styles.itemIndice}>
          <Text>{props.index}</Text>
        </View>
        <View style={styles.itemContenido}>
          <Text style={styles.textoPrincipal}>{props.producto.nombre} ({props.producto.categoria})</Text>
          <Text style={styles.textoSecundario}>Precio Compra: ${props.producto.precioCompra}</Text>
          <Text style={styles.textoSecundario}>Total: ${props.producto.precioTotal}</Text>
        </View>
        <View style={styles.itemBotones}>
          <Button
            title="E"
            color="red"
            onPress={() => {
              setTxtCodigo(props.producto.codigo);
              setTxtNombre(props.producto.nombre);
              setTxtCategoria(props.producto.categoria);
              setTxtPrecioCompra(props.producto.precioCompra);
              setTxtPrecioTotal(props.producto.precioTotal);
              esNuevo = false;
              indiceSeleccionado = props.index;
            }}
          />
          <Button
            title="X"
            color="green"
            onPress={() => {
              productos.splice(props.index, 1);
              setNumElementos(productos.length);
            }}
          />
        </View>
      </View>
    );
  };

  let limpiar = () => {
    setTxtCodigo(null);
    setTxtNombre(null);
    setTxtCategoria(null);
    setTxtPrecioCompra(null);
    setTxtPrecioTotal(null);
    esNuevo = true;
  };

  let existeProducto = () => {
    for (let i = 0; i < productos.length; i++) {
      if (productos[i].codigo == txtCodigo) {
        return true;
      }
    }
    return false;
  };

  let guardarProducto = () => {
    const precioTotal = calcularPrecioTotal(txtPrecioCompra);

    if (esNuevo) {
      if (existeProducto()) {
        Alert.alert('INFO', 'Ya existe un producto con el código ' + txtCodigo);
      } else {
        let producto = {
          codigo: txtCodigo,
          nombre: txtNombre,
          categoria: txtCategoria,
          precioCompra: txtPrecioCompra,
          precioTotal: precioTotal,
        };
        productos.push(producto);
      }
    } else {
      productos[indiceSeleccionado].nombre = txtNombre;
      productos[indiceSeleccionado].categoria = txtCategoria;
      productos[indiceSeleccionado].precioCompra = txtPrecioCompra;
      productos[indiceSeleccionado].precioTotal = precioTotal;
    }
    limpiar();
    
    setNumElementos(productos.length);
  };

  return (
    <View style={styles.container}>
      <View style={styles.areaCabecera}>
        <Text>PRODUCTOS</Text>
        <TextInput
          style={styles.txt}
          value={txtCodigo}
          placeholder="Código"
          keyboardType="numeric"
          editable={esNuevo} // No se puede editar si no es nuevo
          onChangeText={setTxtCodigo}
        />
        <TextInput
          style={styles.txt}
          value={txtNombre}
          placeholder="Nombre del Producto"
          onChangeText={setTxtNombre}
        />
        <TextInput
          style={styles.txt}
          value={txtCategoria}
          placeholder="Categoría"
          onChangeText={setTxtCategoria}
        />
        <TextInput
          style={styles.txt}
          value={txtPrecioCompra}
          placeholder="Precio de Compra"
          keyboardType="numeric"
          onChangeText={(value) => {
            setTxtPrecioCompra(value);
            setTxtPrecioTotal(calcularPrecioTotal(value)); // Actualizar total en tiempo real
          }}
        />
        <TextInput
          style={styles.txt}
          value={txtPrecioTotal}
          placeholder="Total"
          editable={false} // No se puede modificar manualmente
        />
        <View style={styles.areaBotones}>
          <Button title="Guardar" onPress={() => guardarProducto()} />
          <Button title="Nuevo" onPress={() => limpiar()} />
        </View>
        <Text>Elementos: {numElementos}</Text>
      </View>
      <View style={styles.areaContenido}>
        <FlatList
          data={productos}
          renderItem={(elemento) => (
            <ItemProductos index={elemento.index} producto={elemento.item} />
          )}
          keyExtractor={(item) => item.codigo}
        />
      </View>
      <View style={styles.areaPie}>
        <Text>Autor: Bryan Raura</Text>
      </View>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'flex-start',
    paddingTop: 40,
    paddingHorizontal: 10,
  },
  itemProducto: {
    backgroundColor: '#ffcc80',
    marginBottom: 10,
    padding: 10,
    flexDirection: 'row',
    borderRadius: 5,
  },
  textoPrincipal: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  textoSecundario: {
    fontSize: 14,
    fontStyle: 'italic',
  },
  areaCabecera: {
    width: '100%',
    backgroundColor: '#add8e6',
    padding: 10,
  },
  areaContenido: {
    flex: 1,
    width: '100%',
    backgroundColor: '#f5f5f5',
  },
  areaPie: {
    width: '100%',
    backgroundColor: '#ffb6c1',
    alignItems: 'center',
    padding: 5,
  },
  itemIndice: {
    justifyContent: 'center',
    alignItems: 'center',
    paddingRight: 10,
  },
  itemContenido: {
    flex: 1,
  },
  txt: {
    borderWidth: 1,
    borderColor: 'gray',
    paddingVertical: 5,
    paddingHorizontal: 10,
    marginBottom: 10,
    borderRadius: 5,
  },
  areaBotones: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    marginTop: 10,
  },
  itemBotones: {
    justifyContent: 'space-between',
    flexDirection: 'row',
  },
});
