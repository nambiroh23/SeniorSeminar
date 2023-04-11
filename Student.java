class Student {
  String email;
  String name;
  int[] choices = new int[5];
  int[] orChoices = new int[5]; // to save the originals when we change the choices array to zero as choices are satisfied
  int[] classes = new int[5];
  int[] classrooms = new int[5]; // where

  public Student(String email, String name, int[] choices) {
    this.email = email;
    this.name = name;
    this.choices = choices;
    for(int i = 0; i<5; i++) {
      orChoices[i] = choices[i];
    }
    // orChoices = choices; ... this did not work because computers are weird ):
  }

  public boolean inClass(int x) { // is this student already in class x? ... for use in checking before adding
    for(int i=0; i<5; i++) {
      if(classes[i]==x) return true;
    }
    return false;
  }

  public boolean choseThis(int x) { // did this student mark this class among their top 5? ... for use in counting conflicts
    for(int i=0; i<5; i++) {
      if(orChoices[i]==x || orChoices[i]==0) return true;
    }
    return false;
  }

  // public String toString() { ... old/bad
  //   return name + " " + classes[0] + " " + classes[1] + " " + classes[2] + " " + classes[3] + " " + classes[4] + " // " + choices[0] + " " + choices[1] + " " + choices[2] + " " + choices[3] + " " + choices[4] + "\n";
  // }

  public String toString() { // for printing
    return "\n\n" + name + ": "
      + "\n\t Block 1: " + "session " + classes[0] + " in room " + (classrooms[0]+1)
      + "\n\t Block 2: " + "session " + classes[1] + " in room " + (classrooms[1]+1)
      + "\n\t Block 3: " + "session " + classes[2] + " in room " + (classrooms[2]+1)
      + "\n\t Block 4: " + "session " + classes[3] + " in room " + (classrooms[3]+1)
      + "\n\t Block 5: " + "session " + classes[4] + " in room " + (classrooms[4]+1);
  }
}