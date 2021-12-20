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
import javax.swing.JSpinner;
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
public class PerMedPaciente extends JFrame {
	
	private JPanel content;

	
	public static String Genero=null;
	public static String TipoDoc=null;
	public static String Tipo=null;
	public static boolean correcto;
	private JTextField tFListaPaciente;
	private JTextField tFDataPaciente;
	public static JTextField tFGDataApellido;
	public static JTextField tFGDataNombre;
	public static JTextField tFGDataDocoumento;
	public static JTextField tFGDataAlergias;
	public static JTextField tFGDataObs;
	public static JTextField tFGDataTIngreso;
	public static JTextField tFADataDiagPrincipal;
	public static JTextField tFADataTalla;
	public static JTextField tFADataPulso;
	public static JTextField tFADataRCardiaco;
	public static JTextField tFADataPeso;
	public static JTextField tFADataMedicinas;
	public static JTextField tFADataTrastorno;
	@SuppressWarnings("rawtypes")
	public static DefaultComboBoxModel cBgdatatipodoc,cBgdatasexo,cBgdataeingreso,cBgdatamotivo,cBgdataalergias,cBcdatacitas,cBdclinicaencargado;
	public static JCalendar cGDataCalendario, cIDataCalendario;
	public static JDateChooser dCADataFecha,dCDClinicaFecha;
	public static JSpinner sGDataEdad;
	public static JTextField tFCDataMotivo;
	public static JTextField tFADataAntecedentes;
	public static JTextField tFDClinicaResultados;
	public static JTextField tFDClinicaPruebas;
	public static JTextField tFDClinicaNHClinica;
	public static JCalendar cCDataCalendar;
	public static JSpinner spinner,spinner_1,sIDataHabitacion,sIDataPiso;
	public static JTextField textField;
	public static JButton btnIDataHClinica,btnIDataHTraslada,btnIDataHAlta;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PerMedPaciente(String Usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cl\u00EDnica UNAC");
		setBounds(100, 100, 818, 718);
		content = new JPanel();
		content.setBackground(new Color(100, 149, 237));
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		content.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 45, 792, 632);
		content.add(tabbedPane);
		
		JPanel LPaciente = new JPanel();
		LPaciente.setBackground(new Color(240, 248, 255));
		LPaciente.setLayout(null);
		tabbedPane.addTab("Lista de Paciente", null, LPaciente, null);
		
