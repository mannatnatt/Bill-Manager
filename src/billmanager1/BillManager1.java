/*

Mannat Natt
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billmanager1;


import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.input.InputMethodRequests;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;
/**

 *
 * @author Mannat Natt
 */
public class BillManager1 extends Application {
    private final Stage mainstage=new Stage(StageStyle.TRANSPARENT);
    Button homebtn=new Button("Home");
    private final double height=500;
    private final double width=500;
     double initX;
    double initY;
    int i=0;
    
    @Override
    public void start(Stage mainstage1) {
        homebtn.setVisible(false);
        Label wel=new Label("Welcome");
        wel.setFont(Font.loadFont(BillManager1.class.getResourceAsStream("/billmanager1/3d.otf"),30));
        wel.setStyle("-fx-text-fill:white;");
        Label to=new Label("to");
        to.setFont(Font.loadFont(BillManager1.class.getResourceAsStream("/billmanager1/3d.otf"),30));
        to.setStyle("-fx-text-fill:white;");
        Label man=new Label("Bill manager");
        man.setFont(Font.loadFont(BillManager1.class.getResourceAsStream("/billmanager1/3d.otf"),30));
        man.setStyle("-fx-text-fill:white;");
        String pass="12345";
        PasswordField pf=new PasswordField();
        pf.setLayoutX(160);
        pf.setLayoutY(230);
        
        String[] colors=new String[9];
        //#ff9999,#33cc00,#0066ff,#9900ff
        colors[0]="#ff9999";
        colors[1]="#33cc00";
        colors[2]="#0066ff";
        colors[3]="#9900ff";
        colors[4]="#ccccff";
        colors[5]="#00ffcc";
        colors[6]="burlywood";
        colors[7]="yellowgreen";
        colors[8]="violet";
        pf.setOnKeyPressed((KeyEvent event) -> {
            
            
            if(event.getCode().equals(KeyCode.ENTER)){
                if(pf.getText().equals(pass)){
                    homebtn.setVisible(true);
                    mainpage(mainstage);
                }else{
                    if(i==9){
                        i=0;
                    }
                    pf.setStyle("-fx-background-color:"+colors[i]+";");
                    i++;
                    
                    
                   
                    
                    
                }
            }
        });
        Pane pane=new Pane(wel,to,man,pf);
        
        
        Scene scene=new Scene(pane, 500, 500);
        controls(pane,mainstage,scene);
        pane.setStyle("-fx-background-color:linear-gradient(#328BDB 0%, #207BCF 25%, #1973C9 75%, #0A65BF 100%);");
        mainstage.setScene(scene);
        mainstage.show();
       
                
                 
                TranslateTransition anit=new TranslateTransition(Duration.seconds(1),wel);
                TranslateTransition anit2=new TranslateTransition(Duration.seconds(1.5),to);
                TranslateTransition anit3=new TranslateTransition(Duration.seconds(2),man);
                anit.setToX(80);
                anit.setToY(170);
                anit2.setToX(210);
                anit2.setToY(170);
                anit3.setToX(250);
                anit3.setToY(170);
                
                anit.play();
                anit2.play();
                anit3.play();
                
           
    }
    
    
    private void controls(Pane pane,Stage stage,Scene scene){
        Button close=new Button("X");
        
        //homebtn=new Button("Home");
        homebtn.setLayoutX(0);
        homebtn.setStyle("-fx-background-color:#33cc00;-fx-background-insets: 0;-fx-text-fill: white;-fx-padding: 5 10 5 10;-fx-border-radius:100;-fx-background-radius: 10,10,9;");
        homebtn.setOnAction((ActionEvent event) -> {
            mainpage(mainstage);
        });
        close.setStyle("-fx-background-color:red;-fx-background-insets: 0;-fx-text-fill: white;-fx-padding: 5 10 5 10;");
        Button min=new Button("_");
        min.setStyle("-fx-background-color:white;-fx-background-insets: 0;-fx-text-fill: black;-fx-padding: 5 10 5 10;");
        Button dragbtn=new Button("f");
        dragbtn.setPrefWidth(450);
        dragbtn.setOpacity(0);
        close.setLayoutX(470);
        min.setLayoutX(444);
        
        //.setOpacity(0);
        close.setOnAction((ActionEvent event) -> {
            mainstage.close();
        });
        dragbtn.setOnMousePressed((MouseEvent me) -> {
            initX = me.getScreenX() -mainstage.getX();
            initY = me.getScreenY() -mainstage.getY();
        });
        dragbtn.setOnMouseDragged((MouseEvent me) -> {
            mainstage.setX(me.getScreenX() - initX);
            mainstage.setY(me.getScreenY() - initY);
        });
        Rectangle rect=new javafx.scene.shape.Rectangle(500, 500);
        rect.setArcHeight(30);
        rect.setArcWidth(30);
        pane.setClip(rect);
        scene.setFill(null);
        scene.getStylesheets().add(BillManager1.class.getResource("/billmanager1/list.css").toExternalForm());
    pane.getChildren().addAll(close,min,dragbtn,homebtn);
    pane.setStyle("-fx-background-color:#ccff66;");
    
    }
    private void billview(Stage x){
        retreivetable();
        Label line=new Label("Select from Date below");
        line.setLayoutX(300);
        line.setLayoutY(50);
        Label line2=new Label("Select Bill");
        line2.setLayoutX(100);
        line2.setLayoutY(50);
        ObservableList ol=FXCollections.observableArrayList();
        
        ChoiceBox chbox=new ChoiceBox(FXCollections.observableArrayList(bills));
        chbox.setStyle("fx-background-color:red;");
        chbox.setLayoutX(100);
        chbox.setLayoutY(100);
       
       Button open22=new Button("View");
       open22.setLayoutX(100);
       open22.setLayoutY(150);
       
        ListView lv=new ListView();
        lv.setBackground(Background.EMPTY);
        lv.setMaxWidth(180);
        lv.setMaxHeight(400);
        lv.setLayoutX(300);
        lv.setLayoutY(75);
       //String tablename=JOptionPane.showInputDialog("Enter Table name to open");
        String host="jdbc:derby:C:\\Users\\Mannat Natt\\Documents\\NetBeansProjects\\BillManager1\\billdatabase";
        String name="redneston";
        String pass="opsharma";
        open22.setOnAction((ActionEvent event) -> {
            //String tablename=chbox.getValue().toString();
            Object tablename=chbox.getValue();
            ol.clear();
            
            if(tablename!=null){try {
                int heightcount=0;
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection con=DriverManager.getConnection(host, name, pass);
                String sql="select * from "+tablename+"";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    ol.add(rs.getString(1));
                    heightcount++;
                }
                lv.setPrefHeight(42*heightcount);
                lv.setItems(ol);
                con.close();
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            }
        });
        
        
       
        
        
        
        
        
      lv.setOnMouseClicked((MouseEvent event) -> {
          try {
              String aa="";
              String tablename=chbox.getValue().toString();
              Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
              Connection con=DriverManager.getConnection(host, name, pass);
              String sql="select * from  "+tablename+" where date='"+lv.getSelectionModel().getSelectedItem().toString()+"'";
              PreparedStatement ps1=con.prepareStatement(sql);
              ResultSet rs1=ps1.executeQuery();
              while(rs1.next()){
                  aa=rs1.getString(3);
              }
              File tempf=File.createTempFile(pass,"."+aa+"");
              //=new File("D:\\temp\\newfile."+aa+"");
              OutputStream os=new FileOutputStream(tempf);
              PreparedStatement ps=con.prepareStatement(sql);
              ResultSet rs=ps.executeQuery();
              while(rs.next()){
                  Blob bl=rs.getBlob(2);
                  int length=(int) bl.length();
                  
                  os.write(bl.getBytes(1,length));
                  os.flush();
                  os.close();
                  Desktop openf=Desktop.getDesktop();
                  openf.open(tempf);
                  
                  
              }
              // tempf.deleteOnExit();
              ps.close();
              con.close();
              
          } catch (ClassNotFoundException | SQLException | IOException e) {
              JOptionPane.showMessageDialog(null, e);
          }
        });
        
        
        Pane pane=new Pane(line2,open22,chbox,lv,line);
    Scene scene=new Scene(pane, width, height);
    controls(pane,mainstage,scene);
    scene.getStylesheets().add(BillManager1.class.getResource("/billmanager1/list.css").toExternalForm());
    x.setScene(scene);
    
    
    }
    
