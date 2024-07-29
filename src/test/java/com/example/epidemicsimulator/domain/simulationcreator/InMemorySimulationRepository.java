package com.example.epidemicsimulator.domain.simulationcreator;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

class InMemorySimulationRepository implements SimulationRepository{

    private final Map<Long, Simulation> database = new ConcurrentHashMap<>();
    private final AtomicLong index = new AtomicLong(0);

    @Override
    public <S extends Simulation> S save(S entity) {
        if (entity.getId() == null) {
            long newId = index.incrementAndGet();
            Simulation simulationToSave = new Simulation(
                    newId,
                    entity.getSimulationName(),
                    entity.getPopulationSize(),
                    entity.getInitialInfected(),
                    entity.getInfectionRate(),
                    entity.getMortalityRate(),
                    entity.getInfectionDuration(),
                    entity.getDeathDuration(),
                    entity.getSimulationDuration(),
                    entity.getDailyDataList());
            database.put(newId, simulationToSave);
            return (S) simulationToSave;
        } else {
            database.put(entity.getId(), entity);
            return entity;
        }
    }

    @Override
    public Optional<Simulation> findById(Long aLong) {
        return Optional.ofNullable(database.get(aLong));
    }

    @Override
    public List<Simulation> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void deleteById(Long aLong) {
        database.remove(aLong);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Simulation> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Simulation> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Simulation> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Simulation getOne(Long aLong) {
        return null;
    }

    @Override
    public Simulation getById(Long aLong) {
        return null;
    }

    @Override
    public Simulation getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Simulation> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Simulation> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Simulation> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Simulation> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Simulation> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Simulation> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Simulation, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Simulation> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Simulation> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Simulation entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Simulation> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Simulation> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Simulation> findAll(Pageable pageable) {
        return null;
    }
}
