package suing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

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

import conexión.CnRecepcionista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Color;

@SuppressWarnings("serial")
public class RecepCitas extends JFrame {

	private JPanel contentPane;
	private JTextField tFNuevoCita;
	private JTextField tFNuevoMotivo;
	private JTextField tFBuscarCita;
	public static JTextField tFBuscarPaciente;
	public static JTextField tFBuscarMotivo;
	public static JTextField tFBuscarHabitacion;
	public static JTextField tFBuscarPiso;
	public static String Especialidad,Doctor,Fecha;
	private JTextField tFNuevoHabitacion;
	private JTextField tFNuevoPiso;
	private JTextField tFListaCita;
	@SuppressWarnings("rawtypes")
	public static JComboBox cBBuscarPaciente;
	@SuppressWarnings("rawtypes")
	public static DefaultComboBoxModel cBuscarPaciente;
	public static JCalendar cBuscarCalendario;

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public RecepCitas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setTitle("Cl\u00EDnica UNAC - Citas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(44, 58, 480, 390);
		panel.add(tabbedPane);
		String columnas[]= {"NºCita","Nombre Paciente","Nombre Doctor","Fecha","Especialidad","Tipo"};
		JTable lista = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		lista.setModel(modelLista);
		
		JPanel Lista = new JPanel();
		Lista.setBackground(new Color(240, 255, 255));
		Lista.setLayout(null);
		tabbedPane.addTab("Lista", null, Lista, null);
		
		String columna[]= {"NºCita","Nombre Paciente","Nombre Doctor","Fecha","Especialidad","Motivo"};
		JTable list = new JTable();
		DefaultTableModel modelList = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		
		try {
			CnRecepcionista.LlenarTablaCita(modelLista);
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setBounds(10, 46, 455, 305);
		scrollPane.setViewportView(list);
		Lista.add(scrollPane);
		
		JLabel label_12 = new JLabel("Buscar Cita");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_12.setBounds(25, 15, 160, 20);
		Lista.add(label_12);
		
		tFListaCita = new JTextField();
		tFListaCita.setColumns(10);
		tFListaCita.setBounds(164, 15, 90, 20);
		Lista.add(tFListaCita);
		
		JLabel btnListaBuscar = new JLabel("Buscar");
		btnListaBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnRecepcionista.buscarTablaCita(modelLista, tFListaCita.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaBuscar.setBounds(319, 20, 46, 15);
		Lista.add(btnListaBuscar);
		
		JLabel label_14 = new JLabel("Mostrar Todo");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_14.setBounds(477, 20, 108, 14);
		Lista.add(label_14);
		
		JLabel lblTodo = new JLabel("Todo");
		lblTodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnRecepcionista.LlenarTablaCita(modelLista);
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
		});
		lblTodo.setBounds(399, 21, 46, 14);
		Lista.add(lblTodo);
		
		JPanel Registrar = new JPanel();
		Registrar.setBackground(new Color(240, 255, 255));
		tabbedPane.addTab("Nuevo", null, Registrar, null);
		Registrar.setLayout(null);
		
		JLabel lblNDeCita = new JLabel("N\u00BA de Cita:");
		lblNDeCita.setBounds(10, 11, 65, 14);
		Registrar.add(lblNDeCita);
		
		tFNuevoCita = new JTextField();
		tFNuevoCita.setEnabled(false);
		tFNuevoCita.setBounds(85, 8, 86, 20);
		Registrar.add(tFNuevoCita);
		tFNuevoCita.setColumns(10);
		
		try {
			tFNuevoCita.setText(CnRecepcionista.CodigoAleatorioCita());
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		JLabel lblNobreDelPaciente = new JLabel("Nobre del Paciente:");
		lblNobreDelPaciente.setBounds(10, 36, 123, 14);
		Registrar.add(lblNobreDelPaciente);
		
		JLabel lblMotivoDeVisita = new JLabel("Motivo de Cita:");
		lblMotivoDeVisita.setBounds(10, 61, 123, 14);
		Registrar.add(lblMotivoDeVisita);
		
		tFNuevoMotivo = new JTextField();
		tFNuevoMotivo.setBounds(133, 58, 190, 20);
		Registrar.add(tFNuevoMotivo);
		tFNuevoMotivo.setColumns(10);
		
		JLabel lblNombreDelDoctor = new JLabel("Nombre del Doctor:");
		lblNombreDelDoctor.setBounds(10, 111, 123, 14);
		Registrar.add(lblNombreDelDoctor);
				
		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setBounds(10, 86, 123, 14);
		Registrar.add(lblEspecialidad);
		

		JComboBox cBNuevoDoctor = new JComboBox();
		DefaultComboBoxModel cNuevoDoctor=new DefaultComboBoxModel();
		cBNuevoDoctor.setBounds(133, 108, 190, 20);
		Registrar.add(cBNuevoDoctor);
		
		JComboBox cBNuevoEspecialidad = new JComboBox();
		cBNuevoEspecialidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					cBNuevoDoctor.setModel(CnRecepcionista.LlenarDoctor(cNuevoDoctor,(String)cBNuevoEspecialidad.getSelectedItem()));
				} 
				catch (SQLException e1) 
				{

					e1.printStackTrace();
				}
			}
		});
		
		DefaultComboBoxModel cNEspecialidad=new DefaultComboBoxModel();
		cBNuevoEspecialidad.setModel(cNEspecialidad);
		try 
		{
			CnRecepcionista.LlenarEspecialidad(cNEspecialidad);
		} 
		catch (SQLException e2) 
		{
			e2.printStackTrace();
		}
		cBNuevoEspecialidad.setBounds(133, 83, 190, 20);
		Registrar.add(cBNuevoEspecialidad);
		
		
		JLabel lblFechaDeLa = new JLabel("Fecha de la Cita:");
		lblFechaDeLa.setBounds(10, 136, 100, 14);
		Registrar.add(lblFechaDeLa);
		
		DateFormat df=DateFormat.getDateInstance();
		
		JCalendar cNuevoCalendario = new JCalendar();
		cNuevoCalendario.setBounds(133, 136, 302, 109);
		Registrar.add(cNuevoCalendario);
		
		JComboBox cBNuevoNPaciente = new JComboBox();
		cBNuevoNPaciente.setBounds(133, 32, 188, 20);
		DefaultComboBoxModel cNuevoNPaciente=new DefaultComboBoxModel();
		Registrar.add(cBNuevoNPaciente);
		try {
			cBNuevoNPaciente.setModel(CnRecepcionista.LlenarPacientes(cNuevoNPaciente));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JButton btnNuevoRegistrar = new JButton("Registrar");
		btnNuevoRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date sqlDate=new java.sql.Date(cNuevoCalendario.getDate().getTime());
				try {
					CnRecepcionista.RegistrarCita(tFNuevoCita.getText(),cBNuevoNPaciente.getSelectedItem().toString().substring(0, 8), tFNuevoMotivo.getText(), (String)cBNuevoEspecialidad.getSelectedItem(), ((String)cBNuevoDoctor.getSelectedItem()).substring(0,8), sqlDate, Integer.parseInt(tFNuevoHabitacion.getText()), Integer.parseInt(tFNuevoPiso.getText()));
					cBNuevoNPaciente.setEnabled(false);
					tFNuevoMotivo.setEnabled(false);
					cBNuevoEspecialidad.setEnabled(false);
					cBNuevoDoctor.setEnabled(false);
					cNuevoCalendario.setEnabled(false);
					tFNuevoHabitacion.setEnabled(false);
					tFNuevoPiso.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Guardado Correctamente");
					try {
						CnRecepcionista.LlenarTablaCita(modelLista);
					} catch (SQLException e3) {
						e3.printStackTrace();
					}
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNuevoRegistrar.setBounds(191, 321, 89, 30);
		Registrar.add(btnNuevoRegistrar);
		
		JLabel lblHabitacin = new JLabel("Habitaci\u00F3n:");
		lblHabitacin.setBounds(45, 288, 65, 14);
		Registrar.add(lblHabitacin);
		
		tFNuevoHabitacion = new JTextField();
		tFNuevoHabitacion.setText("");
		tFNuevoHabitacion.setColumns(10);
		tFNuevoHabitacion.setBounds(133, 285, 86, 20);
		Registrar.add(tFNuevoHabitacion);
		
		JLabel label_11 = new JLabel("Piso:");
		label_11.setBounds(304, 288, 35, 14);
		Registrar.add(label_11);
		
		tFNuevoPiso = new JTextField();
		tFNuevoPiso.setColumns(10);
		tFNuevoPiso.setBounds(349, 285, 86, 20);
		Registrar.add(tFNuevoPiso);
		
		JButton btnNuevaCita = new JButton("nueva cita");
		btnNuevaCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tFNuevoCita.setText(CnRecepcionista.CodigoAleatorioCita());
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
				cBNuevoNPaciente.setSelectedIndex(0);
				tFNuevoMotivo.setText("");
				cBNuevoEspecialidad.setSelectedIndex(0);
				cBNuevoDoctor.setSelectedIndex(0);
				Date date = new Date();
				cNuevoCalendario.setDate(date);
				tFNuevoHabitacion.setText("");
				tFNuevoPiso.setText("");
				cBNuevoNPaciente.setEnabled(true);
				tFNuevoMotivo.setEnabled(true);
				cBNuevoEspecialidad.setEnabled(true);
				cBNuevoDoctor.setEnabled(true);
				cNuevoCalendario.setEnabled(true);
				tFNuevoHabitacion.setEnabled(true);
				tFNuevoPiso.setEnabled(true);
			}
		});
		btnNuevaCita.setBounds(290, 325, 89, 23);
		Registrar.add(btnNuevaCita);
		
		JPanel Buscar = new JPanel();
		Buscar.setBackground(new Color(240, 255, 255));
		Buscar.setLayout(null);
		tabbedPane.addTab("Buscar", null, Buscar, null);
		
		JLabel label = new JLabel("N\u00BA de Cita:");
		label.setBounds(10, 11, 65, 14);
		Buscar.add(label);
		
		tFBuscarCita = new JTextField();
		tFBuscarCita.setColumns(10);
		tFBuscarCita.setBounds(85, 8, 86, 20);
		Buscar.add(tFBuscarCita);
		
		JLabel label_1 = new JLabel("Nobre del Paciente:");
		label_1.setBounds(10, 36, 123, 14);
		Buscar.add(label_1);
		
		cBBuscarPaciente = new JComboBox();
		cBBuscarPaciente.setEnabled(false);
		cBBuscarPaciente.setBounds(133, 33, 190, 20);
		cBuscarPaciente=new DefaultComboBoxModel();
		Buscar.add(cBBuscarPaciente);
		try {
			cBBuscarPaciente.setModel(CnRecepcionista.LlenarPacientes(cBuscarPaciente));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JLabel label_2 = new JLabel("Motivo de Cita:");
		label_2.setBounds(10, 61, 123, 14);
		Buscar.add(label_2);
		
		tFBuscarMotivo = new JTextField();
		tFBuscarMotivo.setEnabled(false);
		tFBuscarMotivo.setColumns(10);
		tFBuscarMotivo.setBounds(133, 58, 190, 20);
		Buscar.add(tFBuscarMotivo);
		
		JLabel label_3 = new JLabel("Nombre del Doctor:");
		label_3.setBounds(10, 111, 123, 14);
		Buscar.add(label_3);
		
		JComboBox cBBuscarDoctor = new JComboBox();
		cBBuscarDoctor.setEnabled(false);
		DefaultComboBoxModel cDoctor=new DefaultComboBoxModel();
		cDoctor.addElement("-Seleccione-");
		cBBuscarDoctor.setModel(cDoctor);
		cBBuscarDoctor.setBounds(133, 108, 190, 20);
		Buscar.add(cBBuscarDoctor);
		
		JLabel label_4 = new JLabel("Especialidad:");
		label_4.setBounds(10, 86, 123, 14);
		Buscar.add(label_4);
		
		JComboBox cBBuscarEspecialidad = new JComboBox();
		cBBuscarEspecialidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					cBBuscarDoctor.setModel(CnRecepcionista.LlenarDoctor(cDoctor, (String)cBBuscarEspecialidad.getSelectedItem()));
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		DefaultComboBoxModel cEspecialidad = new DefaultComboBoxModel();
		cEspecialidad.addElement("-Seleccione-");
		cBBuscarEspecialidad.setModel(cEspecialidad);
		cBBuscarEspecialidad.setEnabled(false);
		cBBuscarEspecialidad.setBounds(133, 83, 190, 20);
		Buscar.add(cBBuscarEspecialidad);
		
		JLabel label_5 = new JLabel("Fecha de la Cita:");
		label_5.setBounds(10, 136, 100, 14);
		Buscar.add(label_5);
			
		cBuscarCalendario = new JCalendar();
		cBuscarCalendario.setBounds(133, 136, 302, 109);
		Buscar.add(cBuscarCalendario);
		cBuscarCalendario.setEnabled(false);
		
		JButton btnBuscarBuscar = new JButton("Buscar");
		btnBuscarBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					cBBuscarPaciente.setEnabled(false);
					tFBuscarMotivo.setEnabled(false);
					cBBuscarEspecialidad.setEnabled(false);
					cBBuscarDoctor.setEnabled(false);
					cBuscarCalendario.setEnabled(false);
					tFBuscarHabitacion.setEnabled(false);
					tFBuscarPiso.setEnabled(false);
					int i;
					CnRecepcionista.LlenarBuscarCita(tFBuscarCita.getText());
					tFBuscarCita.setEnabled(false);
					CnRecepcionista.LlenarEspecialidad(cEspecialidad);
					cBBuscarEspecialidad.setSelectedItem(Especialidad);
					cBBuscarDoctor.setModel(CnRecepcionista.LlenarDoctor(cDoctor,Especialidad));
					for(i=0;i<cBBuscarDoctor.getItemCount();i++)
					{
						if((((String)cBBuscarDoctor.getItemAt(i)).substring(0,8)).equals(Doctor))
						{
							cBBuscarDoctor.setSelectedIndex(i);
						}
					}
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				} 
			}
		});
		btnBuscarBuscar.setBounds(247, 7, 89, 23);
		Buscar.add(btnBuscarBuscar);
		
		JButton btnBuscarEditar = new JButton("Editar");
		btnBuscarEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cBBuscarPaciente.setEnabled(true);
				tFBuscarMotivo.setEnabled(true);
				cBBuscarEspecialidad.setEnabled(true);
				cBBuscarDoctor.setEnabled(true);
				cBuscarCalendario.setEnabled(true);
				tFBuscarHabitacion.setEnabled(true);
				tFBuscarPiso.setEnabled(true);
			}
		});
		btnBuscarEditar.setBounds(85, 330, 90, 23);
		Buscar.add(btnBuscarEditar);
		
		JButton btnBuscarGuardar = new JButton("Guardar");
		btnBuscarGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date sqlDate=new java.sql.Date(cBuscarCalendario.getDate().getTime());
				try
				{
					CnRecepcionista.UpdateCita(tFBuscarCita.getText(),cBBuscarPaciente.getSelectedItem().toString().substring(0, 8),tFBuscarMotivo.getText(),(String)cBBuscarEspecialidad.getSelectedItem(),((String)cBBuscarDoctor.getSelectedItem()).substring(0,8),sqlDate,Integer.parseInt(tFBuscarHabitacion.getText()),Integer.parseInt(tFBuscarPiso.getText()));
					JOptionPane.showMessageDialog(null, "Guardado Correctamente");
					tFBuscarCita.setEnabled(false);
					cBBuscarPaciente.setEnabled(false);
					tFBuscarMotivo.setEnabled(false);
					cBBuscarEspecialidad.setEnabled(false);
					cBBuscarDoctor.setEnabled(false);
					cBuscarCalendario.setEnabled(false);
					tFBuscarHabitacion.setEnabled(false);
					tFBuscarPiso.setEnabled(false);
					try {
						CnRecepcionista.LlenarTablaCita(modelLista);
					} catch (SQLException e3) {
						e3.printStackTrace();
					}
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnBuscarGuardar.setBounds(217, 330, 90, 23);
		Buscar.add(btnBuscarGuardar);
		
		JButton btnBuscarEliminar = new JButton("Eliminar");
		btnBuscarEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Eliminar Cita "+tFBuscarCita.getText())==0)
				{
					try
					{
						CnRecepcionista.DeleteCita(tFBuscarCita.getText());
						tFBuscarCita.setEnabled(true);
						cBBuscarPaciente.setEnabled(false);
						tFBuscarMotivo.setEnabled(false);
						cBBuscarEspecialidad.setEnabled(false);
						cBBuscarDoctor.setEnabled(false);
						cBuscarCalendario.setEnabled(false);
						tFBuscarHabitacion.setEnabled(false);
						tFBuscarPiso.setEnabled(false);
						tFBuscarCita.setText("");
						cBBuscarPaciente.setSelectedIndex(0);
						tFBuscarMotivo.setEnabled(false);
						cBBuscarEspecialidad.setSelectedIndex(0);
						cBBuscarDoctor.setSelectedIndex(0);
						Date date=new Date();
						cBuscarCalendario.setEnabled(false);
						cBuscarCalendario.setDate(date);
						tFBuscarHabitacion.setEnabled(false);
						tFBuscarPiso.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
						try {
							CnRecepcionista.LlenarTablaCita(modelLista);
						} catch (SQLException e3) {
							e3.printStackTrace();
						}
					}
					catch(Exception e1)
					{
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnBuscarEliminar.setBounds(345, 330, 90, 23);
		Buscar.add(btnBuscarEliminar);
		
		JLabel lblHabitacion = new JLabel("Habitaci\u00F3n:");
		lblHabitacion.setBounds(45, 288, 65, 14);
		Buscar.add(lblHabitacion);
		
		tFBuscarHabitacion = new JTextField();
		tFBuscarHabitacion.setEnabled(false);
		tFBuscarHabitacion.setText("");
		tFBuscarHabitacion.setBounds(133, 285, 86, 20);
		Buscar.add(tFBuscarHabitacion);
		tFBuscarHabitacion.setColumns(10);
		
		tFBuscarPiso = new JTextField();
		tFBuscarPiso.setEnabled(false);
		tFBuscarPiso.setBounds(349, 285, 86, 20);
		Buscar.add(tFBuscarPiso);
		tFBuscarPiso.setColumns(10);
		
		JLabel lblPiso = new JLabel("Piso:");
		lblPiso.setBounds(302, 288, 34, 14);
		Buscar.add(lblPiso);
		
		JButton btnNewBusqueda = new JButton("Buscar Otro");
		btnNewBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				tFBuscarCita.setEnabled(true);
				tFBuscarCita.setText("");
				cBBuscarPaciente.setEnabled(false);
				cBBuscarPaciente.setSelectedIndex(0);
				tFBuscarMotivo.setEnabled(false);
				tFBuscarMotivo.setText("");
				cBBuscarEspecialidad.setEnabled(false);
				cBBuscarEspecialidad.setSelectedIndex(0);
				cBBuscarDoctor.setEnabled(false);
				cBBuscarDoctor.setSelectedIndex(0);
				cBuscarCalendario.setEnabled(false);
				cBuscarCalendario.setDate(date);
				tFBuscarHabitacion.setEnabled(false);
				tFBuscarHabitacion.setText("");
				tFBuscarPiso.setEnabled(false);
				tFBuscarPiso.setText("");
				JOptionPane.showMessageDialog(null, "La Hoja de Busqueda ha Sido Limpiada");
			}
		});
		btnNewBusqueda.setBounds(365, 7, 89, 23);
		Buscar.add(btnNewBusqueda);
				
		JLabel lblCitas = new JLabel("Citas");
		lblCitas.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCitas.setBounds(32, 15, 113, 32);
		panel.add(lblCitas);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recepcionista recepcionista= new Recepcionista();
				recepcionista.setVisible(true);
				dispose();
				
			}
		});
		btnRegresar.setBounds(451, 24, 89, 23);
		panel.add(btnRegresar);
	}
}