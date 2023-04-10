class Student {
  String email;
  String name;
  int[] choices = new int[5];
  int[] classes = new int[5];
  int[] classrooms = new int[5];

  public Student(String email, String name, int[] choices) {
    this.email = email;
    this.name = name;
    this.choices = choices;
  }

  public boolean inClass(int x) {
    for(int i=0; i<5; i++) {
      if(classes[i]==x) return true;
    }
    return false;
  }

  // public String toString() {
  //   return name + " " + classes[0] + " " + classes[1] + " " + classes[2] + " " + classes[3] + " " + classes[4] + " // " + choices[0] + " " + choices[1] + " " + choices[2] + " " + choices[3] + " " + choices[4] + "\n";
  // }

  public String toString() {
    return "\n\n" + name + ": "
      + "\n\t Block 1: " + "session " + classes[0] + " in room " + (classrooms[0]+1)
      + "\n\t Block 2: " + "session " + classes[1] + " in room " + (classrooms[1]+1)
      + "\n\t Block 3: " + "session " + classes[2] + " in room " + (classrooms[2]+1)
      + "\n\t Block 4: " + "session " + classes[3] + " in room " + (classrooms[3]+1)
      + "\n\t Block 5: " + "session " + classes[4] + " in room " + (classrooms[4]+1);
  }
}