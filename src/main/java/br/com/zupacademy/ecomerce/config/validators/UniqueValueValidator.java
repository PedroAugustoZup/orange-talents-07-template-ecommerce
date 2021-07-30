package br.com.zupacademy.ecomerce.config.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String field;
    private Class<?> table;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        this.table = constraintAnnotation.table();
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null){
            return true;
        }

        List<?> list = manager.createQuery("select 1 from "+table.getName()+" where "+field+"=:value")
                .setParameter("value", o)
                .getResultList();
        if(list.isEmpty()){
            return true;
        }
        return false;
    }
}
