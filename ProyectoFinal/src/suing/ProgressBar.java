package suing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import conexión.CnAdministrador;
import conexión.CnLogIn;
import conexión.CnPersonal;
import conexión.CnRecepcionista; 

public class ProgressBar{
	
	static JFrame ventana;
	static JButton boton;
	static JProgressBar barra;
	
	
	public ProgressBar(){
		ventana=new JFrame("PrograAdvance");
		ventana.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Desktop\\u\\ProyectoFinalxd\\45900.png"));
		ventana.setTitle("Cl\u00EDnica UNAC");
		
		boton =new JButton("Iniciar programa");
		barra=new JProgressBar(0,100);
		
		
		boton.addActionListener(new Escucha());
		ventana.setLocationRelativeTo(null);
		ventana.getContentPane().setLayout(new FlowLayout());
		ventana.getContentPane().add(barra);
		ventana.getContentPane().add(boton);
		ventana.pack();
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new ProgressBar();
			
	}
	
	public static class Escucha implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new Thread(new Hilo()).start();
		}
	}
	public static class Hilo implements Runnable{
		
		public void run(){
			try {
				DefaultTableModel modelo=new DefaultTableModel();
				CnLogIn.login(null,null,1);
				for(int x=0;x<10;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}
				}
				CnAdministrador.LlenarTablaPersonal(modelo);
				for(int x=10;x<17;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}
				}
				CnAdministrador.CodigoAleatorio();
				for(int x=17;x<34;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}
				}
				CnAdministrador.LlenarTablaCitas(modelo);
				for(int x=34;x<42;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}
				}
				CnAdministrador.llenarTablaPacientes(modelo);
				for(int x=42;x<50;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}
				}
				CnRecepcionista.LlenarTablaPaciente(modelo);
				for(int x=50;x<60;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}
				}
				CnRecepcionista.LlenarTablaCita(modelo);
				for(int x=60;x<70;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}
				}
				CnRecepcionista.CodigoAleatorio();
				for(int x=70;x<80;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}
				}
				CnRecepcionista.CodigoAleatorioCita();
				for(int x=80;x<90;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}
				}
				CnPersonal.LlenarTablaCita(modelo,null);
				for(int x=90;x<100;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}
				}
				CnPersonal.llenarTablaPacientes(modelo, null);
				for(int x=100;x<110;x++)
				{
					barra.setValue(x);
					barra.repaint();
					try{
						Thread.sleep(30);
					}catch(Exception ex){}		
				}
				ventana.dispose();
				LogIn login = new LogIn();
				login.setVisible(true);
		
			}catch(Exception e)
			{}
	}
}
}
