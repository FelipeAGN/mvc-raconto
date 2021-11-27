package form;

public class Product {

    private Integer id;
    private String name;
    private Double price;
    private Integer categoria;
    private String description;
    private String pathToImage;

    public Product() { }

    public Product(Integer id, String name, Double price, Integer categoria, String description, String pathToImage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoria = categoria;
        this.description = description;
        this.pathToImage = pathToImage;
    }

    public Product(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getCategoria() { return categoria; }
    public void setCategoria(Integer categoria) { this.categoria = categoria; }
    public void setCategoria(CategoriaProduct categoria) { this.categoria = categoria.getCategoriaProductValue(); }
    public String getPathToImage() { return pathToImage; }
    public void setPathToImage(String pathToImage) { this.pathToImage = pathToImage; }
}
