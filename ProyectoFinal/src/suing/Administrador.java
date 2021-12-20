package suing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class Administrador extends JFrame {

	private JPanel contentPaneAdministrador;

	public Administrador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cl\u00EDnica UNAC");
		setBounds(100, 100, 600, 600);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(240, 248, 255));
		setJMenuBar(menuBar);
		
		JMenu mnPaciente = new JMenu("Paciente");
		menuBar.add(mnPaciente);
		
		JMenuItem mntmDatosdePaciente = new JMenuItem("Datos del Paciente");
		mntmDatosdePaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdmnPaciente admnPaciente = new AdmnPaciente(2,1);
				admnPaciente.setVisible(true);
				dispose();
			}
		});
		
		JMenuItem mntmListaDePaciente = new JMenuItem("Lista de Pacientes");
		mntmListaDePaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmnPaciente admnPaciente = new AdmnPaciente(1,0);
				admnPaciente.setVisible(true);
				dispose();
			}
		});
		mnPaciente.add(mntmListaDePaciente);
		mnPaciente.add(mntmDatosdePaciente);
		
		JMenu mnCita = new JMenu("Cita");
		menuBar.add(mnCita);
		
		JMenuItem mntmListaDeCitas = new JMenuItem("Lista de Citas");
		mntmListaDeCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmnCita admnCita =new AdmnCita(1);
				admnCita.setVisible(true);
				dispose();
			}
		});
		mnCita.add(mntmListaDeCitas);
		
		JMenuItem mntmDetalleCita = new JMenuItem("Detalles de la Cita");
		mntmDetalleCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmnCita admnCita =new AdmnCita(2);
				admnCita.setVisible(true);
				dispose();
			}
		});
		mnCita.add(mntmDetalleCita);
		
		JMenu mnMdico = new JMenu("Personal M\u00E9dico");
		menuBar.add(mnMdico);
		
		JMenuItem mntmVisualizarPersonalMdico = new JMenuItem("Visualizar Personal M\u00E9dico");
		mntmVisualizarPersonalMdico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmnPersonal admnPersonal = new AdmnPersonal(1);
				admnPersonal.setVisible(true);
				dispose();
			}
		});
		mnMdico.add(mntmVisualizarPersonalMdico);
		
		JMenuItem mntmAadirAlPersonal = new JMenuItem("A\u00F1adir Personal");
		mntmAadirAlPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmnPersonal admnPersonal = new AdmnPersonal(2);
				admnPersonal.setVisible(true);
				dispose();
			}
		});
		mnMdico.add(mntmAadirAlPersonal);
		
		JMenuItem mntmEditarPersonal = new JMenuItem("Editar Personal");
		mntmEditarPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmnPersonal admnPersonal = new AdmnPersonal(3);
				admnPersonal.setVisible(true);
				dispose();
			}
		});
		mnMdico.add(mntmEditarPersonal);
		
		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar Sesi\u00F3n");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogIn logIn  =new LogIn();
				logIn.setVisible(true);
				dispose();
			}
		});
		mnUsuario.add(mntmCerrarSesin);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmContacto = new JMenuItem("Contacto");
		mntmContacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Comuníquese al 984829297");
			}
		});
		mnAyuda.add(mntmContacto);
		
		JMenu mnAcercaDe = new JMenu("Acerca de");
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmCreador = new JMenuItem("Creadores");
		mntmCreador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "UNAC-Students");
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
		contentPaneAdministrador = new JPanel();
		contentPaneAdministrador.setBackground(new Color(100, 149, 237));
		contentPaneAdministrador.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAdministrador);
		contentPaneAdministrador.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido al ");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel.setBounds(291, 357, 179, 29);
		contentPaneAdministrador.add(lblNewLabel);
		
		JLabel lblBuscarPAciente = new JLabel("Buscar Paciente");
		lblBuscarPAciente.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblBuscarPAciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarPAciente.setBounds(33, 210, 200, 20);
		contentPaneAdministrador.add(lblBuscarPAciente);
		
		JButton btnBPaciente = new JButton();
		btnBPaciente.setIcon(new ImageIcon(Administrador.class.getResource("/suing/buscar.paciente.png")));
		btnBPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdmnPaciente admnPaciente = new AdmnPaciente(1,0);
				admnPaciente.setVisible(true);
				dispose();				
			}
		});
		btnBPaciente.setBounds(60, 51, 150, 150);
		contentPaneAdministrador.add(btnBPaciente);
		
		JLabel lblAgendaDeCitas = new JLabel("Agenda de Citas");
		lblAgendaDeCitas.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblAgendaDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgendaDeCitas.setBounds(370, 241, 150, 20);
		contentPaneAdministrador.add(lblAgendaDeCitas);
		
		JButton btnACitas = new JButton();
		btnACitas.setIcon(new ImageIcon(Administrador.class.getResource("/suing/citas.icon.png")));
		btnACitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmnCita AdmnCita= new AdmnCita(1);
				AdmnCita.setVisible(true);
				dispose();
			}
		});
		btnACitas.setBounds(360, 80, 150, 150);
		contentPaneAdministrador.add(btnACitas);
		
		JLabel lblBuscarExpediente = new JLabel("Personal M\u00E9dico\r\n");
		lblBuscarExpediente.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblBuscarExpediente.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarExpediente.setBounds(33, 505, 200, 20);
		contentPaneAdministrador.add(lblBuscarExpediente);
		
		JButton btnBExpediente = new JButton();
		btnBExpediente.setIcon(new ImageIcon(Administrador.class.getResource("/suing/personal.medico.icon.png")));
		btnBExpediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmnPersonal AdmnPersonal = new AdmnPersonal(1);
				AdmnPersonal.setVisible(true);
				dispose();
			}
		});
		btnBExpediente.setBounds(60, 344, 150, 150);
		contentPaneAdministrador.add(btnBExpediente);
		
		JLabel lblNewLabel_1 = new JLabel("Panel del");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel_1.setBounds(334, 407, 123, 29);
		contentPaneAdministrador.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Administrador");
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel_2.setBounds(372, 459, 212, 29);
		contentPaneAdministrador.add(lblNewLabel_2);
	}
}
