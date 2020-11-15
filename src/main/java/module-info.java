module sample
{
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    opens server;
    opens client.Controllers;
    opens server.Categories;
    
    opens client to javafx.graphics, javafx.fxml;
    
}