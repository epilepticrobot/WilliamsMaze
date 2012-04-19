package org.wintrisstech.erik.iaroc;

import android.os.SystemClock;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.wintrisstech.irobot.ioio.IRobotCreateAdapter;
import org.wintrisstech.irobot.ioio.IRobotCreateInterface;
import org.wintrisstech.irobot.ioio.IRobotCreateScript;
import org.wintrisstech.sensors.UltraSonicSensors;

/**
 * A Ferrari is an implementation of the IRobotCreateInterface.
 *
 * @author Erik
 */
public class Ferrari extends IRobotCreateAdapter implements Runnable
{
    private static final String TAG = "Ferrari";
    private final UltraSonicSensors ultraSonicSensors;
    /*
     * The maze can be thought of as a grid of quadratic cells, separated by
     * zero-width walls. The cell width includes half a pipe diameter on each
     * side, i.e the cell edges pass through the center of surrounding pipes.
     * <p> Row numbers increase northward, and column numbers increase eastward.
     * <p> Positions and direction use a reference system that has its origin at
     * the west-most, south-most corner of the maze. The x-axis is oriented
     * eastward; the y-axis is oriented northward. The unit is 1 mm. <p> What
     * the Ferrari knows about the maze is:
     */
    private final static int NUM_ROWS = 12;
    private final static int NUM_COLUMNS = 4;
    private final static int CELL_WIDTH = 712;
    /*
     * The reading of the front sensor when the Ferrari is placed at the center
     * of a cell facing a wall.
     */
    private final static int FRONT_ADJUSTMENT = 151;
    /*
     * The sum of the readings of the left and right sensors divided by two when
     * the Ferrari is placed at the center of a cell with a wall on each side
     */
    private final static int LEFT_ADJUSTMENT = 159;
    private final static int START_ROW = 0;
    private final static int START_COLUMN = 3;
    private final static CardinalDirection START_DIRECTION = CardinalDirection.NORTH;
    private final static int FINISH_ROW = 11;
    private final static int FINISH_COLUMN = 1;
    private final static CardinalDirection FINISH_DIRECTION = CardinalDirection.NORTH;
    /*
     * State variables:
     */
    private int speed = 300; // The normal speed of the Ferrari when going straight
    // The row and column number of the current cell. 
    private int row;
    private int column;
    // The direction from the current cell to the next cell:
    private CardinalDirection direction;
    // Current estimated position and direction of the Ferrari:
    private int estimatedPositionX;
    private int estimatedPositionY;
    private int estimatedDirectionX;
    private int estimatedDirectionY;
    // The center of the next cell, where we want the Ferrari to go:
    private int targetPositionX;
    private int targetPositionY;
    // Sensed walls surrounding the Ferrari:
    private boolean rightWallPresent;
    private boolean frontWallPresent;
    private boolean leftWallPresent;
    private boolean firstPass = true;
    private int currentDistance = 0;
    // Used to store the direction to exit each cell.
    private CardinalDirection[][] exitDirection = new CardinalDirection[NUM_ROWS][NUM_COLUMNS];
    private boolean firstRun = true;
    private boolean emergencyStop = false;
    private boolean running = true;
    private final static int SECOND = 1000; // number of millis in a second
    int[] frontSong =
    {
        +70, 100
    };
    int[] gus = new int[1000];
    int distanceMovedSoFar = 0;
    int howFarTurned = 0;

    /**
     * Constructs a Ferrari, an amazing machine!
     *
     * @param ioio the IOIO instance that the Ferrari can use to communicate
     * with other peripherals such as sensors
     * @param create an implementation of an iRobot
     */
    public Ferrari(IOIO ioio, IRobotCreateInterface create) throws ConnectionLostException
    {
        super(create);
        ultraSonicSensors = new UltraSonicSensors(ioio);
    }

