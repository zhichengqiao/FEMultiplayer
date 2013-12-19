package net.fe.unit;


import chu.engine.Entity;
import chu.engine.Resources;
import chu.engine.anim.Renderer;

public class ItemDisplay extends Entity{
	private Item item;
	private boolean equip;
	public ItemDisplay(float f, float g, Item i, boolean equip){
		super(f,g);
		renderDepth = 0.05f;
		item = i;
		this.equip = equip;
	}
	public void render(){
		int row = item.id/8;
		int col = item.id%8;
		Renderer.render(Resources.getTexture("gui_weaponIcon"), 
				col/8.0f, row/9.0f, (col+1)/8.0f, (row+1)/9.0f,
				x-1, y, x+16, y+17, renderDepth);
		Resources.getBitmapFont("default_med").render(item.name, x+16, y+3, renderDepth);
		if(equip){
			Renderer.render(Resources.getTexture("e"), 
					0, 0, 1, 1,
					x+10, y+10, x+16, y+17, renderDepth);
		}
	}
	
	public Item getItem(){
		return item;
	}
}
