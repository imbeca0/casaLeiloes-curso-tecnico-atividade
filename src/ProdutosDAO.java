/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep = null;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome,valor,status) VALUES (?,?,?) ";
        conn = new conectaDAO().connectDB();
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "dados inseridos.");
            System.out.println(" Dados inseridos com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir\n" + e.getMessage());
            System.out.println("Erro inserindo : " + e.getMessage());
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        conn = new conectaDAO().connectDB();
        String sql = "select * from produtos";
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            listagem.clear();

            while (resultset.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));

                listagem.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos\n" + e.getMessage());
            System.out.println("Erro ao listar produtos: " + e.getMessage());

        }
    

    return listagem ;
    }

}
