package conexión;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import suing.LogIn;

public class CnLogIn {
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
	
	public static boolean login(String usuario, String contraseña,int i) throws SQLException {
		Connection cnLogin=CnLogIn.getConnection();
		boolean correcto=false;
		String query=null,user=null,pass=null,Tipo=null;
		switch(i) {
			case 1:query="select * from Users where Usuario=?";user="Usuario";pass="Password";Tipo="Tipo";break;
			case 2:query="select * from Users where Usuario=?";user="Usuario";pass="Password";Tipo="Tipo";break;
			case 3:query="select IDPersonal,Documento,Estado from Personal where IDPersonal=?";user="IDPersonal";pass="Documento";break;
		}
		PreparedStatement stm=cnLogin.prepareStatement(query);
		stm.setString(1, usuario);
		ResultSet rst=stm.executeQuery();
		while(rst.next())
		{
			if(i==3) {
				if(rst.getString("Estado").equals("Activo")) {
					if(usuario.equals(rst.getString(user))&&contraseña.equals(rst.getString(pass))) {
						if(i<3) {
							if(Integer.parseInt(rst.getString(Tipo))==i) {
								correcto=true;
							}
						} else {
							correcto=true;
							LogIn.Usuario=usuario;
						}
					} else {
						JOptionPane.showMessageDialog(null,"Datos incorrectos");
					}
				} else {
					JOptionPane.showMessageDialog(null,"Este elemento del personal fue despedido");
				}
			} else {
				if(usuario.equals(rst.getString(user))&&contraseña.equals(rst.getString(pass))) {
					if(i<3) {
						if(Integer.parseInt(rst.getString(Tipo))==i) {
							correcto=true;
						}
					} else {
						correcto=true;
						LogIn.Usuario=usuario;
					}
				} else {
					JOptionPane.showMessageDialog(null,"Datos invalidos, porfavor; vuelva a ingresar");
				}
			}
		}
		return correcto;	
	}
}