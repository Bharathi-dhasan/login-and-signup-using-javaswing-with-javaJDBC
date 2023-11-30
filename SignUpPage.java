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
import java.sql.PreparedStatement;
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

public class SignUpPage implements ActionListener {
	
	static String url="jdbc:mysql://localhost:3306/javaswing";
	static String u="root";
	static String p="intmain";
	static Connection con;
	
	
	
	
	JFrame frame =new JFrame();
	JLabel name=new JLabel();
	JLabel pass=new JLabel();	
	JLabel title=new JLabel();	
	JPanel titleholder=new JPanel();
	JPanel userholder=new JPanel();
	JTextField username=new JTextField();
	JPasswordField password=new JPasswordField();
	JButton register=new JButton();
	JLabel login=new JLabel();
	
	SignUpPage() throws SQLException{
		con=DriverManager.getConnection(url,u,p);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(500,500);
		frame.setResizable(false);
		
		titleholder.setLayout(new BorderLayout());
		title.setText("RegisteR");
		title.setFont(new Font("Ink Free",Font.BOLD,74));
		title.setForeground(Color.blue);
		title.setVerticalAlignment(JLabel.CENTER);
		title.setHorizontalAlignment(JLabel.CENTER);
		
		titleholder.add(title);
		titleholder.setPreferredSize(new Dimension(600,100));
		
		userholder.setLayout(null);
		userholder.setBackground(Color.orange);
		
		name.setText("UserName");
		pass.setText("PassWord");
		pass.setSize(200,200);
		name.setBounds(100,80,120,12);
		pass.setBounds(100,145,120,12);
		
		username.setFont(new Font("MV Boli", Font.BOLD,34));
		password.setFont(new Font("MV Boli", Font.BOLD,34));
		
		username.setHorizontalAlignment(JTextField.CENTER);
		password.setHorizontalAlignment(JTextField.CENTER);
		
		username.setBounds(180,70,150,40);
		password.setBounds(180,125,150,40);
		
		
		register.setText("SiGn Up");
		register.setFont(new Font("MV Boli",Font.BOLD,34));
		register.setBounds(120,200,200,50);
		register.setBackground(Color.green);
		register.setForeground(Color.black);
		register.addActionListener(this);
		register.setFocusable(false);
		
		
		
		login.setText("LoGiN");
		login.setForeground(Color.BLUE);
		login.setFont(new Font("Ink Free",Font.BOLD,30));
		login.setBounds(180,280,150,40);
		login.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(e.getSource()==login) {
					frame.dispose();
					new loginpage();
				}
			
		}

		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==login) {
				login.setForeground(Color.red);
				login.setCursor(Cursor.getPredefinedCursor(12));
			}
			
		}

		public void mouseExited(MouseEvent e) {
			if(e.getSource()==login) {
				login.setForeground(Color.BLUE);
			}		
		}
		});
		
		userholder.add(name);
		userholder.add(pass);
		userholder.add(username);
		userholder.add(password);
		userholder.add(register);
		userholder.add(login);
		frame.add(titleholder,BorderLayout.NORTH);
		frame.add(userholder,BorderLayout.CENTER);
		frame.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String query="insert into javaswinggetdata values(?,?)";
		PreparedStatement pst;
		try {
			int result=0;
			String d=username.getText();
			String df=password.getText();
			if((!d.equals("") && !df.equals(""))) {
			if(check(d)){
				
				pst = con.prepareStatement(query);
				pst.setString(1,username.getText());
				pst.setString(2,password.getText());
				result=pst.executeUpdate();
				
				if(result==1) {
					JOptionPane.showMessageDialog(frame,"successfully registered");
					int a=JOptionPane.showConfirmDialog(frame, "Did you want to login?");
					if(a==0) {
						frame.dispose();
						new loginpage();
					}
				}
				
			}
			else  {
				JOptionPane.showMessageDialog(frame,"user is already registered");			}
			}
			else {
				JOptionPane.showMessageDialog(frame,"Invalid data");
				JOptionPane.showMessageDialog(frame,"please enter valid data");
			}
			}
		
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	public  boolean check(String d) throws SQLException {
		String query="select username  from javaswinggetdata where username = '"
				+d+"'";
				Statement st=con.createStatement();
				ResultSet rst=st.executeQuery(query);
				while(rst.next()) {
					if(rst.getString(1).equals(d)) {
						return false;
					}
				}
	
	return true;
	}
	
	
	

	public static void main(String[] args) throws SQLException {
		new SignUpPage();
		
	}

	

}
