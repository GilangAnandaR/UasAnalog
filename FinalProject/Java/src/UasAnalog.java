import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UasAnalog {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UasAnalog window = new UasAnalog();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UasAnalog() {
		initialize();
		Connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/uasanalog", "root","");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }

	    }
	 
	 public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from buku");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	
	
	
	
	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 782, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTokoBuku = new JLabel("Toko Buku");
		lblTokoBuku.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 25));
		lblTokoBuku.setBounds(342, 11, 245, 35);
		frame.getContentPane().add(lblTokoBuku);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Regristasi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 57, 300, 181);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nama Buku");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 28, 101, 21);
		panel.add(lblNewLabel);
		
		JLabel lblEdisi = new JLabel("Edisi");
		lblEdisi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEdisi.setBounds(10, 73, 101, 21);
		panel.add(lblEdisi);
		
		JLabel lblNewLabel_1_1 = new JLabel("Harga");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(10, 122, 101, 21);
		panel.add(lblNewLabel_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(104, 31, 86, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(104, 76, 86, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(104, 125, 86, 20);
		panel.add(txtprice);
		
		
		//tombol simpan\\
		JButton btnNewButton = new JButton("Simpan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
							
				 try {
					pst = con.prepareStatement("insert into buku(nama,edisi,harga)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "DATA TERSIMPAN! ! !");
					table_load();       
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(10, 249, 95, 35);
		frame.getContentPane().add(btnNewButton);
		
		//TOMBOL KELUAR\\
		JButton btnKeluar = new JButton("Keluar");
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnKeluar.setBounds(109, 249, 89, 35);
		frame.getContentPane().add(btnKeluar);
		
		//TOMBOL BERSIHKAN\\
		JButton btnBersihkan = new JButton("Bersihkan");
		btnBersihkan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
			}
		});
		btnBersihkan.setBounds(203, 249, 107, 35);
		frame.getContentPane().add(btnBersihkan);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 69, 424, 227);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Cari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 303, 283, 75);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblIdBuku = new JLabel("ID Buku");
		lblIdBuku.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIdBuku.setBounds(34, 25, 80, 21);
		panel_1.add(lblIdBuku);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 try {
			          
			            String id = txtbid.getText();
			                pst = con.prepareStatement("select nama,edisi,harga from buku where id = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            	
			            {
			              
			                String name = rs.getString(1);
			                String edition = rs.getString(2);
			                String price = rs.getString(3);
			                
			                txtbname.setText(name);
			                txtedition.setText(edition);
			                txtprice.setText(price);
			                      
			            }   
			            else
			            {
			            	txtbname.setText("");
			            	txtedition.setText("");
			                txtprice.setText("");
			                 
			            }

			        } 
				
				 catch (SQLException ex) {
			           
				 }
			
			}
			
			
		});
		txtbid.setColumns(10);
		txtbid.setBounds(110, 28, 138, 20);
		panel_1.add(txtbid);
		
		//TOMBOL UPDATE\\
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,bid;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid = txtbid.getText();
							
				 try {
					pst = con.prepareStatement("update buku set nama= ?,edisi=?,harga=? where id =?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "UPDATE TERSIMPAN! ! !");
					table_load();       
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(408, 310, 107, 35);
		frame.getContentPane().add(btnUpdate);
		
		//TOMBOL DELETE\\
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				bid = txtbid.getText();
				 try {
					pst = con.prepareStatement("delete from buku where id =?");
					pst.setString(1, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "DATA DIHAPUS! ! !");
					table_load();       
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(525, 310, 112, 35);
		frame.getContentPane().add(btnDelete);
	}
}
