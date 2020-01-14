package application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

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
	@FXML
	private Label SelectedPath;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.getIcons().add(new Image("triangle-512.png"));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Made By Catalin");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	static Storage s = new Storage();

	public void getPath() {
		Stage primaryStage = new Stage();
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(primaryStage);
		while (file == null) {
			file = fileChooser.showOpenDialog(primaryStage);
			path.setText(file.toString());
		}
		s.set(file);
		SelectedPath.setText(file.toString());
	}

	public void selectEncrypt() throws IOException {

		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(s.get()))) {
			byte[] bbuf = new byte[1048576];
			byte[] wow = new byte[1048576];
			int len;
			String fileString = convertPath(s.get().toString());
			fileString = fileString + "\\" + genName();
			System.out.println(fileString);
			File file = new File(fileString);
			int cipher = Integer.valueOf(key.getText());
			while ((len = in.read(bbuf)) != -1) {
				for (int i = 0; i < bbuf.length; i++) {
					bbuf[i] = (byte) (bbuf[i] + cipher);
				}
				try (FileOutputStream fos = new FileOutputStream(file, true)) {
					fos.write(wow);
					fos.write(bbuf);
					System.out.println("Success");
					progress.setText("Success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			in.close();
			Files.deleteIfExists(s.get().toPath());
		}

	}

	public String convertPath(String s) {
		int index = s.lastIndexOf('\\');
		String converted = s.substring(0, index);
		return converted;
	}
	
	public int genName() {
		Random rand = new Random();
		int rand_name1 = rand.nextInt(10000);
		int rand_name2 = rand.nextInt(10000);
		int rand_name3 = rand.nextInt(10000);
		int finalName = rand_name1+rand_name2+rand_name3;
		return finalName;
	}
	
	public int genDecryptFlag() {
		Random rand = new Random();
		int flag = rand.nextInt(10);
		while (flag%2!=0 && flag > 999) {
			flag=rand.nextInt(99999);
		} 
		
		int flag2 = rand.nextInt(10);
		while (flag2%2!=0 && flag2 > 999) {
			flag2=rand.nextInt(99999);
		}
		
		int flag3 = rand.nextInt(10);
		while (flag3%2!=0 && flag3 > 999) {
			flag3=rand.nextInt(99999);
		} 
		
		System.out.println(flag);
		return flag;
	}

	public void selectDecrypt() throws IOException {

		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(s.get()))) {
			byte[] bbuf = new byte[1048576];
			int len;
			String fileString = convertPath(s.get().toString());
			fileString = fileString + "\\" + genName();
			System.out.println(fileString);
			File file = new File(fileString);
			int cipher = Integer.valueOf(key.getText());
			while ((len = in.read(bbuf)) != -1) {
				for (int i = 0; i < bbuf.length; i++) {
					bbuf[i] = (byte) (bbuf[i] - cipher);
				}
				try (FileOutputStream fos = new FileOutputStream(file, true)) {
					fos.write(bbuf);
					System.out.println("Success");
					progress.setText("Success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			in.close();
			Files.deleteIfExists(s.get().toPath());
		}
	}
}
