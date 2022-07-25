package dev.codewizz;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;

public class Main extends Application {

	public static void main(String[] args) {
		launch(new Main());
	}
	
	@Override
	protected void configure(Configuration config) {
		config.setTitle("Bitch");
	}

	@Override
	public void process() {
		ImGui.text("Hello!");
	}
}
