class Block { // one block of the day
  public Class[] rooms = new Class[5]; // what classes are in each of the rooms, 0-4

  public boolean classInBlock(int x) { // does this block contain class x?
    for(int i = 0; i<5; i++) {
      if(rooms[i]!=null && rooms[i].id==x) return true;
    }
    return false;
  }

  public int findInBlock(int x) { // find in which room we have class x; -1 of not found
    for(int i = 0; i<5; i++) {
      if(rooms[i].id==x) return i;
    }
    return -1;
  }

  public int nextAvail() { // next available classroom; -1 if none
    for(int i = 0; i<5; i++) {
      if(rooms[i]==null) return i;
    }
    return -1;
  }

  public void addClass(int x) { // add class with id x
    rooms[nextAvail()] = new Class(x);
  }
}