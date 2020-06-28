/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo;

//Ctrl + Shift + i

import br.newtonpaiva.modelo.erros.NomeTarefaInvalidoException;
import br.newtonpaiva.modelo.erros.PrioridadeTarefaInvalidaException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import java.sql.DriverManager; // utilizado para carregar as classes do Driver do JDBC do MySQL
import java.sql.Connection; // responsável por manipular conexão no banco de dados
import java.sql.Date;
import java.sql.PreparedStatement; // responsável por executar comandos na base dados
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * NOTA: Código iniciado a partir do commit do Tarley realizado em 02/06/20 - https://github.com/tarley/ControleDeTarefasSolucao
 * @author Cézar Willian Ferreira - RA 12106991
 */

public class Tarefa implements Comparable<Tarefa>{
    
    private static final String DB_HOST = "jdbc:mysql://localhost:3306/gestao_tarefas";
    private static final String DB_NOME = "root";
    private static final String DB_SENHA = "";
    
    /* Atributos */
    private Integer id; // 10
    private String nome;
    private Integer prioridade;
    private Calendar dataLimite;
    private String situacao;
    private Integer percentual;
    private String descricao;

    public Tarefa() {
    }

    /**
     * Construtor de tarefa basica.
     * @param nome Este é o nome da tarefa.
     * @param prioridade Esta é a prioridade da tarefa
     */
    public Tarefa(String nome, Integer prioridade) {
        this.nome = nome;
        this.prioridade = prioridade;
    }

    public Tarefa(Integer id, String nome, Integer prioridade, Calendar dataLimite, String situacao, Integer percentual, String descricao) throws NomeTarefaInvalidoException, PrioridadeTarefaInvalidaException {
        
        if(nome == null || nome.trim().isEmpty()) {
            throw new NomeTarefaInvalidoException();
        }
        
        if(prioridade == null) {
            throw new PrioridadeTarefaInvalidaException();
        }
        
        
        this.id = id;
        this.nome = nome;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
        this.situacao = situacao;
        this.percentual = percentual;
        this.descricao = descricao;
    }

    
    
