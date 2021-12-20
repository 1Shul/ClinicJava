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

import com.toedter.calendar.JCalendar;

import conexión.CnAdministrador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class AdmnCita extends JFrame {

	private JPanel contentPaneCitas;
	public static String descripcion;
	public static String conclusion;
	private JTextField tFDetailNCita;
	public static JTextField tFDetailNomPaciente;
	public static JTextField tFDetailDateMatter;
	public static JTextField tFDetailHabitacion;
	public static JTextField tFDetailPiso;
	private JTextField tFListSearchCita;
	@SuppressWarnings("rawtypes")
	public static DefaultComboBoxModel cBdetaildoctorname,cBdetailEspeciality;
	public static java.util.Date Calendar;
	public static JCalendar calendar;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AdmnCita(int a) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cl\u00EDnica UNAC");
		setBounds(100, 100, 630, 530);
		contentPaneCitas = new JPanel();
		contentPaneCitas.setBackground(new Color(100, 149, 237));
		contentPaneCitas.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneCitas);
		contentPaneCitas.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Citas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 10, 89, 29);
		contentPaneCitas.add(lblNewLabel);
		
		JTabbedPane tabbedPaneCita = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCita.setBounds(10, 50, 600, 440);
		contentPaneCitas.add(tabbedPaneCita);
		
		JPanel pListCita = new JPanel();
		pListCita.setBackground(new Color(240, 248, 255));
		pListCita.setLayout(null);
		tabbedPaneCita.addTab("Lista de Citas", null, pListCita, null);
		
		String columnas[]= {"N�Cita","Nombre Paciente","Nombre Doctor","Fecha","Especialidad","Motivo"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		
		try {
			CnAdministrador.LlenarTablaCitas(modelLista);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setBounds(10, 46, 575, 355);
		scrollPane.setViewportView(list);
		pListCita.add(scrollPane);
		
		JLabel lblSearchBuscarCita = new JLabel("Buscar Cita");
		lblSearchBuscarCita.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearchBuscarCita.setBounds(10, 13, 160, 20);
		pListCita.add(lblSearchBuscarCita);
		
		tFListSearchCita = new JTextField();
		tFListSearchCita.setColumns(10);
		tFListSearchCita.setBounds(129, 15, 154, 20);
		pListCita.add(tFListSearchCita);
		
		JLabel btnListSearchCita = new JLabel("Buscar");
		btnListSearchCita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnAdministrador.buscarTablaCita(modelLista, tFListSearchCita.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListSearchCita.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListSearchCita.setBounds(399, 20, 46, 15);
		pListCita.add(btnListSearchCita);
		
		JLabel btnListShowAll = new JLabel("Mostrar Todo");
		btnListShowAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnAdministrador.LlenarTablaCitas(modelLista);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListShowAll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListShowAll.setBounds(477, 20, 108, 14);
		pListCita.add(btnListShowAll);
			
			JPanel pDetailCita = new JPanel();
			pDetailCita.setBackground(new Color(240, 248, 255));
			tabbedPaneCita.addTab("Detalles de la Cita", null, pDetailCita, null);
			pDetailCita.setLayout(null);
			
			JLabel lblDetailNCita = new JLabel("N\u00BA de Cita:");
			lblDetailNCita.setBounds(22, 11, 65, 14);
			pDetailCita.add(lblDetailNCita);
			
			JButton btnDetailSearch = new JButton("Buscar");
			btnDetailSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						CnAdministrador.rellenarcita(tFDetailNCita.getText());
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			btnDetailSearch.setBounds(281, 7, 89, 23);
			pDetailCita.add(btnDetailSearch);
			
			tFDetailNCita = new JTextField();
			tFDetailNCita.setBounds(122, 8, 86, 20);
			pDetailCita.add(tFDetailNCita);
			tFDetailNCita.setColumns(10);
			
			JPanel pane = new JPanel();
			pane.setBackground(new Color(240, 248, 255));
			pane.setBounds(0, 36, 595, 376);
			pDetailCita.add(pane);
			pane.setLayout(null);
			
			JLabel lblNomPaciente = new JLabel("Nobre del Paciente:");
			lblNomPaciente.setBounds(10, 36, 123, 14);
			pane.add(lblNomPaciente);
			
			tFDetailNomPaciente = new JTextField();
			tFDetailNomPaciente.setEnabled(false);
			tFDetailNomPaciente.setBounds(133, 33, 190, 20);
			tFDetailNomPaciente.setColumns(10);
			pane.add(tFDetailNomPaciente);
			
			JLabel label_2 = new JLabel("Motivo de Cita:");
			label_2.setBounds(10, 61, 123, 14);
			pane.add(label_2);
			
			tFDetailDateMatter = new JTextField();
			tFDetailDateMatter.setEnabled(false);
			tFDetailDateMatter.setBounds(133, 58, 190, 20);
			tFDetailDateMatter.setColumns(10);
			pane.add(tFDetailDateMatter);
			
			JLabel label_3 = new JLabel("Nombre del Doctor:");
			label_3.setBounds(10, 86, 123, 14);
			pane.add(label_3);
			
			cBdetaildoctorname=new DefaultComboBoxModel();
			JComboBox cBDetailDoctorName = new JComboBox();
			cBDetailDoctorName.setEnabled(false);
			cBDetailDoctorName.setModel(cBdetaildoctorname);
			cBDetailDoctorName.setBounds(133, 83, 190, 20);
			pane.add(cBDetailDoctorName);
			
			JLabel label_4 = new JLabel("Especialidad:");
			label_4.setBounds(10, 111, 123, 14);
			pane.add(label_4);
			
			cBdetailEspeciality =new DefaultComboBoxModel();
			JComboBox cBDetailEspeciality = new JComboBox();
			cBDetailEspeciality.setEnabled(false);
			cBDetailEspeciality.setModel(cBdetailEspeciality);
			cBDetailEspeciality.setBounds(133, 108, 190, 20);
			pane.add(cBDetailEspeciality);
			
			JLabel label_5 = new JLabel("Fecha de la Cita:");
			label_5.setBounds(10, 136, 100, 14);
			pane.add(label_5);
	
			Calendar=null;
			calendar = new JCalendar();
			calendar.setBounds(133, 136, 302, 109);
			pane.add(calendar);
			calendar.setEnabled(false);
			
			JLabel lblHabitacin = new JLabel("Habitaci\u00F3n:");
			lblHabitacin.setBounds(56, 310, 65, 14);
			pane.add(lblHabitacin);
			
			tFDetailHabitacion = new JTextField();
			tFDetailHabitacion.setEnabled(false);
			tFDetailHabitacion.setBounds(133, 307, 86, 20);
			tFDetailHabitacion.setColumns(10);
			pane.add(tFDetailHabitacion);
			
			tFDetailPiso = new JTextField();
			tFDetailPiso.setEnabled(false);
			tFDetailPiso.setBounds(380, 307, 86, 20);
			tFDetailPiso.setColumns(10);
			pane.add(tFDetailPiso);
			
			JLabel label_8 = new JLabel("Piso:");
			label_8.setBounds(332, 310, 65, 14);
			pane.add(label_8);
			
			JButton btnRegresar = new JButton("Regresar");
			btnRegresar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Administrador administrador = new Administrador();
					administrador.setVisible(true);
					dispose();
				}
			});
			btnRegresar.setBounds(521, 19, 89, 23);
			contentPaneCitas.add(btnRegresar);
		
		DefaultComboBoxModel cEspecialidad = new DefaultComboBoxModel();
		cEspecialidad.addElement("-Seleccione-");
		
		DefaultComboBoxModel cDoctor = new DefaultComboBoxModel();
		cDoctor.addElement("-Seleccione-");
		
		DefaultComboBoxModel cDoctorID = new DefaultComboBoxModel();
		cDoctorID.addElement("-Seleccione-");
		
		
		switch(a)
		{
		case 1:tabbedPaneCita.setSelectedComponent(pListCita);break;
		case 2:tabbedPaneCita.setSelectedComponent(pDetailCita);break;
		}
		
	}
	
}
