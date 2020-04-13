import java.util.Map;

enum OthelloGrid {
    NONE,
    WHITE,
    BLACK
}

enum Turn {
    WHITE,
    BLACK
}

public class Main {
    private static OthelloGrid[][] othelloGrid = new OthelloGrid[8][8];

    static {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                othelloGrid[row][column] = OthelloGrid.NONE;
            }
        }
        othelloGrid[3][3] = OthelloGrid.WHITE;
        othelloGrid[4][4] = OthelloGrid.WHITE;
        othelloGrid[3][4] = OthelloGrid.BLACK;
        othelloGrid[4][3] = OthelloGrid.BLACK;
    }

    public static void main(String[] args) {
        Turn turn = Turn.BLACK;


        while (true) {
            Map<Integer, Map<String, Integer>> available = AvailableOthelloGrid.available(turn, othelloGrid);
            OutPut.printResult(othelloGrid);
            System.out.println(turn.toString());
            InputOutPut.printOutPut(available);

            if (available.isEmpty()) {
                turn = toggleTurn(turn);
                System.out.println("Pass");
                available = AvailableOthelloGrid.available(turn, othelloGrid);
                if (available.isEmpty()) {
                    Map<Turn, Integer> temp = calculateWinner.calculate(othelloGrid);
                    int whiteTurn = temp.get(Turn.WHITE);
                    int blackTurn = temp.get(Turn.BLACK);
                    if (whiteTurn > blackTurn) {
                        System.out.println("whiteTurn is winner " + whiteTurn);
                    } else if (blackTurn > whiteTurn) {
                        System.out.println("blackTurn is winner " + blackTurn);
                    } else {
                        System.out.println("Equals " + whiteTurn);
                    }
                    break;
                }
            } else {

                int num = InputOutPut.getInput();
                if (available.containsKey(num)) {
                    othelloGrid = Insert.insertion(turn, othelloGrid, available, num);
                    turn = toggleTurn(turn);
                } else {
                    System.out.println("This Grid Is Not Available For You , Try Again");
                }
            }
        }

    }

    private static Turn toggleTurn(Turn turn) {
        if (turn == Turn.WHITE) return Turn.BLACK;
        return Turn.WHITE;
    }


}
