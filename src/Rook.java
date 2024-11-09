public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (!board.checkPos(line) || !board.checkPos(column) || !board.checkPos(toLine) || !board.checkPos(toColumn)) {
            return false;
        }

        if (line == toLine && column == toColumn) {
            return false;
        }

        if (line == toLine) {
            int step = (toColumn > column) ? 1 : -1;
            for (int col = column + step; col != toColumn; col += step) {
                if (board.board[line][col] != null) return false;
            }
        } else if (column == toColumn) {
            int step = (toLine > line) ? 1 : -1;
            for (int row = line + step; row != toLine; row += step) {
                if (board.board[row][column] != null) return false;
            }
        } else {
            return false;
        }

        return board.board[toLine][toColumn] == null || !board.board[toLine][toColumn].getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}