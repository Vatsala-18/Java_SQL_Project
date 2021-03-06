import java.awt.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
public class TablePro extends JFrame implements ActionListener{
   
    Connection con;
    Statement st;
    String query;
	JFrame frame = new JFrame("My Data");;
	
	JLabel l1 = new JLabel("Registration Form");
	JLabel l2 = new JLabel("E.No   : ");
	JLabel l3 = new JLabel("Name   : ");
	JLabel l4 = new JLabel("Mobile : ");
	JLabel l5 = new JLabel("Course : ");
	JLabel l6 = new JLabel("Univ   : ");
	
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();
	JTextField tf5 = new JTextField();
	
	JButton bu = new JButton("Update"); 
	JButton ba = new JButton("Add");
    JButton bd = new JButton("Delete");
	
	JTable jt;
	DefaultTableModel m = new DefaultTableModel();
	
	String cn[] = {"E.NO","NAME","MOBILE","COURSE","UNIVERSITY"};
	Object data[] = new Object[5];
		
	TablePro(){

		setSize(2000, 1100);
        setLayout(null);
		getContentPane().setBackground(Color.DARK_GRAY);
		
		l1.setBounds(50,50,800,100);
		l1.setFont(new Font("Serif",Font.BOLD,60));
		l1.setForeground(Color.white);
		
		
		l2.setBounds(40,200,150,50);
		l2.setFont(new Font("Serif",Font.BOLD,30));
		l2.setForeground(Color.white);
		
		l3.setBounds(40,300,150,50);
		l3.setFont(new Font("Serif",Font.BOLD,30));
		l3.setForeground(Color.white);
		
		l4.setBounds(40,400,150,50);
		l4.setFont(new Font("Serif",Font.BOLD,30));
		l4.setForeground(Color.white);
		
		l5.setBounds(40,500,150,50);
		l5.setFont(new Font("Serif",Font.BOLD,30));
		l5.setForeground(Color.white);
		
		l6.setBounds(40,600,150,50);
		l6.setFont(new Font("Serif",Font.BOLD,30));
		l6.setForeground(Color.white);
		
		tf1.setBounds(200,200,300,50);
		tf1.setFont(new Font("Serif",Font.PLAIN,30));
		
		tf2.setBounds(200,300,300,50);
		tf2.setFont(new Font("Serif",Font.PLAIN,30));
		
		tf3.setBounds(200,400,300,50);
		tf3.setFont(new Font("Serif",Font.PLAIN,30));
		
		tf4.setBounds(200,500,300,50);
		tf4.setFont(new Font("Serif",Font.PLAIN,30));
		
		tf5.setBounds(200,600,300,50);
		tf5.setFont(new Font("Serif",Font.PLAIN,30));
		
		ba.setBounds(80,700,150,50);
		ba.setFont(new Font("MV Boli",Font.BOLD,30));
		ba.setFocusable(false);
		ba.setBackground(Color.black);
		ba.setForeground(Color.white);
		ba.addActionListener(this);
		
		bu.setBounds(280,700,150,50);
		bu.setFont(new Font("MV Boli",Font.BOLD,30));
		bu.setFocusable(false);
		bu.setBackground(Color.black);
		bu.setForeground(Color.white);
		bu.addActionListener(this);

        bd.setBounds(180,780,150,50);
		bd.setFont(new Font("MV Boli",Font.BOLD,30));
		bd.setFocusable(false);
		bd.setBackground(Color.black);
		bd.setForeground(Color.white);
		bd.addActionListener(this);
		
		jt = new JTable();
		Container c=getContentPane();
		c.setLayout(null);	
		jt.getTableHeader().setOpaque(false);
		jt.getTableHeader().setBackground(Color.orange);
		jt.getTableHeader().setForeground(Color.black);
		jt.getTableHeader().setFont(new Font("MV Boli",Font.BOLD,20));
		
		m.setColumnIdentifiers(cn);
		
		jt.setModel(m);
		
		jt.setForeground(Color.black);
		jt.setBackground(Color.LIGHT_GRAY);
		jt.setFont(new Font("Serif",Font.PLAIN,20));
		jt.setRowHeight(30);
		jt.setGridColor(Color.ORANGE);
		jt.setShowHorizontalLines(true);
		
		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(800, 90, 1000, 700);
		
		add(sp);
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);
		add(tf1);
		add(tf2);
		add(tf3);
		add(tf4);
		add(tf5);
		add(ba);
		add(bu);
        add(bd);
		
        setVisible(true);

        try {            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/univStudents", "root", "groot");
            System.out.println("Connection Successful..");
            st = con.createStatement();    
        } catch (Exception e) {
            System.out.println(e);
        }
        showDatabase();
}
   public void showDatabase(){

       try{
            PreparedStatement pstm = con.prepareStatement("select * from StudentData");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                m.addRow(new Object[]{Rs.getInt(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5)});
             }
        }catch(Exception e){}
   }

    public void addUp(){

        query = "Insert into StudentData values ('"+Integer.parseInt(tf1.getText())+"','"+tf2.getText()+"','"+tf3.getText()+"','"+tf4.getText()+"','"+tf5.getText()+"')";
        System.out.println(query);
        data[0] = Integer.parseInt(tf1.getText());
		data[1] = tf2.getText();
		data[2] = tf3.getText();
		data[3]= tf4.getText();
		data[4]= tf5.getText();
	}

    public void insert(){

        try {    
            addUp();
            st.executeUpdate(query);
            System.out.println("Insertion Successful...");
            st.close(); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    	
	public void update(){

        String query = "Update StudentData set name='"+tf2.getText()+"',mobile='"+tf3.getText()+"',course='"+tf4.getText()+"',univ='"+tf5.getText()+"' where ENo="+Integer.parseInt(tf1.getText());
        System.out.println(query);
        try {
            st.executeUpdate(query);
            System.out.println("Update Successful...");
           
        } catch (Exception e) {
            System.out.println(e);
        }
	}

    public void delete(){

        String query = "Delete from StudentData where ENo="+Integer.parseInt(tf1.getText());
        System.out.println(query);
        try {
            st.executeUpdate(query);
            System.out.println("Deletion Successful...");
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	public void actionPerformed(ActionEvent e){

        m.setRowCount(0);
        
		if(e.getSource() == ba)
		{
			insert();
			m.addRow(data);
            showDatabase();
   
		}
		if(e.getSource() == bu)
		{
			update();
            showDatabase();
		}
        if(e.getSource() == bd)
		{
			delete();
            showDatabase();
		}
	}
	
	public static void main(String [] arr){
        
		new TablePro();
	}
}