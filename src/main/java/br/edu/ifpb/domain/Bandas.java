package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.List;

public interface Bandas extends Serializable {

    public void nova(Banda banda);

    public List<Banda> todas();

    public void excluir(Banda banda);

    public void atualizar(Banda banda);

    public Banda localizarBandaComId(Integer id);

}
