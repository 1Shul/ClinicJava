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

import suing.AdmnCita;
import suing.AdmnPaciente;
import suing.AdmnPacienteHojaAlta;
import suing.AdmnPacienteHojaClinica;
import suing.AdmnPacienteHojaTranslado;
import suing.AdmnPersonal;

public class CnAdministrador {
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
	public String recibir() throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		if (cnAdministrador!=null) {
			System.out.println("Valido");
		}else {
			System.out.println("Invalido");
		}
		return null;
	}
	
	public static String CodigoAleatorio() throws SQLException {
		String iden="PAH-";
		int i=1;
		String codigo=iden+"000"+i;
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		Statement stm = cnRecepcionista.createStatement();
		ResultSet rst = stm.executeQuery("select IDPersonal from Personal");
		while(rst.next())
		{
			if(codigo.equals(rst.getString("IDPersonal")))
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
	
	public static void EnviarBDPersonal(String IDPersonal,String Puesto,String Nombre,String Apellido, int Edad,String Especializacion, String Genero, String GSanguineo,String Direccion,String Provincia,String Distrito, String Telefono, String TipoDoc, int Documento, String ECivil, Date FIngreso,String Estado) throws SQLException {
		Connection cnRecepcionista=CnRecepcionista.getConnection();
		PreparedStatement stm=cnRecepcionista.prepareStatement("insert into Personal (IDPersonal,Puesto,Nombre,Apellido,Edad,Rspecializacion,Genero,GSanguineo,Direccion,Provincia,Distrito,telefono,TipoDocumento,Documento,ECivil,FIngreso,Estado) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		stm.setString(1, IDPersonal);
		stm.setString(2, Puesto);
		stm.setString(3, Nombre);
		stm.setString(4, Apellido);
		stm.setInt(5, Edad);
		stm.setString(6, Especializacion);
		stm.setString(7, Genero);
		stm.setString(8, GSanguineo);
		stm.setString(9, Direccion);
		stm.setString(10, Provincia);
		stm.setString(11, Distrito);
		stm.setString(12,  Telefono);
		stm.setString(13, TipoDoc);
		stm.setInt(14, Documento);
		stm.setString(15, ECivil);
		stm.setDate(16, FIngreso);
		stm.setString(17, Estado);
		stm.executeUpdate();
		cnRecepcionista.close();
	}
	
	public static void RellenarBuscarPaciente(String IDPersonal) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from Personal where IDPersonal=?");
		stm.setString(1, IDPersonal);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			AdmnPersonal.tFIEditNombre.setText(rst.getString("Nombre"));
			AdmnPersonal.tFIEditApellido.setText(rst.getString("Apellido"));
			AdmnPersonal.tFIEditEdad.setText(rst.getString("Edad"));
			AdmnPersonal.tFIEditEspecializacion.setText(rst.getString("Rspecializacion"));
			stm=cnAdministrador.prepareStatement("select Genero from Personal where IDPersonal=?");
			stm.setString(1, IDPersonal);
			ResultSet rst1=stm.executeQuery();
			while(rst1.next()) {
				for(int i=0;i<AdmnPersonal.cBIEditGenero.getItemCount();i++) {
					if(rst1.getString("Genero").equals((String)AdmnPersonal.cBIEditGenero.getItemAt(i))) {
						AdmnPersonal.cBIEditGenero.setSelectedIndex(i);
					}
				}
			}
			stm=cnAdministrador.prepareStatement("select GSanguineo from Personal where IDPersonal=?");
			stm.setString(1, IDPersonal);
			ResultSet rst2=stm.executeQuery();
			while(rst2.next()) {
				for(int i=0;i<AdmnPersonal.cBIEditGSanguineo.getItemCount();i++) {
					if(rst2.getString("GSanguineo").equals((String)AdmnPersonal.cBIEditGSanguineo.getItemAt(i))) {
						AdmnPersonal.cBIEditGSanguineo.setSelectedIndex(i);
					}
				}
			}
			AdmnPersonal.tFIEditDireccion.setText(rst.getString("Direccion"));
			AdmnPersonal.tFIEditProvincia.setText(rst.getString("Provincia"));
			AdmnPersonal.tFIEditDistrito.setText(rst.getString("Distrito"));
			AdmnPersonal.tFIEditTelefono.setText(rst.getString("telefono"));
			stm=cnAdministrador.prepareStatement("select TipoDocumento from Personal where IDPersonal=?");
			stm.setString(1, IDPersonal);
			ResultSet rst3=stm.executeQuery();
			while(rst3.next()) {
				for(int i=0;i<AdmnPersonal.cBIEditDocumento.getItemCount();i++) {
					if(rst3.getString("TipoDocumento").equals((String)AdmnPersonal.cBIEditDocumento.getItemAt(i))) {
						AdmnPersonal.cBIEditDocumento.setSelectedIndex(i);
					}
				}
			}
			AdmnPersonal.tFIEditDocumento.setText(rst.getString("Documento"));
			stm=cnAdministrador.prepareStatement("select ECivil from Personal where IDPersonal=?");
			stm.setString(1, IDPersonal);
			ResultSet rst4=stm.executeQuery();
			while(rst4.next()) {
				for(int i=0;i<AdmnPersonal.cBIEditECivil.getItemCount();i++) {
					if(rst4.getString("ECivil").equals((String)AdmnPersonal.cBIEditECivil.getItemAt(i))) {
						AdmnPersonal.cBIEditECivil.setSelectedIndex(i);
					}
				}
			}
			stm=cnAdministrador.prepareStatement("select convert(varchar,FIngreso,105) as fecha from Personal where IDPersonal=?");
			stm.setString(1, IDPersonal);
			ResultSet rst5=stm.executeQuery();
			while(rst5.next()) {
				String dateValue=rst5.getString("fecha");
				try {
					java.util.Date date=new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
					AdmnPersonal.dCIEditFIngreso.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			}
		}
		PreparedStatement stm6=cnAdministrador.prepareStatement("select IDPersonal,Motivo, convert(varchar,Fecha,105) as fecha from Desempleado where IDPersonal=?");
		stm6.setString(1, IDPersonal);
		ResultSet rst6=stm6.executeQuery();
		while(rst6.next()) {
			if(rst6.getString("IDPersonal").equals(IDPersonal)) {
				AdmnPersonal.textField.setText(rst6.getString("Motivo"));
				AdmnPersonal.textField.setEnabled(false);
				String dateValue=rst6.getString("fecha");
				try {
					java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
					AdmnPersonal.calendar.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				AdmnPersonal.calendar.setEnabled(false);
				AdmnPersonal.button.setEnabled(false);
			} else
				AdmnPersonal.button.setEnabled(true);
		}
		cnAdministrador.close();		
	}
	
	public static void ActualizarBDPersonal(String IDPersonal,String Nombre,String Apellido, int Edad,String Especializacion, String Genero, String GSanguineo,String Direccion,String Provincia,String Distrito, String Telefono, String TipoDoc, int Documento, String ECivil, Date FIngreso) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("update Personal set Nombre=?,Apellido=?,Edad=?,Rspecializacion=?,Genero=?,GSanguineo=?,Direccion=?,Provincia=?,Distrito=?,telefono=?,TipoDocumento=?,Documento=?,ECivil=?,FIngreso=? where IDPersonal=?");
		stm.setString(1, Nombre);
		stm.setString(2, Apellido);
		stm.setInt(3, Edad);
		stm.setString(4, Especializacion);
		stm.setString(5, Genero);
		stm.setString(6, GSanguineo);
		stm.setString(7, Direccion);
		stm.setString(8, Provincia);
		stm.setString(9, Distrito);
		stm.setString(10, Telefono);
		stm.setString(11, TipoDoc);
		stm.setInt(12, Documento);
		stm.setString(13, ECivil);
		stm.setDate(14,FIngreso);
		stm.setString(15, IDPersonal);
		stm.executeUpdate();
		cnAdministrador.close();
	}
	
	public static void DespedirPersonal(String IDPersonal,String Motivo,Date Fecha) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select Puesto from Personal where IDPersonal=?");
		stm.setString(1, IDPersonal);
		ResultSet rst=stm.executeQuery();
		String Puesto=null;
		PreparedStatement stm2=cnAdministrador.prepareStatement("insert into Desempleado (IDPersonal,Motivo,Fecha,Puesto) values (?,?,?,?)");
		while(rst.next())
		{
			Puesto=rst.getString("Puesto");
		}
		
		stm2.setString(1, IDPersonal);
		stm2.setString(2, Motivo);
		stm2.setDate(3, Fecha);
		stm2.setString(4, Puesto);
		stm2.executeUpdate();
		
		PreparedStatement stm3=cnAdministrador.prepareStatement("update Personal set Estado=? where IDPersonal=?");
		stm3.setString(1, "Despedido");
		stm3.setString(2, IDPersonal);
		stm3.executeUpdate();
		
	
		
	}
	
	public static void LlenarTablaPersonal(DefaultTableModel modelo) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		
		for (int i = 0; i < modelo.getRowCount(); i++)
		{
			modelo.removeRow(i);
			i-=1;
		}
	
		Statement stm=cnAdministrador.createStatement();
		ResultSet rst=stm.executeQuery("select * from Personal");
		while(rst.next()) {
			modelo.addRow(new Object[] {rst.getString("IDPersonal"),rst.getString("Puesto"),rst.getString("Nombre"),rst.getString("Apellido"),rst.getString("Documento"),rst.getString("Telefono"),rst.getString("Rspecializacion"),rst.getString("Estado")});
		}
		cnAdministrador.close();
	}
	
	public static void buscarTablaPersonal(DefaultTableModel modelo,String Personal) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		
		for (int i = 0; i < modelo.getRowCount(); i++)
		{
			modelo.removeRow(i);
			i-=1;
		}
	
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from Personal where IDPersonal=?");
		stm.setString(1, Personal);
		ResultSet rst=stm.executeQuery();
		while(rst.next()) {
			modelo.addRow(new Object[] {rst.getString("IDPersonal"),rst.getString("Puesto"),rst.getString("Nombre"),rst.getString("Apellido"),rst.getString("Documento"),rst.getString("Telefono"),rst.getString("Rspecializacion"),rst.getString("Estado")});
		}
		cnAdministrador.close();
	}
	
	/*cita*/
	
	@SuppressWarnings("unchecked")
	public static void rellenarcita(String NHClinica) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from Cita where NCita=?");
		stm.setString(1, NHClinica);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			AdmnCita.tFDetailNomPaciente.setText(rst.getString("Paciente"));
			AdmnCita.tFDetailDateMatter.setText(rst.getString("Motivo"));
			AdmnCita.cBdetaildoctorname.removeAllElements();
			AdmnCita.cBdetaildoctorname.addElement(rst.getString("NDoctor")+" - "+rst.getString("Doctor"));
			AdmnCita.cBdetaildoctorname.setSelectedItem(rst.getString("NDoctor")+" - "+rst.getString("Doctor"));
			AdmnCita.cBdetailEspeciality.removeAllElements();
			AdmnCita.cBdetailEspeciality.addElement(rst.getString("Especialidad"));
			AdmnCita.cBdetailEspeciality.setSelectedItem(rst.getString("Especialidad"));
			stm=cnAdministrador.prepareStatement("select convert(varchar,Fecha,105) as fecha from Cita where NCita=?");
			stm.setString(1, NHClinica);
			ResultSet rst5=stm.executeQuery();
			while(rst5.next()) {
				String dateValue=rst5.getString("fecha");
				try {
					java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
					AdmnCita.calendar.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			}
			AdmnCita.tFDetailHabitacion.setText(rst.getInt("Habitacion")+"");
			AdmnCita.tFDetailPiso.setText(rst.getInt("Piso")+"");
		}
		cnAdministrador.close();
	}
	
	public static void LlenarTablaCitas(DefaultTableModel lista) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		for (int i = 0; i < lista.getRowCount(); i++)
		{
			lista.removeRow(i);
			i-=1;
		}

		String p1="no";
		String p2="no";
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from Cita");
		ResultSet rst=stm.executeQuery();
		PreparedStatement stm1=cnAdministrador.prepareStatement("select NCita from PacienteDAsistencial where NCita = ?");
		PreparedStatement stm2=cnAdministrador.prepareStatement("select NCita from HojasClinicas where NCita = ?");
		while(rst.next()) {
			stm1.setString(1, rst.getString("NCita"));
			stm2.setString(1, rst.getString("NCita"));
			ResultSet rst1=stm1.executeQuery();
			ResultSet rst2=stm2.executeQuery();
			while(rst1.next())
			{
				if(rst.getString("NCita").equals(rst1.getString("NCita")))
					p1="si";
			}
			while(rst2.next())
			{
				if(rst.getString("NCita").equals(rst2.getString("NCita")))
					p2="si";
			}
			lista.addRow(new Object[] {rst.getString("NCita"),rst.getString("Paciente"),rst.getString("Doctor"),rst.getString("Fecha"),rst.getString("Especialidad"),rst.getString("Motivo"),p1+"/"+p2});
		}
	}
	
	public static void buscarTablaCita(DefaultTableModel lista,String Cita) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		for (int i = 0; i < lista.getRowCount(); i++)
		{
			lista.removeRow(i);
			i-=1;
		}

		String p1="no";
		String p2="no";
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from Cita where NCita=?");
		stm.setString(1, Cita);
		ResultSet rst=stm.executeQuery();
		PreparedStatement stm1=cnAdministrador.prepareStatement("select NCita from PacienteDAsistencial where NCita = ?");
		PreparedStatement stm2=cnAdministrador.prepareStatement("select NCita from HojasClinicas where NCita = ?");
		while(rst.next()) {
			stm1.setString(1, rst.getString("NCita"));
			stm2.setString(1, rst.getString("NCita"));
			ResultSet rst1=stm1.executeQuery();
			ResultSet rst2=stm2.executeQuery();
			while(rst1.next())
			{
				if(rst.getString("NCita").equals(rst1.getString("NCita")))
					p1="si";
			}
			while(rst2.next())
			{
				if(rst.getString("NCita").equals(rst2.getString("NCita")))
					p2="si";
			}
			lista.addRow(new Object[] {rst.getString("NCita"),rst.getString("Paciente"),rst.getString("Doctor"),rst.getString("Fecha"),rst.getString("Especialidad"),rst.getString("Motivo"),p1+"/"+p2});
		}
	}
	
	/*paciente*/
	public static void llenarTablaPacientes(DefaultTableModel lista) throws SQLException {
		for (int i = 0; i < lista.getRowCount(); i++)
		{
			lista.removeRow(i);
			i-=1;
		}
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from PacienteDGeneral");
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			lista.addRow(new Object[] {rst.getString("IDPaciente"),rst.getString("Nombre"),rst.getString("Apellido"),rst.getString("Edad"),rst.getString("Documento"),rst.getString("Seco"),rst.getString("EIngreso")});	
		}
	}
	
	public static void buscarTablaPaciente(DefaultTableModel lista,String Paciente) throws SQLException {
		for (int i = 0; i < lista.getRowCount(); i++)
		{
			lista.removeRow(i);
			i-=1;
		}
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from PacienteDGeneral where IDPaciente=?");
		stm.setString(1, Paciente);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			lista.addRow(new Object[] {rst.getString("IDPaciente"),rst.getString("Nombre"),rst.getString("Apellido"),rst.getString("Edad"),rst.getString("Documento"),rst.getString("Seco"),rst.getString("EIngreso")});	
		}
	}
	
	public static void LlenarBuscarPaciente(String IDPaciente) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from PacienteDGeneral where IDPaciente=?");
		stm.setString(1, IDPaciente);
		ResultSet rst=stm.executeQuery();
		while(rst.next()) 
		{
			AdmnPaciente.tFGDataNombrePaciente.setText(rst.getString("Nombre"));
			AdmnPaciente.tFGDataApellidoPaciente.setText(rst.getString("Apellido"));
			AdmnPaciente.tFGDataDocNum.setText(rst.getInt("Documento")+"");
			stm=cnAdministrador.prepareStatement("select TipoDoc from PacienteDGeneral where IDPaciente=?");
			stm.setString(1, IDPaciente);
			ResultSet rst1=stm.executeQuery();
			while(rst1.next())
			{
				for(int i=0;i<AdmnPaciente.cBGDataDocType.getItemCount();i++)
				{
					if(rst1.getString("TipoDoc").equals((String)AdmnPaciente.cBGDataDocType.getItemAt(i)))
						{
							AdmnPaciente.cBGDataDocType.setSelectedIndex(i);
						}
					
				}
			}
			AdmnPaciente.tFGDataDocNum.setText(rst.getInt("Documento")+"");
			stm=cnAdministrador.prepareStatement("select Seco from PacienteDGeneral where IDPaciente=?");
			stm.setString(1, IDPaciente);
			ResultSet rst2=stm.executeQuery();
			while(rst2.next())
			{
				for(int i=0;i<AdmnPaciente.cBDataSexo.getItemCount();i++)
				{
					if(rst2.getString("Seco").equals((String)AdmnPaciente.cBDataSexo.getItemAt(i)))
						{
							AdmnPaciente.cBDataSexo.setSelectedIndex(i);
						}
					
				}
			}
			stm=cnAdministrador.prepareStatement("select EIngreso from PacienteDGeneral where IDPaciente=?");
			stm.setString(1, IDPaciente);
			ResultSet rst3=stm.executeQuery();
			while(rst3.next())
			{
				for(int i=0;i<AdmnPaciente.cBGDataInState.getItemCount();i++)
				{
					if(rst3.getString("EIngreso").equals((String)AdmnPaciente.cBGDataInState.getItemAt(i)))
						{
							AdmnPaciente.cBGDataInState.setSelectedIndex(i);
						}
					
				}
			}
			AdmnPaciente.GDataEdadPaciente.setValue(rst.getInt("Edad"));
			AdmnPaciente.tFGDataBloodType.setText(rst.getString("GSanguineo"));
			stm=cnAdministrador.prepareStatement("select convert(varchar,FNaciminto,105) as fecha from PacienteDGeneral where IDPaciente=?");
			stm.setString(1, IDPaciente);
			ResultSet rst5=stm.executeQuery();
			while(rst5.next()) {
				String dateValue=rst5.getString("fecha");
				try {
					java.util.Date date=new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
					AdmnPaciente.dCGDataBornDate.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			}
			stm=cnAdministrador.prepareStatement("select Motivo from PacienteDGeneral where IDPaciente=?");
			stm.setString(1, IDPaciente);
			ResultSet rst4=stm.executeQuery();
			while(rst4.next())
			{
				for(int i=0;i<AdmnPaciente.cBGDataMatter.getItemCount();i++)
				{
					if(rst4.getString("Motivo").equals((String)AdmnPaciente.cBGDataMatter.getItemAt(i)))
						{
							AdmnPaciente.cBGDataMatter.setSelectedIndex(i);
						}
					
				}
			}
			AdmnPaciente.tFGDataAllergy.setText(rst.getString("Alergia"));
			AdmnPaciente.tFGDataObservations.setText(rst.getString("Observaciones"));
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void LlenarListaCitas(String IDPaciente,DefaultComboBoxModel citas) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		citas.removeAllElements();
		citas.addElement("-Seleccione-");
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from Cita where NPaciente=?");
		stm.setString(1, IDPaciente);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			citas.addElement(rst.getString("NCita"));
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void LlenarCita(String NCita) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from Cita where NCita=?");
		stm.setString(1, NCita);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			AdmnPaciente.textField.setText(rst.getString("Motivo"));
			stm=cnAdministrador.prepareStatement("select convert(varchar,Fecha,105) as fecha from Cita where NCita=?");
			stm.setString(1, NCita);
			ResultSet rst5=stm.executeQuery();
			while(rst5.next()) {
				String dateValue=rst5.getString("fecha");
				try {
					java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
					AdmnPaciente.calendar.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			}
			AdmnPaciente.combobox_1.removeAllElements();
			AdmnPaciente.combobox_1.addElement(rst.getString("NDoctor"));
			AdmnPaciente.spinner.setValue(rst.getInt("Habitacion"));
			AdmnPaciente.spinner_1.setValue(rst.getInt("Piso"));
		}
		PreparedStatement stm1=cnAdministrador.prepareStatement("select * from PacienteDAsistencial where NCita=?");
		stm1.setString(1, NCita);
		ResultSet rst1=stm1.executeQuery();
		while(rst1.next())
		{
			AdmnPaciente.tFADataWeigth.setText(rst1.getFloat("Peso")+"");
			AdmnPaciente.tFADataSize.setText(rst1.getFloat("Talla")+"");
			AdmnPaciente.tFADataPulse.setText(rst1.getFloat("Pulso")+"");
			AdmnPaciente.tFADataHRithm.setText(rst1.getInt("RitmoCardiaco")+"");
			AdmnPaciente.tFADataPrincipalDiagnostic.setText(rst1.getString("DiagnosticoPrincipal"));
			AdmnPaciente.tFADataDrugs.setText(rst1.getString("Medicinas"));
			stm=cnAdministrador.prepareStatement("select convert(varchar,Fecha,105) as fecha from PacienteDAsistencial where NCita=?");
			stm.setString(1, NCita);
			ResultSet rst5=stm.executeQuery();
			while(rst5.next()) {
				String dateValue=rst5.getString("fecha");
				try {
					java.util.Date date=new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
					AdmnPaciente.dCADataData.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			}
			AdmnPaciente.textField_4.setText(rst1.getString("Antecedentes"));
			AdmnPaciente.tFADataDisorder.setText(rst1.getString("Trastorno"));
		}
		PreparedStatement stm2=cnAdministrador.prepareStatement("select * from HojasClinicas where NCita=?");
		stm2.setString(1, NCita);
		ResultSet rst2=stm2.executeQuery();
		AdmnPaciente.combobox_2.removeAllElements();
		while(rst2.next())
		{
			AdmnPaciente.textField_3.setText(rst2.getString("NHClinica"));
			AdmnPaciente.combobox_2.addElement(rst2.getString("Encargado"));
			AdmnPaciente.combobox_2.setSelectedItem(rst2.getString("Encargado"));
			stm=cnAdministrador.prepareStatement("select convert(varchar,Fecha,105) as fecha from HojasClinicas where NCita=?");
			stm.setString(1, NCita);
			ResultSet rst5=stm.executeQuery();
			while(rst5.next()) {
				String dateValue=rst5.getString("fecha");
				try {
					java.util.Date date=new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
					AdmnPaciente.dateChooser.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			}
			AdmnPaciente.textField_2.setText(rst2.getString("Pruebas"));
			AdmnPaciente.textField_1.setText(rst2.getString("Resultados"));
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void llenarInternados(String NPaciente) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from PacienteDHospitalizado where NPaciente=?");
		stm.setString(1, NPaciente);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			AdmnPaciente.textField_5.setText(rst.getString("Motivo"));
			stm=cnAdministrador.prepareStatement("select convert(varchar,Fecha,105) as fecha from PacienteDHospitalizado where NPaciente=?");
			stm.setString(1, NPaciente);
			ResultSet rst5=stm.executeQuery();
			while(rst5.next()) {
				String dateValue=rst5.getString("fecha");
				try {
					java.util.Date date=new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
					AdmnPaciente.cIDataCalendario.setDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}			
			}
			AdmnPaciente.cBidatadoctor.removeAllElements();
			AdmnPaciente.cBidatadoctor.addElement(rst.getString("NDoctor"));
			AdmnPaciente.cBidatadoctor.setSelectedItem(rst.getString("NDoctor"));
			AdmnPaciente.sIDataHabitacion.setValue(rst.getInt("Habitacion"));
			AdmnPaciente.sIDataPiso.setValue(rst.getInt("Piso"));
		}
		cnAdministrador.close();
	}
	
	/*hojas*/
	
	@SuppressWarnings("unchecked")
	public static void llenarHojaAlta(String NHAlta) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from HojasAlta where NHAlta=?");
		stm.setString(1, NHAlta);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			try {
				AdmnPacienteHojaAlta.cDatosfalta=new SimpleDateFormat("dd-MMM-yyyy").parse(rst.getString("Fecha"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			AdmnPacienteHojaAlta.cDatosFAlta.setDate(AdmnPacienteHojaAlta.cDatosfalta);
			AdmnPacienteHojaAlta.cBdatosdoctor.removeAllElements();
			AdmnPacienteHojaAlta.cBdatosdoctor.addElement(rst.getString("Doctor"));
			AdmnPacienteHojaAlta.cBdatosdoctor.setSelectedItem(rst.getString("Doctor"));
			AdmnPacienteHojaAlta.tFDatosMotivo.setText(rst.getString("Motivo"));
		}
	}
	
	public static void llenarTablaAlta(DefaultTableModel alta, String IDPaciente) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from HojasAlta where IDPaciente=?");
		stm.setString(1, IDPaciente);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			alta.addRow(new Object [] {rst.getString("NHAlta"),rst.getString("Doctor"),rst.getString("Fecha"),rst.getString("Motivo")});
		}
	}
	
	public static void buscarTablaAlta(DefaultTableModel alta, String IDPaciente,String HAlta) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from HojasAlta where IDPaciente=? and NHalta=?");
		stm.setString(1, IDPaciente);
		stm.setString(2, HAlta);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			alta.addRow(new Object [] {rst.getString("NHAlta"),rst.getString("Doctor"),rst.getString("Fecha"),rst.getString("Motivo")});
		}
	}
	
	/*clinica*/
	@SuppressWarnings("unchecked")
	public static void llenarHojaClinica(String NHClinica) throws SQLException {
		Connection cnAdministrador = CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from HojasClinicas where NHClinica =?");
		stm.setString(1, NHClinica);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			AdmnPacienteHojaClinica.cBdatosnencargado.removeAllElements();
			AdmnPacienteHojaClinica.cBdatosnencargado.addElement(rst.getString("Encargado"));
			AdmnPacienteHojaClinica.cBdatosnencargado.setSelectedItem(rst.getString("Encargado"));
			try {
				AdmnPacienteHojaClinica.dCdatosfecha=new SimpleDateFormat("dd-MMM-yyyy").parse(rst.getString("Fecha"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			AdmnPacienteHojaClinica.dCDatosFecha.setDate(AdmnPacienteHojaClinica.dCdatosfecha);
			AdmnPacienteHojaClinica.tFDatosPruebas.setText(rst.getString("Pruebas"));
			AdmnPacienteHojaClinica.tFDatosResultados.setText(rst.getString("Resultados"));
		}
	}
	
	public static void llenarTablaClinica(DefaultTableModel clinicas,String IDPaciente) throws SQLException {
		
		for (int i = 0; i < clinicas.getRowCount(); i++)
		{
			clinicas.removeRow(i);
			i-=1;
		}
		
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from HojasClinicas where IDPaciente=?");
		stm.setString(1, IDPaciente);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			clinicas.addRow(new Object [] {rst.getString("NHClinica"),rst.getString("Encargado"),rst.getString("IDPaciente"),rst.getString("Pruebas"),rst.getString("Fecha"),rst.getString("Resultados")});
		}
	}
	
public static void buscarTablaClinica(DefaultTableModel clinicas,String IDPaciente,String HClinica) throws SQLException {
		
		for (int i = 0; i < clinicas.getRowCount(); i++)
		{
			clinicas.removeRow(i);
			i-=1;
		}
		
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from HojasClinicas where IDPaciente=? and NHClinica=?");
		stm.setString(1, IDPaciente);
		stm.setString(2, HClinica);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			clinicas.addRow(new Object [] {rst.getString("NHClinica"),rst.getString("Encargado"),rst.getString("IDPaciente"),rst.getString("Pruebas"),rst.getString("Fecha"),rst.getString("Resultados")});
		}
	}
	
	/*traslado*/
	
	@SuppressWarnings("unchecked")
	public static void llenarHojaTraslado(String NHTraslado) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from HojasTraslado where NHTraslado=?");
		stm.setString(1, NHTraslado);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			AdmnPacienteHojaTranslado.textField.setText(rst.getString("Motivo"));
			try {
				AdmnPacienteHojaTranslado.dCdatosftraslado=new SimpleDateFormat("dd-MMM-yyyy").parse(rst.getString("Fecha"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			AdmnPacienteHojaTranslado.dCDatosFTraslado.setDate(AdmnPacienteHojaTranslado.dCdatosftraslado);
			AdmnPacienteHojaTranslado.cBdatosdoctor.removeAllElements();
			AdmnPacienteHojaTranslado.cBdatosdoctor.addElement(rst.getString("Doctor"));
			AdmnPacienteHojaTranslado.cBdatosdoctor.setSelectedItem(rst.getString("Doctor"));
			AdmnPacienteHojaTranslado.tFDatosObs.setText(rst.getString("Observacion"));
		}
	}
	
	public static void llenarTablaTranslado(DefaultTableModel lista,String IDPaciente) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from HojasTraslado where IDPaciente=?");
		stm.setString(1, IDPaciente);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			lista.addRow(new Object[] {rst.getString("NHTraslado"),rst.getString("Doctor"),rst.getString("IDPaciente"),rst.getString("Motivo"),rst.getString("Fecha")});
		}
	}
	
	public static void buscarTablaTranslado(DefaultTableModel lista,String IDPaciente, String Traslado) throws SQLException {
		Connection cnAdministrador=CnAdministrador.getConnection();
		PreparedStatement stm=cnAdministrador.prepareStatement("select * from HojasTraslado where IDPaciente=? and Traslado=?");
		stm.setString(1, IDPaciente);
		stm.setString(2, Traslado);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			lista.addRow(new Object[] {rst.getString("NHTraslado"),rst.getString("Doctor"),rst.getString("IDPaciente"),rst.getString("Motivo"),rst.getString("Fecha")});
		}
	}
}