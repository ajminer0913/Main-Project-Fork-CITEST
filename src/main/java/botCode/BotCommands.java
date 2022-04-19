package botCode;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BotCommands extends ListenerAdapter {

    Botoperations cust = Botoperations.getInstance();
    public String prefix = "!";
    private boolean ordersuccess;

    /**
     * Logic for when the bot sees a message in the chat
     * @param event the message in chat
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String[] args = event.getMessage().getContentRaw().split(" ");
        String user = event.getAuthor().getAsMention();

        if(args[0].equalsIgnoreCase(prefix + "commands")){
            menu(event,user);
        }
        if(args[0].equalsIgnoreCase(prefix + "createorder")){
            createOrder(event,args);
        }
        if(args[0].equalsIgnoreCase(prefix + "orders")){
            pastOrders(event,args);
        }
        if(args[0].equalsIgnoreCase(prefix + "browse")){
            browse(event);
        }
        if(args[0].equalsIgnoreCase(prefix + "processorder")) {
            processOrder(event, args);
        }
    }

    private void menu(MessageReceivedEvent event, String  user){
        event.getChannel().sendMessage("Hello " + user).queue();
        event.getChannel().sendMessage("1.) If you would like to place an order, please " +
                "enter the following in order separated by spaces:").queue();
        event.getChannel().sendMessage("!createorder email location(int) product id quantity(int)").queue();
        event.getChannel().sendMessage("2.) If you would like to look at past orders, enter !orders").queue();
        event.getChannel().sendMessage("3.) If you would like a sample of our items, enter !browse").queue();
    }

    private void createOrder(MessageReceivedEvent event, String[] args){
        Connection conn = cust.connect();
        ordersuccess = true;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currDate = dateFormat.format(date);
        String email = args[1];
        int location = Integer.parseInt(args[2]);;
        String product_id = args[3];
        int quantity = Integer.parseInt(args[4]);
        String custId = event.getAuthor().getId();

        boolean isThere = checkStock(event,product_id,quantity);

        if(isThere) {
            event.getChannel().sendMessage("Date: " + currDate).queue();
            event.getChannel().sendMessage("Email: " + email).queue();
            event.getChannel().sendMessage("Location:" + location).queue();
            event.getChannel().sendMessage("Product ID: " + product_id).queue();
            event.getChannel().sendMessage("Quantity: " + quantity).queue();
            //manually doing the SQL query since I can't really wait for the updated Create
            try {
                Statement stmt = null;

                conn.setAutoCommit(false);
                stmt = conn.createStatement();

                String out = "INSERT INTO discord_orders(date, cust_email, cust_location, product_id, product_quantity,cust_id,order_status)"
                        + "VALUES('" + currDate + "','" + email + "'," + location + ",'" + product_id + "'," + quantity + ",'" + custId + "','Processing');";

                stmt.executeUpdate(out);
                stmt.close();
                conn.commit();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
                event.getChannel().sendMessage("Error in creating order").queue();
                ordersuccess = false;
            }
            if (ordersuccess) {
                event.getChannel().sendMessage("Order Successfully Created").queue();
            }
        }
    }
    private void pastOrders(MessageReceivedEvent event, String[] args){
        Connection conn = cust.connect();
        String custId = event.getAuthor().getId();
        try {
            Statement stmt = null;

            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT date, product_id, product_quantity,order_status,order_id FROM discord_orders WHERE cust_id = '" + custId + "';");
            if (rs != null){
                event.getChannel().sendMessage("Previous Order").queue();
                event.getChannel().sendMessage("Date" + "\t\t\t      Product ID" + "\tQuantity" +"\tOrder ID" + "\tOrder Status ").queue();
                event.getChannel().sendMessage("------------------------------------------------------------").queue();
                while (rs.next()){
                    String date = rs.getString("date");
                    String prodId = rs.getString("product_id");
                    int qt = rs.getInt("product_quantity");
                    String orderStat = rs.getString("order_status");
                    String orderId = rs.getString("order_id");
                    event.getChannel().sendMessage(date + "\t" + prodId + "\t" + qt + "\t\t" + orderId + "\t\t" + orderStat).queue();
                }
            }else{
                event.getChannel().sendMessage("No previous orders found").queue();
            }
            rs.close();
            stmt.close();
            conn.commit();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void browse(MessageReceivedEvent event){
        Connection conn = cust.connect();
        try {
            Statement stmt = null;

            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT A.product_id,B.prod_name,B.prod_description,A.quantity,A.sale_price FROM products A NATURAL JOIN product_descriptions B;");
            if (rs != null){
                event.getChannel().sendMessage("Product ID" + "\t\tProduct Name" +"\t\tProduct Description" + "\t\tQuantity" + "\t\tSale Price ").queue();
                event.getChannel().sendMessage("------------------------------------------------------------").queue();
                while (rs.next()){
                    String prodId = rs.getString("product_id");
                    String prodName = rs.getString("prod_name");
                    String desc = rs.getString("prod_description");
                    int qt = rs.getInt("quantity");
                    double saleprice = rs.getDouble("sale_price");
                    event.getChannel().sendMessage(prodId + "\t" + prodName + "\t\t" + desc + "\t\t" + qt + "\t\t$" + saleprice).queue();
                }
            }else{
                event.getChannel().sendMessage("No previous orders found").queue();
            }
            rs.close();
            stmt.close();
            conn.commit();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkStock(MessageReceivedEvent event, String prodId, int quant){
        Connection conn = cust.connect();

        boolean isThere = true;
        try{
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products WHERE product_id = '" + prodId.toUpperCase() + "';");

            while(rs.next()){
                int qt = rs.getInt("quantity");
                if(quant > qt){
                    event.getChannel().sendMessage("Insufficient Quantity").queue();
                    isThere = false;
                }
            }

        } catch (Exception e){
            e.printStackTrace();
            event.getChannel().sendMessage("Product not found").queue();
            isThere = false;
        }

        return isThere;
    }

    public void processOrder(MessageReceivedEvent event, String[] args){
        Connection conn = cust.connect();
        String prodId = null;
        int discordOrderId = 0;
        int quant = 0;
        int orderQuant = 0;
        double wholeSale = 0;
        double salePrice = 0;
        String supplier_id = "";
        String status = "";
        try{
            discordOrderId = Integer.parseInt(args[1]);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM discord_orders WHERE order_id = " + discordOrderId + ";");

            while(rs.next()){
                prodId = rs.getString("product_id");
                quant = rs.getInt("product_quantity");
                status = rs.getString("order_status");
            }

            if(status.equals("Processing")) {
                rs = stmt.executeQuery("SELECT * FROM  products WHERE product_id = '" + prodId + "';");

                while (rs.next()) {
                    orderQuant = rs.getInt("quantity");
                    wholeSale = rs.getDouble("wholesale_cost");
                    salePrice = rs.getDouble("sale_price");
                    supplier_id = rs.getString("supplier_id");

                }

                quant = orderQuant - quant;
                stmt.executeUpdate("DELETE FROM products WHERE product_id = '" + prodId + "';");
                conn.commit();

                stmt.executeUpdate("INSERT INTO products VALUES\n" +
                        "('" + prodId + "'," + quant + "," + wholeSale + "," + salePrice + ",'" + supplier_id + "');");
                conn.commit();

                stmt.executeUpdate("UPDATE discord_orders SET  order_status = 'Complete' WHERE order_id = '" + discordOrderId + "';");
                conn.commit();
                event.getChannel().sendMessage("Order processed!").queue();
            }else {
                event.getChannel().sendMessage("Order already processed").queue();
            }
            rs.close();
            stmt.close();
            conn.commit();
            conn.close();

        } catch (Exception e){
            e.printStackTrace();
            event.getChannel().sendMessage("Order Not Found").queue();

        }
    }
}
