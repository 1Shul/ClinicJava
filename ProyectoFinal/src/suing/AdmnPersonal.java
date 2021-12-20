package suing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import conexión.CnAdministrador;

import java.awt.Color;
import java.awt.Toolkit;
import com.toedter.calendar.JCalendar;

@SuppressWarnings("serial")
public class AdmnPersonal extends JFrame {

	private JPanel contentPanePersonal;
	private JTextField tFListaID;
	private JTextField tFRegNombre;
	private JTextField tFRegEdad;
	private JTextField tFRegApellido;
	private JTextField tFRegEspecializacion;
	private JTextField tFRegProvincia;
	private JTextField tFRegDireccion;
	private JTextField tFRegTelefono;
	private JTextField tFRegDocumento;
	private JTextField tFRegID;
	public static JTextField tFRegDistrito;
	public static JTextField tFIEditNombre;
	public static JTextField tFIEditEdad;
	public static JTextField tFIEditApellido;
	public static JTextField tFIEditEspecializacion;
	public static JTextField tFIEditProvincia;
	public static JTextField tFIEditDireccion;
	public static JTextField tFIEditTelefono;
	public static JTextField tFIEditDocumento;
	public static JTextField tFIEditDistrito;
	private JTextField tFEditID;
	public static String Genero,GSanguineo,Documento,ECivil,FIngreso;
	@SuppressWarnings("rawtypes")
	public static JComboBox cBIEditECivil,cBIEditDocumento,cBIEditGSanguineo,cBIEditGenero;
	public static java.util.Date dCdeditdespedido;
	@SuppressWarnings("rawtypes")
	public static DefaultComboBoxModel cBieditgenero,cBieditgsanguineo,cBieditdocumento,cBieditecivil;
	public static java.util.Date cDieditfingreso;
	public static JDateChooser dCIEditFIngreso;
	public static JTextField textField;
	public static JCalendar calendar;
	public static JButton button;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AdmnPersonal(int a) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setTitle("Cl\u00EDnica UNAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 638);
		contentPanePersonal = new JPanel();
		contentPanePersonal.setBackground(new Color(100, 149, 237));
		contentPanePersonal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanePersonal);
		contentPanePersonal.setLayout(null);
		
		JLabel lblPersonal = new JLabel("Personal");
		lblPersonal.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPersonal.setBounds(10, 10, 185, 25);
		contentPanePersonal.add(lblPersonal);
		
		JTabbedPane tabbedPanePersonal = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanePersonal.setBounds(10, 45, 782, 544);
		contentPanePersonal.add(tabbedPanePersonal);
		
		JPanel pList = new JPanel();
		pList.setBackground(new Color(240, 248, 255));
		tabbedPanePersonal.addTab("Lista Personal", null, pList, null);
		pList.setLayout(null);
		
