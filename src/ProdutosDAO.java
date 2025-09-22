

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto) {
    conn = new conectaDAO().connectDB();
    
    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";
    boolean sucesso = false;

    try {
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        prep.execute();
        prep.close();
        sucesso = true;
    } catch (SQLException e) {
        
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
    System.err.println("Erro ao conectar ao banco:  " + e.getMessage());
}
    }
    return sucesso;
}
    
    public ArrayList<ProdutosDTO> listarProdutos() {
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    conn = new conectaDAO().connectDB();
    String sql = "SELECT * FROM produtos";

    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            listagem.add(produto);
        }
    } catch (SQLException e) {
       
    } finally {
        try {
            if (resultset != null) resultset.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            
        }
    }
    return listagem;
}
    
    
    
        
}

