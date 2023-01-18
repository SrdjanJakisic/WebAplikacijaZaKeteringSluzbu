package beans;

public class Product extends ProductType
{
    int productId;
    String productName;
    double productPrice;
    int productWeight;
    String productDescription;
    String productImage;
    int typeId;
    int storeId;
    

    public Product(){}

    public Product(int productId, String productName, double productPrice, int productWeight, String productDescription, String productImage, int typeId, int storeId)
    {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productWeight = productWeight;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.typeId = typeId;
        this.storeId = storeId;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public double getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(double productPrice)
    {
        this.productPrice = productPrice;
    }

    public int getProductWeight()
    {
        return productWeight;
    }

    public void setProductWeight(int productWeight)
    {
        this.productWeight = productWeight;
    }

    public String getProductDescription()
    {
        return productDescription;
    }

    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }

    public String getProductImage()
    {
        return productImage;
    }

    public void setProductImage(String image)
    {
        this.productImage = productImage;
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    public int getStoreId()
    {
        return storeId;
    }

    public void setStoreId(int storeId)
    {
        this.storeId = storeId;
    }
}
