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

import com.toedter.calendar.JDateChooser;

import conexión.CnAdministrador;
import conexión.CnPersonal;
import suing.AdmnPaciente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AdmnPacienteHojaTranslado extends JFrame {

	private JPanel contentPane;
	public static JTextField tFDatosObs;
	private JTextField tFDatosNTraslado;
	private JTextField tFListaNTraslado;
	public static JTextField textField;
	public static java.util.Date dCdatosftraslado;
	public static JDateChooser dCDatosFTraslado;
	@SuppressWarnings("rawtypes")
	public static DefaultComboBoxModel cBdatosdoctor;

	@SuppressWarnings({ "rawtypes" })
	public AdmnPacienteHojaTranslado(String IDPaciente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setTitle("Cl\u00EDnica UNAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 24, 434, 366);
		contentPane.add(tabbedPane);
		
		JPanel Lista = new JPanel();
		Lista.setBackground(new Color(240, 248, 255));
		tabbedPane.addTab("Lista", null, Lista, null);
		Lista.setLayout(null);
		
		String columnas[]= {"N�HC","Nombre de Encargado","Paciente","Motivo","Fecha"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		try {
			CnPersonal.LlenarTablaTraslado(modelLista,IDPaciente);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 409, 283);
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setViewportView(list);
		Lista.add(scrollPane);
		
		JLabel lblBuscarNtranslado = new JLabel("Buscar N\u00BA Translado:");
		lblBuscarNtranslado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBuscarNtranslado.setBounds(10, 11, 192, 20);
		Lista.add(lblBuscarNtranslado);
		
		JLabel btnListaBuscar = new JLabel("Buscar");
		btnListaBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnAdministrador.buscarTablaTranslado(modelLista, IDPaciente, tFListaNTraslado.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListaBuscar.setBounds(282, 14, 44, 15);
		Lista.add(btnListaBuscar);
		
		JLabel btnListaEliminar = new JLabel("Todo");
		btnListaEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnPersonal.LlenarTablaTraslado(modelLista,IDPaciente);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnListaEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btnListaEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnListaEliminar.setBounds(351, 14, 68, 14);
		Lista.add(btnListaEliminar);
		
		tFListaNTraslado = new JTextField();
		tFListaNTraslado.setColumns(10);
		tFListaNTraslado.setBounds(175, 13, 97, 20);
		Lista.add(tFListaNTraslado);
		
		JPanel pDatos = new JPanel();
		pDatos.setBackground(new Color(240, 248, 255));
		tabbedPane.addTab("Datos", null, pDatos, null);
		pDatos.setLayout(null);
		
		JLabel lblCertificadodePaciente = new JLabel("Certificado de traslado de paciente N\u00BA:");
		lblCertificadodePaciente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCertificadodePaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCertificadodePaciente.setBounds(6, 11, 268, 20);
		pDatos.add(lblCertificadodePaciente);
		
		JLabel lblMotivoDeTranslado = new JLabel("Motivo de Translado:");
		lblMotivoDeTranslado.setBounds(20, 50, 114, 20);
		pDatos.add(lblMotivoDeTranslado);
		
		JLabel lblDoctor = new JLabel("Doctor:");
		lblDoctor.setBounds(20, 150, 50, 20);
		pDatos.add(lblDoctor);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(20, 100, 46, 20);
		pDatos.add(lblFecha);
		
		JLabel lblObseravaciones = new JLabel("Obseravaciones:");
		lblObseravaciones.setBounds(20, 200, 90, 20);
		pDatos.add(lblObseravaciones);
		
		dCdatosftraslado=null;
		dCDatosFTraslado = new JDateChooser();
		dCDatosFTraslado.setBounds(136, 100, 100, 20);
		pDatos.add(dCDatosFTraslado);
		
		cBdatosdoctor=new DefaultComboBoxModel();
		JComboBox cBDatosDoctor = new JComboBox();
		cBDatosDoctor.setBounds(136, 150, 100, 20);
		pDatos.add(cBDatosDoctor);
		
		tFDatosObs = new JTextField();
		tFDatosObs.setBounds(136, 200, 177, 101);
		pDatos.add(tFDatosObs);
		tFDatosObs.setColumns(10);
		
		tFDatosNTraslado = new JTextField();
		tFDatosNTraslado.setEnabled(false);
		tFDatosNTraslado.setColumns(10);
		tFDatosNTraslado.setBounds(296, 12, 66, 20);
		pDatos.add(tFDatosNTraslado);
		
		textField = new JTextField();
		textField.setBounds(136, 50, 106, 20);
		pDatos.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Registrar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CnAdministrador.llenarHojaTraslado(tFDatosNTraslado.getText());
					CnPersonal.LlenarTablaTraslado(modelLista,IDPaciente);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(330, 304, 89, 23);
		pDatos.add(btnBuscar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(354, 11, 90, 23);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdmnPaciente admnPaciente= new AdmnPaciente(2,4);
				admnPaciente.setVisible(true);
				dispose();
			}
		});
	}
}