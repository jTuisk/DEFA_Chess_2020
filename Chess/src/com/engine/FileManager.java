package com.engine;

import com.engine.board.Board;
import com.engine.piece.*;
import com.engine.player.Player;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileManager {

    private static String fileName = "DEFA_Chess_SaveGame.txt";

    private static String[][] loadFileData(){
        String[][] data = new String[11][8];
        File file = new File(fileName);
        try{
            Scanner scanner = new Scanner(file);
            int line = 0;
            while(scanner.hasNextLine()){
                String currentLine = scanner.nextLine();
                String[] lineData = currentLine.split(";");
                data[line] = lineData;
                line++;
            }
            System.out.println("loaded file data!");
        }catch(IOException e){
            System.out.println("Saved game file not found!");
            return null;
        }
        for(String[] s : data)
            System.out.println(Arrays.toString(s));

        return data;
    }

    private static Piece getPieceByChar(Board board, char c, Alliance alliance, Player player, int[] pos, boolean firstMove){
        Piece piece = null;
        switch(c){
            case 'K':
                piece = new King(board, alliance, player, pos, firstMove);
                    break;
            case 'Q':
                piece = new Queen(board, alliance, player, pos, firstMove);
                    break;
            case 'N':
                piece = new Knight(board, alliance, player, pos, firstMove);
                    break;
            case 'B':
                piece = new Bishop(board, alliance, player, pos, firstMove);
                    break;
            case 'R':
                piece = new Rook(board, alliance, player, pos, firstMove);
                    break;
            case 'P':
                piece = new Pawn(board, alliance, player, pos, firstMove);
                    break;
        }
        return piece;
    }

    private static Alliance getAllianceByChar(char c){
        return Alliance.WHITE.getAllianceChar() == c ? Alliance.WHITE : Alliance.BLACK;
    }

    private static boolean charToBoolean(char c){
        return c == '1';
    }

    public static void loadGame(Board board){
        String[][] loadedData = loadFileData();

        Board f_board = board.getFutureBoard();

        GameStatus loadedGameStatus = null;
        Alliance loadedPlayerTurn = null;
        int[] lastMovedPiecePosition = new int[2];

        board.getPlayer1().getPlayerPieces().clear();
        board.getPlayer1().getLostPieces().clear();
        board.getPlayer2().getPlayerPieces().clear();
        board.getPlayer2().getLostPieces().clear();

        ArrayList<Piece> loadedLostPiecesPlayer1 = new ArrayList<>();
        ArrayList<Piece> loadedLostPiecesPlayer2 = new ArrayList<>();

        //Game board tiles
        try{
            for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
                for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                    int[] tilePos = new int[]{x,y};


                    if(loadedData[x][y].length() != 4){
                        board.getTile(tilePos).setPiece(null);
                        f_board.getTile(tilePos).setPiece(null);
                        continue;
                    }
                    char pieceChar = loadedData[x][y].charAt(0);
                    char allianceChar = loadedData[x][y].charAt(1);
                    char playerChar = loadedData[x][y].charAt(2);
                    char pieceFirstMoveChar = loadedData[x][y].charAt(3);

                    Player player = playerChar == '0' ? board.getPlayer1() : board.getPlayer2();
                    Player f_player =  playerChar == '0' ? f_board.getPlayer1() : f_board.getPlayer2();

                    Piece piece = getPieceByChar(board, pieceChar, getAllianceByChar(allianceChar), player, tilePos, charToBoolean(pieceFirstMoveChar));
                    Piece f_piece = getPieceByChar(f_board, pieceChar, getAllianceByChar(allianceChar), f_player, tilePos, charToBoolean(pieceFirstMoveChar));

                    board.getTile(tilePos).setPiece(piece);
                    f_board.getTile(tilePos).setPiece(f_piece);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error loading game! Please make sure you have valid save game",  "Error!", JOptionPane.WARNING_MESSAGE);
            System.out.println(e.getMessage());
            return;
        }

        //Lost pieces
        try{
            String[] player1_lostPiecesArray = loadedData[8][0].split(","); //save to same string?
            String[] player2_lostPiecesArray = loadedData[9][0].split(",");

            for(String pieceString : player1_lostPiecesArray){
                if(pieceString.length() != 4)
                    continue;;
                char pieceChar = pieceString.charAt(0);
                char allianceChar = pieceString.charAt(1);
                char playerChar = pieceString.charAt(2);
                char pieceFirstMoveChar = pieceString.charAt(3);
                int[] piecePos = new int[] {0,0};
                Piece piece = getPieceByChar(board, pieceChar, getAllianceByChar(allianceChar), board.getPlayer1(), piecePos, charToBoolean(pieceFirstMoveChar));
                loadedLostPiecesPlayer1.add(piece);
            }

            for(String pieceString : player2_lostPiecesArray){
                if(pieceString.length() != 4)
                    continue;;
                char pieceChar = pieceString.charAt(0);
                char allianceChar = pieceString.charAt(1);
                char playerChar = pieceString.charAt(2);
                char pieceFirstMoveChar = pieceString.charAt(3);
                int[] piecePos = new int[] {0,0};

                Piece piece = getPieceByChar(board, pieceChar, getAllianceByChar(allianceChar), board.getPlayer2(), piecePos, charToBoolean(pieceFirstMoveChar));
                loadedLostPiecesPlayer2.add(piece);
            }
        }catch(Exception e){
            System.out.println("Loading game error! - Lost pieces");
            System.out.println(e.getMessage());
            return;
        }
        //Player Turn
        try{
           loadedPlayerTurn =getAllianceByChar(loadedData[10][0].charAt(0));
        }catch(Exception e){
            System.out.println("Loading game error! - Player turn");
            System.out.println(e.getMessage());
            return;
        }
        //Game Status
        try{
            loadedGameStatus = GameStatus.getGameStatusByChar(loadedData[10][1].charAt(0));
        }catch(Exception e){
            System.out.println("Loading game error! - Game status");
            System.out.println(e.getMessage());
            return;
        }

        //Last moved piece
        try{
            System.out.println();
            lastMovedPiecePosition[0] = Integer.valueOf(""+loadedData[10][2].charAt(0));
            lastMovedPiecePosition[1] = Integer.valueOf(""+loadedData[10][2].charAt(1));
        }catch(Exception e){
            lastMovedPiecePosition = null;
            System.out.println("Loading game error! - Last moved piece");
            System.out.println(e.getMessage());
            return;
        }


        board.getPlayer1().setLostPieces(loadedLostPiecesPlayer1);
        board.getPlayer2().setLostPieces(loadedLostPiecesPlayer2);
        f_board.getPlayer1().setLostPieces(loadedLostPiecesPlayer1);
        f_board.getPlayer2().setLostPieces(loadedLostPiecesPlayer2);
        board.recalculatePlayerPieces();
        f_board.recalculatePlayerPieces();
        Piece lastMovedPiece = board.getTile(lastMovedPiecePosition).getPiece();
        Piece f_lastMovedPiece = f_board.getTile(lastMovedPiecePosition).getPiece();
        board.setLastMovedPiece(lastMovedPiece);
        f_board.setLastMovedPiece(f_lastMovedPiece);
        board.setGameStatus(loadedGameStatus);
        board.setPlayerTurn(loadedPlayerTurn);

        System.out.println(board.getLastMovedPiece());
        board.refreshUI();
        JOptionPane.showMessageDialog(null, "Game loaded",  "Game loaded", JOptionPane.WARNING_MESSAGE);
    }

    public static void saveGame(Board board, Player p1, Player p2){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            writer.write(gameDataInString(board, p1 ,p2));
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    private static String gameDataInString(Board board, Player p1, Player p2){
        final StringBuilder builder = new StringBuilder();

        //Game board pieces
        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++) {
            for (int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++) {
                int[] pos = new int[]{x, y};
                if (!board.getTile(pos).isEmpty()) {
                    Piece piece = board.getTile(pos).getPiece();
                    builder.append(turnPieceDataString(piece)+";");
                } else {
                    builder.append(" ;");
                }
            }
            builder.append("\n");
        }
        //Lost pieces
        if(p1.getLostPieces().size() > 0){
            for(int i = 0; i < p1.getLostPieces().size(); i++){
                String comma = (i < p1.getLostPieces().size()-1) ? "," : ";";
                builder.append(turnPieceDataString(p1.getLostPieces().get(i))+comma);
            }
        }else
            builder.append(" ;");
        builder.append("\n");

        if(p2.getLostPieces().size() > 0){
            for(int i = 0; i < p2.getLostPieces().size(); i++){
                String comma = (i < p2.getLostPieces().size()-1) ? "," : ";";
                builder.append(turnPieceDataString(p2.getLostPieces().get(i))+comma);
            }
        }else
            builder.append(" ;");
        builder.append("\n");

        //Player turn
        builder.append(board.getPlayerTurn().getAllianceChar()+";");
        //Game status
        builder.append(board.getGameStatus()+";");
        //Last moved piece
        builder.append(board.getLastMovedPiece().getPosition()[0]+""+board.getLastMovedPiece().getPosition()[1]+";");

        return builder.toString();
    }

    private static String turnPieceDataString(Piece piece){
        String pieceChar = piece.toString();
        char allianceChar = piece.getAlliance().getAllianceChar();
        char playerChar = allianceChar == 'W' ? '0' : '1';
        char firstMoveChar = piece.getFirstMove() ? '1' : '0';
        return (pieceChar+allianceChar+playerChar+firstMoveChar);
    }
}