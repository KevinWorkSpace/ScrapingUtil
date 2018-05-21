package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Statement stmt;
	
	private Stage stage;
	/**
	 * <b>得到Stage对象</b>
	 * @return Stage对象
	 */
	public Stage getStage() {
		return stage;
	}
	/**
	 * <b>得到Statement对象</b>
	 * @return Statement对象
	 */
	public Statement getStatement() {
		return stmt;
	}
	/**
	 * <b>初始化程序界面</b>
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
        Connection conn = null;
        stage = primaryStage;
        String url = "jdbc:mysql://localhost:3306/crashcourse?"
                + "user=root&password=Zhchming1&useUnicode=true&characterEncoding=UTF8";
 
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            stmt.execute("SET names 'utf8mb4'");
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(this.getClass().getResource("Scraping.fxml"));
	        BorderPane root = (BorderPane) loader.load();
	        ScrapingController scrapingController = loader.getController();
	        scrapingController.setMain(this);
			Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
			primaryStage.show();
        } catch (SQLException e) {
        	System.out.println("MySQL操作错误");
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	/**
	 * <b>程序主入口</b>
	 * @param args 参数
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
