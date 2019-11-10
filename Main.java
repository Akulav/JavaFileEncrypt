package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;

public class Main extends Application {
	@FXML
	private Label progress;
	@FXML
	private Label path;
	@FXML
	private Button encrypt;
	@FXML
	private Button decrypt;
	@FXML
	private PasswordField key;

	static Storage s = new Storage();

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image("triangle-512.png"));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Made By Catalin");
			primaryStage.setResizable(false);
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(primaryStage);

			primaryStage.show();
			while (file == null) {
				file = fileChooser.showOpenDialog(primaryStage);
				path.setText(file.toString());
			}
			s.set(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void selectEncrypt() throws IOException {
		byte[] fileBytes = Files.readAllBytes(s.get().toPath());
		int cipher = Integer.valueOf(key.getText());
		for (int i = 0; i < fileBytes.length; i++) {
			fileBytes[i] = (byte) (fileBytes[i] + cipher);

		}
		System.out.println("Applied Cipher");
		try (FileOutputStream fos = new FileOutputStream(s.get())) {
			fos.write(fileBytes);
			System.out.println("Successfully written data to the file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void selectDecrypt() throws IOException {
		byte[] fileBytes = Files.readAllBytes(s.get().toPath());
		int cipher = Integer.valueOf(key.getText());
		for (int i = 0; i < fileBytes.length; i++) {
			fileBytes[i] = (byte) (fileBytes[i] - cipher);
		}
		System.out.println("Applied Cipher");
		try (FileOutputStream fos = new FileOutputStream(s.get())) {
			fos.write(fileBytes);
			System.out.println("Successfully written data to the file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*
 * File file = fileChooser.showOpenDialog(primaryStage); while (file == null) {
 * file = fileChooser.showOpenDialog(primaryStage); } try (Writer writer = new
 * BufferedWriter(new OutputStreamWriter( new FileOutputStream("E:/path"),
 * "utf-8"))) { writer.write(file.toString()); writer.close(); }
 */
/*
 * final DirectoryChooser directoryChooser = new DirectoryChooser(); final File
 * selectedDirectory = directoryChooser.showDialog(stage); if (selectedDirectory
 * != null) { selectedDirectory.getAbsolutePath(); }
 */
