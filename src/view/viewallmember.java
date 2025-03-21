package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.connection;

public class viewallmember extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewallmember frame = new viewallmember();
					frame.setVisible(true);
					frame.setLayout(null);
					frame.setTitle("VIEW ALL MEMBER");
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public viewallmember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		Color myColor = new Color(192, 192, 192);
		contentPane.setBackground(myColor);
		
	    LineBorder border = new LineBorder(Color.RED, 2);
	    contentPane.setBorder(border);
	    
	
	 
		
		JButton backbtn8=new JButton("Back To Home");
		
		  backbtn8.setSize(180,35);
	        backbtn8.setLocation(10,600);
	        backbtn8.setBackground(Color.BLUE);
	        backbtn8.setForeground(Color.WHITE);
	        contentPane.add(backbtn8);
	        backbtn8.setBorder(border);
	        backbtn8.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Home.main(new String[] {});
					dispose();
				}
			});
	        
	        try {

    			connection d = new connection();
    			d.createConnection();
    			Connection con = d.conn();
	            Statement statement = con.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM addmember"); 

	            // Create a table for jpl2
	            JTable table = new JTable(buildTableModel(resultSet));
	            JScrollPane scrollPane = new JScrollPane(table);
	            scrollPane.setBounds(70, 50, 700, 500);
	            contentPane.add(scrollPane);

	            resultSet.close();
	            statement.close();
	            con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    
	    public static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
	        java.sql.ResultSetMetaData metaData = resultSet.getMetaData();

	      
	        int columnCount = metaData.getColumnCount();
	        String[] columnNames = new String[columnCount];
	        for (int i = 1; i <= columnCount; i++) {
	            columnNames[i - 1] = metaData.getColumnName(i);
	        }

	        // Create data array
	        Object[][] data = new Object[30][columnCount];

	        int rowCount = 0;
	        while (resultSet.next() && rowCount < 30) { 
	            for (int i = 1; i <= columnCount; i++) {
	                data[rowCount][i - 1] = resultSet.getObject(i);
	            }
	            rowCount++;
	        }

	        return new DefaultTableModel(data, columnNames);
	}
	

}
