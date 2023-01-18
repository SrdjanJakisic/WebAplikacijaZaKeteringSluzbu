package beans;

import interfaces.ProductInterface;

public class OrderItem extends Order implements ProductInterface
{
    int orderItemId;
    int quantity;
    int price;
    int productId;
    String productName;

    public OrderItem(int orderItemId, int quantity, int price, int productId, int orderId)
    {
        this.orderItemId = orderItemId;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.orderId = orderId;
    }

    public OrderItem(){}

    public int getOrderItemId()
    {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId)
    {
        this.orderItemId = orderItemId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    @Override
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    @Override
    public String getProductName()
    {
        return productName;
    }
}
