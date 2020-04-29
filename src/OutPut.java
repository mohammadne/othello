

class OutPut {

    static void printResult(OthelloGrid[][] othelloGrids) {
        System.out.print(buildFirstRow());
        System.out.print(buildRows(othelloGrids));
    }


    private static String buildRows(OthelloGrid[][] othelloGrids) {
        StringBuilder str = new StringBuilder();
        for (int iter = 0; iter < 8; iter++) {
            str.append(buildSingleRow(iter, othelloGrids[iter]));
            str.append(buildHorizontalLine());
        }

        return str.toString();
    }

    private static String buildSingleRow(int index, OthelloGrid[] othelloGrids) {
        StringBuilder str = new StringBuilder();
        str.append(index+1);
        str.append('\t');
        str.append('|');
        for (int i = 0, n = 8; i < n; i++) {
            str.append('\t');
            if (othelloGrids[i] == OthelloGrid.BLACK) {
                str.append('\u25CB');
            } else if (othelloGrids[i] == OthelloGrid.WHITE){
                str.append('\u25CF');
            } else {
                str.append(' ');
            }
            str.append("\t|");
        }

//        str.append(buildHorizontalLine());
        return str.toString();
    }


    private static String buildFirstRow() {
        String text = "ABCDEFGH";
        StringBuilder str = new StringBuilder();
        str.append("\t\t");
        for (int i = 0, n = text.length(); i < n; i++) {
            str.append(text.charAt(i));
            str.append("\t\t");
        }
        str.append(buildHorizontalLine());
        return str.toString();
    }

    private static String buildHorizontalLine() {
        StringBuilder str = new StringBuilder();
        str.append("\n\t");
        for (int i = 0, n = 8; i < n; i++) {
            str.append("________");
        }
        str.append('\n');
        return str.toString();
    }
}
