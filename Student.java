class Student {
  String email;
  String name;
  int[] choices = new int[5];
  int[] classes = new int[5];

  public Student(String email, String name, int[] choices) {
    this.email = email;
    this.name = name;
    this.choices = choices;
  }

  public String toString() {
    return name + " " + classes[0] + " " + classes[1] + " " + classes[2] + " " + classes[3] + " " + classes[4] + " // " + choices[0] + " " + choices[1] + " " + choices[2] + " " + choices[3] + " " + choices[4] + "\n";
  }
}