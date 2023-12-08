package sim;

/**
 * This is a Friction Ball class that extends Ball class and follows Newtonian Physics.
 */
public class FrictionBall extends Ball {

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
  public FrictionBall(double x, double y, double rad, double speed, double vX, double vY)
          throws IllegalArgumentException {
    super(x, y, rad, speed, vX, vY);
  }

  /**
   * Method to calculate minimum time.
   *
   * @param a a parameter of a quadratic equation.
   * @param b b parameter of a quadratic equation.
   * @param c c parameter of a quadratic equation.
   * @return minimum time calculated.
   */
  private static double calcTime(double a, double b, double c) {
    double delta = Math.pow(b, 2) - 4 * a * c;
    double rootDelta = Math.sqrt(delta);
    double time1 = (2 * c / (-b + rootDelta));
    double time2 = (2 * c / (-b - rootDelta));
    if (time1 >= 0 && time2 >= 0) {
      return Math.min(time1, time2);
    } else if (time1 >= 0) {
      return time1;
    } else if (time2 >= 0) {
      return time2;
    } else {
      return Double.POSITIVE_INFINITY;
    }
  }

  /**
   * Abstract Method to advance the ball.
   *
   * @param width  Width of Pool Table.
   * @param height Height Pool Table.
   */
  @Override
  public void advance(double width, double height) {
    if (speed < 0) {
      return;
    }
    double xMin = 0;
    double yMin = 0;
    double xMax = xMin + width;
    double yMax = yMin + height;
    double friction = 0.1;
    double vMag = Math.sqrt((vX * vX) + (vY * vY));
    double dx = vX / vMag;
    double dy = vY / vMag;
    double timeRight;
    double timeLeft;
    double timeTop;
    double timeBottom;
    double accX = 9.81 * friction * dx;
    double accY = 9.81 * friction * dy;

    if (vX >= 0) {
      double a = accX / 2;
      double c = xMax - rad - x;
      double b = -(vX);
      timeRight = calcTime(a, b, c);
    } else {
      timeRight = Double.POSITIVE_INFINITY;
    }
    if (vX < 0) {
      double a = -(9.81 * friction * dx) / 2;
      double c = x - xMin - rad;
      double b = (vX);
      timeLeft = calcTime(a, b, c);
    } else {
      timeLeft = Double.POSITIVE_INFINITY;
    }
    if (vY >= 0) {
      double a = (accY) / 2;
      double c = yMax - rad - y;
      double b = -(vY);
      timeTop = calcTime(a, b, c);
    } else {
      timeTop = Double.POSITIVE_INFINITY;
    }
    if (vY < 0) {
      double a = -(accY) / 2;
      double c = y - yMin - rad;
      double b = (vY);
      timeBottom = calcTime(a, b, c);
    } else {
      timeBottom = Double.POSITIVE_INFINITY;
    }
    double impactTime = Math.min(timeRight, Math.min(timeLeft, Math.min(timeBottom, timeTop)));
    double stopTime;
    if (speed > 0) {
      stopTime = speed / (friction * 9.81);
    } else {
      stopTime = Double.POSITIVE_INFINITY;
    }
    double minimumTime = Math.min(stopTime, impactTime);
    x += (vX) * minimumTime - ((accX / 2) * (minimumTime * minimumTime));
    if (minimumTime == timeRight) {
      x = xMax - rad;
    }
    if (minimumTime == timeLeft) {
      x = xMin + rad;
    }
    y += (vY) * minimumTime - ((accY / 2) * (minimumTime * minimumTime));
    if (minimumTime == timeBottom) {
      y = yMin + rad;
    }
    if (minimumTime == timeTop) {
      y = yMax - rad;
    }

    if (minimumTime == timeBottom || minimumTime == timeTop) {
      dy = -dy;
      vY = -vY;
    }
    if (minimumTime == timeLeft || minimumTime == timeRight) {
      vX = -vX;
      dx = -dx;
    }
    double acc = (-9.81 * friction);
    speed += acc * minimumTime;
    vX = speed * dx;
    vY = speed * dy;
  }
}
