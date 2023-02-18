package simulandoTrenecitos.ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import simulandoTrenecitos.logic.Train;
import simulandoTrenecitos.logic.MovimientoTren;
import simulandoTrenecitos.logic.Trenecitos;
import simulandoTrenecitos.ui.gui.views.BoardPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainWindow extends JFrame {

	private JMenuBar mbPrincipal;
	private JMenu mnEdit;
	private JMenuItem mntmNuevoTren;
	public Trenecitos juego;
	JMenuItem mntmSimulacion;
	private JMenu mnMover;
	private JMenu mnSimulacion;
	private JMenuItem mntmBorrarTren;
	private JMenuItem mntmSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		// Barra principal
		mbPrincipal = new JMenuBar();
		setJMenuBar(mbPrincipal);

		// Secciones de la barra principal
		JMenu mnFile = new JMenu("Archivo");
		mbPrincipal.add(mnFile);
		
		// Acción Botón Salir
		JMenuItem mntmExit = new JMenuItem("Salir");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		// Acción Iniciar Simulación
				mntmSimulacion = new JMenuItem("Iniciar Simulación");
				mntmSimulacion.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String value = null;
						int filas=0;
						int columnas=0;
						do {
							value = (String) JOptionPane.showInputDialog(null, "Introduce el nº de filas", "Nº de filas",
									JOptionPane.DEFAULT_OPTION);
							if(value!=null && isNumeric(value)) {
								filas = Integer.parseInt(value.trim());
								value = (String) JOptionPane.showInputDialog(null, "Introduce el nº de columnas", "Nº de columnas",
										JOptionPane.DEFAULT_OPTION);
									if(value!=null && isNumeric(value)) {
										 columnas = Integer.parseInt(value.trim());
										 juego = new Trenecitos(filas, columnas);
										MainWindow.this.setContentPane(new BoardPanel(MainWindow.this, filas, columnas));
										MainWindow.this.repaint();
										MainWindow.this.mntmSave.setEnabled(true);
										MainWindow.this.revalidate();
								}
							}
							if(!isNumeric(value)) {
								JOptionPane.showMessageDialog(null, "Los datos introducidos son incorrectos, pulse OK para volver a introducirlos");
							}
						}while(!isNumeric(value));
					}
				});
				mnFile.add(mntmSimulacion);

		// Acción Botón Cargar
		JMenuItem mntmFile = new JMenuItem("Cargar simulación");
		mnFile.add(mntmFile);
		mntmFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				List<Train> list=new ArrayList<Train>();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					BufferedReader br;
					try {
						br = new BufferedReader(
								new InputStreamReader(new FileInputStream(selectedFile), StandardCharsets.UTF_8));
						String line=br.readLine();
						while ((line = br.readLine()) != null) {
							String[] chars=line.split(" ");
							String dir=chars[0];
							MovimientoTren move=null;
							switch(dir) {
							case "D":
								move=MovimientoTren.DERECHA;
								break;
							case "I":
								move=MovimientoTren.IZQUIERDA;
								break;
							case "A":
								move=MovimientoTren.ARRIBA;
								break;
							case "B":
								move=MovimientoTren.ABAJO;
								break;
							
						}
							int length=Integer.parseInt(chars[1]);
							int x=Integer.parseInt(chars[2]);
							int y=Integer.parseInt(chars[3]);
							Train tren = new Train(move,length,x,y);
							list.add(tren);
						}
						if(juego==null) {
							MainWindow.this.mntmSimulacion.doClick();
						}
						if(juego.insertTrains(list)) {
							MainWindow.this.getContentPane().repaint();
						}
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		});

		// Acción Botón Guardar
		 mntmSave = new JMenuItem("Guardar simulación");
		mnFile.add(mntmSave);
		mntmSave.setEnabled(false);
		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					String path=new File (fileChooser.getSelectedFile().getAbsolutePath(),"Partida.txt").getAbsolutePath();
					  try {
				            // Java 11 , default StandardCharsets.UTF_8
				            Files.writeString(Paths.get(path), MainWindow.this.juego.getOutput());

				         
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
				}
				
		        
		      


			}

		});

		mnEdit = new JMenu("Editar");
		mbPrincipal.add(mnEdit);
		mnEdit.setEnabled(false);

		// Acción botón añadir tren
		JMenuItem mntmNuevoTren = new JMenuItem("Añadir un tren");
		mnEdit.add(mntmNuevoTren);
		mntmNuevoTren.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String[] direcciones = { "Izquierda", "Derecha", "Arriba", "Abajo" };
				int direccion = JOptionPane.showOptionDialog(MainWindow.this.getContentPane(),
						"Seleccione la dirección del tren", "Dirección del tren", JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, direcciones, direcciones[0]);
				List<Train> list = new ArrayList<Train>();
				boolean check;

				String value =null;
				do {
					list.clear();
					do {
						value = (String) JOptionPane.showInputDialog(MainWindow.this.getContentPane(),"Indique la longitud del tren");
					}while(value==null || !isNumeric(value));
					
					int longitud=Integer.parseInt(value.trim());
					
					do {
						value = (String) JOptionPane.showInputDialog(MainWindow.this.getContentPane(),"Indique la fila de la locomotora");
					}while(value==null || !isNumeric(value));
					
					int x = Integer.parseInt(value.trim());
					
					do {
						value = (String) JOptionPane.showInputDialog(MainWindow.this.getContentPane(),"Indique la columna de la locomotora");
					}while(value==null || !isNumeric(value));

					int y = Integer.parseInt(value.trim());

					MovimientoTren dir = MovimientoTren.ABAJO;

					switch (direccion) {
					case 0:
						dir = MovimientoTren.IZQUIERDA;
						break;
					case 1:
						dir = MovimientoTren.DERECHA;
						break;
					case 2:
						dir = MovimientoTren.ARRIBA;
						break;
					case 3:
						dir = MovimientoTren.ABAJO;
						break;
					}
					
					Train tren = new Train(dir, longitud, y, x);
					list.add(tren);
					 check=juego.insertTrains(list);
					if (check) {
						MainWindow.this.getContentPane().repaint();
						MainWindow.this.mntmBorrarTren.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(null,"Los datos introducidos son erroneos.");
					}
				}while(!check);
				

			}

		});

		// Acción botón borrar tren
		 mntmBorrarTren = new JMenuItem("Borrar un tren");
		mnEdit.add(mntmBorrarTren);
		mntmBorrarTren.setEnabled(false);
		mntmBorrarTren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String value =null;
				int filas=0;
				int columnas=0;
				do {
					value = (String) JOptionPane.showInputDialog(MainWindow.this, "Introduce la fila de la cabeza del tren", "Eliminar tren",
							JOptionPane.DEFAULT_OPTION);
					if(value!=null && isNumeric(value)) {
						filas = Integer.parseInt(value.trim());
						value = (String) JOptionPane.showInputDialog(MainWindow.this, "Introduce la columna de la cabeza del tren", "Eliminar Tren",
								JOptionPane.DEFAULT_OPTION);
							if(value!=null && isNumeric(value)) {
								 columnas = Integer.parseInt(value.trim());
								 if(MainWindow.this.juego.removeTrains(filas,columnas)) {
										MainWindow.this.getContentPane().repaint();
								}else {
									JOptionPane.showMessageDialog(null, "Los datos introducidos son incorrectos, pulse OK para volver a introducirlos");
								}
								
						}
					}
					if(!isNumeric(value)) {
						JOptionPane.showMessageDialog(null, "Los datos introducidos son incorrectos, pulse OK para volver a introducirlos");
					}
				}while(!isNumeric(value));
