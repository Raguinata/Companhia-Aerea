import java.time.LocalDateTime;

public class Voo {

    private int numeroVoo;
    private int capacidadeAeronave;
    private double precoVoo;
    private String origem;
    private String destino;
    private LocalDateTime horarioPartida;
    private LocalDateTime horarioChegada;

    public Voo() { // Método Construtor Padrão

    }

    public Voo(int numeroVoo, int capacidadeAeronave, double precoVoo, String origem, String destino,
    LocalDateTime horarioPartida, LocalDateTime horarioChegada) {
        this.numeroVoo = numeroVoo;
        this.capacidadeAeronave = capacidadeAeronave;
        this.precoVoo = precoVoo;
        this.origem = origem;
        this.destino = destino;
        this.horarioPartida = horarioPartida;
        this.horarioChegada = horarioChegada;
    }

    /**
     * @return int return the numeroVoo
     */
    public int getNumeroVoo() {
        return numeroVoo;
    }

    /**
     * @param numeroVoo the numeroVoo to set
     */
    public void setNumeroVoo(int numeroVoo) {
        this.numeroVoo = numeroVoo;
    }

    /**
     * @return int return the capacidadeAeronave
     */
    public int getCapacidadeAeronave() {
        return capacidadeAeronave;
    }

    /**
     * @param capacidadeAeronave the capacidadeAeronave to set
     */
    public void setCapacidadeAeronave(int capacidadeAeronave) {
        this.capacidadeAeronave = capacidadeAeronave;
    }

    /**
     * @return String return the origem
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    /**
     * @return String return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return LocalDateTime return the horarioPartida
     */
    public LocalDateTime getHorarioPartida() {
        return horarioPartida;
    }

    /**
     * @param horarioPartida the horarioPartida to set
     */
    public void setHorarioPartida(LocalDateTime horarioPartida) {
        this.horarioPartida = horarioPartida;
    }

    /**
     * @return LocalDateTime return the horarioChegada
     */
    public LocalDateTime getHorarioChegada() {
        return horarioChegada;
    }

    /**
     * @param horarioChegada the horarioChegada to set
     */
    public void setHorarioChegada(LocalDateTime horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    /**
     * @return double return the precoVoo
     */
    public double getPrecoVoo() {
        return precoVoo;
    }

    /**
     * @param precoVoo the precoVoo to set
     */
    public void setPrecoVoo(double precoVoo) {
        this.precoVoo = precoVoo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Número do Voo: " + numeroVoo + "\n");
        sb.append("Capacidade da Aeronave: " + capacidadeAeronave + "\n");
        sb.append("Origem: " + origem + "\n");
        sb.append("Destino: " + destino + "\n");
        sb.append("Horário de Partida: " + horarioPartida + "\n");
        sb.append("Horário de Chegada: " + horarioChegada + "\n");
        sb.append("////////////////////////////////////////////////////////////////\n");

        return sb.toString();

    }

}
