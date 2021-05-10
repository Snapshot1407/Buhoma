package com.mygdx.buhoma;

import com.badlogic.gdx.Screen;


public abstract class BaseScreen implements Screen {
// его необходимо наследовать при создании новых экранов
    public Buhoma game;

    public BaseScreen(Buhoma game) {
        this.game = game;

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    public  void resize(int width, int height){

    }


    public abstract void resize(float width, float height);

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}