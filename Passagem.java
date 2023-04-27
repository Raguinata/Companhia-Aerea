import java.util.ArrayList;
import java.util.List;

public class Passagem {

    protected String nome;
    protected byte idade;
    protected String numeroPassaporte;
    protected double precoTotal;
    protected int numVoo;
    protected byte escolhaClasse;
    protected int indiceVoo;
    private int assento;
    private ArrayList<String> comidasEscolhidas;
    private ArrayList<Passagem> reservas;

    Sistema sistema = new Sistema();
    ArrayList<Voo> voos = sistema.getVoos();

    public Passagem() {
        super();
        reservas = new ArrayList<>();
    }

    public Passagem(String nome, byte idade, String numeroPassaporte, int numVoo, byte escolhaClasse, int assento,
            ArrayList<String> comidasEscolhidas) {
        this.nome = nome;
        this.idade = idade;
        this.numeroPassaporte = numeroPassaporte;
        this.indiceVoo = getIndiceVooPorNumero(numVoo);
        this.numVoo = numVoo;
        this.comidasEscolhidas = comidasEscolhidas;

        if (escolhaClasse == 1) {
            ClasseEconomica classeEconomica = new ClasseEconomica();
            this.precoTotal = classeEconomica.calcularPreco();
        } else if (escolhaClasse == 2) {
            ClasseExecutiva classeExecutiva = new ClasseExecutiva(comidasEscolhidas.size());
            this.precoTotal = classeExecutiva.calcularPreco();
        }

        this.assento = assento + 1;
    }

    public ArrayList<Passagem> getReservas() { // Retorna o ArrayList reservas para usa-lo em outrs clases
        return reservas;
    }

    public void adicionarPassageiro(Passagem passagem) { // Adiciona os passageiros que forem feitos na classe "SistemaOperador" e o adiciona no ArrayList Reservas
        reservas.add(passagem);
    }

    public int getIndiceVooPorNumero(int numVoo) {
        for (int i = 0; i < voos.size(); i++) {
            if (voos.get(i).getNumeroVoo() == numVoo) {
                return i;
            }
        }
        return -1;
    }

    public List<Passagem> buscarVoosPorNumero(int numVoo) { // Busca passageiros que estão nesse voo
        List<Passagem> voosEncontrados = new ArrayList<>();
        for (Passagem passagem : reservas) {
            if (passagem.numVoo == numVoo) {
                voosEncontrados.add(passagem);
            }
        }
        return voosEncontrados;
    }

    public List<Passagem> buscarPorNome(String nome) { // Busca passageiros pelos nomes
        List<Passagem> passagensEncontradas = new ArrayList<>();
        for (Passagem passagem : reservas) {
            if (passagem.getNome().equalsIgnoreCase(nome)) {
                passagensEncontradas.add(passagem);
            }
        }
        return passagensEncontradas;
    }

    public List<Passagem> buscarPorNumeroPassaporte(String numeroPassaporte) { // Busca passageiros pelo número do passaporte
        List<Passagem> passagensEncontradas = new ArrayList<>();
        for (Passagem passagem : reservas) {
            if (passagem.getNumeroPassaporte().equals(numeroPassaporte)) {
                passagensEncontradas.add(passagem);
            }
        }
        return passagensEncontradas;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return byte return the idade
     */
    public byte getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(byte idade) {
        this.idade = idade;
    }

    /**
     * @return String return the numeroPassaporte
     */
    public String getNumeroPassaporte() {
        return numeroPassaporte;
    }

    /**
     * @param numeroPassaporte the numeroPassaporte to set
     */
    public void setNumeroPassaporte(String numeroPassaporte) {
        this.numeroPassaporte = numeroPassaporte;
    }

    /**
     * @return double return the precoTotal
     */
    public double getPrecoTotal() {
        return precoTotal;
    }

    /**
     * @param precoTotal the precoTotal to set
     */
    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    /**
     * @return int return the assento
     */
    public int getAssento() {
        return assento;
    }

    /**
     * @param assento the assento to set
     */
    public void setAssento(int assento) {
        this.assento = assento;
    }

    /**
     * @return ArrayList<String> return the comidasEscolhidas
     */
    public ArrayList<String> getComidasEscolhidas() {
        return comidasEscolhidas;
    }

    /**
     * @param comidasEscolhidas the comidasEscolhidas to set
     */
    public void setComidasEscolhidas(ArrayList<String> comidasEscolhidas) {
        this.comidasEscolhidas = comidasEscolhidas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(ArrayList<Passagem> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: " + this.nome + "\n");
        sb.append("Idade: " + this.idade + "\n");
        sb.append("Número do Passaporte: " + this.numeroPassaporte + "\n");
        sb.append("Número do Voo: " + this.numVoo + "\n");
        sb.append("Pais de Origem: " + voos.get(indiceVoo).getOrigem() + "\n");
        sb.append("Pais de Destino: " + voos.get(indiceVoo).getDestino() + "\n");
        sb.append("Data/Horário de Partida: " + voos.get(indiceVoo).getHorarioPartida() + "\n");
        sb.append("Data/Horário de Chegada: " + voos.get(indiceVoo).getHorarioChegada() + "\n");
        sb.append("Assento: " + this.assento + "\n");

        if (!comidasEscolhidas.isEmpty()) {
            sb.append("Refeições Escolhidas: ");
            for (String comida : comidasEscolhidas) {
                sb.append(comida + ", ");
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("\n");
        }

        sb.append("Valor Total: " + this.precoTotal + "\n");
        sb.append(
                "////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n");

        return sb.toString();
    }

}
