package controller;

import view.Interface;
import model.Atividade;
import javax.swing.JOptionPane;

public class AtividadeController {
    private final Interface tela;

    public AtividadeController(Interface tela) {
        this.tela = tela;

        tela.painelInicio.botaoIniciar.addActionListener(e -> {
            tela.mostrarPainelCRUD();
        });

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
            // Salvar edição existente
            Atividade atividade = tela.painelCRUD.modeloLista.get(indiceEdicao);
            atividade.setTitulo(nome);
            atividade.setDescricao(desc);
            atividade.setDataEntrega(data);
            tela.painelCRUD.lista.repaint(); // Atualiza visualização
            indiceEdicao = -1;
            tela.painelCRUD.botaoAdicionar.setText("Adicionar");
        } else {
            // Adicionar nova atividade
            Atividade nova = new Atividade(nome, desc, data);
            tela.painelCRUD.modeloLista.addElement(nova);
        }

        // Limpar campos
        tela.painelCRUD.campoNome.setText("");
        tela.painelCRUD.campoDesc.setText("");
        tela.painelCRUD.campoData.setText("");
        tela.painelCRUD.lista.clearSelection();
    }

    private void removerSelecionada() {
        int index = tela.painelCRUD.lista.getSelectedIndex();
        if (index != -1) {
            tela.painelCRUD.modeloLista.remove(index);
        }
    }

    private void editarSelecionada(){
        int index = tela.painelCRUD.lista.getSelectedIndex();
        if (index >= 0) {
            Atividade atividade = tela.painelCRUD.modeloLista.get(index);
            tela.painelCRUD.campoNome.setText(atividade.getTitulo());
            tela.painelCRUD.campoDesc.setText(atividade.getDescricao());
            tela.painelCRUD.campoData.setText(atividade.getDataEntrega());
            tela.painelCRUD.setIndiceEdicao(index);
            tela.painelCRUD.botaoAdicionar.setText("Salvar");
        } else {
            JOptionPane.showMessageDialog(tela.painelCRUD, "Selecione uma atividade para editar.");
        }
    }
}