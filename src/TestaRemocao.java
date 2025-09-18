import java.sql.*;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        try(Connection connection = factory.recuperarConexao()) {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Produto WHERE ID > ?");
            stm.setInt(1, 2);
            stm.execute();

            Integer linhasModificadas = stm.getUpdateCount();
            System.out.println("Quantidade de linhas modificadas:" + linhasModificadas);
        }
    }
}
