public class Queen extends ChessPiece {

    public Queen(String color) {
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

        if (line == toLine || column == toColumn) {
            int stepLine = (line == toLine) ? 0 : (toLine > line ? 1 : -1);
            int stepColumn = (column == toColumn) ? 0 : (toColumn > column ? 1 : -1);

            int row = line + stepLine, col = column + stepColumn;
            while (row != toLine || col != toColumn) {
                if (board.board[row][col] != null) return false;
                row += stepLine;
                col += stepColumn;
            }
        } else if (Math.abs(line - toLine) == Math.abs(column - toColumn)) {
            int stepLine = (toLine > line) ? 1 : -1;
            int stepColumn = (toColumn > column) ? 1 : -1;

            int row = line + stepLine, col = column + stepColumn;
            while (row != toLine && col != toColumn) {
                if (board.board[row][col] != null) return false;
                row += stepLine;
                col += stepColumn;
            }
        } else {
            return false;
        }

        return board.board[toLine][toColumn] == null || !board.board[toLine][toColumn].getColor().equals(this.color);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}