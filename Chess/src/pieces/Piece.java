package pieces;

public abstract class Piece {

    enum Color { WHITE, BLACK}

    private int posX, posY;
    private final Color color;

    public Piece(int posX, int posY, Color color){
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    public int getPosX(){return this.posX;}
    public int getPosY(){return this.posY;}
    public void setPosX(int posX){this.posX = posX;}
    public void setPosY(int posY){this.posY = posY;}


    public abstract boolean canMove(int desX, int desY);

}
