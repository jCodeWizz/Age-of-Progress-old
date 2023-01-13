package dev.codewizz.gfx.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class UIText extends UIElement {
	
	public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	public static final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("../assets/fonts/basic.ttf"));
	private String text; 
	private BitmapFont f;
	
	public UIText(String id, int x, int y, int w, int h, String text, int size) {
		super(id, x, y, w, h);
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size * UILayer.SCALE;
		parameter.characters = FONT_CHARACTERS;
		parameter.magFilter = Texture.TextureFilter.Nearest;
		parameter.minFilter = Texture.TextureFilter.Nearest;
		f = generator.generateFont(parameter);
		
		
		
		this.text = text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void render(SpriteBatch b) {
		f.draw(b, text, x, y);
	}
}
