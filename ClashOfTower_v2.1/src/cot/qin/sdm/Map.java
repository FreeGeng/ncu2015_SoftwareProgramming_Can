package cot.qin.sdm;

public class Map {
	private BasicBlock[][] scene;
	public void setScene(BasicBlock[][] scene) {
		this.scene = scene;
	}

	public BasicBlock[][] getScene() {
		return scene;
	}

	public String getBlockImage(int x, int y) {
		return scene[y][x].getTypeImage();
	}
	
}
