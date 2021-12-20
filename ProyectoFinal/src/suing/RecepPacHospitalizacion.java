package suing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;

import conexión.CnRecepcionista;

import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.Color;

@SuppressWarnings("serial")
public class RecepPacHospitalizacion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RecepPacHospitalizacion(String Paciente, int i) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		setTitle("Cl\u00EDnica UNAC - Internamiento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 618);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Motivo de Internamiento:");
		label.setBounds(109, 176, 150, 20);
		contentPane.add(label);
		
		JLabel lblFechaDeInscripcin = new JLabel("Fecha de Inscripci\u00F3n:");
		lblFechaDeInscripcin.setBounds(109, 226, 122, 20);
		contentPane.add(lblFechaDeInscripcin);
				
		JCalendar calendar = new JCalendar();
		calendar.setBounds(269, 227, 248, 136);
		contentPane.add(calendar);
		
		JLabel lblHabitacin = new JLabel("Habitaci\u00F3n:");
		lblHabitacin.setBounds(109, 436, 74, 20);
		contentPane.add(lblHabitacin);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(237, 436, 50, 20);
		contentPane.add(spinner);
		
		JLabel label_3 = new JLabel("Piso:");
		label_3.setBounds(417, 436, 46, 20);
		contentPane.add(label_3);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(499, 436, 50, 20);
		contentPane.add(spinner_1);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(340, 517, 89, 23);
		contentPane.add(btnGuardar);
		
		JLabel lblInternarPaciente = new JLabel("Internar Paciente");
		lblInternarPaciente.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblInternarPaciente.setBounds(21, 27, 238, 32);
		contentPane.add(lblInternarPaciente);
		
		JLabel lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setBounds(109, 100, 74, 14);
		contentPane.add(lblEspecialidad);
		
		JLabel lblDoctor = new JLabel("Doctor a Cargo");
		lblDoctor.setBounds(109, 140, 74, 14);
		contentPane.add(lblDoctor);
		
		DefaultComboBoxModel combobox_1=new DefaultComboBoxModel();
		
		JComboBox comboBox_1 = new JComboBox();
		
		comboBox_1.setModel(combobox_1);
		comboBox_1.setBounds(269, 97, 160, 20);
		contentPane.add(comboBox_1);
		
		try {
			CnRecepcionista.LlenarEspecialidad(combobox_1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DefaultComboBoxModel combobox_2=new DefaultComboBoxModel();
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(combobox_2);
		comboBox_2.setBounds(269, 137, 160, 20);
		contentPane.add(comboBox_2);
		
		textField = new JTextField();
		textField.setBounds(269, 176, 160, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CnRecepcionista.LlenarDoctor(combobox_2, (String)comboBox_1.getSelectedItem());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i==0) {
					java.sql.Date sqlDate=new java.sql.Date(calendar.getDate().getTime());
					try {
						CnRecepcionista.EnviarBDPacHospitalizado(Paciente, ((String)comboBox_2.getSelectedItem()).substring(0, 8), (String)comboBox_1.getSelectedItem(), textField.getText(), sqlDate, (int)spinner.getValue(), (int)spinner_1.getValue());
						JOptionPane.showMessageDialog(null, "Registrado Correctamente");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					java.sql.Date sqlDate=new java.sql.Date(calendar.getDate().getTime());
					try {
						CnRecepcionista.UpdateBDPacHospitalizado(Paciente, ((String)comboBox_2.getSelectedItem()).substring(0, 8), (String)comboBox_1.getSelectedItem(), textField.getText(), sqlDate, (int)spinner.getValue(), (int)spinner_1.getValue());
						JOptionPane.showMessageDialog(null, "Actualizado Correctamente");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
