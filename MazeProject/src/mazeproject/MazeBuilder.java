//copyright William Zulueta 2012
package mazeproject;

import java.awt.Color;
import java.util.Random;

public class MazeBuilder
{
    private MazePiece emptySpaceN = new EmptySpace(); //create empty space north
    private MazePiece emptySpaceS = new EmptySpace(); //create empty space south
    private MazePiece emptySpaceE = new EmptySpace(); //create empty space east
    private MazePiece emptySpaceW = new EmptySpace(); //create empty space west
    private MazePiece emptySpaceNW = new EmptySpace();
    private MazePiece currentPiece;
    private MazePiece adjacentPiece = new EmptySpace();
    private MazePiece randomPiece = new EmptySpace()
    {
        @Override
        public Color getColor()
        {
            return Color.GREEN;
        }
    };
    private Random r;

    public void simpleBuild()
    {
        currentPiece = emptySpaceS;
        emptySpaceN.setSouthNeighbor(emptySpaceS);//connect empty space south with north
        emptySpaceS.setNorthNeighbor(emptySpaceN);
        emptySpaceNW.setEastNeighbor(emptySpaceN);
        emptySpaceN.setWestNeighbor(emptySpaceNW);
        currentPiece.setEastNeighbor(adjacentPiece);
        adjacentPiece.setWestNeighbor(currentPiece);
        currentPiece.setSouthNeighbor(new EmptySpace());
        adjacentPiece = currentPiece.getSouthNeighbor();
        adjacentPiece.setNorthNeighbor(currentPiece);
        currentPiece = adjacentPiece;
        currentPiece.setEastNeighbor(new EmptySpace());
        adjacentPiece = currentPiece.getEastNeighbor();
        adjacentPiece.setWestNeighbor(currentPiece);
        currentPiece = adjacentPiece;
        currentPiece.setEastNeighbor(new EmptySpace());
        adjacentPiece = currentPiece.getEastNeighbor();
        adjacentPiece.setWestNeighbor(currentPiece);
        currentPiece = adjacentPiece;
        currentPiece.setNorthNeighbor(new EmptySpace());
        adjacentPiece = currentPiece.getNorthNeighbor();
        adjacentPiece.setSouthNeighbor(currentPiece);
        //ajacentPiece.setNorthNeighbor(currentPiece);
        //currentPiece.setSouthNeighbor(adjacentPiece);

    }

    public void randomBuild()
    {
        r = new Random();
        float r2;
        currentPiece = randomPiece;
        for (int i = 0; i < 100; i++)
        {
            r2 = r.nextFloat();
            System.out.println(r2);
            if (r2 <= .25)
            {

                currentPiece.setNorthNeighbor(new EmptySpace());
                adjacentPiece = currentPiece.getNorthNeighbor();
                adjacentPiece.setSouthNeighbor(currentPiece);
            }
            if (r2 > .25 && r2 < .55)
            {

                currentPiece.setEastNeighbor(new EmptySpace());
                adjacentPiece = currentPiece.getEastNeighbor();
                adjacentPiece.setWestNeighbor(currentPiece);
            }
            if (r2 >= .55 && r2 <= .75)
            {

                currentPiece.setSouthNeighbor(new EmptySpace());
                adjacentPiece = currentPiece.getSouthNeighbor();
                adjacentPiece.setNorthNeighbor(currentPiece);

            }
            if (r2 > .75)
            {

                currentPiece.setWestNeighbor(new EmptySpace());
                adjacentPiece = currentPiece.getWestNeighbor();
                adjacentPiece.setEastNeighbor(currentPiece);
            }
            currentPiece = adjacentPiece;
        }
    }

    public MazePiece startingMazePiece()
    {
        randomBuild();
        return randomPiece;

    }
}
