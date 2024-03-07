package lab6;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageProcessingApp {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ImageProcessingApp <input_directory> <output_directory>");
            System.exit(1);
        }

        String inputDirectory = args[0];
        String outputDirectory = args[1];

        processImages(inputDirectory, outputDirectory);
    }

    private static void processImages(String inputDirectory, String outputDirectory) {
        try (Stream<Path> stream = Files.list(Path.of(inputDirectory))) {
            List<Path> files = stream.collect(Collectors.toList());

            ForkJoinPool customThreadPool = new ForkJoinPool(4); // Adjust the pool size as needed
            customThreadPool.submit(() ->
                    files.parallelStream()
                            .map(ImageProcessingApp::loadImage)
                            .map(pair -> Pair.of(pair.getLeft(), processImage(pair.getRight())))
                            .forEach(pair -> saveImage(pair.getLeft(), pair.getRight(), outputDirectory))
            ).get();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Pair<String, BufferedImage> loadImage(Path path) {
        try {
            BufferedImage image = ImageIO.read(path.toFile());
            return Pair.of(path.getFileName().toString(), image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage processImage(BufferedImage original) {
        BufferedImage processedImage = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for (int i = 0; i < original.getWidth(); i++) {
            for (int j = 0; j < original.getHeight(); j++) {
                int rgb = original.getRGB(i, j);
                Color color = new Color(rgb);
                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();
                Color outColor = new Color(red, blue, green);
                int outRgb = outColor.getRGB();
                processedImage.setRGB(i, j, outRgb);
            }
        }

        return processedImage;
    }

    private static void saveImage(String fileName, BufferedImage image, String outputDirectory) {
        Path outputPath = Path.of(outputDirectory, fileName);
        try {
            ImageIO.write(image, "png", outputPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
