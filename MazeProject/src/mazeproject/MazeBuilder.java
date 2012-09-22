//copyright William Zulueta 2012
package mazeproject;

public class MazeBuilder
{
    private MazePiece emptySpaceN = new EmptySpace(); //create empty space north
    private MazePiece emptySpaceS = new EmptySpace(); //create empty space south
    private MazePiece emptySpaceE = new EmptySpace(); //create empty space east
    private MazePiece emptySpaceW = new EmptySpace(); //create empty space west
    private MazePiece emptySpaceNW = new EmptySpace();
    private MazePiece currentPiece;
    private MazePiece adjacentPiece = new EmptySpace();

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
        //
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

    public MazePiece startingMazePiece()
    {
        simpleBuild();
        return emptySpaceS;

    }
}
