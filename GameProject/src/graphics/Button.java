package graphics;

import static graphics.Designer.quickTexture;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import main.Boot;
import map.TileType;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

public class Button {
  public int xx,yy,ww,hh;
  private Texture texture;
	String t;
	float R = 0.7F,G = 0.7F,B = 0.7F;
	float oR = 1F,oG = 1F,oB = 1F;
	boolean mouseOverEnabled = false, visible = true;
	public Button(int x, int y, int w, int h, TileType type,String text){
		xx=x;
		yy=y;
		ww=w;
		hh=h;
		t=text;
		texture = quickTexture(type.textureName);
	}
	public void draw(){
		if(visible){
		if(isMouseOver() && mouseOverEnabled)
		GL11.glColor3d(oR,oG,oB);
		else
		GL11.glColor3d(R,G,B);
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2d(xx-1, yy+1);
			GL11.glVertex2d(xx+ww+1, yy+1);
			GL11.glVertex2d(xx+ww+1, yy+hh-1);
			GL11.glVertex2d(xx-1, yy+hh-1);			
		}		
		GL11.glEnd();
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glVertex2d(xx+1, yy-1);
			GL11.glVertex2d(xx+ww-1, yy-1);
			GL11.glVertex2d(xx+ww-1, yy+hh+1);
			GL11.glVertex2d(xx+1, yy+hh+1);			
		}		
		GL11.glEnd();
		
		Font awtFont = new Font("Arial", Font.BOLD, 8);
		TrueTypeFont font = new TrueTypeFont(awtFont, true);
		font.drawString(xx,yy, t.toUpperCase());
		}
	}
	
	public boolean isClicked(){
		Rectangle r = new Rectangle();
		r.x=xx;
		r.y=yy;
		r.width=ww;
		r.height=hh;
		return r.contains(new Point(Mouse.getX(), (Boot.getNoRows()*32)-Mouse.getY() )) &&Mouse.isButtonDown(0);
	}
	public boolean isMouseOver(){
		Rectangle r = new Rectangle();
		r.x=xx;
		r.y=yy;
		r.width=ww;
		r.height=hh;		
		return r.contains(new Point(Mouse.getX(), 800-Mouse.getY()));
	}
	public void setColor(float r, float g, float b){
		R = r;
		G = g;
		B = b;
	}
	public void setMouseOverColor(float r, float g, float b){
		oR = r;
		oG = g;
		oB = b;		
	}
	public void enableMouseOver(boolean b){
		mouseOverEnabled = b;
	}
	public void setVisible(boolean b){
		visible = b;
	}
	
	public Texture getTexture()
	{
		return texture;
	}
	
	
}