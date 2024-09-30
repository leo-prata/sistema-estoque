/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

package trabalhoPratico.view;

import trabalhoPratico.model.Categoria;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author leopp
 */
public class TelaCategoria extends JFrame {
    
    private DefaultListModel<Categoria> listaModel;
    private JList<Categoria> listaCategorias;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnEditar;

    public TelaCategoria() {
        super("Gerenciamento de Categorias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);

        listaModel = new DefaultListModel<>();
        listaCategorias = new JList<>(listaModel);
        JScrollPane scrollPane = new JScrollPane(listaCategorias);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel();
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        btnEditar = new JButton("Editar");
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnRemover);
        panelBotoes.add(btnEditar);

        add(panelBotoes, BorderLayout.SOUTH);
    }

    public void carregaCategorias(List<Categoria> categorias) {
        listaModel.clear();
        for (Categoria categoria : categorias) {
            listaModel.addElement(categoria);
        }
    }

    public JButton getBtnAdicionar() {
        return btnAdicionar;
    }

    public JButton getBtnRemover() {
        return btnRemover;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JList<Categoria> getListaCategorias() {
        return listaCategorias;
    }
}
