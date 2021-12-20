package suing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import conexión.CnRecepcionista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Color;


@SuppressWarnings("serial")
public class RecepPaciente extends JFrame {

	private JPanel Paciente;
	private JTextField tFRegPaciente;
	private JTextField tFRegApellido;
	private JTextField tFRegNombre;
	private JTextField tFRegDocumento;
	private JTextField tFRegAlergia;
	private JTextField tFRegObs;
	private JTextField tFRegGSanguineo;
	private JTextField tFBuscarPaciente;
	public static JTextField tFBuscarNombre;
	public static JTextField tFBuscarDocumento;
	public static JTextField tFBuscarAlergia;
	public static JTextField tFBuscarObs;
	public static JTextField tFBuscarGSanguineo;
	public static JTextField tFBuscarApellido;
	public static JSpinner sBuscarEdad;
	public static JCalendar cBuscarCalendario;
	public static String TipoDoc,Genero,EIngreso,Fecha,Motivo;
	private JTextField tFListaPaciente;
	Date date=new Date();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RecepPaciente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setTitle("Cl\u00EDnica UNAC - Paciente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 650);
		Paciente = new JPanel();
		Paciente.setBackground(new Color(100, 149, 237));
		Paciente.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Paciente);
		Paciente.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 59, 674, 541);
		Paciente.add(tabbedPane);
		
		JPanel LPaciente = new JPanel();
		LPaciente.setBackground(new Color(240, 255, 255));
		tabbedPane.addTab("Lista de Paciente", null, LPaciente, null);
		LPaciente.setLayout(null);
		
		String columnas[]= {"ID", "Nombre","Apellido","Edad","Genero","Documento","Tipo Sanguineo","Motivo","Estado"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		try 
		{
			CnRecepcionista.LlenarTablaPaciente(modelLista);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		JScrollPane scroller = new JScrollPane();
		scroller.setBounds(15, 56, 642, 446);
		scroller.setPreferredSize(new Dimension(400, 150));
		scroller.setViewportView(list);
		LPaciente.add(scroller);
		
		JLabel label_12 = new JLabel("Buscar Paciente");
		label_12.setBounds(25, 15, 160, 20);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 15));
		LPaciente.add(label_12);
		
		tFListaPaciente = new JTextField();
		tFListaPaciente.setBounds(174, 15, 147, 20);
		tFListaPaciente.setColumns(10);
		LPaciente.add(tFListaPaciente);
		
		JLabel btnListaBuscar = new JLabel("Buscar");
		btnListaBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					CnRecepcionista.buscarTablaPaciente(modelLista, tFListaPaciente.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnListaBuscar.setBounds(521, 18, 46, 15);
		btnListaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LPaciente.add(btnListaBuscar);
		
		JLabel label_14 = new JLabel("Mostrar Todo");
		label_14.setBounds(670, 20, 108, 14);
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LPaciente.add(label_14);
		
		JLabel btnListaEliminar = new JLabel("Todo");
		btnListaEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try 
				{
					CnRecepcionista.LlenarTablaPaciente(modelLista);
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnListaEliminar.setBounds(611, 18, 46, 15);
		btnListaEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LPaciente.add(btnListaEliminar);
		
		JPanel RPacientes = new JPanel();
		RPacientes.setBackground(new Color(240, 255, 255));
		tabbedPane.addTab("Registrar Paciente", null, RPacientes, null);
		RPacientes.setLayout(null);
		
		JLabel lblID = new JLabel("ID de Paciente");
		lblID.setBounds(36, 20, 83, 14);
		RPacientes.add(lblID);
		
		tFRegPaciente = new JTextField();
		tFRegPaciente.setEditable(false);
		tFRegPaciente.setBounds(117, 20, 86, 20);
		RPacientes.add(tFRegPaciente);
		tFRegPaciente.setColumns(10);
		
		try 
		{
			tFRegPaciente.setText(CnRecepcionista.CodigoAleatorio());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		JLabel lblNombre = new JLabel("Nombres:");
		lblNombre.setBounds(36, 70, 71, 14);
		RPacientes.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(330, 70, 70, 14);
		RPacientes.add(lblApellidos);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(330, 120, 46, 14);
		RPacientes.add(lblSexo);
		
		JComboBox cBRegSexo = new JComboBox();
		cBRegSexo.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "Masculino", "Femenino"}));
		cBRegSexo.setBounds(410, 117, 86, 20);
		RPacientes.add(cBRegSexo);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(36, 120, 71, 14);
		RPacientes.add(lblDocumento);
		
		JComboBox cBRegDocumento = new JComboBox();
		cBRegDocumento.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "DNI", "Carné de Extranjeria"}));
		cBRegDocumento.setBounds(117, 117, 86, 20);
		RPacientes.add(cBRegDocumento);
		
		tFRegApellido = new JTextField();
		tFRegApellido.setBounds(410, 67, 190, 20);
		RPacientes.add(tFRegApellido);
		tFRegApellido.setColumns(10);
		
		tFRegNombre = new JTextField();
		tFRegNombre.setBounds(117, 67, 190, 20);
		RPacientes.add(tFRegNombre);
		tFRegNombre.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado de Ingreso:");
		lblEstado.setBounds(36, 170, 115, 14);
		RPacientes.add(lblEstado);
		
		tFRegDocumento = new JTextField();
		tFRegDocumento.setBounds(221, 117, 86, 20);
		RPacientes.add(tFRegDocumento);
		tFRegDocumento.setColumns(10);
		
		JLabel lblFechaDeNacimineto = new JLabel("Fecha de Nacimineto:");
		lblFechaDeNacimineto.setBounds(36, 220, 134, 14);
		RPacientes.add(lblFechaDeNacimineto);
		
		JCalendar cRegCalendario = new JCalendar();
		cRegCalendario.setBounds(180, 221, 261, 153);
		RPacientes.add(cRegCalendario);
		
		JComboBox cBRegEIngreso = new JComboBox();
		cBRegEIngreso.setModel(new DefaultComboBoxModel(new String[] {"-Seleccione-", "Registrado", "Hospitalizado"}));
		cBRegEIngreso.setBounds(160, 167, 83, 20);
		RPacientes.add(cBRegEIngreso);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(270, 170, 46, 14);
		RPacientes.add(lblEdad);
		
		JSpinner sRegEdad = new JSpinner();
		sRegEdad.setModel(new SpinnerNumberModel(0, 0, 200, 1));
		sRegEdad.setBounds(308, 167, 46, 20);
		RPacientes.add(sRegEdad);
		
		JLabel lblAlergias = new JLabel("Alergias:");
		lblAlergias.setBounds(36, 425, 69, 14);
		RPacientes.add(lblAlergias);
		
		JComboBox cBRegAlergia = new JComboBox();
		cBRegAlergia.setModel(new DefaultComboBoxModel(new String[] {"-Vacio-", "Si", "No"}));
		cBRegAlergia.setBounds(146, 422, 65, 20);
		RPacientes.add(cBRegAlergia);
		cBRegAlergia.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if(cBRegAlergia.getSelectedIndex()==1) {
		        	tFRegAlergia.setEnabled(true);
		        } else {
		        	tFRegAlergia.setEnabled(false);
		        	tFRegAlergia.setText("");
		        }
		    }
		});
		
		tFRegAlergia = new JTextField();
		tFRegAlergia.setEnabled(false);
		tFRegAlergia.setBounds(221, 422, 190, 20);
		RPacientes.add(tFRegAlergia);
		tFRegAlergia.setColumns(10);
		
		JLabel lblObservacion = new JLabel("Observaciones:");
		lblObservacion.setBounds(36, 472, 100, 14);
		RPacientes.add(lblObservacion);
		
		tFRegObs = new JTextField();
		tFRegObs.setBounds(146, 472, 261, 30);
		RPacientes.add(tFRegObs);
		tFRegObs.setColumns(10);
		
		JComboBox cBRegMotivo = new JComboBox();
		cBRegMotivo.setModel(new DefaultComboBoxModel(new String[] {"-Vacio-", "Cita", "Emergencia"}));
		cBRegMotivo.setBounds(146, 384, 83, 20);
		RPacientes.add(cBRegMotivo);
		
		JButton btnRegRegistrar = new JButton("Registrar");
		btnRegRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				java.sql.Date sqlDate=new java.sql.Date(cRegCalendario.getDate().getTime());
				try
				{
					CnRecepcionista.EnviarBDPacienteDAsistencial(tFRegPaciente.getText(),tFRegNombre.getText(),tFRegApellido.getText(),(String)cBRegDocumento.getSelectedItem(),Integer.parseInt(tFRegDocumento.getText()),(String)cBRegSexo.getSelectedItem(),(String)cBRegEIngreso.getSelectedItem(),(Integer)sRegEdad.getValue(),tFRegGSanguineo.getText(),sqlDate,(String)cBRegMotivo.getSelectedItem(),tFRegAlergia.getText(),"no",tFRegObs.getText());
					if(((String)cBRegEIngreso.getSelectedItem()).equals("Hospitalizado"))
					{
						RecepPacHospitalizacion recepPacHostpitalizacion=new RecepPacHospitalizacion(tFRegPaciente.getText(),0);
						recepPacHostpitalizacion.setVisible(true);
					}
					CnRecepcionista.LlenarTablaPaciente(modelLista);
					JOptionPane.showMessageDialog(null, "El Registro se Completo Satisfactoriamente");
					tFRegNombre.setEnabled(false);
					tFRegApellido.setEnabled(false);
					tFRegDocumento.setEnabled(false);
					tFRegGSanguineo.setEnabled(false);
					tFRegAlergia.setEnabled(false);
					tFRegObs.setEnabled(false);
					cBRegDocumento.setEnabled(false);
					cBRegSexo.setEnabled(false);
					cBRegEIngreso.setEnabled(false);
					cBRegMotivo.setEnabled(false);
					cBRegAlergia.setEnabled(false);
					sRegEdad.setEnabled(false);
					cRegCalendario.setEnabled(false);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
			}
		});
		btnRegRegistrar.setBounds(503, 468, 89, 23);
		RPacientes.add(btnRegRegistrar);
		
		JLabel lblTipoSanguineo = new JLabel("Tipo Sanguineo:");
		lblTipoSanguineo.setBounds(380, 170, 100, 14);
		RPacientes.add(lblTipoSanguineo);
		
		tFRegGSanguineo = new JTextField();
		tFRegGSanguineo.setText("");
		tFRegGSanguineo.setBounds(490, 167, 86, 20);
		RPacientes.add(tFRegGSanguineo);
		tFRegGSanguineo.setColumns(10);
		
		JLabel lblMotivo = new JLabel("Motivo:");
		lblMotivo.setBounds(36, 387, 46, 14);
		RPacientes.add(lblMotivo);
		
		JButton btnNuevoRegistro = new JButton("Nuevo Registro");
		btnNuevoRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tFRegNombre.setEnabled(true);
				tFRegApellido.setEnabled(true);
				tFRegDocumento.setEnabled(true);
				tFRegGSanguineo.setEnabled(true);
				tFRegAlergia.setEnabled(true);
				tFRegObs.setEnabled(true);
				cBRegDocumento.setEnabled(true);
				cBRegSexo.setEnabled(true);
				cBRegEIngreso.setEnabled(true);
				cBRegMotivo.setEnabled(true);
				cBRegAlergia.setEnabled(true);
				sRegEdad.setEnabled(true);
				cRegCalendario.setEnabled(true);
				tFRegNombre.setText("");
				tFRegApellido.setText("");
				tFRegDocumento.setText("");
				tFRegGSanguineo.setText("");
				tFRegAlergia.setText("");
				tFRegObs.setText("");
				cBRegDocumento.setSelectedIndex(0);
				cBRegSexo.setSelectedIndex(0);
				cBRegEIngreso.setSelectedIndex(0);
				cBRegMotivo.setSelectedIndex(0);
				cBRegAlergia.setSelectedIndex(0);
				sRegEdad.setValue(Integer.valueOf(0));
				Date date=new Date();
				cRegCalendario.setDate(date);
				try 
				{
					tFRegPaciente.setText(CnRecepcionista.CodigoAleatorio());
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Hoja de Registro Limpiada");
			}
		});
		btnNuevoRegistro.setBounds(490, 421, 110, 23);
		RPacientes.add(btnNuevoRegistro);
		
		JPanel BPaciente = new JPanel();
		BPaciente.setBackground(new Color(240, 255, 255));
		tabbedPane.addTab("Buscar Paciente", null, BPaciente, null);
		BPaciente.setLayout(null);
		
		JLabel label = new JLabel("ID de Paciente");
		label.setBounds(36, 20, 83, 14);
		BPaciente.add(label);
		
		tFBuscarPaciente = new JTextField();
		tFBuscarPaciente.setColumns(10);
		tFBuscarPaciente.setBounds(117, 20, 86, 20);
		BPaciente.add(tFBuscarPaciente);
		
		JLabel label_1 = new JLabel("Nombres:");
		label_1.setBounds(36, 70, 71, 14);
		BPaciente.add(label_1);
		
		JLabel label_2 = new JLabel("Apellidos:");
		label_2.setBounds(330, 70, 70, 14);
		BPaciente.add(label_2);
		
		JLabel label_3 = new JLabel("Sexo:");
		label_3.setBounds(330, 120, 46, 14);
		BPaciente.add(label_3);
		
		JComboBox cBBuscarSexo = new JComboBox();
		cBBuscarSexo.setEnabled(false);
		cBBuscarSexo.setModel(new DefaultComboBoxModel(new String[] {"", "Masculino", "Femenino"}));
		cBBuscarSexo.setBounds(410, 117, 86, 20);
		BPaciente.add(cBBuscarSexo);
		
		JLabel label_4 = new JLabel("Documento:");
		label_4.setBounds(36, 120, 71, 14);
		BPaciente.add(label_4);
		
		JComboBox cBBuscarDocumento = new JComboBox();
		cBBuscarDocumento.setEnabled(false);
		cBBuscarDocumento.setModel(new DefaultComboBoxModel(new String[] {"", "DNI", "Carn\u00E9 de Extranjeria"}));
		cBBuscarDocumento.setBounds(117, 117, 86, 20);
		BPaciente.add(cBBuscarDocumento);
		
		tFBuscarApellido = new JTextField();
		tFBuscarApellido.setEnabled(false);
		tFBuscarApellido.setColumns(10);
		tFBuscarApellido.setBounds(410, 67, 190, 20);
		BPaciente.add(tFBuscarApellido);
		
		tFBuscarNombre = new JTextField();
		tFBuscarNombre.setEnabled(false);
		tFBuscarNombre.setColumns(10);
		tFBuscarNombre.setBounds(117, 67, 190, 20);
		BPaciente.add(tFBuscarNombre);
		
		JLabel label_5 = new JLabel("Estado de Ingreso:");
		label_5.setBounds(36, 170, 115, 14);
		BPaciente.add(label_5);
		
		tFBuscarDocumento = new JTextField();
		tFBuscarDocumento.setEnabled(false);
		tFBuscarDocumento.setColumns(10);
		tFBuscarDocumento.setBounds(221, 117, 86, 20);
		BPaciente.add(tFBuscarDocumento);
		
		JLabel label_6 = new JLabel("Fecha de Nacimineto:");
		label_6.setBounds(36, 220, 134, 14);
		BPaciente.add(label_6);
		
		cBuscarCalendario = new JCalendar();
		cBuscarCalendario.setBounds(180, 221, 261, 153);
		BPaciente.add(cBuscarCalendario);
		cBuscarCalendario.setEnabled(false);
		
		JComboBox cBBuscarEIngreso = new JComboBox();
		cBBuscarEIngreso.setEnabled(false);
		cBBuscarEIngreso.setModel(new DefaultComboBoxModel(new String[] {"", "Registrado", "Hospitalizado"}));
		cBBuscarEIngreso.setBounds(160, 167, 83, 20);
		BPaciente.add(cBBuscarEIngreso);
		
		JLabel label_7 = new JLabel("Edad:");
		label_7.setBounds(270, 170, 46, 14);
		BPaciente.add(label_7);
		
		sBuscarEdad = new JSpinner();
		sBuscarEdad.setEnabled(false);
		sBuscarEdad.setBounds(320, 167, 34, 20);
		BPaciente.add(sBuscarEdad);
		
		JLabel label_8 = new JLabel("Motivo:");
		label_8.setBounds(36, 385, 46, 14);
		BPaciente.add(label_8);
		
		JComboBox cBBuscarMotivo = new JComboBox();
		cBBuscarMotivo.setEnabled(false);
		cBBuscarMotivo.setModel(new DefaultComboBoxModel(new String[] {"", "Cita", "Emergencia"}));
		cBBuscarMotivo.setBounds(146, 385, 83, 20);
		BPaciente.add(cBBuscarMotivo);
		
		JLabel label_9 = new JLabel("Alergias:");
		label_9.setBounds(36, 425, 69, 14);
		BPaciente.add(label_9);
		
		JComboBox cBBuscarAlergias = new JComboBox();
		cBBuscarAlergias.setEnabled(false);
		cBBuscarAlergias.setModel(new DefaultComboBoxModel(new String[] {"", "Si", "No"}));
		cBBuscarAlergias.setBounds(146, 422, 55, 20);
		BPaciente.add(cBBuscarAlergias);
		
		tFBuscarAlergia = new JTextField();
		tFBuscarAlergia.setEnabled(false);
		tFBuscarAlergia.setColumns(10);
		tFBuscarAlergia.setBounds(221, 422, 183, 20);
		BPaciente.add(tFBuscarAlergia);
		
		JLabel label_10 = new JLabel("Observaciones:");
		label_10.setBounds(36, 472, 100, 14);
		BPaciente.add(label_10);
		
		tFBuscarObs = new JTextField();
		tFBuscarObs.setEnabled(false);
		tFBuscarObs.setColumns(10);
		tFBuscarObs.setBounds(146, 472, 261, 30);
		BPaciente.add(tFBuscarObs);
		
		JLabel label_11 = new JLabel("Tipo Sanguineo:");
		label_11.setBounds(380, 170, 100, 14);
		BPaciente.add(label_11);
		
		tFBuscarGSanguineo = new JTextField();
		tFBuscarGSanguineo.setEnabled(false);
		tFBuscarGSanguineo.setText("");
		tFBuscarGSanguineo.setColumns(10);
		tFBuscarGSanguineo.setBounds(490, 167, 110, 20);
		BPaciente.add(tFBuscarGSanguineo);
		
		JButton btnBuscarBuscar = new JButton("Buscar");
		btnBuscarBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					CnRecepcionista.RellenarBuscarPaciente(tFBuscarPaciente.getText());
					tFBuscarPaciente.setEnabled(false);
					cBBuscarDocumento.setSelectedItem(TipoDoc);
					cBBuscarSexo.setSelectedItem(Genero);
					cBBuscarEIngreso.setSelectedItem(EIngreso);
					cBBuscarMotivo.setSelectedItem(Motivo);
					if(!tFBuscarAlergia.getText().equals(""))
					{
						cBBuscarAlergias.setSelectedItem("Si");
					}
					else
					{
						cBBuscarAlergias.setSelectedItem("No");
					}
					tFBuscarNombre.setEnabled(false);
					tFBuscarApellido.setEnabled(false);
					cBBuscarDocumento.setEnabled(false);
					tFBuscarDocumento.setEnabled(false);
					sBuscarEdad.setEnabled(false);
					cBBuscarSexo.setEnabled(false);
					cBBuscarEIngreso.setEnabled(false);
					tFBuscarGSanguineo.setEnabled(false);
					cBuscarCalendario.setEnabled(false);
					cBBuscarMotivo.setEnabled(false);
					cBBuscarAlergias.setEnabled(false);
					tFBuscarAlergia.setEnabled(false);
					tFBuscarObs.setEnabled(false);
						
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}				
			}
		});
		btnBuscarBuscar.setBounds(213, 16, 89, 23);
		BPaciente.add(btnBuscarBuscar);
		
		JButton btnBuscarEditar = new JButton("Editar");
		btnBuscarEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tFBuscarNombre.setEnabled(true);
				tFBuscarApellido.setEnabled(true);
				cBBuscarDocumento.setEnabled(true);
				tFBuscarDocumento.setEnabled(true);
				sBuscarEdad.setEnabled(true);
				cBBuscarSexo.setEnabled(true);
				cBBuscarEIngreso.setEnabled(true);
				tFBuscarGSanguineo.setEnabled(true);
				cBuscarCalendario.setEnabled(true);
				cBBuscarMotivo.setEnabled(true);
				cBBuscarAlergias.setEnabled(true);
				tFBuscarAlergia.setEnabled(true);
				tFBuscarObs.setEnabled(true);
				
			}
		});
		btnBuscarEditar.setBounds(511, 381, 89, 23);
		BPaciente.add(btnBuscarEditar);
		
		JButton btnBuscarGuardar = new JButton("Guardar");
		btnBuscarGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date sqlDate=new java.sql.Date(cBuscarCalendario.getDate().getTime());
				try 
				{
					tFBuscarPaciente.setEnabled(false);
					tFBuscarNombre.setEnabled(false);
					tFBuscarApellido.setEnabled(false);
					cBBuscarDocumento.setEnabled(false);
					tFBuscarDocumento.setEnabled(false);
					sBuscarEdad.setEnabled(false);
					cBBuscarSexo.setEnabled(false);
					cBBuscarEIngreso.setEnabled(false);
					tFBuscarGSanguineo.setEnabled(false);
					cBuscarCalendario.setEnabled(false);
					cBBuscarMotivo.setEnabled(false);
					cBBuscarAlergias.setEnabled(false);
					tFBuscarAlergia.setEnabled(false);
					tFBuscarObs.setEnabled(false);
					CnRecepcionista.ActualizarBDPacienteGDatos(tFBuscarPaciente.getText(),tFBuscarNombre.getText(),tFBuscarApellido.getText(),(String)cBBuscarDocumento.getSelectedItem(),Integer.parseInt(tFBuscarDocumento.getText()),(String)cBBuscarSexo.getSelectedItem(),(String)cBBuscarEIngreso.getSelectedItem(),(Integer)sBuscarEdad.getValue(),tFBuscarGSanguineo.getText(),sqlDate,(String)cBBuscarMotivo.getSelectedItem(),tFBuscarAlergia.getText(),tFBuscarObs.getText());
					if(cBBuscarEIngreso.getSelectedIndex()==2)
					{
						RecepPacHospitalizacion recepPacHostpitalizacion=new RecepPacHospitalizacion(tFBuscarPaciente.getText(),1);
						recepPacHostpitalizacion.setVisible(true);
					}
					JOptionPane.showMessageDialog(null, "Registro Actualizado Correctamente");
					try 
					{
						CnRecepcionista.LlenarTablaPaciente(modelLista);
					} 
					catch (SQLException e3) 
					{
						e3.printStackTrace();
					}
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnBuscarGuardar.setBounds(511, 421, 89, 23);
		BPaciente.add(btnBuscarGuardar);
		
		JButton btnBuscarEliminar = new JButton("Eliminar");
		btnBuscarEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tFBuscarPaciente.isEnabled())
				{
					if(JOptionPane.showConfirmDialog(null, "Eliminar paciente " + tFBuscarNombre.getText())==0)
					{
						try 
						{
							CnRecepcionista.EliminarDBDPacienteGDatos(tFBuscarPaciente.getText());
							CnRecepcionista.LlenarTablaPaciente(modelLista);
							JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
							tFBuscarPaciente.setEnabled(true);
							tFBuscarNombre.setEnabled(false);
							tFBuscarNombre.setText("");
							tFBuscarApellido.setEnabled(false);
							tFBuscarApellido.setText("");
							cBBuscarDocumento.setEnabled(false);
							cBBuscarDocumento.setSelectedIndex(0);
							tFBuscarDocumento.setEnabled(false);
							tFBuscarDocumento.setText("");
							sBuscarEdad.setEnabled(false);
							sBuscarEdad.setValue(0);
							cBBuscarSexo.setEnabled(false);
							cBBuscarSexo.setSelectedIndex(0);
							cBBuscarEIngreso.setEnabled(false);
							cBBuscarEIngreso.setSelectedIndex(0);
							tFBuscarGSanguineo.setEnabled(false);
							tFBuscarGSanguineo.setText("");
							cBuscarCalendario.setEnabled(false);
							cBuscarCalendario.setCalendar(null);
							cBBuscarMotivo.setEnabled(false);
							cBBuscarMotivo.setSelectedIndex(0);
							cBBuscarAlergias.setEnabled(false);
							cBBuscarAlergias.setSelectedIndex(0);
							tFBuscarAlergia.setEnabled(false);
							tFBuscarAlergia.setText("");
							tFBuscarObs.setEnabled(false);
							tFBuscarObs.setText("");
							try 
							{
								CnRecepcionista.LlenarTablaPaciente(modelLista);
							} 
							catch (SQLException e5) 
							{
								e5.printStackTrace();
							}
						} 
						catch (SQLException e1) 
						{
						
							e1.printStackTrace();
						}
						
							
					}
				}
				
			}
		});
		btnBuscarEliminar.setBounds(511, 463, 89, 23);
		BPaciente.add(btnBuscarEliminar);
		
		JButton btnNuevaBusqueda = new JButton("Nueva B\u00FAsqueda");
		btnNuevaBusqueda.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevaBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tFBuscarPaciente.setEnabled(true);
				tFBuscarPaciente.setText("");
				tFBuscarNombre.setEnabled(false);
				tFBuscarNombre.setText("");
				tFBuscarApellido.setEnabled(false);
				tFBuscarApellido.setText("");
				cBBuscarDocumento.setEnabled(false);
				cBBuscarDocumento.setSelectedIndex(0);
				tFBuscarDocumento.setEnabled(false);
				tFBuscarDocumento.setText("");
				sBuscarEdad.setEnabled(false);
				sBuscarEdad.setValue(0);
				cBBuscarSexo.setEnabled(false);
				cBBuscarSexo.setSelectedIndex(0);
				cBBuscarEIngreso.setEnabled(false);
				cBBuscarEIngreso.setSelectedIndex(0);
				tFBuscarGSanguineo.setEnabled(false);
				tFBuscarGSanguineo.setText("");
				cBuscarCalendario.setEnabled(false);
				cBuscarCalendario.setDate(date);
				cBBuscarMotivo.setEnabled(false);
				cBBuscarMotivo.setSelectedIndex(0);
				cBBuscarAlergias.setEnabled(false);
				cBBuscarAlergias.setSelectedIndex(0);
				tFBuscarAlergia.setEnabled(false);
				tFBuscarAlergia.setText("");
				tFBuscarObs.setEnabled(false);
				tFBuscarObs.setText("");
			}
		});
		btnNuevaBusqueda.setBounds(330, 16, 150, 23);
		BPaciente.add(btnNuevaBusqueda);
		
		JLabel lblPaciente = new JLabel("Paciente");
		lblPaciente.setBounds(10, 14, 161, 34);
		Paciente.add(lblPaciente);
		lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(585, 27, 89, 23);
		Paciente.add(btnRegresar);
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Recepcionista recepcionista= new Recepcionista();
				recepcionista.setVisible(true);
				dispose();
			}
		});
	}
}
