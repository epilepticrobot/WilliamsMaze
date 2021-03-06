//copyright William Zulueta 2012
package mazeproject;

import java.awt.Color;


public class RunsEastWestWall implements MazePiece  
{
     private MazePiece northNeighbor;
    private MazePiece southNeighbor;
    private MazePiece eastNeighbor;
    private MazePiece westNeighbor;

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
        return 24;
    }

    @Override
    public double getHeight()
    {
        return 3;
    }

    @Override
    public Color getColor()
    {
        return Color.BLACK;
    }

    @Override
    public void changeColor()
    {
    }

}
