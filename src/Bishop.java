public class Bishop extends ChessPiece {

    public Bishop(String color) {
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

        if (Math.abs(line - toLine) == Math.abs(column - toColumn)) {
            int stepLine = (toLine > line) ? 1 : -1;
            int stepColumn = (toColumn > column) ? 1 : -1;

            int row = line + stepLine, col = column + stepColumn;
            while (row != toLine && col != toColumn) {
                if (board.board[row][col] != null) return false;
                row += stepLine;
                col += stepColumn;
            }

            return board.board[toLine][toColumn] == null || !board.board[toLine][toColumn].getColor().equals(this.color);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}