//				do {
//					value = (String) JOptionPane.showInputDialog("Indique la fila de la locomotora");
//				}while(value==null || !isNumeric(value));
//				
//				int x = Integer.parseInt(value.trim());
//				
//				do {
//					value = (String) JOptionPane.showInputDialog("Indique la columna de la locomotora");
//				}while(value==null || !isNumeric(value));
//
//				int y = Integer.parseInt(value.trim());
//				
//				if(MainWindow.this.juego.removeTrains(x, y)) {
//					MainWindow.this.getContentPane().repaint();
//				}
			}

		});

		// Acción botón deshacer movimiento
		JMenuItem mntmEliminarMovimiento = new JMenuItem("Deshacer movimiento");
		mnEdit.add(mntmEliminarMovimiento);
		mntmEliminarMovimiento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		// Acción botón deshacer movimiento
		JMenuItem mntmRehacerMovimiento = new JMenuItem("Rehacer movimiento");
		mnEdit.add(mntmRehacerMovimiento);
		mntmRehacerMovimiento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		 mnSimulacion = new JMenu("Simulación");
		mbPrincipal.add(mnSimulacion);

		// Acción botón arrancar simulacion
		JMenuItem mntmJugar = new JMenuItem("Simular partida");
		mnSimulacion.add(mntmJugar);
		mnSimulacion.setEnabled(false);
		mntmJugar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Thread thread = new Thread() {
				      public void run(){
				    	  while(!juego.isEndedGame()) {
								juego.nextRound();
								MainWindow.this.getContentPane().repaint();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
				      }
				   };
				 thread.start();
			}
		});

		JMenu mnHelp = new JMenu("Ayuda");
		mbPrincipal.add(mnHelp);

		// Acción Botón ayuda
		JMenuItem mntmHelp = new JMenuItem("Ayuda");
		mnHelp.add(mntmHelp);
		mntmHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "La cabeza de cada tren está representada por una casilla en blanco.\n "
						+ "                               Pulsela para mover el tren.");

			}

		});

		
		mnFile.add(mntmExit);

//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);

	}
	
	public boolean isNumeric(String str) {
		if(str==null) {
			return true;
		}
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public JMenu getSimulacion() {
		return this.mnSimulacion;
	}
	
	public JMenu getEdit() {
		return this.mnEdit;
	}
	
	
}
