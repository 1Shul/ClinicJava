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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import conexión.CnPersonal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Color;

@SuppressWarnings("serial")
public class PerMedPacienteHojaClinica extends JFrame {

	private JPanel contentPane;
	public static JTextField tFBuscarResultado;
	public static JTextField tFBuscarPrueba;
	private JLabel lblBuscar;
	private JTextField tFBuscarNHClinica;
	private JTextField tFListaNHClinica;
	private JTextField tFNuevoResultados;
	private JTextField tFNuevoPruebas;
	private JTextField tFNuevoNHCLinica;
	@SuppressWarnings("rawtypes")
	public static DefaultComboBoxModel cBbuscarencargado;
	public static java.util.Date dCbuscarfecha;
	public static JDateChooser dCBuscarFecha;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PerMedPacienteHojaClinica(String Usuario,String Paciente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Cl\u00EDnica UNAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 320);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 35, 539, 256);
		contentPane.add(tabbedPane);
		
		JPanel Registro = new JPanel();
		Registro.setBackground(new Color(240, 248, 255));
		tabbedPane.addTab("Lista", null, Registro, null);
		Registro.setLayout(null);
		
		String columnas[]= {"N�HC","IDPaciente","Nombre de Encargado","Fecha","Resultados"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setBounds(10, 46, 514, 171);
		scrollPane.setViewportView(list);
		Registro.add(scrollPane);
		
		try {
			CnPersonal.LlenarTablaHClinicas(modelLista,Paciente);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		JLabel BClinica = new JLabel("Buscar N\u00BA Hoja Cl\u00CDnica:");
		BClinica.setFont(new Font("Tahoma", Font.BOLD, 15));
		BClinica.setBounds(10, 17, 187, 20);
		Registro.add(BClinica);
		
		tFListaNHClinica = new JTextField();
		tFListaNHClinica.setColumns(10);
		tFListaNHClinica.setBounds(199, 19, 108, 20);
		Registro.add(tFListaNHClinica);
		
		JLabel btnListaBuscar = new JLabel("Buscar");
		btnListaBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					CnPersonal.buscarTablaHClinica(modelLista, Usuario, tFListaNHClinica.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnListaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaBuscar.setBounds(336, 20, 46, 15);
		Registro.add(btnListaBuscar);
		
		JLabel btnListaShowAll = new JLabel("Mostrar Todo");
		btnListaShowAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnPersonal.LlenarTablaHClinicas(modelLista,Paciente);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnListaShowAll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaShowAll.setBounds(426, 20, 108, 14);
		Registro.add(btnListaShowAll);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setLayout(null);
		tabbedPane.addTab("Nuevo", null, panel, null);
		
		JLabel lblNombreDelEncargado = new JLabel("Nombre del Encargado:");
		lblNombreDelEncargado.setBounds(10, 47, 149, 14);
		panel.add(lblNombreDelEncargado);
		
		JLabel label_4 = new JLabel("Fecha:");
		label_4.setBounds(10, 74, 120, 14);
		panel.add(label_4);
		
		JLabel label_7 = new JLabel("Pruebas:");
		label_7.setBounds(10, 104, 174, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Resultados:");
		label_8.setBounds(10, 134, 94, 14);
		panel.add(label_8);
		
		tFNuevoResultados = new JTextField();
		tFNuevoResultados.setColumns(10);
		tFNuevoResultados.setBounds(160, 133, 364, 50);
		panel.add(tFNuevoResultados);
		
		@SuppressWarnings("unused")
		java.util.Date dCnuevofecha=null;
		JDateChooser dCNuevoFecha = new JDateChooser();
		dCNuevoFecha.setBounds(160, 74, 120, 20);
		panel.add(dCNuevoFecha);
		
		DefaultComboBoxModel cBnuevoencargado=new DefaultComboBoxModel();
		JComboBox cBNuevoEncargado = new JComboBox();
		cBNuevoEncargado.setModel(cBnuevoencargado);
		cBNuevoEncargado.setEnabled(false);
		cBNuevoEncargado.setBounds(160, 44, 120, 20);
		panel.add(cBNuevoEncargado);
		
		tFNuevoPruebas = new JTextField();
		tFNuevoPruebas.setColumns(10);
		tFNuevoPruebas.setBounds(160, 101, 120, 20);
		panel.add(tFNuevoPruebas);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 534, 29);
		panel.add(panel_1);
		
		JLabel label_9 = new JLabel("N\u00BA de Hoja:");
		label_9.setBounds(10, 7, 90, 14);
		panel_1.add(label_9);
		
		tFNuevoNHCLinica = new JTextField();
		tFNuevoNHCLinica.setEnabled(false);
		tFNuevoNHCLinica.setColumns(10);
		tFNuevoNHCLinica.setBounds(110, 4, 90, 20);
		panel_1.add(tFNuevoNHCLinica);
		
		try {
			tFNuevoNHCLinica.setText(CnPersonal.CodigoAleatorio(cBnuevoencargado,Usuario));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JButton btnNuevoRegistrar = new JButton("Registrar");
		btnNuevoRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date sqlDate=new java.sql.Date(dCNuevoFecha.getDate().getTime());
				try {
					CnPersonal.mandarBDHojaClinica(tFNuevoNHCLinica.getText(), Paciente, Usuario, sqlDate, tFNuevoPruebas.getText(), tFNuevoResultados.getText());
					CnPersonal.LlenarTablaHClinicas(modelLista,Paciente);
					try {
						CnPersonal.LlenarTablaHClinicas(modelLista,Paciente);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					dCNuevoFecha.setEnabled(false);
					tFNuevoPruebas.setEnabled(false);
					tFNuevoResultados.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Guardado Correctamente");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNuevoRegistrar.setBounds(232, 194, 89, 23);
		panel.add(btnNuevoRegistrar);
		
		JButton btnNuevaHoja = new JButton("nueva hoja");
		btnNuevaHoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tFNuevoNHCLinica.setText(CnPersonal.CodigoAleatorio(cBnuevoencargado,Usuario));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				dCNuevoFecha.setEnabled(true);
				dCNuevoFecha.setCalendar(null);
				tFNuevoPruebas.setEnabled(true);
				tFNuevoPruebas.setText("");
				tFNuevoResultados.setEnabled(true);
				tFNuevoResultados.setText("");
				JOptionPane.showMessageDialog(null, "Hoja Limpiada");
			}
		});
		btnNuevaHoja.setBounds(342, 194, 89, 23);
		panel.add(btnNuevaHoja);
		
		JPanel Buscar = new JPanel();
		Buscar.setBackground(new Color(240, 248, 255));
		Buscar.setLayout(null);
		tabbedPane.addTab("Buscar ", null, Buscar, null);
		
		JLabel lblNombreDelEncargado_1 = new JLabel("Nombre del Encargado:");
		lblNombreDelEncargado_1.setBounds(10, 40, 149, 14);
		Buscar.add(lblNombreDelEncargado_1);
		
		JLabel label_1 = new JLabel("Fecha:");
		label_1.setBounds(10, 70, 120, 14);
		Buscar.add(label_1);
		
		JLabel lblPruebas = new JLabel("Pruebas:");
		lblPruebas.setBounds(10, 100, 174, 14);
		Buscar.add(lblPruebas);
		
		JLabel label_3 = new JLabel("Resultados:");
		label_3.setBounds(10, 130, 94, 14);
		Buscar.add(label_3);
		
		tFBuscarResultado = new JTextField();
		tFBuscarResultado.setEnabled(false);
		tFBuscarResultado.setColumns(10);
		tFBuscarResultado.setBounds(160, 125, 364, 50);
		Buscar.add(tFBuscarResultado);
		
		dCbuscarfecha=null;
		dCBuscarFecha = new JDateChooser();
		dCBuscarFecha.setBounds(160, 70, 120, 20);
		Buscar.add(dCBuscarFecha);
		dCBuscarFecha.setEnabled(false);
		
		cBbuscarencargado=new DefaultComboBoxModel();
		JComboBox cBBuscarEncargado = new JComboBox();
		cBBuscarEncargado.setEnabled(false);
		cBBuscarEncargado.setModel(cBbuscarencargado);
		cBBuscarEncargado.setBounds(160, 40, 120, 20);
		Buscar.add(cBBuscarEncargado);
		
		tFBuscarPrueba = new JTextField();
		tFBuscarPrueba.setEnabled(false);
		tFBuscarPrueba.setColumns(10);
		tFBuscarPrueba.setBounds(160, 100, 120, 20);
		Buscar.add(tFBuscarPrueba);
		
		JPanel Mostrar = new JPanel();
		Mostrar.setBackground(new Color(240, 248, 255));
		Mostrar.setBounds(0, 0, 534, 29);
		Buscar.add(Mostrar);
		Mostrar.setLayout(null);
		
		lblBuscar = new JLabel("N\u00BA de Hoja:");
		lblBuscar.setBounds(10, 7, 90, 14);
		Mostrar.add(lblBuscar);
		
		tFBuscarNHClinica = new JTextField();
		tFBuscarNHClinica.setBounds(110, 4, 132, 20);
		Mostrar.add(tFBuscarNHClinica);
		tFBuscarNHClinica.setColumns(10);
		
		JButton btnBuscarBuscar = new JButton("Buscar");
		btnBuscarBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CnPersonal.llenarBuscarHClinica(tFBuscarNHClinica.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnBuscarBuscar.setBounds(272, 3, 89, 23);
		Mostrar.add(btnBuscarBuscar);
		
		JButton btnBuscarEditar = new JButton("Editar");
		btnBuscarEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dCBuscarFecha.setEnabled(true);
				tFBuscarPrueba.setEnabled(true);
				tFBuscarResultado.setEnabled(true);
			}
		});
		btnBuscarEditar.setBounds(129, 190, 89, 23);
		Buscar.add(btnBuscarEditar);
		
		JButton btnBuscarGuardar = new JButton("Guardar");
		btnBuscarGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.Date sqlDate=new java.sql.Date(dCBuscarFecha.getDate().getTime());
				try {
					CnPersonal.ActualizaBDHojasClinicas(tFBuscarNHClinica.getText(), Paciente, Usuario, sqlDate, tFBuscarPrueba.getText(), tFBuscarResultado.getText());
					dCBuscarFecha.setEnabled(false);
					tFBuscarPrueba.setEnabled(false);
					tFBuscarResultado.setEnabled(false);
					try {
						CnPersonal.LlenarTablaHClinicas(modelLista,Paciente);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Guardado Correctamente");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnBuscarGuardar.setBounds(306, 190, 89, 23);
		Buscar.add(btnBuscarGuardar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVolver.setBounds(469, 11, 80, 23);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerMedPaciente perMedPaciente= new PerMedPaciente(Usuario);
				perMedPaciente.setVisible(true);
				dispose();
			}
		});
	}
}