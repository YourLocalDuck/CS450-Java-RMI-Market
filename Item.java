import java.io.Serializable;

public class Item implements Serializable{
    protected int id;
    protected String name;
    protected String type;
    protected String description;
    protected float price;
    protected int quantity;

    Item(int id, String name, String type, String description, float price, int quantity)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    Item()
    {
        this.id = 0;
        this.name = "";
        this.type = "";
        this.description = "";
        this.price = 0;
        this.quantity = 0;
    }

    public int getId()
    {
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }
    public String getType()
    {
        return this.type;
    }
    public String getDescription()
    {
        return this.description;
    }
    public float getPrice()
    {
        return this.price;
    }
    public int getQuantity()
    {
        return this.quantity;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public void setPrice(float price)
    {
        this.price = price;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