    /**
     * Esse método grava na base de dados na tabela chamada tb_tarefa
     * um registro de tarefa.
     * 
     * @throws IOException Erro caso o arquivo tarefa.csv não exista no disco.
     */
    public void salvar() throws IOException {
        Path arquivo = Paths.get(TAREFA_CSV);
       
        String conteudo = gerarLinhaCSV();
        
        Files.write(arquivo, conteudo.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
    
    public int salvarDB() throws SQLException{
                
        String insert = 
                "INSERT INTO tarefa "
                + "(nome, prioridade, data_limite, situacao, percentual, descricao) "
                + "values (?,?,?,?,?,?)";
              
        // Try with a resource - criado a partir do Java 7
        try(
            // as conexões estabelecidas como argumento no try serão fechadas automaticamente
            Connection conn = DriverManager.getConnection(DB_HOST, DB_NOME, DB_SENHA);
            PreparedStatement stm = conn.prepareStatement(insert);
            ){
            
            stm.setString(1, getNome());
            stm.setInt(2, getPrioridade());
            stm.setDate(3, new Date(getDataLimite().getTimeInMillis()));
            stm.setString(4, getSituacao());
            stm.setInt(5, getPercentual());
            stm.setString(6, getDescricao());
            
            int row = stm.executeUpdate();
            
            return row;   
        }     
    }

    private String gerarLinhaCSV() {
        return this.nome + ";" + this.prioridade +
                ";" + this.situacao + ";" + this.percentual +
                ";" + this.descricao + " ;\n";
    }   
   
    /*
    public static List<Tarefa> listar() throws IOException, 
            NomeTarefaInvalidoException, PrioridadeTarefaInvalidaException {
        // Buscar do banco de dados as tarefas
        System.out.println("Executou o listar");
        
        Path arquivo = Paths.get(TAREFA_CSV);
        List<String> linhas = Files.readAllLines(arquivo);
        
        List<Tarefa> listaParaRetorno = new LinkedList<>();
        
        // Percorer as linhas retornadas
        for(String linha : linhas) {
            // Quebrar a string em ;
            String[] informacoes = linha.split(";");
            String nome = informacoes[0];
            Integer prioridade = Integer.parseInt(informacoes[1]);
            Calendar dataLimite = Calendar.getInstance();
            String situacao = informacoes[2];
            Integer percentual = Integer.parseInt(informacoes[3]);
            String descricao = informacoes[4];

            // Criar objeto e popular com as informações
            Tarefa t = new Tarefa(null, nome, prioridade, dataLimite, situacao, percentual, descricao);
            
            // Armazenar em uma lista de tarefas
            listaParaRetorno.add(t);
        }
//        Collections.sort(listaParaRetorno, new Comparator<Tarefa>() {
//            @Override
//            public int compare(Tarefa o1, Tarefa o2) {
//                return o1.getPrioridade().compareTo(o2.getPrioridade());
//            }
//        });
        
        Collections.sort(listaParaRetorno);
        return listaParaRetorno;
    }
    */
    
    public static List<Tarefa> listar() throws IOException, 
            NomeTarefaInvalidoException, PrioridadeTarefaInvalidaException {
        // Buscar do banco de dados as tarefas
        System.out.println("Executou o listar");
        
        Path arquivo = Paths.get(TAREFA_CSV);
        return Files.readAllLines(arquivo)
                    .stream()
                    .map((linha) -> {
                        String[] informacoes = linha.split(";");
                        
                        Tarefa t = new Tarefa();
                        t.setNome(informacoes[0]);
                        t.setPrioridade(Integer.parseInt(informacoes[1]));
                        t.setDataLimite(Calendar.getInstance());
                        t.setSituacao(informacoes[2]);
                        t.setPercentual(Integer.parseInt(informacoes[3]));
                        t.setDescricao(informacoes[4]);
                        
                        return t;
                    })
                    .sorted((Tarefa t1, Tarefa t2 ) -> {
                        return t1.getPercentual().compareTo(t2.getPercentual());
                    })
                    //.filter(t -> t.getSituacao().equals("Em andamento"))
                    .collect(Collectors.toList());
    }
    
    public static List<Tarefa> listarDB() throws SQLException{
        
        String query = "SELECT id_tarefa, nome, prioridade, data_limite, situacao, percentual, descricao "
                     + "FROM tarefa ";
        
        try(
            Connection conn = DriverManager.getConnection(DB_HOST, DB_NOME, DB_SENHA);
            PreparedStatement stm = conn.prepareStatement(query);
            ){
            
            ResultSet rs = stm.executeQuery();
            
            List<Tarefa> lista = new LinkedList<>();
            
            while(rs.next()){
                Tarefa t = new Tarefa();
                t.setId(rs.getInt("id_tarefa"));
                t.setNome(rs.getString("nome"));
                t.setPrioridade(rs.getInt("prioridade"));
                t.setSituacao(rs.getString("situacao"));
                t.setPercentual(rs.getInt("percentual"));
                t.setDescricao(rs.getString("descricao"));
                
                Date dataAux = rs.getDate("data_limite");
                Calendar dataLimite = Calendar.getInstance();
                dataLimite.setTimeInMillis(dataAux.getTime());
                
                t.setDataLimite(dataLimite);
                
                lista.add(t);
            }
            
            return lista;   
        }   
        
    }
    
    public static List<Tarefa> listarDB(String filtro) throws SQLException{
        
        String query = "SELECT id_tarefa, nome, prioridade, data_limite, situacao, percentual, descricao "
                     + "FROM tarefa "
                     + "WHERE nome like ? ";
        
        try(
            Connection conn = DriverManager.getConnection(DB_HOST, DB_NOME, DB_SENHA);
            PreparedStatement stm = conn.prepareStatement(query);
            ){
            
            stm.setString(1, "%" + filtro + "%");
            
            ResultSet rs = stm.executeQuery();
            
            List<Tarefa> lista = new LinkedList<>();
            
            while(rs.next()){
                Tarefa t = new Tarefa();
                t.setId(rs.getInt("id_tarefa"));
                t.setNome(rs.getString("nome"));
                t.setPrioridade(rs.getInt("prioridade"));
                t.setSituacao(rs.getString("situacao"));
                t.setPercentual(rs.getInt("percentual"));
                t.setDescricao(rs.getString("descricao"));
                
                Date dataAux = rs.getDate("data_limite");
                Calendar dataLimite = Calendar.getInstance();
                dataLimite.setTimeInMillis(dataAux.getTime());
                
                t.setDataLimite(dataLimite);
                
                lista.add(t);
            }
            
            return lista;   
        }   
        
    }
    
    /*
    public static List<Tarefa> listar(String filtro) throws IOException, 
            NomeTarefaInvalidoException, PrioridadeTarefaInvalidaException {
        
        List<Tarefa> listaParaRetorno = listar();
        
        Iterator<Tarefa> i = listaParaRetorno.iterator();
        
        while(i.hasNext()) {
            Tarefa t = i.next();
            
            if(!t.getNome().toUpperCase().contains(filtro.toUpperCase()))
                i.remove();
        }
        
        return listaParaRetorno;
    }
    */
    
    public static List<Tarefa> listar(String filtro) throws IOException, 
            NomeTarefaInvalidoException, PrioridadeTarefaInvalidaException {
        
        return listar()
                   .stream()
                   .filter(t -> !t.getNome().toUpperCase().contains(filtro.toUpperCase()))
                   .collect(Collectors.toList());
    }

    public static void excluir(int id) throws IOException, 
            NomeTarefaInvalidoException, PrioridadeTarefaInvalidaException {
        
        List<Tarefa> lista = listar();
        lista.remove(id);
        
        StringBuilder conteudoTotal = new StringBuilder();
        
        for(Tarefa t : lista) {
            String conteudo = t.gerarLinhaCSV();
            
            conteudoTotal.append(conteudo);
        }
        Path arquivo = Paths.get(TAREFA_CSV);
        Files.write(arquivo, conteudoTotal.toString().getBytes(), 
                StandardOpenOption.TRUNCATE_EXISTING);
        
    }
    
    public static boolean excluirDB(int id) throws SQLException{
                
        String delete = 
                "DELETE FROM tarefa "
                + "WHERE id_tarefa = ? ";
        
        // Try with a resource - criado a partir do Java 7
        try(
            // as conexões estabelecidas como argumento no try serão fechadas automaticamente
            Connection conn = DriverManager.getConnection(DB_HOST, DB_NOME, DB_SENHA);
            PreparedStatement stm = conn.prepareStatement(delete);
            ){
            
            stm.setInt(1, id);
            
            int linhasAtualizadas = stm.executeUpdate();
            
            return linhasAtualizadas > 0;   
        }     
    }
    
    private static final String TAREFA_CSV = "tarefa.csv";
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
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
     * @return the prioridade
     */
    public Integer getPrioridade() {
        return prioridade;
    }

    /**
     * @param prioridade the prioridade to set
     */
    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    /**
     * @return the dataLimite
     */
    public Calendar getDataLimite() {
        return dataLimite;
    }

    /**
     * @param dataLimite the dataLimite to set
     */
    public void setDataLimite(Calendar dataLimite) {
        this.dataLimite = dataLimite;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the percentual
     */
    public Integer getPercentual() {
        return percentual;
    }

    /**
     * @param percentual the percentual to set
     */
    public void setPercentual(Integer percentual) {
        this.percentual = percentual;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Tarefa{" + "id=" + id + ", nome=" + nome + ", prioridade=" + prioridade + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarefa other = (Tarefa) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
 
        return true;
    }

    @Override
    public int compareTo(Tarefa o) {
//        Integer minhaPrioridade = getPrioridade();
//        Integer outraPrioridade = o.getPrioridade();
//        
//        return minhaPrioridade.compareTo(outraPrioridade);

          String meuNome = getNome();
          String outroNome = o.getNome();
          
          return meuNome.toUpperCase().compareTo(outroNome.toUpperCase()) * -1;
    }
}
