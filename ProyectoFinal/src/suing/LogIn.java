package suing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import conexión.CnLogIn;


@SuppressWarnings("serial")
public class LogIn extends JFrame {

	public static String Usuario;
	private JPanel contentPane;
	private JTextField tFUsuario;
	private JPasswordField pFContraseña;
	private int login_as;


	public LogIn() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		Thread.currentThread().setPriority((int)(Thread.MAX_PRIORITY*0.8));
		setResizable(false);
		setTitle("Cl\u00EDnica UNAC - Ingreso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((Toolkit.getDefaultToolkit().getScreenSize().width)/2-186, (Toolkit.getDefaultToolkit().getScreenSize().height)/2-152, 372, 304);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setForeground(new Color(216, 191, 216));
		layeredPane.setBackground(new Color(100, 149, 237));
		layeredPane.setBounds(0, 10, 366, 254);
		contentPane.add(layeredPane);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBackground(new Color(100, 149, 237));
		layeredPane_1.setBounds(20, 25, 325, 225);
		contentPane.add(layeredPane_1);
		layeredPane_1.setVisible(false);
		
		JLabel lblProgramadvance = new JLabel("Cl\u00EDnica UNAC");
		lblProgramadvance.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramadvance.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		lblProgramadvance.setBounds(20, 10, 325, 50);
		contentPane.add(lblProgramadvance);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(55, 160, 95, 20);
		layeredPane_1.add(lblPassword);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIngresar.setBounds(128, 200, 90, 25);
		layeredPane_1.add(btnIngresar);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUser.setBounds(55, 104, 61, 20);
		layeredPane_1.add(lblUser);
		
		JLabel lblConectandoseComo = new JLabel("Conectandose como:");
		lblConectandoseComo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConectandoseComo.setBounds(0, 61, 167, 14);
		layeredPane_1.add(lblConectandoseComo);
		
		JLabel lblLoginAs = new JLabel("");
		lblLoginAs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLoginAs.setBounds(149, 61, 102, 14);
		layeredPane_1.add(lblLoginAs);
		
		JLabel lblCambiar = new JLabel("Cambiar");
		lblCambiar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCambiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				layeredPane_1.setVisible(false);
				layeredPane.setVisible(true);
				tFUsuario.setText("");
				pFContraseña.setText("");
			}
		});
		lblCambiar.setBounds(261, 61, 61, 14);
		layeredPane_1.add(lblCambiar);
		
		JButton btnAdministrador = new JButton();
		btnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.setVisible(false);
				layeredPane_1.setVisible(true);
				login_as=1;
				lblLoginAs.setText("Administrador");
			}
		});
		btnAdministrador.setBounds(10, 70, 105, 135);
		ImageIcon IAcmon=new ImageIcon("administrador.png");
		Icon IconoAcmon=new ImageIcon(IAcmon.getImage().getScaledInstance(btnAdministrador.getWidth(), btnAdministrador.getHeight()-10, Image.SCALE_SMOOTH));
		btnAdministrador.setIcon(IconoAcmon);
		layeredPane.add(btnAdministrador);
		
		JButton btnDoctor = new JButton();
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.setVisible(false);
				layeredPane_1.setVisible(true);
				login_as=3;
				lblLoginAs.setText("Doctor");
			}
		});
		btnDoctor.setBounds(251, 71, 105, 135);
		ImageIcon IDoc=new ImageIcon("doctor.png");
		Icon IconoDoc=new ImageIcon(IDoc.getImage().getScaledInstance(btnDoctor.getWidth(), btnDoctor.getHeight()-10, Image.SCALE_SMOOTH));
		btnDoctor.setIcon(IconoDoc);
		layeredPane.add(btnDoctor);
		
		JButton btnCliente = new JButton("");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.setVisible(false);
				layeredPane_1.setVisible(true);
				login_as=2;
				lblLoginAs.setText("Recepcionista");
			}
		});
		btnCliente.setBounds(125, 71, 116, 135);
		ImageIcon IClient=new ImageIcon("recepcionista.png");
		Icon IconoCliente=new ImageIcon(IClient.getImage().getScaledInstance(btnCliente.getWidth(), btnCliente.getHeight()-10, Image.SCALE_SMOOTH));
		btnCliente.setIcon(IconoCliente);
		layeredPane.add(btnCliente);
		
		JLabel lblAdministrador = new JLabel("Administrador");
		lblAdministrador.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrador.setBounds(10, 227, 95, 14);
		layeredPane.add(lblAdministrador);
		
		JLabel lblPersonalMedico = new JLabel("Doctor");
		lblPersonalMedico.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPersonalMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonalMedico.setBounds(261, 227, 95, 14);
		layeredPane.add(lblPersonalMedico);
		
		JLabel lblCliente = new JLabel("Recepcionista");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCliente.setBounds(135, 227, 95, 14);
		layeredPane.add(lblCliente);
		
		tFUsuario = new JTextField();
		tFUsuario.setBounds(186, 108, 86, 20);
		layeredPane_1.add(tFUsuario);
		tFUsuario.setColumns(10);
		
		pFContraseña = new JPasswordField();
		pFContraseña.setDropMode(DropMode.INSERT);
		pFContraseña.setBounds(186, 164, 90, 20);
		layeredPane_1.add(pFContraseña);
		
		tFUsuario.addKeyListener(new PresionarTecla());
		
		pFContraseña.addKeyListener(new PresionarTecla());
		
		btnIngresar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(tFUsuario.getText().trim().equals("")||pFContraseña.getText().trim().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null," DEBE LLENAR DATOS"," "+ "VALIDACION",JOptionPane.ERROR_MESSAGE);
					
				}
				else {
				try {
					if(CnLogIn.login(tFUsuario.getText().trim(), pFContraseña.getText().trim(), login_as))
					{
						switch(login_as)
						{
							case 1:
								Administrador administrador=new Administrador();
								administrador.setVisible(true);
								break;
							case 2:
								Recepcionista recepcionista=new Recepcionista();
								recepcionista.setVisible(true);
								break;
							case 3:
								PersonalMedico personalMedico=new PersonalMedico(Usuario);
								personalMedico.setVisible(true);
								dispose();
						}
						
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"DATOS INCORRECTOS");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}}
			}
		});
		
	}
	
	public class PresionarTecla extends KeyAdapter {
		@SuppressWarnings("deprecation")
		public void keyPressed(KeyEvent ke) {
	          if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
	        	  try {
						if(CnLogIn.login(tFUsuario.getText(), pFContraseña.getText(), login_as))
						{
							switch(login_as)
							{
								case 1:
									Administrador administrador=new Administrador();
									administrador.setVisible(true);
									break;
								case 2:
									Recepcionista recepcionista=new Recepcionista();
									recepcionista.setVisible(true);
									break;
								case 3:
									PersonalMedico personalMedico=new PersonalMedico(Usuario);
									personalMedico.setVisible(true);
							}
							dispose();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
	          }
	      }
	}
}