package fr.redteam.dressyourself.core.clothes;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class define the concept of one clothe
 */
public class Clothe implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id;

  private String model;

  private String brand;

  private String color;

  private File image;

  private String type;

  private String bodies;

  private List<String> weather;

  /**
   * This a simple constructor with all argument are {@link null} except image which is an empty
   * {@link File} and weather which is an empty {@link ArrayList} of {@link String}
   */

  public Clothe() {
    this.id = 0L;
    this.model = null;
    this.brand = null;
    this.color = null;
    this.image = new File("");
    this.type = null;
    this.bodies = null;
    this.weather = new ArrayList<String>();
  }

  /**
   * This a simple constructor with a string for modelname all of others arguments are null except
   * image which is an empty {@link File} and weather which is an empty {@link ArrayList} of
   * {@link String}
   */
  public Clothe(String model) {
    this.model = model;
    this.weather = new ArrayList<String>();
    this.id = 0L;
    this.brand = null;
    this.color = null;
    this.image = new File("");
    this.type = null;
    this.bodies = null;
  }

  /**
   * Get the clothe's id
   */
  public long getId() {
    return id;
  }

  /**
   * Get the clothe's model name
   */
  public String getModel() {
    return model;
  }

  /**
   * Set the clothe's ID with the long <code>id</code>
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Set the clothe's model name with the {@link String} <code>model</code>
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Get the clothe's brand
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Set the clothe's brand with the {@link String} <code>brand</code>
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Get the clothe's color name
   */
  public String getColor() {
    return color;
  }

  /**
   * Get the clothe's color name with the {@link String} <code>color</code>
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Get the clothe's path of clothe's image
   */
  public String getImageRelativePath() {
    return image.getPath();
  }

  /**
   * Set the clothe's path with the {@link String} <code>imagePath</code>
   */
  public void setImageRelativePath(String imagePath) {
    this.image = new File(imagePath);
  }

  /**
   * Get the clothe's type
   */
  public String getType() {
    return type;
  }

  /**
   * Set the clothe's type with the {@link String} <code>type</code>
   */
  public void setType(String type) {
    this.type = type;
  }


  /**
   * Get the clothe's bodies
   */
  public String getBodies() {
    return bodies;
  }

  /**
   * Set the clothe's bodies with the {@link String} <code>bodies</code>
   */
  public void setBodies(String bodies) {
    this.bodies = bodies;
  }

  /**
   * Get the clothe's weather
   */
  public List<String> getWeather() {
    return weather;
  }

  /**
   * Set the clothe's weather with one {@link List} of {@link String}
   */
  public void setWeather(List<String> weather) {
    this.weather = weather;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    Clothe clothe = (Clothe) obj;

    if (clothe.getId() != id) {
      return false;
    }

    if (!clothe.getModel().equals(model)) {
      return false;
    }

    if (!clothe.getBrand().equals(brand)) {
      return false;
    }

    if (!clothe.getColor().equals(color)) {
      return false;
    }

    if (!clothe.getImageRelativePath().equals(getImageRelativePath())) {
      return false;
    }

    if (!clothe.getType().equals(type)) {
      return false;
    }

    if (!clothe.getBodies().equals(bodies)) {
      return false;
    }

    if (!clothe.getWeather().equals(weather)) {
      return false;
    }

    return true;
  }
}
