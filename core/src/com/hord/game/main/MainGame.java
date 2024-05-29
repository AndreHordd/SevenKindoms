package com.hord.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainGame extends Game {
	private OrthographicCamera orthographicCamera;

	@Override
	public void create() {
		this.orthographicCamera = new OrthographicCamera();
		this.setScreen(new MenuScreen(this)); // Тепер показуємо екран меню
	}

	@Override
	public void render() {
		super.render(); // Викликає render() метод активного Screen
	}

	@Override
	public void dispose() {

	}
}
