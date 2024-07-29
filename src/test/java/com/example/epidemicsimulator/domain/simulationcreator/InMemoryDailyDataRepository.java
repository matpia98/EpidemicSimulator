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
import java.util.stream.Collectors;

class InMemoryDailyDataRepository implements DailyDataRepository{

    private final Map<Long, DailyData> database = new ConcurrentHashMap<>();
    private final AtomicLong index = new AtomicLong(1);

    @Override
    public void deleteBySimulationId(Long id) {
        database.values().removeIf(dailyData -> dailyData.getSimulation().getId().equals(id));
    }

    @Override
    public void deleteById(Long aLong) {
        database.remove(aLong);
    }

    @Override
    public List<DailyData> findBySimulationId(Long id) {
        return database.values().stream()
                .filter(dailyData -> dailyData.getSimulation().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public <S extends DailyData> List<S> saveAll(Iterable<S> entities) {
        List<S> savedEntities = new ArrayList<>();
        for (S entity : entities) {
            long id = index.getAndIncrement();
            DailyData dailyDataToSave = new DailyData(
                    id,
                    entity.getDay(),
                    entity.getInfected(),
                    entity.getSusceptible(),
                    entity.getDeceased(),
                    entity.getRecovered(),
                    entity.getSimulation()
            );
            database.put(id, dailyDataToSave);
            savedEntities.add((S) dailyDataToSave);
        }
        return savedEntities;
    }

    @Override
    public Optional<DailyData> findById(Long aLong) {
        return Optional.ofNullable(database.get(aLong));
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends DailyData> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends DailyData> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<DailyData> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public DailyData getOne(Long aLong) {
        return null;
    }

    @Override
    public DailyData getById(Long aLong) {
        return null;
    }

    @Override
    public DailyData getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends DailyData> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends DailyData> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends DailyData> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends DailyData> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends DailyData> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends DailyData> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends DailyData, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends DailyData> S save(S entity) {
        return null;
    }



    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<DailyData> findAll() {
        return null;
    }

    @Override
    public List<DailyData> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }



    @Override
    public void delete(DailyData entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends DailyData> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<DailyData> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<DailyData> findAll(Pageable pageable) {
        return null;
    }
}
