class Class {
  int id;
  int studentCount;

  final int capacity = 16;

  public Class(int id) {
    this.id = id;
    studentCount = 1; // we always create a class with one student by the way we have our algorithm set up ... see SeniorSeminar
  }

  public boolean addStudent() { // adds one student if possible and returns true; returns false if not
    if(studentCount == capacity) {
      return false;
    }
    else {
      studentCount++;
      return true;
    }
  }
}