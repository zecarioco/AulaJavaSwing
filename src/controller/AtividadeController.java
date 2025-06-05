package controller;

import view.Interface;
import model.Atividade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AtividadeController {
    private final Interface tela;
    private final ArrayList<Atividade> atividades = new ArrayList<>();

    public AtividadeController(Interface tela) {
        this.tela = tela;

        tela.painelInicio.botaoIniciar.addActionListener(e -> tela.mostrarPainelCRUD());
        tela.painelCRUD.botaoAdicionar.addActionListener(e -> adicionarAtividade());
        tela.painelCRUD.botaoRemover.addActionListener(e -> removerSelecionada());
        tela.painelCRUD.botaoEditar.addActionListener(e -> editarSelecionada());

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
        } else {
            // Nova atividade
            Atividade nova = new Atividade(nome, desc, data);
            atividades.add(nova);

            tela.painelCRUD.modeloTabela.addRow(new String[]{nome, desc, data});
        }

        limparCampos();
    }

    private void removerSelecionada() {
        int index = tela.painelCRUD.table.getSelectedRow();
        if (index != -1) {
            atividades.remove(index);
            tela.painelCRUD.modeloTabela.removeRow(index);
        }
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
}