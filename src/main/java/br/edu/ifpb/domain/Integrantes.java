package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.List;

public interface Integrantes extends Serializable {

    public Integrante nova(Integrante integrante);

    public List<Integrante> todas();

    public void excluir(Integrante integrante);

    public Integrante atualizar(Integrante integrante);

    public Integrante localizarIntegranteComId(Integer id);
}
