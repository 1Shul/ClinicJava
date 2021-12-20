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

import conexión.CnAdministrador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AdmnPaciente extends JFrame {

	private JPanel contentPaneAdmnPaciente;

	
	public static String Genero=null;
	public static String TipoDoc=null;
	public static String Tipo=null;
	public static boolean correcto;
	private JTextField tFListIDPaciente;
	private JTextField tFDataIDPaciente;
	public static JTextField tFGDataApellidoPaciente;
	public static JTextField tFGDataNombrePaciente;
	public static JTextField tFGDataDocNum;
	public static JTextField tFGDataAllergy;
	public static JTextField tFGDataObservations;
	public static JTextField tFGDataBloodType;
	public static JTextField tFADataPrincipalDiagnostic;
	public static JTextField tFADataSize;
	public static JTextField tFADataPulse;
	public static JTextField tFADataHRithm;
	public static JTextField tFADataWeigth;
	public static JTextField tFADataDrugs;
	public static JTextField tFADataDisorder;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	public static JTextField textField_5;
	public static JSpinner GDataEdadPaciente,spinner,spinner_1,sIDataHabitacion,sIDataPiso;
	@SuppressWarnings("rawtypes")
	public static DefaultComboBoxModel cBdatasexo,cBgdatainstate,cBgdatadoctype,cBgdatamatter,combobox_1,combobox_2,cBidatadoctor;
	public static java.util.Date cDgdataborndate,Calendar,cDdatadata,datechooser,cIdatacalendario;
	public static JDateChooser dCGDataBornDate,dCADataData,dateChooser;
	public static JCalendar calendar,cIDataCalendario;
	@SuppressWarnings("rawtypes")
	public static JComboBox cBGDataDocType,cBDataSexo,cBGDataInState,cBGDataMatter;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AdmnPaciente(int a,int b) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cl\u00EDnica UNAC");
		setBounds(100, 100, 818, 646);
		contentPaneAdmnPaciente = new JPanel();
		contentPaneAdmnPaciente.setBackground(new Color(100, 149, 237));
		contentPaneAdmnPaciente.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAdmnPaciente);
		contentPaneAdmnPaciente.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 45, 792, 573);
		contentPaneAdmnPaciente.add(tabbedPane);
		
		JPanel pAdmnPacienteList = new JPanel();
		pAdmnPacienteList.setBackground(new Color(240, 248, 255));
		pAdmnPacienteList.setLayout(null);
		tabbedPane.addTab("Lista de Paciente", null, pAdmnPacienteList, null);
		
		String columnas[]= {"ID", "Nombre","Apellido","Edad","Documento","Sexo","Estado"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		try {
			CnAdministrador.llenarTablaPacientes(modelLista);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setBounds(10, 46, 767, 495);
		scrollPane.setViewportView(list);
		pAdmnPacienteList.add(scrollPane);
		
		JLabel lblListSearchPaciente = new JLabel("Buscar Paciente");
		lblListSearchPaciente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblListSearchPaciente.setBounds(20, 15, 160, 20);
		pAdmnPacienteList.add(lblListSearchPaciente);
		
		tFListIDPaciente = new JTextField();
		tFListIDPaciente.setColumns(10);
		tFListIDPaciente.setBounds(173, 17, 149, 20);
		pAdmnPacienteList.add(tFListIDPaciente);
		
		JLabel btnListSearchPaciente = new JLabel("Buscar");
		btnListSearchPaciente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnAdministrador.buscarTablaPaciente(modelLista, tFListIDPaciente.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListSearchPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListSearchPaciente.setBounds(600, 20, 46, 15);
		pAdmnPacienteList.add(btnListSearchPaciente);
		
		JLabel btnListShowAll = new JLabel("Mostrar Todo");
		btnListShowAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnAdministrador.llenarTablaPacientes(modelLista);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnListShowAll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListShowAll.setBounds(667, 21, 89, 14);
		pAdmnPacienteList.add(btnListShowAll);
		
		JPanel pAdmnPacienteData = new JPanel();
		pAdmnPacienteData.setBackground(new Color(240, 248, 255));
		pAdmnPacienteData.setLayout(null);
		tabbedPane.addTab("Datos del Paciente", null, pAdmnPacienteData, null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 42, 767, 503);
		pAdmnPacienteData.add(tabbedPane_1);
		
		cBdatasexo=new DefaultComboBoxModel();
		
		cBgdatadoctype=new DefaultComboBoxModel();
		
		cBgdatainstate=new DefaultComboBoxModel();
		
		cBgdatamatter=new DefaultComboBoxModel();
		
		cDgdataborndate=null;
		
		DefaultComboBoxModel cBdataappointment=new DefaultComboBoxModel();
		
		Calendar=null;
		
		combobox_1=new DefaultComboBoxModel();
		
		cDdatadata=null;
		
		datechooser=null;
		
		combobox_2=new DefaultComboBoxModel();
		
		JPanel DGeneral = new JPanel();
		DGeneral.setBackground(new Color(240, 255, 240));
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
		cBDataSexo = new JComboBox();
		cBDataSexo.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "Masculino", "Femenino"}));
		cBDataSexo.setEnabled(false);
		cBDataSexo.setBounds(410, 87, 86, 20);
		DGeneral.add(cBDataSexo);
		
		JLabel label_9 = new JLabel("Documento:");
		label_9.setBounds(36, 90, 71, 14);
		DGeneral.add(label_9);
		cBGDataDocType = new JComboBox();
		cBGDataDocType.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "DNI", "Carnet de Extranjería"}));
		cBGDataDocType.setEnabled(false);
		cBGDataDocType.setBounds(117, 90, 93, 20);
		DGeneral.add(cBGDataDocType);
		
		tFGDataApellidoPaciente = new JTextField();
		tFGDataApellidoPaciente.setEnabled(false);
		tFGDataApellidoPaciente.setColumns(10);
		tFGDataApellidoPaciente.setBounds(410, 40, 190, 20);
		DGeneral.add(tFGDataApellidoPaciente);
		
		tFGDataNombrePaciente = new JTextField();
		tFGDataNombrePaciente.setEnabled(false);
		tFGDataNombrePaciente.setColumns(10);
		tFGDataNombrePaciente.setBounds(117, 40, 190, 20);
		DGeneral.add(tFGDataNombrePaciente);
		
		JLabel label_10 = new JLabel("Estado de Ingreso:");
		label_10.setBounds(36, 140, 115, 14);
		DGeneral.add(label_10);
		
		tFGDataDocNum = new JTextField();
		tFGDataDocNum.setEnabled(false);
		tFGDataDocNum.setColumns(10);
		tFGDataDocNum.setBounds(221, 90, 86, 20);
		DGeneral.add(tFGDataDocNum);
		
		JLabel label_11 = new JLabel("DATOS GENERALES");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_11.setBounds(25, 11, 143, 14);
		DGeneral.add(label_11);
		
		JLabel label_12 = new JLabel("Fecha de Nacimineto:");
		label_12.setBounds(36, 190, 132, 14);
		DGeneral.add(label_12);
		cBGDataInState = new JComboBox();
		cBGDataInState.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "Registrado", "Hospitalizado"}));
		cBGDataInState.setEnabled(false);
		cBGDataInState.setBounds(163, 137, 83, 20);
		DGeneral.add(cBGDataInState);
		
		JLabel label_13 = new JLabel("Edad:");
		label_13.setBounds(290, 140, 46, 14);
		DGeneral.add(label_13);
		
		GDataEdadPaciente = new JSpinner();
		GDataEdadPaciente.setEnabled(false);
		GDataEdadPaciente.setBounds(330, 140, 50, 20);
		DGeneral.add(GDataEdadPaciente);
		
		JLabel label_14 = new JLabel("Motivo:");
		label_14.setBounds(36, 240, 46, 14);
		DGeneral.add(label_14);
		
		JLabel label_15 = new JLabel("Alergias:");
		label_15.setBounds(36, 290, 69, 14);
		DGeneral.add(label_15);
		
		tFGDataAllergy = new JTextField();
		tFGDataAllergy.setEnabled(false);
		tFGDataAllergy.setColumns(10);
		tFGDataAllergy.setBounds(146, 290, 190, 20);
		DGeneral.add(tFGDataAllergy);
		
		JLabel label_16 = new JLabel("Observaciones:");
		label_16.setBounds(36, 390, 100, 14);
		DGeneral.add(label_16);
		
		tFGDataObservations = new JTextField();
		tFGDataObservations.setEnabled(false);
		tFGDataObservations.setColumns(10);
		tFGDataObservations.setBounds(146, 390, 489, 65);
		DGeneral.add(tFGDataObservations);
		cBGDataMatter = new JComboBox();
		cBGDataMatter.setModel(new DefaultComboBoxModel(new String[] {"-Vacio-", "Cita", "Emergencia"}));
		cBGDataMatter.setEnabled(false);
		cBGDataMatter.setBounds(160, 240, 86, 20);
		DGeneral.add(cBGDataMatter);
		
		JLabel lblTipoSanguneo = new JLabel("Tipo Sangu\u00CDneo");
		lblTipoSanguneo.setBounds(439, 140, 93, 14);
		DGeneral.add(lblTipoSanguneo);
		
		tFGDataBloodType = new JTextField();
		tFGDataBloodType.setEnabled(false);
		tFGDataBloodType.setColumns(10);
		tFGDataBloodType.setBounds(542, 140, 58, 20);
		DGeneral.add(tFGDataBloodType);
		dCGDataBornDate = new JDateChooser();
		dCGDataBornDate.setEnabled(false);
		dCGDataBornDate.setBounds(166, 184, 100, 20);
		DGeneral.add(dCGDataBornDate);
		
		cIdatacalendario=null;
		
		cBidatadoctor=new DefaultComboBoxModel();
		
		JPanel DCita = new JPanel();
		DCita.setBackground(new Color(240, 248, 255));
		tabbedPane_1.addTab("Datos de Citas", null, DCita, null);
		DCita.setLayout(null);
		DCita.setToolTipText("AA");
		
		JLabel lblCitas = new JLabel("Citas:");
		lblCitas.setBounds(60, 22, 46, 14);
		DCita.add(lblCitas);
		JComboBox cBCDataAppointment = new JComboBox();
		cBCDataAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CnAdministrador.LlenarCita((String)cBCDataAppointment.getSelectedItem());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		cBCDataAppointment.setModel(cBdataappointment);
		cBCDataAppointment.setBounds(220, 19, 160, 20);
		DCita.add(cBCDataAppointment);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(10, 57, 742, 418);
		DCita.add(tabbedPane_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		tabbedPane_2.addTab("Cita", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Motivo de Citas:");
		label.setBounds(24, 11, 150, 20);
		panel.add(label);
		
		JLabel lblFechaDeInscripcin = new JLabel("Fecha de Inscripci\u00F3n:");
		lblFechaDeInscripcin.setBounds(24, 61, 122, 20);
		panel.add(lblFechaDeInscripcin);
		calendar = new JCalendar();
		calendar.setBounds(184, 62, 248, 136);
		panel.add(calendar);
		calendar.setEnabled(false);
		
		JLabel label_2 = new JLabel("Doctor Encargado:");
		label_2.setBounds(24, 221, 106, 14);
		panel.add(label_2);
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.setModel(combobox_1);
		comboBox_1.setBounds(184, 218, 160, 20);
		panel.add(comboBox_1);
		
		JLabel lblHabitacin = new JLabel("Habitaci\u00F3n:");
		lblHabitacin.setBounds(72, 335, 74, 20);
		panel.add(lblHabitacin);
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setBounds(183, 335, 50, 20);
		panel.add(spinner);
		
		JLabel label_4 = new JLabel("Piso:");
		label_4.setBounds(386, 335, 46, 20);
		panel.add(label_4);
		
		spinner_1 = new JSpinner();
		spinner_1.setEnabled(false);
		spinner_1.setBounds(442, 335, 50, 20);
		panel.add(spinner_1);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(184, 11, 210, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel DAsistencial = new JPanel();
		DAsistencial.setBackground(new Color(240, 255, 240));
		tabbedPane_2.addTab("Datos Asistenciales", null, DAsistencial, null);
		DAsistencial.setLayout(null);
		DAsistencial.setToolTipText("AA");
		
		JLabel lblDiagnosticoPrincipal = new JLabel("Diagnostico Principal:");
		lblDiagnosticoPrincipal.setBounds(36, 270, 132, 14);
		DAsistencial.add(lblDiagnosticoPrincipal);
		
		tFADataPrincipalDiagnostic = new JTextField();
		tFADataPrincipalDiagnostic.setEnabled(false);
		tFADataPrincipalDiagnostic.setColumns(10);
		tFADataPrincipalDiagnostic.setBounds(170, 267, 165, 20);
		DAsistencial.add(tFADataPrincipalDiagnostic);
		
		JLabel label_20 = new JLabel("DATOS DE PROCESO ASISTENCIAL");
		label_20.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_20.setBounds(10, 11, 228, 14);
		DAsistencial.add(label_20);
		
		JLabel lblExploracinFsica = new JLabel("Exploraci\u00F3n F\u00EDsica");
		lblExploracinFsica.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblExploracinFsica.setBounds(20, 36, 104, 14);
		DAsistencial.add(lblExploracinFsica);
		
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
		lblExploracinVerbal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblExploracinVerbal.setBounds(317, 36, 131, 14);
		DAsistencial.add(lblExploracinVerbal);
		
		JLabel label_28 = new JLabel("Antecedentes :");
		label_28.setBounds(330, 70, 97, 14);
		DAsistencial.add(label_28);
		
		tFADataSize = new JTextField();
		tFADataSize.setEnabled(false);
		tFADataSize.setColumns(10);
		tFADataSize.setBounds(145, 120, 100, 20);
		DAsistencial.add(tFADataSize);
		
		tFADataPulse = new JTextField();
		tFADataPulse.setEnabled(false);
		tFADataPulse.setColumns(10);
		tFADataPulse.setBounds(145, 170, 100, 20);
		DAsistencial.add(tFADataPulse);
		
		tFADataHRithm = new JTextField();
		tFADataHRithm.setEnabled(false);
		tFADataHRithm.setColumns(10);
		tFADataHRithm.setBounds(145, 220, 100, 20);
		DAsistencial.add(tFADataHRithm);
		
		tFADataWeigth = new JTextField();
		tFADataWeigth.setEnabled(false);
		tFADataWeigth.setColumns(10);
		tFADataWeigth.setBounds(145, 70, 100, 20);
		DAsistencial.add(tFADataWeigth);
		
		JLabel label_29 = new JLabel("Medicinas:");
		label_29.setBounds(36, 320, 63, 14);
		DAsistencial.add(label_29);
		
		tFADataDrugs = new JTextField();
		tFADataDrugs.setEnabled(false);
		tFADataDrugs.setColumns(10);
		tFADataDrugs.setBounds(145, 317, 100, 20);
		DAsistencial.add(tFADataDrugs);
		
		JLabel lblTrastorno = new JLabel("Trastorno:");
		lblTrastorno.setBounds(330, 120, 69, 14);
		DAsistencial.add(lblTrastorno);
		
		tFADataDisorder = new JTextField();
		tFADataDisorder.setEnabled(false);
		tFADataDisorder.setColumns(10);
		tFADataDisorder.setBounds(504, 117, 100, 20);
		DAsistencial.add(tFADataDisorder);
		dCADataData = new JDateChooser();
		dCADataData.setEnabled(false);
		dCADataData.setBounds(145, 364, 100, 20);
		DAsistencial.add(dCADataData);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setBounds(504, 67, 100, 20);
		DAsistencial.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 255, 240));
		tabbedPane_2.addTab("Hoja Cl�nica", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_5 = new JLabel("Nombre del Encargado:");
		label_5.setBounds(28, 60, 149, 14);
		panel_1.add(label_5);
		
		JLabel label_17 = new JLabel("Fecha:");
		label_17.setBounds(28, 87, 120, 14);
		panel_1.add(label_17);
		
		JLabel label_19 = new JLabel("Pruebas:");
		label_19.setBounds(28, 117, 137, 14);
		panel_1.add(label_19);
		
		JLabel label_32 = new JLabel("Resultados:");
		label_32.setBounds(28, 147, 94, 14);
		panel_1.add(label_32);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(132, 147, 379, 50);
		panel_1.add(textField_1);
		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.setBounds(178, 87, 120, 20);
		panel_1.add(dateChooser);
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(combobox_2);
		comboBox_2.setEnabled(false);
		comboBox_2.setBounds(178, 57, 120, 20);
		panel_1.add(comboBox_2);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setColumns(10);
		textField_2.setBounds(178, 114, 120, 20);
		panel_1.add(textField_2);
		
		JLabel lblNumeroDeHoja = new JLabel("Numero de Hoja Cl\u00EDnica:");
		lblNumeroDeHoja.setBounds(28, 32, 137, 14);
		panel_1.add(lblNumeroDeHoja);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setColumns(10);
		textField_3.setBounds(178, 29, 120, 20);
		panel_1.add(textField_3);
		
		JPanel DInternado = new JPanel();
		DInternado.setBackground(new Color(240, 248, 255));
		DInternado.setLayout(null);
		DInternado.setToolTipText("AA");
		tabbedPane_1.addTab("Datos de Internado", null, DInternado, null);
		
		JLabel label_33 = new JLabel("Solo para AdmnPacientes que se encuentren internados ");
		label_33.setBounds(27, 11, 330, 20);
		DInternado.add(label_33);
		
		JLabel label_34 = new JLabel("Motivo de Internamiento:");
		label_34.setBounds(60, 60, 150, 20);
		DInternado.add(label_34);
		
		JLabel lblFechaDeInscripcin_1 = new JLabel("Fecha de Inscripci\u00F3n:");
		lblFechaDeInscripcin_1.setBounds(60, 110, 122, 20);
		DInternado.add(lblFechaDeInscripcin_1);
		cIDataCalendario = new JCalendar();
		cIDataCalendario.setEnabled(false);
		cIDataCalendario.setBounds(220, 111, 248, 136);
		DInternado.add(cIDataCalendario);
		
		JLabel label_36 = new JLabel("Doctor Encargado:");
		label_36.setBounds(60, 270, 106, 14);
		DInternado.add(label_36);
		JComboBox cBIDataDoctor = new JComboBox();
		cBIDataDoctor.setModel(cBidatadoctor);
		cBIDataDoctor.setEnabled(false);
		cBIDataDoctor.setBounds(220, 267, 160, 20);
		DInternado.add(cBIDataDoctor);
		
		JLabel lblHabitacin_1 = new JLabel("Habitaci\u00F3n:");
		lblHabitacin_1.setBounds(60, 320, 74, 20);
		DInternado.add(lblHabitacin_1);
		
		sIDataHabitacion = new JSpinner();
		sIDataHabitacion.setEnabled(false);
		sIDataHabitacion.setBounds(220, 320, 50, 20);
		DInternado.add(sIDataHabitacion);
		
		JLabel label_38 = new JLabel("Piso:");
		label_38.setBounds(330, 320, 46, 20);
		DInternado.add(label_38);
		
		sIDataPiso = new JSpinner();
		sIDataPiso.setEnabled(false);
		sIDataPiso.setBounds(450, 320, 50, 20);
		DInternado.add(sIDataPiso);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setBounds(223, 60, 197, 20);
		DInternado.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel IDPaciente = new JLabel("ID Paciente:");
		IDPaciente.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		IDPaciente.setBounds(10, 11, 100, 20);
		pAdmnPacienteData.add(IDPaciente);
		
		tFDataIDPaciente = new JTextField();
		tFDataIDPaciente.setColumns(10);
		tFDataIDPaciente.setBounds(120, 11, 100, 20);
		pAdmnPacienteData.add(tFDataIDPaciente);
		
		JButton btnDataShow = new JButton("Mostrar");
		btnDataShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CnAdministrador.LlenarBuscarPaciente(tFDataIDPaciente.getText());
					CnAdministrador.LlenarListaCitas(tFDataIDPaciente.getText(),cBdataappointment);
					CnAdministrador.llenarInternados(tFDataIDPaciente.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDataShow.setBounds(230, 10, 90, 21);
		pAdmnPacienteData.add(btnDataShow);
		
		JButton btnBuscarOtro = new JButton("Buscar otro");
		btnBuscarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tFDataIDPaciente.setText("");
				tFGDataNombrePaciente.setText("");
				tFGDataApellidoPaciente.setText("");
				tFGDataDocNum.setText("");
				tFGDataBloodType.setText("");
				tFGDataAllergy.setText("");
				tFGDataObservations.setText("");
				
				
				
			}
		});
		btnBuscarOtro.setBounds(614, 12, 140, 20);
		pAdmnPacienteData.add(btnBuscarOtro);
		
		JButton button = new JButton("Regresar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Administrador administrador= new Administrador();
				administrador.setVisible(true);
				dispose();
			}
		});
		button.setBounds(702, 16, 100, 22);
		contentPaneAdmnPaciente.add(button);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setBounds(10, 9, 190, 25);
		lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPaneAdmnPaciente.add(lblPaciente);
			
		switch(a)
		{
			case 1:tabbedPane.setSelectedComponent(pAdmnPacienteList);break;
			case 2:tabbedPane.setSelectedComponent(pAdmnPacienteData);break;
		}
		
		switch(b)
		{
			case 1:tabbedPane_1.setSelectedComponent(DGeneral);break;
			case 2:tabbedPane_1.setSelectedComponent(DAsistencial);break;
			case 3:tabbedPane_1.setSelectedComponent(DCita);break;
			case 4:tabbedPane_1.setSelectedComponent(DInternado);break;
		}
	}
}