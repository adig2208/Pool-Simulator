import org.junit.Test;

import java.util.Random;

import sim.SimpleBall;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This is a SimpleBallTest class that tests the accurate working of Simple Ball.
 */
public class SimpleBallTest {

  /**
   * Test for valid constructor.
   */
  @Test
  public void testValidSimpleBallConstructor1() {
    try {
      new SimpleBall(80, 80, 10, 100, 12.34, 11.45);
    } catch (IllegalArgumentException e) {
      fail("The constructor should not have thrown an error but it did.");
    }
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSimpleBallNegX() {
    new SimpleBall(-10, 10, 2, 100, 23, 21);
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSimpleBallNegY() {
    new SimpleBall(10, -10, 2, 100, 23, 21);
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSimpleBallNegRad() {
    new SimpleBall(10, 10, -2, 100, 23, 21);
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSimpleBallNegSpeed() {
    new SimpleBall(10, 10, 2, -100, 23, 21);
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSimpleBallNeg() {
    new SimpleBall(-10, -10, 2, 100, 23, 21);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidSimpleBallGetX() {
    SimpleBall ball = new SimpleBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(80, ball.getBallPositionX(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidSimpleBallGetY() {
    SimpleBall ball = new SimpleBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(80, ball.getBallPositionY(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidSimpleBallVelX() {
    SimpleBall ball = new SimpleBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(12.45, ball.getBallVelocityX(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidSimpleBallVelY() {
    SimpleBall ball = new SimpleBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(11.22, ball.getBallVelocityY(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidSimpleBallGetRad() {
    SimpleBall ball = new SimpleBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(10, ball.getBallRadius(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidSimpleBallGetSpeed() {
    SimpleBall ball = new SimpleBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(100, ball.getBallSpeed(), 0.0);
  }

  /**
   * Test for valid constructor.
   */
  @Test
  public void testFuzzSimpleBall() {
    Random r = new Random();
    for (int i = 0; i < 10000; i++) {
      try {
        int x = r.nextInt(80) + 1;
        int y = r.nextInt(80) + 1;
        int radius = r.nextInt(Math.min(x, Math.min(y, Math.min(100 - x, 100 - y))) + 1);
        int speed = r.nextInt(500) + 1;
        double vx;
        double vy;
        do {
          vx = r.nextDouble() + 1;
          vy = r.nextDouble() + 1;
        }
        while (vx == 0 && vy == 0);
        new SimpleBall(x, y, radius, speed, vx, vy);
      } catch (Exception e) {
        fail("The constructor should not have thrown an error but it did.");
      }
    }
  }

  /**
   * Test for advance.
   */
  @Test
  public void testAdvanceSimpleBottom() {
    SimpleBall ball = new SimpleBall(80, 80, 10, 100, 1, 2);
    ball.advance(100, 100);
    assertEquals(85.0, ball.getBallPositionX(), 0.01);
    assertEquals(90.0, ball.getBallPositionY(), 0.01);
    assertEquals(42.4852, ball.getBallVelocityX(), 0.01);
    assertEquals(-84.9705, ball.getBallVelocityY(), 0.01);
    ball.advance(100, 100);
    assertEquals(90.0, ball.getBallPositionX(), 0.01);
    assertEquals(80.0, ball.getBallPositionY(), 0.01);
    assertEquals(-40.2492, ball.getBallVelocityX(), 0.01);
    assertEquals(-80.4984, ball.getBallVelocityY(), 0.01);
    ball.advance(100, 100);
    assertEquals(55.0, ball.getBallPositionX(), 0.01);
    assertEquals(10.0, ball.getBallPositionY(), 0.01);
    assertEquals(-38.0131, ball.getBallVelocityX(), 0.01);
    assertEquals(76.0263, ball.getBallVelocityY(), 0.01);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    assertEquals(45.0, ball.getBallPositionX(), 0.01);
    assertEquals(10.0, ball.getBallPositionY(), 0.01);
    assertEquals(31.3049, ball.getBallVelocityX(), 0.01);
    assertEquals(62.6099, ball.getBallVelocityY(), 0.01);
    assertEquals(70.0, ball.getBallSpeed(), 0.01);

  }

  /**
   * Test advance for hitting all four edges.
   */
  @Test
  public void testAdvanceSimpleAllFour() {
    SimpleBall ball = new SimpleBall(80, 80, 10, 100, 44.72135954999579, 89.44271909999158);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    assertEquals(55.0, ball.getBallPositionX(), 0.01);
    assertEquals(10.0, ball.getBallPositionY(), 0.01);
    assertEquals(-38.0131, ball.getBallVelocityX(), 0.01);
    assertEquals(76.0263, ball.getBallVelocityY(), 0.01);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    assertEquals(45.0, ball.getBallPositionX(), 0.01);
    assertEquals(10.0, ball.getBallPositionY(), 0.01);
    assertEquals(31.3049, ball.getBallVelocityX(), 0.01);
    assertEquals(62.6099, ball.getBallVelocityY(), 0.01);
    assertEquals(70.0, ball.getBallSpeed(), 0.01);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    ball.advance(100, 100);
    assertEquals(10.0, ball.getBallPositionX(), 0.01);
    assertEquals(80.0, ball.getBallPositionY(), 0.01);
    assertEquals(6.7082, ball.getBallVelocityX(), 0.01);
    assertEquals(-13.4164, ball.getBallVelocityY(), 0.01);
    assertEquals(15.0, ball.getBallSpeed(), 0.01);
  }

  /**
   * Test for dx zero.
   */
  @Test
  public void testAdvanceSimpleDxZero() {
    SimpleBall ball = new SimpleBall(80, 80, 10, 100, 1, 0);
    ball.advance(400, 400);
    assertEquals(390.0, ball.getBallPositionX(), 0.01);
    assertEquals(80.0, ball.getBallPositionY(), 0.01);
    assertEquals(-95.0, ball.getBallVelocityX(), 0.01);
    assertEquals(0.0, ball.getBallVelocityY(), 0.01);
    ball.advance(400, 400);
    assertEquals(10.0, ball.getBallPositionX(), 0.01);
    assertEquals(80.0, ball.getBallPositionY(), 0.01);
    assertEquals(90.0, ball.getBallVelocityX(), 0.01);
    assertEquals(0.0, ball.getBallVelocityY(), 0.01);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    assertEquals(10.0, ball.getBallPositionX(), 0.01);
    assertEquals(80.0, ball.getBallPositionY(), 0.01);
    assertEquals(50.0, ball.getBallVelocityX(), 0.01);
    assertEquals(0.0, ball.getBallVelocityY(), 0.01);
    assertEquals(50.0, ball.getBallSpeed(), 0.01);
  }

  /**
   * Test for dy zero.
   */
  @Test
  public void testAdvanceSimpleDyZero() {
    SimpleBall ball = new SimpleBall(80, 80, 10, 100, 0, 1);
    ball.advance(400, 400);
    assertEquals(80.0, ball.getBallPositionX(), 0.01);
    assertEquals(390.0, ball.getBallPositionY(), 0.01);
    assertEquals(0.0, ball.getBallVelocityX(), 0.01);
    assertEquals(-95.0, ball.getBallVelocityY(), 0.01);
    ball.advance(400, 400);
    assertEquals(80.0, ball.getBallPositionX(), 0.01);
    assertEquals(10.0, ball.getBallPositionY(), 0.01);
    assertEquals(0.0, ball.getBallVelocityX(), 0.01);
    assertEquals(90.0, ball.getBallVelocityY(), 0.01);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    ball.advance(400, 400);
    assertEquals(80.0, ball.getBallPositionX(), 0.01);
    assertEquals(10.0, ball.getBallPositionY(), 0.01);
    assertEquals(0.0, ball.getBallVelocityX(), 0.01);
    assertEquals(50.0, ball.getBallVelocityY(), 0.01);
    assertEquals(50.0, ball.getBallSpeed(), 0.01);
  }

}