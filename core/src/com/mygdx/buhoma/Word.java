package com.mygdx.buhoma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Word extends Actor {
    public int type;
    Texture imgWord[] = new Texture[59];
    Sound[] sounds = new Sound[59];

    boolean playSnd=true;

    float x, y, width, height;

    boolean label;


    public Word(){
        for (int i = 0; i< imgWord.length; i++) {
            if (i<6) imgWord[i] = new Texture("fon2.png");
            else imgWord[i] = new Texture(""+i+".jpg");
        }
        for (int i = 0; i < sounds.length; i++) sounds[i] = Gdx.audio.newSound(Gdx.files.internal("lp"+i+".mp3"));
        label = false;
    }

    public boolean isLabel() {return label;}

    public void setLabel(boolean label){
        this.label = label;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setSize(float width, float height){
        this.width = width;
        this.height = height;
    }

    public void draw(Batch batch, float parentAlpha){

        super.draw(batch, parentAlpha);
        batch.draw(imgWord[type], x, y, width, height);
    }

    void setType(int type){
        this.type=type; }
    void playSnd(){
        sounds[type].play();
    }

}
