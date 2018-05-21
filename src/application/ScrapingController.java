package application;

import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ScrapingController {
	
	@FXML
	private TextField search_text;
	
	@FXML
	private Button search_button;
	
	@FXML
	private Label result_label;
	
	private Main m;
	/**
	 * <b>设置Main类对象</b>
	 * @param m Main类对象
	 */
	public void setMain(Main m) {
		this.m = m;
	}
	/**
	 * <b>爬取网站信息.</b>
	 * <p>
	 * 对输入的网址进行爬取, 并将信息放入数据库中
	 * </p>
	 * @throws Exception 异常
	 */
	@FXML
	public void search() throws Exception {
		Statement stmt = null;
		String movieInfo = search_text.getText();
		stmt = m.getStatement();
		String name = null;
		Document doc = Jsoup
		          .connect(movieInfo)
		          .get();
		Elements name1 = doc.select("h1 span");
		//名字
		name = name1.get(0).text();
	    //导演
		Elements director = doc.select("div#info");
		String info = director.text();
	    String sql = "INSERT INTO movies ( name, info ) VALUES ('" + name + "','" + info + "');";  
	    stmt.executeUpdate(sql);
        result_label.setText(name + "爬取成功");
        search_text.setText("");
	}
	
}
