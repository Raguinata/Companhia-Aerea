import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SistemaOperador {

    public void iniciaSistema() {

        // Variaveis Inicio
        String logo = "<html><font face=\"Times New Roman\" size=\"700\">Companhia Aérea Senac</font></html>";
        String opcoes[] = { "Fazer uma Reserva", "Informações de Voos", "Informações de Passageiros", "Sair" };
        String opcoesBuscarVoo[] = { "Listar todos os voos", "Listar um voo específico", "Sair" };
        String opcoesBuscarVoo2[] = { "Número do Voo", "Origem", "Destino", "Sair" };
        String opcoesBuscarPassagem[] = { "Listar todas as passagens", "Listar uma passagem em específico", "Sair" };
        String opcoesBuscarPassagem2[] = { "Número de Voo", "Nome", "Número de Passaporte", "Sair" };
        int vooEscolhido;
        int indiceVooEncontrado = 0;
        int opcao = 0;
        int opcaoBuscaPassageiro;
        int opcaoAuxiliar = 0;
        int contador;
        byte escolhaClasse = 0;
        String resultComida;
        // Variaveis Fim

        Sistema sistema = new Sistema();
        ArrayList<Voo> voos = sistema.getVoos(); // Criando um ArrayList do tipo Voo para armazenar as informações da
                                                 // classe Sistema
        Passagem reserva = new Passagem();
        ArrayList<Passagem> reservas = reserva.getReservas();

        do {
            opcao = JOptionPane.showOptionDialog(null, logo + "\n\nO que você deseja fazer?", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

            switch (opcao) {
                case 0:
                    ArrayList<String> opcoesComida = new ArrayList<String>(); // Declarando a variavel dentro do Case para que ela possa ser resetada
                                                                            // toda vez que for cadastrar uma nova reserva
                    opcoesComida.add("Entrada");
                    opcoesComida.add("Prato principal");
                    opcoesComida.add("Opção vegetariana");
                    opcoesComida.add("Sobremesa");
                    opcoesComida.add("Bebida");

                    ArrayList<String> escolhasUsuario = new ArrayList<String>();

                    // Criação da Interface Inicio
                    JTextField nomeField = new JTextField();
                    JTextField idadeField = new JTextField();
                    JTextField numPassaporteField = new JTextField();
                    JRadioButton economica = new JRadioButton("Econômica");
                    JRadioButton executiva = new JRadioButton("Executiva");
                    ButtonGroup grupo = new ButtonGroup(); // Cria um grupo de botões, e os 2 botões só poderão ser
                                                           // escolhidos individualmente por reserva
                    grupo.add(economica);
                    grupo.add(executiva);

                    Object[] fields = { "Nome: ", nomeField, "Idade: ", idadeField, "Número do Passaporte: ",
                            numPassaporteField, economica, executiva };
                    // Criação da Interface Fim

                    int result = JOptionPane.showConfirmDialog(null, fields, "Adicionar",
                            JOptionPane.OK_CANCEL_OPTION);

                    vooEscolhido = Integer.parseInt(JOptionPane.showInputDialog(null,
                            logo + "\n\nInsira o Número do voo deste(a) passageiro(a)", "Inserir Número do Voo",
                            1));

                    boolean vooEncontrado = false; // Verifica se o Voo existe para poder prosseguir com o codigo
                    for (int i = 0; i < voos.size(); i++) {
                        if (vooEscolhido == voos.get(i).getNumeroVoo()) {
                            vooEncontrado = true;
                            indiceVooEncontrado = i;
                            break;
                        }
                    }

                    if (!vooEncontrado) {
                        JOptionPane.showMessageDialog(null, logo + "\n\nVoo não encontrado, tente novamente", "ERROR",
                                0);
                        break;
                    }

                    contador = 0;

                    for (int i = 0; i < reservas.size(); i++) { // Contando quantas pessoas tem no voo selecionado
                        if (reservas.get(i).numVoo == vooEscolhido) {
                            contador++;
                        }
                    }

                    if (contador >= voos.get(indiceVooEncontrado).getCapacidadeAeronave()) {
                        JOptionPane.showMessageDialog(null, logo + "\n\nO voo " + vooEscolhido + " está lotado!!!",
                                "ERROR", 0);
                        break;
                    }

                    if (result == JOptionPane.OK_OPTION) {
                        if (economica.isSelected()) {
                            escolhaClasse = 1;
                            Passagem novaReserva = new Passagem(nomeField.getText(),
                                    (byte) Integer.parseInt(idadeField.getText()), numPassaporteField.getText(),
                                    vooEscolhido, (byte) escolhaClasse, contador, escolhasUsuario);
                            reservas.add(novaReserva);

                        } else if (executiva.isSelected()) {

                            // Loop para permitir que o usuário escolha suas opções de comida
                            do {
                                // Crie uma caixa de seleção com as opções de comida
                                String[] opcoesSelecionaveis = opcoesComida.toArray(new String[0]);
                                resultComida = (String) JOptionPane.showInputDialog(null, logo
                                        + "\n\nEsse é o nosso cardápio: " +
                                        "\n1. Entrada: Salada mista\n2. Prato principal: Filé de frango grelhado com arroz e batatas assadas\n3. Opção vegetariana: Quiche de cogumelos com salada verde\n4. Sobremesa: Mousse de limão\n5. Bebida: Água mineral \n\n Escolha a(s) opção(ões): ",
                                        "Menu",
                                        JOptionPane.PLAIN_MESSAGE, null, opcoesSelecionaveis, opcoesSelecionaveis[0]);

                                // Adiciona as escolhas do usuário à lista de escolhas de usuário
                                if (resultComida != null && !escolhasUsuario.contains(resultComida)) {
                                    escolhasUsuario.add(resultComida);
                                    opcoesComida.remove(resultComida);
                                }

                                // Enquanto o usuário ainda quiser selecionar opções de comida
                            } while (opcoesComida.size() > 0 && JOptionPane.showConfirmDialog(null,
                                    "Deseja escolher mais alguma opção de comida?", "Menu",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

                            if (escolhasUsuario.size() > 0) {
                                String mensagem = "Você escolheu as seguintes opções de comida:\n";
                                for (String escolha : escolhasUsuario) {
                                    mensagem += "- " + escolha + "\n";
                                }
                                JOptionPane.showMessageDialog(null, mensagem);
                            } else {
                                JOptionPane.showMessageDialog(null, "Você não escolheu nenhuma opção de comida.");
                            }
                            escolhaClasse = 2;
                            Passagem novaReserva = new Passagem(nomeField.getText(),
                                    (byte) Integer.parseInt(idadeField.getText()), numPassaporteField.getText(),
                                    vooEscolhido, (byte) escolhaClasse, contador, escolhasUsuario);
                            reservas.add(novaReserva);
                        }
                    }
                    break;
                case 1:
                    opcaoAuxiliar = JOptionPane.showOptionDialog(null, logo + "\n\nOque você deseja fazer?",
                            "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoesBuscarVoo,
                            opcoesBuscarVoo[0]);

                    if (opcaoAuxiliar == 0) { // Lista todos os Voos
                        StringBuilder sb = new StringBuilder();
                        for (Voo voo : voos) {
                            sb.append(voo.toString()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, logo + "\n\n" + sb.toString());
                    }

                    else if (opcaoAuxiliar == 1) { // Procura um Voo(s) de acordo com uma caracteristica especifica
                        opcaoAuxiliar = JOptionPane.showOptionDialog(null,
                                logo + "\n\nPor qual opção você deseja buscar?",
                                "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                opcoesBuscarVoo2,
                                opcoesBuscarVoo2[0]);

                        if (opcaoAuxiliar == 0) { // Procura Voos de acordo com o Número do Voo
                            int numVoo = Integer.parseInt(JOptionPane.showInputDialog(null,
                                    logo + "\n\nInsira o Número do voo que deseja procurar", "Buscar", 1));
                            List<Voo> voosEncontrados = sistema.buscarVoosPorNumero(numVoo);
                            if (!voosEncontrados.isEmpty()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Voos encontrados para o número: ").append(numVoo).append("\n\n");
                                for (Voo voo : voosEncontrados) {
                                    sb.append(voo).append("\n\n");
                                }
                                JOptionPane.showMessageDialog(null, logo + "\n\n" + sb.toString(), "Menu", 1);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        logo + "\n\nNenhum voo encontrado para o número: " + numVoo, "Menu", 1);
                            }
                        }

                        else if (opcaoAuxiliar == 1) { // Procura Voos de acordo com a Origem
                            String origem = JOptionPane.showInputDialog(null,
                                    logo + "\n\nInsira a origem do voo que deseja procurar", "Buscar", 1);
                            List<Voo> voosEncontrados = sistema.buscarVoosPorOrigem(origem);
                            if (!voosEncontrados.isEmpty()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Voos encontrados para origem: ").append(origem).append("\n\n");
                                for (Voo voo : voosEncontrados) {
                                    sb.append(voo).append("\n\n");
                                }
                                JOptionPane.showMessageDialog(null, logo + "\n\n" + sb.toString(), "Menu", 1);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        logo + "\n\nNenhum voo encontrado para a origem: " + origem, "Menu", 1);
                            }
                        }

                        else if (opcaoAuxiliar == 2) { // Procura Voos de acordo com o Destino
                            String destino = JOptionPane.showInputDialog(null,
                                    logo + "\n\nInsira o destino do voo que deseja procurar", "Buscar", 1);
                            List<Voo> voosEncontrados = sistema.buscarVoosPorDestino(destino);
                            if (!voosEncontrados.isEmpty()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Voos encontrados para origem: ").append(destino).append("\n\n");
                                for (Voo voo : voosEncontrados) {
                                    sb.append(voo).append("\n\n");
                                }
                                JOptionPane.showMessageDialog(null, logo + "\n\n" + sb.toString(), "Menu", 1);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        logo + "\n\nNenhum voo encontrado para o destino: " + destino, "Menu", 1);
                            }
                        }
                    }

                    break;
                case 2:
                    opcaoAuxiliar = JOptionPane.showOptionDialog(null, logo + "\n\nO que você deseja fazer?",
                            "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            opcoesBuscarPassagem,
                            opcoesBuscarPassagem[0]);

                    if (opcaoAuxiliar == 0) {
                        if (reservas.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Não há reservas registradas.");
                        } else {
                            StringBuilder sb = new StringBuilder();
                            for (Passagem passagem : reservas) {
                                sb.append(passagem.toString()).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, logo + "\n\n" + sb.toString());
                        }
                    }

                    else if (opcaoAuxiliar == 1) { // Procura passagens de acordo com uma caracteristica especifica
                        opcaoBuscaPassageiro = JOptionPane.showOptionDialog(null,
                                logo + "\n\nPor qual opção você deseja buscar?",
                                "Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                opcoesBuscarPassagem2,
                                opcoesBuscarPassagem2[0]);

                        if (opcaoBuscaPassageiro == 0) { // Procura passagens de acordo com o Número do Voo
                            int numVoo = Integer.parseInt(JOptionPane.showInputDialog(null,
                                    logo + "\n\nInsira o Número do voo que deseja procurar", "Buscar", 1));
                            List<Passagem> passagensEncontradas = reserva.buscarVoosPorNumero(numVoo);
                            if (!passagensEncontradas.isEmpty()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Passagens encontradas para o número: ").append(numVoo).append("\n\n");
                                for (Passagem passagem : passagensEncontradas) {
                                    sb.append(passagem.toString()).append("\n");
                                }
                                JOptionPane.showMessageDialog(null, logo + "\n\n" + sb.toString(), "Menu", 1);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        logo + "\n\nNenhuma passagem encontrada para o número de voo: " + numVoo,
                                        "Menu", 1);
                            }
                        }

                        if (opcaoBuscaPassageiro == 1) { // Procura passagens de acordo com nomes
                            String nome = JOptionPane.showInputDialog(null,
                                    logo + "\n\nInsira o nome do passageiro:");
                            List<Passagem> passagensEncontradas = reserva.buscarPorNome(nome);
                            if (passagensEncontradas.isEmpty()) {
                                JOptionPane.showMessageDialog(null,
                                        "Não foram encontradas passagens para o passageiro " + nome);
                            } else {
                                StringBuilder sb = new StringBuilder();
                                for (Passagem passagem : passagensEncontradas) {
                                    sb.append(passagem.toString()).append("\n");
                                }
                                JOptionPane.showMessageDialog(null, sb.toString());
                            }
                        }

                        if (opcaoBuscaPassageiro == 2) { // Procura passagens de acordo com o número de passaporte
                            String numeroPassaporte = JOptionPane.showInputDialog(null,
                                    logo + "\n\nInsira o número do passaporte:");
                            List<Passagem> passagensEncontradas = reserva
                                    .buscarPorNumeroPassaporte(numeroPassaporte);
                            if (passagensEncontradas.isEmpty()) {
                                JOptionPane.showMessageDialog(null,
                                        "Não foram encontradas passagens para o número de passaporte "
                                                + numeroPassaporte);
                            } else {
                                StringBuilder sb = new StringBuilder();
                                for (Passagem passagem : passagensEncontradas) {
                                    sb.append(passagem.toString()).append("\n");
                                }
                                JOptionPane.showMessageDialog(null, sb.toString());
                            }
                        }

                    }

                    break;
                case 3:
                    // Encerra o programa
                    System.exit(0);
            }
        } while (opcao != 3);

    }
}
