import org.junit.Test;

import java.util.Random;

import sim.FrictionBall;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This is a FrictionBallTest class that tests the accurate working of Friction Ball.
 */
public class FrictionBallTest {
  /**
   * Test for valid constructor.
   */
  @Test
  public void testValidFrictionBallConstructor1() {
    try {
      new FrictionBall(80, 80, 10, 100, 12.34, 11.45);
    } catch (IllegalArgumentException e) {
      fail("The constructor should not have thrown an error but it did.");
    }
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFrictionBallNegX() {
    new FrictionBall(-10, 10, 2, 100, 23, 21);
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFrictionBallNegY() {
    new FrictionBall(10, -10, 2, 100, 23, 21);
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFrictionBallNegRad() {
    new FrictionBall(10, 10, -2, 100, 23, 21);
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFrictionBallNegSpeed() {
    new FrictionBall(10, 10, 2, -100, 23, 21);
  }

  /**
   * Test for Invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFrictionBallNeg() {
    new FrictionBall(-10, -10, 2, 100, 23, 21);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidFrictionBallGetX() {
    FrictionBall ball = new FrictionBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(80, ball.getBallPositionX(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidFrictionBallGetY() {
    FrictionBall ball = new FrictionBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(80, ball.getBallPositionY(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidFrictionBallVelX() {
    FrictionBall ball = new FrictionBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(12.45, ball.getBallVelocityX(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidFrictionBallVelY() {
    FrictionBall ball = new FrictionBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(11.22, ball.getBallVelocityY(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidFrictionBallGetRad() {
    FrictionBall ball = new FrictionBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(10, ball.getBallRadius(), 0.0);
  }

  /**
   * Test for getter.
   */
  @Test
  public void testValidFrictionBallGetSpeed() {
    FrictionBall ball = new FrictionBall(80, 80, 10, 100, 12.45, 11.22);
    assertEquals(100, ball.getBallSpeed(), 0.0);
  }

  /**
   * Test for valid constructor.
   */
  @Test
  public void testFuzzFrictionBall() {
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
        new FrictionBall(x, y, radius, speed, vx, vy);
      } catch (Exception e) {
        fail("The constructor should not have thrown an error but it did.");
      }
    }
  }

}