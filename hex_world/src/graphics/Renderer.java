package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;

import javax.swing.JPanel;

import hex_world.World;
import tiles.Tile;

public class Renderer extends JPanel {
	private static final long serialVersionUID = -7525797435255257636L;

	private double minScale = 0.0007, maxScale = 0.07, scaleSensitivity = 0.001;
	private World world;
	private double tx, ty, scale = 0.007;

	public Renderer(World world) {
		this.setBackground(Color.DARK_GRAY);

		this.world = world;

		TransformerMouseListener transformerMouseListener = new TransformerMouseListener();
		this.addMouseListener(transformerMouseListener);
		this.addMouseMotionListener(transformerMouseListener);
		this.addMouseWheelListener(transformerMouseListener);
	}

	public void resetCamera() {
		tx = 0.5 / scale;
		ty = 0.5 * this.getHeight() / (this.getWidth() * scale);
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setStroke(new BasicStroke(1.4f));
		g2.scale(scale * getWidth(), scale * getWidth());
		g2.translate(tx, ty);

		g2.rotate(Math.PI);

		Iterator<Tile> tileIter = world.getTileMap().iterator();

		while (tileIter.hasNext()) {
			Tile next = tileIter.next();

			next.draw(g2, null);
		}

		g2.dispose();
	}

	private class TransformerMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		private int px, py;

		@Override
		public void mouseDragged(MouseEvent e) {
			tx += (e.getX() - px) / scale / getWidth();
			ty += (e.getY() - py) / scale / getWidth();

			px = e.getX();
			py = e.getY();

			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			px = e.getX();
			py = e.getY();
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int mouseX = e.getX();
			int mouseY = e.getY();

			double pScale = scale;
			scale += e.getPreciseWheelRotation() * scaleSensitivity;

			if (scale < minScale)
				scale = minScale;
			else if (scale > maxScale)
				scale = maxScale;

			tx += (double) mouseX / getWidth() * (1.0 / scale - 1.0 / pScale);
			ty += (double) mouseY / getWidth() * (1.0 / scale - 1.0 / pScale);

			repaint();
		}

	}

}
