package app.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import org.yaml.snakeyaml.Yaml;
import java.nio.file.Files;
import java.io.InputStream;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DatabaseController {
  private final String enviroment = "development";
  private String driver = "com.mysql.jdbc.Driver";
  private String user;
  private String password;
  private String database;
  private String host;
  private String port;
  private String url;
  private Connection connection;

  public static void main(String[] args) {
    DatabaseController controller = loadConfig();
    if(controller != null) {
      controller.setUrl();
      controller.connect();
    } else {
      System.err.println("Configuración invalida");
    }
  }

  public void connect() {
    try {
      connection = DriverManager.getConnection(url, user, password);
    } catch(Exception exception) {
      exception.printStackTrace();
    }
  }

  public static DatabaseController loadConfig() {
    try {
      String config_file = new File("src/app/config/database.yml").getAbsolutePath();
      Yaml yaml = new Yaml();
      InputStream file = Files.newInputStream(Paths.get(config_file));
      return yaml.loadAs(file, DatabaseController.class);
    } catch(Exception exception) {
      exception.printStackTrace();
    }
    return null;
  }
  
  public void setUser(String user) {
    if(user != null && !user.equals("")) {
      this.user = user;
    }
  }

  public void setPassword(String password) {
    if(password != null && !password.equals("")) {
      this.password = password;
    }
  }

  public void setDatabase(Map<String, String> database) {
    if(database != null && !database.equals("")) {
      this.database = database.get(enviroment);
    }
  }

  public void setHost(String host) {
    if(host != null && !host.equals("")) {
      this.host = host;
    }
  }

  public void setPort(String port) {
    if(port != null && !port.equals("")) {
      this.port = port;
    }
  }

  public void setUrl() {
    url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false";
  }
}