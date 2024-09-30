package agendamentos;
import java.sql.*;

public class ConexaoBanco {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/agendamentos";
        String user = "postgres";
        String password = "admin";

        try {
            Class.forName("org.postgresql.Driver"); // Certifique-se que o driver foi adicionado ao projeto
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão estabelecida com sucesso!");
            return conn;
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao carregar o driver: " + ex);
        }
        return null;
    }
}
