package logic;

import java.awt.Color;
import java.util.ArrayList;
import perssistence.Proceso;
import presentation.Ventana;

/**
 * @author Juan Diego
 *
 */
public class Procesador implements Runnable {

    private ArrayList<Proceso> listos;
    private boolean pausado;
    private boolean finalizado;
    private Thread hilo;
    private Ventana ventana;

    /**
     * @param ventana
     */
    public Procesador(Ventana ventana) {
        this.ventana = ventana;
        pausado = true;
        listos = new ArrayList<Proceso>();
        hilo = new Thread(this);
        hilo.start();
    }

    /**
     * @param listos
     */
    public void agregarProcesos(ArrayList<Proceso> listos) {
        this.listos = listos;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while (!finalizado) {
            if (!pausado) {
                procesar();
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     */
    private void procesar() {
        if (listos.size() > 0) {
            Proceso proceso = listos.get(0);
            if (!proceso.isBloqueado()) {
                ventana.getEjecutando().setBackground(Color.BLUE);
                if (!sumarSegundo()) {
                    // agregar a finalizados
                    ventana.getDmEjecutados().addElement(proceso);
                    ventana.getDmFinalizados().addElement(proceso);
                    System.out.println("Finalizado");
                    listos.remove(0);
                } else {
                    // System.out.println("se le resta");
                    // ventana.getDmEjecutados().addElement(proceso);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // ventana.getEjecutando().setText("Bloqueado: ");
                // System.out.println("PPPPPPPP bloqueado");
                // ventana.getDmEjecutados().addElement(proceso);
                // ventana.getDmBloqueados().addElement(proceso);

            }
        } else {
            limpiarProcesador();
            // System.out.println("Procesos finalizados");
            // ventana.getEjecutando().setBackground(Color.GRAY);
            // ventana.getEjecutando().setText(
            // "Procesador sin usar - No hay procesos");
            // ventana.getBtnBloquear().setEnabled(false);
            // ventana.getBtnInterrumpir().setEnabled(false);
            // // mostarProcesos();
            // pausado = true;
            // // finalizado = true;
        }
    }

    public void limpiarProcesador() {
        System.out.println("Procesos finalizados");
        ventana.getEjecutando().setBackground(Color.GRAY);
        ventana.getEjecutando()
                .setText("Procesador sin usar - No hay procesos");
        ventana.getBtnBloquear().setEnabled(false);
        ventana.getBtnInterrumpir().setEnabled(false);
        // mostarProcesos();
        pausado = true;
        // finalizado = true;
    }

    /**
     * @param tiempo
     */
    public void bloquearProceso(int tiempo, String operacion) {
        ventana.getDmEjecutados().addElement(listos.get(0));
        ventana.getDmBloqueados().addElement(listos.get(0));

        listos.get(0).setBloqueado(true);
        listos.get(0).getOperaciones().add(operacion);
        int tiempoActual = listos.get(0).getTiempoBloqueo();
        for (int i = 1; i <= tiempo; i++) {
            // System.out.println("Bloqueo: " + tiempo);
            listos.get(0).setTiempoBloqueo(tiempoActual + i);
            ventana.getEjecutando().setBackground(Color.BLACK);
            ventana.getEjecutando()
                    .setText("Esperando proceso bloqueado: " + i);
            if (!listos.get(0).isError()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Proceso interrumpido");
                ventana.getBtnBloquear().setEnabled(false);
                ventana.getBtnInterrumpir().setEnabled(false);
                ventana.getEjecutando().setBackground(Color.RED);
                ventana.getEjecutando().setText("Proceso interrumpido");

                ventana.getDmFinalizados().addElement(listos.get(0));
                listos.remove(0);
                break;
                // listos.get(0).setError(true);
            }
        }

        if (listos.size() > 0) {
            ventana.getBtnBloquear().setEnabled(true);
            listos.get(0).setBloqueado(false);
            pausado = false;
        } else {
            limpiarProcesador();
            finalizado = true;
        }
    }

    /**
     *
     */
    public void interrumpirProceso() {
        listos.get(0).setError(true);
    }

    /**
     * @return isActivo
     */
    private boolean sumarSegundo() {
        boolean isActivo = true;
        Proceso aux = listos.get(0);
        if (aux.getTiempo() > 0) {
            // System.out.println(aux.toString2());
            ventana.getEjecutando().setText(aux.toString2());
            aux.setTiempo(aux.getTiempo() - 1);
            aux.setTiempoAux(aux.getTiempoAux() + 1);

        } else {
            isActivo = false;
        }
        return isActivo;
    }

    /**
     *
     */
    public void iniciar() {
        pausado = false;
    }

    /**
     *
     */
    public void pausar() {
        pausado = true;
    }

    /**
     *
     */
    public void finalizar() {
        finalizado = true;
    }

}
