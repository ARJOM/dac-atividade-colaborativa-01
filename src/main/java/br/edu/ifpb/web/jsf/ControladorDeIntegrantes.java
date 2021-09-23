package br.edu.ifpb.web.jsf;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.domain.Integrantes;
import br.edu.ifpb.infra.persistence.database.IntegrantesJDBC;

@Named
@SessionScoped
public class ControladorDeIntegrantes implements Serializable {

    private Integrante integrante;
    private Integrantes integrantes = new IntegrantesJDBC();

    // public String imprimirNome(){
    // System.out.println("nome: " + this.nome);
    // // return null; //redirecionamento a própria página
    // // return "home.xhtml"; //redirecionamento para home.xhtml
    // return "home?faces-redirect=true"; //redirecionamento para home.xhtml
    // }

    public String adicionar(){
        Integrante integranteLocalizado = this.integrantes.localizarIntegranteComId(
            this.integrante.getId()
            );
            if (Integrante.fake().equals(integranteLocalizado)) {
                integrantes.nova(integrante);
            }else{
                integrantes.atualizar(integrante);        
            }
            integrante = new Integrante(1,"Jubileu",LocalDate.now(),new CPF(""));
            return "list?faces-redirect=true";
        }

    public List<Integrante> todosOsIntegrantes() {
        return integrantes.todas();
    }

    public String excluir(Integrante integrante) {
        integrantes.excluir(integrante);
        return "list?faces-redirect=true";
    }

    public String editar(Integrante integrante) {
        this.integrante = integrante;
        return "edit";
    }

    

    public Integrante getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

}
