package Preloader;

import com.sun.javafx.application.LauncherImpl;

@SuppressWarnings("restriction")
public class Main {
	public static void main(String[] args) {
		LauncherImpl.launchApplication(MyApplication.class, PreloaderTest2.class, args);
	}
}