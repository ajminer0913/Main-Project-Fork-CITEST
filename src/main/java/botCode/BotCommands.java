package botCode;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import oldCode.CustOrder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BotCommands extends ListenerAdapter {

    CustOrder cust = CustOrder.getInstance();
    Connection conn = cust.connect();
    public String prefix = "!";

    /**
     * Logic for when the bot sees a message in the chat
     * @param event the message in chat
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String[] args = event.getMessage().getContentRaw().split(" ");
        String user = event.getAuthor().getAsMention();
        String email;
        int location;
        String product_id;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currDate = dateFormat.format(date);
        int quantity;

        if(args[0].equalsIgnoreCase(prefix + "order")){
            event.getChannel().sendMessage("Hello " + user).queue();
            event.getChannel().sendMessage("If you would like to place an order, please " +
                    "enter the following in order separated by spaces:").queue();
            event.getChannel().sendMessage("!createorder email location(int) product id quantity(int)").queue();
        }
        if(args[0].equalsIgnoreCase(prefix + "createorder")){
            email = args[1];
            location = Integer.parseInt(args[2]);
            product_id = args[3];
            quantity = Integer.parseInt(args[4]);
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

                String out = "INSERT INTO cust_orders (date, cust_email, cust_location, product_id, product_quantity)"
                        + "VALUES('" + currDate + "','" + email +"'," + location + ",'" + product_id +"'," + quantity + ");";

                stmt.executeUpdate(out);
                stmt.close();
                conn.commit();

            } catch (Exception e) {
                e.printStackTrace();
                event.getChannel().sendMessage("Error in creating orer").queue();
            }
            event.getChannel().sendMessage("Order Successfully Created").queue();
        }
    }
}
