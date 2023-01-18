package beans;

public class ProductType
{
    int productTypeId;
    String typeName;
    
    public ProductType(){}

    public ProductType(int productTypeId, String typeName)
    {
        this.productTypeId = productTypeId;
        this.typeName = typeName;
    }

    public int getProductTypeId()
    {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId)
    {
        this.productTypeId = productTypeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
}