    /**
     * Main method that gets the Ferrari running.
     *
     */
    public void run()
    {

        int[] jenny =
        {
            72, 20, 72, 20, 72, 20, 72, 40, 71, 20, 69, 20, 71, 20, 72, 20, 74,
            20, 76, 20, 76, 20, 76, 20, 76, 40, 74, 20, 72, 20, 74, 20, 76, 20,
            77, 20, 79, 20, 72, 20, 79, 20, 77, 20, 76, 20, 74, 20, 72, 20, 71,
            20, 69, 20, 67, 20, 60, 20
        };
        int[] CT =
        {
            72, 20, 72, 20, 72, 20, 72, 40, 71, 20, 69, 20, 71, 20, 72, 20, 74,
            20, 76, 20, 76, 20, 76, 20, 76, 40, 74, 20, 72, 20, 74, 20, 76, 20,
            77, 20, 79, 20, 72, 20, 79, 20, 77, 20, 76, 20, 74, 20, 72, 20, 71,
            20, 69, 20, 67, 20, 60, 20
        };

        int[] a =
        {
            60, 32, 61, 32, 60, 32
        };
        int[] b =
        {
            31, 31, 31, 31, 31, 31
        };
        int[] c =
        {
            127, 127, 127, 127, 127, 127
        };
        int[] right =
        {
            48, 20, 52, 20, 53, 20, 55, 80, 48, 20, 52, 20, 53, 20, 55, 80, 48, 20, 52, 20, 53, 20, 55, 80, 52, 80, 48, 80, 52, 80, 50, 80
        };
        int[] left =
        {
            127, 127, 42, 127, 127, 127
        };
        try
        {
            driveDirect(100, 100);
            while (true)
            {
                readSensors(SENSORS_GROUP_ID6);
                if (isBumpLeft() && isBumpRight())//bump front
                {
                    driveDirect(-100, -100);
                } else
                {
                    if (isBumpLeft())
                    {
                        driveDirect(-100, -70);
                        SystemClock.sleep(5000);
                        driveDirect(100, 100);
                    }
                    if (isBumpRight())
                    {
                        driveDirect(-70, -100);
                        SystemClock.sleep(5000);
                        driveDirect(100, 100);
                    }
                if (getInfraredByte() != 255)//robot sees somthing
                {
                    driveDirect(100, 100);
                } else
                {
                    driveDirect(200, -200);
                }


                }
            }
        } catch (ConnectionLostException ex)
        {
        }
    }

