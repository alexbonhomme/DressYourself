package fr.redteam.dressyourself.core.clothes;

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

  private String imageRelativePath;

  private String type;

  private String bodies;

  private List<String> weather;

  /**
   * this a simple constructor with all argument are null except weather which is an empty arraylist
   */

  public Clothe() {
    this.id = 0L;
    this.model = null;
    this.brand = null;
    this.color = null;
    this.imageRelativePath = null;
    this.type = null;
    this.bodies = null;
    this.weather = new ArrayList<String>();
  }

  /**
   * this a simple constructor with a string for modelname all of others arguments are null except
   * weather which is an empty arraylist
   */
  public Clothe(String model) {
    this.model = model;
    this.weather = new ArrayList<String>();
    this.id = 0L;
    this.brand = null;
    this.color = null;
    this.imageRelativePath = null;
    this.type = null;
    this.bodies = null;
  }

  /**
   * this a simple constructor with a string for modelname and the second string define the path all
   * of others arguments are null except weather which is an empty arraylist
   */
  public Clothe(String model, String image) {
    this.model = model;
    this.imageRelativePath = image;
    this.id = 0L;
    this.brand = null;
    this.color = null;
    this.type = null;
    this.bodies = null;
    this.weather = new ArrayList<String>();
  }

  /**
   * get the clothe's id
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
   * set the clothe's id with the long id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Set the clothe's model name with the string model
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * get the clothe's brand
   */
  public String getBrand() {
    return brand;
  }

  /**
   * set the clothe's brand with the string brand
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * get the clothe's color
   */
  public String getColor() {
    return color;
  }

  /**
   * set the clothe's color with the string color
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * get the clothe's path of clothe's image
   */
  public String getImageRelativePath() {
    return imageRelativePath;
  }

  /**
   * set the clothe's path with the string imagePath
   */
  public void setImageRelativePath(String imagePath) {
    this.imageRelativePath = imagePath;
  }

  /**
   * get the clothe's type
   */
  public String getType() {
    return type;
  }

  /**
   * set the clothe's type with the string type
   */
  public void setType(String type) {
    this.type = type;
  }


  /**
   * get the clothe's bodies
   */
  public String getBodies() {
    return bodies;
  }

  /**
   * set the clothe's bodies with the string bodies
   */
  public void setBodies(String bodies) {
    this.bodies = bodies;
  }

  /**
   * get the clothe's weather
   */
  public List<String> getWeather() {
    return weather;
  }

  /**
   * set the clothe's weather with one List of string
   */
  public void setWeather(List<String> weather) {
    this.weather = weather;
  }

}
