package suing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;

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

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import conexión.CnPersonal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class PerMedCitas extends JFrame {

	private JPanel contentPaneCitas;
	public static String descripcion;
	public static String conclusion;
	private JTextField tFBuscarCita;
	public static JTextField tFBuscarPaciente;
	public static JTextField tBuscarMotivo;
	public static JTextField tFBuscarHabitacion;
	public static JTextField tFBuscarPiso;
	private JTextField tFListCita;
	private JTable table;
	public static JTextField tFDADiagnostico;
	public static JTextField tFDATalla;
	public static JTextField tFDAPulso;
	public static JTextField tFDARitCardiaco;
	public static JTextField tFDAPeso;
	public static JTextField tFDAMedicinas;
	public static JTextField textField_6;
	public static String Especialidad;
	public static JTextField tFHCResultados;
	public static JTextField tFHCPruebas;
	public static String FechaDA,Antecedentes,Trastorno,FechaHC;
	public static JDateChooser cDDAFecha, dCHCFecha;
	@SuppressWarnings("rawtypes")
	public static JComboBox cBHCEncargado;
	DateFormat df= DateFormat.getDateInstance();
	public static JTextField tFDAAntecedentes;
	public static JTextField tFDATrastorno;
	public static JTextField tFHCNHojaClinica;
	public static JCalendar calendar;
	public static JButton btnHCRegistrar,btnHCActualizar,btnDAActualizar,btnDAGuardar;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PerMedCitas(String Usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cl\u00EDnica UNAC");
		setBounds(100, 100, 630, 655);
		contentPaneCitas = new JPanel();
		contentPaneCitas.setBackground(new Color(100, 149, 237));
		contentPaneCitas.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneCitas);
		contentPaneCitas.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Citas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 10, 84, 28);
		contentPaneCitas.add(lblNewLabel);
		
		JTabbedPane tabbedPaneCita = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCita.setBounds(10, 50, 600, 566);
		contentPaneCitas.add(tabbedPaneCita);
		
		JPanel pListCita = new JPanel();
		pListCita.setBackground(new Color(240, 248, 255));
		pListCita.setLayout(null);
		tabbedPaneCita.addTab("Lista de Citas", null, pListCita, null);
		
		String columnas[]= {"N�Cita","Nombre Paciente","Nombre Doctor","Fecha","Especialidad","Motivo","D.Asis./H.Clin."};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		try {
			CnPersonal.LlenarTablaCita(modelLista,Usuario);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setBounds(10, 46, 575, 481);
		scrollPane.setViewportView(list);;
		pListCita.add(scrollPane);
		
		JLabel lblBuscarCita = new JLabel("Buscar Cita");
		lblBuscarCita.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBuscarCita.setBounds(10, 15, 160, 20);
		pListCita.add(lblBuscarCita);
		
		tFListCita = new JTextField();
		tFListCita.setColumns(10);
		tFListCita.setBounds(131, 19, 146, 20);
		pListCita.add(tFListCita);
		
		JLabel btnListCita = new JLabel("Buscar");
		btnListCita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					CnPersonal.buscarTablaCita(modelLista,Usuario,tFListCita.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnListCita.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListCita.setBounds(392, 20, 46, 15);
		pListCita.add(btnListCita);
		
		JLabel btnListShowAll = new JLabel("Mostrar Todo");
		btnListShowAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnPersonal.LlenarTablaCita(modelLista,Usuario);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListShowAll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListShowAll.setBounds(496, 20, 89, 14);
		pListCita.add(btnListShowAll);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		pListCita.add(table);
			
			JPanel pBuscCita = new JPanel();
			pBuscCita.setBackground(new Color(240, 248, 255));
			tabbedPaneCita.addTab("Buscar Cita", null, pBuscCita, null);
			pBuscCita.setLayout(null);
			
			JLabel label = new JLabel("N\u00BA de Cita:");
			label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			label.setBounds(22, 11, 76, 19);
			pBuscCita.add(label);
			
			JButton btnBuscarBuscar = new JButton("Buscar");
			
			btnBuscarBuscar.setBounds(258, 9, 89, 23);
			pBuscCita.add(btnBuscarBuscar);
			
			tFBuscarCita = new JTextField();
			tFBuscarCita.setBounds(122, 10, 86, 20);
			pBuscCita.add(tFBuscarCita);
			tFBuscarCita.setColumns(10);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(10, 41, 575, 486);
			pBuscCita.add(tabbedPane);
			
			JPanel spBuscCita = new JPanel();
			spBuscCita.setBackground(new Color(240, 255, 255));
			tabbedPane.addTab("Datos de Cita", null, spBuscCita, null);
			spBuscCita.setLayout(null);
			
			JLabel lblNombreDelPaciente = new JLabel("Nombre del Paciente:");
			lblNombreDelPaciente.setBounds(10, 36, 123, 14);
			spBuscCita.add(lblNombreDelPaciente);
			
			tFBuscarPaciente = new JTextField();
			tFBuscarPaciente.setEnabled(false);
			tFBuscarPaciente.setBounds(133, 33, 190, 20);
			tFBuscarPaciente.setColumns(10);
			spBuscCita.add(tFBuscarPaciente);
			
			JLabel label_2 = new JLabel("Motivo de Cita:");
			label_2.setBounds(10, 61, 123, 14);
			spBuscCita.add(label_2);
			
			tBuscarMotivo = new JTextField();
			tBuscarMotivo.setEnabled(false);
			tBuscarMotivo.setBounds(133, 58, 190, 20);
			tBuscarMotivo.setColumns(10);
			spBuscCita.add(tBuscarMotivo);
			
			JLabel label_4 = new JLabel("Especialidad:");
			label_4.setBounds(10, 86, 123, 14);
			spBuscCita.add(label_4);
			
			DefaultComboBoxModel cEspecialidad = new DefaultComboBoxModel();
			cEspecialidad.addElement("-Seleccione-");
			
			JComboBox cBEspecialidad = new JComboBox();
			cBEspecialidad.setModel(cEspecialidad);
			cBEspecialidad.setEnabled(false);
			cBEspecialidad.setBounds(133, 80, 190, 20);
			spBuscCita.add(cBEspecialidad);
			
			JLabel label_5 = new JLabel("Fecha de la Cita:");
			label_5.setBounds(10, 136, 100, 14);
			spBuscCita.add(label_5);
			
			calendar = new JCalendar();
			calendar.setBounds(133, 136, 302, 109);
			spBuscCita.add(calendar);
			calendar.setEnabled(false);
			
			JLabel lblHabitacin = new JLabel("Habitaci\u00F3n:");
			lblHabitacin.setBounds(25, 288, 65, 14);
			spBuscCita.add(lblHabitacin);
			
			tFBuscarHabitacion = new JTextField();
			tFBuscarHabitacion.setEnabled(false);
			tFBuscarHabitacion.setBounds(117, 285, 86, 20);
			tFBuscarHabitacion.setText("");
			tFBuscarHabitacion.setColumns(10);
			spBuscCita.add(tFBuscarHabitacion);
			
			tFBuscarPiso = new JTextField();
			tFBuscarPiso.setEnabled(false);
			tFBuscarPiso.setBounds(417, 285, 100, 20);
			tFBuscarPiso.setColumns(10);
			spBuscCita.add(tFBuscarPiso);
			
			JLabel label_8 = new JLabel("Piso:");
			label_8.setBounds(370, 288, 44, 14);
			spBuscCita.add(label_8);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(240, 255, 255));
			tabbedPane.addTab("Datos Asistenciales", null, panel, null);
			panel.setLayout(null);
			
			JLabel lblDiagnsticoPrincipal = new JLabel("Diagn\u00F3stico Principal:");
			lblDiagnsticoPrincipal.setBounds(19, 270, 132, 14);
			panel.add(lblDiagnsticoPrincipal);
			
			tFDADiagnostico = new JTextField();
			tFDADiagnostico.setColumns(10);
			tFDADiagnostico.setBounds(153, 267, 165, 20);
			panel.add(tFDADiagnostico);
			
			JLabel label_6 = new JLabel("DATOS DE PROCESO ASISTENCIAL");
			label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_6.setBounds(10, 11, 228, 14);
			panel.add(label_6);
			
			JLabel lblExploracinFisica = new JLabel("Exploraci\u00F3n Fisica");
			lblExploracinFisica.setBounds(10, 36, 104, 14);
			panel.add(lblExploracinFisica);
			
			JLabel label_10 = new JLabel("Peso:");
			label_10.setBounds(19, 70, 46, 14);
			panel.add(label_10);
			
			JLabel label_11 = new JLabel("Talla:");
			label_11.setBounds(19, 120, 46, 14);
			panel.add(label_11);
			
			JLabel label_12 = new JLabel("Pulso:");
			label_12.setBounds(19, 170, 100, 14);
			panel.add(label_12);
			
			JLabel label_13 = new JLabel("Ritmo Cardiaco:");
			label_13.setBounds(19, 220, 95, 14);
			panel.add(label_13);
			
			JLabel label_14 = new JLabel("Fecha:");
			label_14.setBounds(19, 370, 95, 14);
			panel.add(label_14);
			
			JLabel lblExploracinVerbal = new JLabel("Exploraci\u00F3n Verbal");
			lblExploracinVerbal.setBounds(313, 36, 115, 14);
			panel.add(lblExploracinVerbal);
			
			JLabel label_16 = new JLabel("Antecedentes :");
			label_16.setBounds(313, 70, 97, 14);
			panel.add(label_16);
			
			tFDATalla = new JTextField();
			tFDATalla.setColumns(10);
			tFDATalla.setBounds(128, 120, 100, 20);
			panel.add(tFDATalla);
			
			tFDAPulso = new JTextField();
			tFDAPulso.setColumns(10);
			tFDAPulso.setBounds(128, 170, 100, 20);
			panel.add(tFDAPulso);
			
			tFDARitCardiaco = new JTextField();
			tFDARitCardiaco.setColumns(10);
			tFDARitCardiaco.setBounds(128, 220, 100, 20);
			panel.add(tFDARitCardiaco);
			
			tFDAPeso = new JTextField();
			tFDAPeso.setColumns(10);
			tFDAPeso.setBounds(128, 70, 100, 20);
			panel.add(tFDAPeso);
			
			JLabel label_17 = new JLabel("Medicinas:");
			label_17.setBounds(19, 320, 63, 14);
			panel.add(label_17);
			
			tFDAMedicinas = new JTextField();
			tFDAMedicinas.setColumns(10);
			tFDAMedicinas.setBounds(128, 317, 100, 20);
			panel.add(tFDAMedicinas);
			
			JLabel label_20 = new JLabel("Trastorno:");
			label_20.setBounds(313, 120, 69, 14);
			panel.add(label_20);
			
			textField_6 = new JTextField();
			textField_6.setColumns(10);
			textField_6.setBounds(571, 117, 100, 20);
			panel.add(textField_6);
			
			JComboBox comboBox_3 = new JComboBox();
			comboBox_3.setBounds(638, 267, 107, 20);
			panel.add(comboBox_3);
			
			cDDAFecha = new JDateChooser();
			cDDAFecha.setBounds(128, 364, 100, 20);
			panel.add(cDDAFecha);
			
			btnDAGuardar = new JButton("Guardar");
			btnDAGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					java.sql.Date sqlDate=new java.sql.Date(cDDAFecha.getDate().getTime());
					try {
						CnPersonal.EnviarBDCDAsistenciales(tFBuscarCita.getText(), tFBuscarPaciente.getText(), Float.parseFloat(tFDAPeso.getText()), Float.parseFloat(tFDATalla.getText()), Float.parseFloat(tFDAPulso.getText()), Integer.parseInt(tFDARitCardiaco.getText()), tFDAMedicinas.getText(), tFDADiagnostico.getText(), sqlDate, tFDAAntecedentes.getText(), tFDATrastorno.getText());
						tFDAPeso.setEnabled(false);
						tFDATalla.setEnabled(false);
						tFDAPulso.setEnabled(false);
						tFDARitCardiaco.setEnabled(false);
						tFDADiagnostico.setEnabled(false);
						tFDAMedicinas.setEnabled(false);
						cDDAFecha.setEnabled(false);
						tFDAAntecedentes.setEnabled(false);
						tFDATrastorno.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Guardado Correctamente");
						tFDAPeso.setEnabled(false);
						
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
				}
			});
			btnDAGuardar.setBounds(446, 408, 89, 23);
			panel.add(btnDAGuardar);
			
			tFDAAntecedentes = new JTextField();
			tFDAAntecedentes.setBounds(435, 67, 100, 20);
			panel.add(tFDAAntecedentes);
			tFDAAntecedentes.setColumns(10);
			
			tFDATrastorno = new JTextField();
			tFDATrastorno.setBounds(435, 117, 100, 20);
			panel.add(tFDATrastorno);
			tFDATrastorno.setColumns(10);
			
			btnDAActualizar = new JButton("Actualizar");
			btnDAActualizar.setEnabled(false);
			btnDAActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(JOptionPane.showConfirmDialog(null, "¿Guardar los datos actualizados?")==0)
					{
						java.sql.Date sqlDate=new java.sql.Date(cDDAFecha.getDate().getTime());
						try {
							CnPersonal.ActualizarBDDAsist(tFBuscarCita.getText(), Float.parseFloat(tFDAPeso.getText()), Float.parseFloat(tFDATalla.getText()), Float.parseFloat(tFDAPulso.getText()), Integer.parseInt(tFDARitCardiaco.getText()), tFDADiagnostico.getText(), tFDAMedicinas.getText(), sqlDate, tFDAAntecedentes.getText(), tFDATrastorno.getText());
							JOptionPane.showMessageDialog(null, "Guardado Correctamente");
							btnDAActualizar.setVisible(false);
							tFDAPeso.setEnabled(false);
							tFDATalla.setEnabled(false);
							tFDAPulso.setEnabled(false);
							tFDARitCardiaco.setEnabled(false);
							tFDADiagnostico.setEnabled(false);
							tFDAMedicinas.setEnabled(false);
							cDDAFecha.setEnabled(false);
							tFDAAntecedentes.setEnabled(false);
							tFDATrastorno.setEnabled(false);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});
			btnDAActualizar.setBounds(321, 408, 89, 23);
			panel.add(btnDAActualizar);
			btnDAActualizar.setVisible(false);
			
			JPanel pHojasClinicas = new JPanel();
			pHojasClinicas.setBackground(new Color(240, 255, 255));
			tabbedPane.addTab("Hoja Clinica", null, pHojasClinicas, null);
			pHojasClinicas.setLayout(null);
			
			JLabel label_19 = new JLabel("Nombre del Encargado:");
			label_19.setBounds(38, 68, 149, 14);
			pHojasClinicas.add(label_19);
			
			JLabel label_21 = new JLabel("Fecha:");
			label_21.setBounds(38, 95, 120, 14);
			pHojasClinicas.add(label_21);
			
			JLabel label_22 = new JLabel("Pruebas:");
			label_22.setBounds(38, 125, 174, 14);
			pHojasClinicas.add(label_22);
			
			JLabel label_23 = new JLabel("Resultados:");
			label_23.setBounds(38, 155, 94, 14);
			pHojasClinicas.add(label_23);
			
			tFHCResultados = new JTextField();
			tFHCResultados.setColumns(10);
			tFHCResultados.setBounds(142, 155, 379, 205);
			pHojasClinicas.add(tFHCResultados);
			
			dCHCFecha = new JDateChooser();
			dCHCFecha.setBounds(188, 95, 120, 20);
			pHojasClinicas.add(dCHCFecha);
			
			DefaultComboBoxModel cBchencargado = new DefaultComboBoxModel();
			
			cBHCEncargado = new JComboBox();
			cBHCEncargado.setEnabled(false);
			cBHCEncargado.setModel(cBchencargado);
			cBHCEncargado.addItem("-Seleccione-");
			cBHCEncargado.setBounds(188, 65, 120, 20);
			pHojasClinicas.add(cBHCEncargado);
			
			tFHCPruebas = new JTextField();
			tFHCPruebas.setColumns(10);
			tFHCPruebas.setBounds(188, 122, 205, 20);
			pHojasClinicas.add(tFHCPruebas);
			
			btnHCRegistrar = new JButton("Registrar");
			btnHCRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						CnPersonal.HojasClinicas(tFBuscarPaciente.getText(), tFBuscarCita.getText(), (String)cBHCEncargado.getSelectedItem(), tFHCNHojaClinica.getText(), df.format(dCHCFecha.getDate()), tFHCPruebas.getText(), tFHCResultados.getText());
						dCHCFecha.setEnabled(false);
						tFHCPruebas.setEnabled(false);
						tFHCResultados.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Guardado Correctamente");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			});
			btnHCRegistrar.setBounds(371, 408, 89, 23);
			pHojasClinicas.add(btnHCRegistrar);
			
			btnHCActualizar = new JButton("Actualizar");
			btnHCActualizar.setEnabled(false);
			btnHCActualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null, "�Guardar los datos actualizados?")==0)
					{
						try {
							CnPersonal.ActualizarHClinica(tFHCNHojaClinica.getText(),df.format(calendar.getDate()),tFHCPruebas.getText(),tFHCResultados.getText());
							JOptionPane.showMessageDialog(null, "Guardado Correctamente");
							btnHCActualizar.setVisible(false);
							dCHCFecha.setEnabled(false);
							tFHCPruebas.setEnabled(false);
							tFHCResultados.setEnabled(false);
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			btnHCActualizar.setBounds(86, 408, 89, 23);
			pHojasClinicas.add(btnHCActualizar);
			
			JLabel lblNumeroDeHoja = new JLabel("N\u00FAmero de Hoja Cl\u00EDnica");
			lblNumeroDeHoja.setBounds(38, 40, 137, 14);
			pHojasClinicas.add(lblNumeroDeHoja);
			
			tFHCNHojaClinica = new JTextField();
			tFHCNHojaClinica.setEnabled(false);
			tFHCNHojaClinica.setBounds(188, 37, 120, 20);
			pHojasClinicas.add(tFHCNHojaClinica);
			tFHCNHojaClinica.setColumns(10);
			btnHCActualizar.setVisible(false);
			
			JButton btnBuscarOtro = new JButton("Buscar otro");
			btnBuscarOtro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tFBuscarCita.setEnabled(true);
					tFBuscarPaciente.setText("");
					tBuscarMotivo.setText("");
					cEspecialidad.removeAllElements();
					cEspecialidad.addElement("-Seleccione-");
					tFBuscarHabitacion.setText("");
					tFBuscarPiso.setText("");
					tFDAPeso.setEnabled(true);
					tFDAPeso.setText("");
					tFDATalla.setEnabled(true);
					tFDATalla.setText("");
					tFDAPulso.setEnabled(true);
					tFDAPulso.setText("");
					tFDARitCardiaco.setEnabled(true);
					tFDARitCardiaco.setText("");
					tFDADiagnostico.setEnabled(true);
					tFDADiagnostico.setText("");
					tFDAMedicinas.setEnabled(true);
					tFDAMedicinas.setText("");
					cDDAFecha.setEnabled(true);
					tFDAAntecedentes.setEnabled(true);
					tFDAAntecedentes.setText("");
					tFDATrastorno.setEnabled(true);
					dCHCFecha.setCalendar(null);
					tFDATrastorno.setText("");
					cDDAFecha.setCalendar(null);
					cBHCEncargado.setEnabled(true);
					cBchencargado.removeAllElements();
					cBchencargado.addElement("-Seleccione-");
					cBchencargado.setSelectedItem("-Seleccione-");
					dCHCFecha.setEnabled(true);
					tFHCPruebas.setEnabled(true);
					tFHCPruebas.setText("");
					tFHCResultados.setEnabled(true);
					tFHCResultados.setText("");
				}
			});
			btnBuscarOtro.setBounds(480, 10, 105, 23);
			pBuscCita.add(btnBuscarOtro);
			
			JButton btnBuscarEditar = new JButton("Editar");
			btnBuscarEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tFDAPeso.setEnabled(true);
					tFDATalla.setEnabled(true);
					tFDAPulso.setEnabled(true);
					tFDARitCardiaco.setEnabled(true);
					tFDADiagnostico.setEnabled(true);
					tFDAMedicinas.setEnabled(true);
					cDDAFecha.setEnabled(true);
					tFDAAntecedentes.setEnabled(true);
					tFDATrastorno.setEnabled(true);
					dCHCFecha.setEnabled(true);
					tFHCPruebas.setEnabled(true);
					tFHCResultados.setEnabled(true);
					btnDAActualizar.setVisible(true);
					btnHCActualizar.setVisible(true);
					btnDAActualizar.setEnabled(true);
					btnHCActualizar.setEnabled(true);
					btnDAGuardar.setEnabled(false);
					btnHCRegistrar.setEnabled(false);
				}
			});
			btnBuscarEditar.setBounds(383, 10, 89, 23);
			pBuscCita.add(btnBuscarEditar);
			
			JButton btnRegresar = new JButton("Regresar");
			btnRegresar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PersonalMedico obj= new PersonalMedico(Usuario);
					obj.setVisible(true);
					dispose();
				}
			});
			btnRegresar.setBounds(521, 19, 89, 23);
			contentPaneCitas.add(btnRegresar);
		
		
		DefaultComboBoxModel cDoctor = new DefaultComboBoxModel();
		cDoctor.addElement("-Seleccione-");
		
		DefaultComboBoxModel cDoctorID = new DefaultComboBoxModel();
		cDoctorID.addElement("-Seleccione-");
		
		
		btnBuscarBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CnPersonal.LlenarBuscarCita(tFBuscarCita.getText(), Usuario, cEspecialidad, cBchencargado, null);
					tFBuscarCita.setEnabled(false);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
