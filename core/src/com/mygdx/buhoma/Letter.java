package com.mygdx.buhoma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Letter extends Actor {
    public int type;
    public float x,y;
    public float height,width;
    final float const_width = 0.0390625f;
    final float const_height = 0.0694444f;
    boolean increase = true;
    boolean snd = true ;
    boolean touche = true;
    boolean marker = false;


    String screen;

    Texture imgABC[] = new Texture[33];
    Sound[] sndABC = new Sound[33];


    public float dx,dy;
    private float move_x, move_y;

    public boolean alive = true;
    //перестроить летер  на циркл

    public Letter(){
        height = Buhoma.height * const_height ;
        width = Buhoma.width * const_width;
        dx = MathUtils.random(1,2);
        dy = MathUtils.random(1,2);
        for (int i =0;i<imgABC.length;i++)imgABC[i] = new Texture(""+i+".png");
        for (int i = 0; i < sndABC.length; i++) sndABC[i] = Gdx.audio.newSound(Gdx.files.internal(i+".mp3"));
    }

    public boolean isAlive(){
        return alive;
    }

    boolean isIncrease() {return increase;}

    public void setStringScreen(String s){screen = s;}

    public void setIncrease(boolean increase){this.increase = increase;}

    boolean isTouche() { return touche;}

    boolean isSnd(){ return snd; }
    boolean isMarker(){return marker;}

    public void act(){
        x += dx;
        y += dy;
        if (screen == "Game"){
            if (x> Buhoma.width-width|| x<0)dx=-dx;
            if (y>Buhoma.height-height ||y<100)dy=-dy;}
        if (screen == "Quest"){
            if (x> Buhoma.width * QuestScreen.const_Width_P + (Buhoma.width * QuestScreen.const_Width_S) - width   || x < Buhoma.width * QuestScreen.const_Width_S )dx=-dx;
            if (y>Buhoma.height * QuestScreen.const_Height_P + (Buhoma.height * QuestScreen.const_Height_S) - height || y < Buhoma.height * QuestScreen.const_Height_S)dy=-dy;
        }
    }


    public void hit(float t_x,float t_y){
        if (t_x>x && t_x<x+width && t_y>y &&t_y<y+height) setAlive(false);
    }

    void increase(float n){
        width +=  n;
        height += n;

    }

    void setType(int n){
        type = n;
    }
    void setMarker(boolean marker){this.marker = marker;}

    public void setTouche(boolean touche){this.touche = touche;}

    void stop(){
        move_x = dx;
        move_y = dy;
        dx = dy = 0;
        setAlive(false);
        setIncrease(false);
        setTouche(false);
        setSnd(false);


    }

    void resume(){
        dx = move_x;
        dy = move_y;
        setAlive(true);
        setIncrease(true);
        setTouche(true);
        setSnd(true);

    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    void setSnd(boolean snd){
        this.snd = snd;
    }


    public void draw (Batch batch, float parentAlpha){

        super.draw(batch, parentAlpha);
        if (alive && increase && touche || marker)
            batch.draw(imgABC[type], x, y, width, height);
    }



    void playSnd(){
        sndABC[type].play();
    }


}
