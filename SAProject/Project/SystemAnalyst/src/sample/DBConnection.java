package sample;

import Model.Customer;
import Model.Product;
import Pattern.Singeton.Customers;
import Pattern.Singeton.Order;
import Pattern.Singeton.User;
import TableModel.OrderDetail;
import TableModel.ProductTable;
import TableModel.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class DBConnection {
    private static String driver = "org.sqlite.JDBC";
    private static String urlDB = "jdbc:sqlite:new.db";

    public static boolean login(String username,String password){
        boolean check = false;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select password From Employee Where user = \"" + username + "\"";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (password.equals(resultSet.getString("Password"))) {
                    check = true;
                    break;
                }
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static void setUser(String username){
        User user = User.getInstance();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select employee_id,name,last_name,role From Employee Where user = \"" + username + "\"";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setLastname(resultSet.getString("last_name"));
                user.setRole(resultSet.getString("role"));
                user.setId(resultSet.getInt("employee_id"));
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ProductTable> showProduct(){
        ObservableList<ProductTable> product =FXCollections.observableArrayList();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select product_id,product_name,price,quantity From Product";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                product.add(new ProductTable(resultSet.getInt("product_id"),resultSet.getString("product_name"),
                        resultSet.getInt("quantity"),resultSet.getInt("price")));
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static void ModifiedStock(int id,int quantity){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Update Product Set quantity = quantity + "+quantity + " Where product_id = "+id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNewCustomer(String identity){
        boolean check = true;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select customer_id From Customer Where identity_personal = '"+identity + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                    check = false;
                    break;
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static boolean isAbleToBuy(String identity){
        boolean check = false;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select status From Customer Where identity_personal = '"+identity + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if(resultSet.getInt("status") >= 0){
                    check = true;
                    break;
                }
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static Customer CustomerDetail(String identity){
        Customer customer= new Customer();
        Customers customers = Customers.getInstance();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From Customer Where identity_personal = '"+identity + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(resultSet.getString("customer_name"));
                customer.setAddress(resultSet.getString("address"));
                customer.setHospital(resultSet.getString("hospital"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setStatus(resultSet.getInt("status"));
                customer.setIdentity(identity);
                customers.setCustomerid(customer.getId());
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customer;
    }
    public static void addNewCutomer(Customer customer){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Insert Into Customer Values(null,'"+customer.getName()+"','"+customer.getAddress()+"',0,'" +
                    customer.getHospital()+"','"+customer.getIdentity()+"','"+customer.getPhone()+"')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void upDateCustomer(Customer customer){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Update Customer set customer_name = '"+customer.getName() +"', address = '"+customer.getAddress()+"', hospital ='"+
                    customer.getHospital()+"', phone = '"+customer.getPhone()+"' Where identity_personal = '" +customer.getIdentity() +"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createNewOrder(){
        User user = User.getInstance();
        Customers customers = Customers.getInstance();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Insert Into Orderr Values(null,"+user.getId()+","+customers.getCustomerid()+",'"+ LocalDate.now().toString()+"','unpacked')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void orderID(){
        Order order = Order.getInstance();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "SELECT MAX(order_id) FROM Orderr";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
                order.setOrderid(resultSet.getInt("MAX(order_id)"));
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addOrderDetail(ProductTable productTable){
        Order order = Order.getInstance();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Insert Into OrderDetail Values("+order.getOrderid()+","+productTable.getId()+","+productTable.getPrice()+","+productTable.getQuan()+")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setPayment(Integer sum){
        Customers customers = Customers.getInstance();
        Integer sumation =  -sum;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Update Customer Set status = "+ sumation +" Where customer_id = "+customers.getCustomerid();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int CustomerID(String identity){
        Customers customers = Customers.getInstance();
        int id = 0;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select customer_id From Customer Where identity_personal = '"+identity+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                id = resultSet.getInt("customer_id");
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static Integer getStatus(String identity){
        Integer payment = 0;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select status From Customer Where identity_personal = '"+identity+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                payment = resultSet.getInt("status");
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return payment;
    }

    public static void settoZeroPayment(int zero, String identity){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Update Customer Set status = "+ zero +" Where identity_personal = '"+identity + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ProductTable> showbyDetail(Integer orderid){
        ObservableList<ProductTable> product =FXCollections.observableArrayList();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "SELECT Orderr.order_id,Product.product_id ,Product.product_name, OrderDetail.quantity, Product.price\n" +
                    "FROM ((OrderDetail\n" +
                    "INNER JOIN Orderr ON Orderr.order_id = OrderDetail.order_id)\n" +
                    "INNER JOIN Product ON OrderDetail.product_id = Product.product_id);";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                if(orderid == resultSet.getInt("order_id")){
                    product.add(new ProductTable(resultSet.getInt("product_id"),
                            resultSet.getString("product_name"),resultSet.getInt("quantity"),
                            resultSet.getInt("price")));
                }
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static void decreaseProduct(ProductTable productTable){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Update Product Set quantity = quantity -"+ productTable.getQuan() + " Where product_id = "+productTable.getId();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void PackingAddress(){
        Customers customers = Customers.getInstance();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select phone,address,hospital,customer_name From Customer Where identity_personal = '"+customers.getIdentity()+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                customers.setAddress(resultSet.getString("address"));
                customers.setHospital(resultSet.getString("hospital"));
                customers.setPhone(resultSet.getString("phone"));
                customers.setName(resultSet.getString("customer_name"));
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Supplier> showSupplier(){
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From Supliers";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                suppliers.add(new Supplier(resultSet.getInt("suppliers_id"),resultSet.getString("company_name"),resultSet.getString("address"),resultSet.getString("city"),
                        resultSet.getString("phone"),resultSet.getString("fax"),resultSet.getString("country")));
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public static void addSupplier(Supplier supplier){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Insert Into Supliers Values(null,'" +supplier.getAddress()+"','"+supplier.getName()+"','"
                    +supplier.getCity() + "','"+supplier.getPhone()+"','"+ supplier.getFax() + "','"+supplier.getCountry()+"')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Product getProductDetail(int id){
        Product productDetail = new Product();
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From Product Where product_id = "+id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                productDetail.setTubesize(resultSet.getString("tube_size"));
                productDetail.setWeight(resultSet.getString("weight"));
                productDetail.setBodydimension(resultSet.getString("body_dimension"));
                productDetail.setSetpointtemp(resultSet.getString("setpoint_temporature"));
                productDetail.setDisplay(resultSet.getString("display"));
                productDetail.setUsewith("user_with");
                productDetail.setCpu(resultSet.getString("cpu"));
                productDetail.setMemory(resultSet.getString("memory"));
                productDetail.setConnected(resultSet.getString("connected"));
                productDetail.setPower(resultSet.getString("power_requirements"));
                productDetail.setFlowrate(resultSet.getString("flow_rate"));
                productDetail.setGaranteeyear(resultSet.getString("warranty_year"));
                productDetail.setSupplierid(resultSet.getInt("supliers_id"));
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return productDetail;
    }

    public static void addToProductDetail(Product pd){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Insert Into Product Values("+null+",'"+pd.getTubesize()+"','"+pd.getWeight()+"','"+pd.getBodydimension()+
                    "','"+pd.getSetpointtemp()+"','"+pd.getDisplay()+"','"+pd.getUsewith()+"','"+pd.getCpu()+"','"+pd.getMemory()+"','"+pd.getConnected()+
                    "','"+pd.getPower()+"','"+pd.getFlowrate()+"','"+pd.getGaranteeyear()+"','"+pd.getName()+"',"+0+","+pd.getSupplierid()+","+pd.getPrice()+")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkOrderTracking(String identity,int orderid){
        boolean check = false;
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "select Customer.identity_personal ,Orderr.order_id From Customer inner join Orderr Where Customer.customer_id = Orderr.customer_id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                if(resultSet.getInt("order_id") == orderid && identity.equals(resultSet.getString("identity_personal")))
                    check = true;
            }
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static boolean checkPacked(int orderid){
        boolean check = false;
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select status From Orderr Where order_id = "+orderid;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                if(resultSet.getString("status").equals("packed"))
                    check = true;
            }
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static void setStatusPack(int orderid,String status){
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Update Orderr Set status ='"+status+"' Where order_id = " +orderid;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<OrderDetail> showOrderDetail(){
        ObservableList<OrderDetail> list = FXCollections.observableArrayList();
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "select Customer.customer_name,Orderr.order_id,Product.product_name,OrderDetail.quantity,Orderr.status\n" +
                    "FROM (((Orderr inner join OrderDetail on Orderr.order_id = OrderDetail.order_id)\n" +
                    "inner join Product On Product.product_id = OrderDetail.product_id)\n" +
                    "inner join Customer On Customer.customer_id = Orderr.customer_id)";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                list.add(new OrderDetail(resultSet.getInt("order_id"),resultSet.getInt("quantity"),
                        resultSet.getString("product_name"),resultSet.getString("status"),
                        resultSet.getString("customer_name")));
            }
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void setProductDetail(Product pd){
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Update Product Set tube_size ='"+pd.getTubesize()+"',weight = '"+pd.getWeight()+"',body_dimension = '"+pd.getBodydimension()+
                    "',setpoint_temporature = '"+pd.getSetpointtemp()+"',display = '"+pd.getDisplay()+"',use_with = '"+pd.getUsewith()+"',cpu = '"+pd.getCpu()+"',memory ='"+pd.getMemory()+"',connected = '"+pd.getConnected()+
                    "',power_requirements ='"+pd.getPower()+"',flow_rate = '"+pd.getFlowrate()+"',warranty_year ='"+pd.getGaranteeyear()+"',product_name ='"+pd.getName()+"',supliers_id = "+pd.getSupplierid()+",price = "+pd.getPrice()+" Where product_id =" +pd.getId();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkSupllierID(Integer id){
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select supliers_id From Product Where "+id+" = supliers_id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            while(resultSet.next())
                return true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isHave(String identity){
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From Customer Where identity_personal = '"+identity+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            while(resultSet.next())
                return true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
