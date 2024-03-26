package com.mygdx.game.EntityMgmt.Player;


import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.EntityMgmt.Appliances.Appliance;
import com.mygdx.game.EntityMgmt.Entity;
import com.mygdx.game.EntityMgmt.GameObjectType;

public class Player extends Entity {
    private float speed;
    private Animation<TextureRegion> walkAnimationForward,walkAnimationBackward,standAnimation;
    private float stateTime;
    private String direction;
    private float score;
    private float hp;
    private int energyLevel;
    private float Iradius;


    public Player(){
        stateTime = 0f;
    }

    public Player(String path, float x, float y, float speed,String direction,float score){
        super(path, x, y);
        this.speed = speed;
        this.direction = direction;
        this.energyLevel = 100;
        this.hp = 1000;
        this.score = score;
        initialisePlayer();
    }
    public void updateAnimations(List<Integer> pressedKeys) {
        if (pressedKeys.contains(Keys.LEFT)) {
            direction = "LEFT";
        } else if (pressedKeys.contains(Keys.RIGHT)) {
            direction = "RIGHT";
        } else if (pressedKeys.contains(Keys.DOWN)) {
            direction = "DOWN";
        } else if (pressedKeys.contains(Keys.UP)) {
            direction = "UP";
        } else {
            direction = null;
        }
    }

    public void initialisePlayer(){
        Texture spriteSheet = new Texture(getPath());
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 10, spriteSheet.getHeight() / 2);
        TextureRegion[] framesForward = new TextureRegion[9]; // Frames for walking forward
        TextureRegion[] framesBackward = new TextureRegion[9]; // Frames for walking backward
        System.arraycopy(tmp[0], 1, framesForward, 0, 9); // Start from index 1 for walking forward
        System.arraycopy(tmp[1], 1, framesBackward, 0, 9); // Start from index 1 for walking backward
        walkAnimationForward = new Animation<>(0.1f, framesForward); // Animation for walking forward
        walkAnimationBackward = new Animation<>(0.1f, framesBackward);
        standAnimation = new Animation<>(0.1f, tmp[0][0]); // Standing frame
    }

    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getScore(){
        return score;
    }

    public void setScore(float score){
        this.score += score;
    }

    public void reduceScore(float score){
        this.score -= score;
    }
    public String getDirection(){
        return direction;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }

    public float getEnergy(){
        return energyLevel;
    }
    public void setEnergy(float energy){
        energyLevel -= energy;
    }

    @Override
    public float getHeight() {
        return super.getHeight()/2;
    }

    @Override
    public float getWidth() {
        return super.getWidth()/10;
    }


    @Override
    public void draw(SpriteBatch batch) {
        // Get the current frame of the animation
        stateTime += Gdx.graphics.getDeltaTime(); // Update the state time
        TextureRegion currentFrame = null;
        if(direction == "RIGHT"){
            currentFrame = walkAnimationForward.getKeyFrame(stateTime, true);
            System.out.println("RIGHT ANI");
        }
        else if(direction == "LEFT"){
            currentFrame = walkAnimationBackward.getKeyFrame(stateTime, true);
            System.out.println("LEFT ANI");
        }
        else {
            currentFrame = standAnimation.getKeyFrame(stateTime, true);
        }
        // Draw the current frame
        batch.draw(currentFrame, getX(), getY());
    }

    public void turnOffAppliance(Appliance appliance) {
        // Logic for turning off the specified appliance
        // Deduct energy or update score as needed
    }

    public void interactWithAppliance(Appliance appliance) {
        if (isWithinInteractionRange(appliance)) {
            appliance.turnOff();
            // Update player's attributes
            setEnergy(getEnergy() - appliance.getEnergyConsumption());
            setScore(getScore() + appliance.getScore());
        }
    }

    private boolean isWithinInteractionRange(Appliance appliance) {
        float distance = (float) Math.sqrt(Math.pow(getX() - appliance.getX(), 2) + Math.pow(getY() - appliance.getY(), 2));
        return distance <= Iradius;
    }


    public void incrementScore(float scoreValue) {
        setScore(scoreValue);
    }

    public void decreaseEnergy(float energyConsumption) {
    }
    @Override
    public GameObjectType getType() {
        return GameObjectType.PLAYER;
    }
}
