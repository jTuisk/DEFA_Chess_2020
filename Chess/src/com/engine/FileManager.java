package com.engine;

import com.engine.board.Board;
import com.engine.piece.*;
import com.engine.player.Player;
import com.engine.ui.DataPanel;
import com.engine.ui.GameBoardPanel;
import com.engine.ui.UserInterface;

import java.io.*;
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

    private static Piece getPieceByChar(char c, Alliance alliance, Player player, int[] pos){
        Piece piece = null;
        switch(c){
            case 'K':
                piece = new King(alliance, player, pos);
                    break;
            case 'Q':
                piece = new Queen(alliance, player, pos);
                break;
            case 'N':
                piece = new Knight(alliance, player, pos);
                break;
            case 'B':
                piece = new Bishop(alliance, player, pos);
                break;
            case 'R':
                piece = new Rook(alliance, player, pos);
                break;
            case 'P':
                piece = new Pawn(alliance, player, pos);
                break;
        }
        return piece;
    }

    private static Alliance getAllianceByChar(char c){
        return Alliance.WHITE.getAllianceChar() == c ? Alliance.WHITE : Alliance.BLACK;
    }

    public static void loadGame(Board board, Player p1, Player p2, DataPanel dataPanel, GameBoardPanel gameBoardPanel){
        String[][] data = loadFileData();

        //Game board
        for(int x = 0; x < GameUtils.GAME_BOARD_SIZE_HEIGHT; x++){
            for(int y = 0; y < GameUtils.GAME_BOARD_SIZE_WIDTH; y++){
                int[] pos = new int[]{x,y};
                if(data[x][y].length() != 3){
                    board.getTile(pos).setPiece(null);
                    continue;
                }
                char pieceChar = data[x][y].charAt(0);
                char allianceChar = data[x][y].charAt(1);
                char playerChar = data[x][y].charAt(2);
                Player player = playerChar == '0' ? p1 : p2;
                board.getTile(pos).setPiece(getPieceByChar(pieceChar, getAllianceByChar(allianceChar), player, pos));
            }
        }
        //Lost pieces
        try{
            p1.clearLostPieces();
            p2.clearLostPieces();

            String[] p1_lostPiecesArray = data[8][0].split(",");
            String[] p2_lostPiecesArray = data[9][0].split(",");

            for(String s : p1_lostPiecesArray){
                if(s.length() != 3)
                    continue;

                char pieceChar = s.charAt(0);
                char allianceChar = s.charAt(1);
                char playerChar = s.charAt(2);
                int[] pos = new int[]{0,0};
                p1.addLostPiece(getPieceByChar(pieceChar, getAllianceByChar(allianceChar), p1, pos));
            }
            for(String s : p2_lostPiecesArray){
                if(s.length() != 3)
                    continue;

                char pieceChar = s.charAt(0);
                char allianceChar = s.charAt(1);
                char playerChar = s.charAt(2);
                int[] pos = new int[]{0,0};
                p2.addLostPiece(getPieceByChar(pieceChar, getAllianceByChar(allianceChar), p2, pos));
            }

        }catch(Exception e){

        }

        //Player Turn
        try{
            GameUtils.PLAYER_TURN = getAllianceByChar(data[10][0].charAt(0));
        }catch(Exception e){

        }

        dataPanel.refreshDataPanel();
        gameBoardPanel.refreshTiles(board);
        System.out.println(board.toString());
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
        builder.append(GameUtils.PLAYER_TURN.getAllianceChar()+";");

        return builder.toString();
    }

    private static String turnPieceDataString(Piece piece){
        String pieceChar = piece.toString();
        char allianceChar = piece.getAlliance().getAllianceChar();
        char playerChar = allianceChar == 'W' ? '0' : '1';
        return (pieceChar+allianceChar+playerChar);
    }
}

/*

KW0;KB1;;;;;;QW0;
 ; ; ; ; ; ; ; ;
 ; ; ; ; ; ; ; ;
 ; ; ; ; ; ; ; ;
 ; ; ; ; ; ; ; ;
 ; ; ; ; ; ; ; ;
 ; ; ; ; ; ; ; ;
 ; ; ; ; ; ; ; ;
 ;PW0,PW0,PW0,KW0,NW0;
 ;KB1,PB1,PB1,KB1,NB1;
B;


RW0;NW0;BW0;QW0;KW0;BW0;NW0;RW0;
PW0;PW0;PW0; ;PW0;PW0;PW0;PW0;
 ; ; ; ; ; ; ; ;
 ; ; ; ; ; ; ; ;
 ; ; ; ;PW0; ; ; ;
 ; ; ; ; ; ; ; ;
PB1;PB1;PB1;PB1; ;PB1;PB1;PB1;
RB1;NB1;BB1;QB1;KB1;BB1;NB1;RB1;
 ; ;
PB1, ;
B;

 */