package chu.engine.anim;

import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class BitmapFont {
	private Texture texture;
	private int glyphHeight;
	private int spacing;
	private HashMap<Character, Glyph> glyphs;
	
	public BitmapFont(String texName) {
		try {
			texture = TextureLoader.getTexture("PNG", 
					ResourceLoader.getResourceAsStream("res/"+texName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		glyphs = new HashMap<Character, Glyph>();
	}
	
	public void setHeight(int height) {
		glyphHeight = height;
	}
	
	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}
	
	public void put(char c, int pos, int width) {
		glyphs.put(c, new Glyph(pos, width));
	}
	
	public void render(String string, float beginX, float beginY, float depth) {
		int x = (int)beginX;
		for(char c : string.toCharArray()) {
			Glyph g = glyphs.get(c);
			float tx0 = (float)g.pos/texture.getImageWidth();
			float tx1 = (float)(g.pos+g.width)/texture.getImageWidth();
			Renderer.render(texture, tx0, 0, tx1, 1, x, beginY, x+g.width, beginY+glyphHeight, depth);
			x += g.width;
			x += spacing;
		}
	}
	
	private class Glyph {
		int pos;
		int width;
		public Glyph(int pos, int width) {
			this.pos = pos;
			this.width = width;
		}
	}
}