		String columnas[]= {"ID","Puesto ", "Nombre","Apellido","Documento","Telefono","Especializacion","Estado"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		
		try {
			CnAdministrador.LlenarTablaPersonal(modelLista);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		JScrollPane scroller = new JScrollPane();
		scroller.setPreferredSize(new Dimension(400,150));
		scroller.setViewportView (list);
		scroller.setBounds(10, 60, 757, 445);
		pList.add(scroller);
		
		JLabel lblBuscarDoctor = new JLabel("Buscar Doctor");
		lblBuscarDoctor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBuscarDoctor.setBounds(23, 20, 160, 20);
		pList.add(lblBuscarDoctor);
		
		tFListaID = new JTextField();
		tFListaID.setBounds(181, 22, 259, 20);
		pList.add(tFListaID);
		tFListaID.setColumns(10);
		
		JLabel btnListaBuscar = new JLabel("Buscar");
		btnListaBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					CnAdministrador.buscarTablaPersonal(modelLista, tFListaID.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		});
		btnListaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaBuscar.setBounds(573, 23, 46, 15);
		pList.add(btnListaBuscar);
		
		JLabel lblMostrarTodo = new JLabel("Mostrar Todo");
		lblMostrarTodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					CnAdministrador.LlenarTablaPersonal(modelLista);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		lblMostrarTodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMostrarTodo.setBounds(659, 23, 108, 14);
		pList.add(lblMostrarTodo);
		
		JPanel pReg = new JPanel();
		pReg.setBackground(new Color(240, 248, 255));
		tabbedPanePersonal.addTab("Registro de Personal", null, pReg, null);
		pReg.setLayout(null);
		
		JLabel lblIdDeDoctor = new JLabel("ID de Personal:");
		lblIdDeDoctor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdDeDoctor.setBounds(40, 14, 134, 20);
		pReg.add(lblIdDeDoctor);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombres.setBounds(40, 110, 80, 15);
		pReg.add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellidos.setBounds(260, 110, 65, 15);
		pReg.add(lblApellidos);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEdad.setBounds(40, 165, 46, 15);
		pReg.add(lblEdad);
		
		JLabel lblEspecializacin = new JLabel("Especializaci\u00F3n:");
		lblEspecializacin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEspecializacin.setBounds(260, 165, 97, 15);
		pReg.add(lblEspecializacin);
		
		JLabel lblGnero = new JLabel("G\u00E9nero:");
		lblGnero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGnero.setBounds(40, 220, 90, 14);
		pReg.add(lblGnero);
		
		JLabel lblGrupoSanguneo = new JLabel("Grupo Sangu\u00EDneo:");
		lblGrupoSanguneo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGrupoSanguneo.setBounds(260, 220, 125, 15);
		pReg.add(lblGrupoSanguneo);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDireccin.setBounds(40, 275, 90, 15);
		pReg.add(lblDireccin);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefono.setBounds(260, 330, 125, 15);
		pReg.add(lblTelefono);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProvincia.setBounds(40, 330, 80, 15);
		pReg.add(lblProvincia);
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEstadoCivil.setBounds(260, 399, 97, 15);
		pReg.add(lblEstadoCivil);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDocumento.setBounds(40, 399, 80, 15);
		pReg.add(lblDocumento);
		
		JLabel lblFechaDeIngreso = new JLabel("Fecha de Ingreso:");
		lblFechaDeIngreso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFechaDeIngreso.setBounds(260, 440, 125, 20);
		pReg.add(lblFechaDeIngreso);
		JComboBox cBRegECivil = new JComboBox();
		cBRegECivil.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "Soltero(a)", "Casado(a)", "Divorsiado(a)", "Viudo(a)"}));
		cBRegECivil.setBounds(390, 398, 90, 20);
		pReg.add(cBRegECivil);
		
		
		JComboBox cBRegTipoDoc = new JComboBox();
		cBRegTipoDoc.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "D.N.I.", "Carnet de Extranjer\u00EDa"}));
		cBRegTipoDoc.setBounds(140, 398, 90, 20);
		pReg.add(cBRegTipoDoc);
		
		
		
		JComboBox cBRegGenero = new JComboBox();
		cBRegGenero.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "Masculino", "Femenino"}));
		cBRegGenero.setBounds(140, 214, 90, 20);
		pReg.add(cBRegGenero);
		
		
		
		JComboBox cBRegGSanguineo = new JComboBox();
		cBRegGSanguineo.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "A", "B", "AB", "O"}));
		cBRegGSanguineo.setBounds(390, 214, 90, 20);
		pReg.add(cBRegGSanguineo);
		
		JButton btnRegRegistrar = new JButton("Registrar");
		btnRegRegistrar.setBounds(619, 464, 134, 23);
		pReg.add(btnRegRegistrar);
		
		tFRegNombre = new JTextField();
		tFRegNombre.setBounds(140, 105, 90, 20);
		pReg.add(tFRegNombre);
		tFRegNombre.setColumns(10);
		
		tFRegEdad = new JTextField();
		tFRegEdad.setColumns(10);
		tFRegEdad.setBounds(140, 160, 90, 20);
		pReg.add(tFRegEdad);
		
		tFRegApellido = new JTextField();
		tFRegApellido.setColumns(10);
		tFRegApellido.setBounds(390, 105, 90, 20);
		pReg.add(tFRegApellido);
		
		tFRegEspecializacion = new JTextField();
		tFRegEspecializacion.setColumns(10);
		tFRegEspecializacion.setBounds(390, 160, 90, 20);
		pReg.add(tFRegEspecializacion);
		
		tFRegProvincia = new JTextField();
		tFRegProvincia.setColumns(10);
		tFRegProvincia.setBounds(140, 325, 90, 20);
		pReg.add(tFRegProvincia);
		
		tFRegDireccion = new JTextField();
		tFRegDireccion.setColumns(10);
		tFRegDireccion.setBounds(140, 270, 340, 20);
		pReg.add(tFRegDireccion);
		
		tFRegTelefono = new JTextField();
		tFRegTelefono.setColumns(10);
		tFRegTelefono.setBounds(390, 325, 90, 20);
		pReg.add(tFRegTelefono);
		
		
		JLabel lblNdocumento = new JLabel("N\u00B0 Documento:");
		lblNdocumento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNdocumento.setBounds(40, 440, 103, 15);
		pReg.add(lblNdocumento);
		
		tFRegDocumento = new JTextField();
		tFRegDocumento.setColumns(10);
		tFRegDocumento.setBounds(140, 435, 90, 20);
		pReg.add(tFRegDocumento);
				
		tFRegID = new JTextField();
		tFRegID.setEnabled(false);
		tFRegID.setBounds(194, 17, 103, 20);
		pReg.add(tFRegID);
		tFRegID.setColumns(10);
		
		try
		{
			tFRegID.setText(CnAdministrador.CodigoAleatorio());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		JLabel lblDistrito = new JLabel("Distrito:");
		lblDistrito.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDistrito.setBounds(40, 356, 65, 15);
		pReg.add(lblDistrito);
		
		tFRegDistrito = new JTextField();
		tFRegDistrito.setBounds(140, 356, 90, 20);
		pReg.add(tFRegDistrito);
		tFRegDistrito.setColumns(10);
		
		JDateChooser dCRegFIngreso = new JDateChooser();
		dCRegFIngreso.setBounds(395, 440, 95, 20);
		pReg.add(dCRegFIngreso);
		
		JLabel lblPuesto = new JLabel("Puesto:");
		lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPuesto.setBounds(40, 62, 80, 15);
		pReg.add(lblPuesto);
		
		JComboBox cBRegPuesto = new JComboBox();
		cBRegPuesto.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "Doctor", "Enfemero"}));
		cBRegPuesto.setBounds(140, 61, 90, 20);
		pReg.add(cBRegPuesto);
		
		JButton btnNewButton = new JButton("Nuevo Registro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tFRegNombre.setText("");
				tFRegEdad.setText("");
				tFRegApellido.setText("");
				tFRegEspecializacion.setText("");
				tFRegDireccion.setText("");
				tFRegProvincia.setText("");
				tFRegTelefono.setText("");
				tFRegApellido.setText("");
				tFRegDistrito.setText("");
				tFRegDocumento.setText("");
				cBRegPuesto.setSelectedIndex(0);
				cBRegECivil.setSelectedIndex(0);
				cBRegGenero.setSelectedIndex(0);
				cBRegGSanguineo.setSelectedIndex(0);
				cBRegTipoDoc.setSelectedIndex(0);
				dCRegFIngreso.setCalendar(null);
				tFRegNombre.setEnabled(true);
				tFRegEdad.setEnabled(true);
				tFRegApellido.setEnabled(true);
				tFRegEspecializacion.setEnabled(true);
				tFRegDireccion.setEnabled(true);
				tFRegProvincia.setEnabled(true);
				tFRegTelefono.setEnabled(true);
				tFRegApellido.setEnabled(true);
				tFRegDistrito.setEnabled(true);
				tFRegDocumento.setEnabled(true);
				cBRegPuesto.setEnabled(true);
				cBRegECivil.setEnabled(true);
				cBRegGenero.setEnabled(true);
				cBRegGSanguineo.setEnabled(true);
				cBRegTipoDoc.setEnabled(true);
				dCRegFIngreso.setEnabled(true);
				try
				{
					tFRegID.setText(CnAdministrador.CodigoAleatorio());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Hoja de Registro Limpiada");
			}
		});
		btnNewButton.setBounds(619, 411, 134, 23);
		pReg.add(btnNewButton);
		
		JPanel pEdit = new JPanel();
		pEdit.setBackground(new Color(240, 248, 255));
		tabbedPanePersonal.addTab("Editar Personal", null, pEdit, null);
		pEdit.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 47, 757, 458);
		pEdit.add(tabbedPane);
		
		JPanel pEditarInfo = new JPanel();
		pEditarInfo.setBackground(new Color(240, 255, 255));
		tabbedPane.addTab("Informacion", null, pEditarInfo, null);
		pEditarInfo.setLayout(null);
		
		JLabel label_1 = new JLabel("Nombres:");
		label_1.setBounds(42, 22, 80, 15);
		pEditarInfo.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_3 = new JLabel("Edad:");
		label_3.setBounds(42, 77, 46, 15);
		pEditarInfo.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_5 = new JLabel("G\u00E9nero:");
		label_5.setBounds(42, 133, 90, 14);
		pEditarInfo.add(label_5);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_7 = new JLabel("Direcci\u00F3n:");
		label_7.setBounds(42, 182, 90, 15);
		pEditarInfo.add(label_7);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_9 = new JLabel("Provincia:");
		label_9.setBounds(42, 237, 80, 15);
		pEditarInfo.add(label_9);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_14 = new JLabel("Distrito:");
		label_14.setBounds(42, 274, 65, 15);
		pEditarInfo.add(label_14);
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_11 = new JLabel("Documento:");
		label_11.setBounds(42, 313, 80, 15);
		pEditarInfo.add(label_11);
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_13 = new JLabel("N\u00B0Documento:");
		label_13.setBounds(42, 348, 103, 15);
		pEditarInfo.add(label_13);
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tFIEditDocumento = new JTextField();
		tFIEditDocumento.setEnabled(false);
		tFIEditDocumento.setBounds(147, 347, 90, 20);
		pEditarInfo.add(tFIEditDocumento);
		tFIEditDocumento.setColumns(10);
		
		JLabel label_12 = new JLabel("Fecha de Ingreso:");
		label_12.setBounds(259, 345, 125, 20);
		pEditarInfo.add(label_12);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		cDieditfingreso=null;
		dCIEditFIngreso = new JDateChooser();
		dCIEditFIngreso.setBounds(394, 348, 95, 20);
		pEditarInfo.add(dCIEditFIngreso);
		dCIEditFIngreso.setEnabled(false);
		
		JLabel label_10 = new JLabel("Estado Civil:");
		label_10.setBounds(259, 313, 97, 15);
		pEditarInfo.add(label_10);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_8 = new JLabel("Telefono:");
		label_8.setBounds(269, 237, 125, 15);
		pEditarInfo.add(label_8);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tFIEditTelefono = new JTextField();
		tFIEditTelefono.setEnabled(false);
		tFIEditTelefono.setBounds(388, 236, 90, 20);
		pEditarInfo.add(tFIEditTelefono);
		tFIEditTelefono.setColumns(10);
		
		cBieditecivil=new DefaultComboBoxModel();
		cBIEditECivil = new JComboBox();
		cBIEditECivil.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "Soltero(a)", "Casado(a)", "Divorsiado(a)", "Viudo(a)"}));
		cBIEditECivil.setEnabled(false);
		cBIEditECivil.setBounds(388, 312, 90, 20);
		pEditarInfo.add(cBIEditECivil);
		
		JLabel label_6 = new JLabel("Grupo Sangu\u00EDneo:");
		label_6.setBounds(259, 133, 125, 15);
		pEditarInfo.add(label_6);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		cBieditgsanguineo=new DefaultComboBoxModel();
		cBIEditGSanguineo = new JComboBox();
		cBIEditGSanguineo.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "A", "B", "AB", "O"}));
		cBIEditGSanguineo.setEnabled(false);
		cBIEditGSanguineo.setBounds(388, 132, 90, 20);
		pEditarInfo.add(cBIEditGSanguineo);
		
		tFIEditEspecializacion = new JTextField();
		tFIEditEspecializacion.setEnabled(false);
		tFIEditEspecializacion.setBounds(394, 76, 90, 20);
		pEditarInfo.add(tFIEditEspecializacion);
		tFIEditEspecializacion.setColumns(10);
		
		JLabel label_4 = new JLabel("Especializaci\u00F3n:");
		label_4.setBounds(259, 77, 97, 15);
		pEditarInfo.add(label_4);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_2 = new JLabel("Apellidos:");
		label_2.setBounds(259, 22, 65, 15);
		pEditarInfo.add(label_2);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tFIEditApellido = new JTextField();
		tFIEditApellido.setEnabled(false);
		tFIEditApellido.setBounds(388, 21, 90, 20);
		pEditarInfo.add(tFIEditApellido);
		tFIEditApellido.setColumns(10);
		
		cBieditdocumento=new DefaultComboBoxModel();
		cBIEditDocumento = new JComboBox();
		cBIEditDocumento.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "D.N.I.", "Carnet de Extranjer\u00EDa"}));
		cBIEditDocumento.setEnabled(false);
		cBIEditDocumento.setBounds(147, 312, 90, 20);
		pEditarInfo.add(cBIEditDocumento);
		
		cBieditgenero=new DefaultComboBoxModel();
		cBIEditGenero = new JComboBox();
		cBIEditGenero.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "Masculino", "Femenino"}));
		cBIEditGenero.setEnabled(false);
		cBIEditGenero.setBounds(147, 132, 90, 20);
		pEditarInfo.add(cBIEditGenero);
		
		tFIEditEdad = new JTextField();
		tFIEditEdad.setEnabled(false);
		tFIEditEdad.setBounds(147, 76, 90, 20);
		pEditarInfo.add(tFIEditEdad);
		tFIEditEdad.setColumns(10);
		
		tFIEditNombre = new JTextField();
		tFIEditNombre.setEnabled(false);
		tFIEditNombre.setBounds(147, 21, 90, 20);
		pEditarInfo.add(tFIEditNombre);
		tFIEditNombre.setColumns(10);
		
		tFIEditProvincia = new JTextField();
		tFIEditProvincia.setEnabled(false);
		tFIEditProvincia.setBounds(147, 236, 90, 20);
		pEditarInfo.add(tFIEditProvincia);
		tFIEditProvincia.setColumns(10);
		
		tFIEditDistrito = new JTextField();
		tFIEditDistrito.setEnabled(false);
		tFIEditDistrito.setBounds(147, 267, 90, 20);
		pEditarInfo.add(tFIEditDistrito);
		tFIEditDistrito.setColumns(10);
		
		tFIEditDireccion = new JTextField();
		tFIEditDireccion.setEnabled(false);
		tFIEditDireccion.setBounds(142, 181, 340, 20);
		pEditarInfo.add(tFIEditDireccion);
		tFIEditDireccion.setColumns(10);
		
		JButton btnIEditEditar = new JButton("Editar");
		
		btnIEditEditar.setBounds(635, 346, 89, 23);
		pEditarInfo.add(btnIEditEditar);
		
		JButton btnIEditGuardar = new JButton("Guardar");
		btnIEditGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date sqlDate=new java.sql.Date(dCIEditFIngreso.getDate().getTime());
				try 
				{
					CnAdministrador.ActualizarBDPersonal(tFEditID.getText(), tFIEditNombre.getText(), tFIEditApellido.getText(), Integer.parseInt(tFIEditEdad.getText()), tFIEditEspecializacion.getText(), (String)cBIEditGenero.getSelectedItem(), (String)cBIEditGSanguineo.getSelectedItem(), tFIEditDireccion.getText(), tFIEditProvincia.getText(), tFIEditDistrito.getText(), tFIEditTelefono.getText(), (String)cBIEditDocumento.getSelectedItem(), Integer.parseInt(tFIEditDocumento.getText()), (String)cBIEditECivil.getSelectedItem(), sqlDate);
					JOptionPane.showMessageDialog(null, "Guardado Correctamente");
					tFIEditNombre.setEnabled(false);
					tFIEditApellido.setEnabled(false);
					tFIEditEdad.setEnabled(false);
					tFIEditEspecializacion.setEnabled(false);
					cBIEditGenero.setEnabled(false);
					cBIEditGSanguineo.setEnabled(false);
					tFIEditDireccion.setEnabled(false);
					tFIEditProvincia.setEnabled(false);
					tFIEditDistrito.setEnabled(false);
					tFIEditTelefono.setEnabled(false);
					cBIEditDocumento.setEnabled(false);
					tFIEditDocumento.setEnabled(false);
					cBIEditECivil.setEnabled(false);
					dCIEditFIngreso.setEnabled(false);
				} 
				catch (NumberFormatException | SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnIEditGuardar.setBounds(635, 396, 89, 23);
		pEditarInfo.add(btnIEditGuardar);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Despido de Personal", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Motivo de Despido:");
		label.setBounds(97, 57, 119, 14);
		panel.add(label);
		
		JLabel label_15 = new JLabel("Fecha de Despido:");
		label_15.setBounds(97, 112, 103, 14);
		panel.add(label_15);
		
		button = new JButton("Despedir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Seugro que quiere despedir al doctor?")==0) {
					java.sql.Date sqlDate=new java.sql.Date(calendar.getDate().getTime());
					try {
						CnAdministrador.DespedirPersonal(tFEditID.getText(), textField.getText(), sqlDate);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					textField.setEnabled(false);
					calendar.setEnabled(false);
					button.setEnabled(false);
					JOptionPane.showMessageDialog(null, "El doctor Ha Sido Despedido");
				}
			}
		});
		button.setBounds(280, 303, 89, 23);
		panel.add(button);
		
		calendar = new JCalendar();
		calendar.setBounds(210, 103, 184, 153);
		panel.add(calendar);
		
		textField = new JTextField();
		textField.setBounds(213, 54, 119, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		dCdeditdespedido=null;

		btnIEditEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tFIEditNombre.setEnabled(true);
				tFIEditApellido.setEnabled(true);
				tFIEditEdad.setEnabled(true);
				tFIEditEspecializacion.setEnabled(true);
				cBIEditGenero.setEnabled(true);
				cBIEditGSanguineo.setEnabled(true);
				tFIEditDireccion.setEnabled(true);
				tFIEditProvincia.setEnabled(true);
				tFIEditDistrito.setEnabled(true);
				tFIEditTelefono.setEnabled(true);
				cBIEditDocumento.setEnabled(true);
				tFIEditDocumento.setEnabled(true);
				cBIEditECivil.setEnabled(true);
				dCIEditFIngreso.setEnabled(true);
			}
		});
		
		JLabel lblIdPersonal = new JLabel("ID Personal");
		lblIdPersonal.setBounds(10, 21, 71, 14);
		pEdit.add(lblIdPersonal);
		
		tFEditID = new JTextField();
		tFEditID.setBounds(88, 18, 86, 20);
		pEdit.add(tFEditID);
		tFEditID.setColumns(10);
		
		JButton btnEditBuscar = new JButton("Buscar");
		btnEditBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					CnAdministrador.RellenarBuscarPaciente(tFEditID.getText());
					tFEditID.setEnabled(false);
					tFIEditNombre.setEnabled(false);
					tFIEditApellido.setEnabled(false);
					tFIEditEdad.setEnabled(false);
					tFIEditEspecializacion.setEnabled(false);
					cBIEditGenero.setEnabled(false);
					cBIEditGSanguineo.setEnabled(false);
					tFIEditDireccion.setEnabled(false);
					tFIEditProvincia.setEnabled(false);
					tFIEditDistrito.setEnabled(false);
					tFIEditTelefono.setEnabled(false);
					cBIEditDocumento.setEnabled(false);
					tFIEditDocumento.setEnabled(false);
					cBIEditECivil.setEnabled(false);
					dCIEditFIngreso.setEnabled(false);
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnEditBuscar.setBounds(201, 17, 89, 23);
		pEdit.add(btnEditBuscar);
		
		JButton btnNuevaBusqueda = new JButton("Nueva B\u00FAsqueda");
		btnNuevaBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tFEditID.setEnabled(true);
				tFEditID.setText("");
				tFIEditNombre.setEnabled(true);
				tFIEditNombre.setText("");
				tFIEditApellido.setEnabled(true);
				tFIEditApellido.setText("");
				tFIEditEdad.setEnabled(true);
				tFIEditEdad.setText("");
				tFIEditEspecializacion.setEnabled(true);
				tFIEditEspecializacion.setText("");
				cBIEditGenero.setEnabled(true);
				cBIEditGenero.setSelectedIndex(0);
				cBIEditGSanguineo.setEnabled(true);
				cBIEditGSanguineo.setSelectedIndex(0);
				tFIEditDireccion.setEnabled(true);
				tFIEditDireccion.setText("");
				tFIEditProvincia.setEnabled(true);
				tFIEditProvincia.setText("");
				tFIEditDistrito.setEnabled(true);
				tFIEditDistrito.setText("");
				tFIEditTelefono.setEnabled(true);
				tFIEditTelefono.setText("");
				cBIEditDocumento.setEnabled(true);
				cBIEditDocumento.setSelectedIndex(0);
				tFIEditDocumento.setEnabled(true);
				tFIEditDocumento.setText("");
				cBIEditECivil.setEnabled(true);
				cBIEditECivil.setSelectedIndex(0);
				dCIEditFIngreso.setEnabled(true);
				dCIEditFIngreso.setCalendar(null);
				JOptionPane.showMessageDialog(null, "Hoja de Busqueda Limpiada");
			}
		});
		btnNuevaBusqueda.setBounds(332, 17, 113, 23);
		pEdit.add(btnNuevaBusqueda);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrador administrador= new Administrador();
				administrador.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(703, 17, 89, 23);
		contentPanePersonal.add(btnRegresar);
		
		btnRegRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.Date sqlDate=new java.sql.Date(dCRegFIngreso.getDate().getTime());
				try 
				{
					CnAdministrador.EnviarBDPersonal(tFRegID.getText(),(String)cBRegPuesto.getSelectedItem(),tFRegNombre.getText(),tFRegApellido.getText(),Integer.parseInt(tFRegEdad.getText()),tFRegEspecializacion.getText(),(String)cBRegGenero.getSelectedItem(),(String)cBRegGSanguineo.getSelectedItem(),tFRegDireccion.getText(),tFRegProvincia.getText(),tFRegDistrito.getText(),tFRegTelefono.getText(),(String)cBRegTipoDoc.getSelectedItem(),Integer.parseInt(tFRegDocumento.getText()),(String)cBRegECivil.getSelectedItem(),sqlDate,"Activo");
					JOptionPane.showMessageDialog(null, "Guardado Correctamente");
					CnAdministrador.LlenarTablaPersonal(modelLista);
					tFRegNombre.setEnabled(false);
					tFRegEdad.setEnabled(false);
					tFRegApellido.setEnabled(false);
					tFRegEspecializacion.setEnabled(false);
					tFRegDireccion.setEnabled(false);
					tFRegProvincia.setEnabled(false);
					tFRegTelefono.setEnabled(false);
					tFRegApellido.setEnabled(false);
					tFRegDistrito.setEnabled(false);
					tFRegDocumento.setEnabled(false);
					cBRegPuesto.setEnabled(false);
					cBRegECivil.setEnabled(false);
					cBRegGenero.setEnabled(false);
					cBRegGSanguineo.setEnabled(false);
					cBRegTipoDoc.setEnabled(false);
					dCRegFIngreso.setEnabled(false);
					
				} 
				catch (NumberFormatException e) 
				{
					e.printStackTrace();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		switch(a)
		{
			case 1:tabbedPanePersonal.setSelectedComponent(pList);break;
			case 2:tabbedPanePersonal.setSelectedComponent(pReg);break;
			case 3:tabbedPanePersonal.setSelectedComponent(pEdit);break;
		}
	}
}