import java.util.*;
import java.io.*;

public class AgendaManager implements GerenciadorContatos {

    private Map<String, Contato> contatos = new HashMap<>();

    @Override
    public void adicionarContato(Contato contato) throws ContatoExistenteException {
        if (contatos.containsKey(contato.getNome().toLowerCase())) {
            throw new ContatoExistenteException("Contato já existe!");
        }
        contatos.put(contato.getNome().toLowerCase(), contato);
    }

    @Override
    public Contato buscarContato(String nome) throws ContatoNaoEncontradoException {
        Contato c = contatos.get(nome.toLowerCase());
        if (c == null) throw new ContatoNaoEncontradoException("Contato não encontrado");
        return c;
    }

    @Override
    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        if (!contatos.containsKey(nome.toLowerCase())) {
            throw new ContatoNaoEncontradoException("Contato não encontrado");
        }
        contatos.remove(nome.toLowerCase());
    }

    @Override
    public List<Contato> listarTodosContatos() {
        return new ArrayList<>(contatos.values());
    }

    @Override
    public List<Contato> listarContatosOrdenados() {
        List<Contato> lista = listarTodosContatos();
        lista.sort(Comparator.comparing(Contato::getNome));
        return lista;
    }

    @Override
    public List<Contato> buscarPorDominioEmail(String dominio) {
        List<Contato> encontrados = new ArrayList<>();
        for (Contato c : contatos.values()) {
            if (c.getEmail().endswith(dominio)) {
                encontrados.add(c);
            }
        }
        return encontrados;
    }

    public void salvarContatosCSV(String nomeArquivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Contato c : contatos.values()) {
                bw.write(c.getNome() + ";" + c.getTelefone() + ";" + c.getEmail());
                bw.newLine();
            }
        }
    }

    public void carregarContatosCSV(String nomeArquivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");
                Contato c = new Contato(p[0], p[1], p[2]);
                contatos.put(c.getNome().toLowerCase(), c);
            }
        }
    }
}