    public void turnRight360()
    {
        try
        {
            howFarTurned = howFarTurned + getAngle();
            readSensors(SENSORS_GROUP_ID6);
            if (howFarTurned > 360)
            {
                driveDirect(0, 0);//stop
                SystemClock.sleep(1000);
                return;
            }
            driveDirect(-100, 100);
            SystemClock.sleep(20);
        } catch (ConnectionLostException ex)
        {
            Logger.getLogger(Ferrari.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * To run this test, place the Ferrari in a cell surrounded by 4 walls. <p>
     * Note: The sensors draw power from the Create's battery. Make sure it is
     * charged.
     */
    public void testUltraSonicSensors()
    {
        long endTime = System.currentTimeMillis() + 20 * SECOND;
        while (System.currentTimeMillis() < endTime)
        {
            try
            {
                ultraSonicSensors.readUltrasonicSensors();
            } catch (ConnectionLostException ex)
            {
                //TODO
            } catch (InterruptedException ex)
            {
                //TODO
            }
            SystemClock.sleep(500);
        }

        /**
         * Main method that gets the Ferrari moving. Implements multiple runs of
         * the Ferrari through the maze.
         */
    }

    public void go()
    {
        while (true)
        {
            try
            {
                singleRun();
            } catch (ConnectionLostException ex)
            {
            } catch (InterruptedException ex)
            {
            }
        }
    }

    public void bumpRight()
    {
        try
        {
            driveDirect(-200, -250);
            SystemClock.sleep(2000);
            driveDirect(250, 250);
        } catch (ConnectionLostException ex)
        {
        }
    }

    public void bumpLeft()
    {
        try
        {
            driveDirect(-250, -200);
            SystemClock.sleep(2000);
            driveDirect(250, 250);
        } catch (ConnectionLostException ex)
        {
        }
    }

    public void bumpFront()
    {
        try
        {
            playSong(2);
            turnAndGo(0, -500);
            turnAndGo(90, 500);
        } catch (Exception ex)
        {
        }
    }

    /**
     * Place the Ferrari in the start cell facing the start direction then press
     * SW1.
     */
    private void singleRun() throws ConnectionLostException, InterruptedException
    {
        row = START_ROW;
        column = START_COLUMN;
        direction = START_DIRECTION;
        estimatedDirectionX = direction.x;
        estimatedDirectionY = direction.y;
        estimatePosition();
        while (!(row == FINISH_ROW && column == FINISH_COLUMN))
        {
            //finds the direction of the next cell and its center       
            findNextTarget();
            goToTarget(); //moves to target
            updatePositionParameters();
        }
//        hitFinishPlate();
        // wheels on Create have stopped.
        firstRun = false; // Ferrari reached finish plate.
    }

    /**
     * Estimates the Ferrari's current position based on the row, column and
     * orientation, and the readings from the distance gauges. When this method
     * is called, the Ferrari's approximate orientation must be given by
     * direction.
     */
    private void estimatePosition()
    {
        estimatedPositionX = column * CELL_WIDTH + CELL_WIDTH / 2;
        estimatedPositionY = row * CELL_WIDTH + CELL_WIDTH / 2;
        int xOffset = estimateFrontOffset(); //the x offset if direction = (1,0)
        int yOffset = estimateLeftOffset();  //the y offset if direction = (1,0)
        estimatedPositionX += direction.x * xOffset - direction.y * yOffset;
        estimatedPositionY += direction.y * xOffset + direction.x * yOffset;
    }

    /**
     * Estimates the distance between the center of the cell and the center of
     * the Ferrari in the back-to-front direction. Positive values mean that
     * that the Ferrari is ahead of the center, negative values mean that the
     * Ferrari is behind the center.
     *
     * @return the offset in mm
     */
    private int estimateFrontOffset()
    {
        int front = ultraSonicSensors.getFrontDistance();
        if (0 < front && front < CELL_WIDTH)
        {
            frontWallPresent = true;
            return FRONT_ADJUSTMENT - front;
        } else
        {
            frontWallPresent = false;
            return 0; // assume that the Ferrari is centered
        }
    }

    /**
     * Estimates the distance between the center of the cell and the center of
     * the Ferrari in the right-to-left direction. A positive value means that
     * that the Ferrari is to the left of the center, a negative value means
     * that the Ferrari is to the right of the center.
     *
     * @return the offset in mm.
     */
    private int estimateLeftOffset()
    {
        int right = ultraSonicSensors.getRightDistance();
        int left = ultraSonicSensors.getLeftDistance();
        if (0 < right && right < CELL_WIDTH)
        {
            rightWallPresent = true;
            if (0 < left && left < CELL_WIDTH)
            {
                leftWallPresent = true;
                return (right - left) / 2;
            } else
            {
                leftWallPresent = false;
                return -LEFT_ADJUSTMENT + right;
            }
        } else if (0 < left && left < CELL_WIDTH)
        {
            rightWallPresent = false;
            leftWallPresent = true;
            return LEFT_ADJUSTMENT - left;
        } else
        {
            rightWallPresent = false;
            leftWallPresent = false;
            return 0; // assume that the Ferrari is centered
        }
    }

    /**
     * Determines which cell to move to next, and sets the target coordinates to
     * the center of that cell. When this method is called, the Ferrari's
     * approximate orientation must be given by direction. The method will
     * update direction to indicate the direction from the current cell to the
     * next cell.
     */
    private void findNextTarget()
    {

        if (firstRun)
        { // do left "wall hugger"
            if (!leftWallPresent)
            {
                direction = direction.left(); // turn left
            } else if (!frontWallPresent)
            {
                // keep same direction
            } else if (!rightWallPresent)
            {
                direction = direction.right(); //turn right
            } else
            {
                direction = direction.back(); //make u-turn
            }
            exitDirection[row][column] = direction;
        } else
        { // use info collected from a previous run
            direction = exitDirection[row][column];
        }
        targetPositionX = (column + direction.x) * CELL_WIDTH + CELL_WIDTH / 2;
        targetPositionY = (row + direction.y) * CELL_WIDTH + CELL_WIDTH / 2;
    }

    /**
     * Calculates the necessary turn needed to face the target position and the
     * distance to get there, then turns and goes there.
     */
    private void goToTarget() throws ConnectionLostException, InterruptedException
    {
        int targetDirectionX = targetPositionX - estimatedPositionX;
        int targetDirectionY = targetPositionY - estimatedPositionY;
        int angle = angle(estimatedDirectionX, estimatedDirectionY,
                targetDirectionX, targetDirectionY);
        int distance = distance(estimatedPositionX, estimatedPositionY,
                targetPositionX, targetPositionY);
        turnAndGo(angle, distance);
    }

    /**
     * Updates the cell row and column, and the position and direction estimates
     * after the Ferrari has moved into a new cell.
     */
    private void updatePositionParameters()
    {
        row += direction.y;
        column += direction.x;
        int prevEstimatedPositionX = estimatedPositionX;
        int prevEstimatedPositionY = estimatedPositionY;
        estimatePosition();
        estimatedDirectionX = estimatedPositionX - prevEstimatedPositionX;
        estimatedDirectionY = estimatedPositionY - prevEstimatedPositionY;
    }

    /**
     * Turns in place and then goes forward.
     *
     * @param angle the angle in degrees that the Ferrari shall turn. Negative
     * values makes clockwise turns.
     * @param distance the distance in mm that the Ferrari shall run forward.
     * Must be positive.
     */
    private void turnAndGo(int angle, int distance)
            throws ConnectionLostException, InterruptedException
    {
        IRobotCreateScript script = new IRobotCreateScript();
        /*
         * The Create overshoots by approx. 3 degrees depending on the floor
         * surface. Note: This is speed sensitive.
         */
        // TODO: Further tweaks to make the Ferrari make more precise turns.  
        if (angle < 0)
        {
            angle = Math.min(0, angle + 3);
        }
        if (angle > 0)
        {
            angle = Math.max(0, angle - 3);
        }
        if (angle != 0)
        {
            script.turnInPlace(100, angle < 0); // Do not change speed!
            script.waitAngle(angle);
        }
        if (distance > 0)
        {
            script.driveStraight(speed);
            script.waitDistance(distance);
        }
        if (angle != 0 || distance > 0)
        {
            script.stop();
            playScript(script.getBytes(), false);
            // delay return from this method until script has finished executing
        }
    }

    /**
     * Method to call when the Ferrari is in the finish cell. A call to this
     * method makes the Ferrari turn toward the finish plate and advance toward
     * it until it hits it.
     */
//    private void hitFinishPlate() throws ConnectionLostException, InterruptedException
//    {
//
//        int angle = angle(estimatedDirectionX, estimatedDirectionY,
//                FINISH_DIRECTION.x, FINISH_DIRECTION.y);
//        turnAndGo(angle, 0);// turn to face finish plate
//        goForward(); // Move gently forward
//        while (!isBumpLeft() && !isBumpRight())
//        { // while sensing for bumps
//            SystemClock.sleep(100);
//            readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
//        }
//        driveDirect(0, 0); // stop
//    }
    /**
     * Calculates the angle in degrees rounded to the nearest int between a
     * startDirection vector and an endDirection vector.
     *
     * @param startDirectionX the x-coordinate of the startDirection vector
     * @param startDirectionY the y-coordinate of the startDirection vector
     * @param endDirectionX the x-coordinate of the enDirection vector
     * @param endDirectionY the y-coordinate of the endDirection vector
     * @return the angle in degrees
     */
    public static int angle(int startDirectionX, int startDirectionY,
            int endDirectionX, int endDirectionY)
    {
        return (int) Math.round(Math.atan2(
                -startDirectionY * endDirectionX + startDirectionX * endDirectionY,
                startDirectionX * endDirectionX + startDirectionY * endDirectionY));
    }

    /**
     * Calculates the distance between two points and rounds the result to the
     * nearest int.
     *
     * @param startX x-coordinate of the first point
     * @param startY y-coordinate of the first point
     * @param endX x-coordinate of the second point
     * @param endY y-coordinate of the second point
     * @return
     */
    public static int distance(int startX, int startY, int endX, int endY)
    {
        int a = endX - startX;
        int b = endY - startY;
        return (int) (Math.sqrt(a * a + b * b) + 0.5);
    }

    /**
     * Closes down all the connections of the Ferrari, including the connection
     * to the iRobot Create and the connections to all the sensors.
     */
    public void shutDown()
    {
        closeConnection(); // close the connection to the Create
        ultraSonicSensors.closeConnection();
    }

    //// Methods made public for the purpose of the Dashboard ////
    public int getLeftDistance()
    {
        return ultraSonicSensors.getLeftDistance();
    }

    public int getFrontDistance()
    {
        return ultraSonicSensors.getFrontDistance();
    }

    public int getRightDistance()
    {
        return ultraSonicSensors.getRightDistance();
    }

    public synchronized boolean isRunning()
    {
        return running;
    }

    private synchronized void setRunning(boolean b)
    {
        running = false;
    }

    private void playSong(int[] song1)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}