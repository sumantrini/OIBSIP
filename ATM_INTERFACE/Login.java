import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener{

    JButton login, clear, signup;
    JTextField cardTextField;
    JPasswordField pinTextField;
    Login(){
        
        setTitle("AUTOMATED TELLER MACHINE");  //sets the title of the frame

        setLayout(null);

        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/bank_icon.png"));
        Image i2= i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT); //takes image from i1 and scale its width and height 
        ImageIcon i3= new ImageIcon(i2);
        JLabel label= new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        JLabel text= new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Oswald", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel card_no= new JLabel("Card No.: ");
        card_no.setFont(new Font("Oswald", Font.BOLD, 28));
        card_no.setBounds(150, 150, 150, 40);
        add(card_no);

        cardTextField= new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin= new JLabel("PIN: ");
        pin.setFont(new Font("Oswald", Font.BOLD, 28));
        pin.setBounds(150, 200, 400, 40);
        add(pin);

        pinTextField= new JPasswordField();
        pinTextField.setBounds(300, 200, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        login= new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        clear= new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        signup= new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.white);

        setSize(800,480);  //dimensions of frame
        setVisible(true);  //makes the frame visible
        setLocation(350, 100);  //can change the location of opening of the frame(left, right)
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== clear){
            cardTextField.setText("");
            pinTextField.setText("");
        }

        else if(ae.getSource()== login){
            Conn conn= new Conn();
            String cardnumber= cardTextField.getText();
            String pinnumber= pinTextField.getText();
            String query= "select * from login where cardnumber= '"+cardnumber+"' and pin= '"+pinnumber+"'  ";
            
            try{
                ResultSet rs= conn.s.executeQuery(query);

                if(rs.next()){
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card number or PIN");
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }

        else if(ae.getSource()== signup){
            setVisible(false);
            new SignupOne().setVisible(true);
            //to go to the SignUp page when clicked on sign up button
        }
    }
    public static void main(String args[]){

        new Login();
               
    }
}