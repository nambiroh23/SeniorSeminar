import java.io.*;
import java.util.*;

class SeniorSeminar {
  File data = new File("data.csv");
  // String x = scan1.nextLine(); // skip line
  ArrayList<Student> students = new ArrayList<Student>();
  Student[] stu; // we'll use this for easy changes, then put it back in the ArrayList later
  Block[] blocks = new Block[5]; // each block consists of the five classes held then
  int conflicts = 0; // a counter for later

  public SeniorSeminar() {

    // **load data**
    
    try {
      Scanner scan2;
      Scanner scan1 = new Scanner(data);
      while(scan1.hasNextLine()) {
        scan2 = new Scanner(scan1.nextLine());
        scan2.useDelimiter(",");
        String e = scan2.next();
        String n = scan2.next();
        int[] c = new int[5];
        for(int i = 0; i<5; i++) {
          c[i] = scan2.nextInt();
        }
        students.add(new Student(e,n,c));
      }
    } catch(FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    // System.out.println(students);

    // **put it in the array**
    
    stu = new Student[students.size()];

    for(int i=0; i<stu.length; i++) {
      stu[i] = students.get(i);
    }

    
    for(int i=0; i<5; i++) { // initialize each block for later use
      blocks[i] = new Block();
    }
    
  }

  public void sort() {

    // **sort based on preferences**
    
    for(int i = 0; i<5; i++) { // for each block
      for(int q=0; q<stu.length; q++) { // for each student
        for(int j = 0; j<5; j++) { // for each choice
          if(stu[q].choices[j]!=0) { // if this choice hasn't been satisfied yet
            if(blocks[i].classInBlock(stu[q].choices[j])) { // if the class exists already in this block
              if(blocks[i].rooms[blocks[i].findInBlock(stu[q].choices[j])].addStudent()) { // if we can add a student (b/c capacity), do so
                stu[q].classes[i] = stu[q].choices[j]; // set that student's class to this choice for this block, since we just gave them that choice
                stu[q].choices[j] = 0; // this choice has now been satisfied
                break; // stop going through this student for this block; we'll come back to them next block (:)
              } // close check for space if
            } // close check if class already exists if
            else { // if class does not already exist
              if(blocks[i].nextAvail()!=-1) { // if there exists a free classroom this block
                if(countClass(stu[q].choices[j])<2) { // if there are less than 2 sections of this class so far
                  blocks[i].addClass(stu[q].choices[j]); // add class to this block
                  stu[q].classes[i] = stu[q].choices[j]; // give it to the student
                  stu[q].choices[j] = 0; // that choice has been satisfied
                } // close check section count if
              } // close if space to add a new class
            } // close else (if class does not already exist)
          } // close if choice still unsatisfied
        } // close for each choice
      } // close for each student
    } // close for each block

    // **fill the rest** by jist running through

    for(int q=0; q<stu.length; q++) {
      for(int i = 0; i<5; i++) {
        if(stu[q].classes[i]==0) {
          for(int j = 0; j<5; j++) {
            if(blocks[i]!=null && blocks[i].rooms[j]!=null && !stu[q].inClass(blocks[i].rooms[j].id) && blocks[i].rooms[j].addStudent()) {
              stu[q].classes[i] = blocks[i].rooms[j].id;
              break;
            }
          }
        }
      }
    }

    // ** count conflicts and assign classrooms to each student object
    
    for(int i=0; i<stu.length; i++) {
      for(int j = 0; j<5; j++) {
        if(!stu[i].choseThis(stu[i].classes[j])) conflicts++;
        stu[i].classrooms[j] = blocks[j].findInBlock(stu[i].classes[j]);
      }
      students.set(i,stu[i]);
    }
    
  }
  
  
  public int countClass(int x) { // count number of sections of this class over the whole day
    int c = 0;
    for(int i = 0; i<5; i++) {
      if(blocks[i]!=null) {
        for(int j = 0; j<5; j++) {
          if(blocks[i].rooms[j]!=null) {
            if(blocks[i].rooms[j].id==x) c++;
          }
        }
      }
    }
    return c;
  }

  public void print() { // print the "output"
    System.out.println("Here are the class id's for each of the five blocks for each student.");
    System.out.println(students);
    System.out.println("\n" + conflicts + " \"conflicts\" for " + stu.length + " students");
  }
  
}