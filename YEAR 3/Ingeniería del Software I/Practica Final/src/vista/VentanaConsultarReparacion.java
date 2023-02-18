package vista;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.vo.ReparacionVO;
import modelo.vo.UsuarioVO;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VentanaConsultarReparacion extends JFrame{

	private UsuarioVO user;
	private String value;
	private ArrayList<ReparacionVO> listaReparaciones;
	private JTextField textIdReparacion;
	private JTextField textPrecio;
	private JTextField textReparacion;
	private JTextField textMatricula;
	private JTextField textMatriculaVehiculo;
	private JTextField textIdFactura;
	private JTextField textPrecioFinal;
	private JTextField textIsPagada;
	private JTextField textEstado;
	private JTextField textNifMecanico;
	private JTextField textCliente;
	private JTextField textTipo;
	private JTextField textDescripcion;
	
	public VentanaConsultarReparacion(ArrayList<ReparacionVO> listaReparaciones, String value) {
		this.value=value;
		this.listaReparaciones=listaReparaciones;
		setBounds(130, 130, 1050, 440);
		setTitle("Reparacion "+ listaReparaciones.get(Integer.parseInt(value)).getIdReparacion());
		getContentPane().setLayout(null);
		initializate();
	}
	
	public void initializate() {
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(523, 10, 159, 80);
		ImageIcon imagen=new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(),Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		
		this.getContentPane().add(lblLogo);
		
		JLabel lblReparacion = new JLabel("");
		lblReparacion.setBounds(88, 120, 340, 246);
		switch(listaReparaciones.get(Integer.parseInt(value)).getTipo()) {
			case "RUEDA": 	ImageIcon imagen_1=new ImageIcon("src/imagenes/rueda.jpg");
							Icon icono_1=new ImageIcon(imagen_1.getImage().getScaledInstance(lblReparacion.getWidth(),lblReparacion.getHeight(),Image.SCALE_DEFAULT));
							lblReparacion.setIcon(icono_1);
				break;
			case "LUNA":	ImageIcon imagen_2=new ImageIcon("src/imagenes/luna.jpg");
							Icon icono_2=new ImageIcon(imagen_2.getImage().getScaledInstance(lblReparacion.getWidth(),lblReparacion.getHeight(),Image.SCALE_DEFAULT));
							lblReparacion.setIcon(icono_2);
				break;
			case "AIRE":	ImageIcon imagen_3=new ImageIcon("src/imagenes/aire.jpg");
							Icon icono_3=new ImageIcon(imagen_3.getImage().getScaledInstance(lblReparacion.getWidth(),lblReparacion.getHeight(),Image.SCALE_DEFAULT));
							lblReparacion.setIcon(icono_3);
				break;
			case "LUCES":	ImageIcon imagen_4=new ImageIcon("src/imagenes/luces.jpg");
							Icon icono_4=new ImageIcon(imagen_4.getImage().getScaledInstance(lblReparacion.getWidth(),lblReparacion.getHeight(),Image.SCALE_DEFAULT));
							lblReparacion.setIcon(icono_4);
				break;
			case "RETROVISOR":	ImageIcon imagen_5=new ImageIcon("src/imagenes/retrovisor.jpg");
								Icon icono_5=new ImageIcon(imagen_5.getImage().getScaledInstance(lblReparacion.getWidth(),lblReparacion.getHeight(),Image.SCALE_DEFAULT));
								lblReparacion.setIcon(icono_5);
				break;
			case "OTRO":		ImageIcon imagen_6=new ImageIcon("src/imagenes/otro.jpg");
								Icon icono_6=new ImageIcon(imagen_6.getImage().getScaledInstance(lblReparacion.getWidth(),lblReparacion.getHeight(),Image.SCALE_DEFAULT));
								lblReparacion.setIcon(icono_6);
				default:
		}
		this.getContentPane().add(lblReparacion);

		
		JLabel lblRegistro = new JLabel("Datos de la reparaci\u00F3n");
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblRegistro.setBounds(670, 10, 268, 96);
		getContentPane().add(lblRegistro);
		
		
		JLabel lblidReparacion = new JLabel("ID Reparacion");
		lblidReparacion.setBounds(523, 103, 77, 40);
		getContentPane().add(lblidReparacion);
		
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(523, 203, 87, 40);
		getContentPane().add(lblEstado);
		
	
		JLabel lblMatriculoVehiculo = new JLabel("Matricula Vehiculo");
		lblMatriculoVehiculo.setBounds(613, 103, 116, 40);
		getContentPane().add(lblMatriculoVehiculo);
		
		
		JLabel lblidFactura = new JLabel("IdFactura");
		lblidFactura.setBounds(739, 103, 77, 40);
		getContentPane().add(lblidFactura);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(840, 103, 99, 40);
		getContentPane().add(lblPrecio);
		
		JLabel lblPagada = new JLabel("Pagada");
		lblPagada.setBounds(932, 103, 77, 40);
		getContentPane().add(lblPagada);
		
		textMatriculaVehiculo = new JTextField();
		textMatriculaVehiculo.setEditable(false);
		textMatriculaVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textMatriculaVehiculo.setText("0");
		textMatriculaVehiculo.setColumns(10);
		textMatriculaVehiculo.setBounds(613, 153, 99, 19);
		textMatriculaVehiculo.setText(listaReparaciones.get(Integer.parseInt(value)).getMatricula());
		getContentPane().add(textMatriculaVehiculo);
		
		textIdReparacion = new JTextField();
		textIdReparacion.setEditable(false);
		textIdReparacion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textIdReparacion.setBounds(523, 153, 64, 19);
		getContentPane().add(textIdReparacion);
		textIdReparacion.setText(String.valueOf(listaReparaciones.get(Integer.parseInt(value)).getIdReparacion()));
		textIdReparacion.setColumns(10);

		
		textIdFactura = new JTextField();
		textIdFactura.setEditable(false);
		textIdFactura.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textIdFactura.setText("0");
		textIdFactura.setColumns(10);
		textIdFactura.setBounds(739, 153, 77, 19);
		textIdFactura.setText(String.valueOf(listaReparaciones.get(Integer.parseInt(value)).getIdFactura()));
		getContentPane().add(textIdFactura);

		
		textPrecioFinal = new JTextField();
		textPrecioFinal.setEditable(false);
		textPrecioFinal.setText("0");
		textPrecioFinal.setColumns(10);
		textPrecioFinal.setBounds(842, 153, 68, 19);
		textPrecioFinal.setText(String.valueOf(listaReparaciones.get((int) Double.parseDouble(value)).getPrecio()));
		getContentPane().add(textPrecioFinal);

		
		textIsPagada = new JTextField();
		textIsPagada.setEditable(false);
		textIsPagada.setForeground(Color.BLACK);
		textIsPagada.setText("0");
		textIsPagada.setColumns(10);
		textIsPagada.setBounds(932, 153, 56, 19);
		textIsPagada.setText(String.valueOf(listaReparaciones.get(Integer.parseInt(value)).isPagada()));
		getContentPane().add(textIsPagada);

		
		textEstado = new JTextField();
		textEstado.setEditable(false);
		textEstado.setText("0");
		textEstado.setColumns(10);
		textEstado.setBounds(523, 253, 87, 19);
		textEstado.setText(String.valueOf(listaReparaciones.get(Integer.parseInt(value)).getEstado()));
		getContentPane().add(textEstado);

		
		JLabel lblNombreMecanico = new JLabel("NIF Mecanico");
		lblNombreMecanico.setBounds(620, 203, 77, 40);
		getContentPane().add(lblNombreMecanico);
		
		JLabel lblNombreCliente = new JLabel("NIF Cliente");
		lblNombreCliente.setBounds(719, 203, 77, 40);
		getContentPane().add(lblNombreCliente);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(833, 203, 77, 40);
		getContentPane().add(lblTipo);
		
		textNifMecanico = new JTextField();
		textNifMecanico.setEditable(false);
		textNifMecanico.setColumns(10);
		textNifMecanico.setBounds(620, 253, 77, 19);
		textNifMecanico.setText(String.valueOf(listaReparaciones.get(Integer.parseInt(value)).getNifMecanico()));
		getContentPane().add(textNifMecanico);

		
		textCliente = new JTextField();
		textCliente.setEditable(false);
		textCliente.setColumns(10);
		textCliente.setBounds(719, 253, 77, 19);
		textCliente.setText(String.valueOf(listaReparaciones.get(Integer.parseInt(value)).getNifCliente()));
		getContentPane().add(textCliente);

		
		textTipo = new JTextField();
		textTipo.setEditable(false);
		textTipo.setColumns(10);
		textTipo.setBounds(833, 253, 77, 19);
		textTipo.setText(String.valueOf(listaReparaciones.get(Integer.parseInt(value)).getTipo()));
		getContentPane().add(textTipo);

		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(523, 303, 77, 40);
		getContentPane().add(lblDescripcion);
		
		textDescripcion = new JTextField();
		textDescripcion.setEditable(false);
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(523, 353, 467, 19);
		textDescripcion.setText(String.valueOf(listaReparaciones.get(Integer.parseInt(value)).getDescripcion()));
		getContentPane().add(textDescripcion);

		
		JLabel lblTipoDeReparacin = new JLabel("Tipo de reparaci\u00F3n");
		lblTipoDeReparacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeReparacin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTipoDeReparacin.setBounds(88, 27, 340, 77);
		getContentPane().add(lblTipoDeReparacin);
	}
}
