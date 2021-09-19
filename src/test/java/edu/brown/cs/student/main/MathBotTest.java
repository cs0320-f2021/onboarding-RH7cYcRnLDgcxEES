package edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathBotTest {

  @Test
  public void testAddition() {
    MathBot matherator9000 = new MathBot();
    double output = matherator9000.add(10.5, 3);
    assertEquals(13.5, output, 0.01);
  }

  @Test
  public void testLargerNumbers() {
    MathBot matherator9001 = new MathBot();
    double output = matherator9001.add(100000, 200303);
    assertEquals(300303, output, 0.01);
  }

  @Test
  public void testSubtraction() {
    MathBot matherator9002 = new MathBot();
    double output = matherator9002.subtract(18, 17);
    assertEquals(1, output, 0.01);
  }

  // TODO: add more unit tests of your own
  @Test
  public void testNegativeSub() {
    MathBot matherator9003 = new MathBot();
    double output = matherator9003.subtract(17, 18);
    assertEquals(-1, output, 0.01);
    double output2 = matherator9003.subtract(-15, -5);
    assertEquals(-10, output2, 0.01);
    double output3 = matherator9003.subtract(5.5, 10);
    assertEquals(-4.5, output3, 0.01);
  }

  @Test
  public void testDoubleNegSub() {
    MathBot matherator9004 = new MathBot();
    double output = matherator9004.subtract(5.5, -10);
    assertEquals(15.5, output, 0.01);
    double output2 = matherator9004.subtract(-3.75, -10);
    assertEquals(6.25, output2, 0.01);
    double output3 = matherator9004.subtract(-10, -3.75);
    assertEquals(-6.25, output3, 0.01);
  }

}
