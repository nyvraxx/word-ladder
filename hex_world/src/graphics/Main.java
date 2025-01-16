package graphics;

import javax.swing.JFrame;

import hex_world.World;

public class Main {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			World world = new World(15);

//		Iterator<Tile> iter = world.getTileMap().iterator();
//
//		PerlinNoise noiseGen = new PerlinNoise(1234);
//
//		while (iter.hasNext()) {
//			DebugTile tile = new DebugTile();
//
//			world.getTileMap().replaceTile(iter.next(), tile);
//
//			float hue = (float) noiseGen.noise(tile.getCartesianX() * 0.04, tile.getCartesianY() * 0.04);
//			tile.setColor(new Color(Color.HSBtoRGB(hue, 1f, 1f)));
//		}

			Renderer renderer = new Renderer(world);
			JFrame frame = new SingleElementFrame(16 / 10.0, renderer);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			renderer.resetCamera();
			frame.setVisible(true);
		}
	}
}
