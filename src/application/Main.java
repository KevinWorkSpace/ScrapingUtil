package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	
	public Stage getStage() {
		return stage;
	}
	
	public Statement getStatement() {
		return stmt;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        Connection conn = null;
        stage = primaryStage;
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
