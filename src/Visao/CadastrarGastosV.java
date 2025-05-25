package Visao;

import Controle.CategoriaC;
import Controle.TransacaoC;
import Modelo.CategoriaM;
import Modelo.TransacaoM;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CadastrarGastosV extends JFrame {

    private CategoriaC categoriaController;
    private TransacaoC transacaoController;
    private int usuarioId;

    public CadastrarGastosV(int usuarioId) throws SQLException {
        this.usuarioId = usuarioId;
        categoriaController = new CategoriaC();
        transacaoController = new TransacaoC();
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastrar Gastos - Gerenciamento de Gastos");
        setSize(450, 350);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // 🎨 Cores
        Color backgroundColor = Color.WHITE;
        Color tituloColor = new Color(33, 37, 41);
        Color buttonColor = new Color(0, 123, 255);
        Color buttonBackColor = new Color(108, 117, 125);

        getContentPane().setBackground(backgroundColor);

        // 🔖 Título
        JLabel labelTitulo = new JLabel("Cadastrar Gastos");
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelTitulo.setForeground(tituloColor);

        // 🔖 Labels
        JLabel labelDescricao = new JLabel("Descrição:");
        JLabel labelValor = new JLabel("Valor:");
        JLabel labelCategoria = new JLabel("Categoria:");

        labelDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelValor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        // ✏️ Campos
        JTextField textDescricao = new JTextField(20);
        textDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JTextField textValor = new JTextField(10);
        textValor.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JComboBox<CategoriaM> comboCategoria = new JComboBox<>();
        carregarCategorias(comboCategoria);

        // ✅ Botão Cadastrar
        JButton buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        buttonCadastrar.setBackground(buttonColor);
        buttonCadastrar.setForeground(Color.WHITE);
        buttonCadastrar.setFocusPainted(false);
        buttonCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonCadastrar.addActionListener(e -> {
            String descricao = textDescricao.getText();
            double valor;
            try {
                valor = Double.parseDouble(textValor.getText());
                if (valor <= 0) {
                    throw new NumberFormatException("O valor deve ser positivo.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CategoriaM categoria = (CategoriaM) comboCategoria.getSelectedItem();
            if (categoria == null) {
                JOptionPane.showMessageDialog(this, "Selecione uma categoria válida.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String data = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            TransacaoM transacao = new TransacaoM(0, descricao, valor, data, categoria.getId(), 1, usuarioId);

            if (transacaoController.cadastrarTransacao(descricao, valor, data, categoria.getId(), 1, usuarioId)) {
                JOptionPane.showMessageDialog(this, "Gasto cadastrado com sucesso!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar gasto. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 📐 Layout com GridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);

        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelTitulo, gbc);

        // Descrição
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(labelDescricao, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(textDescricao, gbc);

        // Valor
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(labelValor, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(textValor, gbc);

        // Categoria
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(labelCategoria, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(comboCategoria, gbc);

        // Painel para os botões, centralizados
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setBackground(backgroundColor);
        painelBotoes.add(buttonCadastrar);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(painelBotoes, gbc);

        setBorderPadding();
    }

    private void setBorderPadding() {
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void carregarCategorias(JComboBox<CategoriaM> comboCategoria) {
        List<CategoriaM> categorias = categoriaController.listarPorUsuario(usuarioId);
        System.out.println("Categorias carregadas: " + categorias.size()); // Debug
        if (categorias != null) {
            for (CategoriaM categoria : categorias) {
                comboCategoria.addItem(categoria);
                System.out.println("Categoria adicionada: " + categoria.getNome()); // Debug
            }
        } else {
            System.out.println("Nenhuma categoria encontrada."); // Debug
        }
    }

}
