package main.java.fr.redteam.dressyourself.core.clothes;

import java.io.Serializable;
import java.util.List;


public class Clothe implements Serializable {

  final static long serialVersionUID = 1L;

  private String model;

  private String brand;

  private String color;

  private String imageUrl;

  private String type;

  private List<String> weather;


  public Clothe() {
    super();
  }

  public Clothe(String model) {
    this.model = model;
  }

  public String getModel() {
    return model;
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

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<String> getWeather() {
    return weather;
  }

  public void setWeather(List<String> weather) {
    this.weather = weather;
  }

}
