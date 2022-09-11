package ir.sahab.sahabino.rulesEvaluator;

import ir.sahab.sahabino.utility.Log;

public interface Rule {
    SQLRecord update(Log log);
}
