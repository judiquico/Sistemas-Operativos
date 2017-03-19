package perssistence;

import java.util.ArrayList;

/**
 * @author Usuario
 * 
 */
public class Proceso {
	private int pid;
	private String nombre;
	private int tiempo;
	private int tiempoAux;
	private int tiempoBloqueo;
	private boolean isBloqueado;
	private boolean isError;
	private ArrayList<String> operaciones;

	/**
	 * 
	 */
	public Proceso() {
	}

	/**
	 * @param pid
	 * @param nombre
	 * @param tiempo
	 * @param tiempoBloqueo
	 * @param isBloqueado
	 * @param isError
	 * @param operaciones
	 */
	public Proceso(int pid, String nombre, int tiempo, int tiempoBloqueo,
			boolean isBloqueado, boolean isError, ArrayList<String> operaciones) {
		super();
		this.pid = pid;
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.tiempoAux = 0;
		this.tiempoBloqueo = tiempoBloqueo;
		this.isBloqueado = isBloqueado;
		this.isError = isError;
		this.operaciones = operaciones;
	}

	/**
	 * @return pid
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @param pid
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return tiempo
	 */
	public int getTiempo() {
		return tiempo;
	}

	/**
	 * @param tiempo
	 */
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * @return tiempoAux
	 */
	public int getTiempoAux() {
		return tiempoAux;
	}

	/**
	 * @param tiempoAux
	 */
	public void setTiempoAux(int tiempoAux) {
		this.tiempoAux = tiempoAux;
	}

	/**
	 * @return tiempoBloqueo
	 */
	public int getTiempoBloqueo() {
		return tiempoBloqueo;
	}

	/**
	 * @param tiempoBloqueo
	 */
	public void setTiempoBloqueo(int tiempoBloqueo) {
		this.tiempoBloqueo = tiempoBloqueo;
	}

	/**
	 * @return isBloqueado
	 */
	public boolean isBloqueado() {
		return isBloqueado;
	}

	/**
	 * @param isBloqueado
	 */
	public void setBloqueado(boolean isBloqueado) {
		this.isBloqueado = isBloqueado;
	}

	/**
	 * @return isError
	 */
	public boolean isError() {
		return isError;
	}

	/**
	 * @param isError
	 */
	public void setError(boolean isError) {
		this.isError = isError;
	}

	/**
	 * @return operaciones
	 */
	public ArrayList<String> getOperaciones() {
		return operaciones;
	}

	/**
	 * @param operaciones
	 */
	public void setOperaciones(ArrayList<String> operaciones) {
		this.operaciones = operaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "pid: " + pid + "| nombre: " + nombre + "| tiempo: " + tiempoAux
				+ " | tiempoBloqueo: " + tiempoBloqueo + " | Operaciones: "
				+ operaciones;
	}

	/**
	 * @return pid: " + pid + "| nombre: " + nombre + "| tiempo: " + tiempo +
	 *         " | tiempoBloqueo: " + tiempoBloqueo
	 */
	public String toString2() {
		return "pid: " + pid + "| nombre: " + nombre + "| tiempo: " + tiempo
				+ " | tiempoBloqueo: " + tiempoBloqueo + " | Operaciones: "
				+ operaciones;
	}

}
