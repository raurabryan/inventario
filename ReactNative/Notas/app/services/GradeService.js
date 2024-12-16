let grades = [{ subject: 'Matemáticas', grade: 9.5 }, { subject: 'Física', grade: 9.2 }];

export const saveGrade = (grade) => {
  grades.push(grade);
  console.log(grade);
};

export const getGrades = () => {
  return grades;
};

export const updateGrade = (nota) => {
  const gradeRetrieved = grades.find((g) => g.subject === nota.subject);
  if (gradeRetrieved) {
    gradeRetrieved.grade = nota.grade;
  }
};
