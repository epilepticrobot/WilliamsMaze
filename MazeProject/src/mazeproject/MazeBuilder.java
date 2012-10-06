//copyright William Zulueta 2012
package mazeproject;

import java.awt.Color;
import java.util.HashMap;
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
    private HashMap<String, EmptySpace> mazePieceStore = new HashMap();
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
        int x = 0;
        int y = 0;
        r = new Random();
        float r2;
        currentPiece = randomPiece;
        for (int i = 0; i < 638; i++)
        {
            r2 = r.nextFloat();
            //System.out.println(r2);
            if (r2 <= .25)
            {
                y--;
                currentPiece.setNorthNeighbor(nextSpace(x, y));
                adjacentPiece = currentPiece.getNorthNeighbor();
                System.out.println(adjacentPiece + "gotHere");
                adjacentPiece.setSouthNeighbor(currentPiece);
            }
            if (r2 > .25 && r2 < .55)
            {
                x++;
                currentPiece.setEastNeighbor(nextSpace(x, y));
                adjacentPiece = currentPiece.getEastNeighbor();
                System.out.println(adjacentPiece + "gotHere");
                adjacentPiece.setWestNeighbor(currentPiece);
            }
            if (r2 >= .55 && r2 <= .75)
            {
                y++;
                currentPiece.setSouthNeighbor(nextSpace(x, y));
                adjacentPiece = currentPiece.getSouthNeighbor();
                System.out.println(adjacentPiece + "gotHere");
                adjacentPiece.setNorthNeighbor(currentPiece);

            }
            if (r2 > .75)
            {
                x--;
                currentPiece.setWestNeighbor(nextSpace(x, y));
                adjacentPiece = currentPiece.getWestNeighbor();
                System.out.println(adjacentPiece + "gotHere");
                adjacentPiece.setEastNeighbor(currentPiece);
            }
            currentPiece = adjacentPiece;
        }
    }

    private EmptySpace nextSpace(int x, int y)
    {

        if (mazePieceStore.containsKey(getXYString(x, y)))
        {
            System.out.println("get");
            // return mazePieceStore.get(x + "," + y);
            EmptySpace eS = mazePieceStore.get(getXYString(x, y));
            System.out.println(eS);
            System.out.println("hello");
            return eS;

        } else
        {
            System.out.println("put");
            EmptySpace es = new EmptySpace();
            mazePieceStore.put(getXYString(x, y), es);
            return es;
        }


    }

    private String getXYString(int x, int y)
    {
        return x + ", " + y;
    }

    private Integer getXY(int x, int y)
    {
        return new Integer(x * 1000 + y);
    }

    public MazePiece startingMazePiece()
    {
        randomBuild();
        return randomPiece;

    }
}
