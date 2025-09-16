import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();
        connection.setAutoCommit(false);
        PreparedStatement stm = connection.prepareStatement("INSERT INTO Produto (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        try {
            adicionarVariavel(stm, "SmartTV", "45 polegadas");
            adicionarVariavel(stm, "Radio", "Radio de bateria");

            connection.commit();
            stm.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("RollBack Executado.");
            connection.rollback();
        }
    }
    private static void adicionarVariavel(PreparedStatement stm, String nome, String descricao) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

        stm.execute();
        if (nome.equals("Radio")){
            throw new RuntimeException("Sai fora com isso ai dog, pode radio nao");
        }
        ResultSet rst = stm.getGeneratedKeys();
        while(rst.next()) {
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi:" + id);
        }
        rst.close();
    }
}
