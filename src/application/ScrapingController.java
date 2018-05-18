package application;

import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ScrapingController {
	@FXML
	private TextField search_text;
	@FXML
	private Button search_button;
	@FXML
	private Label result_label;
	
	private Main m;

	public void setMain(Main m) {
		this.m = m;
	}
	
	@FXML
	public void search() throws Exception {
		Statement stmt = null;
		String movieInfo = search_text.getText();
		stmt = m.getStatement();
		String name = null;
		String dire = null;
		String actor = null;
		String year = null;
		String type = null;
		Document doc = Jsoup
		          .connect(movieInfo)
		          .get();
		Elements name1 = doc.select("h1 span");
		//名字
		name = name1.get(0).text();
	    //导演
		Elements director = doc.select("div#info span");
	    dire = director.get(0).text();
	    //演员
	    actor = director.get(6).text();
	    //年份
	    year = director.get(15).text() + director.get(16).text();
	    //类型
	    type = director.get(9).text() + director.get(10).text();
	    
	    String sql = "INSERT INTO movie ( name, director, actor, year, type ) VALUES ('" + name + "','" + dire + "','" + actor + "','" + year + "','" + type + "');";  
	    stmt.executeUpdate(sql);
        result_label.setText(name + "爬取成功");
	}
	
}
