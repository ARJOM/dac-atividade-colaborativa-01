package br.edu.ifpb.infra.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.domain.Integrantes;

public class IntegrantesJDBC implements Integrantes{ 

    private Connection connection;

    public IntegrantesJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/pratica",
                    "postgres","postgres"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IntegrantesJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public Integrante nova (Integrante integrante) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "INSERT INTO integrante(nome, dataDeNascimento,cpf) VALUES(?,?,?) RETURNING *; "
                    );
            statement.setString(1,integrante.getNome());
            
            statement.setDate(2,java.sql.Date.valueOf(integrante.getDataDeNascimento()));
            statement.setString(3,integrante.getCpf().valor());
            ResultSet result = statement.executeQuery();
            if(result.next())
                return criarIntegrante(result);
        } catch (SQLException e) {
            Logger.getLogger(IntegrantesJDBC.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }

    @Override
    public Integrante localizarIntegranteComId (Integer id) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "SELECT * FROM integrante WHERE id=?"
                    );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return criarIntegrante(resultSet);
        } catch (SQLException e) {
            Logger.getLogger(IntegrantesJDBC.class.getName()).log(Level.SEVERE,null,e);;
        }
        return null;
    }

    @Override
    public Integrante atualizar(Integrante integrante) {
        try{
            PreparedStatement statement = connection
                    .prepareStatement(
                            "UPDATE integrante  SET nome=?, dataDeNascimento=?, cpf=? WHERE id=? RETURNING *"
                    );
            statement.setString(1, integrante.getNome());
            statement.setDate(2,java.sql.Date.valueOf(integrante.getDataDeNascimento()));
            statement.setString(3, integrante.getCpf().valor());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return criarIntegrante(resultSet);
        } catch (SQLException e) {
            Logger.getLogger(BandasJDBC.class.getName()).log(Level.SEVERE,null,e);;
        }
        return null;
    }

    @Override
    public List<Integrante> todas() {
        try {
            List<Integrante> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                    "SELECT * FROM integrante"
            ).executeQuery();
            while (result.next()) {
                lista.add(
                    criarIntegrante(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public void excluir(Integrante integrante) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM integrante WHERE id=?");
            statement.setInt(1, integrante.getId());
            statement.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(IntegrantesJDBC.class.getName()).log(Level.SEVERE, null, e);
            ;
        }
    }

    private Integrante criarIntegrante(ResultSet resultSet) throws SQLException {
        
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        LocalDate dataDeNascimento = resultSet.getDate("dataDeNascimento").toLocalDate();
        CPF cpf = new CPF (resultSet.getString("cpf"));
        return new Integrante(id,nome,dataDeNascimento,cpf);
    }

    // private Date converterData (){
    //     Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    // }
 }


