package cine.base;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Thumbnail {
//썸네일 이미지 생성 클래스
	public static void createThumb(String oPath) {

		File oFile = new File(oPath);

		int index = oPath.lastIndexOf(".");
		String ext = oPath.substring(index + 1); // 파일 확장자

		String tPath = oFile.getParent() + File.separator + "t-" + oFile.getName(); // 썸네일저장 경로
		File tFile = new File(tPath);

		double ratio = 2; // 이미지 축소 비율

		try {
			BufferedImage oImage = ImageIO.read(oFile); // 원본이미지
			int tWidth = (int) (oImage.getWidth() / ratio); // 생성할 썸네일이미지의 너비
			int tHeight = (int) (oImage.getHeight() / ratio); // 생성할 썸네일이미지의 높이

			BufferedImage tImage = new BufferedImage(tWidth, tHeight, BufferedImage.TYPE_3BYTE_BGR); // 썸네일이미지
			Graphics2D graphic = tImage.createGraphics();
			Image image = oImage.getScaledInstance(tWidth, tHeight, Image.SCALE_SMOOTH);
			graphic.drawImage(image, 0, 0, tWidth, tHeight, null);
			graphic.dispose(); // 리소스를 모두 해제

			ImageIO.write(tImage, ext, tFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
