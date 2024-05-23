package com.project.expensetrackingapp.service.typefinance;

import com.project.expensetrackingapp.exception.IdNotFound;
import com.project.expensetrackingapp.exception.typefinance.TypeFinanceAlreadyExist;
import com.project.expensetrackingapp.exception.typefinance.TypeFinanceNotExist;
import com.project.expensetrackingapp.exception.typefinance.TypeFinanceNotFound;
import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.repository.typefinance.TypeFinanceDatabaseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Manage Type Finance CRUD Implementation Data
 * @author Angel Sic
 */
@Service
public class TypeFinanceServiceImpl implements TypeFinanceService{

    private TypeFinanceDatabaseStrategy typeFinanceDatabaseStrategy;

    @Autowired
    public void setDatabaseStrategy(@Qualifier("typeFinanceStrategy") TypeFinanceDatabaseStrategy typeFinanceDatabaseStrategy ){
        this.typeFinanceDatabaseStrategy = typeFinanceDatabaseStrategy;
    }

    @Override
    public TypeFinance saveTypeFinance(TypeFinance typeFinance) {
        if(typeFinance.getName() == null){
            throw new TypeFinanceNotFound();
        }
        TypeFinance newTypeFinance = typeFinanceDatabaseStrategy.findByName(typeFinance.getName());
        if(newTypeFinance == null){
            return typeFinanceDatabaseStrategy.save(typeFinance);
        }
        throw new TypeFinanceAlreadyExist(typeFinance.getName());
    }

    @Override
    public TypeFinance getTypeFinance(String name) {
        return typeFinanceDatabaseStrategy.findByName(name);
    }

    @Override
    public List<TypeFinance> getAllTypeFinance() {
        return typeFinanceDatabaseStrategy.findAll();
    }

    @Override
    public TypeFinance updateTypeFinance(TypeFinance typeFinance) {
        if(typeFinance.getId() == 0){
            throw new IdNotFound("Type Finance is not defined");
        }else if (typeFinance.getName() == null){
            throw new TypeFinanceNotFound();
        }
        TypeFinance newTypeFinance = typeFinanceDatabaseStrategy.findById(typeFinance.getId()).orElse(null);
        if(newTypeFinance != null){
            TypeFinance updTypeFinance = typeFinanceDatabaseStrategy.findByName(typeFinance.getName());
            if(updTypeFinance == null){
                return typeFinanceDatabaseStrategy.save(typeFinance);
            }
            throw new TypeFinanceAlreadyExist(typeFinance.getName());
        }
        throw new TypeFinanceNotExist(typeFinance.getName());
    }

    @Override
    public String deleteTypeFinance(String name) {
        TypeFinance typeFinance = typeFinanceDatabaseStrategy.findByName(name);
        if (typeFinance != null){
            typeFinanceDatabaseStrategy.deleteById(typeFinance.getId());
            return name + " was removed";
        }
        return name + " not exist";
    }
}
