package com.mygdx.game.EntityMgmt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

class TexturedObject extends Entity implements CreateTexture{
    private Texture tex;
    private String path;
    private boolean isUserControlled;
    private AIControllerManager aiControllerManager;

    TexturedObject() {

    }

    TexturedObject(String path, float x, float y, float speed, Boolean isUserControlled, AIControllerManager aiControllerManager) {
        super(x, y, Color.BLACK, speed);
        this.tex = new Texture(path);
        this.path = path;
        this.isUserControlled = isUserControlled;
        this.aiControllerManager = aiControllerManager;
    }

    @Override
    public float getWidth() {
        return tex.getWidth();
    }

    @Override
    public float getHeight() {
        return tex.getHeight();
    }

    @Override
    void draw(ShapeRenderer shape) {
    }

    public boolean isUserControlled() {
        return isUserControlled;
    }
    public void setUserControlled(boolean isUserControlled) {
        this.isUserControlled = isUserControlled;
    }

    public void moveAIControlled() {
        float newY = getY() - getSpeed() * Gdx.graphics.getDeltaTime();
        setY(newY);

        if (getY() <= 0) {
            float newSpeed = (float)(Math.random() * getSpeed()) + 50;
            float newX = (float)(Math.random() * Gdx.graphics.getWidth());
            setY(Gdx.graphics.getHeight());
            setX(newX);
            setSpeed(newSpeed);
        }
    }

    public void moveUserControlled() {
    }


    void update() {
        System.out.println("TexturedObject with texture at " + path + " is at position (" + getX() + ", " + getY() + ")");
    }


    @Override
    void draw(SpriteBatch batch) {
        batch.draw(getTexture(), getX(), getY(), getTexture().getWidth(),getTexture().getHeight());
    }
    Texture getTexture() {
        return tex;
    }

    TexturedObject createText(){
        return new TexturedObject("bucket.png", 300, 0, 200, true, aiControllerManager);
    }

    TexturedObject createDrop(AIControllerManager aiControllerManager){
        float initialX = (float) (Math.random() * Gdx.graphics.getWidth());
        return new TexturedObject("droplet.png", initialX, 400, 400, false, aiControllerManager);
    }
    

    void setTexture(Texture tex) {
        this.tex = tex;
    }

    String getPath() {
        return path;
    }
    void setPath(String path) {
        this.path = path;
    }

    void dispose() {
        tex.dispose();
    }


    @Override
    public Entity createTexture(int number) {
        if (number == 1){
            return createText();
        }
        else if (number == 2) {
           return createDrop(aiControllerManager);
        }
        return null;
    }
}