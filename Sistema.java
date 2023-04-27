import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sistema {

    private ArrayList<Voo> voos;

    public Sistema() { // Quando Inicializado em outra classe ele cria o ArrayList e o método "criarVoos"
        voos = new ArrayList<>();
        criarVoos();
    }

    private void criarVoos() { // Criando os Voos manualmente
        Voo voo1 = new Voo(325, 2, 4075, "Brasil", "Africa do Sul", LocalDateTime.of(2023, 4, 15, 15, 10),
                LocalDateTime.of(2023, 4, 17, 8, 10));
        Voo voo2 = new Voo(457, 150, 3593, "Brasil", "Japão", LocalDateTime.of(2023, 4, 16, 22, 25),
                LocalDateTime.of(2023, 4, 17, 15, 00));
        Voo voo3 = new Voo(789, 100, 3700, "Brasil", "Inglaterra", LocalDateTime.of(2023, 4, 17, 21, 45),
                LocalDateTime.of(2023, 4, 18, 17, 35));

        voos.add(voo1);
        voos.add(voo2);
        voos.add(voo3);
    }

    public ArrayList<Voo> getVoos() { // Retornando a lista de voss para outras classes
        return voos;
    }

    public List<Voo> buscarVoosPorNumero(int numeroVoo) { // Busca o Voo pelo número do mesmo
        List<Voo> voosEncontrados = new ArrayList<>();
        for (Voo voo : voos) {
            if (voo.getNumeroVoo() == numeroVoo) {
                voosEncontrados.add(voo);
            }
        }
        return voosEncontrados;
    }

    public List<Voo> buscarVoosPorOrigem(String origem) { // Busca o Voo pela origem do mesmo
        List<Voo> voosEncontrados = new ArrayList<>();
        for (Voo voo : voos) {
            if (voo.getOrigem().trim().equalsIgnoreCase(origem)) {
                voosEncontrados.add(voo);
            }
        }
        return voosEncontrados;
    }

    public List<Voo> buscarVoosPorDestino(String destino) { // Busca o Voo pelo destino do mesmo
        List<Voo> voosEncontrados = new ArrayList<>();
        for (Voo voo : voos) {
            if (voo.getDestino().trim().equalsIgnoreCase(destino)) {
                voosEncontrados.add(voo);
            }
        }
        return voosEncontrados;
    }
}