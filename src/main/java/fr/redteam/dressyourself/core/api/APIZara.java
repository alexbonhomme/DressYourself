package fr.redteam.dressyourself.core.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.redteam.dressyourself.core.clothes.Clothe;

public class APIZara extends APIAbstractHelper implements APIInterface {

  private static final String DB_PATH = "res/raw/zara_20131110.sqlite";
  private Connection connector;

  /**
   * 
   */
  public APIZara() {
    super();

    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Clothe> getClothesByType(String typeName) {
    ArrayList<Clothe> clothesList = new ArrayList<Clothe>();

    try {
      connector = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
      Statement statement = connector.createStatement();

      ResultSet resultSet =
          statement
              .executeQuery("SELECT * FROM clothes JOIN type ON clothes.ID_t=type.ID_type WHERE type.typename LIKE '"
                  + typeName + "';");
      while (resultSet.next()) {
        Clothe product = new Clothe(resultSet.getString("model"));
        // TODO Implement totaly
        clothesList.add(product);
      }

      connector.close();
      statement.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return clothesList;
  }

  @Override
  public Clothe getClothe(int id) {
    Clothe product = new Clothe();


    try {
      connector = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
      Statement statement = connector.createStatement();

      ResultSet resultSet =
          statement.executeQuery("SELECT model, " + "brand.brandName AS brand, "
              + "color.colorName AS color, " + "type.typeName AS type, "
              + "bodies.bodiesName AS bodies " + "FROM clothes "
              + "JOIN brand ON clothes.ID_br=brand.ID_brand "
              + "JOIN color ON clothes.ID_c=color.ID_color "
              + "JOIN type ON clothes.ID_t=type.ID_type "
              + "JOIN bodies ON type.ID_b=bodies.ID_bodies " + "WHERE clothes.ID_clothes=" + id
              + ";");
      if (resultSet.next()) {
        product = new Clothe(resultSet.getString("model"));
        product.setColor(resultSet.getString("color"));
        product.setBrand(resultSet.getString("brand"));
        product.setBodies(resultSet.getString("bodies"));
        product.setType(resultSet.getString("type"));

        product.setImage(resultSet.getBlob("image").getBinaryStream());
      }

      connector.close();
      statement.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return product;
  }

  @Override
  public List<Clothe> searchAll(String query) {
    // TODO Implement
    return Collections.emptyList();
  }

}
