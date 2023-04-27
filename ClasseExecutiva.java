public class ClasseExecutiva extends Passagem {

    private int tamanhoRefeicoes;

    public ClasseExecutiva(int tamanhoRefeicoes) {
        this.tamanhoRefeicoes = tamanhoRefeicoes;
    }

    public double calcularPreco() { // Calcula o preço da passagem de acordo com as regras de negocio
        if (tamanhoRefeicoes == 0) {
            return voos.get(indiceVoo).getPrecoVoo() * 1.5;
        }
        else {
            return ((voos.get(indiceVoo).getPrecoVoo() * 1.5) + (voos.get(indiceVoo).getPrecoVoo() * 1.5) * (tamanhoRefeicoes * 0.01));
        }
    }
}
