package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.List;

public interface Bandas extends Serializable {

    public Banda nova(Banda banda);

    public List<Banda> todas();

    public void excluir(Banda banda);

    public Banda atualizar(Banda banda);

    public Banda localizarBandaComId(Integer id);

    public void alocarIntegrante(Banda banda, Integrante integrante);

    public void desalocarIntegrante(Banda banda, Integrante integrante);
}
