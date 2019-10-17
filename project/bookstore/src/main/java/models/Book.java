package models;

public class Book {

  private String title;
  private String author;
  private String price;
  private String image;

  public Book(String title, String author, String price, String image) {
    setTitle(title);
    setAuthor(author);
    setPrice(price);
    setImage(image);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
