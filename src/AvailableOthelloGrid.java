import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

class AvailableOthelloGrid {

    private static boolean areTheSame(Turn turn, OthelloGrid grid) {
        if (turn == Turn.WHITE) {
            return grid == OthelloGrid.WHITE;
        }
        return grid == OthelloGrid.BLACK;
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

    private static OthelloGrid[] reverseArray(OthelloGrid[] grids) {
        OthelloGrid[] temp = new OthelloGrid[grids.length];
        for (int i = 0; i < grids.length; i++) {
            temp[i] = grids[grids.length - 1 - i];
        }
        return temp;
    }

    private static OthelloGrid[] remainingGridsRight(int index, OthelloGrid[] allGrids) {
        OthelloGrid[] remaining = new OthelloGrid[allGrids.length - index - 1];
        System.arraycopy(allGrids, index + 1, remaining, 0, remaining.length);
        return remaining;
    }

    private static OthelloGrid[] remainingGridsLeft(int index, OthelloGrid[] allGrids) {
        OthelloGrid[] remaining = new OthelloGrid[index];
        System.arraycopy(allGrids, 0, remaining, 0, remaining.length);
        return reverseArray(remaining);
    }

    private static OthelloGrid[] remainingGridsUp(int row, int column, OthelloGrid[][] othelloGrid) {
        OthelloGrid[] remaining = new OthelloGrid[row];
        for (int i = 0; i < row; i++) {
            remaining[i] = othelloGrid[i][column];
        }
        return reverseArray(remaining);
    }

    private static OthelloGrid[] remainingGridsDown(int row, int column, OthelloGrid[][] othelloGrid) {
        OthelloGrid[] remaining = new OthelloGrid[7 - row];
        for (int i = 0; i < 7 - row; i++) {
            remaining[i] = othelloGrid[i + row + 1][column];
        }
        return remaining;
    }

    private static OthelloGrid[] remainingGridsUpRight(int row, int column, OthelloGrid[][] othelloGrid) {
        int possibleRight = 7 - column;
        int length = Math.min(possibleRight, row);
        OthelloGrid[] remaining = new OthelloGrid[length];
        for (int i = 0; i < length; i++) {
            remaining[i] = othelloGrid[row - 1 - i][column + 1 + i];
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
        int possibleRight = 7 - row;
        int possibleDown = 7 - column;
        int length = Math.min(possibleRight, possibleDown);
        OthelloGrid[] remaining = new OthelloGrid[length];
        for (int i = 0; i < length; i++) {
            remaining[i] = othelloGrid[row + 1 + i][column + 1 + i];
        }
        return remaining;
    }

    private static OthelloGrid[] remainingGridsDownLeft(int row, int column, OthelloGrid[][] othelloGrid) {
        int possibleDown = 7 - row;
        int length = Math.min(column, possibleDown);
        OthelloGrid[] remaining = new OthelloGrid[length];
        for (int i = 0; i < length; i++) {
            remaining[i] = othelloGrid[row + 1 + i][column - 1 - i];
        }
        return remaining;
    }

    static Map<Integer, Map<String, Integer>> available(Turn turn, OthelloGrid[][] othelloGrid) {
        /*
         *first Integer => grid is 2 length
         * second Integer => possible grids to turn is 1 length
         */
        Map<Integer, Map<String, Integer>> availableGrids = new HashMap<>();


        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                /*
                 *it only looks for the same grids and
                 * calculate how many grids are there in 8 position of
                 * that same grid
                 */
                if (areTheSame(turn, othelloGrid[row][column])) {
                    OthelloGrid[] remaining;
                    // right
                    remaining = remainingGridsRight(column, othelloGrid[row]);
                    int right = calculateAvailableByList(turn, remaining);
                    if (right != 0) {
                        int key = row * 10 + (column + right + 1);
                        if (availableGrids.containsKey(key)) {
                            Map<String, Integer> temp = new HashMap<>(availableGrids.get(key));
                            temp.put("left", right);
                            availableGrids.put(key, temp);
                        } else {
                            Map<String, Integer> temp = Map.ofEntries(
                                    entry("left", right)
                            );
                            availableGrids.put(key, temp);
                        }
                    }

                    // left
                    remaining = remainingGridsLeft(column, othelloGrid[row]);
                    int left = calculateAvailableByList(turn, remaining);
                    if (left != 0) {
                        int key = row * 10 + (column - left - 1);
                        if (availableGrids.containsKey(key)) {
                            Map<String, Integer> temp = new HashMap<>(availableGrids.get(key));
                            temp.put("right", left);
                            availableGrids.put(key, temp);
                        } else {
                            Map<String, Integer> temp = Map.ofEntries(
                                    entry("right", left)
                            );
                            availableGrids.put(key, temp);
                        }
                    }

                    // up
                    remaining = remainingGridsUp(row, column, othelloGrid);
                    int up = calculateAvailableByList(turn, remaining);
                    if (up != 0) {
                        int key = (row - up - 1) * 10 + column;
                        if (availableGrids.containsKey(key)) {
                            Map<String, Integer> temp = new HashMap<>(availableGrids.get(key));
                            temp.put("down", up);
                            availableGrids.put(key, temp);
                        } else {
                            Map<String, Integer> temp = Map.ofEntries(
                                    entry("down", up)
                            );
                            availableGrids.put(key, temp);
                        }
                    }

                    // down
                    remaining = remainingGridsDown(row, column, othelloGrid);
                    int down = calculateAvailableByList(turn, remaining);
                    if (down != 0) {
                        int key = (row + down + 1) * 10 + column;
                        if (availableGrids.containsKey(key)) {
                            Map<String, Integer> temp = new HashMap<>(availableGrids.get(key));
                            temp.put("up", down);
                            availableGrids.put(key, temp);
                        } else {
                            Map<String, Integer> temp = Map.ofEntries(
                                    entry("up", down)
                            );
                            availableGrids.put(key, temp);
                        }
                    }

                    // upRight
                    remaining = remainingGridsUpRight(row, column, othelloGrid);
                    int upRight = calculateAvailableByList(turn, remaining);
                    if (upRight != 0) {
                        int key = (row - upRight - 1) * 10 + (column + upRight + 1);
                        if (availableGrids.containsKey(key)) {
                            Map<String, Integer> temp = new HashMap<>(availableGrids.get(key));
                            temp.put("downLeft", upRight);
                            availableGrids.put(key, temp);
                        } else {
                            Map<String, Integer> temp = Map.ofEntries(
                                    entry("downLeft", upRight)
                            );
                            availableGrids.put(key, temp);
                        }
                    }

                    // upLeft
                    remaining = remainingGridsUpLeft(row, column, othelloGrid);
                    int upLeft = calculateAvailableByList(turn, remaining);
                    if (upLeft != 0) {
                        int key = (row - upLeft - 1) * 10 + (column - upLeft - 1);
                        if (availableGrids.containsKey(key)) {
                            Map<String, Integer> temp = new HashMap<>(availableGrids.get(key));
                            temp.put("downRight", upLeft);
                            availableGrids.put(key, temp);
                        } else {
                            Map<String, Integer> temp = Map.ofEntries(
                                    entry("downRight", upLeft)
                            );
                            availableGrids.put(key, temp);
                        }
                    }

                    // downRight
                    remaining = remainingGridsDownRight(row, column, othelloGrid);
                    int downRight = calculateAvailableByList(turn, remaining);
                    if (downRight != 0) {
                        int key = (row + downRight + 1) * 10 + (column + downRight + 1);
                        if (availableGrids.containsKey(key)) {
                            Map<String, Integer> temp = new HashMap<>(availableGrids.get(key));
                            temp.put("upLeft", downRight);
                            availableGrids.put(key, temp);
                        } else {
                            Map<String, Integer> temp = Map.ofEntries(
                                    entry("upLeft", downRight)
                            );
                            availableGrids.put(key, temp);
                        }
                    }

                    // downLeft
                    remaining = remainingGridsDownLeft(row, column, othelloGrid);
                    int downLeft = calculateAvailableByList(turn, remaining);
                    if (downLeft != 0) {
                        int key = (row + downLeft + 1) * 10 + (column - downLeft - 1);
                        if (availableGrids.containsKey(key)) {
                            Map<String, Integer> temp = new HashMap<>(availableGrids.get(key));
                            temp.put("upRight", downLeft);
                            availableGrids.put(key, temp);
                        } else {
                            Map<String, Integer> temp = Map.ofEntries(
                                    entry("upRight", downLeft)
                            );
                            availableGrids.put(key, temp);
                        }
                    }

                }
            }
        }


        return availableGrids;
    }
}
