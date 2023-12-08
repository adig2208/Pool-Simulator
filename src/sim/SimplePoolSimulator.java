package sim;

/**
 * This class represents a SimplePoolSimulator that implements the interface.
 */
public class SimplePoolSimulator implements PoolSimulator {

  private final int width;
  private final int height;
  private final String type;
  private boolean status;
  private Ball ball;

  /**
   * This is a SimplePoolSimulator constructor with width, height and type.
   *
   * @param width  Width of the pool table. Must be greater than 0.
   * @param height Height of the pool table. Must be greater than 0.
   * @param type   Type of ball physics. Included types are "simple" and "friction".
   * @throws IllegalArgumentException if the provided dimensions are
   *                                  invalid or if the type is not supported.
   */
  public SimplePoolSimulator(int width, int height, String type)
          throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width/Height cannot be zero or less than zero.");
    }
    if (type == null || (!type.equalsIgnoreCase("simple") && !type.equalsIgnoreCase("friction"))) {
      throw new IllegalArgumentException("Type parameter is invalid.");
    }
    this.width = width;
    this.height = height;
    this.type = type;
  }

  /**
   * Method to start the simulation of pool table and ball.
   *
   * @param x      x-coordinate of the ball.
   * @param y      y-coordinate of the ball.
   * @param radius radius of the ball.
   * @param speed  speed of the ball.
   * @param dx     x-direction of the ball.
   * @param dy     y-direction of the ball.
   * @throws IllegalArgumentException if the provided arguments are not valid.
   */
  @Override
  public void start(int x, int y, int radius, int speed, double dx, double dy)
          throws IllegalArgumentException {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("X/Y coordinates cannot be less than 0.");
    }
    if (radius < 0 || speed < 0) {
      throw new IllegalArgumentException("Radius/Speed cannot be less than 0.");
    }
    if (dx == 0 && dy == 0) {
      throw new IllegalArgumentException("dx and dy both cannot be 0.");
    }
    if (((x - radius < 0) || (x + radius > width)) || ((y - radius < 0) || (y + radius > height))) {
      throw new IllegalArgumentException("Invalid Ball position.");
    }
    double mag = Math.sqrt((dx * dx) + (dy * dy));
    double vX = (speed * dx);
    vX = vX / mag;
    double vY = (speed * dy);
    vY = vY / mag;
    if (type.equalsIgnoreCase("simple")) {
      ball = new SimpleBall(x, y, radius, speed, vX, vY);
    }
    if (type.equalsIgnoreCase("friction")) {
      ball = new FrictionBall(x, y, radius, speed, vX, vY);
    }
    status = true;
  }

  /**
   * Method to advance the ball.
   */
  @Override
  public void advance() {
    status = false;
    ball.advance(this.width, this.height);
  }

  /**
   * Method to get width of pool table.
   *
   * @return Width of the pool table.
   */
  @Override
  public int getTableWidth() {
    return this.width;
  }

  /**
   * Method to get height of pool table.
   *
   * @return Height of the pool table.
   */
  @Override
  public int getTableHeight() {
    return this.height;
  }

  /**
   * Method to get X-position of ball.
   *
   * @return X-position of ball.
   */
  @Override
  public double getBallPositionX() {
    if (ball == null) {
      return Double.NEGATIVE_INFINITY;
    }
    return ball.getBallPositionX();
  }

  /**
   * Method to get Y-position of ball.
   *
   * @return Y-position of ball.
   */
  @Override
  public double getBallPositionY() {
    if (ball == null) {
      return Double.NEGATIVE_INFINITY;
    }
    return ball.getBallPositionY();
  }

  /**
   * Method to get radius of ball.
   *
   * @return Radius of ball.
   */
  @Override
  public double getBallRadius() {
    if (ball == null) {
      return Double.NEGATIVE_INFINITY;
    }
    return ball.getBallRadius();
  }

  /**
   * Method to get X-velocity of ball.
   *
   * @return X-velocity of ball.
   */
  @Override
  public double getBallVelocityX() {
    if (ball == null) {
      return 0;
    }
    return ball.getBallVelocityX();
  }

  /**
   * Method to get Y-velocity of ball.
   *
   * @return Y-velocity of ball.
   */
  @Override
  public double getBallVelocityY() {
    if (ball == null) {
      return 0;
    }
    return ball.getBallVelocityY();
  }

  /**
   * Method to get speed of ball.
   *
   * @return Speed of ball.
   */
  @Override
  public double getBallSpeed() {
    if (ball == null) {
      return 0;
    }
    return ball.getBallSpeed();
  }

  /**
   * Method to return status of the ball.
   *
   * @return Status of ball.
   */
  @Override
  public String getStatus() {
    if (ball == null) {
      return "Status: Ball not set up";
    }
    if (Double.compare(ball.getBallSpeed(), 0) == 0) {
      return "Status: Ball is stationary";
    } else {
      if (status) {
        return "Status: Simulation started";
      }
      if (Double.compare(ball.getBallPositionX() - ball.getBallRadius(), 0) == 0) {
        return "Status: Ball hit left edge";
      }
      if (Double.compare(ball.getBallPositionX() + ball.getBallRadius(), this.width) == 0) {
        return "Status: Ball hit right edge";
      }
      if (Double.compare(ball.getBallPositionY() - ball.getBallRadius(), 0) == 0) {
        return "Status: Ball hit bottom edge";
      }
      if (Double.compare(ball.getBallPositionY() + ball.getBallRadius(), this.height) == 0) {
        return "Status: Ball hit top edge";
      }
    }
    return null;
  }
}
