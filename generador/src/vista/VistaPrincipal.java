package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Generador;
import modelo.NumeroAleatorio;

import java.awt.Frame;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private Generador generador;
	private JTextField txtNumeros;
	private JTextField txtDigitos;
	private JTextField txtSeed;
	private JTable tablaGenerador;
	private JButton btnGenerar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = new VistaPrincipal();
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
	public VistaPrincipal() {
		setTitle("Generador");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setSize(new Dimension(1200, 600));
//		setPreferredSize(new Dimension(1200, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		generador = new Generador();
		
		JPanel panelOpciones = new JPanel();
		contentPane.add(panelOpciones, BorderLayout.WEST);
		panelOpciones.setLayout(new GridLayout(3, 2, 0, 200));
		
		JLabel lblSeed = new JLabel("Seed");
		lblSeed.setFont(new Font("Manjari Regular", Font.BOLD, 20));
		lblSeed.setHorizontalAlignment(SwingConstants.CENTER);
		panelOpciones.add(lblSeed);
		
		txtSeed = new JTextField();
		txtSeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSeed.getText().matches("[0-9]+")) {
					if (Integer.parseInt(txtSeed.getText()) > 15 && Integer.parseInt(txtSeed.getText()) < 100) {
						txtNumeros.requestFocus();
					} else {
						JOptionPane.showMessageDialog(null, "La semilla debe ser un número mayor a 15 y menor a 100.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "La semilla sólo puede contener números enteros.");
				}
				
			}
		});
		txtSeed.setToolTipText("La semilla debe ser mayor a 15 y menor a 100");
		txtSeed.setFont(new Font("Manjari Regular", Font.PLAIN, 20));
		panelOpciones.add(txtSeed);
		txtSeed.setColumns(10);
		
		JLabel lblNumeros = new JLabel("Tamaño");
		lblNumeros.setFont(new Font("Manjari Regular", Font.BOLD, 20));
		lblNumeros.setHorizontalAlignment(SwingConstants.CENTER);
		panelOpciones.add(lblNumeros);
		
		txtNumeros = new JTextField();
		txtNumeros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtNumeros.getText().matches("[0-9]+")) {
					if (!txtNumeros.getText().equals("0")) {
						txtDigitos.requestFocus();
					} else {
						JOptionPane.showMessageDialog(null, "El tamaño del generador no puede ser cero.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "El tamaño del generador sólo puede contener números enteros.");
				}
			}
		});
		txtNumeros.setToolTipText("Tamaño del generador");
		txtNumeros.setFont(new Font("Manjari Regular", Font.PLAIN, 20));
		panelOpciones.add(txtNumeros);
		txtNumeros.setColumns(10);
		
		JLabel lblDigitos = new JLabel("Dígitos");
		lblDigitos.setFont(new Font("Manjari Regular", Font.BOLD, 20));
		lblDigitos.setHorizontalAlignment(SwingConstants.CENTER);
		panelOpciones.add(lblDigitos);
		
		txtDigitos = new JTextField();
		txtDigitos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtDigitos.getText().matches("[0-9]+")) {
					if (Integer.parseInt(txtDigitos.getText()) >= 3 && Integer.parseInt(txtDigitos.getText()) <= 5) {
						btnGenerar.requestFocus();
					} else {
						JOptionPane.showMessageDialog(null, "La cantidad de dígitos tomados puede ser 3, 4 o 5.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "La cantidad de dígitos sólo puede contener números enteros.");
				}
			}
		});
		txtDigitos.setToolTipText("Ingreser un número entre 3 y 5");
		txtDigitos.setFont(new Font("Manjari Regular", Font.PLAIN, 20));
		panelOpciones.add(txtDigitos);
		txtDigitos.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (camposLlenos()) {
					generador = new Generador();
					generarNumeros(txtSeed.getText(), txtNumeros.getText(), txtDigitos.getText());
					generarTabla();
				} else {
					JOptionPane.showMessageDialog(null, "Error. Campos no completados.");
				}
			}
		});
		btnGenerar.setFont(new Font("Manjari Regular", Font.BOLD, 20));
		panel.add(btnGenerar);
		
		tablaGenerador = new JTable();
		contentPane.add(tablaGenerador, BorderLayout.NORTH);
		
		JScrollPane panelTabla = new JScrollPane(tablaGenerador);
		contentPane.add(panelTabla, BorderLayout.CENTER);		
		
	}
	
	private void generarNumeros(String seed, String numeros, String digitos) {
		try {
			generador.generar(Integer.parseInt(seed), Integer.parseInt(numeros), Integer.parseInt(digitos));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getRootPane(), "ERROR AL GENERAR");
		}
	}
	
	private void generarTabla() {
		DefaultTableModel modelo = new DefaultTableModel(0,10);
		List<Integer> lista = new ArrayList<>();
		
		int con = 0;
		
		for (int i = 0; i < generador.getNumeros().size(); i++) {
			if (con == 9) {
				lista.add(generador.getNumeros().get(i).getNum());
				modelo.addRow(lista.toArray());
				con = 0;
				lista.clear();
			} else {
				lista.add(generador.getNumeros().get(i).getNum());
				con++;
			} 
		}
		
		tablaGenerador.setModel(modelo);
	}
	
	private boolean camposLlenos() {
		if (!txtSeed.getText().isEmpty() && !txtNumeros.getText().isEmpty() && !txtDigitos.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
