package fr.redteam.dressyourself.core.clothes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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

  public long getId() {
    return id;
  }

  public String getModel() {
    return model;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getImageRelativePath() {
    return imageRelativePath;
  }

  public void setImageRelativePath(String imagePath) {
    this.imageRelativePath = imagePath;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getBodies() {
    return bodies;
  }


  public void setBodies(String bodies) {
    this.bodies = bodies;
  }

  public List<String> getWeather() {
    return weather;
  }

  public void setWeather(List<String> weather) {
    this.weather = weather;
  }

}
