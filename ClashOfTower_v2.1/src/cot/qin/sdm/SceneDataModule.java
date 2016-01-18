package cot.qin.sdm;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class SceneDataModule {
	private static SceneDataModule sdm = null;
	private Map map = null;
	public boolean	notFound = false,imageTypeErr = false;
	
	public static SceneDataModule getInstance(){
		if(sdm == null)
			sdm = new SceneDataModule();
		return sdm;
	}
	
	public void loadMap(String mapfile) {
		map = new Map();
		BasicBlock[][] scene = new BasicBlock[50][50];
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("res/"+mapfile + ".txt"));
			for (int i = 0; i < scene.length; i++) {
				String input = bufferedReader.readLine();
				String[] strings = input.split(" ");
				for (int j = 0; j < scene[0].length; j++) {
					int imageIndex = Integer.parseInt(strings[j]);
					if (imageIndex < 0 || imageIndex > 20) {
						imageTypeErr = true;
						return;
					}else {
						scene[i][j] = new BasicBlock(imageIndex);
					}
				}
			}
			map.setScene(scene);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			notFound = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	public Map getMap() {
		return map;
	}
}
