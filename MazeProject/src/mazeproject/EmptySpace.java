//copyright William Zulueta 2012
package mazeproject;

import java.awt.Color;

public class EmptySpace implements MazePiece
{
    private MazePiece eastNeighbor = new RunsNorthSouthWall();
    private MazePiece westNeighbor = new RunsNorthSouthWall();
    private MazePiece northNeighbor = new RunsEastWestWall();
    private MazePiece southNeighbor = new RunsEastWestWall();

    @Override
    public MazePiece getNorthNeighbor()
    {
        return northNeighbor;
    }

    @Override
    public void setNorthNeighbor(MazePiece mazePieceWMPN)
    {
        northNeighbor = mazePieceWMPN;
    }

    @Override
    public MazePiece getSouthNeighbor()
    {
        return southNeighbor;
    }

    @Override
    public void setSouthNeighbor(MazePiece mazePieceWMPS)
    {
        southNeighbor = mazePieceWMPS;
    }

    @Override
    public MazePiece getEastNeighbor()
    {
        return eastNeighbor;
    }

    @Override
    public void setEastNeighbor(MazePiece mazePieceWMPE)
    {
        eastNeighbor = mazePieceWMPE;
    }

    @Override
    public MazePiece getWestNeighbor()
    {
        return westNeighbor;
    }

    @Override
    public void setWestNeighbor(MazePiece mazePieceWMPW)
    {
        westNeighbor = mazePieceWMPW;
    }

    @Override
    public double getWidth()
    {
        return 21;
    }

    @Override
    public double getHeight()
    {
        return 21;
    }

    @Override
    public Color getColor()
    {
        return Color.magenta;
    }

    @Override
    public void changeColor(Color c)
    {
        return c.green;
    }
}
