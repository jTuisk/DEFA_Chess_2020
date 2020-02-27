package com.engine;

public enum Alliance {
    BLACK('B'), WHITE('W');

    public boolean isWhite(){
        return this == Alliance.WHITE;
    }

    public boolean isBlack(){
        return this == Alliance.BLACK;
    }

    private char allianceChar;

    Alliance(char allianceChar){
        this.allianceChar = allianceChar;
    }

    public char getAllianceChar(){return this.allianceChar;}
}
