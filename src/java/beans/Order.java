package beans;

public class Order
{
    int orderId;
    String email;
    int userId;
    String userName;
    String userSurname;
    String address;
    int phone;
    int storeId;
    double totalPrice;
    String date;

    public Order(){}

    public Order(int orderId, String email, int userId, String userName, String userSurname, String address, int phone, int storeId, double totalPrice, String date)
    {
        this.orderId = orderId;
        this.email = email;
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.address = address;
        this.phone = phone;
        this.storeId = storeId;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserSurname()
    {
        return userSurname;
    }

    public void setUserSurname(String userSurname)
    {
        this.userSurname = userSurname;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getPhone()
    {
        return phone;
    }

    public void setPhone(int phone)
    {
        this.phone = phone;
    }

    public int getStoreId()
    {
        return storeId;
    }

    public void setStoreId(int storeId)
    {
        this.storeId = storeId;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    } 
}
