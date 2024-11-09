public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (!checkPos(startLine) || !checkPos(startColumn) || board[startLine][startColumn] == null) {
            return false;
        }

        ChessPiece piece = board[startLine][startColumn];

        if (!nowPlayer.equals(piece.getColor())) {
            return false;
        }

        if (piece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
            board[endLine][endColumn] = piece;
            board[startLine][startColumn] = null;
            this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

            if (piece instanceof King || piece instanceof Rook) {
                piece.check = false;
            }

            return true;
        }
        return false;
    }

    public boolean castling0() {
        if ("White".equals(nowPlayer)) {
            return castlingHelper(0, 0, 4, 0, 2, 3);  // Рокировка для белых
        } else if ("Black".equals(nowPlayer)) {
            return castlingHelper(7, 0, 4, 7, 2, 3);  // Рокировка для черных
        }
        return false;
    }

    public boolean castling7() {
        if ("White".equals(nowPlayer)) {
            return castlingHelper(0, 7, 4, 0, 6, 5);  // Рокировка для белых
        } else if ("Black".equals(nowPlayer)) {
            return castlingHelper(7, 7, 4, 7, 6, 5);  // Рокировка для черных
        }
        return false;
    }

    private boolean castlingHelper(int rookRow, int rookCol, int kingCol, int row, int newKingCol, int newRookCol) {
        if (board[row][kingCol] instanceof King && board[row][rookCol] instanceof Rook) {
            King king = (King) board[row][kingCol];
            Rook rook = (Rook) board[row][rookCol];

            if (king.check && rook.check && isPathClear(row, kingCol, rookCol) && !king.isUnderAttack(this, row, kingCol)) {
                board[row][newKingCol] = king;
                board[row][newRookCol] = rook;
                board[row][kingCol] = null;
                board[row][rookCol] = null;

                king.check = false;
                rook.check = false;

                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            }
        }
        return false;
    }

    private boolean isPathClear(int row, int startCol, int endCol) {
        int step = startCol < endCol ? 1 : -1;
        for (int col = startCol + step; col != endCol; col += step) {
            if (board[row][col] != null) {
                return false;
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println("Очередь " + nowPlayer);
        System.out.println();
        System.out.println("Игрок 2 (Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Игрок 1 (White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}