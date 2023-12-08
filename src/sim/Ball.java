package sim;

/**
 * The Ball class is an abstract class that represents a ball that
 * can move on a 2D plane.
 */
public abstract class Ball {
  protected double x;
  protected double y;
  protected double rad;
  protected double speed;
  protected double vX;
  protected double vY;


  /**
   * Method to construct a Ball.
   *
   * @param x     Initial x-coordinate of the ball.
   * @param y     Initial y-coordinate of the ball.
   * @param rad   Radius of the ball.
   * @param speed Initial speed of the ball.
   * @param vX    Initial x-component of the ball's velocity.
   * @param vY    Initial y-component of the ball's velocity.
   * @throws IllegalArgumentException if the provided values are invlaid.
   */
  protected Ball(double x, double y, double rad, double speed, double vX, double vY)
          throws IllegalArgumentException {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("X/Y coordinates cannot be less than 0.");
    }
    if (rad < 0 || speed < 0) {
      throw new IllegalArgumentException("Radius/Speed cannot be less than 0.");
    }
    this.x = x;
    this.y = y;
    this.rad = rad;
    this.speed = speed;
    this.vX = vX;
    this.vY = vY;
  }

  /**
   * Abstract Method to advance the ball.
   *
   * @param width  Width of Pool Table.
   * @param height Height Pool Table.
   */
  protected abstract void advance(double width, double height);

  /**
   * Method to get X-position of ball.
   *
   * @return X-position of ball.
   */
  public double getBallPositionX() {
    return x;
  }

  /**
   * Method to get Y-position of ball.
   *
   * @return Y-position of ball.
   */
  public double getBallPositionY() {
    return y;
  }

  /**
   * Method to get Radius of ball.
   *
   * @return Radius of ball.
   */
  public double getBallRadius() {
    return rad;
  }

  /**
   * Method to get X-velocity of ball.
   *
   * @return X-velocity of ball.
   */
  public double getBallVelocityX() {
    return vX;
  }

  /**
   * Method to get Y-velocity of ball.
   *
   * @return Y-velocity of ball.
   */
  public double getBallVelocityY() {
    return vY;
  }

  /**
   * Method to get Speed of ball.
   *
   * @return Speed of ball.
   */
  public double getBallSpeed() {
    return speed;
  }
}