    private void mainpage(Stage x){
    Button addbtn=new Button("ADD");
    addbtn.setLayoutX(200);
    addbtn.setLayoutY(50);
    Button viewbtn=new Button("View");
    viewbtn.setLayoutX(200);
    viewbtn.setLayoutY(100);
    Button del=new Button("Delete");
    del.setLayoutX(200);
    del.setLayoutY(150);
    del.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
     delcell();
        }
    });
    viewbtn.setOnAction((ActionEvent event) -> {
        billview(mainstage);
    });
    Button mangbtn=new Button("Manage");
    mangbtn.setLayoutX(200);
    mangbtn.setLayoutY(200);
    addbtn.setOnAction((ActionEvent event) -> {
        Connection con;
        addpage(mainstage);
    });
    
    mangbtn.setOnAction((ActionEvent event) -> {
        managepage(mainstage);
    });
        Pane pane=new Pane();
        pane.getChildren().addAll(addbtn,mangbtn,viewbtn,del);
        Scene scene=new Scene(pane, 500, 500);
        controls(pane,mainstage,scene);
        x.setScene(scene);
    }
    
    
    ArrayList<String> bills=new ArrayList<>();
    File billfile;
    
    
    private void delcell(){
     retreivetable();
        Label line=new Label("Select from Date below");
        line.setLayoutX(300);
        line.setLayoutY(50);
        Label line2=new Label("Select Bill");
        line2.setLayoutX(100);
        line2.setLayoutY(50);
        ObservableList ol=FXCollections.observableArrayList();
        ChoiceBox chbox=new ChoiceBox(FXCollections.observableArrayList(bills));
        chbox.setStyle("fx-background-color:red;");
        chbox.setLayoutX(100);
        chbox.setLayoutY(100);
       
       Button open22=new Button("View");
       open22.setLayoutX(100);
       open22.setLayoutY(150);
       
        ListView lv=new ListView();
        lv.setBackground(Background.EMPTY);
        lv.setMaxWidth(180);
        lv.setMaxHeight(400);
        lv.setLayoutX(300);
        lv.setLayoutY(75);
       //String tablename=JOptionPane.showInputDialog("Enter Table name to open");
        String host="jdbc:derby:C:\\Users\\Mannat Natt\\Documents\\NetBeansProjects\\BillManager1\\billdatabase";
        String name="redneston";
        String pass="opsharma";
        
        open22.setOnAction((ActionEvent event) -> {
            //String tablename=chbox.getValue().toString();
            Object tablename=chbox.getValue();
            ol.clear();
            
            if(tablename!=null){try {
                int heightcount=0;
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection con=DriverManager.getConnection(host, name, pass);
                String sql="select * from "+tablename+"";
                PreparedStatement ps=con.prepareStatement(sql);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    ol.add(rs.getString(1));
                    heightcount++;
                }
                lv.setPrefHeight(42*heightcount);
                lv.setItems(ol);
                con.close();
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            }
        });
        
        
       
        
        
        
        
        
      lv.setOnMouseClicked((MouseEvent event) -> {
          try {
              String aa="";
              String tablename=chbox.getValue().toString();
              Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
              Connection con=DriverManager.getConnection(host, name, pass);
              String sql="Delete from "+chbox.getValue()+" where DATE='"+lv.getSelectionModel().getSelectedItem()+"'";
              PreparedStatement ps1=con.prepareStatement(sql);
              int response=JOptionPane.showConfirmDialog(null, "Are you sure to delete", null, JOptionPane.YES_NO_OPTION);
              if(response==0){
              ps1.execute();
              }
              ps1.close();
              int heightcount=0;
              String sql1="select * from "+tablename+"";
                PreparedStatement ps2=con.prepareStatement(sql1);
                ResultSet rs=ps2.executeQuery();
                while(rs.next()){
                    ol.add(rs.getString(1));
                    heightcount++;
                }
                lv.setPrefHeight(42*heightcount);
                lv.setItems(ol);
                ps2.close();
            
            con.close();
            
              
          } catch (ClassNotFoundException | SQLException e) {
              JOptionPane.showMessageDialog(null, e);
          }
        });
        
        
        Pane pane=new Pane(line2,open22,chbox,lv,line);
    Scene scene=new Scene(pane, width, height);
    controls(pane,mainstage,scene);
    scene.getStylesheets().add(BillManager1.class.getResource("/billmanager1/list.css").toExternalForm());
    mainstage.setScene(scene);
    
    }
    private void addpage(Stage x){
        
        retreivetable();
        
        Button save=new Button("Save");
        save.setLayoutX(200);
        save.setLayoutY(200);
        DatePicker datepickr=new DatePicker();
        datepickr.setLayoutX(200);
        datepickr.setLayoutY(100);
        FileChooser filechsr = new FileChooser();
        Button billselt=new Button("Select Bill");
        billselt.setLayoutX(200);
        billselt.setLayoutY(150);
        
        
        
        billselt.setOnAction((ActionEvent event) -> {
            billfile=filechsr.showOpenDialog(x);
        });
        ChoiceBox chbox=new ChoiceBox(FXCollections.observableArrayList(bills));
        chbox.setLayoutX(200);
        chbox.setLayoutY(50);
        
              save.setOnAction((ActionEvent event) -> {
                  String host="jdbc:derby:C:\\Users\\Mannat Natt\\Documents\\NetBeansProjects\\BillManager1\\billdatabase";
                  String name="redneston";
                  String pass="opsharma";
                  
                  if(chbox.getValue()!=null&&datepickr.getValue()!=null){
                      try {
                           int day=datepickr.getValue().getDayOfMonth();
                String month=datepickr.getValue().getMonth().toString();
                int year=datepickr.getValue().getYear();
                          InputStream ip=new FileInputStream(billfile);
                          String ex=FilenameUtils.getExtension(billfile.getName());
                          Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                          Connection con=DriverManager.getConnection(host, name, pass);
                          String sql="INSERT INTO "+chbox.getValue()+" (DATE,FILE,EXTENSION) VALUES (?,?,?)";
                          PreparedStatement ps=con.prepareStatement(sql);
                          ps.setString(1, ""+day+" "+month+" "+year+"");
                          ps.setBlob(2, ip);
                          ps.setString(3, ex);
                          ps.execute();
                          JOptionPane.showMessageDialog(null, "Bill Added successfully");
                          ps.close();
                          con.close();
                      } catch (FileNotFoundException | ClassNotFoundException | SQLException | HeadlessException  |NullPointerException e) {
                          JOptionPane.showMessageDialog(null, e);
                      }}
        });
        
        Pane pane=new Pane(chbox,datepickr,billselt,save);
          
        Scene scene=new Scene(pane, 500, 500);
        controls(pane,mainstage,scene);
        scene.getStylesheets().add(BillManager1.class.getResource("/billmanager1/list.css").toExternalForm());
        mainstage.setScene(scene);
       
        
    
    }
    private void managepage(Stage x){
        Button addbtn=new Button("Add New Datebase");
        addbtn.setLayoutX(200);
        addbtn.setLayoutY(50);
        Button delbtn=new Button("Delete");
        delbtn.setLayoutX(200);
        delbtn.setLayoutY(100);
        delbtn.setOnAction((ActionEvent event) -> {
            deletetable(mainstage);
        });
        addbtn.setOnAction((ActionEvent event) -> {
          String tablename=JOptionPane.showInputDialog("Tablename");
            createtable(tablename);
            
            
            
            //System.out.print(jp.getValue());
        });
        
        Pane pane=new Pane(addbtn,delbtn);
        Scene scene=new Scene(pane,500, 500);
        controls(pane,mainstage,scene);
        x.setScene(scene);
        
    }
    
    private void deletetable(Stage stage){
        retreivetable();
        ChoiceBox chbox=new ChoiceBox(FXCollections.observableList(bills));
        chbox.setLayoutX(200);
        chbox.setLayoutY(50);
        
        Button delete=new Button("Delete");
        delete.setLayoutX(200);
        delete.setLayoutY(100);
        delete.setOnAction((ActionEvent event) -> {
            String host="jdbc:derby:C:\\Users\\Mannat Natt\\Documents\\NetBeansProjects\\BillManager1\\billdatabase";
            String name="redneston";
            String pass="opsharma";
            if(chbox.getValue()!=null){
                try {
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                    Connection con=DriverManager.getConnection(host, name, pass);
                    String sql="drop table "+chbox.getValue()+"";
                    PreparedStatement ps=con.prepareStatement(sql);
                    int response=JOptionPane.showConfirmDialog(null, "Are You sure to delete", null, JOptionPane.YES_NO_OPTION);
                    if(response==0){
                        ps.execute();
                        JOptionPane.showMessageDialog(null, "Deletion Succesful");
                        retreivetable();
                        chbox.setItems(FXCollections.observableList(bills));}
                    ps.close();
                    con.close();
                } catch (ClassNotFoundException | SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, e);
                }}
        });
         
    Pane pane=new Pane(chbox,delete);
    Scene scene=new Scene(pane, width, height);
        controls(pane, mainstage, scene);
    stage.setScene(scene);
    
        
        
        
        
        
        
        
    }
    
    private void createtable(String tablename){
        String host="jdbc:derby:C:\\Users\\Mannat Natt\\Documents\\NetBeansProjects\\BillManager1\\billdatabase";
        String name="redneston";
        String pass="opsharma";
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con=DriverManager.getConnection(host, name, pass);
            String sql="CREATE TABLE "+tablename+" (DATE VARCHAR(20) NOT NULL, FILE BLOB NOT NULL, EXTENSION VARCHAR(10) NOT NULL, PRIMARY KEY (DATE))";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.execute();
            JOptionPane.showMessageDialog(null, "New Billbase created successfully");
            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    private void retreivetable(){
        bills.clear();
    String host="jdbc:derby:C:\\Users\\Mannat Natt\\Documents\\NetBeansProjects\\BillManager1\\billdatabase";
        String name="redneston";
        String pass="opsharma";
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con=DriverManager.getConnection(host, name, pass);
            String[] type={"TABLE"};
            DatabaseMetaData md=con.getMetaData();
            ResultSet rs=md.getTables(null, null, "%",type);
            while(rs.next()){
            bills.add(rs.getString(3));
        }
            
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
