import java.io.*;
import java.util.*;

class SeniorSeminar {
  File data = new File("data.csv");
  // String x = scan1.nextLine(); // skip line
  ArrayList<Student> students = new ArrayList<Student>();
  Student[] stu;
  Block[] blocks = new Block[5];

  public SeniorSeminar() {
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

    stu = new Student[students.size()];

    for(int i=0; i<stu.length; i++) {
      stu[i] = students.get(i);
    }

    for(int i=0; i<5; i++) {
      blocks[i] = new Block();
    }
    
  }

  public void sort() {

    // **sort based on preferences**
    
    for(int i = 0; i<5; i++) {
      for(int q=0; q<stu.length; q++) {
        for(int j = 0; j<5; j++) {  
          if(stu[q].choices[j]!=0) {
            if(blocks[i].classInBlock(stu[q].choices[j])) {
              if(blocks[i].rooms[blocks[i].findInBlock(stu[q].choices[j])].addStudent()) {
                stu[q].classes[i] = stu[q].choices[j];
                stu[q].choices[j] = 0;
                break;
              } // close check for space if
            } // close check if class already exists if
            else { // if class does not already exist
              if(blocks[i].nextAvail()!=-1) {
                if(countClass(stu[q].choices[j])<2) {
                  blocks[i].addClass(stu[q].choices[j]);
                  stu[q].classes[i] = stu[q].choices[j];
                  stu[q].choices[j] = 0;
                }
              } // close if space to add a new class
            }
          } // close if choice still unsatisfied
        } // close for each choice
      } // close for each student
    } // close for each block

    // **fill the rest**

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

    for(int i=0; i<stu.length; i++) {
      for(int j = 0; j<5; j++) {
        // stu[i].classrooms[j] = stu[i].classes[j];
        stu[i].classrooms[j] = blocks[j].findInBlock(stu[i].classes[j]);
      }
      students.set(i,stu[i]);
    }
    
  }
  

  public int countClass(int x) {
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

  public void print() {
    System.out.println("Here are the class id's for each of the five blocks for each student.");
    System.out.println(students);
  }
  
}