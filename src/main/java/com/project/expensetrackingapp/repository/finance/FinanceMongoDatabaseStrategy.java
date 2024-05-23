package com.project.expensetrackingapp.repository.finance;

import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Finance Mongodb Database Strategy access
 * @author Angel Sic
 */
@Repository
public class FinanceMongoDatabaseStrategy implements FinanceDatabaseStrategy{

    @Autowired
    private FinanceRepositoryMongo repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Finance> findByPortfolioId(Long id) {
        List<Finance> lst = findAll();
        return lst.stream().filter(f -> f.getPortfolio().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Finance> findAll() {
        return repository.findAll();
    }

    @Override
    public Finance save(Finance entity) {
        if(entity.getId() == 0) {
            entity.setId(Math.abs(System.currentTimeMillis() + UUID.randomUUID().getMostSignificantBits()));
        }
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Finance> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Finance> findByTypeFinanceId(Long id) {
        List<Finance> lst = findAll();
        return lst.stream().filter(f -> f.getTypeFinance().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Finance> findByCategoryId(Long id) {
        return findAll().stream().filter(f -> f.getCategory().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Finance> findByDateTimeBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByDateTimeBetween(start,end);
    }

    @Override
    public List<Finance> findByAmountBetween(double init, double end) {
        return repository.findByAmountBetween(init, end);
    }

    @Override
    public Finance findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<FinanceReport> getFinanceReport(LocalDateTime start, LocalDateTime end, Long id) {
        AggregationOperation match = Aggregation
                .match(Criteria.where("dateTime").gte(start).lte(end)
                        .and("portfolio.id").is(id));
        AggregationOperation group = Aggregation
                .group("typeFinance.name")
                .sum("amount").as("amount");
        AggregationOperation project = Aggregation.project()
                .and("_id").as("typename")
                .and("amount").as("amount")
                .andExclude("_id");

        TypedAggregation<Finance> aggregation = Aggregation
                .newAggregation(Finance.class, match, group, project);

        AggregationResults<FinanceReport> results = mongoTemplate
                .aggregate(aggregation, "finance", FinanceReport.class);
        return results.getMappedResults();
    }
}
