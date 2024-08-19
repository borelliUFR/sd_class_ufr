public class Sinonimo {

    // Array de strings que armazena grupos de sinônimos
    private String dic[];

    // Construtor que inicializa a lista de sinônimos
    public Sinonimo() {
        preenche(); // Preenche o array com sinônimos
    }

    // Método privado para preencher o array com sinônimos
    private void preenche() {
        dic = new String[8];
        
        // Inicializa o array com grupos de sinônimos
        dic[0] = "Importante Significativo Valoroso Respeitável";
        dic[1] = "Apresentar Expor Exibir Publicar";
        dic[2] = "Processo Método Decurso Sequência";
        dic[3] = "Trabalho Ofício Ocupação Serviço";
        dic[4] = "Necessário Indispensável Fundamental Substancial";
        dic[5] = "Feliz Alegre Afortunado Bem-aventurado";
        dic[6] = "Computar Estimar Calcular Avaliar";
        dic[7] = "Através Por entre Transversalmente";
    }

    // Método para consultar sinônimos de uma palavra
    public String consulta(String palavra) {
        // Mensagem padrão caso a palavra não seja encontrada
        String resposta = "Palavra nao encontrada";
        // Percorre o array de sinônimos
        for (int i = 0; i < 8; i++) {
            // Verifica se o grupo de sinônimos contém a palavra consultada
            if (dic[i].toUpperCase().contains(palavra.toUpperCase())) 
                // Remove a palavra da resposta e atualiza a mensagem
                resposta = dic[i].replace(palavra, "").trim();
        }

        return resposta;
    }
}
