package beans;

public class Store
{
    int storeId;
    String storeName;
    int storePhone;
    String storeImage;
    String storeStreet;
    String storeCity;
    int userId;

    public Store(){}

    public Store(int storeId, String storeName, int storePhone, String storeStreet, String storeCity, String storeImage, int userId)
    {
        this.storeId = storeId;
        this.storeName = storeName;  
        this.storePhone = storePhone;
        this.storeStreet = storeStreet;
        this.storeCity = storeCity;
        this.userId = userId;
        this.storeImage = storeImage;
    }

    public int getStoreId()
    {
        return storeId;
    }

    public void setStoreId(int storeId)
    {
        this.storeId = storeId;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getStoreImage()
    {
        return storeImage;
    }

    public void setStoreImage(String storeImage)
    {
        this.storeImage = storeImage;
    }

    public String getStoreStreet()
    {
        return storeStreet;
    }

    public void setStoreStreet(String storeStreet)
    {
        this.storeStreet = storeStreet;
    }

    public String getStoreCity()
    {
        return storeCity;
    }

    public void setStoreCity(String storeCity)
    {
        this.storeCity = storeCity;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getStorePhone()
    {
        return storePhone;
    }

    public void setStorePhone(int storePhone)
    {
        this.storePhone = storePhone;
    }
    
}
