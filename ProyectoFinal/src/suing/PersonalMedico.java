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
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class PersonalMedico extends JFrame {

	private JPanel contentPane;

	public PersonalMedico(String usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setTitle("Cl\u00EDnica UNAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 383);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPaciente = new JMenu("Paciente");
		menuBar.add(mnPaciente);
		
		JMenuItem mntmListaDePaciente = new JMenuItem("Lista de Paciente");
		mnPaciente.add(mntmListaDePaciente);
		
		JMenuItem mntmDatosDelPaciente = new JMenuItem("Datos del Paciente");
		mnPaciente.add(mntmDatosDelPaciente);
		
		JMenu mnCitas = new JMenu("Citas");
		menuBar.add(mnCitas);
		
		JMenuItem mntmListaDeCitas = new JMenuItem("Lista de Citas");
		mnCitas.add(mntmListaDeCitas);
		
		JMenuItem mntmBuscarCita = new JMenuItem("Buscar Cita");
		mnCitas.add(mntmBuscarCita);
		
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
		
		JMenu mnContacto = new JMenu("Contacto");
		mnAyuda.add(mnContacto);
		
		JMenuItem mntmAdministrador = new JMenuItem("Administrador");
		mnContacto.add(mntmAdministrador);
		
		JMenuItem mntmEnfermera = new JMenuItem("Enfermera");
		mnContacto.add(mntmEnfermera);
		
		JMenuItem mntmAiuda = new JMenuItem("Aiuda");
		mnAyuda.add(mntmAiuda);
		mntmAiuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "io tahmpoko c khe acer, papuh :'(");
			}
		});
		
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
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblDoctor.setBounds(109, 75, 118, 27);
		contentPane.add(lblDoctor);
		
		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel.setBounds(32, 37, 195, 27);
		contentPane.add(lblNewLabel);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1, 1);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 424, 262);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JButton btnListPaciente = new JButton();
		btnListPaciente.setIcon(new ImageIcon(PersonalMedico.class.getResource("/suing/registro.paciente.png")));
		btnListPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PerMedPaciente perMedPaciente= new PerMedPaciente(usuario);
				perMedPaciente.setVisible(true);
				dispose();
			}
		});
		btnListPaciente.setBounds(63, 144, 129, 134);
		contentPane.add(btnListPaciente);
		
		JButton btnListCitas = new JButton();
		btnListCitas.setIcon(new ImageIcon(PersonalMedico.class.getResource("/suing/citas.icon.png")));
		btnListCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerMedCitas perMedCitas= new PerMedCitas(usuario);
				perMedCitas.setVisible(true);
				dispose();
			}
		});
		btnListCitas.setBounds(366, 72, 129, 134);
		contentPane.add(btnListCitas);
		
		JLabel lblListadoDePaciente = new JLabel("Listado de Paciente");
		lblListadoDePaciente.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblListadoDePaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDePaciente.setBounds(56, 289, 150, 20);
		contentPane.add(lblListadoDePaciente);
		
		JLabel lblListadoDeCitas = new JLabel("Listado  de Citas");
		lblListadoDeCitas.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblListadoDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDeCitas.setBounds(345, 217, 150, 20);
		contentPane.add(lblListadoDeCitas);
	}
}
