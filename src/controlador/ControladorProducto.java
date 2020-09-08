package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.JOptionPane;
import modelo.ConsultasProducto;
import modelo.Producto;
import vista.VistaProducto;

public class ControladorProducto implements ActionListener {
	private VistaProducto vista;
	private Producto producto;
	private ConsultasProducto modelo;

	public ControladorProducto(VistaProducto vista, Producto producto, ConsultasProducto modelo) {
		this.vista = vista;
		this.producto = producto;
		this.modelo = modelo;

		vista.btnRegistrar.addActionListener(this);
		vista.btnBuscar.addActionListener(this);
		vista.btnLimpiar.addActionListener(this);
		vista.btnModificar.addActionListener(this);
		vista.btnEliminar.addActionListener(this);
	}

	public void iniciar() {
		vista.setTitle("CREPERIA LUISITO GUADALAJARA");
		vista.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent botonAcciones) {

		// Registra Platillo
		if (botonAcciones.getSource() == vista.btnRegistrar) {

			if (String.valueOf(vista.txtPlatillo.getText()).compareTo("") == 0
					|| String.valueOf(vista.txtCodigo.getText()).compareTo("") == 0
					|| String.valueOf(vista.txtPrecio.getText()).compareTo("") == 0
					|| String.valueOf(vista.comboBoxCantidad.getSelectedItem().toString()).compareTo("") == 0
					|| String.valueOf(vista.textAreaIngredientes.getText()).compareTo("") == 0) {
				// poner mensaje el usuario debe de llenar todos los campos de texto
				JOptionPane.showMessageDialog(null, "Debes Completar Todos Los Campos", "Warning",
						JOptionPane.WARNING_MESSAGE);

			} else {

				// Primero busca si ya hay algun registro igual
				producto.setCodigo(vista.txtCodigo.getText());

				if (modelo.buscar(producto) == true) {
					JOptionPane.showMessageDialog(null, "Este Codigo Ya Existe", "Warning",
							JOptionPane.WARNING_MESSAGE);
					limpiarCajas();
				}

				if (modelo.buscar(producto) != true) {
					producto.setPlatillo(vista.txtPlatillo.getText());
					producto.setCodigo(vista.txtCodigo.getText());
					producto.setPrecio(vista.txtPrecio.getText());
					producto.setCantidad(vista.comboBoxCantidad.getSelectedItem().toString());
					producto.setIngredientes(vista.textAreaIngredientes.getText());

					if (modelo.insertar(producto) == true) {
						JOptionPane.showMessageDialog(null, "Registro Insertado Correctamente.");
						limpiarCajas();
						vista.lblPerfilPlatillo.setVisible(true);
						vista.btnLimpiar.setVisible(true);
						vista.textAreaPerfilPlatillo.setVisible(true);
						vista.textAreaPerfilPlatillo.setText("PLATILLO: " + producto.getPlatillo() + "\n\nREGISTRO: "
								+ producto.getCodigo() + "\n\nPRECIO: " + producto.getPrecio() + "\n\nCANTIDAD: "
								+ producto.getCantidad() + "\n\nINGREDIENTES: \n" + producto.getIngredientes());

					} else {
						JOptionPane.showMessageDialog(null, "Error al insertar registro");
						limpiarCajas();
					}

				}
			}
		}

		// Buscar Platillo
		if (botonAcciones.getSource() == vista.btnBuscar) {
			producto.setCodigo(vista.txtCodigo.getText());

			if (modelo.buscar(producto) == true) {
				JOptionPane.showMessageDialog(null, "Codigo Encontrado.");
				vista.lblPerfilPlatillo.setVisible(true);
				vista.textAreaPerfilPlatillo.setVisible(true);
				vista.btnLimpiar.setVisible(true);
				vista.btnModificar.setVisible(true);
				vista.textAreaIngredientes.setVisible(true);
				vista.lblIngredientes.setVisible(true);

				vista.textAreaPerfilPlatillo.setText("idProducto: " + String.valueOf(producto.getIdProducto())
						+ "\n\nPLATILLO: " + producto.getPlatillo() + "\n\nREGISTRO: " + producto.getCodigo()
						+ "\n\nPRECIO: " + producto.getPrecio() + "\n\nCANTIDAD: " + producto.getCantidad()
						+ "\n\nINGREDIENTES: \n" + producto.getIngredientes());

				vista.lblPlatillo.setVisible(true);
				vista.txtPlatillo.setVisible(true);
				vista.lblPrecio.setVisible(true);
				vista.txtPrecio.setVisible(true);
				vista.lblCantidad.setVisible(true);
				vista.comboBoxCantidad.setVisible(true);
				vista.btnBuscar.setVisible(false);

				vista.txtPlatillo.setText(producto.getPlatillo());
				vista.txtCodigo.setText(producto.getCodigo());
				vista.txtPrecio.setText(producto.getPrecio());
				vista.comboBoxCantidad.setSelectedIndex(0);
				vista.txtidProducto.setText(String.valueOf(producto.getIdProducto()));
				vista.textAreaIngredientes.setText(producto.getIngredientes());

			} else {
				JOptionPane.showMessageDialog(null, "No existe ese Platillo.");
				limpiarCajas();
			}
		}

		// Modificar
		if (botonAcciones.getSource() == vista.btnModificar) {

			if (String.valueOf(vista.txtPlatillo.getText()).compareTo("") == 0
					|| String.valueOf(vista.txtCodigo.getText()).compareTo("") == 0
					|| String.valueOf(vista.txtPrecio.getText()).compareTo("") == 0
					|| String.valueOf(vista.comboBoxCantidad.getSelectedItem().toString()).compareTo("") == 0
					|| String.valueOf(vista.textAreaIngredientes.getText()).compareTo("") == 0) {
				// poner mensaje el usuario debe de llenar todos los campos de texto
				JOptionPane.showMessageDialog(null, "Debes Completar Todos Los Campos", "Warning",
						JOptionPane.WARNING_MESSAGE);

			} else {

				producto.setIdProducto(Integer.parseInt(vista.txtidProducto.getText()));
				producto.setPlatillo(vista.txtPlatillo.getText());
				producto.setCodigo(vista.txtCodigo.getText());
				producto.setPrecio(vista.txtPrecio.getText());
				producto.setCantidad(vista.comboBoxCantidad.getSelectedItem().toString());
				producto.setIngredientes(vista.textAreaIngredientes.getText());

				if (modelo.modificar(producto) == true) {
					JOptionPane.showMessageDialog(null, "Registro Modificado Correctamente...");
					limpiarCajas();

					vista.textAreaPerfilPlatillo.setText("idProducto: " + String.valueOf(producto.getIdProducto())
							+ "\n\nPLATILLO: " + producto.getPlatillo() + "\n\nREGISTRO: " + producto.getCodigo()
							+ "\n\nPRECIO: " + producto.getPrecio() + "\n\nCANTIDAD: " + producto.getCantidad()
							+ "\n\nINGREDIENTES: \n" + producto.getIngredientes());

				} else {
					JOptionPane.showMessageDialog(null, "No es posible modificar este Registro");
					limpiarCajas();
				}

			}

		}

		
		
		
		
		
		
		
		
		if (botonAcciones.getSource() == vista.btnEliminar) {

			if (String.valueOf(vista.txtCodigo.getText()).compareTo("") == 0) {
				JOptionPane.showMessageDialog(null, "Indica el Codigo a Eliminar", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {

				producto.setCodigo(vista.txtCodigo.getText());

				if (modelo.buscar(producto) == true) {
					// Registro encontrado
					// tomas el id de idProducto
					//JOptionPane.showMessageDialog(null, "Registro encontrado");

					int response = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres Eliminar este Registro?",
							"Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					// Condicion del JOptionPane
					if (response == JOptionPane.YES_OPTION) {

						vista.txtidProducto.setText(String.valueOf(producto.getIdProducto()));
						producto.setIdProducto(Integer.parseInt(vista.txtidProducto.getText()));

						if (modelo.eliminar(producto) == true) {
							JOptionPane.showMessageDialog(null, "Registro Eliminado Correctamente.");
							limpiarCajas();
						}
					} 

					if (response == JOptionPane.NO_OPTION) {
						limpiarCajas();
					}

				}else {
					JOptionPane.showMessageDialog(null, "Registro No Encontrado.");
					limpiarCajas();
				}

			}

		}
		
		
		
		if (botonAcciones.getSource() == vista.btnLimpiar) {
			limpiarCajas();
		}
		
		
		
	}

	public void limpiarCajas() {
		vista.txtPlatillo.setText("");
		vista.txtCodigo.setText("");
		vista.txtPrecio.setText("");
		vista.comboBoxCantidad.setSelectedIndex(0);
		vista.textAreaIngredientes.setText("");
		vista.textAreaPerfilPlatillo.setText("");
	}
}