		String columnas[]= {"ID", "Nombre","Apellido","Edad","Documento","Sexo","Tipo Sanguineo","Tipo"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		try {
			CnPersonal.llenarTablaPacientes(modelLista, Usuario);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setBounds(10, 60, 768, 533);
		scrollPane.setViewportView(list);
		LPaciente.add(scrollPane);
		
		JLabel label_1 = new JLabel("Buscar Paciente");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(25, 15, 160, 20);
		LPaciente.add(label_1);
		
		tFListaPaciente = new JTextField();
		tFListaPaciente.setColumns(10);
		tFListaPaciente.setBounds(231, 15, 90, 20);
		LPaciente.add(tFListaPaciente);
		
		JLabel btnListaBuscar = new JLabel("Buscar");
		btnListaBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					CnPersonal.buscarTablaPaciente(modelLista, Usuario, tFListaPaciente.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnListaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaBuscar.setBounds(520, 20, 46, 15);
		LPaciente.add(btnListaBuscar);
		
		JLabel btnListaShowAll = new JLabel("Mostrar Todo");
		btnListaShowAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnPersonal.llenarTablaPacientes(modelLista, Usuario);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnListaShowAll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaShowAll.setBounds(670, 20, 108, 14);
		LPaciente.add(btnListaShowAll);
		
		JPanel DPaciente = new JPanel();
		DPaciente.setBackground(new Color(240, 248, 255));
		DPaciente.setLayout(null);
		tabbedPane.addTab("Datos del Paciente", null, DPaciente, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 42, 767, 554);
		DPaciente.add(tabbedPane_1);
		
		JPanel DGeneral = new JPanel();
		DGeneral.setBackground(new Color(240, 255, 255));
		DGeneral.setLayout(null);
		tabbedPane_1.addTab("Datos Generales", null, DGeneral, null);
		
		JLabel label_6 = new JLabel("Nombres:");
		label_6.setBounds(36, 40, 71, 14);
		DGeneral.add(label_6);
		
		JLabel label_7 = new JLabel("Apellidos:");
		label_7.setBounds(330, 40, 70, 14);
		DGeneral.add(label_7);
		
		JLabel label_8 = new JLabel("Sexo:");
		label_8.setBounds(330, 90, 46, 14);
		DGeneral.add(label_8);
		
		cBgdatasexo=new DefaultComboBoxModel();
		JComboBox cBGDataSeco = new JComboBox();
		cBGDataSeco.setEnabled(false);
		cBGDataSeco.setModel(cBgdatasexo);
		cBGDataSeco.addItem("-Seleccione-");
		cBGDataSeco.setBounds(410, 90, 86, 20);
		DGeneral.add(cBGDataSeco);
		
		JLabel label_9 = new JLabel("Documento:");
		label_9.setBounds(36, 90, 71, 14);
		DGeneral.add(label_9);
		
		cBgdatatipodoc=new DefaultComboBoxModel();
		JComboBox cBGDataTipoDoc = new JComboBox();
		cBGDataTipoDoc.setEnabled(false);
		cBGDataTipoDoc.setModel(cBgdatatipodoc);
		cBGDataTipoDoc.addItem("-Seleccione-");
		cBGDataTipoDoc.setBounds(117, 90, 86, 20);
		DGeneral.add(cBGDataTipoDoc);
		
		tFGDataApellido = new JTextField();
		tFGDataApellido.setEnabled(false);
		tFGDataApellido.setColumns(10);
		tFGDataApellido.setBounds(410, 40, 190, 20);
		DGeneral.add(tFGDataApellido);
		
		tFGDataNombre = new JTextField();
		tFGDataNombre.setEnabled(false);
		tFGDataNombre.setColumns(10);
		tFGDataNombre.setBounds(117, 40, 190, 20);
		DGeneral.add(tFGDataNombre);
		
		JLabel label_10 = new JLabel("Estado de Ingreso:");
		label_10.setBounds(36, 140, 115, 14);
		DGeneral.add(label_10);
		
		tFGDataDocoumento = new JTextField();
		tFGDataDocoumento.setEnabled(false);
		tFGDataDocoumento.setColumns(10);
		tFGDataDocoumento.setBounds(221, 90, 86, 20);
		DGeneral.add(tFGDataDocoumento);
		
		JLabel label_11 = new JLabel("DATOS GENERALES");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_11.setBounds(23, 11, 143, 14);
		DGeneral.add(label_11);
		
		JLabel label_12 = new JLabel("Fecha de Nacimineto:");
		label_12.setBounds(36, 190, 132, 14);
		DGeneral.add(label_12);
		
		cBgdataeingreso=new DefaultComboBoxModel();
		JComboBox cBGDataEIngreso = new JComboBox();
		cBGDataEIngreso.setEnabled(false);
		cBGDataEIngreso.setModel(cBgdataeingreso);
		cBGDataEIngreso.addItem("-Seleccione-");
		cBGDataEIngreso.setBounds(146, 137, 83, 20);
		DGeneral.add(cBGDataEIngreso);
		
		JLabel label_13 = new JLabel("Edad:");
		label_13.setBounds(290, 140, 46, 14);
		DGeneral.add(label_13);
		
		sGDataEdad = new JSpinner();
		sGDataEdad.setEnabled(false);
		sGDataEdad.setBounds(346, 137, 34, 20);
		DGeneral.add(sGDataEdad);
		
		JLabel label_14 = new JLabel("Motivo:");
		label_14.setBounds(36, 368, 46, 14);
		DGeneral.add(label_14);
		
		JLabel label_15 = new JLabel("Alergias:");
		label_15.setBounds(36, 399, 69, 14);
		DGeneral.add(label_15);
		
		cBgdataalergias=new DefaultComboBoxModel();
		
		tFGDataAlergias = new JTextField();
		tFGDataAlergias.setEnabled(false);
		tFGDataAlergias.setColumns(10);
		tFGDataAlergias.setBounds(146, 396, 190, 20);
		DGeneral.add(tFGDataAlergias);
		
		JLabel label_16 = new JLabel("Observaciones:");
		label_16.setBounds(36, 441, 100, 14);
		DGeneral.add(label_16);
		
		tFGDataObs = new JTextField();
		tFGDataObs.setEnabled(false);
		tFGDataObs.setColumns(10);
		tFGDataObs.setBounds(146, 436, 489, 65);
		DGeneral.add(tFGDataObs);
		
		cBgdatamotivo=new DefaultComboBoxModel();
		JComboBox cBGDataMotivo = new JComboBox();
		cBGDataMotivo.setEnabled(false);
		cBGDataMotivo.setModel(cBgdatamotivo);
		cBGDataMotivo.addItem("-Seleccione-");
		cBGDataMotivo.setBounds(146, 365, 86, 20);
		DGeneral.add(cBGDataMotivo);
		
		JLabel lblTipoSanguneo = new JLabel("Tipo Sangu\u00EDneo");
		lblTipoSanguneo.setBounds(449, 140, 83, 14);
		DGeneral.add(lblTipoSanguneo);
		
		tFGDataTIngreso = new JTextField();
		tFGDataTIngreso.setEnabled(false);
		tFGDataTIngreso.setColumns(10);
		tFGDataTIngreso.setBounds(542, 137, 58, 20);
		DGeneral.add(tFGDataTIngreso);
		
		java.util.Date cGdatacalendario=null;
		cGDataCalendario = new JCalendar();
		cGDataCalendario.setBounds(160, 190, 184, 153);
		cGDataCalendario.setEnabled(false);
		DGeneral.add(cGDataCalendario);
		
		JPanel DCitas = new JPanel();
		DCitas.setBackground(new Color(240, 255, 255));
		DCitas.setLayout(null);
		DCitas.setToolTipText("AA");
		tabbedPane_1.addTab("Datos de Citas", null, DCitas, null);
		
		JLabel lblCita = new JLabel("Cita:");
		lblCita.setBounds(60, 22, 46, 14);
		DCitas.add(lblCita);
		
		cBcdatacitas=new DefaultComboBoxModel();
		JComboBox cBCDataCitas = new JComboBox();
		
		cBCDataCitas.setModel(cBcdatacitas);
		cBCDataCitas.setBounds(220, 19, 160, 20);
		DCitas.add(cBCDataCitas);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(10, 47, 742, 468);
		DCitas.add(tabbedPane_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		tabbedPane_2.addTab("Cita", null, panel, null);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("Motivo de Citas:");
		label_2.setBounds(10, 11, 150, 20);
		panel.add(label_2);
		
		JLabel lblFechaDeInscripcin = new JLabel("Fecha de Inscripci\u00F3n:");
		lblFechaDeInscripcin.setBounds(10, 61, 122, 20);
		panel.add(lblFechaDeInscripcin);
		
		cCDataCalendar = new JCalendar();
		cCDataCalendar.setBounds(170, 62, 248, 136);
		cCDataCalendar.setEnabled(false);
		panel.add(cCDataCalendar);
		
		JLabel lblHabitacin = new JLabel("Habitaci\u00F3n:");
		lblHabitacin.setBounds(58, 244, 74, 20);
		panel.add(lblHabitacin);
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setBounds(170, 244, 50, 20);
		panel.add(spinner);
		
		JLabel label_5 = new JLabel("Piso:");
		label_5.setBounds(416, 244, 46, 20);
		panel.add(label_5);
		
		spinner_1 = new JSpinner();
		spinner_1.setEnabled(false);
		spinner_1.setBounds(484, 244, 50, 20);
		panel.add(spinner_1);
		
		tFCDataMotivo = new JTextField();
		tFCDataMotivo.setEnabled(false);
		tFCDataMotivo.setBounds(170, 11, 167, 20);
		panel.add(tFCDataMotivo);
		tFCDataMotivo.setColumns(10);
		
		JPanel DAsistencial = new JPanel();
		DAsistencial.setBackground(new Color(240, 255, 255));
		tabbedPane_2.addTab("Datos Asistenciales", null, DAsistencial, null);
		DAsistencial.setLayout(null);
		DAsistencial.setToolTipText("AA");
		
		JLabel lblDiagnosticoPrincipal = new JLabel("Diagn\u00F3stico Principal:");
		lblDiagnosticoPrincipal.setBounds(36, 270, 132, 14);
		DAsistencial.add(lblDiagnosticoPrincipal);
		
		tFADataDiagPrincipal = new JTextField();
		tFADataDiagPrincipal.setEnabled(false);
		tFADataDiagPrincipal.setColumns(10);
		tFADataDiagPrincipal.setBounds(170, 267, 165, 20);
		DAsistencial.add(tFADataDiagPrincipal);
		
		JLabel label_20 = new JLabel("DATOS DE PROCESO ASISTENCIAL");
		label_20.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_20.setBounds(10, 11, 228, 14);
		DAsistencial.add(label_20);
		
		JLabel lblExploracinFisica = new JLabel("Exploraci\u00F3n Fisica");
		lblExploracinFisica.setBounds(27, 36, 104, 14);
		DAsistencial.add(lblExploracinFisica);
		
		JLabel label_22 = new JLabel("Peso:");
		label_22.setBounds(36, 70, 46, 14);
		DAsistencial.add(label_22);
		
		JLabel label_23 = new JLabel("Talla:");
		label_23.setBounds(36, 120, 46, 14);
		DAsistencial.add(label_23);
		
		JLabel label_24 = new JLabel("Pulso:");
		label_24.setBounds(36, 170, 100, 14);
		DAsistencial.add(label_24);
		
		JLabel label_25 = new JLabel("Ritmo Cardiaco:");
		label_25.setBounds(36, 220, 95, 14);
		DAsistencial.add(label_25);
		
		JLabel label_26 = new JLabel("Fecha:");
		label_26.setBounds(36, 370, 95, 14);
		DAsistencial.add(label_26);
		
		JLabel lblExploracinVerbal = new JLabel("Exploraci\u00F3n Verbal");
		lblExploracinVerbal.setBounds(330, 36, 155, 14);
		DAsistencial.add(lblExploracinVerbal);
		
		JLabel label_28 = new JLabel("Antecedentes :");
		label_28.setBounds(330, 70, 97, 14);
		DAsistencial.add(label_28);
		
		tFADataTalla = new JTextField();
		tFADataTalla.setEnabled(false);
		tFADataTalla.setColumns(10);
		tFADataTalla.setBounds(145, 120, 100, 20);
		DAsistencial.add(tFADataTalla);
		
		tFADataPulso = new JTextField();
		tFADataPulso.setEnabled(false);
		tFADataPulso.setColumns(10);
		tFADataPulso.setBounds(145, 170, 100, 20);
		DAsistencial.add(tFADataPulso);
		
		tFADataRCardiaco = new JTextField();
		tFADataRCardiaco.setEnabled(false);
		tFADataRCardiaco.setColumns(10);
		tFADataRCardiaco.setBounds(145, 220, 100, 20);
		DAsistencial.add(tFADataRCardiaco);
		
		tFADataPeso = new JTextField();
		tFADataPeso.setEnabled(false);
		tFADataPeso.setColumns(10);
		tFADataPeso.setBounds(145, 70, 100, 20);
		DAsistencial.add(tFADataPeso);
		
		JLabel label_29 = new JLabel("Medicinas:");
		label_29.setBounds(36, 320, 63, 14);
		DAsistencial.add(label_29);
		
		tFADataMedicinas = new JTextField();
		tFADataMedicinas.setEnabled(false);
		tFADataMedicinas.setColumns(10);
		tFADataMedicinas.setBounds(145, 317, 100, 20);
		DAsistencial.add(tFADataMedicinas);
		
		JLabel lblTrastorno = new JLabel("Trastorno:");
		lblTrastorno.setBounds(330, 120, 69, 14);
		DAsistencial.add(lblTrastorno);
		
		tFADataTrastorno = new JTextField();
		tFADataTrastorno.setEnabled(false);
		tFADataTrastorno.setColumns(10);
		tFADataTrastorno.setBounds(485, 117, 100, 20);
		DAsistencial.add(tFADataTrastorno);
		
		dCADataFecha = new JDateChooser();
		dCADataFecha.setBounds(145, 364, 100, 20);
		dCADataFecha.setEnabled(false);
		DAsistencial.add(dCADataFecha);
		
		tFADataAntecedentes = new JTextField();
		tFADataAntecedentes.setEnabled(false);
		tFADataAntecedentes.setBounds(485, 67, 100, 20);
		DAsistencial.add(tFADataAntecedentes);
		tFADataAntecedentes.setColumns(10);
		
		JPanel DHojaClinica = new JPanel();
		DHojaClinica.setBackground(new Color(240, 255, 255));
		tabbedPane_2.addTab("Hoja Clinica", null, DHojaClinica, null);
		DHojaClinica.setLayout(null);
		
		JLabel label_17 = new JLabel("Nombre del Encargado:");
		label_17.setBounds(28, 53, 149, 14);
		DHojaClinica.add(label_17);
		
		JLabel label_19 = new JLabel("Fecha:");
		label_19.setBounds(28, 80, 120, 14);
		DHojaClinica.add(label_19);
		
		JLabel label_31 = new JLabel("Pruebas:");
		label_31.setBounds(28, 110, 174, 14);
		DHojaClinica.add(label_31);
		
		JLabel label_32 = new JLabel("Resultados:");
		label_32.setBounds(28, 140, 94, 14);
		DHojaClinica.add(label_32);
		
		tFDClinicaResultados = new JTextField();
		tFDClinicaResultados.setEnabled(false);
		tFDClinicaResultados.setColumns(10);
		tFDClinicaResultados.setBounds(178, 140, 526, 130);
		DHojaClinica.add(tFDClinicaResultados);
		
		dCDClinicaFecha = new JDateChooser();
		dCDClinicaFecha.setBounds(178, 80, 120, 20);
		dCDClinicaFecha.setEnabled(false);
		DHojaClinica.add(dCDClinicaFecha);
		
		cBCDataCitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CnPersonal.LlenarCita((String)cBCDataCitas.getSelectedItem());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		cBdclinicaencargado=new DefaultComboBoxModel();
		JComboBox cBDClinicaEncargado = new JComboBox();
		cBDClinicaEncargado.setEnabled(false);
		cBDClinicaEncargado.setModel(cBdclinicaencargado);
		cBDClinicaEncargado.setBounds(178, 50, 120, 20);
		DHojaClinica.add(cBDClinicaEncargado);
		
		tFDClinicaPruebas = new JTextField();
		tFDClinicaPruebas.setEnabled(false);
		tFDClinicaPruebas.setColumns(10);
		tFDClinicaPruebas.setBounds(178, 107, 120, 20);
		DHojaClinica.add(tFDClinicaPruebas);
		
		JLabel lblNmeroDeHoja = new JLabel("N\u00FAmero de Hoja Cl\u00EDnica");
		lblNmeroDeHoja.setBounds(28, 25, 137, 14);
		DHojaClinica.add(lblNmeroDeHoja);
		
		tFDClinicaNHClinica = new JTextField();
		tFDClinicaNHClinica.setEnabled(false);
		tFDClinicaNHClinica.setColumns(10);
		tFDClinicaNHClinica.setBounds(178, 22, 120, 20);
		DHojaClinica.add(tFDClinicaNHClinica);
		
		JPanel DInternado = new JPanel();
		DInternado.setBackground(new Color(240, 255, 255));
		DInternado.setLayout(null);
		DInternado.setToolTipText("AA");
		tabbedPane_1.addTab("Datos de Intenados", null, DInternado, null);
		
		btnIDataHClinica = new JButton("Hojas de Curso Clinico");
		btnIDataHClinica.setEnabled(false);
		btnIDataHClinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			PerMedPacienteHojaClinica perMedPacienteHojaClinica= new PerMedPacienteHojaClinica(Usuario,tFDataPaciente.getText());
			perMedPacienteHojaClinica.setVisible(true);
			dispose();
			}
		});
		btnIDataHClinica.setBounds(108, 392, 150, 30);
		DInternado.add(btnIDataHClinica);
		
		btnIDataHAlta = new JButton("Hoja de Alta");
		btnIDataHAlta.setEnabled(false);
		btnIDataHAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PerMedPacienteHojaAlta perMedPacienteHojaAlta= new PerMedPacienteHojaAlta(Usuario,tFDataPaciente.getText());
				perMedPacienteHojaAlta.setVisible(true);
				dispose();
			}
		});
		btnIDataHAlta.setBounds(486, 392, 150, 30);
		DInternado.add(btnIDataHAlta);
		
		JLabel label_33 = new JLabel("Solo para pacientes que se encuentren internados ");
		label_33.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label_33.setBounds(23, 11, 330, 20);
		DInternado.add(label_33);
		
		JLabel label_34 = new JLabel("Motivo de Internamiento:");
		label_34.setBounds(58, 58, 150, 20);
		DInternado.add(label_34);
		
		JLabel lblFechaDeInscripcin_1 = new JLabel("Fecha de Inscripci\u00F3n:");
		lblFechaDeInscripcin_1.setBounds(58, 108, 122, 20);
		DInternado.add(lblFechaDeInscripcin_1);
		
		java.util.Date cIdatacalendario=null;
		
		cIDataCalendario = new JCalendar();
		cIDataCalendario.setBounds(218, 109, 248, 136);
		cIDataCalendario.setEnabled(false);
		DInternado.add(cIDataCalendario);
		
		JLabel lblHabitacin_1 = new JLabel("Habitaci\u00F3n:");
		lblHabitacin_1.setBounds(108, 318, 74, 20);
		DInternado.add(lblHabitacin_1);
		
		sIDataHabitacion = new JSpinner();
		sIDataHabitacion.setEnabled(false);
		sIDataHabitacion.setBounds(218, 318, 50, 20);
		DInternado.add(sIDataHabitacion);
		
		JLabel label_38 = new JLabel("Piso:");
		label_38.setBounds(384, 318, 46, 20);
		DInternado.add(label_38);
		
		sIDataPiso = new JSpinner();
		sIDataPiso.setEnabled(false);
		sIDataPiso.setBounds(448, 318, 50, 20);
		DInternado.add(sIDataPiso);
		
		btnIDataHTraslada = new JButton("Hoja de Traslado");
		btnIDataHTraslada.setEnabled(false);
		btnIDataHTraslada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PerMedPacienteHojaTranslado perMedPacienteHojaTranslado=new PerMedPacienteHojaTranslado(Usuario,tFDataPaciente.getText());
				perMedPacienteHojaTranslado.setVisible(true);
				dispose();
			}
		});
		btnIDataHTraslada.setBounds(296, 392, 150, 30);
		DInternado.add(btnIDataHTraslada);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(218, 58, 248, 20);
		DInternado.add(textField);
		textField.setColumns(10);
		
		JLabel IDPaciente = new JLabel("ID Paciente:");
		IDPaciente.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		IDPaciente.setBounds(10, 10, 79, 20);
		DPaciente.add(IDPaciente);
		
		tFDataPaciente = new JTextField();
		tFDataPaciente.setColumns(10);
		tFDataPaciente.setBounds(99, 11, 121, 20);
		DPaciente.add(tFDataPaciente);
		
		JButton btnDataBuscar = new JButton("Buscar");
		btnDataBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PerMedPaciente.btnIDataHClinica.setEnabled(false);
					PerMedPaciente.btnIDataHTraslada.setEnabled(false);
					PerMedPaciente.btnIDataHAlta.setEnabled(false);
					CnPersonal.LlenarBuscarPaciente(tFDataPaciente.getText(),cGdatacalendario);
					CnPersonal.LlenarListaCitas(tFDataPaciente.getText(), Usuario, cBcdatacitas);
					CnPersonal.llenarInternados(tFDataPaciente.getText(),cIdatacalendario);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDataBuscar.setBounds(239, 11, 90, 20);
		DPaciente.add(btnDataBuscar);
		
		JButton button = new JButton("Regresar");
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PersonalMedico obj= new PersonalMedico(Usuario);
				obj.setVisible(true);
				dispose();
			}
		});
		button.setBounds(702, 12, 100, 30);
		content.add(button);
		
		JLabel label = new JLabel("Paciente:");
		label.setBounds(10, 9, 190, 25);
		label.setFont(new Font("Tahoma", Font.BOLD, 25));
		content.add(label);
	}
}