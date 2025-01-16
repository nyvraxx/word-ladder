package graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

public class SingleElementFrame extends JFrame {
	private static final int TAB_THICKNESS = 27;
	private static final long serialVersionUID = 1L;

	private double aspectRatio = 16.0 / 9;
	private Component component = null;

	public SingleElementFrame(double aspectRatio, Component c) {
		super();
		this.getContentPane().setBackground(Color.BLACK);

		this.aspectRatio = aspectRatio;
		int h = 720;
		int w = (int) (aspectRatio * h);
		this.setSize(new Dimension(w, h + TAB_THICKNESS));

		this.setLayout(null);

		component = c;
		this.add(component);

		updateComponentSize(w, h);

		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				updateComponentSize(getWidth(), getHeight());
			}
		});

	}

	private void updateComponentSize(int frameWidth, int frameHeight) {
		frameHeight -= TAB_THICKNESS;

		// Calculate the new dimensions based on the aspect ratio
		int newWidth = frameWidth;
		int newHeight = (int) (newWidth / aspectRatio);

		// If the calculated height exceeds the frame height, adjust accordingly
		if (newHeight > frameHeight) {
			newHeight = frameHeight;
			newWidth = (int) (newHeight * aspectRatio);
		}

		// Set the size and position of the component
		component.setBounds((frameWidth - newWidth) / 2, (frameHeight - newHeight) / 2, newWidth, newHeight);
		component.setPreferredSize(new Dimension(newWidth, newHeight));
		component.revalidate(); // Refresh the component layout
	}
}
