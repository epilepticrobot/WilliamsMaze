//copyright William Zulueta 2012
package mazeproject;

public class MazeProject
{
    private int width = 500;
    private int height = 400;
    public static void main(String[] args)
    {
        MazeProject mp = new MazeProject();
        mp.runme();
    }

    private void runme()
    {
         MazeFrame mf = new MazeFrame();
//         mf.setSize(width, height);
         mf.setVisible(true);
    }
}
