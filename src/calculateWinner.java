import java.util.Map;

import static java.util.Map.entry;

public class calculateWinner {
    static Map<Turn, Integer> calculate(OthelloGrid[][] othelloGrids) {
        int whiteTurn = 0;
        int blackTurn = 0;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (othelloGrids[row][column] == OthelloGrid.BLACK) {
                    blackTurn++;
                } else if (othelloGrids[row][column] == OthelloGrid.WHITE) {
                    whiteTurn++;
                }
            }
        }
        return Map.ofEntries(
                entry(Turn.BLACK, blackTurn),
                entry(Turn.WHITE, whiteTurn)
        );
    }
}
