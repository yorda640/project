package models;

public class Item {
    public int Id;
    public String Name;
    public double Price;
    public String Description;
    public int StockQuantity;
    
    public Item(){}
    
    public Item(String Name, double Price, String Description, int StockQuantity) {
        this.Name = Name;
        this.Price = Price;
        this.Description = Description;
        this.StockQuantity = StockQuantity;
    }

    public Item(int id, String Name, double Price, String Description, int StockQuantity) {
        this.Id = id;
        this.Name = Name;
        this.Price = Price;
        this.Description = Description;
        this.StockQuantity = StockQuantity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getStockQuantity() {
        return StockQuantity;
    }

    public void setStockQuantity(int StockQuantity) {
        this.StockQuantity = StockQuantity;
    }
    
}
