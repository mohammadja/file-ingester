package ir.sahab.sahabino.rulesEvaluator;

import ir.sahab.sahabino.common.database.SQLRecord;
import ir.sahab.sahabino.common.log.Log;

public interface Rule {
    SQLRecord update(Log log);
}
