package ru.dimas.education.generic;

import java.util.ArrayList;
import java.util.List;

public class WildcardTest {
  
  public WildcardTest() {
  }
  
  
  class Fruit {
  }
  
  class Apple extends Fruit {
  }
  class Jonatan extends Apple {
  }
  class Orange extends Fruit {
  }
  
  class Box<T> {
    T val;
    
    public void put(T val) {
      this.val = val;
    }
    
    public T get() {
      return val;
    }
  }
  
  
  private Number unboxin(Box<? extends Number> box) {
    return box.get();
  }
  
  private void fillList(List<? super Apple> list) {
    
    list.add(new Apple());
    list.add(new Jonatan());
    
  }
  
  private void fillsuperNumberList(List<? super Number> nlist) {
    Double d = 2.05;
    Long l = (long) 100500;
    nlist.add(d);
    nlist.add(l);
  }
  
  private void fillNumberList(List<Number> nlist) {
    Double d = 2.05;
    Long l = (long) 100500;
    nlist.add(d);
    nlist.add(l);
  }
  
  private void listParty() {
    List<Integer> iList = new ArrayList<Integer>();
    List<Number> numList = new ArrayList<Number>();
    numList.add(Double.valueOf(1.0));
    List<? super Integer> superInt = iList;
    
  }
  
  
  public static void main(String[] args) {
    WildcardTest t = new WildcardTest();
    List<? super Integer> iList;
    List<Number> numList = new ArrayList<Number>();
    numList.add(Double.valueOf(5.5));
    iList = numList;
    for (Object i : iList) {
    }
  }
}
