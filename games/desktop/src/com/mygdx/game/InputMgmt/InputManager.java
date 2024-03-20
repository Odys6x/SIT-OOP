package com.mygdx.game.InputMgmt;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.EntityMgmt.EntityManager;

public class InputManager {
	private EntityManager entityManager;
    private InputHandler keyboardInput;
    private InputHandler mouseInput;
    private List<Integer> pressedKeys = new ArrayList<>();

    public InputManager(EntityManager entityManager, InputHandler keyboardInput, InputHandler mouseInput) {
    	this.entityManager = entityManager;
        this.keyboardInput = keyboardInput;
        this.mouseInput = mouseInput;
    }

    public void update() {
    	// Get user input from input handlers and control user-controlled entities
    	// holy fucking jank
        if (entityManager.getUserControlledEntity() != null) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                keyboardInput.handleInput(InputCommand.MOVE_LEFT);
                
            } if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                keyboardInput.handleInput(InputCommand.MOVE_RIGHT);
                
            } if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                keyboardInput.handleInput(InputCommand.INTERACT);
                
            } if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                mouseInput.handleInput(InputCommand.MOUSE_LEFT_CLICK);
                
            } if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                mouseInput.handleInput(InputCommand.MOUSE_RIGHT_CLICK);
             
               
        }
      }
        
    }
    public List<Integer> getPressedKeys() {
        List<Integer> pressedKeys = new ArrayList<>();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            pressedKeys.add(Input.Keys.LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            pressedKeys.add(Input.Keys.RIGHT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            pressedKeys.add(Input.Keys.E);
        }
        return pressedKeys;
    }
}

