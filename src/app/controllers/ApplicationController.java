package app.controllers;

import initializers.*;

public class ApplicationController {
  public static void main(String[] args) {
    Database database = Database.loadConfig();
    database.initialize();
  }
}