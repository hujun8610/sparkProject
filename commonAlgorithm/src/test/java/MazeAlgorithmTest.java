import junit.framework.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by hujun on 2016/3/24.
 */
public class MazeAlgorithmTest {

    MazeAlgorithm mazeAlgorithm = new MazeAlgorithm();

    @Test
    public void searchMazeBFSTest(){
        int[][] maze = new int[][]{
            {0,1,0,0,0},
            {0,1,0,1,1},
            {0,0,0,0,0},
            {1,1,1,1,0},
            {1,1,1,1,0}
        };

        Stack<Point> stack = mazeAlgorithm.searchMazeBFS(maze);
        Assert.assertEquals(8,stack.size());

    }

}
