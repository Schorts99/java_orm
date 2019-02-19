package app.models;

import config.initializers.*;
import java.sql.Connection;

class ActiveModel {
  private Connection connection;

  ActiveModel() {
    Database database = Database.loadConfig();
    database.initialize();
    connection = database.getConnection();
  }
}