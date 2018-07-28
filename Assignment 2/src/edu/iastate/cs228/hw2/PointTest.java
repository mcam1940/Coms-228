package edu.iastate.cs228.hw2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.iastate.cs228.hw2.Point;

public class PointTest {
  private Point p1;
  private Point p2;
  private Point copy;
  
  @Before
  public void before() {
    p1 = new Point(1, 2);
    p2 = new Point(3, 4);
    copy = new Point(p1);
  }

  @Test
  public void constructorTest() {
    assertEquals(1, p1.getX());
    assertEquals(2, p1.getY());
    assertEquals(1, copy.getX());
  }
  
  @Test
  public void equalsTest() {
    assertTrue(p1.equals(copy));
    assertFalse(p1.equals(p2));
  }
  
  @Test
  public void compareToTest() {
    assertEquals(-1, p1.compareTo(p2));
    assertEquals(0, p1.compareTo(p1));
    assertEquals(1, p2.compareTo(p1));
  }
  
  @Test
  public void toStringTest() {
    assertEquals("(1, 2)", p1.toString());
  }
}