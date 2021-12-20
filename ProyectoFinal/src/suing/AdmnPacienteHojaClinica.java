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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import conexión.CnAdministrador;
import suing.AdmnPaciente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AdmnPacienteHojaClinica extends JFrame {

	private JPanel contentPaneAdmnHojaClinica;
	public static JTextField tFDatosResultados;
	public static JTextField tFDatosPruebas;
	private JLabel lblBuscar;
	private JTextField tFDatosNHClinica;
	private JTextField tFListaNHClinica;
	@SuppressWarnings("rawtypes")
	public static DefaultComboBoxModel cBdatosnencargado;
	public static java.util.Date dCdatosfecha;
	public static JDateChooser dCDatosFecha;

	@SuppressWarnings("rawtypes")
	public AdmnPacienteHojaClinica(int a,String Paciente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Cl\u00EDnica UNAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 320);
		contentPaneAdmnHojaClinica = new JPanel();
		contentPaneAdmnHojaClinica.setBackground(new Color(100, 149, 237));
		contentPaneAdmnHojaClinica.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAdmnHojaClinica);
		contentPaneAdmnHojaClinica.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 35, 539, 246);
		contentPaneAdmnHojaClinica.add(tabbedPane);
		
		JPanel Registro = new JPanel();
		Registro.setBackground(new Color(240, 248, 255));
		tabbedPane.addTab("Lista", null, Registro, null);
		Registro.setLayout(null);
		
		String columnas[]= {"N�HC","Nombre de Encargado","Paciente","Prueba","Fecha","Resultados"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		try {
			CnAdministrador.llenarTablaClinica(modelLista, Paciente);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setBounds(10, 46, 514, 161);
		scrollPane.setViewportView(list);
		Registro.add(scrollPane);
		
		JLabel BClinica = new JLabel("Buscar N\u00BA Hoja Cl\u00EDnica:");
		BClinica.setFont(new Font("Tahoma", Font.BOLD, 15));
		BClinica.setBounds(10, 15, 179, 20);
		Registro.add(BClinica);
		
		tFListaNHClinica = new JTextField();
		tFListaNHClinica.setColumns(10);
		tFListaNHClinica.setBounds(198, 17, 108, 20);
		Registro.add(tFListaNHClinica);
		
		JLabel btnListaBuscar = new JLabel("Buscar");
		btnListaBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnAdministrador.buscarTablaClinica(modelLista, Paciente, tFListaNHClinica.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaBuscar.setBounds(360, 20, 46, 15);
		Registro.add(btnListaBuscar);
		
		JLabel btnListaMostrarTodo = new JLabel("Mostrar Todo");
		btnListaMostrarTodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnAdministrador.llenarTablaClinica(modelLista, Paciente);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnListaMostrarTodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaMostrarTodo.setBounds(426, 20, 98, 14);
		Registro.add(btnListaMostrarTodo);
		
		JPanel Datos = new JPanel();
		Datos.setBackground(new Color(240, 248, 255));
		Datos.setLayout(null);
		tabbedPane.addTab("Datos de la H.C.", null, Datos, null);
		
		JLabel lblNombreDelEncargado = new JLabel("Nombre del Encargado:");
		lblNombreDelEncargado.setBounds(10, 40, 149, 14);
		Datos.add(lblNombreDelEncargado);
		
		JLabel label_1 = new JLabel("Fecha:");
		label_1.setBounds(10, 70, 120, 14);
		Datos.add(label_1);
		
		JLabel lblPruebas = new JLabel("Pruebas:");
		lblPruebas.setBounds(10, 100, 174, 14);
		Datos.add(lblPruebas);
		
		JLabel label_3 = new JLabel("Resultados:");
		label_3.setBounds(10, 130, 94, 14);
		Datos.add(label_3);
		
		tFDatosResultados = new JTextField();
		tFDatosResultados.setColumns(10);
		tFDatosResultados.setBounds(160, 125, 364, 77);
		Datos.add(tFDatosResultados);
		
		dCdatosfecha=null;
		dCDatosFecha = new JDateChooser();
		dCDatosFecha.setBounds(160, 70, 120, 20);
		Datos.add(dCDatosFecha);
		
		cBdatosnencargado=new DefaultComboBoxModel();
		JComboBox cBDatosNEncargado = new JComboBox();
		cBDatosNEncargado.setBounds(160, 40, 120, 20);
		Datos.add(cBDatosNEncargado);
		
		tFDatosPruebas = new JTextField();
		tFDatosPruebas.setColumns(10);
		tFDatosPruebas.setBounds(160, 97, 120, 20);
		Datos.add(tFDatosPruebas);
		
		JPanel Mostrar = new JPanel();
		Mostrar.setBackground(new Color(240, 248, 255));
		Mostrar.setBounds(0, 0, 534, 29);
		Datos.add(Mostrar);
		Mostrar.setLayout(null);
		
		lblBuscar = new JLabel("N\u00BA de Hoja:");
		lblBuscar.setBounds(10, 7, 90, 14);
		Mostrar.add(lblBuscar);
		
		tFDatosNHClinica = new JTextField();
		tFDatosNHClinica.setBounds(84, 4, 116, 20);
		Mostrar.add(tFDatosNHClinica);
		tFDatosNHClinica.setColumns(10);
		
		JButton btnDatosMostrar = new JButton("Mostrar");
		btnDatosMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CnAdministrador.llenarHojaClinica(tFDatosNHClinica.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDatosMostrar.setBounds(435, 3, 89, 23);
		Mostrar.add(btnDatosMostrar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(469, 22, 80, 23);
		contentPaneAdmnHojaClinica.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmnPaciente admnPaciente= new AdmnPaciente(2,a);
				admnPaciente.setVisible(true);
				dispose();
			}
		});
	}
}
