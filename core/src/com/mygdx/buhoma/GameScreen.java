package com.mygdx.buhoma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.buhoma.util.FontFactory;
import java.util.Locale;


public class GameScreen extends BaseScreen {
// игровое меню(2-экран)

    final Buhoma game;
    OrthographicCamera camera;





    //сначала пробую сделать слова которые вылетят(59)слов
    public int[][] k = {{13,0,13,0}, {16,0,16,0}, {1,17,0,19},{18,5,18,19,17,0},{4,32,4,32},{19,6,19,32},//мама.папа.брат.сестра.дядя.тётя-
            {0,17,1,20,8}, {0,2,19,15,1,20,18}, {0,1,17,9,11,15,18},{0,16,5,12,29,18,9,14},{0,13,5,19,9,18,19},{0,9,18,19},//арбуз.автобус.абрикос.апельсин.аметист.аист-
            {1,0,14,0,14},{1,0,17,0,14},{1,0,19,15,14},{1,0,32,14},{1,5,12,11,0},//банан.баран.батон.баян.белка-
            {2,15,17,15,14,0},{2,5,14,9,11},{2,5,19,11,0},{2,5,12,15,18,9,16,5,19},{2,5,17,1,12,31,4},//ворона.веник.ветка.велосипет.верблюд-
            {4,5,17,5,2,15},{4,17,15,2,0},{4,32,19,5,12},{4,28,14,32},{4,2,5,17,29},//дерево.дрова.дятел.дыня.дверь-
            {5,14,15,19},{5,7,5,2,9,11,0},{5,4,9,14,15,17,15,3},{6,12,11,0},{6,7},//енот.ежевика.единорог.ёлка.ёж-
            {18,0,13,15,12,6,19},{18,12,15,14},{18,15,1,0,11,0},{18,0,13,15,2,0,17},{18,0,12,31,19},//самолёт.слон.собака.самовар.салют-
            {16,5,19,20,22},{16,9,14,3,2,9,14},{16,0,20,11},{16,5,17,5,23},{16,15,13,9,4,15,17},{16,0,2,12,9,14},//петух.пингвин.паук.перец.помидор.павлин-
            {24,0,25,11,0},{24,0,18,28},{24,5,17,5,16,0,22,0},{24,5,13,15,4,0,14},{24,5,18,14,15,11},{24,0,10,11,0},//чашка.часы.черепаха.чемодан.чеснок.чайка-
            {17,0,11},{17,28,1,0},{17,0,11,20,25,11,0},{17,0,11,5,19,0},{17,15,3,0,19,11,0},{17,5,16,0},{17,28,18,29},{17,15,8,0},{17,15,13,0,25,11,0},{17,20,11,0},//рак.рыба.ракушка.ракета.рогатка.репа.рысь.роза.ромашка.рука-*/


    };//(1-слово,2-буквы)
    //в идеале масив который должен работать
    int [][][] twoDimArray = {{{5,7,3,17}, {7,0,1,12}, {8,1,2,3}},{{5,7,3,17}, {7,0,1,12}, {8,1,2,3}},{{5,7,3,17}, {7,0,1,12}, {8,1,2,3}},{{5,7,3,17}, {7,0,1,12}, {8,1,2,3}}};//1-обозначет тематику,2-слово,3-буквы
    //переменные для таймера спавна
    long timeGame= TimeUtils.millis(),timeSpawn=3000,timeLastSpawn=TimeUtils.millis()-4000;
    //переменная для количества букв в слове
    public int count_i=0, count_m = MathUtils.random(k.length-1);// переменная для выбора случайного слова
    //массив буквы рандомной
    Array<Integer> arrayLetter = new Array<>();
    //переменная для подсчета сколько букв удалили
    int count_num=0;
    //проверяет все ли буквы вылетели
    boolean bolExited=false;
    //рабочии инструменты
    Vector3 touchPos;

    //рабочии объекты
    Letter letter[] = new  Letter[k[count_m].length];//создает массив нужного размера для слова(которое рандомом выбрали)
    Texture imgTeremok,imgFon;
    Texture imgABC[] = new Texture[33];
    Image terem, fon;

    private Skin skin;
    public Locale ruLocale;
    private Button exit_to_menu;

    Texture imgSun;
    Image sun;

    final float const_sun = 0.29166666666666666666666666666667f;

    public GameScreen(final Buhoma game){
        super(game);
        this.game = game;
        // создает камеру
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()  );
        //создаем рабочии инструменты
        touchPos = new Vector3();



        //загружаем картинки







    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(game.stage);

        imgTeremok=new Texture("teremok.png");
        TextureRegion img = new TextureRegion(imgTeremok,imgTeremok.getWidth(),imgTeremok.getHeight());
        terem = new Image(img);

        imgFon = new Texture("fon1.png");
        img = new TextureRegion(imgFon,imgFon.getWidth(),imgFon.getHeight());
        fon = new Image(img);

        imgSun = new Texture("sun.png");
        img = new TextureRegion(imgSun,imgSun.getWidth(),imgSun.getHeight());
        sun = new Image(img);

        for (int i =0;i<imgABC.length;i++)imgABC[i] = new Texture(""+i+".png");
        //загружаем буквы в список слова
        for (int n=0;n<k[count_m].length;n++) {
            arrayLetter.add(k[count_m][n]);

        }


