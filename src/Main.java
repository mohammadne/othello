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
        othelloGrid[3][3] = OthelloGrid.BLACK;
        othelloGrid[4][4] = OthelloGrid.BLACK;
        othelloGrid[3][4] = OthelloGrid.WHITE;
        othelloGrid[4][3] = OthelloGrid.WHITE;
    }

    public static void main(String[] args) {
        Turn turn = Turn.WHITE;
        Map<Integer, Integer> avilables = AvailableOthelloGrid.available(turn, othelloGrid);
//        while (true) {
//            if (true) {
//                Scanner input = new Scanner(System.in);
//            } else {
//                if (true){
//
//                } else {
//                    System.out.println("برنده کیست ؟");
//                    break;
//                }
//            }
//        }


        OutPut a = new OutPut(othelloGrid);
        a.printResult();
    }
}
