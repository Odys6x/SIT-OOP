package com.mygdx.game.SceneMgmt;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class    SceneManager {
    private SceneScreen currentScene;
    private static SceneManager instance;
    private Game game;

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        System.out.println("instance" + instance);
        return instance;
    }
        public void setGame(Game game) {
        this.game = game;
    }

    public void setScene(SceneScreen scene) {
        Gdx.app.log("SceneManager", "Attempting to set new scene."); // error handling
        if (game == null) {
            Gdx.app.log("SceneManager", "Game instance is null. Cannot change screen."); // also error handling
        }
        if (currentScene != null) {
            currentScene.dispose();
        }
        currentScene = scene;
        currentScene.show();
        game.setScreen(currentScene); 
    }
    public void render(float delta){
        if (currentScene != null){
            currentScene.render(delta);
        }
    }
    public void dispose() {
        if (currentScene != null){
            currentScene.dispose();
        }
    }
    private SceneManager() {
    }

}
    