package controller;

import view.Interface;
import model.Atividade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AtividadeController {
    private final Interface tela;
    private String CAMINHO_ARQUIVO = "data.csv";
    private final ArrayList<Atividade> atividades = new ArrayList<>();

    public AtividadeController(Interface tela) {
        String caminhoProjeto = System.getProperty("user.dir");
        CAMINHO_ARQUIVO = caminhoProjeto + "/src/data/data.csv";

        this.tela = tela;

        tela.painelInicio.botaoIniciar.addActionListener(e -> tela.mostrarPainelCRUD());
        tela.painelCRUD.botaoAdicionar.addActionListener(e -> adicionarAtividade());
        tela.painelCRUD.botaoRemover.addActionListener(e -> removerSelecionada());
        tela.painelCRUD.botaoEditar.addActionListener(e -> editarSelecionada());

        try{
            carregarDoArquivo();
        }
        catch(FileNotFoundException f){
            System.err.println(f);
        }

        /* tela.painelCRUD.botaoAdicionar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adicionarAtividade();
                }
        }); */
    }

    private void adicionarAtividade() {
        String nome = tela.painelCRUD.campoNome.getText().trim();
        String desc = tela.painelCRUD.campoDesc.getText().trim();
        String data = tela.painelCRUD.campoData.getText().trim();

        if (nome.isEmpty() || data.isEmpty()) {
            JOptionPane.showMessageDialog(tela.painelCRUD, "Nome e data são obrigatórios.");
            return;
        }

        int indiceEdicao = tela.painelCRUD.getIndiceEdicao();
        if (indiceEdicao >= 0) {
            // Editando atividade existente
            Atividade atividade = atividades.get(indiceEdicao);
            atividade.setTitulo(nome);
            atividade.setDescricao(desc);
            atividade.setDataEntrega(data);

            DefaultTableModel model = tela.painelCRUD.modeloTabela;
            model.setValueAt(nome, indiceEdicao, 0);
            model.setValueAt(desc, indiceEdicao, 1);
            model.setValueAt(data, indiceEdicao, 2);

            tela.painelCRUD.setIndiceEdicao(-1);
            tela.painelCRUD.botaoAdicionar.setText("Adicionar");
            salvarEmArquivo();
        } else {
            // Nova atividade
            Atividade nova = new Atividade(nome, desc, data);
            atividades.add(nova);

            tela.painelCRUD.modeloTabela.addRow(new String[]{nome, desc, data});
            salvarEmArquivo();
        }

        limparCampos();
    }

    private void removerSelecionada() {
        int index = tela.painelCRUD.table.getSelectedRow();
        if (index != -1) {
            atividades.remove(index);
            tela.painelCRUD.modeloTabela.removeRow(index);
        }

        salvarEmArquivo();
    }

    private void editarSelecionada() {
        int index = tela.painelCRUD.table.getSelectedRow();
        if (index >= 0) {
            Atividade atividade = atividades.get(index);
            tela.painelCRUD.campoNome.setText(atividade.getTitulo());
            tela.painelCRUD.campoDesc.setText(atividade.getDescricao());
            tela.painelCRUD.campoData.setText(atividade.getDataEntrega());

            tela.painelCRUD.setIndiceEdicao(index);
            tela.painelCRUD.botaoAdicionar.setText("Salvar");
        } else {
            JOptionPane.showMessageDialog(tela.painelCRUD, "Selecione uma atividade para editar.");
        }
    }

    private void limparCampos() {
        tela.painelCRUD.campoNome.setText("");
        tela.painelCRUD.campoDesc.setText("");
        tela.painelCRUD.campoData.setText("");
        tela.painelCRUD.table.clearSelection();
    }

    private void salvarEmArquivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Atividade atividade : atividades) {
                writer.printf("%s,%s,%s%n",
                        atividade.getTitulo().replace(",", " "),
                        atividade.getDescricao().replace(",", " "),
                        atividade.getDataEntrega().replace(",", " "));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(tela.painelCRUD, "Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    private void carregarDoArquivo() throws FileNotFoundException{
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()){
            throw new FileNotFoundException("Arquivo não encontrado");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",", -1);
                Atividade a = new Atividade(partes[0], partes[1], partes[2]);
                if (partes.length == 3) {
                    atividades.add(a);
                    tela.painelCRUD.modeloTabela.addRow(new String[]{partes[0], partes[1], partes[2]});
                }
                else{
                    System.out.println("Atividade - " + a + " não salva, partes insuficientes");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(tela.painelCRUD, "Erro ao carregar arquivo: " + e.getMessage());
        }
    }

}