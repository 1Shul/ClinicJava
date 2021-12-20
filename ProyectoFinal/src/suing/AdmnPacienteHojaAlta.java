package suing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import conexión.CnAdministrador;
import suing.AdmnPaciente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AdmnPacienteHojaAlta extends JFrame {

	private JPanel contentPane;
	public static JTextField tFDatosMotivo;
	private JTextField tFListaNHAlta;
	private JTextField tFDatosNHAlta;
	public static java.util.Date cDatosfalta;
	public static JCalendar cDatosFAlta;
	@SuppressWarnings("rawtypes")
	public static DefaultComboBoxModel cBdatosdoctor;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AdmnPacienteHojaAlta(String Paciente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Cl\u00EDnica UNAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 34, 509, 366);
		contentPane.add(tabbedPane);
		
		JPanel Lista = new JPanel();
		Lista.setBackground(new Color(240, 248, 255));
		tabbedPane.addTab("Lista", null, Lista, null);
		Lista.setLayout(null);
		
		String columnas[]= {"N�","Nombre del Doctor","Fecha","Motivo"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		try {
			CnAdministrador.llenarTablaAlta(modelLista, Paciente);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 484, 276);
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setViewportView(list);
		Lista.add(scrollPane);
		
		JLabel label = new JLabel("Buscar N\u00BA Hoja de Alta");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(15, 13, 210, 20);
		Lista.add(label);
		
		tFListaNHAlta = new JTextField();
		tFListaNHAlta.setColumns(10);
		tFListaNHAlta.setBounds(213, 14, 96, 22);
		Lista.add(tFListaNHAlta);
		
		JLabel btnListaSearch = new JLabel("Buscar");
		btnListaSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnAdministrador.buscarTablaAlta(modelLista, Paciente, tFListaNHAlta.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListaSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListaSearch.setBounds(331, 15, 46, 15);
		Lista.add(btnListaSearch);
		
		JLabel btnListaShowAll = new JLabel("Mostrar Todo");
		btnListaShowAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnAdministrador.llenarTablaAlta(modelLista, Paciente);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnListaShowAll.setHorizontalAlignment(SwingConstants.CENTER);
		btnListaShowAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListaShowAll.setBounds(387, 15, 108, 14);
		Lista.add(btnListaShowAll);
		
		JPanel Datos = new JPanel();
		Datos.setBackground(new Color(240, 248, 255));
		tabbedPane.addTab("Datos", null, Datos, null);
		Datos.setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(23, 59, 55, 14);
		Datos.add(lblFecha);
		
		cDatosfalta=null;
		cDatosFAlta = new JCalendar();
		cDatosFAlta.setBounds(88, 58, 246, 125);
		Datos.add(cDatosFAlta);
		
		JLabel lblNewLabel = new JLabel("Doctor(a):");
		lblNewLabel.setBounds(23, 196, 55, 14);
		Datos.add(lblNewLabel);
		
		JLabel lblMotivoDeAlta = new JLabel("Motivo de Alta:");
		lblMotivoDeAlta.setBounds(23, 242, 101, 14);
		Datos.add(lblMotivoDeAlta);
		
		tFDatosMotivo = new JTextField();
		tFDatosMotivo.setBounds(108, 242, 252, 72);
		Datos.add(tFDatosMotivo);
		tFDatosMotivo.setColumns(10);
		
		JLabel lblCertificadoDeAlta = new JLabel("Certificado de Alta de la Clinica N\u00BA:");
		lblCertificadoDeAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCertificadoDeAlta.setBounds(10, 21, 187, 14);
		Datos.add(lblCertificadoDeAlta);
		
		cBdatosdoctor=new DefaultComboBoxModel();
		JComboBox cBDatosDoctor = new JComboBox();
		cBDatosDoctor.setModel(cBdatosdoctor);
		cBDatosDoctor.setBounds(108, 194, 160, 20);
		Datos.add(cBDatosDoctor);
		
		tFDatosNHAlta = new JTextField();
		tFDatosNHAlta.setEnabled(false);
		tFDatosNHAlta.setBounds(207, 18, 127, 20);
		Datos.add(tFDatosNHAlta);
		tFDatosNHAlta.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CnAdministrador.llenarHojaAlta(tFDatosNHAlta.getText());
					CnAdministrador.llenarTablaAlta(modelLista, Paciente);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(405, 304, 89, 23);
		Datos.add(btnNewButton);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(394, 11, 120, 23);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdmnPaciente admnPaciente= new AdmnPaciente(2,4);
				admnPaciente.setVisible(true);
				dispose();
			}
		});
	}
}
