class Block {
  public Class[] rooms = new Class[5];

  public boolean classInBlock(int x) {
    for(int i = 0; i<5; i++) {
      if(rooms[i]!=null && rooms[i].id==x) return true;
    }
    return false;
  }

  public int findInBlock(int x) {
    for(int i = 0; i<5; i++) {
      if(rooms[i].id==x) return i;
    }
    return -1;
  }

  public int nextAvail() {
    for(int i = 0; i<5; i++) {
      if(rooms[i]==null) return i;
    }
    return -1;
  }

  public void addClass(int x) {
    rooms[nextAvail()] = new Class(x);
  }
}