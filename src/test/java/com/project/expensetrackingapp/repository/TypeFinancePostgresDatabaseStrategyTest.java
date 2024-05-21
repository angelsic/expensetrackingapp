package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.repository.typefinance.TypeFinancePostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.typefinance.TypeFinanceRepositoryPostgres;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class TypeFinancePostgresDatabaseStrategyTest {

    @InjectMocks
    private TypeFinancePostgresDatabaseStrategy typeFinancePostgresDatabaseStrategy;

    @Mock
    private TypeFinanceRepositoryPostgres typeFinanceRepositoryPostgres;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByName() {
        String typeName = "Test Type";
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setName(typeName);

        when(typeFinanceRepositoryPostgres.findByName(typeName)).thenReturn(typeFinance);

        TypeFinance result = typeFinancePostgresDatabaseStrategy.findByName(typeName);

        assertNotNull(result);
        assertEquals(typeName, result.getName());
    }

    @Test
    void testFindAll() {
        List<TypeFinance> typeFinances = new ArrayList<>();
        typeFinances.add(new TypeFinance());
        typeFinances.add(new TypeFinance());

        when(typeFinanceRepositoryPostgres.findAll()).thenReturn(typeFinances);

        List<TypeFinance> result = typeFinancePostgresDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave() {
        TypeFinance newType = new TypeFinance();
        newType.setName("New Type");

        when(typeFinanceRepositoryPostgres.save(newType)).thenReturn(newType);

        TypeFinance savedType = typeFinancePostgresDatabaseStrategy.save(newType);

        assertNotNull(savedType);
    }

    @Test
    void testDeleteById() {
        Long typeId = 1L;

        typeFinancePostgresDatabaseStrategy.deleteById(typeId);

        verify(typeFinanceRepositoryPostgres, times(1)).deleteById(typeId);
    }

    @Test
    void testFindById() {
        Long typeId = 1L;
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setId(typeId);

        when(typeFinanceRepositoryPostgres.findById(typeId)).thenReturn(Optional.of(typeFinance));

        Optional<TypeFinance> result = typeFinancePostgresDatabaseStrategy.findById(typeId);

        assertNotNull(result);
        assertEquals(Optional.of(typeId), Optional.of(result.get().getId()));
    }
}
