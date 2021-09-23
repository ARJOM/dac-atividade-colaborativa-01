package br.edu.ifpb.infra.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.Bandas;
import br.edu.ifpb.domain.Integrante;

public class BandasJDBC implements Bandas {

    private Connection connection;

    public BandasJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://host-banco:5432/pratica", "postgres",
                    "postgres");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IntegrantesJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public Banda nova (Banda banda) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "INSERT INTO banda(localDeOrigem, nomeFantasia) VALUES(?,?) RETURNING *; "
                    );
            statement.setString(1,banda.getLocalDeOrigem());
            statement.setString(2,banda.getNomeFantasia());
            ResultSet result = statement.executeQuery();
            if(result.next())
                return criarBanda(result);
        } catch (SQLException e) {
            Logger.getLogger(BandasJDBC.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }

    @Override
    public Banda localizarBandaComId (Integer id) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "SELECT * FROM banda WHERE id=?"
                    );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return criarBanda(resultSet);
        } catch (SQLException e) {
            Logger.getLogger(BandasJDBC.class.getName()).log(Level.SEVERE,null,e);;
        }
        return null;
    }

    @Override
    public Banda atualizar(Banda banda) {
        try{
            PreparedStatement statement = connection
                    .prepareStatement(
                            "UPDATE banda SET localDeOrigem=?, nomeFantasia=? WHERE id=? RETURNING *"
                    );
            statement.setString(1, banda.getLocalDeOrigem());
            statement.setString(2, banda.getNomeFantasia());
            statement.setInt(3, banda.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return criarBanda(resultSet);
        } catch (SQLException e) {
            Logger.getLogger(BandasJDBC.class.getName()).log(Level.SEVERE,null,e);;
        }
        return null;
    }

    @Override
    public List<Banda> todas() {
        try {
            List<Banda> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement("SELECT * FROM banda").executeQuery();
            while (result.next()) {
                lista.add(criarBanda(result));
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public void excluir(Banda banda) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM banda WHERE id=?");
            statement.setInt(1, banda.getId());
            statement.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(BandasJDBC.class.getName()).log(Level.SEVERE, null, e);
            ;
        }
    }

    @Override
    public void alocarIntegrante(Banda banda, Integrante integrante){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO integrante_banda(id_banda, id_integrante) VALUES(?,?) RETURNING *; ");
            statement.setInt(1, banda.getId());
            statement.setInt(2, integrante.getId());
            statement.executeQuery();
        } catch (Exception e) {
            Logger.getLogger(BandasJDBC.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void desalocarIntegrante(Banda banda, Integrante integrante){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM integrante_banda WHERE id_banda=? AND id_integrante=?");
            statement.setInt(1, banda.getId());
            statement.setInt(2, integrante.getId());
            statement.executeQuery();
        } catch (Exception e) {
            Logger.getLogger(BandasJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private Banda criarBanda(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String localDeOrigem = resultSet.getString("localDeOrigem");
        String nomeFantasia = resultSet.getString("nomeFantasia");

        return new Banda(id, localDeOrigem, nomeFantasia);
    }

}
