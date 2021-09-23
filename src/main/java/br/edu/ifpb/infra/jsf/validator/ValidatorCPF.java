package br.edu.ifpb.infra.jsf.validator;

import br.edu.ifpb.domain.CPF;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/07/2021, 09:02:11
 */

@FacesValidator(value = "validator.cpf")
public class ValidatorCPF implements Validator{

    @Override
    public void validate(FacesContext context,UIComponent component,Object value) throws ValidatorException {
        CPF cpf = (CPF) value;
        if(cpf.valido()) return;
        
        throw new ValidatorException(
            new FacesMessage("CPF inválido")
        );
        
    }

}
