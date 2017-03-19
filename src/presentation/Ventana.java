package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import logic.Procesador;
import perssistence.Proceso;

/**
 * @author Usuario
 * 
 */
public class Ventana extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	private JPanel panelNorte;
	private JTextField tfEntrada;
	private JTextField tfTiempo;
	private JButton btnAgregar;
	private JButton btnIniciar;
	private int cantidad = 1;
	private ArrayList<Proceso> listaNuevos;
	private Procesador procesador;

	private JPanel panelCentro;
	private JList<Proceso> jlListos;
	private DefaultListModel<Proceso> dmListos;
	private JList<Proceso> jlEjecutados;
	private DefaultListModel<Proceso> dmEjecutados;
	private JList<Proceso> jlBloqueados;
	private DefaultListModel<Proceso> dmBloqueados;
	private JList<Proceso> jlFinalizados;
	private DefaultListModel<Proceso> dmFinalizados;

	private JLabel ejecutando;

	private JPanel panelSur;
	private JButton btnBloquear;
	private JButton btnInterrumpir;

	/**
	 * 
	 */
	public Ventana() {
		super("Mono-programación");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.cyan);

		panelNorte = new JPanel(new FlowLayout());
		// panelNorte.setLayout(null);

		panelNorte.setBorder(BorderFactory
				.createTitledBorder("Ingreso de datos"));

		tfEntrada = new JTextField(13);
		tfEntrada.setBorder(BorderFactory
				.createTitledBorder("Nombre del Proceso"));

		tfTiempo = new JTextField(13);
		tfTiempo.setBorder(BorderFactory
				.createTitledBorder("Tiempo de ejecucion"));

		btnAgregar = new JButton("Agregar proceso");
		btnAgregar.setActionCommand("NUEVO");
		btnAgregar.addActionListener(this);

		btnIniciar = new JButton("Iniciar");
		btnIniciar.setActionCommand("START");
		btnIniciar.addActionListener(this);
		btnIniciar.setEnabled(false);

		panelNorte.add(tfEntrada);
		panelNorte.add(tfTiempo);
		panelNorte.add(btnAgregar);
		panelNorte.add(btnIniciar);

		panelCentro = new JPanel(new FlowLayout());
		panelCentro.setBorder(BorderFactory.createTitledBorder("Reportes"));

		jlListos = new JList<Proceso>();
		JScrollPane scrollpanelListos = new JScrollPane();
		scrollpanelListos.setViewportView(jlListos);

		jlListos.setBorder(BorderFactory.createTitledBorder("Listos"));
		dmListos = new DefaultListModel<Proceso>();
		// dmListos.addElement(new Proceso());
		// dmListos.addElement("listos   ");
		// dmListos.addElement("listos   ");
		// dmListos.addElement("listos   ");
		jlListos.setModel(dmListos);

		jlEjecutados = new JList<Proceso>();
		jlEjecutados.setBorder(BorderFactory.createTitledBorder("Ejecutados"));
		JScrollPane scrollpanelEjecutados = new JScrollPane();
		scrollpanelEjecutados.setViewportView(jlEjecutados);
		dmEjecutados = new DefaultListModel<Proceso>();
		// dmEjecutados.addElement(new Proceso());
		// dmEjecutados.addElement("ejecutados   ");
		// dmEjecutados.addElement("ejecutados   ");
		// dmEjecutados.addElement("ejecutados   ");
		jlEjecutados.setModel(dmEjecutados);

		jlBloqueados = new JList<Proceso>();
		jlBloqueados.setBorder(BorderFactory.createTitledBorder("Bloqueados"));
		JScrollPane scrollpanelBloqueados = new JScrollPane();
		scrollpanelBloqueados.setViewportView(jlBloqueados);
		dmBloqueados = new DefaultListModel<Proceso>();
		// dmBloqueados.addElement(new Proceso());
		// dmBloqueados.addElement("Bloqueados   ");
		// dmBloqueados.addElement("Bloqueados   ");
		// dmBloqueados.addElement("Bloqueados   ");
		jlBloqueados.setModel(dmBloqueados);

		jlFinalizados = new JList<Proceso>();
		jlFinalizados
				.setBorder(BorderFactory.createTitledBorder("Finalizados"));
		JScrollPane scrollpanelFinalizados = new JScrollPane();
		scrollpanelFinalizados.setViewportView(jlFinalizados);
		dmFinalizados = new DefaultListModel<Proceso>();
		// dmFinalizados.addElement(new Proceso());
		// dmFinalizados.addElement("Finalizados   ");
		// dmFinalizados.addElement("Finalizados   ");
		// dmFinalizados.addElement("Finalizados   ");
		jlFinalizados.setModel(dmFinalizados);

		scrollpanelListos.setSize(100, 400);
		scrollpanelEjecutados.setSize(100, 400);
		scrollpanelBloqueados.setSize(100, 400);
		scrollpanelFinalizados.setSize(100, 400);

		panelCentro.add(scrollpanelListos);
		panelCentro.add(scrollpanelEjecutados);
		panelCentro.add(scrollpanelFinalizados);
		panelCentro.add(scrollpanelBloqueados);
		// panelCentro.add(ejecutando);
		panelSur = new JPanel();
		panelSur.setBorder(BorderFactory.createTitledBorder("Configurar"));

		btnBloquear = new JButton("Bloquear");
		btnBloquear.setActionCommand("BLOQUEAR");
		btnBloquear.addActionListener(this);
		btnBloquear.setEnabled(false);

		btnInterrumpir = new JButton("Interrumpir");
		btnInterrumpir.setActionCommand("INTERRUMPIR");
		btnInterrumpir.addActionListener(this);
		btnInterrumpir.setEnabled(false);

		ejecutando = new JLabel("Procesador sin usar - No hay procesos");
		TitledBorder tituloBorde = BorderFactory
				.createTitledBorder("Uso del Procesador");
		tituloBorde.setTitleColor(Color.WHITE);
		ejecutando.setBorder(tituloBorde);
		ejecutando.setOpaque(true);
		ejecutando.setBackground(Color.GRAY);
		ejecutando.setForeground(Color.WHITE);

		panelSur.add(ejecutando);
		panelSur.add(btnBloquear);
		panelSur.add(btnInterrumpir);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		getContentPane().add(panelSur, BorderLayout.SOUTH);

		listaNuevos = new ArrayList<Proceso>();
		procesador = new Procesador(this);
		pack();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("NUEVO")) {
			if (!tfEntrada.getText().isEmpty() && !tfTiempo.getText().isEmpty()) {
				// System.out.println("Nuevo");
				ArrayList<String> temp = new ArrayList<String>();
				String nombre = tfEntrada.getText();
				int tiempo = Integer.parseInt(tfTiempo.getText());
				Proceso proceso = new Proceso(cantidad, nombre, tiempo, 0,
						false, false, temp);
				listaNuevos.add(proceso);
				dmListos.addElement(proceso);
				cantidad++;
				btnIniciar.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Debe llenar los campos");
			}
		}

		if (e.getActionCommand().equals("START")) {
			// System.out.println("Start");
			crearProcesos();
			btnBloquear.setEnabled(true);
		}

		if (e.getActionCommand().equals("BLOQUEAR")) {
			// System.out.println("bloquear");
			btnInterrumpir.setEnabled(true);
			bloquear();
		}

		if (e.getActionCommand().equals("INTERRUMPIR")) {
			System.out.println("interrumpir");
			interrumpir();
		}
	}

	/**
	 * 
	 */
	public void crearProcesos() {
		ArrayList<Proceso> aux = listaNuevos;
		procesador = new Procesador(this);
		procesador.agregarProcesos(aux);
		procesador.iniciar();
	}

	/**
	 * 
	 */
	private void bloquear() {
		btnBloquear.setEnabled(false);
		Thread hilo = new Thread(this);
		hilo.start();
	}

	/**
	 * 
	 */
	private void interrumpir() {
		procesador.interrumpirProceso();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		procesador.pausar();
		int tiempo = Integer.parseInt(JOptionPane
				.showInputDialog("Ingrese el tiempo de ejecución"));
		String operacion = JOptionPane
				.showInputDialog("Ingrese el tipo de operación");
		procesador.bloquearProceso(tiempo, operacion);
	}

	/**
	 * @return serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return panelNorte
	 */
	public JPanel getPanelNorte() {
		return panelNorte;
	}

	/**
	 * @return tfEntrada
	 */
	public JTextField getTfEntrada() {
		return tfEntrada;
	}

	/**
	 * @return tfTiempo
	 */
	public JTextField getTfTiempo() {
		return tfTiempo;
	}

	/**
	 * @return btnAgregar
	 */
	public JButton getBtnNuevo() {
		return btnAgregar;
	}

	/**
	 * @return btnIniciar
	 */
	public JButton getBtnStart() {
		return btnIniciar;
	}

	/**
	 * @return cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @return panelCentro
	 */
	public JPanel getPanelCentro() {
		return panelCentro;
	}

	/**
	 * @return jlListos
	 */
	public JList<Proceso> getJlListos() {
		return jlListos;
	}

	/**
	 * @return dmListos
	 */
	public DefaultListModel<Proceso> getDmListos() {
		return dmListos;
	}

	/**
	 * @return jlEjecutados
	 */
	public JList<Proceso> getJlEjecutados() {
		return jlEjecutados;
	}

	/**
	 * @return dmEjecutados
	 */
	public DefaultListModel<Proceso> getDmEjecutados() {
		return dmEjecutados;
	}

	/**
	 * @return jlBloqueados
	 */
	public JList<Proceso> getJlBloqueados() {
		return jlBloqueados;
	}

	/**
	 * @return dmBloqueados
	 */
	public DefaultListModel<Proceso> getDmBloqueados() {
		return dmBloqueados;
	}

	/**
	 * @return jlFinalizados
	 */
	public JList<Proceso> getJlFinalizados() {
		return jlFinalizados;
	}

	/**
	 * @return dmFinalizados
	 */
	public DefaultListModel<Proceso> getDmFinalizados() {
		return dmFinalizados;
	}

	/**
	 * @return panelSur
	 */
	public JPanel getPanelSur() {
		return panelSur;
	}

	/**
	 * @return btnBloquear
	 */
	public JButton getBtnBloquear() {
		return btnBloquear;
	}

	/**
	 * @return btnInterrumpir
	 */
	public JButton getBtnInterrumpir() {
		return btnInterrumpir;
	}

	/**
	 * @return ejecutando
	 */
	public JLabel getEjecutando() {
		return ejecutando;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ventana v = new Ventana();
		v.setVisible(true);
		v.setResizable(true);
		v.setSize(900, 600);
		v.setLocationRelativeTo(null);
	}

}
