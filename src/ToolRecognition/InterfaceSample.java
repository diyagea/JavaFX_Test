package ToolRecognition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfaceSample extends Application {
	//String path = "C:/Users/haipeng/Desktop/新建文件夹/";
	String path = "D:/";

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		Label name = new Label("型号：");
		box.getChildren().add(name);
		ImageView imageView = new ImageView();
		imageView.setFitHeight(200);
		imageView.setFitWidth(200);
		box.getChildren().add(imageView);
		Button btn = new Button("开始识别刀具");
		box.getChildren().add(btn);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				btn.setText("读取中...");
				btn.setDisable(true);
				writeCommand();
				boolean flag = true;
				while (flag) {
					try {
						Thread.sleep(2000);

						String data = readData();
						if (data != null && data.length() > 0) {
							name.setText("型号：" + data);
							Image img = null;
							img = new Image("file:" + path + "当前图像.jpg");
							imageView.setImage(img);
						}
						flag = false;
						btn.setDisable(false);
						btn.setText("开始识别刀具");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		Scene scene = new Scene(box, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void writeCommand() {
		File f = new File(path + "相机控制.txt");
		String content = "1";
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			//fw = new FileWriter(f.getAbsoluteFile(), true); //true表示可以追加新内容  
			fw = new FileWriter(f.getAbsoluteFile()); //表示不追加
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String readData() {
		String s = "";
		File f = new File(path + "识别信息.txt");
		BufferedReader br = null;
		try {
			System.out.println("按照行读取文件内容");
			br = new BufferedReader(new FileReader(f));
			String temp;
			while ((temp = br.readLine()) != null) {
				s += temp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("file content:" + s);
		return s;
	}
}
