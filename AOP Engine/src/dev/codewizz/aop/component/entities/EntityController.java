package dev.codewizz.aop.component.entities;

import org.joml.Vector4f;

import dev.codewizz.engine.object.Component;
import dev.codewizz.engine.object.component.SpriteRenderer;
import imgui.ImGui;

public class EntityController extends Component {

	private SpriteRenderer r;
	
	@Override
	public void start() {
		r = gameObject.getComponent(SpriteRenderer.class);
	}
	
	@Override
	public void renderUI() {
		float[] color = { r.getColor().x, r.getColor().y, r.getColor().z, r.getColor().w };
		if(ImGui.colorPicker4("Pick color: ", color)) {
			r.getColor().set(color);
		}
		
		if(ImGui.imageButton(r.getTexture().getTextureID(), 10f, 10f, 20f, -58f)) {
			r.getColor().set(new Vector4f(1f, 1f, 1f, 1f));
		}
	}

	@Override
	public void update(float dt) {
		this.gameObject.transform.position.x += dt*20;
	}
}
