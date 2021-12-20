package suing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import conexión.CnPersonal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Color;


@SuppressWarnings("serial")
public class PerMedPacienteHojaTranslado extends JFrame {

	private JPanel contentPane;
	private JTextField tFNuevoObs;
	private JTextField tFNuevoNHTraslado;
	private JTextField tFListaNHTraslado;
	private JTextField tFNuevoMotivo;
	private JTextField cBNuevoDoctor;

	@SuppressWarnings({ "rawtypes", "unused" })
	public PerMedPacienteHojaTranslado(String Usuario,String Paciente) {
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
		tabbedPane.addTab("Lista", null, Lista, null);
		Lista.setLayout(null);
		
		String columnas[]= {"N�HC","Nombre de Encargado","Paciente","Fecha","Motivo"};
		JTable list = new JTable();
		DefaultTableModel modelLista = new DefaultTableModel();
		modelLista.setColumnIdentifiers(columnas);
		list.setModel(modelLista);
		try {
			CnPersonal.LlenarTablaTraslado(modelLista,Usuario);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 409, 283);
		scrollPane.setPreferredSize(new Dimension(400, 150));
		scrollPane.setViewportView(list);
		Lista.add(scrollPane);
		
		JLabel lblBuscarNtranslado = new JLabel("Buscar N\u00BA Traslado:");
		lblBuscarNtranslado.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblBuscarNtranslado.setBounds(10, 13, 192, 20);
		Lista.add(lblBuscarNtranslado);
		
		JLabel btnListaBuscar = new JLabel("Buscar");
		btnListaBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnPersonal.buscarTablaHTraslado(modelLista, Usuario, tFListaNHTraslado.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			}
		});
		btnListaBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaBuscar.setBounds(265, 15, 44, 15);
		Lista.add(btnListaBuscar);
		
		JLabel btnListaEliminar = new JLabel("Mostrar todo");
		btnListaEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					CnPersonal.LlenarTablaTraslado(modelLista,Usuario);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnListaEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btnListaEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListaEliminar.setBounds(329, 15, 90, 14);
		Lista.add(btnListaEliminar);
		
		tFListaNHTraslado = new JTextField();
		tFListaNHTraslado.setColumns(10);
		tFListaNHTraslado.setBounds(172, 13, 72, 20);
		Lista.add(tFListaNHTraslado);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Nuevo", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblCertificadodePaciente = new JLabel("Certificado de traslado de paciente N\u00BA:");
		lblCertificadodePaciente.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCertificadodePaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCertificadodePaciente.setBounds(6, 11, 268, 20);
		panel_1.add(lblCertificadodePaciente);
		
		JLabel lblMotivoDeTranslado = new JLabel("Motivo de Translado:");
		lblMotivoDeTranslado.setBounds(20, 50, 114, 20);
		panel_1.add(lblMotivoDeTranslado);
		
		JLabel lblDoctor = new JLabel("Doctor:");
		lblDoctor.setBounds(20, 150, 50, 20);
		panel_1.add(lblDoctor);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(20, 100, 46, 20);
		panel_1.add(lblFecha);
		
		JLabel lblObseravaciones = new JLabel("Obseravaciones:");
		lblObseravaciones.setBounds(20, 200, 90, 20);
		panel_1.add(lblObseravaciones);
		
		DateFormat df=DateFormat.getInstance();
		JDateChooser dCNuevoFecha = new JDateChooser();
		dCNuevoFecha.setBounds(150, 100, 100, 20);
		panel_1.add(dCNuevoFecha);
		
		DefaultComboBoxModel cBnuevodoctor=new DefaultComboBoxModel();
		
		JButton btnNuevoGuardar = new JButton("Registrar");
		btnNuevoGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date sqlDate=new java.sql.Date(dCNuevoFecha.getDate().getTime());
				try {
					CnPersonal.NuevaHOjaTraslado(tFNuevoNHTraslado.getText(), Paciente, tFNuevoMotivo.getText(), sqlDate, Usuario, tFNuevoObs.getText());
					tFNuevoMotivo.setEnabled(false);
					dCNuevoFecha.setEnabled(false);
					cBNuevoDoctor.setEnabled(true);
					tFNuevoObs.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Guardado Correctamente");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNuevoGuardar.setBounds(206, 301, 90, 30);
		panel_1.add(btnNuevoGuardar);
		
		tFNuevoObs = new JTextField();
		tFNuevoObs.setBounds(150, 200, 200, 90);
		panel_1.add(tFNuevoObs);
		tFNuevoObs.setColumns(10);
		
		tFNuevoNHTraslado = new JTextField();
		tFNuevoNHTraslado.setEnabled(false);
		tFNuevoNHTraslado.setColumns(10);
		tFNuevoNHTraslado.setBounds(284, 12, 66, 20);
		panel_1.add(tFNuevoNHTraslado);
		
		try {
			tFNuevoNHTraslado.setText(CnPersonal.CodigoAleatorioTraslado());
			
			tFNuevoMotivo = new JTextField();
			tFNuevoMotivo.setBounds(150, 50, 100, 20);
			panel_1.add(tFNuevoMotivo);
			tFNuevoMotivo.setColumns(10);
			
			cBNuevoDoctor = new JTextField();
			cBNuevoDoctor.setBounds(150, 150, 86, 20);
			panel_1.add(cBNuevoDoctor);
			cBNuevoDoctor.setColumns(10);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVolver.setBounds(354, 11, 90, 23);
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
