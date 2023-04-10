class Class {
  int id;
  int studentCount;

  final int capacity = 16;

  public Class(int id) {
    this.id = id;
    studentCount = 1; // we always create a class with one student by the way we have our algorithm set up
  }

  public boolean addStudent() {
    if(studentCount == capacity) {
      return false;
    }
    else {
      studentCount++;
      return true;
    }
  }
}