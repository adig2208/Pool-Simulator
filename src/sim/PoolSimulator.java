package sim;

/**
 * This interface represents the operations that a pool simulator should perform.
 */
public interface PoolSimulator {

  /**
   * Method to initialize start parameter.
   *
   * @param x      x-coordinate of the ball.
   * @param y      y-coordinate of the ball.
   * @param radius radius of the ball.
   * @param speed  speed of the ball.
   * @param dx     x-direction of the ball.
   * @param dy     y-direction of the ball.
   * @throws IllegalArgumentException if the provided arguments are not valid.
   */
  void start(int x, int y, int radius, int speed, double dx, double dy)
          throws IllegalArgumentException;

  /**
   * Method to advance the ball.
   */
  void advance();

  /**
   * Method to get width of pool table.
   *
   * @return Width of the pool table.
   */
  int getTableWidth();

  /**
   * Method to get height of pool table.
   *
   * @return Height of the pool table.
   */
  int getTableHeight();

  /**
   * Method to get X-position of ball.
   *
   * @return X-position of ball.
   */
  double getBallPositionX();

  /**
   * Method to get Y-position of ball.
   *
   * @return Y-position of ball.
   */
  double getBallPositionY();

  /**
   * Method to get radius of ball.
   *
   * @return Radius of ball.
   */
  double getBallRadius();

  /**
   * Method to get X-velocity of ball.
   *
   * @return X-velocity of ball.
   */
  double getBallVelocityX();

  /**
   * Method to get Y-velocity of ball.
   *
   * @return Y-velocity of ball.
   */
  double getBallVelocityY();

  /**
   * Method to get speed of ball.
   *
   * @return Speed of ball.
   */
  double getBallSpeed();

  /**
   * Method to return status of the ball.
   *
   * @return Status of ball.
   */
  String getStatus();
}
