import java.util.HashMap;
import java.util.Map;

class AvailableOthelloGrid {

    private static boolean areTheSame(Turn turn, OthelloGrid grid) {
        if (turn == Turn.WHITE) {
            return grid == OthelloGrid.WHITE;
        }
        return grid == OthelloGrid.WHITE;
    }

    private static int calculateAvailableByList(Turn turn, OthelloGrid[] othelloGrid) {
        int available = 0;
        for (OthelloGrid grid : othelloGrid) {
            if (areTheSame(turn, grid)) {
                return 0;
            } else if (grid == OthelloGrid.NONE) {
                return available;
            }
            available++;
        }
        return 0;
    }

    private static OthelloGrid[] remainingGridsRight(int index, OthelloGrid[] allGrids) {
        OthelloGrid[] remaining = new OthelloGrid[allGrids.length - index - 1];
        System.arraycopy(allGrids, index + 1, remaining, 0, remaining.length);
        return remaining;
    }

    private static OthelloGrid[] remainingGridsLeft(int index, OthelloGrid[] allGrids) {
        OthelloGrid[] remaining = new OthelloGrid[index];
        System.arraycopy(allGrids, 0, remaining, 0, remaining.length);
        return remaining;
    }

    private static OthelloGrid[] remainingGridsUp(int index, OthelloGrid[] allGrids) {
        OthelloGrid[] remaining = new OthelloGrid[index];
        System.arraycopy(allGrids, 0, remaining, 0, remaining.length);
        return remaining;
    }

    private static OthelloGrid[] remainingGridsDown(int index, OthelloGrid[] allGrids) {
        OthelloGrid[] remaining = new OthelloGrid[allGrids.length - index - 1];
        System.arraycopy(allGrids, index + 1, remaining, 0, remaining.length);
        return remaining;
    }

    private static OthelloGrid[] remainingGridsUpRight(int row, int column, OthelloGrid[][] othelloGrid) {
        int possibleRows = 7 - row;
        int length = Math.min(possibleRows, column);
        OthelloGrid[] remaining = new OthelloGrid[length];
        for (int i = 0; i < length; i++) {
            remaining[i] = othelloGrid[row + 1 + i][column - 1 - i];
        }
        return remaining;
    }

    private static OthelloGrid[] remainingGridsUpLeft(int row, int column, OthelloGrid[][] othelloGrid) {
        int length = Math.min(row, column);
        OthelloGrid[] remaining = new OthelloGrid[length];
        for (int i = 0; i < length; i++) {
            remaining[i] = othelloGrid[row - 1 - i][column - 1 - i];
        }
        return remaining;
    }

    private static OthelloGrid[] remainingGridsDownRight(int row, int column, OthelloGrid[][] othelloGrid) {
        int possibleRows = 7 - row;
        int possibleColumns = 7 - column;
        int length = Math.min(possibleRows, possibleColumns);
        OthelloGrid[] remaining = new OthelloGrid[length];
        for (int i = 0; i < length; i++) {
            remaining[i] = othelloGrid[row + 1 + i][column + 1 + i];
        }
        return remaining;
    }

    private static OthelloGrid[] remainingGridsDownLeft(int row, int column, OthelloGrid[][] othelloGrid) {
        int possibleColumns = 7 - column;
        int length = Math.min(row, possibleColumns);
        OthelloGrid[] remaining = new OthelloGrid[length];
        for (int i = 0; i < length; i++) {
            remaining[i] = othelloGrid[row - 1 - i][column + 1 + i];
        }
        return remaining;
    }

    static Map<Integer, Integer> available(Turn turn, OthelloGrid[][] othelloGrid) {
        /*
         *first Integer => grid is 2 length
         * second Integer => possible grids to turn is 1 length
         */
        Map<Integer, Integer> availableGrids = new HashMap<>();


        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (areTheSame(turn, othelloGrid[row][column])) {
                    OthelloGrid[] remaining;
                    // right
                    remaining = remainingGridsRight(row, othelloGrid[row]);
                    int right = calculateAvailableByList(turn, remaining);
                    if (right != 0) {
                        int key = (row + right + 1) * 10 + column;
                        if (availableGrids.containsKey(key)) {
                            int temp = availableGrids.get(key);
                            availableGrids.put(key, right + temp);
                        } else {
                            availableGrids.put(key, right);
                        }
                    }

                    // left
                    remaining = remainingGridsLeft(row, othelloGrid[row]);
                    int left = calculateAvailableByList(turn, remaining);
                    if (left != 0) {
                        int key = (row + left - 1) * 10 + column;
                        if (availableGrids.containsKey(key)) {
                            int temp = availableGrids.get(key);
                            availableGrids.put(key, left + temp);
                        } else {
                            availableGrids.put(key, left);
                        }
                    }

                    // up
                    remaining = remainingGridsUp(row, othelloGrid[row]);
                    int up = calculateAvailableByList(turn, remaining);
                    if (up != 0) {
                        int key = row * 10 + (column - up - 1);
                        if (availableGrids.containsKey(key)) {
                            int temp = availableGrids.get(key);
                            availableGrids.put(key, up + temp);
                        } else {
                            availableGrids.put(key, up);
                        }
                    }

                    // down
                    remaining = remainingGridsDown(row, othelloGrid[row]);
                    int down = calculateAvailableByList(turn, remaining);
                    if (down != 0) {
                        int key = row * 10 + (column + down + 1);
                        if (availableGrids.containsKey(key)) {
                            int temp = availableGrids.get(key);
                            availableGrids.put(key, down + temp);
                        } else {
                            availableGrids.put(key, down);
                        }
                    }

                    // upRight
                    remaining = remainingGridsUpRight(row, column, othelloGrid);
                    int upRight = calculateAvailableByList(turn, remaining);
                    if (upRight != 0) {
                        int key = (row + upRight + 1) * 10 + (column - upRight - 1);
                        if (availableGrids.containsKey(key)) {
                            int temp = availableGrids.get(key);
                            availableGrids.put(key, upRight + temp);
                        } else {
                            availableGrids.put(key, upRight);
                        }
                    }

                    // upLeft
                    remaining = remainingGridsUpLeft(row, column, othelloGrid);
                    int upLeft = calculateAvailableByList(turn, remaining);
                    if (upLeft != 0) {
                        int key = (row - upLeft - 1) * 10 + (column - upLeft - 1);
                        if (availableGrids.containsKey(key)) {
                            int temp = availableGrids.get(key);
                            availableGrids.put(key, upLeft + temp);
                        } else {
                            availableGrids.put(key, upLeft);
                        }
                    }

                    // downRight
                    remaining = remainingGridsDownRight(row, column, othelloGrid);
                    int downRight = calculateAvailableByList(turn, remaining);
                    if (downRight != 0) {
                        int key = (row + downRight + 1) * 10 + (column + downRight + 1);
                        if (availableGrids.containsKey(key)) {
                            int temp = availableGrids.get(key);
                            availableGrids.put(key, downRight + temp);
                        } else {
                            availableGrids.put(key, downRight);
                        }
                    }

                    // downLeft
                    remaining = remainingGridsDownLeft(row, column, othelloGrid);
                    int downLeft = calculateAvailableByList(turn, remaining);
                    if (downLeft != 0) {
                        int key = (row - downLeft - 1) * 10 + (column + downLeft + 1);
                        if (availableGrids.containsKey(key)) {
                            int temp = availableGrids.get(key);
                            availableGrids.put(key, downLeft + temp);
                        } else {
                            availableGrids.put(key, downLeft);
                        }
                    }


                }
            }
        }


        return availableGrids;
    }
}
