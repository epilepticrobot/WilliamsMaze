//copyright William Zulueta 2012
package mazeproject;

public class MazeBuilder
{
    private MazePiece emptySpaceN = new EmptySpace(); //create empty space north
    private MazePiece emptySpaceS = new EmptySpace(); //create empty space south
    private MazePiece emptySpaceE = new EmptySpace(); //create empty space east
    private MazePiece emptySpaceW = new EmptySpace(); //create empty space west

    public void simpleBuild()
    {
        emptySpaceN.setSouthNeighbor(emptySpaceS);//connect empty space south with north
        emptySpaceS.setNorthNeighbor(emptySpaceN);

    }

    public MazePiece startingMazePiece()
    {
        simpleBuild();
        return emptySpaceS;

    }
}
