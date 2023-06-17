package ru.doctorixx.applogstorage.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.doctorixx.applogstorage.domain.LogReport;

@Repository
public interface ReportsRepo extends CrudRepository<LogReport, Long> {
}
