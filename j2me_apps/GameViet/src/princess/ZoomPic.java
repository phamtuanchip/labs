package princess;
import javax.microedition.lcdui.Image;
public class ZoomPic {
	public static Image zoom(Image oldImage,int newWidth,int newHeight){
		Image imgNew=null;
		int oldWidth=oldImage.getWidth();
		int oldHeight=oldImage.getHeight();
		int[] arrOld=new int[oldWidth*oldHeight];
		int[] arrNew=new int[newWidth*newHeight];
		oldImage.getRGB(arrOld, 0, oldWidth,0, 0, oldWidth, oldHeight);
		for(int i=0;i<newHeight;i++){
			int temp=i*oldHeight/newHeight;
			for(int j=0;j<newWidth;j++){
				arrNew[i*newWidth+j]=arrOld[temp*oldWidth+j*oldWidth/newWidth];
			}
		}
		imgNew =Image.createRGBImage(arrNew, newWidth, newHeight, true);
		return imgNew;
	}
}