        fon.setSize(game.width,game.height);
        terem.setPosition(-game.width/15 ,(game.height/33*11));
        terem.setSize( game.width/2, game.height/2);

        float sun_size = ((Buhoma.width<Buhoma.height)?Buhoma.width:Buhoma.height)*const_sun;
        sun.setSize(sun_size,sun_size);
        sun.setPosition(Buhoma.width-sun_size, Buhoma.height-sun_size);


        game.stage.addActor(fon);
        game.stage.addActor(terem);
        game.stage.addActor(sun);


        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        //локатор русского языка
        ruLocale = new Locale("ru", "RU");

        // функция отвечающая за инициилизацию русских букв
        FontFactory.getInstance().initialize();
        //создание кнопок

        exit_to_menu = new Button(skin);


        //Setsize button

        exit_to_menu.setSize(Buhoma.width*MainMenuScreen.const_button_width,Buhoma.height*MainMenuScreen.const_button_height);

        //setPosition button

        exit_to_menu.setPosition(Buhoma.width - exit_to_menu.getWidth(),0);





        exit_to_menu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });




        //создание внутреннего label
        Label label_Exit = new Label("", new Label.LabelStyle(FontFactory.getInstance().getFont(ruLocale), new Color(1, 1, 1, 1)));


        //установка позиций

        label_Exit.setPosition(Buhoma.width - exit_to_menu.getWidth()/1.25f, exit_to_menu.getHeight()/2);

        //значение строк

        label_Exit.setText("Выход");


        // добавление объектов на сцеену


        game.stage.addActor(exit_to_menu);
        game.stage.addActor(label_Exit);

    }

    @Override
    public void render(float delta) {

        //System.out.println("count_m="+count_m);
        // для обнуления подсчета мертых
        count_num=0;
        //обработка касания (получения x и y при нажатие на экран)
        if(Gdx.input.justTouched()){
            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);
        }
        /*
        System.out.println("touchPos.x"+touchPos.x);
        System.out.println("touchPos.y"+touchPos.y);

        for (int n=0;n<arrayLetter.size;n++) System.out.println("arrayLetter="+arrayLetter.get(n));
        //*/
        timeGame = TimeUtils.millis();
        //создание буквы по таймеру игровому (реализация через список слова ,вылет букв слова в рандомном порядке)
        if (0 < arrayLetter.size && (timeGame - timeLastSpawn >timeSpawn)){
            int num = MathUtils.random(0,arrayLetter.size-1);
            letter[count_i] = new Letter();
            letter[count_i].setPosition(400,400);
            letter[count_i].setType(arrayLetter.get(num));
            letter[count_i].setStringScreen("Game");
            game.stage.addActor(letter[count_i]);
            arrayLetter.removeIndex(num);
            timeLastSpawn=TimeUtils.millis();
            count_i++;


        }
        //проверяет все ли буквы вылетили если нет, то продолжаем работу
        if (arrayLetter.size==0)bolExited=true;


        //метод отвечающий за движения букв
        for (int n = 0 ; count_i > n ; n++)letter[n].act();
        //метод который проверяет жива ли буква
        for (int n=0;count_i>n;n++) {
            letter[n].hit(touchPos.x,touchPos.y);
            //System.out.println(letter[n].isAlive());
            if(letter[n].isAlive())
                count_num++;
        }
        //переключение на квестовое окно


        /*
        if (touchPos.x<Buhoma.width && touchPos.x>Buhoma.width-exit.getWidth() && touchPos.y>0 && touchPos.y<exit.getHeight()) {
            Gdx.app.exit();
            touchPos.set(-10,-10,0);
        }*/


        if (bolExited && count_num == 0){
            count_i = 0;
            exit_to_menu.remove();
            game.questScreen.massive(k[count_m]);
            game.questScreen.cntWord(count_m);

            for (int i = 0; i < letter.length; i++) {
                letter[i].remove();
            }

            game.setScreen(game.questScreen);

            touchPos.set(-20,-20,0);
            //тут прописать коды для полного обновления окна
            bolExited = false;

            //Ислям хотел вызвездиться, но не получилось
            //for( int i = 0; i < k[count_m].length;i++)k[count_m][i] = -1;
            count_m = MathUtils.random(k.length-1);
            //if (count_m )
            letter = new Letter[k[count_m].length];//создает массив нужного размера для слова(которое рандомом выбрали)
            //загружаем буквы в список слова
            for (int n=0;n<k[count_m].length;n++)arrayLetter.add(k[count_m][n]);
        }

        /*System.out.println("count_i="+count_i);
        System.out.println("count_num="+count_num);
        //
        for (int n=0;count_i>n;n++) System.out.println("letter["+n+"].isAlive="+letter[n].isAlive());
        //
        for (int n=0;arrayLetter.size>n;n++) System.out.println("arrayLetter["+n+"]="+arrayLetter.get(n));
        //что-то делаем с камерой*/

        camera.update();



        //отрисовка изображений
        game.stage.act();
        game.stage.draw();

        //методы которые отвечаю за процессы в сцене (определение не точное)

        touchPos.set(-10,-10,0);
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
    public void dispose() {// не забывай!!!
        game.stage.dispose();


    }
}
