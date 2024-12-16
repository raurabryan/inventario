import { View, StyleSheet, Text, FlatList, TouchableHighlight } from 'react-native';
import { getGrades } from '../services/GradeService';
import { FAB, ListItem, Avatar } from '@rneui/base';
import { useState } from 'react';

export const ListGrades = ({ navigation }) => {
  const [time, setTime] = useState();

  const refreshList = () => {
    setTime(new Date().getTime());
  };

  const ItemGrade = ({ nota }) => (
    <TouchableHighlight onPress={() => {
      navigation.navigate("GradeFromNav", { notitas: nota, fnRefresh: refreshList });
    }}>
      <ListItem bottomDivider>
        <Avatar
          title={nota.subject.substring(0, 1)}
          containerStyle={{ backgroundColor: 'green' }}
          rounded
        />
        <ListItem.Content>
          <ListItem.Title>{nota.subject}</ListItem.Title>
        </ListItem.Content>
        <ListItem.Content>
          <ListItem.Title>{nota.grade}</ListItem.Title>
        </ListItem.Content>
        <ListItem.Chevron />
      </ListItem>
    </TouchableHighlight>
  );

  return (
    <View style={styles.container}>
      <Text>LISTA DE NOTAS</Text>
      <FlatList
        data={getGrades()}
        renderItem={({ item }) => <ItemGrade nota={item} />}
        keyExtractor={(item, index) => index.toString()}
        extraData={time}
      />
      <FAB
        title="+"
        placement="right"
        onPress={() => {
          navigation.navigate("GradeFromNav", { notitas: null, fnRefresh: refreshList });
        }}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'stretch',
    justifyContent: 'center',
  },
});
