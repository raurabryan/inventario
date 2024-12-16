import { View, StyleSheet } from 'react-native';
import { Input, Button } from '@rneui/base';
import { useState } from 'react';
import { saveGrade, updateGrade } from '../services/GradeService';

export const GradeForm = ({ navigation, route }) => {
  const isNew = !route.params?.notitas;
  const [subject, setSubject] = useState(isNew ? "" : route.params.notitas.subject);
  const [grade, setGrade] = useState(isNew ? "" : `${route.params.notitas.grade}`);
  const [errorSubject, setErrorSubject] = useState();
  const [errorGrade, setErrorGrade] = useState();

  const validate = () => {
    let hasErrors = false;

    if (!subject) {
      setErrorSubject("Debe ingresar una materia");
      hasErrors = true;
    }

    const gradeFloat = parseFloat(grade);
    if (isNaN(gradeFloat) || gradeFloat < 0 || gradeFloat > 10) {
      setErrorGrade("Debe ingresar una nota entre 0 y 10");
      hasErrors = true;
    }

    return hasErrors;
  };

  const save = () => {
    setErrorSubject(null);
    setErrorGrade(null);

    if (!validate()) {
      if (isNew) {
        saveGrade({ subject, grade });
      } else {
        updateGrade({ subject, grade });
      }
      navigation.goBack();
      route.params.fnRefresh();
    }
  };

  return (
    <View style={styles.container}>
      <Input
        value={subject}
        onChangeText={setSubject}
        placeholder="Ejemplo: Matemáticas"
        label="Materia"
        errorMessage={errorSubject}
        disabled={!isNew}
      />
      <Input
        value={grade}
        onChangeText={setGrade}
        placeholder="0-10"
        label="Nota"
        errorMessage={errorGrade}
      />
      <Button
        title="Guardar"
        icon={{
          name: "save",
          type: "entypo",
          color: "white"
        }}
        buttonStyle={styles.saveButton}
        onPress={save}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  saveButton: {
    backgroundColor: 'green',
  },
});
