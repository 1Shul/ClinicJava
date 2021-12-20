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

import suing.RecepCitas;
import suing.RecepPaciente;

public class CnRecepcionista {
	private static Connection cn;
	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cn=DriverManager.getConnection("jdbc:sqlserver://localhost:55766;databaseName=ProyectoFinal","sa","123");
		}
		catch (Exception e) {
			cn=null;	
		}
		return cn;
	}
	
	public static String CodigoAleatorio() throws SQLException {
		String iden="PAP-";
		int i=1;
		String codigo=iden+"000"+i;
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		Statement stm = cnRecepcionista.createStatement();
		ResultSet rst = stm.executeQuery("select IDPaciente from PacienteDGeneral");
		while(rst.next())
		{
			if(codigo.equals(rst.getString("IDPaciente")))
			{
				i++;
				if(i<=9)
				{
					codigo=iden+"000"+i;
				}
				else if(i<=99)
				{
					codigo=iden+"00"+i;
				}
				else if(i<=999)
				{
					codigo=iden+"0"+i;
				}
				else
				{
					codigo=iden+i;
				}
			}
		}
		cnRecepcionista.close();
		return codigo;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DefaultComboBoxModel LlenarPacientes(DefaultComboBoxModel modelo) throws SQLException {
		modelo.removeAllElements();
		modelo.addElement("-Seleccione-");
		
		String pacienteichon;
		Connection cnRecepcionista = CnRecepcionista.getConnection();
		PreparedStatement stm = cnRecepcionista.prepareStatement("select IDPaciente,Nombre,Apellido from PacienteDGeneral");
		ResultSet rst = stm.executeQuery();
		while(rst.next())
		{
			pacienteichon=rst.getString("IDPaciente")+" - "+rst.getString("Nombre")+" "+rst.getString("Apellido");
			modelo.addElement(pacienteichon);
			
		}
		cnRecepcionista.close();
		return modelo;
	}
	
	public static void EnviarBDPacienteDAsistencial(String IDPaciente,String Nombre,String Apellido, String TipoDoc, int Documento, String Genero, String EIngreso, int Edad, String GSanguineo, Date FNacimiento, String Motivo,String Alergia, String ProcQuirurgico, String Observaciones) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		PreparedStatement stm=cnRecepcionista.prepareStatement("insert into PacienteDGeneral (IDPaciente,Nombre,Apellido,TipoDoc,Documento,Seco,EIngreso,Edad,GSanguineo,FNaciminto,Motivo,Alergia,ProcQuirurgico,Observaciones) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		stm.setString(1, IDPaciente);
		stm.setString(2, Nombre);
		stm.setString(3, Apellido);
		stm.setString(4,TipoDoc);
		stm.setInt(5, Documento);
		stm.setString(6, Genero);
		stm.setString(7, EIngreso);
		stm.setInt(8, Edad);
		stm.setString(9, GSanguineo);
		stm.setDate(10, FNacimiento);
		stm.setString(11, Motivo);
		stm.setString(12, Alergia);
		stm.setString(13, ProcQuirurgico);
		stm.setString(14, Observaciones);
		stm.executeUpdate();
		cnRecepcionista.close();
	}

	public static void RellenarBuscarPaciente(String IDPaciente) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		PreparedStatement stm=cnRecepcionista.prepareStatement("select * from PacienteDGeneral where IDPaciente=?");
		stm.setString(1, IDPaciente);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			RecepPaciente.tFBuscarNombre.setText(rst.getString("Nombre"));
			RecepPaciente.tFBuscarApellido.setText(rst.getString("Apellido"));
			RecepPaciente.TipoDoc=rst.getString("TipoDoc");
			RecepPaciente.tFBuscarDocumento.setText(rst.getString("Documento"));
			RecepPaciente.Genero=rst.getString("Seco");
			RecepPaciente.EIngreso=rst.getString("EIngreso");
			RecepPaciente.sBuscarEdad.setValue(rst.getInt("Edad"));
			RecepPaciente.tFBuscarGSanguineo.setText(rst.getString("GSanguineo"));
			stm=cnRecepcionista.prepareStatement("select convert(varchar,FNaciminto,105) as fecha from PacienteDGeneral where IDPaciente=?");
			stm.setString(1, IDPaciente);
			ResultSet rst2=stm.executeQuery();
			while(rst2.next()) {
				String dateValue=rst2.getString("fecha");
				try {
					java.util.Date date=new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
					RecepPaciente.cBuscarCalendario.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			}
			RecepPaciente.Motivo=rst.getString("Motivo");
			RecepPaciente.tFBuscarAlergia.setText(rst.getString("Alergia"));
			RecepPaciente.tFBuscarObs.setText(rst.getString("Observaciones"));
		}
		cnRecepcionista.close();
	}
	
	public static void ActualizarBDPacienteGDatos(String IDPaciente,String Nombre,String Apellido, String TipoDoc, int Documento, String Genero, String EIngreso, int Edad, String GSanguineo, Date FNacimiento, String Motivo,String Alergia, String Observaciones) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		PreparedStatement stm=cnRecepcionista.prepareStatement("update PacienteDGeneral set Nombre=?,Apellido=?,TipoDoc=?,Documento=?,Seco=?,EIngreso=?,Edad=?,GSanguineo=?,FNaciminto=?,Motivo=?,Alergia=?,Observaciones=? where IDPaciente=?");
		stm.setString(1, Nombre);
		stm.setString(2, Apellido);
		stm.setString(3,TipoDoc);
		stm.setInt(4, Documento);
		stm.setString(5, Genero);
		stm.setString(6, EIngreso);
		stm.setInt(7, Edad);
		stm.setString(8, GSanguineo);
		stm.setDate(9, FNacimiento);
		stm.setString(10, Motivo);
		stm.setString(11, Alergia);
		stm.setString(12, Observaciones);
		stm.setString(13, IDPaciente);
		stm.executeUpdate();
		cnRecepcionista.close();
	}
	
	public static void EliminarDBDPacienteGDatos(String IDPaciente) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		PreparedStatement stm=cnRecepcionista.prepareStatement("delete from PacienteDGeneral where IDPaciente = ?");
		stm.setString(1, IDPaciente);
		stm.executeUpdate();
		stm=cnRecepcionista.prepareStatement("delete from PacienteDHospitalizado where NPaciente = ?");
		stm.setString(1, IDPaciente);
		stm.executeUpdate();
		stm=cnRecepcionista.prepareStatement("delete from PacienteDAsistencial where IDPaciente = ?");
		stm.setString(1, IDPaciente);
		stm.executeUpdate();
		cnRecepcionista.close();
	}
	
	public static void LlenarTablaPaciente(DefaultTableModel model) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		for (int i = 0; i < model.getRowCount(); i++)
		{
			model.removeRow(i);
			i-=1;
		}
		Statement stm=cnRecepcionista.createStatement();
		ResultSet rst=stm.executeQuery("select * from PacienteDGeneral");
		while(rst.next()) 
		{
			model.addRow(new Object[] {rst.getString("IDPaciente"),rst.getString("Nombre"),rst.getString("Apellido"),rst.getInt("Edad"),rst.getString("Seco"),rst.getString("Documento"),rst.getString("GSanguineo"),rst.getString("Motivo"),rst.getString("EIngreso")});
		}
		cnRecepcionista.close();
	}
	
	public static void buscarTablaPaciente(DefaultTableModel model,String IDPaciente) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		for (int i = 0; i < model.getRowCount(); i++)
		{
			model.removeRow(i);
			i-=1;
		}
		PreparedStatement stm=cnRecepcionista.prepareStatement("select * from PacienteDGeneral where IDPAciente=?");
		stm.setString(1, IDPaciente);
		ResultSet rst=stm.executeQuery();
		while(rst.next()) 
		{
			model.addRow(new Object[] {rst.getString("IDPaciente"),rst.getString("Nombre"),rst.getString("Apellido"),rst.getInt("Edad"),rst.getString("Seco"),rst.getString("Documento"),rst.getString("GSanguineo"),rst.getString("Motivo"),rst.getString("EIngreso")});
		}
		cnRecepcionista.close();
	}
	
	/*METODOS CITA*/
	
	public static String CodigoAleatorioCita() throws SQLException {
		String iden="PAD-";
		int i=1;
		String codigo=iden+"000"+i;
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		Statement stm = cnRecepcionista.createStatement();
		ResultSet rst = stm.executeQuery("select NCita from Cita");
		while(rst.next())
		{
			if(codigo.equals(rst.getString("NCita")))
			{
				i++;
				if(i<=9)
				{
					codigo=iden+"000"+i;
				}
				else if(i<=99)
				{
					codigo=iden+"00"+i;
				}
				else if(i<=999)
				{
					codigo=iden+"0"+i;
				}
				else
				{
					codigo=iden+i;
				}
			}
		}
		cnRecepcionista.close();
		return codigo;
	}
	
	public static void RegistrarCita(String NCita,String NPaciente,String Motivo, String Especialidad, String NDoctor, Date Fecha, int Habitacion, int Piso) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		String Paciente=null,Doctor=null;
		PreparedStatement stm2=cnRecepcionista.prepareStatement("select Nombre,Apellido from PacienteDGeneral where IDPaciente=?");
		stm2.setString(1, NPaciente);
		ResultSet rst2=stm2.executeQuery();
		while(rst2.next())
		{
			Paciente=rst2.getString("Nombre")+" "+rst2.getString("Apellido");
		}
		PreparedStatement stm3=cnRecepcionista.prepareStatement("select Nombre,Apellido from Personal where IDPersonal=?");
		stm3.setString(1, NDoctor);
		ResultSet rst3=stm3.executeQuery();
		while(rst3.next())
		{
			Doctor=rst3.getString("Nombre")+" "+rst3.getString("Apellido");
		}
		PreparedStatement stm=cnRecepcionista.prepareStatement("insert into Cita (NCita,NPaciente,Motivo,Especialidad,NDoctor,Fecha,Habitacion,Piso,Paciente,Doctor) values (?,?,?,?,?,?,?,?,?,?)");
		stm.setString(1, NCita);
		stm.setString(2, NPaciente);
		stm.setString(3, Motivo);
		stm.setString(4, Especialidad);
		stm.setString(5, NDoctor);
		stm.setDate(6, Fecha);
		stm.setInt(7, Habitacion);
		stm.setInt(8, Piso);
		stm.setString(9, Paciente);
		stm.setString(10, Doctor);
		stm.executeUpdate();
		cnRecepcionista.close();
	}
	
	public static void LlenarBuscarCita(String NCita) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		PreparedStatement stm=cnRecepcionista.prepareStatement("select * from Cita where NCita=?");
		stm.setString(1, NCita);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			
			for(int i =0;i<RecepCitas.cBBuscarPaciente.getItemCount();i++) {
	            if (RecepCitas.cBBuscarPaciente.getItemAt(i).toString().startsWith(rst.getString("NPaciente"))) {
	            	RecepCitas.cBBuscarPaciente.setSelectedIndex(i);
	            }
			}
			RecepCitas.tFBuscarMotivo.setText(rst.getString("Motivo"));
			RecepCitas.Especialidad=rst.getString("Especialidad");
			RecepCitas.Doctor=rst.getString("NDoctor");
			stm=cnRecepcionista.prepareStatement("select convert(varchar,Fecha,105) as fecha from Cita where NCita=?");
			stm.setString(1, NCita);
			ResultSet rst2=stm.executeQuery();
			while(rst2.next()) {
				String dateValue=rst2.getString("fecha");
				try {
					java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
					RecepCitas.cBuscarCalendario.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			}
			RecepCitas.Fecha=rst.getString("Fecha");
			RecepCitas.tFBuscarHabitacion.setText(rst.getString("Habitacion"));
			RecepCitas.tFBuscarPiso.setText(rst.getString("Piso"));
		}
		cnRecepcionista.close();
	}
	
	public static void UpdateCita(String NCita,String NPaciente,String Motivo, String Especialidad, String NDoctor, Date Fecha, int Habitacion, int Piso) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		String Paciente=null,Doctor=null;
		PreparedStatement stm2=cnRecepcionista.prepareStatement("select Nombre,Apellido from PacienteDGeneral where IDPaciente=?");
		stm2.setString(1, NPaciente);
		ResultSet rst2=stm2.executeQuery();
		while(rst2.next())
		{
			Paciente=rst2.getString("Nombre")+" "+rst2.getString("Apellido");
		}
		PreparedStatement stm3=cnRecepcionista.prepareStatement("select Nombre,Apellido from Personal where IDPersonal=?");
		stm3.setString(1, NDoctor);
		ResultSet rst3=stm3.executeQuery();
		while(rst3.next())
		{
			Doctor=rst3.getString("Nombre")+" "+rst3.getString("Apellido");
		}
		PreparedStatement stm=cnRecepcionista.prepareStatement("update Cita set NPaciente=?,Motivo=?,Especialidad=?,NDoctor=?,Fecha=?,Habitacion=?,Piso=?,Paciente=?,Doctor=? where NCita=?");
		stm.setString(1, NPaciente);
		stm.setString(2, Motivo);
		stm.setString(3, Especialidad);
		stm.setString(4, NDoctor);
		stm.setDate(5, Fecha);
		stm.setInt(6, Habitacion);
		stm.setInt(7, Piso);
		stm.setString(8, Paciente);
		stm.setString(9, Doctor);
		stm.setString(10, NCita);
		stm.executeUpdate();
		cnRecepcionista.close();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void LlenarEspecialidad(DefaultComboBoxModel modelo) throws SQLException {
		modelo.removeAllElements();
		modelo.addElement("-Seleccione-");
		
		String especelialiseichon;
		Connection cnRecepcionista = CnRecepcionista.getConnection();
		Statement stm = cnRecepcionista.createStatement();
		ResultSet rst = stm.executeQuery("select Rspecializacion from Personal");
		
		while(rst.next())
		{
			especelialiseichon=rst.getString("Rspecializacion");
			if(modelo.getIndexOf(especelialiseichon)==-1)
			{
				modelo.addElement(especelialiseichon);
			}
		}
		cnRecepcionista.close();
	}
	
	public static void DeleteCita(String NCita) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		PreparedStatement stm=cnRecepcionista.prepareStatement("delete from Cita where NCita=?");
		stm.setString(1, NCita);
		stm.executeUpdate();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DefaultComboBoxModel LlenarDoctor(DefaultComboBoxModel modelo,String Especialidad) throws SQLException {
		modelo.removeAllElements();
		modelo.addElement("-Seleccione-");
		
		String especelialiseichon;
		Connection cnRecepcionista = CnRecepcionista.getConnection();
		PreparedStatement stm = cnRecepcionista.prepareStatement("select IDPersonal,Nombre,Apellido from Personal where Rspecializacion=?");
		stm.setString(1, Especialidad);
		ResultSet rst = stm.executeQuery();
		
		while(rst.next())
		{
			especelialiseichon=rst.getString("IDPersonal")+" - "+rst.getString("Nombre")+" "+rst.getString("Apellido");
			modelo.addElement(especelialiseichon);
			
		}
		cnRecepcionista.close();
		return modelo;
	}
	
	public static void LlenarTablaCita(DefaultTableModel modelo) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		
		for (int i = 0; i < modelo.getRowCount(); i++)
		{
			modelo.removeRow(i);
			i-=1;
		}
	
		Statement stm=cnRecepcionista.createStatement();
		ResultSet rst=stm.executeQuery("select * from Cita");
		while(rst.next()) {
			modelo.addRow(new Object[] {rst.getString("NCita"),rst.getString("Paciente"),rst.getString("Doctor"),rst.getString("Fecha"),rst.getString("Especialidad"),rst.getString("Motivo")});
		}
		cnRecepcionista.close();
	}
	
	public static void buscarTablaCita(DefaultTableModel modelo,String NCita) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		
		for (int i = 0; i < modelo.getRowCount(); i++)
		{
			modelo.removeRow(i);
			i-=1;
		}
	
		PreparedStatement stm=cnRecepcionista.prepareStatement("select * from Cita where NCita=?");
		ResultSet rst=stm.executeQuery();
		stm.setString(1, NCita);
		while(rst.next()) {
			modelo.addRow(new Object[] {rst.getString("NCita"),rst.getString("Paciente"),rst.getString("Doctor"),rst.getString("Fecha"),rst.getString("Especialidad"),rst.getString("Motivo")});
		}
		cnRecepcionista.close();
	}
	
	public static void EnviarBDPacHospitalizado(String NPaciente, String NDoctor, String Especializacion,String Motivo, Date Fecha, int Habitacion, int Piso) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		PreparedStatement stm=cnRecepcionista.prepareStatement("insert into PacienteDHospitalizado (NPaciente,NDoctor,Especialidad,Motivo,Fecha,Habitacion,Piso) values(?,?,?,?,?,?,?)");
		stm.setString(1, NPaciente);
		stm.setString(2, NDoctor);
		stm.setString(3, Especializacion);
		stm.setString(4, Motivo);
		stm.setDate(5, Fecha);
		stm.setInt(6, Habitacion);
		stm.setInt(7, Piso);
		stm.executeUpdate();
		cnRecepcionista.close();
	}
	
	public static void UpdateBDPacHospitalizado(String NPaciente, String NDoctor, String Especializacion,String Motivo, Date Fecha, int Habitacion, int Piso) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		PreparedStatement stm=cnRecepcionista.prepareStatement("update PacienteDHospitalizado set NDoctor=?,Especialidad=?,Motivo=?,Fecha=?,Habitacion=?,Piso=? where NPaciente=?");
		stm.setString(1, NDoctor);
		stm.setString(2, Especializacion);
		stm.setString(3, Motivo);
		stm.setDate(4, Fecha);
		stm.setInt(5, Habitacion);
		stm.setInt(6, Piso);
		stm.setString(7, NPaciente);
		stm.executeUpdate();
		cnRecepcionista.close();
	}
}