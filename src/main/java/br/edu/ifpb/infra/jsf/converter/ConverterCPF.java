package br.edu.ifpb.infra.jsf.converter;

import br.edu.ifpb.domain.CPF;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converter.cpf", forClass = CPF.class)
public class ConverterCPF implements Converter{

    @Override
    public Object getAsObject(FacesContext context,UIComponent component,String value) {
        if(value == null) return null;
        CPF cpf = new CPF(value);
        return cpf;
    }

    @Override
    public String getAsString(FacesContext context,UIComponent component,Object value) {
        if(value == null) return null;
        CPF cpf = (CPF) value;
        return cpf.valor();
    }

}
