package ir.sahab.sahabino.utility;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

public class LogDeserializer implements Deserializer<Log> {
    @Override
    public Log deserialize(String topic, byte[] data) {
        String logJson = new String(data);
        Gson gson = new Gson();
        return (Log) gson.fromJson(logJson, Log.class);
    }
}
