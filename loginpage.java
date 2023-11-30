import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginpage  implements ActionListener{
    
	JFrame frame=new JFrame();
    JLabel username = new JLabel();
    JLabel password=new JLabel();
    JTextField user=new JTextField();
    
    JPanel titleholder=new JPanel();
    JPanel userholder=new JPanel();
    JLabel title=new JLabel();
    
    JPasswordField pass=new JPasswordField();
    JButton login=new JButton();
    JLabel register=new JLabel();
    
    
	loginpage(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setTitle("LoGiN using java JDBC");
		frame.setSize(500,500);
//		frame.setLayout(new BorderLayout());
		
		titleholder.setLayout(new BorderLayout());
		titleholder.setBackground(Color.yellow);
		userholder.setLayout(null);
		title.setText("LoGiN");
		title.setFont(new Font("Ink Free",Font.BOLD,74));
		title.setForeground(Color.blue);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setHorizontalAlignment(JLabel.CENTER);
		
		titleholder.add(title);
		titleholder.setPreferredSize(new Dimension(600,100));
		
		
		username.setBounds(100,80,120,12);
		password.setBounds(100,145,120,12);
		
		username.setText("UserName");
		password.setText("PassWord");
		login.setText("LoGiN");
		login.setFocusable(false);
		
		user.setFont(new Font("Ink Free",Font.BOLD,24));
		pass.setFont(new Font("Ink Free",Font.BOLD,24));
		login.setFont(new Font("Ink Free",Font.BOLD,24));
		
		
		user.setBackground(Color.gray);
		pass.setBackground(Color.gray);
		user.setForeground(Color.black);
		pass.setForeground(Color.black);
		login.setBackground(Color.green);
	    
		
		user.setHorizontalAlignment(JTextField.CENTER);
		pass.setHorizontalAlignment(JTextField.CENTER);
		
		login.setBounds(120,200,200,50);
		user.setBounds(180,70,150,40);
		pass.setBounds(180,125,150,40);
		
		register.setText("SiGn Up");
		register.setFont(new Font("Ink Free",Font.BOLD,24));
		register.setBounds(180,280,150,40);
		register.setForeground(Color.BLUE);
		register.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getSource()==register) {
					frame.dispose();
					try {
						new SignUpPage();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			public void mouseEntered(MouseEvent e) {
				 if(e.getSource()==register) {
					 register.setForeground(Color.red);
					 register.setCursor(Cursor.getPredefinedCursor(12));
				 }
				
			}
			public void mouseExited(MouseEvent e) {
				if(e.getSource()==register) {
					register.setForeground(Color.blue);
				}
				
			}
		});
		
		userholder.setBackground(Color.yellow);
		userholder.setOpaque(true);
		userholder.add(username);
		userholder.add(password);
		userholder.add(user);
		userholder.add(pass);
		userholder.add(login);
		userholder.add(register);
		
		login.addActionListener(this);
		
		frame.add(titleholder,BorderLayout.NORTH);
		frame.add(userholder,BorderLayout.CENTER);
	
		
		frame.setResizable(false);
		frame.setVisible(true);
		
		
		
      }
	public static void main(String[] args) {
		new loginpage();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String a=user.getText();
		String b=pass.getText();
		if(e.getSource()==login) {
			if(!a.equals("") && !b.equals("")) {
			try {
				checkwithdatabase(a,b);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
			else {
				JOptionPane.showMessageDialog(frame,"Invalid data");
				JOptionPane.showMessageDialog(frame,"please enter valid data");
			}
		}
	}
	
	
	private void checkwithdatabase(String username, String password) throws SQLException {
		String url="jdbc:mysql://localhost:3306/javaswing";
		String username1="root";
		String password1="intmain";
		Connection con=DriverManager.getConnection(url,username1,password1);
		String query="select *  from javaswinggetdata where username = '"
		+username+"' and password = '"+password+"'";

		Statement st=con.createStatement();
		ResultSet rst=st.executeQuery(query);
		boolean g=false;
		while(rst.next()) {
			if(rst.getString(1).equals(username) && rst.getString(2).equals(password)) {
				g=true;break;
			}
		}
		if(g==true)
		{JOptionPane.showMessageDialog(frame,"Successfully Logged");frame.dispose();
			JFrame fr=new JFrame();
			fr.setLayout(null);
			fr.getContentPane().setBackground(Color.black);
			JLabel k=new JLabel();
			k.setText("Hello "+username);
			k.setFont(new Font("Ink Free",Font.BOLD,36));
			k.setBackground(Color.black);
			k.setOpaque(true);
			fr.setOpacity(1);
			k.setForeground(Color.green);
			k.setBounds(100,180,250,50);
			fr.setSize(400,400);
			fr.add(k);
			fr.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(frame, "Invalid userName or password") ;
			int a=JOptionPane.showConfirmDialog(frame, "Do you want to Register");
			if(a==JOptionPane.YES_OPTION) {frame.dispose();
				new SignUpPage();
			}
			
		}
		

}

}
