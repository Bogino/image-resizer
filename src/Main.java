import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    private static int newWidth = 300;
    public static void main(String[] args){

    String srcFolder = "C:\\Users\\Илья\\Desktop\\фото";
    String dstFolder = "C:\\Users\\Илья\\Desktop\\копия фото";

    File srcDir = new File(srcFolder);

    long start = System.currentTimeMillis();

    File[] files = srcDir.listFiles();

    int cores = Runtime.getRuntime().availableProcessors();

    ConcurrentLinkedQueue<File> queue = new ConcurrentLinkedQueue<>(Arrays.asList(files));


    ImageResizer resizer = new ImageResizer(queue, newWidth, dstFolder, start);

    for (int i = 0; i < cores; i++)
    {
        new Thread(resizer).start();
    }




//    int quarter = (files.length - files.length % 4) / 4;
//    int third = quarter * 3;
//    int middle = quarter * 2;
//
//   File[] files1 = new File[quarter];
//   System.arraycopy(files, 0, files1, 0, files1.length);
//   ImageResizer resizer1 = new ImageResizer(files1, newWidth, dstFolder, start);
//   new Thread(resizer1).start();
//
//   File[] files2 = new File[quarter];
//   System.arraycopy(files, quarter, files2, 0, files2.length);
//   ImageResizer resizer2 = new ImageResizer(files2, newWidth, dstFolder, start);
//   new Thread(resizer2).start();
//
//   File[] files3 = new File[quarter];
//   System.arraycopy(files, middle, files3, 0, files3.length);
//   ImageResizer resizer3 = new ImageResizer(files3, newWidth, dstFolder, start);
//   new Thread(resizer3).start();
//
//   File[] files4 = new File[quarter + files.length % 4];
//   System.arraycopy(files, third, files4, 0, files4.length);
//   ImageResizer resizer4 = new ImageResizer(files4, newWidth, dstFolder, start);
//   new Thread(resizer4).start();



    }
}
