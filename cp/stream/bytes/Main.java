import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Main {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    private static final NumberFormat numberFormat = NumberFormat.getNumberInstance();

    public static void main(String[] args) {
//        fileTest("src/Main.java");
        fileTest("../../");
    }

    public static void fileTest(String fileName) {
        File file = new File(fileName);

        if(file.exists()) {

            System.out.println(String.format("Path [%s]", file.getPath()));
            System.out.println(String.format("AbsolutePath [%s]", file.getAbsolutePath()));
            System.out.println(String.format("isDirectory [%s]", file.isDirectory()));
            System.out.println(String.format("Parent path [%s]", file.getParent()));

            if(file.isFile()) {
                LocalDateTime lastModifiedDate = LocalDateTime.ofEpochSecond(file.lastModified() / 1000, 0, ZoneOffset.ofHours(-5));
                String fileSize = numberFormat.format(file.length());
                System.out.println(String.format("Length [%s] bytes", fileSize));
                System.out.println("Last modified " + dateTimeFormatter.format(lastModifiedDate));
            } else if(file.isDirectory()) {
                for(File subFile: file.listFiles()) {
                    System.out.println(String.format("\t- %s [d=%b]", subFile.getName(), subFile.isDirectory()));
                    if(subFile.isDirectory()) {
                        for(File scndLevel: subFile.listFiles()) {
                            System.out.println(String.format("\t\t- %s [d=%b]", scndLevel.getName(), scndLevel.isDirectory()));
                        }
                    }
                }
            }
        } else {
            System.out.println("File does not exist");
        }
    }
}
