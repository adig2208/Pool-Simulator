package sim;

/**
 * This is a Simple Ball class that extends Ball class and follows Simple Physics.
 */
public class SimpleBall extends Ball {

  /**
   * Method to construct a FrictionBall.
   *
   * @param x     Initial x-coordinate of the ball.
   * @param y     Initial y-coordinate of the ball.
   * @param rad   Radius of the ball.
   * @param speed Initial speed of the ball.
   * @param vX    Initial x-component of the ball's velocity.
   * @param vY    Initial y-component of the ball's velocity.
   * @throws IllegalArgumentException if the provided values are invlaid.
   */
  public SimpleBall(double x, double y, double rad, double speed, double vX, double vY)
          throws IllegalArgumentException {
    super(x, y, rad, speed, vX, vY);
  }

  /**
   * Abstract Method to advance the ball.
   *
   * @param width  Width of Pool Table.
   * @param height Height Pool Table.
   */
  @Override
  public void advance(double width, double height) {
    if (speed <= 0) {
      return;
    }
    double xMin = 0;
    double yMin = 0;
    double xMax = xMin + width;
    double yMax = yMin + height;
    double vMag = Math.sqrt((vX * vX) + (vY * vY));
    double dx = vX / vMag;
    double dy = vY / vMag;
    double timeRight;
    double timeLeft;
    double timeTop;
    double timeBottom;
    if (dx >= 0) {
      timeRight = (xMax - rad - x) / vX;
    } else {
      timeRight = Double.POSITIVE_INFINITY;
    }
    if (dx < 0) {
      timeLeft = (xMin + rad - x) / vX;
    } else {
      timeLeft = Double.POSITIVE_INFINITY;
    }
    if (dy >= 0) {
      timeTop = (yMax - rad - y) / vY;
    } else {
      timeTop = Double.POSITIVE_INFINITY;
    }
    if (dy < 0) {
      timeBottom = (yMin + rad - y) / vY;
    } else {
      timeBottom = Double.POSITIVE_INFINITY;
    }

    double minimumTime = Math.min(timeRight, Math.min(timeLeft, Math.min(timeBottom, timeTop)));

    x += vX * minimumTime;
    if (Math.round(x + rad - xMax) == 0.0) {
      x = xMax - rad;
    } else if (Math.round(x - rad) == 0.0) {
      x = rad;
    }
    y += vY * minimumTime;
    if (Math.round(y + rad - yMax) == 0.0) {
      y = yMax - rad;
    } else if (Math.round(y - rad) == 0.0) {
      y = rad;
    }
    if (minimumTime == timeBottom || minimumTime == timeTop) {
      dy = -dy;
      vY = -vY;
    }
    if (minimumTime == timeLeft || minimumTime == timeRight) {
      dx = -dx;
      vX = -vX;
    }
    speed = speed - 5.0;
    if (speed <= 0.0) {
      speed = 0.0;
    }
    vMag = Math.sqrt((vX * vX) + (vY * vY));
    vX = speed * (vX / vMag);
    vY = speed * (vY / vMag);
  }
}
