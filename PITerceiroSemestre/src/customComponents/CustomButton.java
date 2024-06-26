package customComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

/**
 * Classe para a criacao de um botao com mais opcoes de personalizacao.
 * E possivel importa-lo para a paleta do WindowBuilder.
 */
public class CustomButton extends JButton{
	private static final long serialVersionUID = 1L;

	public CustomButton(){
		color=Color.WHITE;
		colorOver = new Color(179, 250, 160);
		colorClick= new Color(152, 184, 144);
		borderColor = new Color(30, 136, 56);
		setContentAreaFilled(false);
		addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me) {
				setBackground(colorOver);
				over = true;
				
			}
			public void mouseExited(MouseEvent me) {
				setBackground(color);
				over = false;
			}
			public void mousePressed(MouseEvent me) {
				setBackground(colorClick);
			}
			public void mouseReleased(MouseEvent me) {
				if(over) {
					setBackground(colorOver);
					
				}else {
					setBackground(color);
					
				}
			}
			
		});
	}
	
	public boolean isOver() {
		return over;
	}
	public void setOver(boolean over) {
		this.over = over;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
		setBackground(color);
	}
	public Color getColorOver() {
		return colorOver;
	}
	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}
	public Color getColorClick() {
		return colorClick;
	}
	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}

	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color borderColor;
	private int radius = 0;
	
	protected void paintComponent(Graphics grphcs) {
		Graphics2D g2 = (Graphics2D)grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(borderColor);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		g2.setColor(getBackground());
		
		g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
		super.paintComponent(grphcs);
	}
	
	
	

}
