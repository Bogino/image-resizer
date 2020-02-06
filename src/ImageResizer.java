import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImageResizer implements Runnable{

    private ConcurrentLinkedQueue<File> queue;
    private int newWidth;
    private String dstFolder;
    private long start;


    public ImageResizer(ConcurrentLinkedQueue<File> queue, int newWidth, String dstFolder, long start) {
        this.queue = queue;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }



    @Override
    public void run() {
        File file;
        try
        {
            while (queue.size() > 0)
            {
                if ((file = queue.poll()) != null)
                {
                    BufferedImage image = ImageIO.read(file);
                    if (image == null) {
                        continue;
                    }

                    int newHeight = (int) Math.round(
                            image.getHeight() / (image.getWidth() / (double) newWidth));
                    BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

                    int widthStep = image.getWidth() / newWidth;
                    int heightStep = image.getHeight() / newHeight;

                    for (int x = 0; x < newWidth; x++) {
                        for (int y = 0; y < newHeight; y++) {
                            int rgb = image.getRGB(x * widthStep, y * heightStep);
                            newImage.setRGB(x, y, rgb);
                        }
                    }
                    File newFile = new File(dstFolder + File.separator + file.getName());
                    ImageIO.write(newImage, "jpg", newFile);
                }
                else return;
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        System.out.println("Finished after start: " + (System.currentTimeMillis() - start + " ms"));
    }
}
