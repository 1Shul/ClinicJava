package suing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;


@SuppressWarnings("serial")
public class Recepcionista extends JFrame {

	private JPanel contentPaneCliente;
	public String genero="";
	public String TDocumento="";
	public String Tipo="";
	public String Descripcion="";
	public String Conclusion ="";

	public Recepcionista() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setTitle("Cl\u00EDnica UNAC - Recepcionista");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		
		contentPaneCliente = new JPanel();
		contentPaneCliente.setBackground(new Color(100, 149, 237));
		contentPaneCliente.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneCliente);
		
		/*************************/
			/*BARRA DE MENU*/
		/*************************/
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(100, 149, 237));
		setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogIn logIn = new LogIn();
				logIn.setVisible(true);
				dispose();
			}
		});
		mnUsuario.add(mntmCerrarSesion);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAiuda = new JMenuItem("Me ama?");
		mntmAiuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "not bro,Ella no te ama");
			}
		});
		
		JMenu mnContacto = new JMenu("Contacto");
		mnAyuda.add(mnContacto);
		
		JMenuItem mntmAdministrador = new JMenuItem("Administrador");
		mnContacto.add(mntmAdministrador);
		
		JMenuItem mntmEnfermera = new JMenuItem("Enfermera");
		mnContacto.add(mntmEnfermera);
		mnAyuda.add(mntmAiuda);
		
		JMenu mnAcercaDe = new JMenu("Acerca de");
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmCreador = new JMenuItem("Creador");
		mntmCreador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "UNAC Students");
			}
		});
		mnAcercaDe.add(mntmCreador);
		
		JMenuItem mntmEasterEgg = new JMenuItem("Profesora");
		mntmEasterEgg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Erika Zevallos");
			}
		});
		mnAcercaDe.add(mntmEasterEgg);
		
		/*************************/
			/*PANEL PRINCIPAL*/
		/*************************/
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setForeground(new Color(255, 255, 255));
		layeredPane_1.setBackground(new Color(100, 149, 237));
		layeredPane_1.setBounds(0, 0, 574, 340);
		contentPaneCliente.add(layeredPane_1);
		
		JButton btnRegPaciente = new JButton();
		btnRegPaciente.setIcon(new ImageIcon(Recepcionista.class.getResource("/suing/registro.paciente2.png")));
		btnRegPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RecepPaciente recepPaciente = new RecepPaciente();
				recepPaciente.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblRecepcionista = new JLabel("Recepcionista");
		lblRecepcionista.setFont(new Font("Century Gothic", Font.BOLD, 21));
		lblRecepcionista.setBounds(246, 284, 156, 23);
		layeredPane_1.add(lblRecepcionista);
		
		JLabel lblPanelDel = new JLabel("Panel del");
		lblPanelDel.setFont(new Font("Century Gothic", Font.BOLD, 21));
		lblPanelDel.setBounds(214, 250, 156, 23);
		layeredPane_1.add(lblPanelDel);
		
		JLabel lblNewLabel = new JLabel("Bienvenido al");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 21));
		lblNewLabel.setBounds(188, 216, 156, 23);
		layeredPane_1.add(lblNewLabel);
		btnRegPaciente.setBounds(58, 40, 137, 128);
		layeredPane_1.add(btnRegPaciente);
		
		JButton btnRegCita = new JButton();
		btnRegCita.setIcon(new ImageIcon(Recepcionista.class.getResource("/suing/citas.icon.png")));
		btnRegCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecepCitas recepCitas = new RecepCitas();
				recepCitas.setVisible(true);
				dispose();
			}
		});
		btnRegCita.setBounds(366, 40, 137, 128);
		layeredPane_1.add(btnRegCita);
		
		JLabel lblRegistroDePaciente = new JLabel("Registro de Paciente");
		lblRegistroDePaciente.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblRegistroDePaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDePaciente.setBounds(58, 171, 150, 14);
		layeredPane_1.add(lblRegistroDePaciente);
		
		JLabel lblCitas = new JLabel("Citas");
		lblCitas.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblCitas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCitas.setBounds(353, 171, 150, 14);
		layeredPane_1.add(lblCitas);
		
		String columnas[]= {"N°Cita","Nombre Paciente","Nombre Doctor","Fecha","Especialidad","Tipo"};
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		contentPaneCliente.setLayout(null);
				
	}
}