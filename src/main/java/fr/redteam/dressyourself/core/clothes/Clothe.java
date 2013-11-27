package fr.redteam.dressyourself.core.clothes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.redteam.dressyourself.core.ClothesManager;


public class Clothe implements Serializable {

  final static long serialVersionUID = 1L;
  
  private long id;

  private String model;

  private String brand;

  private String color;

  private String imageRelativePath;

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

  public Clothe(String model, String image) {
    this.model = model;
    this.imageRelativePath = image;
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
  
  public String getImageFullPath() {
    return ClothesManager.STORAGE_PATH + imageRelativePath;
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
