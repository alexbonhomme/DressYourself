package fr.redteam.dressyourself.core.clothes;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Clothe implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id;

  private String model;

  private String brand;

  private String color;

  private InputStream image;

  private String type;

  private String bodies;

  private List<String> weather;


  public Clothe() {
    super();
  }

  public Clothe(String model) {
    this.model = model;
    weather = new ArrayList<String>();
  }

  public Clothe(String model, InputStream image) {
    this.model = model;
    this.image = image;
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

  public InputStream getImage() {
    return image;
  }

  public void setImage(InputStream imageUrl) {
    this.image = imageUrl;
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
