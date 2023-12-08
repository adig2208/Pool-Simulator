import org.junit.Test;

import java.util.Random;

import sim.SimplePoolSimulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * This is a SimplePoolSimulatorTest class that tests the accurate working of pool simulator.
 */
public class SimplePoolSimulatorTest {

  /**
   * Test for valid constructor.
   */
  @Test
  public void testValidConstructor1() {
    try {
      new SimplePoolSimulator(100, 100, "simple");
    } catch (IllegalArgumentException e) {
      fail("The constructor should not have thrown an error but it did.");
    }
  }

  /**
   * Test for valid constructor.
   */
  @Test
  public void testValidConstructor2() {
    try {
      new SimplePoolSimulator(100, 100, "friction");
    } catch (IllegalArgumentException e) {
      fail("The constructor should not have thrown an error but it did.");
    }
  }

  /**
   * Test for zero value constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroValueConstructor1() {
    new SimplePoolSimulator(0, 100, "simple");
  }

  /**
   * Test for zero value constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroValueConstructor2() {
    new SimplePoolSimulator(100, 0, "simple");
  }

  /**
   * Test for zero value constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroValueConstructor3() {
    new SimplePoolSimulator(0, 0, "friction");
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHeight() {
    new SimplePoolSimulator(100, -20, "simple");
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWidth() {
    new SimplePoolSimulator(-40, 100, "friction");
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWidthHeight() {
    new SimplePoolSimulator(-40, -100, "friction");
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidType() {
    new SimplePoolSimulator(100, 100, "abcd");
  }

  /**
   * Test for null value constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullType() {
    new SimplePoolSimulator(100, 100, null);
  }

  /**
   * Test for get width.
   */
  @Test
  public void testGetWidth() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    assertEquals(100, pool.getTableWidth(), 0.0);
  }

  /**
   * Test for get height.
   */
  @Test
  public void testGetHeight() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    assertEquals(100, pool.getTableHeight(), 0.0);
  }

  /**
   * Test for valid start.
   */
  @Test
  public void testValidStart() {
    try {
      SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
      pool.start(10, 10, 5, 80, 2, 3);
    } catch (IllegalArgumentException e) {
      fail("The method should not have thrown an error but it did.");
    }
  }

  /**
   * Fuzzy Test for valid start with type simple.
   */
  @Test
  public void fuzzTestValidStartSimple() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    Random r = new Random();
    for (int i = 0; i < 10000; i++) {
      try {
        int x = r.nextInt(80) + 1;
        int y = r.nextInt(80) + 1;
        int radius = r.nextInt(Math.min(x, Math.min(y, Math.min(100 - x, 100 - y))) + 1);
        int speed = r.nextInt(500) + 1;
        double dx;
        double dy;
        do {
          dx = r.nextDouble() + 1;
          dy = r.nextDouble() + 1;
        }
        while (dx == 0 && dy == 0);
        pool.start(x, y, radius, speed, dx, dy);
      } catch (Exception e) {
        fail("failed on test" + i + "with exception" + e);
      }
    }
  }

  /**
   * Fuzzy Test for valid start with type friction.
   */
  @Test
  public void fuzzTestValidStartFriction() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    Random r = new Random();
    for (int i = 0; i < 10000; i++) {
      try {
        int x = r.nextInt(80) + 1;
        int y = r.nextInt(80) + 1;
        int radius = r.nextInt(Math.min(x, Math.min(y, Math.min(100 - x, 100 - y))) + 1);
        int speed = r.nextInt(500) + 1;
        double dx;
        double dy;
        do {
          dx = r.nextDouble() + 1;
          dy = r.nextDouble() + 1;
        }
        while (dx == 0 && dy == 0);
        pool.start(x, y, radius, speed, dx, dy);
      } catch (Exception e) {
        fail("failed on test" + i + "with exception" + e);
      }
    }
  }

  /**
   * Invalid test for negative X.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartNegativeX() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    pool.start(-2, 5, 1, 50, 1, 2);
  }

  /**
   * Invalid test for negative Y.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartNegativeY() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(4, -5, 1, 50, 1, 2);
  }

  /**
   * Invalid test for negative Speed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartNegativeSpeed() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    pool.start(4, 5, 1, -50, 1, 2);
  }

  /**
   * Invalid test for zero dx dy.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartZeroDxDy() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(4, 5, 1, 50, 0, 0);
  }

  /**
   * Invalid test for negative radius.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartNegativeRadius() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    pool.start(4, 5, -2, 50, 1, 2);
  }

  /**
   * Invalid test for negative radius.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartNegRad() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(4, 5, 6, 500, 5, 7);
  }


  /**
   * Invalid test for width bound.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartWidthBound() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(4, 5, 100, 500, 5, 7);
  }

  /**
   * Invalid test for height bound.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStartHeightBound() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(40, 5, 80, 500, 5, 7);
  }

  /**
   * Test for negative dx dy.
   */
  @Test
  public void testStartNegativeDxDy() {
    try {
      SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
      pool.start(30, 50, 10, 500, -5, -7);
    } catch (Exception e) {
      fail("The constructor should not have thrown an error but it did.");
    }

  }

  /**
   * Test for start before Initializing ball.
   */
  @Test
  public void testStartBeforeInitial1() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    assertEquals(Double.NEGATIVE_INFINITY, pool.getBallPositionX(), 0.0);
  }

  /**
   * Test for start before Initializing ball.
   */
  @Test
  public void testStartBeforeInitial2() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    assertEquals(Double.NEGATIVE_INFINITY, pool.getBallPositionY(), 0.0);
  }

  /**
   * Test for start before Initializing ball.
   */
  @Test
  public void testStartBeforeInitial3() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    assertEquals(Double.NEGATIVE_INFINITY, pool.getBallRadius(), 0.0);
  }

  /**
   * Test for start before Initializing ball.
   */
  @Test
  public void testStartBeforeInitial4() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    assertEquals(0, pool.getBallVelocityX(), 0.0);
  }

  /**
   * Test for start before Initializing ball.
   */
  @Test
  public void testStartBeforeInitial5() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    assertEquals(0, pool.getBallVelocityY(), 0.0);
  }

  /**
   * Test for start before Initializing ball.
   */
  @Test
  public void testStartBeforeInitial6() {

    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    assertEquals(0, pool.getBallSpeed(), 0.0);
  }

  /**
   * Test for edge touch.
   */
  @Test
  public void testStartEdge() {
    try {
      SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
      pool.start(95, 95, 5, 200, 3, 4);
    } catch (Exception e) {
      fail("The constructor should not have thrown an error but it did.");
    }

  }

  /**
   * Test for edge touch.
   */
  @Test
  public void testStartRightEdge() {
    try {
      SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
      pool.start(95, 80, 5, 200, 3, 4);
    } catch (Exception e) {
      fail("The constructor should not have thrown an error but it did.");
    }

  }

  /**
   * Test for edge touch.
   */
  @Test
  public void testStartEdgeTop() {
    try {
      SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
      pool.start(80, 95, 5, 200, 3, 4);
    } catch (Exception e) {
      fail("The constructor should not have thrown an error but it did.");
    }

  }

  /**
   * Test for edge touch.
   */
  @Test
  public void testStartEdgeLeft() {
    try {
      SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
      pool.start(5, 95, 5, 200, 3, 4);
    } catch (Exception e) {
      fail("The constructor should not have thrown an error but it did.");
    }

  }

  /**
   * Test for edge touch.
   */
  @Test
  public void testStartEdgeBottom() {
    try {
      SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
      pool.start(80, 5, 5, 200, 3, 4);
    } catch (Exception e) {
      fail("The constructor should not have thrown an error but it did.");
    }
  }

  /**
   * Test for getter.
   */
  @Test
  public void testGetterPosX() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(10, 20, 5, 200, 4, 5);
    assertEquals(10, pool.getBallPositionX(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testGetterPosY() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(10, 20, 5, 200, 4, 5);
    assertEquals(20, pool.getBallPositionY(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testGetterRadius() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(10, 20, 5, 200, 4, 5);
    assertEquals(5, pool.getBallRadius(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testGetterSpeed() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(10, 20, 5, 200, 4, 5);
    assertEquals(200, pool.getBallSpeed(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testGetterVelX() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(10, 20, 5, 200, 4, 5);
    assertEquals(124.93900951088486, pool.getBallVelocityX(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testGetterVelY() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(10, 20, 5, 200, 4, 5);
    assertEquals(156.17376188860607, pool.getBallVelocityY(), 0.0);
  }

  /**
   * Test advance for hitting left edge.
   */
  @Test
  public void testAdvanceSimpleLeft() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(10.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(33.54101966249685, pool.getBallVelocityX(), 0.01);
    assertEquals(-67.0820393249937, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit left edge", pool.getStatus());
  }

  /**
   * Test advance for hitting right edge.
   */
  @Test
  public void testAdvanceSimpleRight() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    pool.advance();
    assertEquals(90.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(-40.2492, pool.getBallVelocityX(), 0.01);
    assertEquals(-80.4984, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(90.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(-26.8328, pool.getBallVelocityX(), 0.01);
    assertEquals(-53.6656, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", pool.getStatus());
  }

  /**
   * Test advance for hitting top edge.
   */
  @Test
  public void testAdvanceSimpleTop() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    assertEquals(85.0, pool.getBallPositionX(), 0.01);
    assertEquals(90.0, pool.getBallPositionY(), 0.01);
    assertEquals(42.4852, pool.getBallVelocityX(), 0.01);
    assertEquals(-84.9705, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit top edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(15.0, pool.getBallPositionX(), 0.01);
    assertEquals(90.0, pool.getBallPositionY(), 0.01);
    assertEquals(-22.3606, pool.getBallVelocityX(), 0.01);
    assertEquals(-44.7213, pool.getBallVelocityY(), 0.01);
    assertEquals(50.0, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit top edge", pool.getStatus());
  }

  /**
   * Test advance for hitting bottom edge.
   */
  @Test
  public void testAdvanceSimpleBottom() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    assertEquals(85.0, pool.getBallPositionX(), 0.01);
    assertEquals(90.0, pool.getBallPositionY(), 0.01);
    assertEquals(42.4852, pool.getBallVelocityX(), 0.01);
    assertEquals(-84.9705, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit top edge", pool.getStatus());
    pool.advance();
    assertEquals(90.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(-40.2492, pool.getBallVelocityX(), 0.01);
    assertEquals(-80.4984, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", pool.getStatus());
    pool.advance();
    assertEquals(55.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(-38.0131, pool.getBallVelocityX(), 0.01);
    assertEquals(76.0263, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(45.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(31.3049, pool.getBallVelocityX(), 0.01);
    assertEquals(62.6099, pool.getBallVelocityY(), 0.01);
    assertEquals(70.0, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
  }

  /**
   * Test advance for hitting all four edges.
   */
  @Test
  public void testAdvanceSimpleAllFour() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(55.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(-38.0131, pool.getBallVelocityX(), 0.01);
    assertEquals(76.0263, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(45.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(31.3049, pool.getBallVelocityX(), 0.01);
    assertEquals(62.6099, pool.getBallVelocityY(), 0.01);
    assertEquals(70.0, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(10.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(6.7082, pool.getBallVelocityX(), 0.01);
    assertEquals(-13.4164, pool.getBallVelocityY(), 0.01);
    assertEquals(15.0, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit left edge", pool.getStatus());
  }

  /**
   * Test advance for ball becomes stationary.
   */
  @Test
  public void testAdvanceSimpleStationary() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    pool.start(80, 80, 10, 100, 1, 2);
    for (int i = 0; i < 20; i++) {
      pool.advance();
    }
    assertEquals("Status: Ball is stationary", pool.getStatus());
  }


  /**
   * Test advance for corners.
   */
  @Test
  public void testAdvanceSimpleCorner() {
    SimplePoolSimulator pool = new SimplePoolSimulator(200, 200, "simple");
    pool.start(100, 100, 10, 100, 0.707, 0.707);
    pool.advance();
    assertEquals(190.0, pool.getBallPositionX(), 0.01);
    assertEquals(190.0, pool.getBallPositionY(), 0.01);
    assertEquals(-67.1751, pool.getBallVelocityX(), 0.01);
    assertEquals(-67.1751, pool.getBallVelocityY(), 0.01);
    assertEquals(95.0, pool.getBallSpeed(), 0.01);
    pool.advance();
    assertEquals(10.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(63.6396, pool.getBallVelocityX(), 0.01);
    assertEquals(63.6396, pool.getBallVelocityY(), 0.01);
    assertEquals(90.0, pool.getBallSpeed(), 0.01);
  }

  /**
   * Test advance for ball not set up.
   */
  @Test
  public void testAdvanceSimpleNotSet() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    assertEquals("Status: Ball not set up", pool.getStatus());
    pool.start(80, 80, 10, 100, 1, 2);
    assertEquals("Status: Simulation started", pool.getStatus());
  }

  /**
   * Test advance for random values.
   */
  @Test
  public void testAdvanceFuzzSimple() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400, 400, "simple");
    pool.start(80, 80, 10, 500, 1, 2);
    for (int i = 0; i < 50; i++) {
      pool.advance();
    }
    assertEquals(390.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(-111.8033, pool.getBallVelocityX(), 0.01);
    assertEquals(-223.6067, pool.getBallVelocityY(), 0.01);
    assertEquals(250.0, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit right edge", pool.getStatus());
  }

  /**
   * Test for dx zero.
   */
  @Test
  public void testAdvanceSimpleDxZero() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400, 400, "simple");
    pool.start(80, 80, 10, 100, 1, 0);
    pool.advance();
    assertEquals(390.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(-95.0, pool.getBallVelocityX(), 0.01);
    assertEquals(0.0, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", pool.getStatus());
    pool.advance();
    assertEquals(10.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(90.0, pool.getBallVelocityX(), 0.01);
    assertEquals(0.0, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit left edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(10.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(50.0, pool.getBallVelocityX(), 0.01);
    assertEquals(0.0, pool.getBallVelocityY(), 0.01);
    assertEquals(50.0, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit left edge", pool.getStatus());
  }

  /**
   * Test for dy zero.
   */
  @Test
  public void testAdvanceSimpleDyZero() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400, 400, "simple");
    pool.start(80, 80, 10, 100, 0, 1);
    pool.advance();
    assertEquals(80.0, pool.getBallPositionX(), 0.01);
    assertEquals(390.0, pool.getBallPositionY(), 0.01);
    assertEquals(0.0, pool.getBallVelocityX(), 0.01);
    assertEquals(-95.0, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit top edge", pool.getStatus());
    pool.advance();
    assertEquals(80.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(0.0, pool.getBallVelocityX(), 0.01);
    assertEquals(90.0, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(80.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(0.0, pool.getBallVelocityX(), 0.01);
    assertEquals(50.0, pool.getBallVelocityY(), 0.01);
    assertEquals(50.0, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
  }

  /**
   * Test advance for hitting left edge.
   */
  @Test
  public void testAdvanceFrictionLeft() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(10.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(43.8295, pool.getBallVelocityX(), 0.01);
    assertEquals(-87.6591, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit left edge", pool.getStatus());
  }

  /**
   * Test advance for hitting right edge.
   */
  @Test
  public void testAdvanceFrictionRight() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    pool.advance();
    assertEquals(90.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(-44.6231, pool.getBallVelocityX(), 0.01);
    assertEquals(-89.2463, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(90.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(-43.0213, pool.getBallVelocityX(), 0.01);
    assertEquals(-86.0426, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", pool.getStatus());
  }

  /**
   * Test advance for hitting top edge.
   */
  @Test
  public void testAdvanceFrictionTop() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    assertEquals(85.0, pool.getBallPositionX(), 0.01);
    assertEquals(90.0, pool.getBallPositionY(), 0.01);
    assertEquals(44.6722, pool.getBallVelocityX(), 0.01);
    assertEquals(-89.3445, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit top edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(15.0, pool.getBallPositionX(), 0.01);
    assertEquals(90.0, pool.getBallPositionY(), 0.01);
    assertEquals(-42.2496, pool.getBallVelocityX(), 0.01);
    assertEquals(-84.4992, pool.getBallVelocityY(), 0.01);
    assertEquals(94.4729, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit top edge", pool.getStatus());
  }

  /**
   * Test advance for hitting all four edges.
   */
  @Test
  public void testAdvanceFrictionBottom() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    assertEquals(85.0, pool.getBallPositionX(), 0.01);
    assertEquals(90.0, pool.getBallPositionY(), 0.01);
    assertEquals(44.6722, pool.getBallVelocityX(), 0.01);
    assertEquals(-89.3445, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit top edge", pool.getStatus());
    pool.advance();
    assertEquals(90.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(-44.6231, pool.getBallVelocityX(), 0.01);
    assertEquals(-89.2463, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", pool.getStatus());
    pool.advance();
    assertEquals(55.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(-44.2777, pool.getBallVelocityX(), 0.01);
    assertEquals(88.5554, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(45.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(43.4778, pool.getBallVelocityX(), 0.01);
    assertEquals(86.9556, pool.getBallVelocityY(), 0.01);
    assertEquals(97.2193, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
  }

  /**
   * Test advance for hitting all four edges.
   */
  @Test
  public void testAdvanceFrictionAllFour() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(55.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(-44.2777, pool.getBallVelocityX(), 0.01);
    assertEquals(88.5554, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(45.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(43.4778, pool.getBallVelocityX(), 0.01);
    assertEquals(86.9556, pool.getBallVelocityY(), 0.01);
    assertEquals(97.2193, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(10.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(40.5000, pool.getBallVelocityX(), 0.01);
    assertEquals(-81.0000, pool.getBallVelocityY(), 0.01);
    assertEquals(90.5608, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit left edge", pool.getStatus());
  }

  /**
   * Test advance for ball becomes stationary.
   */
  @Test
  public void testAdvanceFrictionStationary() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    pool.start(80, 80, 10, 100, 1, 2);
    for (int i = 1; i < 87; i++) {
      pool.advance();
    }
    pool.advance();
    assertEquals("Status: Ball is stationary", pool.getStatus());
  }


  /**
   * Test advance for corners.
   */
  @Test
  public void testAdvanceFrictionCorner() {
    SimplePoolSimulator pool = new SimplePoolSimulator(200, 200, "friction");
    pool.start(100, 100, 10, 100, 0.707, 0.707);
    pool.advance();
    assertEquals(190.0, pool.getBallPositionX(), 0.01);
    assertEquals(190.0, pool.getBallPositionY(), 0.01);
    assertEquals(-69.8221, pool.getBallVelocityX(), 0.01);
    assertEquals(-69.8221, pool.getBallVelocityY(), 0.01);
    assertEquals(98.7434, pool.getBallSpeed(), 0.01);
    pool.advance();
    assertEquals(10.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(68.0104, pool.getBallVelocityX(), 0.01);
    assertEquals(68.0104, pool.getBallVelocityY(), 0.01);
    assertEquals(96.1812, pool.getBallSpeed(), 0.01);
  }

  /**
   * Test advance for ball not set up.
   */
  @Test
  public void testAdvanceFrictionNotSet() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "friction");
    assertEquals("Status: Ball not set up", pool.getStatus());
    pool.start(80, 80, 10, 100, 1, 2);
    assertEquals("Status: Simulation started", pool.getStatus());
  }

  /**
   * Test advance for random values.
   */
  @Test
  public void testAdvanceFuzzFriction() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400, 400, "friction");
    pool.start(80, 80, 10, 500, 1, 2);
    for (int i = 0; i < 50; i++) {
      pool.advance();
    }
    assertEquals(390.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(-210.6969, pool.getBallVelocityX(), 0.01);
    assertEquals(-421.3938, pool.getBallVelocityY(), 0.01);
    assertEquals(471.1326, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit right edge", pool.getStatus());
  }

  /**
   * Test for dx zero.
   */
  @Test
  public void testAdvanceFrictionDxZero() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400, 400, "friction");
    pool.start(80, 80, 10, 100, 1, 0);
    pool.advance();
    assertEquals(390.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(-96.9111, pool.getBallVelocityX(), 0.01);
    assertEquals(0.0, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", pool.getStatus());
    pool.advance();
    assertEquals(10.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(92.9850, pool.getBallVelocityX(), 0.01);
    assertEquals(0.0, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit left edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(10.0, pool.getBallPositionX(), 0.01);
    assertEquals(80.0, pool.getBallPositionY(), 0.01);
    assertEquals(51.7855, pool.getBallVelocityX(), 0.01);
    assertEquals(0.0, pool.getBallVelocityY(), 0.01);
    assertEquals(51.7855, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit left edge", pool.getStatus());
  }

  /**
   * Test for dy zero.
   */
  @Test
  public void testAdvanceFrictionDyZero() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400, 400, "friction");
    pool.start(80, 80, 10, 100, 0, 1);
    pool.advance();
    assertEquals(80.0, pool.getBallPositionX(), 0.01);
    assertEquals(390.0, pool.getBallPositionY(), 0.01);
    assertEquals(0.0, pool.getBallVelocityX(), 0.01);
    assertEquals(-96.9111, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit top edge", pool.getStatus());
    pool.advance();
    assertEquals(80.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(0.0, pool.getBallVelocityX(), 0.01);
    assertEquals(92.9850, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    pool.advance();
    assertEquals(80.0, pool.getBallPositionX(), 0.01);
    assertEquals(10.0, pool.getBallPositionY(), 0.01);
    assertEquals(0.0, pool.getBallVelocityX(), 0.01);
    assertEquals(51.7855, pool.getBallVelocityY(), 0.01);
    assertEquals(51.7855, pool.getBallSpeed(), 0.01);
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
  }

  /**
   * Test to start after advance.
   */
  @Test
  public void testStartAfterAdvance() {
    SimplePoolSimulator pool = new SimplePoolSimulator(100, 100, "simple");
    pool.start(80, 80, 10, 100, 1, 2);
    pool.advance();
    pool.advance();
    pool.start(75, 75, 10, 100, 1, 2);
    assertEquals("Status: Simulation started", pool.getStatus());
  }
}

