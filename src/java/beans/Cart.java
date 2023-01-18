package beans;

import interfaces.ProductTypeInterface;

public class Cart extends Product implements ProductTypeInterface
{
    int quantity;
    String typeName;
    
    public Cart(){}

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    @Override
    public String getTypeName()
    {
        return typeName;
        
    }
    @Override
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
}
