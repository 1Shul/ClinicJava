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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import conexión.CnPersonal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Color;


@SuppressWarnings("serial")
public class PerMedPacienteHojaAlta extends JFrame {

	private JPanel contentPane;
	private JTextField tFNuevaMotivo;
	private JTextField tFListaNHAlta;
	private JTextField tFNuevaNHAlta;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PerMedPacienteHojaAlta(String Usuario, String Paciente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Cl\u00EDnica UNAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 34, 529, 366);
		contentPane.add(tabbedPane);
		
		JPanel Lista = new JPanel();
		Lista.setBackground(new Color(240, 248, 255));
		tabbedPane.addTab("Lista", null, Lista, null);
		Lista.setLayout(null);
		
		String columnas[]= {"N�","Nombre del Doctor","NPaciente","Fecha","Motivo"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		try {
			CnPersonal.LlenarTablaAlta(modelLista, Paciente);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 504, 283);
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setViewportView(list);
		Lista.add(scrollPane);
		
		JLabel lblBuscarNHoja = new JLabel("Buscar N\u00BA Hoja de Alta:");
		lblBuscarNHoja.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBuscarNHoja.setBounds(10, 15, 181, 20);
		Lista.add(lblBuscarNHoja);
		
		tFListaNHAlta = new JTextField();
		tFListaNHAlta.setColumns(10);
		tFListaNHAlta.setBounds(201, 15, 106, 20);
		Lista.add(tFListaNHAlta);
		
		JLabel btnListaBuscar = new JLabel("Buscar");
		btnListaBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnPersonal.buscarTablaHAlta(modelLista, Usuario, tFListaNHAlta.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaBuscar.setBounds(344, 17, 46, 15);
		Lista.add(btnListaBuscar);
		
		JLabel btnListaEliminar = new JLabel("Todo");
		btnListaEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnPersonal.LlenarTablaAlta(modelLista, Usuario);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListaEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btnListaEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaEliminar.setBounds(448, 15, 66, 14);
		Lista.add(btnListaEliminar);
		
		JPanel Datos = new JPanel();
		Datos.setBackground(new Color(240, 248, 255));
		tabbedPane.addTab("Nueva", null, Datos, null);
		Datos.setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(23, 59, 55, 14);
		Datos.add(lblFecha);
		
		JCalendar cNuevaCalendario = new JCalendar();
		cNuevaCalendario.setBounds(88, 58, 246, 125);
		Datos.add(cNuevaCalendario);
		
		JLabel lblNewLabel = new JLabel("Doctor(a):");
		lblNewLabel.setBounds(23, 196, 55, 14);
		Datos.add(lblNewLabel);
		
		JLabel lblMotivoDeAlta = new JLabel("Motivo de Alta:");
		lblMotivoDeAlta.setBounds(23, 232, 101, 14);
		Datos.add(lblMotivoDeAlta);
		
		tFNuevaMotivo = new JTextField();
		tFNuevaMotivo.setBounds(134, 229, 252, 72);
		Datos.add(tFNuevaMotivo);
		tFNuevaMotivo.setColumns(10);
		
		JButton btnNuevaGuardar = new JButton("Registrar");
		btnNuevaGuardar.setBounds(425, 304, 89, 23);
		Datos.add(btnNuevaGuardar);
		
		JLabel lblCertificadoDeAlta = new JLabel("Certificado de Alta de la Clinica N\u00BA:");
		lblCertificadoDeAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCertificadoDeAlta.setBounds(10, 11, 194, 20);
		Datos.add(lblCertificadoDeAlta);
		
		DefaultComboBoxModel doctor=new DefaultComboBoxModel();
		JComboBox cBNuevaDoctor = new JComboBox();
		cBNuevaDoctor.setModel(doctor);
		cBNuevaDoctor.setBounds(98, 194, 160, 20);
		Datos.add(cBNuevaDoctor);
		
		tFNuevaNHAlta = new JTextField();
		tFNuevaNHAlta.setEditable(false);
		tFNuevaNHAlta.setBounds(228, 11, 106, 20);
		Datos.add(tFNuevaNHAlta);
		tFNuevaNHAlta.setColumns(10);
		try {
			tFNuevaNHAlta.setText(CnPersonal.CodigoAleatorioAlta(doctor,Usuario));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		btnNuevaGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date sqlDate=new java.sql.Date(cNuevaCalendario.getDate().getTime());
				try {
					CnPersonal.EnviarBDHojaAlta(tFNuevaNHAlta.getText(), Paciente, sqlDate, Usuario, tFNuevaMotivo.getText());
					cNuevaCalendario.setEnabled(false);
					tFNuevaMotivo.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Guardado Correctamente");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerMedPaciente perMedPaciente= new PerMedPaciente(Usuario);
				perMedPaciente.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(445, 11, 89, 23);
		contentPane.add(btnVolver);
	}
}