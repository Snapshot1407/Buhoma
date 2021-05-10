package com.mygdx.buhoma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.buhoma.util.FontFactory;
import java.util.Locale;


public class MainMenuScreen extends BaseScreen {



    OrthographicCamera camera;



    private static float START_VERT_POSITION_FACTOR = 2.7f; // задаём позицию конпки start
    private static float EXIT_VERT_POSITION_FACTOR = 4.2f; // задаём позицию кнопки exit



///главное меню(1-экран)


    public final static float const_button_width = 0.15625f;
    public final static float const_button_height = 0.13889f;


    private Skin skin;


    private Button play;
    private Button exit;
    //необходим для util, чтоб выводить и писать на labels русскими буквами
    public Locale ruLocale;

    public MainMenuScreen( final Buhoma game) {
        super(game);

        // сцена, на которой размещаются обьекты(актеры и labels)


        Gdx.input.setInputProcessor(game.stage);



    }

    @Override
    public void show() {



        camera = new OrthographicCamera();
        camera.setToOrtho(false, Buhoma.width,Buhoma.height);

        // получаем размеры экрана устройства пользователя и записываем их в переменнные высоты и ширины
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();



        // устанавливаем переменные высоты и ширины в качестве области просмотра нашей игры
        camera = new OrthographicCamera(width, height);

        // этим методом мы центруем камеру на половину высоты и половину ширины
        camera.setToOrtho(false,Buhoma.width,Buhoma.height);



        // кожа кнопки
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        //локатор русского языка
        ruLocale = new Locale("ru", "RU");

        // функция отвечающая за инициилизацию русских букв
        FontFactory.getInstance().initialize();

        //отрисовка кнопок и их надписей
        System.out.println(width+' '+height);


        //создание кнопок
        play = new Button(skin);
        exit = new Button(skin);


        //Setsize button
        play.setSize(width*const_button_width,height*const_button_height);
        exit.setSize(width*const_button_height,height*const_button_height);

        //setPosition button
        play.setPosition((width/2f - play.getWidth()/2) ,height - (height/4) - (play.getHeight()/2));
        exit.setPosition((width/2f - exit.getWidth()/2), (height/4) - (play.getHeight()/2));


        //повесили слушателей
        play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                play.remove(); exit.remove();
                game.setScreen(game.gameScreen);

            }
        });


        exit.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });




        //создание внутреннего label
        Label label_Play = new Label("", new Label.LabelStyle(FontFactory.getInstance().getFont(ruLocale), new Color(1, 1, 1, 1)));
        Label label_Exit = new Label("", new Label.LabelStyle(FontFactory.getInstance().getFont(ruLocale), new Color(1, 1, 1, 1)));



        //установка позиций
        label_Play.setPosition((width/2f - play.getWidth()/3) ,height - (height/4) - (label_Play.getHeight()/2));
        label_Exit.setPosition((width/2f -exit.getWidth()/3), (height/4) - (label_Exit.getHeight()/2));

        //значение строк
        label_Play.setText("Играть");
        label_Exit.setText("Выход");


        // добавление объектов на сцеену

        game.stage.addActor(play);
        game.stage.addActor(label_Play);

        game.stage.addActor(exit);
        game.stage.addActor(label_Exit);

    }

    @Override
    public void render(float delta) {

        //рендер





        camera.update();
        //game.batch.setProjectionMatrix(camera.combined);



        // запуск метода act актеров, которые были добавлены в сцену
        game.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        // прорисовка сцены
        game.stage.draw();

    }

    @Override
    public void resize(float width, float height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {//не забывай!!!
        game.stage.dispose();

    }
}
