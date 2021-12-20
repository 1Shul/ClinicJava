package conexión;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import suing.PerMedCitas;
import suing.PerMedPaciente;
import suing.PerMedPacienteHojaClinica;

public class CnPersonal {
	private static Connection cn;

	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cn = DriverManager.getConnection("jdbc:sqlserver://localhost:55766;databaseName=ProyectoFinal", "sa",
					"123");
		} catch (Exception e) {
			cn = null;
		}
		return cn;
	}

	public String recibir() throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		if (cnPersonal != null) {
			System.out.println("Valido");
		} else {
			System.out.println("Invalido");
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void LlenarBuscarCita(String NCita, String NDoctor, DefaultComboBoxModel Especialidad,
			DefaultComboBoxModel Encargado, Date fecha) throws SQLException {
		Especialidad.removeAllElements();
		String iden = "PAC-";
		int i = 1;
		String NHClinica = iden + "000" + i;
		Connection cnRecepcionista = CnRecepcionista.getConnection();
		Statement stm99 = cnRecepcionista.createStatement();
		ResultSet rst99 = stm99.executeQuery("select NHClinica from HojasClinicas");
		while (rst99.next()) {
			if (NHClinica.equals(rst99.getString("NHClinica"))) {
				i++;
				if (i <= 9) {
					NHClinica = iden + "000" + i;
				} else if (i <= 99) {
					NHClinica = iden + "00" + i;
				} else if (i <= 999) {
					NHClinica = iden + "0" + i;
				} else {
					NHClinica = iden + i;
				}
			}
		}
		Connection cnPersonal = CnPersonal.getConnection();
		String docEncargado = null;
		PreparedStatement stm0 = cnPersonal
				.prepareStatement("select Nombre, Apellido from Personal where IDPersonal = ?");
		stm0.setString(1, NDoctor);
		ResultSet rst0 = stm0.executeQuery();
		while (rst0.next()) {
			docEncargado = rst0.getString("Nombre") + " " + rst0.getString("Apellido");
		}
		PreparedStatement stm = cnPersonal.prepareStatement("select * from Cita where NCita = ? and NDoctor= ?");
		stm.setString(1, NCita);
		stm.setString(2, NDoctor);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			PerMedCitas.tFBuscarPaciente.setText(rst.getString("NPaciente"));
			PerMedCitas.tBuscarMotivo.setText(rst.getString("Motivo"));
			Especialidad.addElement(rst.getString("Especialidad"));
			stm = cnRecepcionista.prepareStatement(
					"select convert(varchar,Fecha,105) as fecha from Cita where NCita=? and NDoctor=?");
			stm.setString(1, NCita);
			stm.setString(2, NDoctor);
			ResultSet rst9 = stm.executeQuery();
			while (rst9.next()) {
				String dateValue = rst9.getString("fecha");
				try {
					java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
					PerMedCitas.calendar.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			PerMedCitas.tFBuscarHabitacion.setText(rst.getString("Habitacion"));
			PerMedCitas.tFBuscarPiso.setText(rst.getString("Piso"));
			Encargado.removeAllElements();
			Encargado.addElement(docEncargado);
			Encargado.setSelectedItem(docEncargado);
			PerMedCitas.tFHCNHojaClinica.setText(NHClinica);
		}

		PreparedStatement stm2 = cnPersonal.prepareStatement("select * from PacienteDAsistencial where NCita = ?");
		stm2.setString(1, NCita);
		ResultSet rst2 = stm2.executeQuery();
		while (rst2.next()) {
			if (NCita.equals(rst2.getString("NCita"))) {
				PerMedCitas.tFDAPeso.setText(rst2.getFloat("Peso") + "");
				PerMedCitas.tFDAPeso.setEnabled(false);
				PerMedCitas.tFDATalla.setText(rst2.getFloat("Talla") + "");
				PerMedCitas.tFDATalla.setEnabled(false);
				PerMedCitas.tFDAPulso.setText(rst2.getFloat("Pulso") + "");
				PerMedCitas.tFDAPulso.setEnabled(false);
				PerMedCitas.tFDARitCardiaco.setText(rst2.getInt("RitmoCardiaco") + "");
				PerMedCitas.tFDARitCardiaco.setEnabled(false);
				PerMedCitas.tFDADiagnostico.setText(rst2.getString("DiagnosticoPrincipal"));
				PerMedCitas.tFDADiagnostico.setEnabled(false);
				PerMedCitas.tFDAMedicinas.setText(rst2.getString("Medicinas"));
				PerMedCitas.tFDAMedicinas.setEnabled(false);
				stm = cnPersonal.prepareStatement(
						"select convert(varchar,Fecha,105) as fecha from PacienteDAsistencial where NCita=?");
				stm.setString(1, NCita);
				ResultSet rst3 = stm.executeQuery();
				while (rst3.next()) {
					String dateValue = rst3.getString("fecha");
					try {
						java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
						PerMedCitas.cDDAFecha.setDate(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				PerMedCitas.cDDAFecha.setEnabled(false);
				PerMedCitas.tFDAAntecedentes.setText(rst2.getString("Antecedentes"));
				PerMedCitas.tFDAAntecedentes.setEnabled(false);
				PerMedCitas.tFDATrastorno.setText(rst2.getString("Trastorno"));
				PerMedCitas.tFDATrastorno.setEnabled(false);
				PerMedCitas.btnDAActualizar.setEnabled(true);
				PerMedCitas.btnDAGuardar.setEnabled(false);
			} else {
				PerMedCitas.btnDAActualizar.setEnabled(true);
				PerMedCitas.btnDAGuardar.setEnabled(false);
			}

		}
		PreparedStatement stm3 = cnPersonal.prepareStatement("select * from HojasClinicas where NCita = ?");
		stm3.setString(1, NCita);
		ResultSet rst3 = stm3.executeQuery();
		while (rst3.next()) {
			Encargado.removeAllElements();
			Encargado.addElement("-Seleccione-");
			Encargado.addElement(docEncargado);
			if (NCita.equals(rst3.getString("NCita"))) {
				PerMedCitas.tFHCNHojaClinica.setText(rst3.getString("NHClinica"));
				PerMedCitas.tFHCNHojaClinica.setEnabled(false);
				Encargado.setSelectedItem(docEncargado);
				PerMedCitas.cBHCEncargado.setEnabled(false);
				stm = cnRecepcionista.prepareStatement(
						"select convert(varchar,Fecha,105) as fecha from HojasClinicas where NCita=?");
				stm.setString(1, NCita);
				ResultSet rst4 = stm.executeQuery();
				while (rst4.next()) {
					String dateValue = rst4.getString("fecha");
					try {
						java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
						PerMedCitas.dCHCFecha.setDate(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				PerMedCitas.dCHCFecha.setEnabled(false);
				PerMedCitas.tFHCPruebas.setText(rst3.getString("Pruebas"));
				PerMedCitas.tFHCPruebas.setEnabled(false);
				PerMedCitas.tFHCResultados.setText(rst3.getString("Resultados"));
				PerMedCitas.tFHCResultados.setEnabled(false);
				PerMedCitas.btnHCRegistrar.setEnabled(false);
				PerMedCitas.btnHCActualizar.setEnabled(true);
			} else {
				PerMedCitas.btnHCRegistrar.setEnabled(false);
				PerMedCitas.btnHCActualizar.setEnabled(true);
			}
		}
		cnPersonal.close();
	}

	public static void EnviarBDCDAsistenciales(String NCita, String Paciente, Float Peso, Float Talla, Float Pulso,
			int RitmoCardiaco, String Medicinas, String Diagnostico, Date Fecha, String Antecedentes, String Trastorno)
			throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement(
				"insert into PacienteDAsistencial (NCita,IDPaciente,Peso,Talla,Pulso,RitmoCardiaco,Medicinas,DiagnosticoPrincipal,Fecha,Antecedentes,Trastorno) values (?,?,?,?,?,?,?,?,?,?,?)");
		stm.setString(1, NCita);
		stm.setString(2, Paciente);
		stm.setFloat(3, Peso);
		stm.setFloat(4, Talla);
		stm.setFloat(5, Pulso);
		stm.setInt(6, RitmoCardiaco);
		stm.setString(7, Medicinas);
		stm.setString(8, Diagnostico);
		stm.setDate(9, Fecha);
		stm.setString(10, Antecedentes);
		stm.setString(11, Trastorno);
		stm.executeUpdate();
		cnPersonal.close();
	}

	public static void HojasClinicas(String IDPaciente, String NCita, String Encargado, String NHClinica, String Fecha,
			String Pruebas, String Resultado) throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm1 = cnPersonal.prepareStatement(
				"insert into HojasClinicas (IDPaciente,NCita,NHClinica,Encargado,Fecha,Pruebas,Resultados) values (?,?,?,?,?,?,?)");
		stm1.setString(1, IDPaciente);
		stm1.setString(2, NCita);
		stm1.setString(3, NHClinica);
		stm1.setString(4, Encargado);
		stm1.setString(5, Fecha);
		stm1.setString(6, Pruebas);
		stm1.setString(7, Resultado);
		stm1.executeUpdate();
		cnPersonal.close();
	}

	public static void LlenarTablaCita(DefaultTableModel listaCitas, String NDoctor) throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		for (int i = 0; i < listaCitas.getRowCount(); i++) {
			listaCitas.removeRow(i);
			i -= 1;
		}

		String p1 = "no";
		String p2 = "no";
		PreparedStatement stm = cnPersonal.prepareStatement("select * from Cita where NDoctor=?");
		stm.setString(1, NDoctor);
		ResultSet rst = stm.executeQuery();
		PreparedStatement stm1 = cnPersonal.prepareStatement("select NCita from PacienteDAsistencial where NCita = ?");
		PreparedStatement stm2 = cnPersonal.prepareStatement("select NCita from HojasClinicas where NCita = ?");
		while (rst.next()) {
			stm1.setString(1, rst.getString("NCita"));
			stm2.setString(1, rst.getString("NCita"));
			ResultSet rst1 = stm1.executeQuery();
			ResultSet rst2 = stm2.executeQuery();
			while (rst1.next()) {
				if (rst.getString("NCita").equals(rst1.getString("NCita")))
					p1 = "si";
			}
			while (rst2.next()) {
				if (rst.getString("NCita").equals(rst2.getString("NCita")))
					p2 = "si";
			}
			listaCitas.addRow(new Object[] { rst.getString("NCita"), rst.getString("Paciente"), rst.getString("Doctor"),
					rst.getString("Fecha"), rst.getString("Especialidad"), rst.getString("Motivo"), p1 + "/" + p2 });
		}
		cnPersonal.close();
	}

	public static void buscarTablaCita(DefaultTableModel listaCitas, String NDoctor, String Cita) throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		for (int i = 0; i < listaCitas.getRowCount(); i++) {
			listaCitas.removeRow(i);
			i -= 1;
		}

		String p1 = "no";
		String p2 = "no";
		PreparedStatement stm = cnPersonal.prepareStatement("select * from Cita where NDoctor=? and NCita=?");
		stm.setString(1, NDoctor);
		stm.setString(2, Cita);
		ResultSet rst = stm.executeQuery();
		PreparedStatement stm1 = cnPersonal.prepareStatement("select NCita from PacienteDAsistencial where NCita = ?");
		PreparedStatement stm2 = cnPersonal.prepareStatement("select NCita from HojasClinicas where NCita = ?");
		while (rst.next()) {
			stm1.setString(1, rst.getString("NCita"));
			stm2.setString(1, rst.getString("NCita"));
			ResultSet rst1 = stm1.executeQuery();
			ResultSet rst2 = stm2.executeQuery();
			while (rst1.next()) {
				if (rst.getString("NCita").equals(rst1.getString("NCita")))
					p1 = "si";
			}
			while (rst2.next()) {
				if (rst.getString("NCita").equals(rst2.getString("NCita")))
					p2 = "si";
			}
			listaCitas.addRow(new Object[] { rst.getString("NCita"), rst.getString("Paciente"), rst.getString("Doctor"),
					rst.getString("Fecha"), rst.getString("Especialidad"), rst.getString("Motivo"), p1 + "/" + p2 });
		}
		cnPersonal.close();
	}

	public static void ActualizarBDDAsist(String NCita, float Peso, float Talla, float Pulso, int RitmoCardiaco,
			String DiagnosticoPrincipal, String Medicinas, Date Fecha, String Antecedentes, String Trastorno)
			throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement(
				"update PacienteDAsistencial set Peso=?,Talla=?,Pulso=?,RitmoCardiaco=?,DiagnosticoPrincipal=?,Medicinas=?,Fecha=?,Antecedentes=?,Trastorno=? where NCita=?");
		stm.setFloat(1, Peso);
		stm.setFloat(2, Talla);
		stm.setFloat(3, Pulso);
		stm.setInt(4, RitmoCardiaco);
		stm.setString(5, DiagnosticoPrincipal);
		stm.setString(6, Medicinas);
		stm.setDate(7, Fecha);
		stm.setString(8, Antecedentes);
		stm.setString(9, Trastorno);
		stm.setString(10, NCita);
		stm.executeUpdate();
		cnPersonal.close();
	}

	public static void ActualizarHClinica(String NHClinica, String Fecha, String Pruebas, String Resultados)
			throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal
				.prepareStatement("update HojasClinicas set Fecha=?,Pruebas=?,Resultados=? where NHClinica=?");
		stm.setString(1, Fecha);
		stm.setString(2, Pruebas);
		stm.setString(3, Resultados);
		stm.setString(4, NHClinica);
		stm.executeUpdate();
		cnPersonal.close();
	}

	/* paciente */
	public static void llenarTablaPacientes(DefaultTableModel modelo, String IDDoctor) throws SQLException {
		for (int i = 0; i < modelo.getRowCount(); i++) {
			modelo.removeRow(i);
			i -= 1;
		}
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement("select * from PacienteDGeneral where IDPaciente=?");
		PreparedStatement stm2 = cnPersonal
				.prepareStatement("select NPaciente from PacienteDHospitalizado where NDoctor=?");
		stm2.setString(1, IDDoctor);
		ResultSet rst2 = stm2.executeQuery();
		while (rst2.next()) {
			stm.setString(1, rst2.getString("NPaciente"));
			ResultSet rst = stm.executeQuery();
			while (rst.next()) {
				modelo.addRow(new Object[] { rst.getString("IDPaciente"), rst.getString("Nombre"),
						rst.getString("Apellido"), rst.getString("Edad"), rst.getString("Documento"),
						rst.getString("Seco"), rst.getString("GSanguineo"), "Hospitalizado" });
			}
		}
		PreparedStatement stm3 = cnPersonal.prepareStatement("select distinct NPaciente from Cita where NDoctor=?");
		stm3.setString(1, IDDoctor);
		ResultSet rst3 = stm3.executeQuery();
		while (rst3.next()) {
			stm.setString(1, rst3.getString("NPaciente"));
			ResultSet rst = stm.executeQuery();
			while (rst.next()) {
				modelo.addRow(new Object[] { rst.getString("IDPaciente"), rst.getString("Nombre"),
						rst.getString("Apellido"), rst.getString("Edad"), rst.getString("Documento"),
						rst.getString("Seco"), rst.getString("GSanguineo"), "Cita" });
			}
		}
	}

	public static void buscarTablaPaciente(DefaultTableModel modelo, String IDDoctor, String Paciente)
			throws SQLException {
		for (int i = 0; i < modelo.getRowCount(); i++) {
			modelo.removeRow(i);
			i -= 1;
		}
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement("select * from PacienteDGeneral where IDPaciente=?");
		PreparedStatement stm2 = cnPersonal
				.prepareStatement("select NPaciente from PacienteDHospitalizado where NDoctor=? and Paciente=?");
		stm2.setString(1, IDDoctor);
		stm2.setString(2, Paciente);
		ResultSet rst2 = stm2.executeQuery();
		while (rst2.next()) {
			stm.setString(1, rst2.getString("NPaciente"));
			ResultSet rst = stm.executeQuery();
			while (rst.next()) {
				modelo.addRow(new Object[] { rst.getString("IDPaciente"), rst.getString("Nombre"),
						rst.getString("Apellido"), rst.getString("Edad"), rst.getString("Documento"),
						rst.getString("Seco"), rst.getString("GSanguineo"), "Cita" });
			}
		}
		PreparedStatement stm3 = cnPersonal
				.prepareStatement("select NPaciente from Cita where NDoctor=? and NPaciente=?");
		stm3.setString(1, IDDoctor);
		stm3.setString(2, Paciente);
		ResultSet rst3 = stm3.executeQuery();
		while (rst3.next()) {
			stm.setString(1, rst3.getString("NPaciente"));
			ResultSet rst = stm.executeQuery();
			while (rst.next()) {
				modelo.addRow(new Object[] { rst.getString("IDPaciente"), rst.getString("Nombre"),
						rst.getString("Apellido"), rst.getString("Edad"), rst.getString("Documento"),
						rst.getString("Seco"), rst.getString("GSanguineo"), "Hospitalizado" });
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void LlenarBuscarPaciente(String IDPaciente, java.util.Date fechaN) throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement("select * from PacienteDGeneral where IDPaciente=?");
		stm.setString(1, IDPaciente);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			PerMedPaciente.tFGDataNombre.setText(rst.getString("Nombre"));
			PerMedPaciente.tFGDataApellido.setText(rst.getString("Apellido"));
			PerMedPaciente.cBgdatatipodoc.removeAllElements();
			PerMedPaciente.cBgdatatipodoc.addElement(rst.getString("TipoDoc"));
			PerMedPaciente.cBgdatatipodoc.setSelectedItem(rst.getString("TipoDoc"));
			PerMedPaciente.tFGDataDocoumento.setText(rst.getInt("Documento") + "");
			PerMedPaciente.cBgdatasexo.removeAllElements();
			PerMedPaciente.cBgdatasexo.addElement(rst.getString("Seco"));
			PerMedPaciente.cBgdatasexo.setSelectedItem(rst.getString("Seco"));
			PerMedPaciente.cBgdataeingreso.removeAllElements();
			PerMedPaciente.cBgdataeingreso.addElement(rst.getString("EIngreso"));
			PerMedPaciente.cBgdataeingreso.setSelectedItem(rst.getString("EIngreso"));
			PerMedPaciente.sGDataEdad.setValue(rst.getInt("Edad"));
			PerMedPaciente.tFGDataTIngreso.setText(rst.getString("GSanguineo"));
			try {
				fechaN = new SimpleDateFormat("yyy-MM-dd").parse(rst.getString("FNaciminto"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			PerMedPaciente.cGDataCalendario.setDate(fechaN);
			PerMedPaciente.cBgdatamotivo.removeAllElements();
			PerMedPaciente.cBgdatamotivo.addElement(rst.getString("Motivo"));
			PerMedPaciente.cBgdatamotivo.setSelectedItem(rst.getString("Motivo"));
			PerMedPaciente.tFGDataAlergias.setText(rst.getString("Alergia"));
			PerMedPaciente.tFGDataObs.setText(rst.getString("Observaciones"));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void LlenarListaCitas(String IDPaciente, String IDPersonal, DefaultComboBoxModel citas)
			throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		citas.removeAllElements();
		citas.addElement("-Seleccione-");
		PreparedStatement stm = cnPersonal.prepareStatement("select * from Cita where NPaciente=? and NDoctor=?");
		stm.setString(1, IDPaciente);
		stm.setString(2, IDPersonal);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			citas.addElement(rst.getString("NCita"));
		}
	}

	@SuppressWarnings("unchecked")
	public static void LlenarCita(String NCita)
			throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement("select * from Cita where NCita=?");
		stm.setString(1, NCita);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			PerMedPaciente.tFCDataMotivo.setText(rst.getString("Motivo"));
			stm = cnPersonal.prepareStatement("select convert(varchar,Fecha,105) as fecha from Cita where NCita=?");
			stm.setString(1, NCita);
			ResultSet rst9 = stm.executeQuery();
			while (rst9.next()) {
				String dateValue = rst9.getString("fecha");
				try {
					java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
					PerMedPaciente.cCDataCalendar.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			PerMedPaciente.cCDataCalendar.setEnabled(false);
			PerMedPaciente.spinner.setValue(rst.getInt("Habitacion"));
			PerMedPaciente.spinner_1.setValue(rst.getInt("Piso"));
		}
		PreparedStatement stm1 = cnPersonal.prepareStatement("select * from PacienteDAsistencial where NCita=?");
		stm1.setString(1, NCita);
		ResultSet rst1 = stm1.executeQuery();
		while (rst1.next()) {
			PerMedPaciente.tFADataPeso.setText(rst1.getFloat("Peso") + "");
			PerMedPaciente.tFADataTalla.setText(rst1.getFloat("Talla") + "");
			PerMedPaciente.tFADataPulso.setText(rst1.getFloat("Pulso") + "");
			PerMedPaciente.tFADataRCardiaco.setText(rst1.getInt("RitmoCardiaco") + "");
			PerMedPaciente.tFADataDiagPrincipal.setText(rst1.getString("DiagnosticoPrincipal"));
			PerMedPaciente.tFADataMedicinas.setText(rst1.getString("Medicinas"));
			stm = cnPersonal.prepareStatement("select convert(varchar,Fecha,105) as fecha from PacienteDAsistencial where NCita=?");
			stm.setString(1, NCita);
			ResultSet rst9 = stm.executeQuery();
			while (rst9.next()) {
				String dateValue = rst9.getString("fecha");
				try {
					java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
					PerMedPaciente.dCADataFecha.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			PerMedPaciente.tFADataAntecedentes.setText(rst1.getString("Antecedentes"));
			PerMedPaciente.tFADataTrastorno.setText(rst1.getString("Trastorno"));
		}
		PreparedStatement stm2 = cnPersonal.prepareStatement("select * from HojasClinicas where NCita=?");
		stm2.setString(1, NCita);
		ResultSet rst2 = stm2.executeQuery();
		PerMedPaciente.cBdclinicaencargado.removeAllElements();
		PerMedPaciente.cBdclinicaencargado.addElement("-Seleccione-");
		while (rst2.next()) {
			PerMedPaciente.tFDClinicaNHClinica.setText(rst2.getString("NHClinica"));
			PerMedPaciente.cBdclinicaencargado.addElement(rst2.getString("Encargado"));
			PerMedPaciente.cBdclinicaencargado.setSelectedItem(rst2.getString("Encargado"));
			stm = cnPersonal.prepareStatement("select convert(varchar,Fecha,105) as fecha from HojasClinicas where NCita=?");
			stm.setString(1, NCita);
			ResultSet rst3 = stm.executeQuery();
			while (rst3.next()) {
				String dateValue = rst3.getString("fecha");
				try {
					java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
					PerMedPaciente.dCDClinicaFecha.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			PerMedPaciente.tFDClinicaPruebas.setText(rst2.getString("Pruebas"));
			PerMedPaciente.tFDClinicaResultados.setText(rst2.getString("Resultados"));
		}
	}

	public static void llenarInternados(String NPaciente, java.util.Date fecha) throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement("select * from PacienteDHospitalizado where NPaciente=?");
		stm.setString(1, NPaciente);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			PerMedPaciente.textField.setText(rst.getString("Motivo"));
			stm = cnPersonal.prepareStatement(
					"select convert(varchar,Fecha,105) as fecha from PacienteDHospitalizado where NPaciente=?");
			stm.setString(1, NPaciente);
			ResultSet rst3 = stm.executeQuery();
			while (rst3.next()) {
				String dateValue = rst3.getString("fecha");
				try {
					java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
					PerMedPaciente.cIDataCalendario.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			PerMedPaciente.sIDataHabitacion.setValue(rst.getInt("Habitacion"));
			PerMedPaciente.sIDataPiso.setValue(rst.getInt("Piso"));
			PerMedPaciente.btnIDataHClinica.setEnabled(true);
			PerMedPaciente.btnIDataHTraslada.setEnabled(true);
			PerMedPaciente.btnIDataHAlta.setEnabled(true);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String CodigoAleatorio(DefaultComboBoxModel encargado, String IDPersonal) throws SQLException {
		String iden = "PAC-";
		int i = 1;
		String codigo = iden + "000" + i;
		Connection cnRecepcionista = CnRecepcionista.getConnection();
		Statement stm = cnRecepcionista.createStatement();
		ResultSet rst = stm.executeQuery("select NHClinica from HojasClinicas");
		while (rst.next()) {
			if (codigo.equals(rst.getString("NHClinica"))) {
				i++;
				if (i <= 9) {
					codigo = iden + "000" + i;
				} else if (i <= 99) {
					codigo = iden + "00" + i;
				} else if (i <= 999) {
					codigo = iden + "0" + i;
				} else {
					codigo = iden + i;
				}
			}
		}
		cnRecepcionista.close();
		encargado.removeAllElements();
		encargado.addElement(IDPersonal);
		encargado.setSelectedItem(IDPersonal);
		return codigo;
	}

	public static void mandarBDHojaClinica(String NHClinica, String IDPaciente, String Encargado, Date Fecha,
			String Pruebas, String Resultados) throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement(
				"insert into HojasClinicas (NHClinica,IDPaciente,Encargado,Fecha,Pruebas,Resultados) values (?,?,?,?,?,?)");
		stm.setString(1, NHClinica);
		stm.setString(2, IDPaciente);
		stm.setString(3, Encargado);
		stm.setDate(4, Fecha);
		stm.setString(5, Pruebas);
		stm.setString(6, Resultados);
		stm.executeUpdate();
		cnPersonal.close();
	}

	@SuppressWarnings("unchecked")
	public static void llenarBuscarHClinica(String NHClinica) throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement("select * from HojasClinicas where NHClinica=?");
		stm.setString(1, NHClinica);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			PerMedPacienteHojaClinica.cBbuscarencargado.removeAllElements();
			PerMedPacienteHojaClinica.cBbuscarencargado.addElement(rst.getString("Encargado"));
			PerMedPacienteHojaClinica.cBbuscarencargado.setSelectedItem(rst.getString("Encargado"));
			stm = cnPersonal.prepareStatement(
					"select convert(varchar,Fecha,105) as fecha from HojasClinicas where NHClinica=?");
			stm.setString(1, NHClinica);
			ResultSet rst2 = stm.executeQuery();
			while (rst2.next()) {
				String dateValue = rst2.getString("fecha");
				try {
					java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
					PerMedPacienteHojaClinica.dCBuscarFecha.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			PerMedPacienteHojaClinica.tFBuscarPrueba.setText(rst.getString("Pruebas"));
			PerMedPacienteHojaClinica.tFBuscarResultado.setText(rst.getString("Resultados"));
		}
		cnPersonal.close();
	}

	public static void LlenarTablaHClinicas(DefaultTableModel lista, String IDPaciente) throws SQLException {
		for (int i = 0; i < lista.getRowCount(); i++) {
			lista.removeRow(i);
			i -= 1;
		}
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement("select * from HojasClinicas where IDPaciente=?");
		stm.setString(1, IDPaciente);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			lista.addRow(new Object[] { rst.getString("NHClinica"), rst.getString("IDPaciente"),
					rst.getString("Encargado"), rst.getString("Fecha"), rst.getString("Resultados") });
		}
	}

	public static void buscarTablaHClinica(DefaultTableModel lista, String IDPaciente, String HClinica)
			throws SQLException {
		for (int i = 0; i < lista.getRowCount(); i++) {
			lista.removeRow(i);
			i -= 1;
		}
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal
				.prepareStatement("select * from HojasClinicas where IDPaciente=? and NHClinica=?");
		stm.setString(1, IDPaciente);
		stm.setString(2, HClinica);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			lista.addRow(new Object[] { rst.getString("NHClinica"), rst.getString("IDPaciente"),
					rst.getString("Encargado"), rst.getString("Fecha"), rst.getString("Resultados") });
		}
	}

	public static void ActualizaBDHojasClinicas(String NHClinica, String IDPaciente, String Encargado, Date sqlDate,
			String Pruebas, String Resultados) throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement(
				"update HojasClinicas set IDPaciente=?,Encargado=?,Fecha=?,Pruebas=?,Resultados=? where NHClinica=?");
		stm.setString(6, NHClinica);
		stm.setString(1, IDPaciente);
		stm.setString(2, Encargado);
		stm.setDate(3, sqlDate);
		stm.setString(4, Pruebas);
		stm.setString(5, Resultados);
		stm.executeUpdate();
	}

	/* Hoja Traslado */

	public static void NuevaHOjaTraslado(String NHTraslado, String IDPaciente, String Motivo, Date Fecha, String Doctor,
			String Observacion) throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement(
				"insert into HojasTraslado (NHTraslado,IDPaciente,Motivo,Fecha,Doctor,Observacion) values (?,?,?,?,?,?)");
		stm.setString(1, NHTraslado);
		stm.setString(2, IDPaciente);
		stm.setString(3, Motivo);
		stm.setDate(4, Fecha);
		stm.setString(5, Doctor);
		stm.setString(6, Observacion);
		stm.executeUpdate();
		PreparedStatement stm2 = cnPersonal
				.prepareStatement("update PacienteDGeneral set EIngreso=? where IDPaciente=?");
		stm2.setString(1, "Trasladado");
		stm2.setString(2, IDPaciente);
		stm2.executeUpdate();
	}

	public static String CodigoAleatorioTraslado() throws SQLException {
		String iden = "PAT-";
		int i = 1;
		String codigo = iden + "000" + i;
		Connection cnRecepcionista = CnRecepcionista.getConnection();
		Statement stm = cnRecepcionista.createStatement();
		ResultSet rst = stm.executeQuery("select NHTraslado from HojasTraslado");
		while (rst.next()) {
			if (codigo.equals(rst.getString("NHTraslado"))) {
				i++;
				if (i <= 9) {
					codigo = iden + "000" + i;
				} else if (i <= 99) {
					codigo = iden + "00" + i;
				} else if (i <= 999) {
					codigo = iden + "0" + i;
				} else {
					codigo = iden + i;
				}
			}
		}
		return codigo;
	}

	public static void LlenarTablaTraslado(DefaultTableModel lista, String Doctor) throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement("select * from HojasTraslado where Doctor = ?");
		stm.setString(1, Doctor);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			lista.addRow(new Object[] { rst.getString("NHTraslado"), rst.getString("Doctor"),
					rst.getString("IDPaciente"), rst.getString("Fecha"), rst.getString("Motivo") });
		}
	}

	public static void buscarTablaHTraslado(DefaultTableModel lista, String Doctor, String HTraslado)
			throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal
				.prepareStatement("select * from HojasTraslado where Doctor = ? and NHTraslado=?");
		stm.setString(1, Doctor);
		stm.setString(2, HTraslado);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			lista.addRow(new Object[] { rst.getString("NHTraslado"), rst.getString("Doctor"),
					rst.getString("IDPaciente"), rst.getString("Fecha"), rst.getString("Motivo") });
		}
	}

	/* Hoja Alta */
	public static void EnviarBDHojaAlta(String NHAlta, String IDPaciente, Date Fecha, String Doctor, String Motivo)
			throws SQLException {
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal
				.prepareStatement("insert into HojasAlta (NHAlta,IDPaciente,Fecha,Doctor,Motivo) values (?,?,?,?,?)");
		stm.setString(1, NHAlta);
		stm.setString(2, IDPaciente);
		stm.setDate(3, Fecha);
		stm.setString(4, Doctor);
		stm.setString(5, Motivo);
		stm.executeUpdate();
		PreparedStatement stm2 = cnPersonal
				.prepareStatement("update PacienteDGeneral set EIngreso=? where IDPaciente=?");
		stm2.setString(1, "Registrado");
		stm2.setString(2, IDPaciente);
		stm2.executeUpdate();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String CodigoAleatorioAlta(DefaultComboBoxModel doctor, String Doctor) throws SQLException {
		String iden = "PAA-";
		int i = 1;
		String codigo = iden + "000" + i;
		Connection cnPersonal = CnRecepcionista.getConnection();
		Statement stm = cnPersonal.createStatement();
		ResultSet rst = stm.executeQuery("select NHAlta from HojasAlta");
		while (rst.next()) {
			if (codigo.equals(rst.getString("NHAlta"))) {
				i++;
				if (i <= 9) {
					codigo = iden + "000" + i;
				} else if (i <= 99) {
					codigo = iden + "00" + i;
				} else if (i <= 999) {
					codigo = iden + "0" + i;
				} else {
					codigo = iden + i;
				}
			}
		}
		doctor.removeAllElements();
		doctor.addElement(Doctor);
		doctor.setSelectedItem(Doctor);
		cnPersonal.close();
		return codigo;
	}

	public static void LlenarTablaAlta(DefaultTableModel lista, String IDPaciente) throws SQLException {
		for (int i = 0; i < lista.getRowCount(); i++) {
			lista.removeRow(i);
			i -= 1;
		}
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement("select * from HojasAlta where IDPaciente=?");
		stm.setString(1, IDPaciente);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			lista.addRow(new Object[] { rst.getString("NHAlta"), rst.getString("Doctor"), rst.getString("IDPaciente"),
					rst.getString("Fecha"), rst.getString("Motivo") });
		}
		cnPersonal.close();
	}

	public static void buscarTablaHAlta(DefaultTableModel lista, String IDPaciente, String HAlta) throws SQLException {
		for (int i = 0; i < lista.getRowCount(); i++) {
			lista.removeRow(i);
			i -= 1;
		}
		Connection cnPersonal = CnPersonal.getConnection();
		PreparedStatement stm = cnPersonal.prepareStatement("select * from HojasAlta where IDPaciente=? and NHAlta=?");
		stm.setString(1, IDPaciente);
		stm.setString(2, HAlta);
		ResultSet rst = stm.executeQuery();
		while (rst.next()) {
			lista.addRow(new Object[] { rst.getString("NHAlta"), rst.getString("Doctor"), rst.getString("IDPaciente"),
					rst.getString("Fecha"), rst.getString("Motivo") });
		}
		cnPersonal.close();
	}
}