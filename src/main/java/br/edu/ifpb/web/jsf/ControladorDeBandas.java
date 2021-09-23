package br.edu.ifpb.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.Bandas;

import br.edu.ifpb.infra.persistence.database.BandasJDBC;


@Named
@SessionScoped
public class ControladorDeBandas implements Serializable{ 

    private Banda banda;
    private Bandas bandas = new BandasJDBC();

//     public String imprimirNome(){
//         System.out.println("nome: " + this.nome);
// //        return null; //redirecionamento a própria página
// //        return "home.xhtml"; //redirecionamento para home.xhtml
//         return "home?faces-redirect=true"; //redirecionamento para home.xhtml
//     }

    public String adicionar(){
        bandas.nova(banda);
        //banda = new Banda(id, localDeOrigem, nomeFantasia)
        return "list?faces-redirect=true";
    }
    
    public List<Banda> todosAsBandas(){
        return bandas.todas();
    }
    
    public Banda getbBanda() {
        return banda;
    }
    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    
    
    
    
    
}
