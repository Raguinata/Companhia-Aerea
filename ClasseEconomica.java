public class ClasseEconomica extends Passagem {

    public double calcularPreco() { // Calcula o preço da passagem de acordo com as regras de negocio
        return voos.get(indiceVoo).getPrecoVoo();
    }
}