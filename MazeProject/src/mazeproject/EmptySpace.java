package mazeproject;

public class EmptySpace implements MazePiece
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
}
