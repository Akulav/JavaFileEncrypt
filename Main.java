import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {
  public static void main(String [] pArgs) throws IOException {
    String fileName = "2.jpg";
    File file = new File(fileName);
    File file1 = new File("3.jpg");
    byte [] fileBytes = Files.readAllBytes(file.toPath());
    System.out.println("Enter key: ");
    Scanner input = new Scanner(System.in);
    int key = input.nextInt();
    System.out.println("Enter 1 for encryption and 2 for decryption");
    int direction = input.nextInt();
    char current;
    if (direction == 1) {
    	for (int i = 0; i<fileBytes.length;i++) {
    		fileBytes[i]=(byte) (fileBytes[i]+key);
    	}
    }
    if (direction == 2) {
    	for (int i = 0; i<fileBytes.length;i++) {
    		fileBytes[i]=(byte) (fileBytes[i]-key);
    	}
    }

		try (FileOutputStream fos = new FileOutputStream(file1)) {
			fos.write(fileBytes);
			System.out.println("Successfully written data to the file");
		} catch (IOException e) {
			e.printStackTrace();
    }
		
  }
}