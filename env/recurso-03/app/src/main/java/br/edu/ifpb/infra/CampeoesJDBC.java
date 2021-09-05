package br.edu.ifpb.infra;

import br.edu.ifpb.domain.Campeao;
import br.edu.ifpb.domain.Campeoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CampeoesJDBC implements Campeoes {

    private Connection connection;

    public CampeoesJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/lol",
                    "postgres","postgres"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CampeoesJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Campeao> listar() {
        try {
            List<Campeao> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                    "SELECT * FROM campeoes"
            ).executeQuery();
            while (result.next()) {
                lista.add(
                        criarCampeao(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    private Campeao criarCampeao(ResultSet resultSet) throws SQLException {
        String nome = resultSet.getString("nome");
        int id = resultSet.getInt("id");
        return new Campeao(id,nome);
    }
}
