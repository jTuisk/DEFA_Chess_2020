package com.engine;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static void loadFile(){

    }

    private static void saveFile(){

    }

    public static void loadGame(){

    }

    public static void saveGame(){

    }

    private static boolean createSaveFile(){
        try {
            File file = new File("DEFA_Chess_SaveGame.txt");
            if (file.createNewFile())
                return true;

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }
}
