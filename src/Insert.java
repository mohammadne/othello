import java.util.Map;

public class Insert {
    private static OthelloGrid alter(Turn turn) {
        if (turn == Turn.WHITE) {
            return OthelloGrid.WHITE;
        }
        return OthelloGrid.BLACK;
    }

    static OthelloGrid[][] insertion(Turn turn, OthelloGrid[][] othelloGrids, Map<Integer, Map<String, Integer>> avilables, int grid) {
        int row = grid / 10;
        int column = grid % 10;
        OthelloGrid[][] newOthelloGrids = othelloGrids;
        Map<String, Integer> changableGrid = avilables.get(grid);

        //point
        newOthelloGrids[row][column] = alter(turn);
        //right
        if (changableGrid.containsKey("right")) {
            int temp = changableGrid.get("right");
            for (int i = 1; i <= temp; i++) {
                newOthelloGrids[row][column + i] = alter(turn);
            }
        }
        //left
        if (changableGrid.containsKey("left")) {
            int temp = changableGrid.get("left");
            for (int i = 1; i <= temp; i++) {
                newOthelloGrids[row][column - i] = alter(turn);
            }
        }
        //up
        if (changableGrid.containsKey("up")) {
            int temp = changableGrid.get("up");
            for (int i = 1; i <= temp; i++) {
                newOthelloGrids[row - i][column] = alter(turn);
            }
        }
        //down
        if (changableGrid.containsKey("down")) {
            int temp = changableGrid.get("down");
            for (int i = 1; i <= temp; i++) {
                newOthelloGrids[row + i][column] = alter(turn);
            }
        }
        //upRight
        if (changableGrid.containsKey("upRight")) {
            int temp = changableGrid.get("upRight");
            for (int i = 1; i <= temp; i++) {
                newOthelloGrids[row - i][column + i] = alter(turn);
            }
        }
        //upLeft
        if (changableGrid.containsKey("upLeft")) {
            int temp = changableGrid.get("upLeft");
            for (int i = 1; i <= temp; i++) {
                newOthelloGrids[row - i][column - i] = alter(turn);
            }
        }
        //downRight
        if (changableGrid.containsKey("downRight")) {
            int temp = changableGrid.get("downRight");
            for (int i = 1; i <= temp; i++) {
                newOthelloGrids[row + i][column + i] = alter(turn);
            }
        }
        //downLeft
        if (changableGrid.containsKey("downLeft")) {
            int temp = changableGrid.get("downLeft");
            for (int i = 1; i <= temp; i++) {
                newOthelloGrids[row + i][column - i] = alter(turn);
            }
        }


        return newOthelloGrids;
    }
}
